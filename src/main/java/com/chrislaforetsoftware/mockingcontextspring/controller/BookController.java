package com.chrislaforetsoftware.mockingcontextspring.controller;

import com.chrislaforetsoftware.mockingcontextspring.controller.dto.AddBookRequestDTO;
import com.chrislaforetsoftware.mockingcontextspring.controller.dto.BookResponseDTO;
import com.chrislaforetsoftware.mockingcontextspring.proxy.CatalogProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/book")
public class BookController {

	private final CatalogProxy catalogProxy;

	@Autowired
	public BookController(CatalogProxy catalogProxy) {
		this.catalogProxy = catalogProxy;
	}

	@GetMapping("/{isbn}")
	public List<BookResponseDTO> getBooksByIsbn(@PathVariable String isbn) {
		return catalogProxy.getBooksByIsbn(isbn);
	}

	@PutMapping("/add")
	public BookResponseDTO addBook(@RequestBody AddBookRequestDTO request) {
		return catalogProxy.addBook(request);
	}
}
