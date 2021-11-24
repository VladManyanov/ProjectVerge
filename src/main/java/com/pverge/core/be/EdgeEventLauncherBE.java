package com.pverge.core.be;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.pverge.core.be.util.TrackCode;
import com.pverge.core.db.PlayerDBLoader;
import com.pverge.core.db.PlayerVehicleDBLoader;
import com.pverge.core.db.dbobjects.PlayerVehicleEntity;
import com.pverge.core.db.dbobjects.RatingVehiclesEntity;
import com.pverge.core.socket.NettySocketIO;
import com.pverge.core.socket.dataobjects.SIOChannelJoinObjects.OWJoinOpts;
import com.pverge.core.socket.dataobjects.SIODataObjects.*;
import com.pverge.core.socket.dataobjects.SIOMatch2Objects.End;
import com.pverge.core.socket.dataobjects.SIOMatch2Objects.MatchPeerOpts;
import com.pverge.core.socket.dataobjects.SIOMatch2Objects.TakeDownTarget;
import com.pverge.core.socket.dataobjects.SIOMatchObjects.*;
import com.pverge.core.socket.dataobjects.SIOMatchRewardObjects.CardsGain;
import com.pverge.core.socket.dataobjects.SIOMatchRewardObjects.CardsLost;
import com.pverge.core.socket.dataobjects.SIOMatchRewardObjects.RewardCard;
import com.pverge.core.socket.dataobjects.SIOMatchRewardObjects.RewardDexp;
import com.pverge.core.socket.dataobjects.SIOMatchRewardObjects.RewardExp;
import com.pverge.core.socket.dataobjects.SIOMatchRewardObjects.RewardHistory;
import com.pverge.core.socket.dataobjects.SIOMatchRewardObjects.RewardItems;
import com.pverge.core.socket.dataobjects.SIOMatchRewardObjects.RewardSP;
import com.pverge.core.socket.dataobjects.SIOMatchRewardObjects.RewardsOpts;
import com.pverge.core.socket.dataobjects.SIOPlayerObjects.OptsStateInfo;
import com.pverge.core.socket.dataobjects.SIOPlayerObjects.StateInfo;
import com.pverge.core.socket.dataobjects.SIORaceCommonObjects.*;
import com.pverge.core.socket.dataobjects.SIOTimeTrialObjects.*;

/**
 * Prepare and send race events with Socket
 * @author Hypernucle
 */
@Stateless
public class EdgeEventLauncherBE {

	@EJB
	private PlayerDBLoader playerDB;
	@EJB
	private PlayerVehicleDBLoader playerVehicleDB;
	@EJB
	private EdgeMatchCreationBE edgeMatchCreationBE;
	@EJB
	private EdgeSocketVehiclesBE edgeSocketVehiclesBE;
	@EJB
	private EdgeVehiclesBE edgeVehiclesBE;
	@EJB
	private EdgeTokensBE edgeTokensBE;
	
	NettySocketIO socketIO = new NettySocketIO();
	static int[] aiDrivers = new int[]{677,691,693,694,695}; 
	
	// TODO Try to add cars & tracks choices?
	
	/**
	 * Get correct track level resource name
	 */
	public String getTrackLevel(int trackCode) {
		String trackLevel = TrackCode.valueOf(trackCode).toString();
		if (trackLevel.startsWith("DUP_")) { // Some level resource names is the same
			trackLevel = trackLevel.replaceFirst("DUP_", "");
		}
		return trackLevel;
	}
	
	/**
	 * Get random track for Room. Note that currently we ignore track collections restrictions of gamemodes
	 */
	public int pickRandomTrack() {
		Random random = new Random();
		TrackCode[] tracks = TrackCode.values();
		int pickId = random.nextInt(tracks.length);
		return tracks[pickId].getId();
	}
	
	/**
	 * Get correct core Game Mode, from meta Game Mode
	 */
	public String getGameModeCore(String gameModeMeta) {
		String coreGameMode = "";
		switch(gameModeMeta) {
		case "SPEEDINDIVIDUAL":
			coreGameMode = "GameMode_Speed_01_Schematic";
			break;
		case "ITEMINDIVIDUAL":
			coreGameMode = "GameMode_ItemBattle_01_Schematic";
			break;
		case "DRIFTINDIVIDUAL":
			coreGameMode = "GameMode_Drift_01_Schematic";
			break;
		case "FOXHUNTINGINDIVIDUAL": case "PURSUITINDIVIDUAL": case "SPEEDTEAM": default:
			System.out.println("!!! [Room] Game mode " + gameModeMeta + " does not currently supported.");
			break;
		}
		return coreGameMode;
	}
	
