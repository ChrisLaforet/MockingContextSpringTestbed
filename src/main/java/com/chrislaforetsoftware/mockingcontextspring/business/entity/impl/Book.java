package com.chrislaforetsoftware.mockingcontextspring.business.entity.impl;

import com.chrislaforetsoftware.mockingcontextspring.business.entity.IBook;
import com.chrislaforetsoftware.mockingcontextspring.business.entity.ITitle;
import lombok.Getter;
import lombok.Setter;

@Getter
public class Book implements IBook {
	private final ITitle titleInstance;
	private final double price;

	@Setter
	private int id;

	public Book(ITitle titleInstance, double price) {
		this.titleInstance = titleInstance;
		this.price = price;
	}

	public Book(int id, ITitle titleInstance, double price) {
		this(titleInstance, price);
		this.id = id;
	}
}
