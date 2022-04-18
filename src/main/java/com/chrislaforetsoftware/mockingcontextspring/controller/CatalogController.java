package com.chrislaforetsoftware.mockingcontextspring.controller;

import com.chrislaforetsoftware.mockingcontextspring.controller.dto.AddTitleRequestDTO;
import com.chrislaforetsoftware.mockingcontextspring.controller.dto.TitleResponseDTO;
import com.chrislaforetsoftware.mockingcontextspring.proxy.CatalogProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/catalog")
public class CatalogController {

	private final CatalogProxy catalogProxy;

	@Autowired
	public CatalogController(CatalogProxy catalogProxy) {
		this.catalogProxy = catalogProxy;
	}

	@GetMapping("/{isbn}")
	public Optional<TitleResponseDTO> getTitleByIsbn(@PathVariable String isbn) {
		return catalogProxy.getTitleByIsbn(isbn);
	}

	@PutMapping("/addTitle")
	public TitleResponseDTO addTitle(@RequestBody AddTitleRequestDTO request) {
		return catalogProxy.addTitle(request);
	}
}