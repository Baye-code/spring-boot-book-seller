package com.laminf.code.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.laminf.code.model.Book;

public interface IBookRepository extends JpaRepository<Book, Long> {

}
