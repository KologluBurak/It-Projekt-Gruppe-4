package de.hdm.itProjektGruppe4.shared.bo;

/**
 *
 *@author Yücel
 *@author Oikonomou
 */

public class Hashtagabonnement extends Abonnement {
	
	private static final long serialVersionUID = 1L;

	//private String name;
	/**
	   * Fremdschlüsselbeziehung zum abonnierten Hashtag
	   */
	public int aboHashtagId;
	
	
//	//Methodenkörper
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}

	public int getAboHashtagId() {
		return aboHashtagId;
	}

	public void setAboHashtagId(int aboHashtagId) {
		this.aboHashtagId = aboHashtagId;
	}
	
	
	
}
