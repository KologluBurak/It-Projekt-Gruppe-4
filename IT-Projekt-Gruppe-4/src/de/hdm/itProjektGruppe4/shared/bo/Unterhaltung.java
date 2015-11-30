package de.hdm.itProjektGruppe4.shared.bo;
import java.sql.Date;
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

	public void setReceiver(String receiver2) {
		// TODO Auto-generated method stub
		
	}

	public void setSender(String sender2) {
		// TODO Auto-generated method stub
		
	}


}
