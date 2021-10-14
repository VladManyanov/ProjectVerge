package com.pverge.core.db;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.pverge.core.db.dbobjects.AttrsPartsEntity;

/**
 * DB - Load performance parts data
 * @author Hypernucle
 */
@Stateless
public class AttrsPartsDBLoader extends DBEntityBase<AttrsPartsEntity> {

	@PersistenceContext
	protected void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public AttrsPartsEntity getPart(int partId) {
		TypedQuery<AttrsPartsEntity> query = entityManager.createNamedQuery("AttrsPartsEntity.getPart", AttrsPartsEntity.class);
		query.setParameter("partId", partId);
		
		List<AttrsPartsEntity> resultList = query.getResultList();
		return !resultList.isEmpty() ? resultList.get(0) : null;
	}

}
