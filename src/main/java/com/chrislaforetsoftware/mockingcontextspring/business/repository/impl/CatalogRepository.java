package com.chrislaforetsoftware.mockingcontextspring.business.repository.impl;

import com.chrislaforetsoftware.mockingcontextspring.business.entity.IBook;
import com.chrislaforetsoftware.mockingcontextspring.business.entity.ICatalog;
import com.chrislaforetsoftware.mockingcontextspring.business.entity.impl.Book;
import com.chrislaforetsoftware.mockingcontextspring.business.entity.impl.Catalog;
import com.chrislaforetsoftware.mockingcontextspring.business.entity.impl.Title;
import com.chrislaforetsoftware.mockingcontextspring.business.repository.ICatalogRepository;
import com.chrislaforetsoftware.mockingcontextspring.entity.BookRecord;
import com.chrislaforetsoftware.mockingcontextspring.entity.CatalogRecord;
import com.chrislaforetsoftware.mockingcontextspring.repository.BookJpaRepository;
import com.chrislaforetsoftware.mockingcontextspring.repository.CatalogJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class CatalogRepository implements ICatalogRepository {

	private final CatalogJpaRepository repository;
	private final BookJpaRepository bookRepository;

	@Autowired
	public CatalogRepository(CatalogJpaRepository repository,
							 BookJpaRepository bookRepository) {
		this.repository = repository;
		this.bookRepository = bookRepository;
	}

	@Override
	public ICatalog addTitleToCatalog(String isbn, String title, String author) {
		CatalogRecord dbCatalog = new CatalogRecord();
		dbCatalog.setIsbn(isbn);
		dbCatalog.setTitle(title);
		dbCatalog.setAuthor(author);
		repository.save(dbCatalog);

		return convertDbCatalog(dbCatalog);
	}

	private ICatalog convertDbCatalog(CatalogRecord dbCatalog) {
		return new Catalog(new Title(dbCatalog.getIsbn(), dbCatalog.getTitle(), dbCatalog.getAuthor()));
	}

	@Override
	public Optional<ICatalog> findTitleByISBN(String isbn) {
		Optional<CatalogRecord> dbCatalog = repository.findById(isbn);
		if (!dbCatalog.isPresent()) {
			return Optional.empty();
		}

		List<BookRecord> dbBooks = bookRepository.findByIsbn(isbn);
		return Optional.of(convertDbCatalog(dbCatalog.get(), dbBooks));
	}

	private ICatalog convertDbCatalog(CatalogRecord dbCatalog, List<BookRecord> dbBooks) {
		Title title = new Title(dbCatalog.getIsbn(), dbCatalog.getTitle(), dbCatalog.getAuthor());
		List<IBook> instances = dbBooks.stream().map(dbBook -> convertDbBook(dbBook, title)).collect(Collectors.toList());
		return new Catalog(title, instances);
	}

	private IBook convertDbBook(BookRecord dbBook, Title title) {
		return new Book(dbBook.getId(), title, dbBook.getPrice());
	}

	@Override
	public List<ICatalog> findAllTitles() {
		// not implemented yet
		return new ArrayList<>();
	}

	@Override
	public IBook addBookToCatalog(String isbn, double price) {
		CatalogRecord dbCatalog = repository.findById(isbn).orElseThrow(() -> new IllegalStateException("ISBN for new book instance cannot be found in catalog"));
		Title title = new Title(dbCatalog.getIsbn(), dbCatalog.getTitle(), dbCatalog.getAuthor());

		BookRecord newBook = new BookRecord();
		newBook.setIsbn(isbn);
		newBook.setPrice(price);
		bookRepository.save(newBook);

		return convertDbBook(newBook, title);
	}

	@Override
	public List<IBook> findBooksByISBN(String isbn) {
		Optional<CatalogRecord> dbCatalog = repository.findById(isbn);
		if (!dbCatalog.isPresent()) {
			return new ArrayList<>();
		}
		Title title = new Title(dbCatalog.get().getIsbn(), dbCatalog.get().getTitle(), dbCatalog.get().getAuthor());

		List<BookRecord> dbBooks = bookRepository.findByIsbn(isbn);
		return dbBooks.stream().map(dbBook -> convertDbBook(dbBook, title)).collect(Collectors.toList());
	}
}
