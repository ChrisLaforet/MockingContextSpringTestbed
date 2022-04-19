package com.chrislaforetsoftware.mockingcontextspring.proxy;

import com.chrislaforetsoftware.mockingcontextspring.business.entity.IBook;
import com.chrislaforetsoftware.mockingcontextspring.business.entity.ICatalog;
import com.chrislaforetsoftware.mockingcontextspring.business.handler.commands.requests.AddBookCommand;
import com.chrislaforetsoftware.mockingcontextspring.business.handler.commands.requests.AddTitleToCatalogCommand;
import com.chrislaforetsoftware.mockingcontextspring.business.handler.queries.requests.GetBooksByISBNQuery;
import com.chrislaforetsoftware.mockingcontextspring.business.handler.queries.requests.GetTitleByISBNQuery;
import com.chrislaforetsoftware.mockingcontextspring.controller.dto.TitleResponseDTO;
import com.chrislaforetsoftware.mockingcontextspring.cqs.ICommandHandler;
import com.chrislaforetsoftware.mockingcontextspring.cqs.IQueryHandler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class CatalogProxyTest {

	@InjectMocks
	CatalogProxy proxy;

	@Mock
	ICommandHandler<AddTitleToCatalogCommand, ICatalog> addTitleToCatalogCommandHandler;

	@Mock
	IQueryHandler<GetTitleByISBNQuery, Optional<ICatalog>> getTitleByISBNQueryHandler;

	@Mock
	ICommandHandler<AddBookCommand, IBook> addBookCommandHandler;

	@Mock
	IQueryHandler<GetBooksByISBNQuery, List<IBook>> getBooksByISBNQueryHandler;

	private static final String VALID_ISBN = "1234567890";

	@Test
	public void givenIsbn_whenRequestingTitle_thenReturnsTitleResponse() {
		Optional<TitleResponseDTO> results  = proxy.getTitleByIsbn(VALID_ISBN);
		assertTrue(results.isPresent());
	}
}