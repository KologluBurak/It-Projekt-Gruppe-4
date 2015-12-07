package de.hdm.itProjektGruppe4.shared.bo;

/**
 *@author Yücel, Nguyen, Oikonomou, Kologlu
 * 
 */

public class Nachricht extends BusinessObject {
	
	private static final long serialVersionUID = 1L;
	
	private String text;
	private String betreff;
	private int kommentarID=0;
	
	
	public int getKommentarID() {
		return kommentarID;
	}

	public void setKommentarID(int kommentarID) {
		this.kommentarID = kommentarID;
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
