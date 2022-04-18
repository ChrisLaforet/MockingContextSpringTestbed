package com.chrislaforetsoftware.mockingcontextspring.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "catalog")
public class CatalogRecord implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String isbn;
	private String title;
	private String author;
}
