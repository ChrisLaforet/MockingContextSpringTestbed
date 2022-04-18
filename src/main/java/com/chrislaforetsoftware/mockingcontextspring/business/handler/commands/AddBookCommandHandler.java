package com.chrislaforetsoftware.mockingcontextspring.business.handler.commands;

import com.chrislaforetsoftware.mockingcontextspring.business.entity.IBook;
import com.chrislaforetsoftware.mockingcontextspring.business.handler.commands.requests.AddBookCommand;
import com.chrislaforetsoftware.mockingcontextspring.business.repository.ICatalogRepository;
import com.chrislaforetsoftware.mockingcontextspring.business.rules.CatalogRules;
import com.chrislaforetsoftware.mockingcontextspring.cqs.ICommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddBookCommandHandler implements ICommandHandler<AddBookCommand, IBook> {

    private final ICatalogRepository repository;
    private final CatalogRules rules;

    @Autowired
    public AddBookCommandHandler(ICatalogRepository repository,
                                 CatalogRules rules) {
        this.repository = repository;
        this.rules = rules;
    }

    @Override
    public IBook handle(AddBookCommand command) {
        assertTitleIsValid(command);
        assertBookPropertiesAreValid(command);

        return repository.addBookToCatalog(command.getBook().getTitleInstance().getISBN(),
                command.getBook().getPrice());
    }

    private void assertTitleIsValid(AddBookCommand command) {
        if (command.getBook().getTitleInstance() == null) {
            throw new IllegalStateException("Cannot add new book instance to catalog - missing title");
        }

        repository.findTitleByISBN(command.getBook().getTitleInstance().getISBN())
                .orElseThrow(() -> new IllegalStateException("Cannot add new book instance to catalog - ISBN not found"));
    }

    private void assertBookPropertiesAreValid(AddBookCommand command) {
        if (command.getBook().getTitleInstance() == null) {
            throw new IllegalStateException("Cannot add new book instance to catalog - missing title");
        }
        if (!rules.isBookEligibleForAdding(command.getBook().getTitleInstance().getISBN(),
                command.getBook().getTitleInstance().getTitle(),
                command.getBook().getPrice())) {
            throw new IllegalStateException("Cannot add new book instance to catalog - missing key information");
        }
    }
}
