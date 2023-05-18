package com.restapi.bookapp.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restapi.bookapp.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer>{

	Optional<Book> findByBookNameAndBookPriceAndAuthorName(String bookName, Integer bookPrice,String AuthorName);
	Optional<List<Book>> findByAuthorName(String authorName);
}
