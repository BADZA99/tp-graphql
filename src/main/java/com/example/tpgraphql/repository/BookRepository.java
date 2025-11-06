package com.example.tpgraphql.repository;

import com.example.tpgraphql.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}