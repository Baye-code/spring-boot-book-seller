package com.laminf.code.service;

import java.util.List;

import com.laminf.code.model.Book;

public interface IBookService {

	List<Book> findAll();

	void deleteBook(Long id);

	Book saveBook(Book book);

}
