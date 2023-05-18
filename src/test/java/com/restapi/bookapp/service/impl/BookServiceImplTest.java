package com.restapi.bookapp.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.restapi.bookapp.dao.BookRepository;
import com.restapi.bookapp.dto.BookDTO;
import com.restapi.bookapp.entity.Book;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceImplTest {

	@Mock
	private BookRepository bookRepository;

	@InjectMocks
	private BookServiceImpl bookService;


	@Before
	public void init() {
		MockitoAnnotations.openMocks(bookRepository);
	}
	@After
	public void afterEachTest() {
		System.out.println("test ended.");
	}

	@Test
	public void testFindAll() {
		List<Book> books = new ArrayList<>();
		books.add(new Book(100,"core java",150,"core java author"));
		when(bookRepository.findAll()).thenReturn(books);
		List<BookDTO> dtos=bookService.findAll();
		assertEquals( books.get(0).getBookName(),dtos.get(0).getBookName());
		verify(bookRepository,times(1)).findAll();
		verifyNoMoreInteractions(bookRepository);
	}

}
