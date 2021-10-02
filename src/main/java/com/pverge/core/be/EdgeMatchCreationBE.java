package com.pverge.core.be;

import com.pverge.core.socket.dataobjects.SIOMatchObjects.NumberPlate;

/**
 * Edge - Various methods to fill & prepare Match events data
 * @author Hypernucle
 */
public class EdgeMatchCreationBE {
	
	/**
	 * Create generic number plate
	 * @return Player data
	 */
	public NumberPlate getDefaultPlate(String playerId) {
		NumberPlate plate = new NumberPlate();
		plate.setPid(playerId);
		plate.setPrefix("PJ");
		plate.setPlateNumber("VERGE");
		plate.setTemplateCode(0);
		plate.setBackground(1);
		plate.setFontColor("#ffffff");
		
		return plate;
	}
}
