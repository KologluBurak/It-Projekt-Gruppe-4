package de.hdm.itProjektGruppe4.shared.bo;

/**
 *@author YÃ¼cel, Nguyen, Oikonomou, Kologlu
 * 
 */

public class Nachricht extends BusinessObject {
	
	private static final long serialVersionUID = 1L;
	
	private String text;
	
	/**
	 * Fremdschlüsselbeziehung
	 */
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
}
