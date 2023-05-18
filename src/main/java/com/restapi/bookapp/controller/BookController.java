package com.restapi.bookapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restapi.bookapp.dto.BookDTO;
import com.restapi.bookapp.service.BookService;

@RestController
@RequestMapping("/v1")
public class BookController {

	@Autowired
	private BookService bookService;



	@GetMapping("/books") //getting all records
	public ResponseEntity<List<BookDTO>> getBooks(){
		return ResponseEntity.ok(bookService.findAll());
	}

	@GetMapping("/books/{bookId}") // get a record by id
	public ResponseEntity<BookDTO> getBookById(@PathVariable String bookId ) {
		return ResponseEntity.ok(bookService.findBookById(Integer.parseInt(bookId)));
	}

	@PostMapping("/books") // saving a record
	public String saveBook(@RequestBody BookDTO bookDTO) {
		return this.bookService.save(bookDTO);
	}

	@PutMapping("/books") // updating  a record
	public String updateBook(@RequestBody BookDTO bookDTO) {
		return this.bookService.updateBook(bookDTO);
	}

	@DeleteMapping("/books/{bookId}") // deleting a record
	public ResponseEntity<HttpStatus> deleteBook(@PathVariable String bookId) {
		try {
			this.bookService.deleteById(Integer.parseInt(bookId));
			return new ResponseEntity<>(HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/books/author/{authorName}") // getting record according to author name
	public List<BookDTO> getByAuthorName(@PathVariable String authorName){
		return this.bookService.findByAuthorName(authorName);
	}

}
