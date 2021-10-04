package com.pverge.core.be.util;

import java.util.Arrays;

public enum CoreGameMode {
	
	EDGE_MiddleWay(2), //
	EDGE_RedwoodHill(3), //
	EDGE_DesertValley(4), //
	EDGE_WestRidgeDrive(5), //
	EDGE_PacificWoods(6), //
	EDGE_StableRoad(8), //
	EDGE_Vineyard(9), //
	EDGE_MadMax02(10), //
	EDGE_SermattPass(11), //
	EDGE_Downspeed(12), //
	EDGE_GrandParkDrive(13), //
	EDGE_DuskCoast(14), //
	EDGE_BaysideHills(15), //
	EDGE_RedCanyon(16), //
	EDGE_AbondonedBridge(17), //
	EDGE_WaterfallHighway(18), //
	EDGE_GrandPeakDownhill(19), //
	EDGE_LongnightFreeway(20), //
	EDGE_DawnSnake_01(21), //
	EDGE_Freeway(22), //
	EDGE_DesertCircuit(23), //
	EDGE_Moebius(24), //
	EDGE_LawlessZone(25), //
	EDGE_DesertValleyReverse(26), //
	EDGE_DownspeedReverse(27), //
	EDGE_WesternTown(29), //
	EDGE_CampingGround(30), //
	EDGE_DowntownCircuit(31), //
	EDGE_DriftMode(32), //
	EDGE_ShanghaiTrack(33), //
	EDGE_FoxhuntingMode(35), //
	EDGE_DeathFogRoad(36), //
	EDGE_DesertMountain(37), //
	EDGE_NightofCity(38), //
	EDGE_AbandonedRoad(39), //
	EDGE_LicenseTrack(99), //
	DUP_EDGE_MiddleWay(102), // Same level
	DUP_EDGE_DesertValley(104), // Same level
	DUP_EDGE_Downspeed(105), // Same level
	DUP_EDGE_PacificWoods(106), // Same level
	DUP_EDGE_WestRidgeDrive(107), // Same level
	DUP_EDGE_ShanghaiTrack(109), // Same level
	EDGE_RWH_Pursuit(203), //
	EDGE_GPDH_Pursuit(204), //
	EDGE_WRD_Pursuit(205), //
	EDGE_DV_Pursuit(206); //

	private int id;

	private CoreGameMode(int id) {
		this.id = id;
	}

	public int getId() {
		return Integer.valueOf(id);
	}
	
	public Long getIdLong() {
		return Integer.valueOf(id).longValue();
	}

	public static CoreGameMode valueOf(int value) {
		return Arrays.stream(values()).filter(legNo -> legNo.id == value).findFirst().get();
	}
}
