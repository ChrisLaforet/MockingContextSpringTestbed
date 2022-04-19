package com.chrislaforetsoftware.mockingcontextspring.proxy;

import com.chrislaforetsoftware.mockingcontextspring.business.entity.IBook;
import com.chrislaforetsoftware.mockingcontextspring.business.entity.ICatalog;
import com.chrislaforetsoftware.mockingcontextspring.business.entity.impl.Book;
import com.chrislaforetsoftware.mockingcontextspring.business.handler.commands.requests.AddBookCommand;
import com.chrislaforetsoftware.mockingcontextspring.business.handler.commands.requests.AddTitleToCatalogCommand;
import com.chrislaforetsoftware.mockingcontextspring.business.handler.queries.requests.GetBooksByISBNQuery;
import com.chrislaforetsoftware.mockingcontextspring.business.handler.queries.requests.GetTitleByISBNQuery;
import com.chrislaforetsoftware.mockingcontextspring.controller.dto.*;
import com.chrislaforetsoftware.mockingcontextspring.cqs.ICommandHandler;
import com.chrislaforetsoftware.mockingcontextspring.cqs.IQueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CatalogProxy {

	private final ICommandHandler<AddTitleToCatalogCommand, ICatalog> addTitleToCatalogCommandHandler;
	private final IQueryHandler<GetTitleByISBNQuery, Optional<ICatalog>> getTitleByISBNQueryHandler;
	private final ICommandHandler<AddBookCommand, IBook> addBookCommandHandler;
	private final IQueryHandler<GetBooksByISBNQuery, List<IBook>> getBooksByISBNQueryHandler;

	@Autowired
	public CatalogProxy(ICommandHandler<AddTitleToCatalogCommand, ICatalog> addTitleToCatalogCommandHandler,
						IQueryHandler<GetTitleByISBNQuery, Optional<ICatalog>> getTitleByISBNQueryHandler,
						ICommandHandler<AddBookCommand, IBook> addBookCommandHandler,
						IQueryHandler<GetBooksByISBNQuery, List<IBook>> getBooksByISBNQueryHandler) {
		this.addTitleToCatalogCommandHandler = addTitleToCatalogCommandHandler;
		this.getTitleByISBNQueryHandler = getTitleByISBNQueryHandler;
		this.addBookCommandHandler = addBookCommandHandler;
		this.getBooksByISBNQueryHandler = getBooksByISBNQueryHandler;
	}

	public Optional<TitleResponseDTO> getTitleByIsbn(String isbn) {
		Optional<ICatalog> catalog = getTitleByISBNQueryHandler.handle(new GetTitleByISBNQuery(isbn));
		if (catalog.isPresent()) {
			return catalog.map(CatalogDTOMappers::mapCatalogToTitleResponseDTO);
		}
		return Optional.empty();
	}

	public TitleResponseDTO addTitle(AddTitleRequestDTO requestDTO) {
		AddTitleToCatalogCommand command = new AddTitleToCatalogCommand(requestDTO.getIsbn(), requestDTO.getTitle(), requestDTO.getAuthor());
		return CatalogDTOMappers.mapCatalogToTitleResponseDTO(addTitleToCatalogCommandHandler.handle(command));
	}

	public List<BookResponseDTO> getBooksByIsbn(String isbn) {
		List<IBook> books = getBooksByISBNQueryHandler.handle(new GetBooksByISBNQuery(isbn));
		return books.stream().map(CatalogDTOMappers::mapBookToBookResponseDTO).collect(Collectors.toList());
	}

	public BookResponseDTO addBook(AddBookRequestDTO requestDTO) {
		ICatalog catalog = getTitleByISBNQueryHandler.handle(new GetTitleByISBNQuery(requestDTO.getIsbn()))
								.orElseThrow(() -> new IllegalStateException("ISBN for new book cannot be found in catalog"));
		Book newBook = new Book(catalog.getTitleInstance(),
								requestDTO.getPrice());

		AddBookCommand command = new AddBookCommand(newBook);
		return CatalogDTOMappers.mapBookToBookResponseDTO(addBookCommandHandler.handle(command));
	}
}
