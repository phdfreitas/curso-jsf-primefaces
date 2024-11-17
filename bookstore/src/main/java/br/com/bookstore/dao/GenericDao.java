package br.com.bookstore.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.bookstore.model.Base;

public class GenericDao<T extends Base> implements Serializable {

	private static final long serialVersionUID = 1L;

	private static EntityManager manager = ConnectionFactory.getEntityManager();
	
	public T findById(Class<T> modelClass, Long id) {
		return manager.find(modelClass, id);
	}
	
	public void save(T t) {
		System.out.println("Dao Save " + t);
		try {
			manager.getTransaction().begin();
						
			if(t.getId() == null) {
				manager.persist(t);
			}
			else {
				manager.merge(t);
			}
			
			manager.getTransaction().commit();
		}
		catch(Exception e) {
			e.printStackTrace();
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
		TypedQuery<T> query = (TypedQuery<T>) manager.createQuery(jpql);
		
		return query.getResultList();
	}
}
