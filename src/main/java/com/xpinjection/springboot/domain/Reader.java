package com.xpinjection.springboot.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Entity
@NoArgsConstructor
public class Reader {
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private long Id;
	@NotNull
	private String name;
	@Min(1) @Max(100)
	private int age;
	private String favouriteAuthor;

	public Reader(String name, int age) {
		this.name = name;
		this.age = age;
	}
}
