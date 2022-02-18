package org.yilmzmesut.readingapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yilmzmesut.readingapp.model.dto.BookDTO;
import org.yilmzmesut.readingapp.model.request.UpdateStockRequest;
import org.yilmzmesut.readingapp.model.response.WrapperResponse;
import org.yilmzmesut.readingapp.service.BookService;

import javax.validation.Valid;

@RestController
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/")
    public ResponseEntity<WrapperResponse<BookDTO>> createBook(@Valid @RequestBody BookDTO bookDTO) {
        var book = bookService.createBook(bookDTO);
        return ResponseEntity.ok(new WrapperResponse<>(book, HttpStatus.CREATED.value()));
    }

    @PutMapping("/stock")
    public ResponseEntity<WrapperResponse<BookDTO>> updateStock(@Valid @RequestBody UpdateStockRequest updateStockRequest) {
        var bookDTO = bookService.updateStocks(updateStockRequest);
        return ResponseEntity.ok(new WrapperResponse<>(bookDTO, HttpStatus.OK.value()));
    }
}
