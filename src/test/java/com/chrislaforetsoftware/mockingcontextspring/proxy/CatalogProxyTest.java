package com.chrislaforetsoftware.mockingcontextspring.proxy;

import com.chrislaforetsoftware.mockingcontext.MockingContext;
import com.chrislaforetsoftware.mockingcontextspring.business.entity.IBook;
import com.chrislaforetsoftware.mockingcontextspring.business.entity.ICatalog;
import com.chrislaforetsoftware.mockingcontextspring.business.handler.commands.AddBookCommandHandler;
import com.chrislaforetsoftware.mockingcontextspring.business.handler.commands.requests.AddBookCommand;
import com.chrislaforetsoftware.mockingcontextspring.business.handler.commands.requests.AddTitleToCatalogCommand;
import com.chrislaforetsoftware.mockingcontextspring.business.handler.queries.GetBooksByISBNQueryHandler;
import com.chrislaforetsoftware.mockingcontextspring.business.handler.queries.GetTitleByISBNQueryHandler;
import com.chrislaforetsoftware.mockingcontextspring.business.handler.queries.requests.GetBooksByISBNQuery;
import com.chrislaforetsoftware.mockingcontextspring.business.handler.queries.requests.GetTitleByISBNQuery;
import com.chrislaforetsoftware.mockingcontextspring.controller.dto.TitleResponseDTO;
import com.chrislaforetsoftware.mockingcontextspring.cqs.ICommandHandler;
import com.chrislaforetsoftware.mockingcontextspring.cqs.IQueryHandler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

//@RunWith(MockitoJUnitRunner.class)
public class CatalogProxyTest {

	@InjectMocks
	CatalogProxy proxy;

	@Mock
	//ICommandHandler<AddTitleToCatalogCommand, ICatalog> addTitleToCatalogCommandHandler;
	AddTitleToCatalogCommand addTitleToCatalogCommandHandler;

	@Mock
	//IQueryHandler<GetTitleByISBNQuery, Optional<ICatalog>> getTitleByISBNQueryHandler;
	GetTitleByISBNQueryHandler getTitleByISBNQueryHandler;

	@Mock
	//ICommandHandler<AddBookCommand, IBook> addBookCommandHandler;
	AddBookCommandHandler addBookCommandHandler;

	@Mock
	//IQueryHandler<GetBooksByISBNQuery, List<IBook>> getBooksByISBNQueryHandler;
	GetBooksByISBNQueryHandler getBooksByISBNQueryHandler;

	private static final String VALID_ISBN = "1234567890";

	@Test
	public void givenMockingContext_whenMockContextCalled_thenLocatesInjectMocks() throws Exception {
		MockingContext mockingContext = MockingContext.createInstance();
		mockingContext.mockContext();

		assertNotNull(this.proxy);
	}

	@Test
	public void givenIsbn_whenRequestingTitle_thenReturnsTitleResponse() {
		when(getTitleByISBNQueryHandler.handle(Mockito.any())).thenReturn(Optional.empty());

		Optional<TitleResponseDTO> results  = proxy.getTitleByIsbn(VALID_ISBN);
		assertTrue(results.isPresent());
	}
}