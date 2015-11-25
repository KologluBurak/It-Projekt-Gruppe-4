package de.hdm.itProjektGruppe4.shared.bo;

/**
 *@author Yücel
 *@author Oikonomou
 */

public class Nachricht extends BusinessObject {
	
	private static final long serialVersionUID = 1L;
	
	private String text;
	private String betreff;
	
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
