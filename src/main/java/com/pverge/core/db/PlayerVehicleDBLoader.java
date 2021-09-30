package com.pverge.core.db;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.pverge.core.db.dbobjects.PlayerVehicleEntity;

@Stateless
public class PlayerVehicleDBLoader extends DBEntityBase<PlayerVehicleEntity> {

	@PersistenceContext
	protected void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public List<PlayerVehicleEntity> getPlayerVehicles(String pid) {
		TypedQuery<PlayerVehicleEntity> query = entityManager.createNamedQuery("PlayerVehicleEntity.getPlayerVehicles", PlayerVehicleEntity.class);
		query.setParameter("pid", pid);
		
		List<PlayerVehicleEntity> resultList = query.getResultList();
		return !resultList.isEmpty() ? query.getResultList() : null;
	}
	
	public PlayerVehicleEntity getVehicleByVid(int vid) {
		TypedQuery<PlayerVehicleEntity> query = entityManager.createNamedQuery("PlayerVehicleEntity.getVehicleByVid", PlayerVehicleEntity.class);
		query.setParameter("id", vid);
		
		List<PlayerVehicleEntity> resultList = query.getResultList();
		return !resultList.isEmpty() ? resultList.get(0) : null;
	}

}
