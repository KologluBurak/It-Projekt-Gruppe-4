package de.hdm.itProjektGruppe4.shared.bo;

/**
 * 
 * @author Yücel, Nguyen
 *
 */

public class Hashtagabonnement extends Abonnement {
	
	private static final long serialVersionUID = 1L;

	private String bezeichnung;
	
	//Methodenkörper
	public String getBezeichnung() {
		return bezeichnung;
	}

	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}
	
	
}
