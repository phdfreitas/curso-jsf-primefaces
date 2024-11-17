package br.com.bookstore.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.NoResultException;

import br.com.bookstore.model.User;
import br.com.bookstore.service.UserService;
import br.com.bookstore.utils.Message;
import br.com.bookstore.utils.PasswordEncrypt;

@Named
@SessionScoped
public class LoginMB implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;
	
	@Inject
	private UserService userService;
	
	public String login() {
		try {			
			User user = userService.findByUsername(username);
			
			if(PasswordEncrypt.encrypt(password).equals(user.getPassword())) {
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", user);
				
				return "protected/author.xhtml?faces-redirect=true";
			}
			else {
				Message.erro("Invalid credentials");
				return null;
			}
		}
		catch(NoResultException e) {
			Message.erro("User doesn't exist");
			return null;
		}
	}
	
	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/login.xhtml?faces-redirect=true";
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
