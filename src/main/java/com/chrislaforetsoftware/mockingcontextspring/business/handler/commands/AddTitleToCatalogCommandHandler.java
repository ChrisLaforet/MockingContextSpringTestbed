package com.chrislaforetsoftware.mockingcontextspring.business.handler.commands;

import com.chrislaforetsoftware.mockingcontextspring.business.entity.ICatalog;
import com.chrislaforetsoftware.mockingcontextspring.business.handler.commands.requests.AddTitleToCatalogCommand;
import com.chrislaforetsoftware.mockingcontextspring.business.repository.ICatalogRepository;
import com.chrislaforetsoftware.mockingcontextspring.business.rules.CatalogRules;
import com.chrislaforetsoftware.mockingcontextspring.cqs.ICommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddTitleToCatalogCommandHandler implements ICommandHandler<AddTitleToCatalogCommand, ICatalog> {

    private final ICatalogRepository repository;

    private final CatalogRules catalogRules;

    @Autowired
    public AddTitleToCatalogCommandHandler(ICatalogRepository repository,
                                           CatalogRules catalogRules) {
        this.repository = repository;
        this.catalogRules = catalogRules;
    }

    @Override
    public ICatalog handle(AddTitleToCatalogCommand command) {
        assertTitlePropertiesAreValid(command);
        return repository.addTitleToCatalog(command.getISBN(), command.getTitle(), command.getAuthor());
    }

    private void assertTitlePropertiesAreValid(AddTitleToCatalogCommand command) {
        if (!catalogRules.isTitleEligibleForAdding(command.getISBN(), command.getTitle(), command.getAuthor())) {
            throw new IllegalStateException("Title must have ISBN, title, and author");
        }
    }
}
