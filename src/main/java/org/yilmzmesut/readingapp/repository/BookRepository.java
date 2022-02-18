package org.yilmzmesut.readingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yilmzmesut.readingapp.entity.Book;

import java.util.Optional;
import java.util.Set;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Set<Book>> findByIdIn(Set<Long> idSet);
}
