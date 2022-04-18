package com.chrislaforetsoftware.mockingcontextspring.business.handler.commands.requests;

import com.chrislaforetsoftware.mockingcontextspring.business.entity.IBook;
import com.chrislaforetsoftware.mockingcontextspring.cqs.ICommand;
import lombok.Getter;

@Getter
public class AddBookCommand implements ICommand {
    private final IBook book;

    public AddBookCommand(final IBook book) {
        this.book = book;
    }
}
