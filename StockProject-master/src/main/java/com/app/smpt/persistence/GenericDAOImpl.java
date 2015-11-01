package com.app.smpt.persistence;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnitUtil;

import com.app.smpt.common.exception.SystemException;


@SuppressWarnings("unchecked")
public abstract class GenericDAOImpl<T, ID extends Serializable> implements GenericDAO<T, ID> {

	private static final long serialVersionUID = 1L;

	private Class<T> persistentClass;

	@PersistenceContext
	protected EntityManager entityManager;

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public GenericDAOImpl() {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@Override
	public PersistenceUnitUtil getPersistenceUnitUtil(){		
			return entityManager.getEntityManagerFactory().getPersistenceUnitUtil();	
	}
	
	public Class<T> getPersistentClass() {
		return persistentClass;
	}

	@Override
	public T getReference(ID id) {
		return entityManager.getReference(persistentClass, id);
	}

	@Override
	public T loadById(ID id) {
		return entityManager.find(persistentClass, id);
	}

	@Override
	public void persist(T entity) throws SystemException {
		try {
			entityManager.persist(entity);			
		} catch (Exception ex) {
			ex.printStackTrace();			
			throw new SystemException("generic.error.occured.while", new String[]{"persisting " + entity.getClass().getName()}, ex);
		}
	}

	@Override
	public void update(T entity) throws SystemException{
		try {
			entityManager.merge(entity);
		} catch (Exception ex) {
			ex.printStackTrace();			
			throw new SystemException("generic.error.occured.while", new String[]{"updating " + entity.getClass().getName()}, ex);
		}
	}

	@Override
	public void delete(ID id) throws SystemException {
		T dbEntity = null;
		try {
			dbEntity = entityManager.getReference(persistentClass, id);
			entityManager.remove(dbEntity);
		} catch (Exception ex) {
			ex.printStackTrace();			
			throw new SystemException("generic.error.occured.while", new String[]{"delete"}, ex);
		}
	}

	@Override
	public List<T> loadAll() {
		return entityManager.createQuery("Select t from " + persistentClass.getSimpleName() + " t").getResultList();
	}

}
