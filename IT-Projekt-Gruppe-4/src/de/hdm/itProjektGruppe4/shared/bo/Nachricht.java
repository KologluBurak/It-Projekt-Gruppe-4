package de.hdm.itProjektGruppe4.shared.bo;

/**
 *@author Yücel, Nguyen, Oikonomou, Kologlu
 * 
 */

public class Nachricht extends Unterhaltung {
	
	private static final long serialVersionUID = 1L;
	
	private int nachrichtId;
	private String text;
	private String betreff;
	
	public int getNachrichtId() {
		return nachrichtId;
	}
	
	public void setNachrichtId(int nachrichtId) {
		this.nachrichtId = nachrichtId;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public String getBetreff() {
		return betreff;
	}
	
	public void setBetreff(String betreff) {
		this.betreff = betreff;
	}
}