	/**
	 * Get number of laps, depending on track
	 */
	public int getLapsAmount(String level) {
		int laps = 1;
		if (level.contentEquals("EDGE_CampingGround") || level.contentEquals("EDGE_ShanghaiTrack") ||
				level.contentEquals("EDGE_Moebius") || level.contentEquals("EDGE_DesertCircuit") || 
				level.contentEquals("EDGE_WesternTown") || level.contentEquals("EDGE_DuskCoast")
				|| level.contentEquals("EDGE_DriftMode") || level.contentEquals("EDGE_BaysideHills")) {
			laps = 2;
		}
		return laps;
	}
	
	/**
	 * Enable traffic, if Track Code allows it
	 */
	public boolean isTrafficEnabled(int trackCode) {
		if (trackCode == 102 || trackCode == 104 || trackCode == 105 || trackCode == 106 ||
				trackCode == 107 || trackCode == 109) {
			return true;
		}
		return false;
	}
	
	/**
	 * Prepare Time Trial event
	 */
	public void prepareTimeTrial(String playerId, String trackLevel) {
		PlayerVehicleEntity currentVehicle = playerVehicleDB.getVehicleByVid(playerDB.getPlayer(playerId).getVid());
		
		ResourceDataObject ttRootData = new ResourceDataObject();
		
		ttRootData.setCmd("match2.start.timetrial.play");
		TTOpts ttOpts = new TTOpts();
		ttOpts.setMatchId(1);
		ttOpts.setCreator("timetrial");
		ttOpts.setCoreGameModeSchematic("GameMode_TimeTrial_01_Schematic");
		ttOpts.setLevel(trackLevel);
		ttOpts.setLaps(getLapsAmount(trackLevel));
		ttOpts.setManagerMode(false);
		ttRootData.setOpts(ttOpts);
		
		List<Observers> observersList = new ArrayList<>();
		ttOpts.setObservers(observersList); // Empty
		
		List<Clients> clientList = new ArrayList<>();
		clientList.add(edgeMatchCreationBE.createPlayerClient(playerId, currentVehicle, false));
		ttOpts.setClients(clientList);
		
		socketIO.sendEvent("msg", ttRootData, ttRootData.getCmd(), edgeTokensBE.getSessionUUID(playerId));
	}
	
	/**
	 * Prepare Room (SuperPeer mode) event. Two events should be sent to initiate the race.
	 * Note: to run races locally, user must be on "Super Peer" mode (on client side)
	 */
	public void prepareRoomSuperPeer(String playerId, int trackCode, String gameModeMeta, 
			int maxPlayers) {
		if (maxPlayers > 6) {
			maxPlayers = 6; // Dirty fix for core level test mode
		}
		maxPlayers = maxPlayers - 1;
		PlayerVehicleEntity currentVehicle = playerVehicleDB.getVehicleByVid(playerDB.getPlayer(playerId).getVid());
		RatingVehiclesEntity ratingEntity = edgeVehiclesBE.calcCarRating(currentVehicle);
		
		ResourceDataObject matchCreatedRootData = new ResourceDataObject();
		matchCreatedRootData.setCmd("match2.created");
		MatchCreatedOpts matchOpts = new MatchCreatedOpts();
		matchOpts.setMatchId(1);
		List<Observers> observersList = new ArrayList<>();
		matchOpts.setObservers(observersList);
		
		List<Integer> aisList = new ArrayList<>();
		switch(gameModeMeta) {
		case "SPEEDINDIVIDUAL":
			for (int i = 0; i < maxPlayers; i++) {
				aisList.add(aiDrivers[i]); // add AI driver 
			}
			break;
		case "ITEMINDIVIDUAL":
			if (maxPlayers != 0) {
				aisList.add(aiDrivers[0]); // add AI driver (Item Battle is unstable with AI, so we add only one bot)
			}
			break;
		default:
			break;
		}
		matchOpts.setAIs(aisList);
		
		matchOpts.setGameMode(gameModeMeta);
		matchOpts.setUseDediSvr(true);
		matchOpts.setTrackCode(trackCode);
		matchOpts.setSchematic(getGameModeCore(gameModeMeta));
		
		List<Players> playersList = new ArrayList<>();
		Players player = new Players();
		
		player.setId(playerId);
		player.setPlate(edgeMatchCreationBE.getDefaultPlate(playerId));
		player.setTeam(0);
		
		Vehicle vehicle = new Vehicle();
		vehicle.setCode(currentVehicle.getVcode());
		vehicle.setGrade(currentVehicle.getGrade());
		vehicle.setId(currentVehicle.getId());
		vehicle.setIGR(false);
		vehicle.setOvr(ratingEntity.getOvrDefault());
		
		Paint paint = new Paint();
		paint.setColorCode(currentVehicle.getColorCode());
		paint.setWheelCode(currentVehicle.getWheelColor());
		paint.setWrapCode(currentVehicle.getWrapCode());
		vehicle.setPaint(paint);
		
		Parts parts = new Parts();
		parts.setBumper(currentVehicle.getPartBumper());
		parts.setEngine(currentVehicle.getPartEngine());
		parts.setFrame(currentVehicle.getPartFrame());
		parts.setNitroTank(currentVehicle.getPartNitroTank());
		parts.setTransmission(currentVehicle.getPartTransmission());
		vehicle.setParts(parts);
		
		Status status = new Status();
		status.setAcceleration(ratingEntity.getAcceleration());
		status.setDurability(ratingEntity.getDurability());
		status.setNitroCapacity(ratingEntity.getNitroCapacity());
		status.setStrength(ratingEntity.getStrength());
		status.setTopSpeed(ratingEntity.getTopSpeed());
		vehicle.setStatus(status);
		vehicle.setSteering(edgeSocketVehiclesBE.prepareSteeringSIO(currentVehicle.getSteering()));
		
		player.setVehicle(vehicle);
		playersList.add(player);
		matchOpts.setPlayers(playersList);
		
		matchCreatedRootData.setOpts(matchOpts);
		socketIO.sendEvent("msg", matchCreatedRootData, matchCreatedRootData.getCmd(), edgeTokensBE.getSessionUUID(playerId));
	}
	
