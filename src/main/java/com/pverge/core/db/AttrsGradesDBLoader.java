package com.pverge.core.db;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.pverge.core.db.dbobjects.AttrsGradesEntity;

/**
 * DB - Load car performance attributes data
 * @author Hypernucle
 */
@Stateless
public class AttrsGradesDBLoader extends DBEntityBase<AttrsGradesEntity> {

	@PersistenceContext
	protected void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public AttrsGradesEntity getAttrsByGrade(int vcode, int grade) {
		TypedQuery<AttrsGradesEntity> query = entityManager.createNamedQuery("AttrsGradesEntity.getAttrsByGrade", AttrsGradesEntity.class);
		query.setParameter("vcode", vcode);
		query.setParameter("grade", grade);
		
		List<AttrsGradesEntity> resultList = query.getResultList();
		return !resultList.isEmpty() ? resultList.get(0) : null;
	}

}
