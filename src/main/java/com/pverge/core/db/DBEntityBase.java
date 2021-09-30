package com.pverge.core.db;

import javax.persistence.EntityManager;

public abstract class DBEntityBase<T> {

	protected EntityManager entityManager;
	protected abstract void setEntityManager(EntityManager entityManager);

	public void insert(T entity) {
		entityManager.persist(entity);
	}

	public void update(T entity) {
		entityManager.merge(entity);
	}

	public void delete(T entity) {
		entityManager.remove(entityManager.merge(entity));
	}

}