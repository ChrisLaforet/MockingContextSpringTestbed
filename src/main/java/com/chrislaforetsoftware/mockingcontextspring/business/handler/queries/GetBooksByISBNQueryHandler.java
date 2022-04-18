package com.chrislaforetsoftware.mockingcontextspring.business.handler.queries;

import com.chrislaforetsoftware.mockingcontextspring.business.entity.IBook;
import com.chrislaforetsoftware.mockingcontextspring.business.handler.queries.requests.GetBooksByISBNQuery;
import com.chrislaforetsoftware.mockingcontextspring.business.repository.ICatalogRepository;
import com.chrislaforetsoftware.mockingcontextspring.cqs.IQueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetBooksByISBNQueryHandler implements IQueryHandler<GetBooksByISBNQuery, List<IBook>> {

    private final ICatalogRepository repository;

    @Autowired
    public GetBooksByISBNQueryHandler(ICatalogRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<IBook> handle(GetBooksByISBNQuery query) {
        return repository.findBooksByISBN(query.getIsbn());
    }
}
