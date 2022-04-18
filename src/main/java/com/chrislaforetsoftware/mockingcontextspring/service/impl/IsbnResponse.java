package com.chrislaforetsoftware.mockingcontextspring.service.impl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IsbnResponse {
	private String isbn_10 = "";
	private String title = "";
	//private List<String> authors;

	public String getIsbn() {
		return isbn_10;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
//		if (authors != null && !authors.isEmpty()) {
//			return authors.get(0);
//		}
		return "TBD";
	}
}
