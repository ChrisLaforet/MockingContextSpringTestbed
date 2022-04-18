package com.chrislaforetsoftware.mockingcontextspring.repository;

import com.chrislaforetsoftware.mockingcontextspring.entity.CatalogRecord;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CatalogJpaRepository extends CrudRepository<CatalogRecord, String> {
    Optional<CatalogRecord> findByIsbn(String isbn);
}
