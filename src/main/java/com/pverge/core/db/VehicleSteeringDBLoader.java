package com.pverge.core.db;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.pverge.core.db.dbobjects.VehicleSteeringEntity;
import com.pverge.core.socket.dataobjects.SIOAssetVehicleObjects.Steering;
import com.pverge.core.socket.dataobjects.SIOAssetVehicleObjects.SteeringOpts;

/**
 * DB - Load vehicle steering settings
 * @author Hypernucle
 */
@Stateless
public class VehicleSteeringDBLoader extends DBEntityBase<VehicleSteeringEntity> {

	@PersistenceContext
	protected void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public VehicleSteeringEntity findByVid(String vid) {
		TypedQuery<VehicleSteeringEntity> query = entityManager.createNamedQuery("VehicleSteeringEntity.findByVid", VehicleSteeringEntity.class);
		query.setParameter("vid", Integer.parseInt(vid));
		
		List<VehicleSteeringEntity> resultList = query.getResultList();
		return !resultList.isEmpty() ? resultList.get(0) : null;
	}
	
	public void updateSteering(String vid, SteeringOpts newSteering) {
		List<Steering> steeringOpts = newSteering.getSteering(); // Must contain 14 values
		Query query = entityManager.createNamedQuery("VehicleSteeringEntity.setSteering");
		
		query.setParameter("vid", Integer.parseInt(vid));
		query.setParameter("v0", steeringOpts.get(0).getVal());
		query.setParameter("v1", steeringOpts.get(1).getVal());
		query.setParameter("v2", steeringOpts.get(2).getVal());
		query.setParameter("v3", steeringOpts.get(3).getVal());
		query.setParameter("v4", steeringOpts.get(4).getVal());
		query.setParameter("v5", steeringOpts.get(5).getVal());
		query.setParameter("v6", steeringOpts.get(6).getVal());
		query.setParameter("v7", steeringOpts.get(7).getVal());
		query.setParameter("v8", steeringOpts.get(8).getVal());
		query.setParameter("v9", steeringOpts.get(9).getVal());
		query.setParameter("v10", steeringOpts.get(10).getVal());
		query.setParameter("v11", steeringOpts.get(11).getVal());
		query.setParameter("v12", steeringOpts.get(12).getVal());
		query.setParameter("v13", steeringOpts.get(13).getVal());
		query.executeUpdate();
	}
	
	public boolean isSteeringValuesEqual(VehicleSteeringEntity entity) {
		// TODO
		return true;
	}

}
