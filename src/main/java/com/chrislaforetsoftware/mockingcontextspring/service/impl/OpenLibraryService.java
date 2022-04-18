package com.chrislaforetsoftware.mockingcontextspring.service.impl;

import com.chrislaforetsoftware.mockingcontextspring.entity.CatalogRecord;
import com.chrislaforetsoftware.mockingcontextspring.service.IOpenLibraryService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class OpenLibraryService implements IOpenLibraryService {

	@Override
	public Optional<IsbnResponse> findByIsbn(String isbn) {
		RestTemplate restTemplate = new RestTemplate();
		IsbnResponse result = restTemplate.getForObject("https://openlibrary.org/isbn/{isbn}.json", IsbnResponse.class, isbn);
		if (result == null) {
			return Optional.empty();
		}
		return Optional.of(result);
	}
}
