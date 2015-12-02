package de.hdm.itProjektGruppe4.shared.bo;
import java.util.Date;
import java.util.ArrayList;

/**
 * 
 * @author Yücel, Nguyen
 *
 */

public class Unterhaltung extends BusinessObject{
	
	private static final long serialVersionUID = 1L;
	
	private ArrayList<Nachricht>refNachricht;
	private Nutzer sender;
	private Nutzer receiver;
	private Date lastEdited;


	public ArrayList<Nachricht> getRefNachricht() {
		return refNachricht;
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

	public Date getLastEdited() {
		return lastEdited;
	}

	public void setLastEdited(Date lastEdited) {
		this.lastEdited = lastEdited;
	}
}
