package com.pverge.core.db;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.pverge.core.db.dbobjects.AttrsPartsInfoEntity;

/**
 * DB - Load performance parts (info) data
 * @author Hypernucle
 */
@Stateless
public class AttrsPartsInfoDBLoader extends DBEntityBase<AttrsPartsInfoEntity> {

	@PersistenceContext
	protected void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public AttrsPartsInfoEntity getPartInfo(int partId) {
		TypedQuery<AttrsPartsInfoEntity> query = entityManager.createNamedQuery("AttrsPartsInfoEntity.getPartInfo", AttrsPartsInfoEntity.class);
		query.setParameter("partId", partId);
		
		List<AttrsPartsInfoEntity> resultList = query.getResultList();
		return !resultList.isEmpty() ? resultList.get(0) : null;
	}

}
