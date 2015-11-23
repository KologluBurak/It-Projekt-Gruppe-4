package de.hdm.itProjektGruppe4.shared.bo;

public class Nutzer extends BusinessObject {
	
	private static final long serialVersionUID = 1L;
	
	private String vorname;
	private String googlemail;
	public String getVorname() {
		return vorname;
	}
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}
	public String getGooglemail() {
		return googlemail;
	}
	public void setGooglemail(String googlemail) {
		this.googlemail = googlemail;
	}
	
	
}
