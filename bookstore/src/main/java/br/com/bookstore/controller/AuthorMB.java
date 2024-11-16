package br.com.bookstore.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.bookstore.model.Author;

@Named("authorManagedBean")
@ApplicationScoped
public class AuthorMB implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private Author author;
	
	private List<Author> authors = new ArrayList<Author>();
	
	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}
		
	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	public String add() {
		authors.add(author);
		System.out.println("OK " + author.getName() + " - " + author.getEmail());
		clearObject();
		
		return "OK";
	}
	
	private void clearObject() {
		author = new Author();
	}
	
}
