package com.chrislaforetsoftware.mockingcontextspring.business.handler.queries;

import com.chrislaforetsoftware.mockingcontextspring.business.entity.ICatalog;
import com.chrislaforetsoftware.mockingcontextspring.business.handler.queries.requests.GetTitleByISBNQuery;
import com.chrislaforetsoftware.mockingcontextspring.business.repository.ICatalogRepository;
import com.chrislaforetsoftware.mockingcontextspring.cqs.IQueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetTitleByISBNQueryHandler implements IQueryHandler<GetTitleByISBNQuery, Optional<ICatalog>> {

    private final ICatalogRepository repository;

    @Autowired
    public GetTitleByISBNQueryHandler(ICatalogRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<ICatalog> handle(GetTitleByISBNQuery query) {
        return repository.findTitleByISBN(query.getISBN());
    }
}
