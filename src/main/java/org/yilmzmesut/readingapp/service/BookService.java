package org.yilmzmesut.readingapp.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yilmzmesut.readingapp.exception.ReadingAppErrorCode;
import org.yilmzmesut.readingapp.exception.ReadingAppException;
import org.yilmzmesut.readingapp.mapper.BookMapper;
import org.yilmzmesut.readingapp.model.dto.BookDTO;
import org.yilmzmesut.readingapp.model.request.BookOrderRequest;
import org.yilmzmesut.readingapp.model.request.UpdateStockRequest;
import org.yilmzmesut.readingapp.repository.BookRepository;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookService(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    public BookDTO createBook(BookDTO bookDTO) {
        Objects.requireNonNull(bookDTO);
        var book = bookRepository.save(bookMapper.toEntity(bookDTO));
        return bookMapper.toDTO(book);
    }

    public BookDTO updateStocks(UpdateStockRequest updateStockRequest) {
        Objects.requireNonNull(updateStockRequest);
        var book = findById(updateStockRequest.getBookId());
        if (Objects.nonNull(book)) {
            if (book.getStock() != null &&
                    !updateStockRequest.getStock().equals(book.getStock())) {
                book.setStock(updateStockRequest.getStock());
                var savedBook = bookRepository.save(bookMapper.toEntity(book));
                return bookMapper.toDTO(savedBook);
            }
        }
        return book;
    }

    @Transactional
    public void decreaseStocks(Long bookId, Long quantity) {
        Objects.requireNonNull(bookId);
        var bookDTO = findById(bookId);
        if (bookDTO.getStock() > 0L && bookDTO.getStock() >= quantity) {
            bookDTO.setStock(bookDTO.getStock() - quantity);
            bookRepository.save(bookMapper.toEntity(bookDTO));
        } else {
            throw new ReadingAppException(ReadingAppErrorCode.STOCK_NOT_AVAILABLE, bookDTO.toString());
        }
    }

    public BigDecimal getTotalPrice(Set<BookOrderRequest> requestSet) {
        var bookMap = requestSet.stream()
                .collect(Collectors.toMap(BookOrderRequest::getBookId, BookOrderRequest::getQuantity));
        var books = bookRepository.findByIdIn(
                requestSet.stream()
                        .map(BookOrderRequest::getBookId)
                        .collect(Collectors.toSet())
        );
        if (books.isPresent()) {
            return books.get()
                    .stream()
                    .map(book -> book.getPrice().multiply(BigDecimal.valueOf(bookMap.get(book.getId()))))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        }
        return BigDecimal.ZERO;
    }

    public BookDTO findById(Long id) {
        Objects.requireNonNull(id);
        var book = bookRepository.findById(id);
        if (book.isPresent()) {
            return bookMapper.toDTO(book.get());
        }
        throw new ReadingAppException(ReadingAppErrorCode.ENTITY_NOT_FOUND, "book is not found by id =" + id);
    }
}
