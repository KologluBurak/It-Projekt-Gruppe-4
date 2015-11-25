package de.hdm.itProjektGruppe4.shared.bo;

/**
 * 
 * @author Yücel
 *
 */

public class Nutzerabonnement extends Abonnement {

	private static final long serialVersionUID = 1L;
	
	private String benutzername;

	//Methodenköprer
	public String getBenutzername() {
		return benutzername;
	}

	public void setBenutzername(String benutzername) {
		this.benutzername = benutzername;
	}
}
