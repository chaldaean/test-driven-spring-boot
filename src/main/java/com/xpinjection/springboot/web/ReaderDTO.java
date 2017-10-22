package com.xpinjection.springboot.web;

public class ReaderDTO {
	private long id;

	public ReaderDTO(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
