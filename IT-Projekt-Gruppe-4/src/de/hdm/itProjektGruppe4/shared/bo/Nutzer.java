package de.hdm.itProjektGruppe4.shared.bo;

/**
 * @author Yücel, Nguyen, Oikonomou
 */

public class Nutzer extends BusinessObject {
	
	private static final long serialVersionUID = 1L;
	private String vorname,nachname,passwort,googleId;
	

	public String getVorname() {
		return vorname;
	}
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}
	
	public String getNachname() {
		return nachname;
	}
	public void setNachname(String nachname) {
		this.nachname = nachname;
	}
		
	public String getPasswort() {
		return passwort;
	}
	public void setPasswort(String passwort) {
		this.passwort = passwort;
	
	}
	public String getGoogleId() {
		return googleId;
	}
	public void setGoogleId(String googleId) {
		this.googleId = googleId;
	}

	
}
