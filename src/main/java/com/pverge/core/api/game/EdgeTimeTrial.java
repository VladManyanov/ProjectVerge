package com.pverge.core.api.game;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.pverge.core.be.EdgePresenceBE;

/**
 * Edge - Time Trial mode related requests
 * @author Hypernucle
 */
@Path("/v2")
public class EdgeTimeTrial {
	
	EdgePresenceBE presence = new EdgePresenceBE();
	private static String forcePlayerId = "33";
	// TODO 
	
	/**
	 * Get Time Trial seasons request
	 */
	@GET
	@Path("timetrial/seasons")
	@Produces(MediaType.APPLICATION_JSON)
	public String apiTTSeasons() {
		JsonObject rootJson = new JsonObject();
		JsonObject prevSeasonJson = new JsonObject();
		prevSeasonJson.addProperty("id", "60e720ff7f7ed63ba134565c");
		prevSeasonJson.addProperty("seasonNo", 8);
		prevSeasonJson.addProperty("startAt", "2021-08-05T15:59:59.000Z");
		prevSeasonJson.addProperty("seasonRewardIndex", 1);
		rootJson.add("prevSeason", prevSeasonJson);
		
		JsonObject currSeasonJson = new JsonObject();
		currSeasonJson.addProperty("id", "610c0affb811464e54ffb529");
		currSeasonJson.addProperty("seasonNo", 9);
		currSeasonJson.addProperty("startAt", "2021-09-02T15:59:59.000Z");
		currSeasonJson.addProperty("seasonRewardIndex", 1);
		rootJson.add("currSeason", currSeasonJson);
		
		JsonObject nextSeasonJson = new JsonObject();
		nextSeasonJson.addProperty("id", "6130f4ff9d071f8e1e9ae869");
		nextSeasonJson.addProperty("seasonNo", 10);
		nextSeasonJson.addProperty("startAt", "2021-09-30T15:59:59.000Z");
		nextSeasonJson.addProperty("seasonRewardIndex", 1);
		rootJson.add("nextSeason", nextSeasonJson);
		
		System.out.println("### [Time Trial] Seasons information request from player ID " + forcePlayerId + ".");
	    return rootJson.toString();
	}
	
