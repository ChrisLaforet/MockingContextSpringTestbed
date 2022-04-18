package com.chrislaforetsoftware.mockingcontextspring.business.handler.queries.requests;

import com.chrislaforetsoftware.mockingcontextspring.cqs.IQuery;
import lombok.Getter;

@Getter
public class GetTitleByISBNQuery implements IQuery {
    private final String ISBN;

    public GetTitleByISBNQuery(String ISBN) {
        this.ISBN = ISBN;
    }
}