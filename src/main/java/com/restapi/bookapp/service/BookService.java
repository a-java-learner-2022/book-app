package com.restapi.bookapp.service;

import java.util.List;

import com.restapi.bookapp.dto.BookDTO;


public interface BookService {

	List<BookDTO> findAll();

	BookDTO findBookById(Integer bookId);

	public String save(BookDTO bookDTO);

	String updateBook(BookDTO bookDTO);

	void deleteById(int bookId);

	List<BookDTO> findByAuthorName(String authorName);





}