	/**
	 * Get Time Trial current season medals request
	 */
	@GET
	@Path("timetrial/seasons/current/medal")
	@Produces(MediaType.APPLICATION_JSON)
	public String apiTTSeasonsCurrentMedals() {
		// TODO Lazy
		String json = "{\"seasonId\":\"610c0affb811464e54ffb529\",\"trackMedalRecords\":[{\"trackCode\":2,\"medalSteps\":{\"1\":{\"step\":1,\"laptime\":125549},\"2\":{\"step\":2,\"laptime\":107845},\"3\":{\"step\":3,\"laptime\":95919}}},{\"trackCode\":3,\"medalSteps\":{\"1\":{\"step\":1,\"laptime\":136866},\"2\":{\"step\":2,\"laptime\":120122},\"3\":{\"step\":3,\"laptime\":112546}}},{\"trackCode\":4,\"medalSteps\":{\"1\":{\"step\":1,\"laptime\":219887},\"2\":{\"step\":2,\"laptime\":207099},\"3\":{\"step\":3,\"laptime\":199995}}},{\"trackCode\":5,\"medalSteps\":{\"1\":{\"step\":1,\"laptime\":133176},\"2\":{\"step\":2,\"laptime\":117210},\"3\":{\"step\":3,\"laptime\":109220}}},{\"trackCode\":6,\"medalSteps\":{\"1\":{\"step\":1,\"laptime\":100014},\"2\":{\"step\":2,\"laptime\":84311},\"3\":{\"step\":3,\"laptime\":73705}}},{\"trackCode\":8,\"medalSteps\":{\"1\":{\"step\":1,\"laptime\":180531},\"2\":{\"step\":2,\"laptime\":162653},\"3\":{\"step\":3,\"laptime\":149344}}},{\"trackCode\":9,\"medalSteps\":{\"1\":{\"step\":1,\"laptime\":162478},\"2\":{\"step\":2,\"laptime\":136394},\"3\":{\"step\":3,\"laptime\":125588}}},{\"trackCode\":10,\"medalSteps\":{\"1\":{\"step\":1,\"laptime\":137877},\"2\":{\"step\":2,\"laptime\":119118},\"3\":{\"step\":3,\"laptime\":103205}}},{\"trackCode\":11,\"medalSteps\":{\"1\":{\"step\":1,\"laptime\":105637},\"2\":{\"step\":2,\"laptime\":87751},\"3\":{\"step\":3,\"laptime\":74370}}},{\"trackCode\":12,\"medalSteps\":{\"1\":{\"step\":1,\"laptime\":115853},\"2\":{\"step\":2,\"laptime\":97070},\"3\":{\"step\":3,\"laptime\":84906}}},{\"trackCode\":13,\"medalSteps\":{\"1\":{\"step\":1,\"laptime\":150232},\"2\":{\"step\":2,\"laptime\":132970},\"3\":{\"step\":3,\"laptime\":120356}}},{\"trackCode\":14,\"medalSteps\":{\"1\":{\"step\":1,\"laptime\":114726},\"2\":{\"step\":2,\"laptime\":97713},\"3\":{\"step\":3,\"laptime\":86864}}},{\"trackCode\":15,\"medalSteps\":{\"1\":{\"step\":1,\"laptime\":172961},\"2\":{\"step\":2,\"laptime\":149395},\"3\":{\"step\":3,\"laptime\":134397}}},{\"trackCode\":16,\"medalSteps\":{\"1\":{\"step\":1,\"laptime\":111136},\"2\":{\"step\":2,\"laptime\":95267},\"3\":{\"step\":3,\"laptime\":79657}}},{\"trackCode\":17,\"medalSteps\":{\"1\":{\"step\":1,\"laptime\":127185},\"2\":{\"step\":2,\"laptime\":110819},\"3\":{\"step\":3,\"laptime\":93440}}},{\"trackCode\":18,\"medalSteps\":{\"1\":{\"step\":1,\"laptime\":151038},\"2\":{\"step\":2,\"laptime\":136315},\"3\":{\"step\":3,\"laptime\":125182}}},{\"trackCode\":19,\"medalSteps\":{\"1\":{\"step\":1,\"laptime\":125601},\"2\":{\"step\":2,\"laptime\":106994},\"3\":{\"step\":3,\"laptime\":89435}}},{\"trackCode\":20,\"medalSteps\":{\"1\":{\"step\":1,\"laptime\":131459},\"2\":{\"step\":2,\"laptime\":114505},\"3\":{\"step\":3,\"laptime\":107203}}},{\"trackCode\":21,\"medalSteps\":{\"1\":{\"step\":1,\"laptime\":157288},\"2\":{\"step\":2,\"laptime\":137740},\"3\":{\"step\":3,\"laptime\":127827}}},{\"trackCode\":22,\"medalSteps\":{\"1\":{\"step\":1,\"laptime\":139084},\"2\":{\"step\":2,\"laptime\":130140},\"3\":{\"step\":3,\"laptime\":121988}}},{\"trackCode\":23,\"medalSteps\":{\"1\":{\"step\":1,\"laptime\":190856},\"2\":{\"step\":2,\"laptime\":175723},\"3\":{\"step\":3,\"laptime\":161159}}},{\"trackCode\":24,\"medalSteps\":{\"1\":{\"step\":1,\"laptime\":169788},\"2\":{\"step\":2,\"laptime\":153815},\"3\":{\"step\":3,\"laptime\":138408}}},{\"trackCode\":25,\"medalSteps\":{\"1\":{\"step\":1,\"laptime\":146661},\"2\":{\"step\":2,\"laptime\":128204},\"3\":{\"step\":3,\"laptime\":114264}}},{\"trackCode\":26,\"medalSteps\":{\"1\":{\"step\":1,\"laptime\":184869},\"2\":{\"step\":2,\"laptime\":173597},\"3\":{\"step\":3,\"laptime\":165618}}},{\"trackCode\":27,\"medalSteps\":{\"1\":{\"step\":1,\"laptime\":178772},\"2\":{\"step\":2,\"laptime\":168970},\"3\":{\"step\":3,\"laptime\":155111}}},{\"trackCode\":29,\"medalSteps\":{\"1\":{\"step\":1,\"laptime\":193688},\"2\":{\"step\":2,\"laptime\":173712},\"3\":{\"step\":3,\"laptime\":156579}}},{\"trackCode\":30,\"medalSteps\":{\"1\":{\"step\":1,\"laptime\":150525},\"2\":{\"step\":2,\"laptime\":139300},\"3\":{\"step\":3,\"laptime\":131376}}},{\"trackCode\":31,\"medalSteps\":{\"1\":{\"step\":1,\"laptime\":135744},\"2\":{\"step\":2,\"laptime\":115561},\"3\":{\"step\":3,\"laptime\":103302}}},{\"trackCode\":32,\"medalSteps\":{\"1\":{\"step\":1,\"laptime\":207532},\"2\":{\"step\":2,\"laptime\":186437},\"3\":{\"step\":3,\"laptime\":170155}}},{\"trackCode\":33,\"medalSteps\":{\"1\":{\"step\":1,\"laptime\":218694},\"2\":{\"step\":2,\"laptime\":192556},\"3\":{\"step\":3,\"laptime\":173372}}},{\"trackCode\":36,\"medalSteps\":{\"1\":{\"step\":1,\"laptime\":257682},\"2\":{\"step\":2,\"laptime\":224140},\"3\":{\"step\":3,\"laptime\":199322}}},{\"trackCode\":37,\"medalSteps\":{\"1\":{\"step\":1,\"laptime\":165674},\"2\":{\"step\":2,\"laptime\":140763},\"3\":{\"step\":3,\"laptime\":122550}}},{\"trackCode\":38,\"medalSteps\":{\"1\":{\"step\":1,\"laptime\":163789},\"2\":{\"step\":2,\"laptime\":148252},\"3\":{\"step\":3,\"laptime\":135369}}},{\"trackCode\":39,\"medalSteps\":{\"1\":{\"step\":1,\"laptime\":180593},\"2\":{\"step\":2,\"laptime\":155964},\"3\":{\"step\":3,\"laptime\":127386}}}]}";
				
		System.out.println("### [Time Trial] Season medals data request from player ID " + forcePlayerId + ".");
	    return json;
	}
	
