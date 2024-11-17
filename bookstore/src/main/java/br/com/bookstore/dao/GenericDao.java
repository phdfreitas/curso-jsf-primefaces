package br.com.bookstore.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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
	
	@SuppressWarnings("unchecked")
	public T findByAttribute(Class<T> modelClass, String attribute, String attributeValue) {
		
		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<T> query = cb.createQuery(modelClass);
		Root<T> root = query.from(modelClass);
		
		query.select(root).where(cb.equal(root.get(attribute), attributeValue));
		
		return manager.createQuery(query).getSingleResult();
	}
}
