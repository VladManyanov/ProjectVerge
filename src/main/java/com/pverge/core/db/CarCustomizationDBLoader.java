package com.pverge.core.db;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.pverge.core.db.dbobjects.CarCustomizationEntity;

/**
 * DB - Load car customization data
 * @author Hypernucle
 */
@Stateless
public class CarCustomizationDBLoader extends DBEntityBase<CarCustomizationEntity> {

	@PersistenceContext
	protected void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public CarCustomizationEntity getItemProperties(int code) {
		TypedQuery<CarCustomizationEntity> query = entityManager.createNamedQuery("CarCustomizationEntity.getItemProperties", CarCustomizationEntity.class);
		query.setParameter("code", code);
		
		List<CarCustomizationEntity> resultList = query.getResultList();
		return !resultList.isEmpty() ? resultList.get(0) : null;
	}
	
	public List<CarCustomizationEntity> loadAllItems() {
		TypedQuery<CarCustomizationEntity> query = entityManager.createNamedQuery("CarCustomizationEntity.loadAllItems", CarCustomizationEntity.class);
		return query.getResultList();
	}

}
