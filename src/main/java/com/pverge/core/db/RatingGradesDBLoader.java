package com.pverge.core.db;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.pverge.core.db.dbobjects.RatingGradesEntity;

/**
 * DB - Load ratings data of vehicles (Grades)
 * @author Hypernucle
 */
@Stateless
public class RatingGradesDBLoader extends DBEntityBase<RatingGradesEntity> {

	@PersistenceContext
	protected void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public RatingGradesEntity findGrade(int vcode, int grade) {
		TypedQuery<RatingGradesEntity> query = entityManager.createNamedQuery("RatingGradesEntity.findGrade", RatingGradesEntity.class);
		query.setParameter("vcode", vcode);
		query.setParameter("grade", grade);
		
		List<RatingGradesEntity> resultList = query.getResultList();
		return !resultList.isEmpty() ? resultList.get(0) : null;
	}

}
