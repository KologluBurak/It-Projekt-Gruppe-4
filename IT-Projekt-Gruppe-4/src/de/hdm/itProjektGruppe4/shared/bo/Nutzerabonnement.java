package de.hdm.itProjektGruppe4.shared.bo;

/**
 *@author Yücel
 *@author Oikonomou
 */

public class Nutzerabonnement extends BusinessObject {

	private static final long serialVersionUID = 1L;
	
	private String nutzername;


	public String getNutzername() {
		return nutzername;
	}

	public void setNutzername(String nutzername) {
		this.nutzername = nutzername;
	
	}
}
