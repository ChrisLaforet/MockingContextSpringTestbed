package com.chrislaforetsoftware.mockingcontextspring.controller.dto;

import com.chrislaforetsoftware.mockingcontextspring.business.entity.IBook;
import com.chrislaforetsoftware.mockingcontextspring.business.entity.ICatalog;
import com.chrislaforetsoftware.mockingcontextspring.entity.CatalogRecord;
import com.chrislaforetsoftware.mockingcontextspring.service.impl.IsbnResponse;

public class CatalogDTOMappers {

	private CatalogDTOMappers() {
		// keep class static only
	}

	public static TitleResponseDTO mapCatalogToTitleResponseDTO(ICatalog catalog) {
		TitleResponseDTO response = new TitleResponseDTO();
		response.setISBN(catalog.getTitleInstance().getISBN());
		response.setTitle(catalog.getTitleInstance().getTitle());
		response.setAuthor(catalog.getTitleInstance().getAuthor());
		return response;
	}

	public static BookResponseDTO mapBookToBookResponseDTO(IBook book) {
		BookResponseDTO response = new BookResponseDTO();
		response.setId(book.getId());
		response.setAuthor(book.getTitleInstance().getAuthor());
		response.setIsbn(book.getTitleInstance().getISBN());
		response.setPrice(book.getPrice());;
		return response;
	}

	public static TitleResponseDTO mapOpenLibraryIsbnResponseToTitleResponseDTO(IsbnResponse isbnResponse) {
		TitleResponseDTO response = new TitleResponseDTO();
		response.setISBN(isbnResponse.getIsbn());
		response.setAuthor(isbnResponse.getAuthor());
		response.setTitle(isbnResponse.getTitle());
		return response;
	}
}
