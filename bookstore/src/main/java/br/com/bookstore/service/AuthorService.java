package br.com.bookstore.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.bookstore.dao.GenericDao;
import br.com.bookstore.exception.BusinessException;
import br.com.bookstore.model.Author;

public class AuthorService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private GenericDao<Author> dao;
	
	public void saveAuthor(Author author) throws BusinessException{
		dao.save(author);
	}
	
	@SuppressWarnings("unchecked")
	public void removeAuthor(Author author) throws BusinessException{
		dao.remove((Class<Author>) author.getClass(), author.getId());
	}
	
	public List<Author> findAllAuthors(){
		return dao.findAll("select a from Author order by a.name");
	}
}
