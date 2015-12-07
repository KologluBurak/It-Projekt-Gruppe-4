package de.hdm.itProjektGruppe4.shared.bo;

import java.util.ArrayList;

/**
 * 
 * @author YÃ¼cel, Nguyen, Kologlu
 *
 */

public class Unterhaltung extends BusinessObject{
	
	private static final long serialVersionUID = 1L;
	
	private ArrayList<Nachricht>refNachricht;
	private Nutzer sender;
	private Nutzer receiver;
	
	/**
	 * Fremdschlüsselbeziehung zu den Nachrichten, die in der jeweiligen Nachricht beinhaltet sind
	 */
	private int nachrichtID;
	
	/**
	 * Setter und getter
	 */
	public ArrayList<Nachricht> getRefNachricht() {
		return refNachricht;
	}

	public int getNachrichtID() {
		return nachrichtID;
	}

	public void setNachrichtID(int nachrichtID) {
		this.nachrichtID = nachrichtID;
	}

	public void setRefNachricht(ArrayList<Nachricht> refNachricht) {
		this.refNachricht = refNachricht;
	}

	public Nutzer getSender() {
		return sender;
	}

	public void setSender(Nutzer sender) {
		this.sender = sender;
	}

	public Nutzer getReceiver() {
		return receiver;
	}

	public void setReceiver(Nutzer receiver) {
		this.receiver = receiver;
	}
}
