package de.hdm.itProjektGruppe4.shared.bo;

public class Markierungsliste extends BusinessObject{

	/**
	 * Fremdschlüsselbeziehung zur Klasse Nachricht 
	 */
	private int nachrichtID;
	
	/**
	 * Fremdschlüsselbeziehung zur Klasse Hashtag
	 */
	private int hashtagID;
	
	/**
	 * Ausgabe der NachrichtID
	 * @return nachrichtID
	 */
	public int getNachrichtID() {
		return nachrichtID;
	}
	
	/**
	 * Setzen der NachrichtID
	 * @param nachrichtID
	 */
	public void setNachrichtID(int nachrichtID) {
		this.nachrichtID = nachrichtID;
	}
	
	/**
	 * Ausgabe der HashtagID
	 * @return hashtagID
	 */
	public int getHashtagID() {
		return hashtagID;
	}
	
	/**
	 * Setzen der HashtagID
	 * @param hashtagID
	 */
	public void setHashtagID(int hashtagID) {
		this.hashtagID = hashtagID;
	}
}
