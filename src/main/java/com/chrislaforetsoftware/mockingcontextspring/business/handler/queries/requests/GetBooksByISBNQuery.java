package com.chrislaforetsoftware.mockingcontextspring.business.handler.queries.requests;

import com.chrislaforetsoftware.mockingcontextspring.cqs.IQuery;
import lombok.Getter;

@Getter
public class GetBooksByISBNQuery implements IQuery {
    private final String isbn;

    public GetBooksByISBNQuery(String isbn) {
        this.isbn = isbn;
    }
}