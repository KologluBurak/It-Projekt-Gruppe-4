package de.hdm.itProjektGruppe4.server;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Vector;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.itProjektGruppe4.server.db.*;
import de.hdm.itProjektGruppe4.shared.*;
import de.hdm.itProjektGruppe4.shared.bo.*;


/**
 * 
 * @author Thies
 * @author Yücel, Nguyen, Raue
 * 
 *
 */

public class MessagingAdministrationImpl extends RemoteServiceServlet implements MessagingAdministration {


	
	private NutzerMapper nutzerMapper = null;
	private AbonnementMapper abonnementMapper= null;
	private NutzerAboMapper nutzerAboMapper=null;
	private HashtagAboMapper hashtagAboMapper=null;
	private NachrichtMapper nachrichtMapper= null;
	private HashtagMapper hashtagMapper= null;
	private UnterhaltungMapper unterhaltungMapper= null;
	
	
	/**
	 * No-Argument   Konstruktor
	 */
	
	public MessagingAdministrationImpl()throws IllegalArgumentException{
		
	}
	
	/*
	   * ***************************************************************************
	   * ABSCHNITT, Beginn: Initialisierung
	   * ***************************************************************************
	   */
	public void init()throws IllegalArgumentException{
		this.nutzerMapper= NutzerMapper.nutzerMapper();
		this.abonnementMapper= AbonnementMapper.abonnementMapper();
		this.nutzerAboMapper= NutzerAboMapper.nutzerAboMapper();
		this.hashtagAboMapper= HashtagAboMapper.hashtagAboMapper();
		this.nachrichtMapper= NachrichtMapper.nachrichtMapper();
		this.hashtagMapper= HashtagMapper.hashtagMapper();
		this.unterhaltungMapper= UnterhaltungMapper.unterhaltungMapper();	
	}
	/*
	   * ***************************************************************************
	   * ABSCHNITT, Ende: Initialisierung
	   * ***************************************************************************
	   */
	
	/*
	   * ***************************************************************************
	   * ABSCHNITT, Beginn: Methoden für Nutzer-Objekte
	   * ***************************************************************************
	   */
	
	  /**
	   * Anlegen eines neuen Nutzers. Der neuer Nutzer wird in die Datenbank gespeichert.
	      */	
	public Nutzer createNutzer (String vorname, String nachname, String passwort, String googleId) 
			throws IllegalArgumentException {
		Nutzer n = new Nutzer();
		n.setVorname(vorname);
		n.setNachname(nachname);
		n.setPasswort(passwort);
		n.setGoogleId(googleId);

		return this.nutzerMapper.insert(n);

	}
	
	public void saveNutzer (Nutzer nutzer)throws IllegalArgumentException{
		this.nutzerMapper.update(nutzer);
	}
	
	public void deleteNutzer (int id) throws IllegalArgumentException{
		nutzerMapper.deleteByKey(id);
	}
	
	/*
	   * ***************************************************************************
	   * ABSCHNITT, Ende: Methoden für Nutzer-Objekte
	   * ***************************************************************************
	   */
	
	/*
	   * ***************************************************************************
	   * ABSCHNITT, Beginn: Methoden für Nachricht-Objekte
	   * ***************************************************************************
	   */
	public Nachricht createNachricht (String text, String betreff) 
			throws IllegalArgumentException{
		Nachricht na = new Nachricht ();
		na.setText(text);
		na.setBetreff(betreff);
		return this.nachrichtMapper.insertNachricht(na);
	}
	
	public void deleteNachricht (Nachricht nachricht) throws IllegalArgumentException{
		nachrichtMapper.deleteNachricht(nachricht);
	}
	
	public void setNachrichtEditedBy (Nachricht nachricht)throws IllegalArgumentException{
		this.nachrichtMapper.updateNachricht(nachricht);
	}
	
	public void saveNachricht (Nachricht nachricht) throws IllegalArgumentException{
		nachrichtMapper.updateNachricht(nachricht);
		
	}
	
	public void senden (Nachricht senden) throws IllegalArgumentException {
		nachrichtMapper.updateNachricht(senden);
	}
	
	public void empfangen (Nachricht empfangen) throws IllegalArgumentException{
		nachrichtMapper.updateNachricht(empfangen);  
		}
	
	public void setDateOfNachricht(Date dateOfNachricht) throws IllegalArgumentException{
		//
	}
	
	/**
	   * Auslesen aller Nachrichten aus der Datenbank.
	      */
	public ArrayList<Nachricht> getAllNachrichten() throws IllegalArgumentException{
		return this.nachrichtMapper.findAllNachrichten();
	}
	
	/**
	   * Auslesen Nachrichten anhand der Id.
	      */
	public Nachricht getNachrichtbyId(int id)throws IllegalArgumentException{
		return this.nachrichtMapper.findNachrichtByKey(id);
	}

	
	//public ArrayList<Nachricht> getNachrichten(Nutzer n, String von, String bis, int sort) throws IllegalArgumentException{
		//return this.nachrichtMapper.alleNachrichtenJeNutzer(n, von, bis, sort);
	//}

	 // Auslesen einer Nachricht anhand einer ID
	public Nachricht getNachrichtById(int id) throws IllegalArgumentException{
		return this.nachrichtMapper.findNachrichtByKey(id);
	}
	
	/*
	   * ***************************************************************************
	   * ABSCHNITT, Ende: Methoden für Nachricht-Objekte
	   * ***************************************************************************
	   */
	
	/*
	   * ***************************************************************************
	   * ABSCHNITT, Beginn: Methoden für Unterhaltung-Objekte
	   * ***************************************************************************
	   */
	
