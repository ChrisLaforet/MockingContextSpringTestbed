package com.chrislaforetsoftware.mockingcontextspring.business.entity.impl;

import com.chrislaforetsoftware.mockingcontextspring.business.entity.IBook;
import com.chrislaforetsoftware.mockingcontextspring.business.entity.ICatalog;
import com.chrislaforetsoftware.mockingcontextspring.business.entity.ITitle;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collection;

@Getter
public class Catalog implements ICatalog {

	private final ITitle titleInstance;
	private final Collection<IBook> instances = new ArrayList<>();

	public Catalog(ITitle titleInstance) {
		this.titleInstance = titleInstance;
	}

	public Catalog(ITitle titleInstance, Collection<IBook> instances) {
		this(titleInstance);
		this.instances.addAll(instances);
	}
}
