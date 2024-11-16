package br.com.bookstore.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class GenericDao<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private static EntityManager manager = ConnectionFactory.getEntityManager();
	
	public T findById(Class<T> modelClass, Long id) {
		return manager.find(modelClass, id);
	}
	
	public void save(T t) {
		try {
			manager.getTransaction().begin();
			
			if(t.getClass().getField("id") == null) {
				manager.persist(t);
			}
			else {
				manager.merge(t);
			}
			
			manager.getTransaction().commit();
		}
		catch(Exception e) {
			manager.getTransaction().rollback();
		}
	}
	
	public void remove(Class<T> modelClass, Long id) {
		T t = findById(modelClass, id);
		
		try {
			manager.getTransaction().begin();
			
			manager.remove(t);
			
			manager.getTransaction().commit();
		}
		catch(Exception e) {
			manager.getTransaction().rollback();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findAll(String jpql){
		Query query = manager.createQuery(jpql);
		
		return query.getResultList();
	}
}