	/**
	 * Prepare Room (SuperPeer mode) event. Two events should be sent to initiate the race.
	 * Note: to run races locally, user must be on "Super Peer" mode (on client side)
	 */
	public void prepareRoomSuperPeer2(String playerId, int trackCode, String gameModeMeta, int maxPlayers) {
		if (maxPlayers > 6) {
			maxPlayers = 6; // Dirty fix for core level test mode
		}
		maxPlayers = maxPlayers - 1;
		String level = getTrackLevel(trackCode);
		PlayerVehicleEntity currentVehicle = playerVehicleDB.getVehicleByVid(playerDB.getPlayer(playerId).getVid());
		String gameModeCore = getGameModeCore(gameModeMeta);
		
		ResourceDataObject matchSuperPeerRootData = new ResourceDataObject();
		matchSuperPeerRootData.setCmd("match2.start.superpeer");
		MatchPeerOpts superPeerOpts = new MatchPeerOpts();
		superPeerOpts.setMatchId(1);
		superPeerOpts.setCreator("room2");
		superPeerOpts.setCoreGameModeSchematic(gameModeCore);
		superPeerOpts.setLevel(level);
		superPeerOpts.setLaps(getLapsAmount(level));
		superPeerOpts.setManagerMode(false);
		superPeerOpts.setClientVersion(0);
		List<Observers> observersList = new ArrayList<>();
		superPeerOpts.setObservers(observersList);
		superPeerOpts.setSchematic(gameModeCore);
		superPeerOpts.setTraffic(isTrafficEnabled(trackCode));
		
		End end = new End();
		end.setChecker(15);
		end.setLimitTime(0);
		end.setTakeDowns(0);
		List<TakeDownTarget> takeDownTargetList = new ArrayList<>();
		end.setTakeDownTarget(takeDownTargetList);
		superPeerOpts.setEnd(end);
		
		List<Clients> clientList = new ArrayList<>();
		clientList.add(edgeMatchCreationBE.createPlayerClient(playerId, currentVehicle, false));
		switch(gameModeMeta) {
		case "SPEEDINDIVIDUAL":
			for (int i = 0; i < maxPlayers; i++) {
				String aiPlayerId = String.valueOf(aiDrivers[i]);
				clientList.add(edgeMatchCreationBE.createPlayerClient(aiPlayerId, currentVehicle, true));
			}
			break;
		case "ITEMINDIVIDUAL":
			if (maxPlayers != 0) {
				clientList.add(edgeMatchCreationBE.createPlayerClient(String.valueOf(aiDrivers[0]), currentVehicle, true));
			}
			break;
		default:
			break;
		}
		superPeerOpts.setClients(clientList);
		matchSuperPeerRootData.setOpts(superPeerOpts);
		socketIO.sendEvent("msg", matchSuperPeerRootData, matchSuperPeerRootData.getCmd(), edgeTokensBE.getSessionUUID(playerId));
	}
	
