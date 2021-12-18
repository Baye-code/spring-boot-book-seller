package com.laminf.code.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.laminf.code.model.Book;
import com.laminf.code.repository.IBookRepository;


@Service
public class BookService implements IBookService {
	
	// ========================================================================= //
	
	private final IBookRepository bookRepository;
	
	public BookService(IBookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}
	
	// ========================================================================= //
	
	@Override
	public Book saveBook(Book book) {
		book.setCreateTime(LocalDateTime.now());
		return bookRepository.save(book);
	}
	
	// ========================================================================= //
	
	@Override
	public void deleteBook(Long id) {
		bookRepository.deleteById(id);
	}
	
	// ========================================================================= //
	
	@Override
	public List<Book> findAll(){
		return bookRepository.findAll();
	}

}
