package com.pverge.core.db;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.pverge.core.db.dbobjects.RatingVehiclesEntity;

/**
 * DB - Load ratings data of vehicles
 * @author Hypernucle
 */
@Stateless
public class RatingVehiclesDBLoader extends DBEntityBase<RatingVehiclesEntity> {

	@PersistenceContext
	protected void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public RatingVehiclesEntity findByCode(int vcode) {
		TypedQuery<RatingVehiclesEntity> query = entityManager.createNamedQuery("RatingVehiclesEntity.findByCode", RatingVehiclesEntity.class);
		query.setParameter("vcode", vcode);
		
		List<RatingVehiclesEntity> resultList = query.getResultList();
		return !resultList.isEmpty() ? resultList.get(0) : null;
	}

}