	/**
	 * Send updated current player vehicle request
	 */
	public void changeRecentVehicleSIO(String pid, String vid) {
		ResourceListDataObject recentRootData = new ResourceListDataObject();
		List<Object> optsList = new ArrayList<>();
		MessageDataStringObject recentOpts = new MessageDataStringObject();
		recentOpts.setUri("/players/" + pid + "/recent/vid");
		recentOpts.setBody(vid);
		optsList.add(recentOpts);
		recentRootData.setCmd("resources");
		recentRootData.setOpts(optsList);
		
		socketIO.sendEvent("msg", recentRootData, recentRootData.getCmd(), edgeTokensBE.getSessionUUID(pid));
	}
	
	/**
	 * Server time sync message
	 */
	public void syncServerTimeSIO(String nowTime, String playerId) {
		ResourceListDataObject timeRootData = new ResourceListDataObject();
		List<Object> optsList = new ArrayList<>();
		OWJoinOpts timeOpts = new OWJoinOpts();
		timeOpts.setUri("/sync/servertime");
		
		timeOpts.setBody(nowTime);
		optsList.add(timeOpts);
		timeRootData.setCmd("resources");
		timeRootData.setOpts(optsList);
		
		socketIO.sendEvent("msg", timeRootData, timeRootData.getCmd(), edgeTokensBE.getSessionUUID(playerId));
	}
	