	/**
	 * Time Trial current season records (Platinum) request
	 */
	@GET
	@Path("timetrial/seasons/current/players/platinum/{playerId}")
	@Produces(MediaType.APPLICATION_JSON)
	public String apiTTSeasonsCurrentMedals(@PathParam(value = "playerId") String playerId) {
		JsonObject rootJson = new JsonObject();
		JsonArray platinumTrackCodesArray = new JsonArray();
		rootJson.add("platinumTrackCodes", platinumTrackCodesArray);
		
		System.out.println("### [Time Trial] Platinum track records request from player ID " + playerId + ".");
	    return rootJson.toString();
	}
	
	/**
	 * Get player results on current Time Trial season request
	 */
	@GET
	@Path("timetrial/seasons/current/players/{playerId}")
	@Produces(MediaType.APPLICATION_JSON)
	public String apiTTSeasonsCurrentResults(@PathParam(value = "playerId") String playerId) {
		// TODO Lazy
		String json = "{\"seasonId\":\"610c0affb811464e54ffb529\",\"trackRecords\":{\"30\":{\"trackCode\":30,\"medalStep\":3,\"bestLaptime\":117199},\"33\":{\"trackCode\":33,\"medalStep\":2,\"bestLaptime\":185196},\"36\":{\"trackCode\":36,\"medalStep\":1,\"bestLaptime\":244396},\"37\":{\"trackCode\":37,\"medalStep\":2,\"bestLaptime\":138559},\"39\":{\"trackCode\":39,\"medalStep\":2,\"bestLaptime\":155343}}}";
		
		System.out.println("### [Time Trial] Season player results request from player ID " + playerId + ".");
	    return json;
	}
	
