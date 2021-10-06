package com.pverge.core.db;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.pverge.core.db.dbobjects.CarColorsEntity;

/**
 * DB - Load car colors data
 * @author Hypernucle
 */
@Stateless
public class CarColorsDBLoader extends DBEntityBase<CarColorsEntity> {

	@PersistenceContext
	protected void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public CarColorsEntity getColor(int code) {
		TypedQuery<CarColorsEntity> query = entityManager.createNamedQuery("CarColorsEntity.getColor", CarColorsEntity.class);
		query.setParameter("code", code);
		
		List<CarColorsEntity> resultList = query.getResultList();
		return !resultList.isEmpty() ? resultList.get(0) : null;
	}

}
