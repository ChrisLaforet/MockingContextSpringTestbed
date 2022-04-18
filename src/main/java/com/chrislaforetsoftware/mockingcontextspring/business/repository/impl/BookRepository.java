package com.chrislaforetsoftware.mockingcontextspring.business.repository.impl;

import com.chrislaforetsoftware.mockingcontextspring.business.entity.IBook;
import com.chrislaforetsoftware.mockingcontextspring.business.entity.impl.Book;
import com.chrislaforetsoftware.mockingcontextspring.business.entity.impl.Title;
import com.chrislaforetsoftware.mockingcontextspring.business.repository.IBookRepository;
import com.chrislaforetsoftware.mockingcontextspring.entity.BookRecord;
import com.chrislaforetsoftware.mockingcontextspring.entity.CatalogRecord;
import com.chrislaforetsoftware.mockingcontextspring.repository.BookJpaRepository;
import com.chrislaforetsoftware.mockingcontextspring.repository.CatalogJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookRepository implements IBookRepository {

    private final BookJpaRepository repository;
    private final CatalogJpaRepository catalogRepository;

    @Autowired
    public BookRepository(BookJpaRepository repository,
                          CatalogJpaRepository catalogRepository) {
        this.repository = repository;
        this.catalogRepository = catalogRepository;
    }

    @Override
    public Optional<IBook> getBookById(int id) {
        Optional<BookRecord> dbBook = repository.findById(id);
        if (!dbBook.isPresent()) {
            return Optional.empty();
        }
        Optional<CatalogRecord> dbCatalog =
                catalogRepository.findByIsbn(dbBook.get().getIsbn());
        if (!dbCatalog.isPresent()) {
            throw new IllegalStateException("Cannot find catalog referenced by existing book");
        }
        Title title = new Title(dbCatalog.get().getIsbn(), dbCatalog.get().getTitle(), dbCatalog.get().getAuthor());
        return Optional.of(convertDbBook(dbBook.get(), title));
    }

    private IBook convertDbBook(BookRecord dbBook, Title title) {
        return new Book(dbBook.getId(), title, dbBook.getPrice());
    }
}
