package com.chrislaforetsoftware.mockingcontextspring.business.rules;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class CatalogRules {

    public boolean isBookEligibleForAdding(String isbn, String title, Double price) {
        return StringUtils.isNotBlank(isbn) &&
                StringUtils.isNotBlank(title) && price != null;
    }

    public boolean isTitleEligibleForAdding(String isbn, String title, String author) {
        return StringUtils.isNotBlank(isbn) &&
                StringUtils.isNotBlank(title) &&
                StringUtils.isNotBlank(author);
    }
}
