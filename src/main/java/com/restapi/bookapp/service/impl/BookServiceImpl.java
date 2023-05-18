package com.restapi.bookapp.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restapi.bookapp.dao.BookRepository;
import com.restapi.bookapp.dto.BookDTO;
import com.restapi.bookapp.entity.Book;
import com.restapi.bookapp.service.BookService;

@Service
public class BookServiceImpl implements BookService{

	@Autowired
	private BookRepository bookRepository;

	@Override
	public List<BookDTO> findAll() {
		List<Book> books = bookRepository.findAll();
		List<BookDTO> bookDTOs = new ArrayList<>();

		for(Book book:books) {
			BookDTO bookDTO = new BookDTO();
			BeanUtils.copyProperties(book, bookDTO);
			bookDTOs.add(bookDTO);
		}
		return bookDTOs;
	}

	@Override
	public BookDTO findBookById(Integer bookId) {
		Optional<Book> optional = bookRepository.findById(bookId);
		BookDTO bookDTO = new BookDTO();
		if (optional.isPresent()) {
			Book book = optional.get();
			BeanUtils.copyProperties(book, bookDTO);
			return bookDTO;
		}
		return null;
	}

	@Override
	public String save(BookDTO bookDTO) {
		//Optional<Book> optional = this.bookRepository.findByBookNameAndBookPriceAndAuthorName(bookDTO.getBookName(), bookDTO.getBookPrice(), bookDTO.getAuthorName());
		//if(optional.isEmpty()) {
		if(bookDTO!=null) {
			Book book = new Book();
			BeanUtils.copyProperties(bookDTO, book);
			this.bookRepository.save(book);
			return "new record has been saved succuessfully.";
		}
		//}
		return "Duplicate records: not saving record in database";
	}

	@Override
	public String updateBook(BookDTO bookDTO) {
		String message="Record is not found to update!";
		Optional<Book> optional= this.bookRepository.findById(bookDTO.getBookId());
		if (optional.isPresent()) {
			Book book =optional.get();
			book.setBookName(bookDTO.getBookName());
			book.setBookPrice(bookDTO.getBookPrice());
			book.setAuthorName(bookDTO.getAuthorName());
			this.bookRepository.save(book);
			message="Record has been updated successfully.";
			return message;
		}
		return message;

	}

	@Override
	public void deleteById(int bookId) {
		this.bookRepository.deleteById(bookId);

	}

	@Override
	public List<BookDTO> findByAuthorName(String authorName) {
		Optional<List<Book>> optional= this.bookRepository.findByAuthorName(authorName);
		List<BookDTO> bookDTOs=new ArrayList<>();
		if (optional.isPresent()) {
			List<Book> books = optional.get();
			for(Book book:books) {
				BookDTO bookDTO = new BookDTO();
				BeanUtils.copyProperties(book, bookDTO);
				bookDTOs.add(bookDTO);
			}
			return bookDTOs;
		}
		return null;
	}


}