	/**
	 * Match finish & rewards request. Contains Match End and Rewards parts
	 */
	public void MatchRewardSIO(String playerId, String gameModeMeta, int trackId) {
		PlayerVehicleEntity currentVehicle = playerVehicleDB.getVehicleByVid(playerDB.getPlayer(playerId).getVid());
		ResourceListDataObject matchRoot = new ResourceListDataObject();
		List<Object> optsList = new ArrayList<>();
		MessageDataObject matchObj = new MessageDataObject();
		
		// Match info part
		matchObj.setUri("/v2/room2s/1/matchend"); // 1 is Match ID
		MatchEnd matchEnd = new MatchEnd();
		matchEnd.setId(1);
		matchEnd.setTrackCode(trackId);
		matchEnd.setGameMode(gameModeMeta);
		matchEnd.setMatchEndAt(123456789);
		
		List<MatchPlayer> matchPlayers = new ArrayList<>();
		MatchPlayer matchPlayer = new MatchPlayer();
		matchPlayers.add(matchPlayer);
		matchPlayer.setPid(playerId);
		matchPlayer.setSid("");
		matchPlayer.setRank(1); // TODO Detect player places somehow
		matchPlayer.setRetire(false);
		matchPlayer.setChannel("ALL");
		matchPlayer.setTeam(0);
		
		Vehicle vehicle = new Vehicle();
		vehicle.setCode(currentVehicle.getVcode());
		vehicle.setGrade(currentVehicle.getGrade());
		
		Parts parts = new Parts();
		parts.setBumper(currentVehicle.getPartBumper());
		parts.setEngine(currentVehicle.getPartEngine());
		parts.setFrame(currentVehicle.getPartFrame());
		parts.setNitroTank(currentVehicle.getPartNitroTank());
		parts.setTransmission(currentVehicle.getPartTransmission());
		vehicle.setParts(parts);
		
		Status status = new Status();
		status.setAcceleration(765);
		status.setDurability(658);
		status.setNitroCapacity(777);
		status.setStrength(680);
		status.setTopSpeed(793);
		vehicle.setStatus(status);
		
		matchPlayer.setVehicle(vehicle);
		matchPlayer.setForceLeave(false);
		matchPlayer.setAbuseStatus(0);
		matchEnd.setPlayers(matchPlayers);
		matchObj.setBody(matchEnd);

		// Rewards part
		MessageDataObject rewardObj = new MessageDataObject();
		rewardObj.setUri("/v2/room2s/1/matchend/" + playerId + "/reward"); // 1 is Match ID
		RewardsOpts rewardsOpts = new RewardsOpts();
		rewardObj.setBody(rewardsOpts);
		
		RewardSP rewardSP = new RewardSP();
		rewardsOpts.setSp(rewardSP);
		rewardSP.setBase(1126);
		rewardSP.setIncrement(0);
		//
		RewardHistory rewardSPHistory = new RewardHistory();
		List<RewardHistory> rewardSPHistoryList = new ArrayList<>();
		rewardSPHistoryList.add(rewardSPHistory);
		rewardSP.setHistory(rewardSPHistoryList);
		//
		rewardSPHistory.setProvider("BASIC");
		rewardSPHistory.setDisplay("TEXT");
		rewardSPHistory.setType("SP_PLUS");
		rewardSPHistory.setCode(1);
		rewardSPHistory.setCount(1166);
		rewardSPHistory.setDisplayValue(1166);
		
		RewardExp rewardExp = new RewardExp();
		rewardsOpts.setExp(rewardExp);
		rewardExp.setBase(925);
		rewardExp.setIncrement(0);
		//
		RewardHistory rewardExpHistory = new RewardHistory();
		List<RewardHistory> rewardExpHistoryList = new ArrayList<>();
		rewardExpHistoryList.add(rewardExpHistory);
		rewardExp.setHistory(rewardExpHistoryList);
		//
		rewardExpHistory.setProvider("BASIC");
		rewardExpHistory.setDisplay("TEXT");
		rewardExpHistory.setType("EXP_PLUS");
		rewardExpHistory.setCode(1);
		rewardExpHistory.setCount(925);
		rewardExpHistory.setDisplayValue(925);
		
		RewardDexp rewardDexp = new RewardDexp();
		rewardsOpts.setDexp(rewardDexp);
		rewardDexp.setBase(0);
		rewardDexp.setIncrement(0);
		//
		List<RewardHistory> rewardDexpHistoryList = new ArrayList<>();
		rewardDexp.setHistory(rewardDexpHistoryList);
		
		RewardCard rewardCard = new RewardCard();
		rewardsOpts.setCard(rewardCard);
		RewardItems rewardItems = new RewardItems();
		rewardCard.setItems(rewardItems);
		List<CardsGain> gainList = new ArrayList<>();
		List<CardsLost> lostList = new ArrayList<>();
		CardsGain cardsGain = new CardsGain();
		gainList.add(cardsGain);
		rewardItems.setGain(gainList);
		rewardItems.setLost(lostList);
		
		cardsGain.setProvider("BASIC"); // Gain - reward which will be given to player (no matter which card)
		cardsGain.setDisplay("ITEM");
		cardsGain.setType("ITEM");
		cardsGain.setCode(400109);
		cardsGain.setCount(1);
		//
		CardsLost cardsLost1 = new CardsLost(); // Lost - "fake" cards with item, cannot be obtainable
		cardsLost1.setProvider("DUMMY");
		cardsLost1.setDisplay("ITEM");
		cardsLost1.setType("BLUEPRINT");
		cardsLost1.setCode(148);
		cardsLost1.setCount(3);
		lostList.add(cardsLost1);
		//
		CardsLost cardsLost2 = new CardsLost();
		cardsLost2.setProvider("DUMMY");
		cardsLost2.setDisplay("ITEM");
		cardsLost2.setType("BLUEPRINT");
		cardsLost2.setCode(148);
		cardsLost2.setCount(3);
		lostList.add(cardsLost2);
		//
		CardsLost cardsLost3 = new CardsLost();
		cardsLost3.setProvider("DUMMY");
		cardsLost3.setDisplay("ITEM");
		cardsLost3.setType("BLUEPRINT");
		cardsLost3.setCode(148);
		cardsLost3.setCount(3);
		lostList.add(cardsLost3);
		
		optsList.add(rewardObj);
		optsList.add(matchObj);
		matchRoot.setCmd("resources");
		matchRoot.setOpts(optsList);
		
		socketIO.sendEvent("msg", matchRoot, matchRoot.getCmd(), edgeTokensBE.getSessionUUID(playerId));
		System.out.println("### [Match] Match ID 1 has been ended, request from player ID " + playerId + ".");
	}
	
	/**
	 * Send current player state data request (SIO)
	 */
	public void stateChangeSIO(String playerId, String state) {
		ResourceDataObject rootObj = new ResourceDataObject();
		rootObj.setCmd("change_presence");
		
		OptsStateInfo optsStateInfo = new OptsStateInfo();
		optsStateInfo.setTime(123456789);
		List<StateInfo> statesList = new ArrayList<>();
		optsStateInfo.setInfo(statesList);
		
		StateInfo stateInfo = new StateInfo();
		stateInfo.setPid(playerId);
		stateInfo.setState(state);
		statesList.add(stateInfo);
		
		rootObj.setOpts(optsStateInfo);
		socketIO.sendEvent("msg", rootObj, rootObj.getCmd(), edgeTokensBE.getSessionUUID(playerId));
	}
}