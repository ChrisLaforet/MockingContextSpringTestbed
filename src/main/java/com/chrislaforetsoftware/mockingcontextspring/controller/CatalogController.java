package com.chrislaforetsoftware.mockingcontextspring.controller;

import com.chrislaforetsoftware.mockingcontextspring.controller.dto.AddTitleRequestDTO;
import com.chrislaforetsoftware.mockingcontextspring.controller.dto.CatalogDTOMappers;
import com.chrislaforetsoftware.mockingcontextspring.controller.dto.TitleResponseDTO;
import com.chrislaforetsoftware.mockingcontextspring.proxy.CatalogProxy;
import com.chrislaforetsoftware.mockingcontextspring.service.IOpenLibraryService;
import com.chrislaforetsoftware.mockingcontextspring.service.impl.IsbnResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/catalog")
public class CatalogController {

	private final CatalogProxy catalogProxy;
	private final IOpenLibraryService openLibraryService;

	@Autowired
	public CatalogController(CatalogProxy catalogProxy, IOpenLibraryService openLibraryService) {
		this.catalogProxy = catalogProxy;
		this.openLibraryService = openLibraryService;
	}

	@GetMapping("/{isbn}")
	public Optional<TitleResponseDTO> getTitleByIsbn(@PathVariable String isbn) {
		return catalogProxy.getTitleByIsbn(isbn);
	}

	@PutMapping("/addTitle")
	public TitleResponseDTO addTitle(@RequestBody AddTitleRequestDTO request) {
		return catalogProxy.addTitle(request);
	}

	@GetMapping("/openlibrary/{isbn}")
	public Optional<TitleResponseDTO> getOpenLibraryTitleByIsbn(@PathVariable String isbn) {
		Optional<IsbnResponse> response = openLibraryService.findByIsbn(isbn);
		return response.map(CatalogDTOMappers::mapOpenLibraryIsbnResponseToTitleResponseDTO);
	}
}