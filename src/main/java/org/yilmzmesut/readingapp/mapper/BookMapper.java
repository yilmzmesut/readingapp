package org.yilmzmesut.readingapp.mapper;

import org.mapstruct.Mapper;
import org.yilmzmesut.readingapp.entity.Book;
import org.yilmzmesut.readingapp.model.dto.BookDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {
    Book toEntity(BookDTO bookDTO);

    BookDTO toDTO(Book book);

    List<Book> toEntityList(List<BookDTO> bookDTOS);

    List<BookDTO> toDTOList(List<Book> books);
}
