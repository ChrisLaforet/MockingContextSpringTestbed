package com.chrislaforetsoftware.mockingcontextspring.repository;

import com.chrislaforetsoftware.mockingcontextspring.entity.BookRecord;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookJpaRepository extends CrudRepository<BookRecord, Integer> {

	List<BookRecord> findByIsbn(String isbn);
}
