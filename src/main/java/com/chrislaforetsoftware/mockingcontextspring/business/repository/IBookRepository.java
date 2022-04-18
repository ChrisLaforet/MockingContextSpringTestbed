package com.chrislaforetsoftware.mockingcontextspring.business.repository;

import com.chrislaforetsoftware.mockingcontextspring.business.entity.IBook;

import java.util.Optional;

public interface IBookRepository {
    Optional<IBook> getBookById(int id);
}
