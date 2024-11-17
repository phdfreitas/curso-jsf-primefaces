package br.com.bookstore.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.bookstore.dao.GenericDao;
import br.com.bookstore.exception.BusinessException;
import br.com.bookstore.model.User;
import br.com.bookstore.utils.PasswordEncrypt;

public class UserService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private GenericDao<User> dao;
	
	public void saveUser(String firstname, String lastname, String email, String username, String password) throws BusinessException{
		
		User user = new User();
		user.setFirstName(firstname);
		user.setLastName(lastname);
		user.setEmail(email);
		user.setUsername(username);
		user.setPassword(PasswordEncrypt.encrypt(password));
		
		dao.save(user);
	}
	
	public User findByUsername(String username) {
		return dao.findByAttribute(User.class, "username", username);
	}

}
