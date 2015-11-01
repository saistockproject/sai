package com.app.smpt.persistence;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnitUtil;

import com.app.smpt.common.exception.SystemException;


public interface GenericDAO<T, ID> extends Serializable {

	T loadById(ID id);

	void persist(T entity) throws SystemException;

	void update(T entity) throws SystemException;

	void delete(ID id) throws SystemException;

	List<T> loadAll();

	T getReference(ID id);

	public void setEntityManager(EntityManager entityManager);
	
	public PersistenceUnitUtil getPersistenceUnitUtil();

}
