package com.rest.api.post;

public class LibraryPOJO {
	
	private String name;
	private String isbn;
	private String aisle;
	private String autor;
	
	
	public LibraryPOJO(String name, String isbn, String aisle, String autor) {
		this.name = name;
		this.isbn = isbn;
		this.aisle = aisle;
		this.autor = autor;
	}
	
	
	//getter and setter methods
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getIsbn() {
		return isbn;
	}


	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}


	public String getAisle() {
		return aisle;
	}


	public void setAisle(String aisle) {
		this.aisle = aisle;
	}


	public String getAutor() {
		return autor;
	}


	public void setAutor(String autor) {
		this.autor = autor;
	}


}
