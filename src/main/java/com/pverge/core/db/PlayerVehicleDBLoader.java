package com.pverge.core.db;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.pverge.core.db.dbobjects.PlayerVehicleEntity;

/**
 * DB - Load vehicle-related data
 * @author Hypernucle
 */
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
	
	public PlayerVehicleEntity getVehicleByVid(String vid) {
		TypedQuery<PlayerVehicleEntity> query = entityManager.createNamedQuery("PlayerVehicleEntity.getVehicleByVid", PlayerVehicleEntity.class);
		query.setParameter("id", Integer.parseInt(vid));
		
		List<PlayerVehicleEntity> resultList = query.getResultList();
		return !resultList.isEmpty() ? resultList.get(0) : null;
	}
	
	public void setCustomization(String vid, int colorCode, int wheelColor, int wrapCode) {
		Query query = entityManager.createNamedQuery("PlayerVehicleEntity.setCustomization");
		query.setParameter("id", Integer.parseInt(vid));
		query.setParameter("colorCode", colorCode);
		query.setParameter("wrapCode", wrapCode);
		query.setParameter("wheelColor", wheelColor);
		query.executeUpdate();
	}

}
