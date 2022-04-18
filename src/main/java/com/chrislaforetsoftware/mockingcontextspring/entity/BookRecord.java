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
@Table(name = "book")
public class BookRecord implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private int id;
	private String isbn;
	private double price;

}
