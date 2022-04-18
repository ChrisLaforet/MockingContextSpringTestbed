package com.chrislaforetsoftware.mockingcontextspring.controller.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;

@Getter
public class AddBookRequestDTO {
	@JsonAlias({"isbn", "ISBN"})
	private String isbn;
	private double price;
}
