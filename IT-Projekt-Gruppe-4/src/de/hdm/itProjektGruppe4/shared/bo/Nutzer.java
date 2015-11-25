package de.hdm.itProjektGruppe4.shared.bo;

public class Nutzer extends BusinessObject {
	
	private static final long serialVersionUID = 1L;
	private String vorname;
	private String googleId;
	
	public String getVorname() {
		return vorname;
	}
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}
	public String getGoogleId() {
		return googleId;
	}
	public void setGoogleId(String googleId) {
		this.googleId = googleId;
	}
	
	
	
}
