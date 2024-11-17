package br.com.bookstore.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.bookstore.exception.BusinessException;
import br.com.bookstore.model.Author;
import br.com.bookstore.service.AuthorService;
import br.com.bookstore.utils.Message;

@Named("authorManagedBean")
@ViewScoped
public class AuthorMB implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private Author author;
	
	@Inject
	private AuthorService authorService;
	
	private List<Author> authors;
	
	@PostConstruct
	public void initElements() {
		authors = authorService.findAllAuthors();
	}
	
	public void addAuthor() {
		try {
			authorService.saveAuthor(author);
			author = new Author();
			
			initElements();
			
			Message.info("Author successfully added.");
		}
		catch(BusinessException e) {
			Message.erro(e.getMessage());
		}
	}
	
	public void removeAuthor() {
		try {
			authorService.removeAuthor(author);
			initElements();
			
			Message.info(author.getName() + "was removed");
		}
		catch(BusinessException e) {
			Message.erro(e.getMessage());
		}
	}
	
	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}
		
	public List<Author> getAuthors() {
		return authors;
	}	
}
