package com.chrislaforetsoftware.mockingcontextspring.business.entity;

import java.util.Collection;

public interface ICatalog {
    ITitle getTitleInstance();
    Collection<IBook> getInstances();
}
