package com.chrislaforetsoftware.mockingcontextspring.business.repository;

import com.chrislaforetsoftware.mockingcontextspring.business.entity.IBook;
import com.chrislaforetsoftware.mockingcontextspring.business.entity.ICatalog;

import java.util.List;
import java.util.Optional;

public interface ICatalogRepository {

	ICatalog addTitleToCatalog(String ISBN, String title, String author);
	Optional<ICatalog> findTitleByISBN(String ISBN);
	List<ICatalog> findAllTitles();

	IBook addBookToCatalog(String ISBN, double price);
	List<IBook> findBooksByISBN(String ISBN);
}