	public Unterhaltung createUnterhaltung (Nutzer sender, Nutzer receiver) 
			throws IllegalArgumentException{
		Unterhaltung u = new Unterhaltung ();
		u.setSender(sender);
		u.setReceiver(receiver);
		return unterhaltungMapper.insert(u);
		
	}
	
	public void saveUnterhaltung (Unterhaltung unterhaltung) throws IllegalArgumentException{
		unterhaltungMapper.update(unterhaltung);
	}
	
	/**
	   * Auslesen von allen Unterhaltungen aus der Datenbank.
	      */
	public ArrayList<Unterhaltung> getAllUnterhaltungen() throws IllegalArgumentException{
		return this.unterhaltungMapper.findAllUnterhaltungen();
	}
	
	/**
	   * Auslesen von Unterhaltungen anhand der ID.
	      */
	public Unterhaltung getUnterhaltungbyId(int id)throws IllegalArgumentException{
		return this.unterhaltungMapper.findUnterhaltungByKey(id);
	}
	
	
	/*
	   * ***************************************************************************
	   * ABSCHNITT, Ende: Methoden für Unterhaltung-Objekte
	   * ***************************************************************************
	   */
	
	/*
	   * ***************************************************************************
	   * ABSCHNITT, Beginn: Methoden für Abonnement-Objekte
	   * ***************************************************************************
	   */
	
	public Abonnement createAbonnement (Nutzerabonnement aboNutzer, Hashtagabonnement aboHashtag) 
			throws IllegalArgumentException{
		Abonnement a = new Abonnement();
		a.setAboNutzer(aboNutzer);
		a.setAboHashtag(aboHashtag);
		return abonnementMapper.insertAbonnement(a);
		//
		}
	
	public void saveAbonnement (Abonnement abo) throws IllegalArgumentException {
		abonnementMapper.updateAbonnement(abo);
		//
	}
	
	/**
	   * Auslesen aller Abonnements.
	      */
	public ArrayList<Abonnement> getAllAbonnements() throws IllegalArgumentException{
		return this.abonnementMapper.findAllAbonnements();
	}
	
	/**
	   * Auslesen aller Abonnements anhand deren Id.
	      */
	public Abonnement getAbonnementbyId(int id)throws IllegalArgumentException{
		return this.abonnementMapper.findAbonnementByKey(id);
	}
	
	/*
	   * ***************************************************************************
	   * ABSCHNITT, Ende: Methoden für Abonnement-Objekte
	   * ***************************************************************************
	   */
	
	/*
	   * ***************************************************************************
	   * ABSCHNITT, Beginn: Methoden für Hashtag-Objekte
	   * ***************************************************************************
	   */
	
	public Hashtag createHashtag (String bezeichnung) throws IllegalArgumentException {
		Hashtag hash = new Hashtag ();
		hash.setBezeichnung(bezeichnung);;
		return hashtagMapper.insertHashtagBezeichnung(hash);
	}
	
	public void saveHashtag (Hashtag hashtag) throws IllegalArgumentException{
		hashtagMapper.updateHashtag(hashtag);
		//
	}
	
	public void deleteHashtag (Hashtag hashtag)throws IllegalArgumentException{
		hashtagMapper.deleteHashtagBezeichnung(hashtag);
	}
	
	/*
	   * ***************************************************************************
	   * ABSCHNITT, Ende: Methoden für Hashtag-Objekte
	   * ***************************************************************************
	   */
	
	/*
	   * ***************************************************************************
	   * ABSCHNITT, Beginn: Methoden für NutzerAbo-Objekte
	   * ***************************************************************************
	   */
	
	public Nutzerabonnement createAboNutzer (int aboNutzerId, int derBeobachteteId)
			throws IllegalArgumentException{
	Nutzerabonnement be = new Nutzerabonnement();
	be.setAboNutzerId(aboNutzerId);
	be.setDerBeobachteteId(derBeobachteteId);
	return nutzerAboMapper.insertNutzerabonnement(be); 
}
	
	public void saveAboNutzer (Nutzerabonnement nutzerAbo)throws IllegalArgumentException{
		nutzerAboMapper.updateNutzerabonnement(nutzerAbo);
		//
	}
	
	public void deleteAboNutzer (Nutzerabonnement nutzerAbo)throws IllegalArgumentException{	
		nutzerAboMapper.deleteNutzerabonnement(nutzerAbo);
		
		//
	}
	
	
	/*
	   * ***************************************************************************
	   * ABSCHNITT, Ende: Methoden für NutzerAbo-Objekte
	   * ***************************************************************************
	   */
	
	/*
	   * ***************************************************************************
	   * ABSCHNITT, Beginn: Methoden für HashtagAbo-Objekte
	   * ***************************************************************************
	   */
	
//	public Hashtagabonnement createAboHashtag (String bezeichnung)throws IllegalArgumentException{
//	Hashtagabonnement b = new Hashtagabonnement();
//	b.setBezeichnung(bezeichnung);
//	return hashtagAboMapper.insert(b);
//	Befehele fehlen noch in der Mapper Klasse
//}

	public void saveAboHashtag (Hashtagabonnement hashtagAbo)throws IllegalArgumentException{
		hashtagAboMapper.updateHashtagabonnement(hashtagAbo);
	//
}	

	public void deleteAboHastag (Hashtagabonnement hashtagAbo)throws IllegalArgumentException{
		hashtagAboMapper.deleteHashtagabonnement(hashtagAbo);

}
	
	/*
	   * ***************************************************************************
	   * ABSCHNITT, Ende: Methoden für HashtagAbo-Objekte
	   * ***************************************************************************
	   */

}
