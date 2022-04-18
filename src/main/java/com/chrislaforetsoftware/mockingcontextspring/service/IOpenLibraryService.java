package com.chrislaforetsoftware.mockingcontextspring.service;

import com.chrislaforetsoftware.mockingcontextspring.entity.CatalogRecord;
import com.chrislaforetsoftware.mockingcontextspring.service.impl.IsbnResponse;

import java.util.Optional;

public interface IOpenLibraryService {
	Optional<IsbnResponse> findByIsbn(String isbn);
}