	/**
	 * Time Trial track details request
	 */
	@GET
	@Path("timetrial/seasons/current/trackcode/{trackId}/details")
	@Produces(MediaType.APPLICATION_JSON)
	public String apiTTGetTrackDetails(@PathParam(value = "trackId") String trackId) {
		// TODO Lazy
		String json = "{\"topRankPlayersInfo\":[{\"rank\":1,\"pid\":\"33\",\"gid\":\"0\",\"bestLaptime\":64515},{\"rank\":2,\"pid\":\"33\",\"gid\":\"0\",\"bestLaptime\":64729},{\"rank\":3,\"pid\":\"33\",\"gid\":\"0\",\"bestLaptime\":64746},{\"rank\":4,\"pid\":\"33\",\"gid\":\"0\",\"bestLaptime\":65166},{\"rank\":5,\"pid\":\"33\",\"gid\":\"0\",\"bestLaptime\":65187},{\"rank\":6,\"pid\":\"33\",\"gid\":\"0\",\"bestLaptime\":65669},{\"rank\":7,\"pid\":\"33\",\"gid\":\"0\",\"bestLaptime\":65674},{\"rank\":8,\"pid\":\"33\",\"gid\":\"0\",\"bestLaptime\":65748},{\"rank\":9,\"pid\":\"33\",\"gid\":\"0\",\"bestLaptime\":65789},{\"rank\":10,\"pid\":\"33\",\"gid\":\"0\",\"bestLaptime\":66043}],\"aboveRankPlayersInfo\":[],\"totalPlayerCount\":18091}";
		
		System.out.println("### [Time Trial] Track details request from player ID " + forcePlayerId + ", track code: " + trackId + ".");
	    return json;
	}
	
	/**
	 * Time Trial track records request
	 */
	@GET
	@Path("timetrial/seasons/current/trackcode/{trackId}/records")
	@Produces(MediaType.APPLICATION_JSON)
	public String apiTTGetTrackRecords(@PathParam(value = "trackId") String trackId) {
		// TODO Lazy
		String json = "{\"topRankRecords\":[{\"rank\":1,\"pid\":\"33\",\"gid\":\"0\",\"bestLaptime\":64515},{\"rank\":2,\"pid\":\"33\",\"gid\":\"0\",\"bestLaptime\":64729},{\"rank\":3,\"pid\":\"33\",\"gid\":\"0\",\"bestLaptime\":64746}],\"recommendRecords\":[{\"rank\":1,\"pid\":\"33\",\"gid\":\"0\",\"bestLaptime\":139047},{\"rank\":1,\"pid\":\"33\",\"gid\":\"0\",\"bestLaptime\":130102},{\"rank\":1,\"pid\":\"33\",\"gid\":\"0\",\"bestLaptime\":121977}]}";
		
		System.out.println("### [Time Trial] Track records request from player ID " + forcePlayerId + ", track code: " + trackId + ".");
	    return json;
	}
	
	/**
	 * Time Trial match start request. Requires Socket-IO request with detailed race data to be started
	 */
	@POST
	@Path("timetrial/{playerId}/trackcode/{trackId}/match/@start")
	@Produces(MediaType.APPLICATION_JSON)
	public String apiTTMatchStart(@PathParam(value = "playerId") String playerId, @PathParam(value = "trackId") String trackId) {		
		presence.setPlayerActivity("timetrial", trackId);
		
		JsonObject rootJson = new JsonObject();
		rootJson.addProperty("matchId", 1);
		
		System.out.println("### [Time Trial] Start match request from player ID " + playerId + ", track code: " + trackId + ".");
	    return rootJson.toString();
	}
    
}