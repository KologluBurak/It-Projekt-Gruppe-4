package de.hdm.itProjektGruppe4.shared.bo;

/**
 *@author Yücel
 *@author Oikonomou
 */

public class Nutzerabonnement extends BusinessObject {

	private static final long serialVersionUID = 1L;
	
//	private String nutzername;
//
//	
//	public String getNutzername() {
//		return nutzername;
//	}
//
//	public void setNutzername(String nutzername) {
//		this.nutzername = nutzername;
//	
//	}
	/**
	   * Fremdschlüsselbeziehung zum Nutzer der Abonniert.
	   */
	private int aboNutzerId;
	
	/**
	   * Fremdschlüsselbeziehung zum Nutzer der Beobachtet werden.
	   */
	private int derBeobachteteId;

	/**
	   * Getter und Setter.
	   */
	public int getAboNutzerId() {
		return aboNutzerId;
	}

	public void setAboNutzerId(int aboNutzerId) {
		this.aboNutzerId = aboNutzerId;
	}

	public int getDerBeobachteteId() {
		return derBeobachteteId;
	}

	public void setDerBeobachteteId(int derBeobachteteId) {
		this.derBeobachteteId = derBeobachteteId;
	}
	
	
	
}
