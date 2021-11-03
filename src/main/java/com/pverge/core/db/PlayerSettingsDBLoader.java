package com.pverge.core.db;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.pverge.core.be.util.MiscUtils;
import com.pverge.core.db.dbobjects.PlayerSettingsEntity;
import com.pverge.core.socket.dataobjects.SIOPlayerObjects.GameSetting;
import com.pverge.core.socket.dataobjects.SIOPlayerObjects.InputKey;
import com.pverge.core.socket.dataobjects.SIOPlayerObjects.PlayerConfig;

/**
 * DB - Load player settings (Inputs & gameplay)
 * @author Hypernucle
 */
@Stateless
public class PlayerSettingsDBLoader extends DBEntityBase<PlayerSettingsEntity> {

	MiscUtils utils = new MiscUtils();
	
	@PersistenceContext
	protected void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public PlayerSettingsEntity findByPid(String pid) {
		TypedQuery<PlayerSettingsEntity> query = entityManager.createNamedQuery("PlayerSettingsEntity.findByPid", PlayerSettingsEntity.class);
		query.setParameter("pid", Integer.parseInt(pid));
		
		List<PlayerSettingsEntity> resultList = query.getResultList();
		return !resultList.isEmpty() ? resultList.get(0) : null;
	}
	
	public void updateSettings(String pid, PlayerConfig playerConfig) {
		GameSetting gameSetting = playerConfig.getGameSetting();
		InputKey inputKey = playerConfig.getInputKey();
		Query query = entityManager.createNamedQuery("PlayerSettingsEntity.setSettings");
		
		query.setParameter("pid", Integer.parseInt(pid));
		query.setParameter("minimapPosition", gameSetting.getMinimapPosition());
		query.setParameter("roomMirrorOff", gameSetting.getRoomMirrorOff());
		query.setParameter("useHcs", gameSetting.getUseHcs());
		query.setParameter("useEsc", gameSetting.getUseEsc());
		query.setParameter("useAbs", gameSetting.getUseAbs());
		query.setParameter("actionFeedbackOn", gameSetting.getActionFeedbackOn());
		query.setParameter("keyGuideOn", gameSetting.getKeyGuideOn());
		query.setParameter("vehicleCameraMode", gameSetting.getVehicleCameraMode());
		query.setParameter("chatOn", gameSetting.getChatOn());
		query.setParameter("flevron", gameSetting.getFlevron());
		//
		query.setParameter("toggleWorldmap", utils.IntListToStr(inputKey.getToggleWorldmap()));
		query.setParameter("useItem2", utils.IntListToStr(inputKey.getUseItem2()));
		query.setParameter("secondBrake", utils.IntListToStr(inputKey.getSecondBrake()));
		query.setParameter("throttle", utils.IntListToStr(inputKey.getThrottle()));
		query.setParameter("brake", utils.IntListToStr(inputKey.getBrake()));
		query.setParameter("steeringLeft", utils.IntListToStr(inputKey.getSteeringLeft()));
		query.setParameter("steeringRight", utils.IntListToStr(inputKey.getSteeringRight()));
		query.setParameter("nitro", utils.IntListToStr(inputKey.getNitro()));
		query.setParameter("handBrake", utils.IntListToStr(inputKey.getHandBrake()));
		query.setParameter("useItem", utils.IntListToStr(inputKey.getUseItem()));
		query.setParameter("reset", utils.IntListToStr(inputKey.getReset()));
		query.setParameter("rearView", utils.IntListToStr(inputKey.getRearView()));
		query.setParameter("leftView", utils.IntListToStr(inputKey.getLeftView()));
		query.setParameter("rightView", utils.IntListToStr(inputKey.getRightView()));
		query.setParameter("horn", utils.IntListToStr(inputKey.getHorn()));
		query.setParameter("toggleCamera", utils.IntListToStr(inputKey.getToggleCamera()));
		query.setParameter("toggleMinimap", utils.IntListToStr(inputKey.getToggleMinimap()));

		query.executeUpdate();
	}

}
