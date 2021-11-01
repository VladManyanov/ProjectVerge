package com.pverge.core.db;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.pverge.core.db.dbobjects.PlayerEntity;

/**
 * DB - Load player-related data
 * @author Hypernucle
 */
@Stateless
public class PlayerDBLoader extends DBEntityBase<PlayerEntity> {

	@PersistenceContext
	protected void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public PlayerEntity findById(int id) {
		return entityManager.find(PlayerEntity.class, id);
	}

	public PlayerEntity getPlayer(String pid) {
		TypedQuery<PlayerEntity> query = entityManager.createNamedQuery("PlayerEntity.getPlayer", PlayerEntity.class);
		query.setParameter("pid", Integer.parseInt(pid));
		
		List<PlayerEntity> resultList = query.getResultList();
		return !resultList.isEmpty() ? resultList.get(0) : null;
	}
	
	public PlayerEntity findUsername(String username) {
		TypedQuery<PlayerEntity> query = entityManager.createNamedQuery("PlayerEntity.findUsername", PlayerEntity.class);
		query.setParameter("username", username);
		
		List<PlayerEntity> resultList = query.getResultList();
		return !resultList.isEmpty() ? resultList.get(0) : null;
	}
	
	public void changeRecentVehicle(String pid, String vid) {
		Query query = entityManager.createNamedQuery("PlayerEntity.changeRecentVehicle");
		query.setParameter("pid", Integer.parseInt(pid));
		query.setParameter("vid", Integer.parseInt(vid));
		query.executeUpdate();
	}
	
	public void setCampaignCode(String pid, int code) {
		Query query = entityManager.createNamedQuery("PlayerEntity.setCampaignCode");
		query.setParameter("pid", Integer.parseInt(pid));
		query.setParameter("campaignCode", code);
		query.executeUpdate();
	}

}
