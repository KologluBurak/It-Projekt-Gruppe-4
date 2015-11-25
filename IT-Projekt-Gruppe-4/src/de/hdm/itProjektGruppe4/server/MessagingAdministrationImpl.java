package de.hdm.itProjektGruppe4.server;

import java.sql.Date;
import java.util.Vector;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.itProjektGruppe4.server.db.*;
import de.hdm.itProjektGruppe4.shared.*;
import de.hdm.itProjektGruppe4.shared.bo.*;

/**
 * 
 * @author Yücel, Nguyen
 * 
 *
 */

public class MessagingAdministrationImpl extends RemoteServiceServlet 
	implements MessagingAdministration {

 
	
	private NutzerMapper nutzerMapper = null;
	private AbonnementMapper abonnementMapper= null;
	private NutzerAboMapper nutzerAboMapper=null;
	private HashtagAboMapper hashtagAboMapper=null;
	private NachrichtMapper nachrichtMapper= null;
	private HashtagMapper hashtagMapper= null;
	private UnterhaltungMapper unterhaltungMapper= null;
	
	
	/**
	 * No-Argument Konstruktor
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
		//this.nutzerAboMapper= NutzerAboMapper.nutzerAboMapper();
		//this.hashtagAboMapper= HastagaboMapeer.hashtagAboMapper;
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
	public Nutzer createNutzer (String vorname, String googleId) throws IllegalArgumentException {
		Nutzer n = new Nutzer();
		n.setVorname(vorname);
		n.setGoogleId(googleId);
		return nutzerMapper.insert(n);
	}
	
	public void saveNutzer (Nutzer nutzer)throws IllegalArgumentException{
		nutzerMapper.update(nutzer);
	}
	
	public void deleteNutzer (Nutzer Nutzer) throws IllegalArgumentException{
		//
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
	public Nachricht createNachricht (String text) 
			throws IllegalArgumentException{
		Nachricht na = new Nachricht ();
		na.setText(text);
		return nachrichtMapper.insert(na);
	}
	
	public void deleteNachricht (Nachricht Nachricht) throws IllegalArgumentException{
		//
	}
	
	public void setNachrichtEditedBy (Nachricht NachrichtEditedBy)throws IllegalArgumentException{
		nachrichtMapper.update(NachrichtEditedBy);
	}
	
	public void saveNachricht (Nachricht Nachricht) throws IllegalArgumentException{
		nachrichtMapper.update(Nachricht);
	}
	
	public void senden (Nachricht Senden) throws IllegalArgumentException {
		//nachrichtMapper.update(Senden);
	}
	
	public void empfangen (Nachricht Empfangen) throws IllegalArgumentException{
		//nachrichtMapper.update(Empfangen);  
		}
	
	public void setDateOfNachricht(Date DateOfNachricht) throws IllegalArgumentException{
		
		//
	}
	
	/**
	   * Auslesen aller Nachrichten aus der Datenbank.
	      */
	public Vector<Nachricht> getAllNachrichten() throws IllegalArgumentException{
		return this.nachrichtMapper.findAll();

	}
	
	/**
	   * Auslesen aller Nachrichten eines Nutzers.
	      */
	
	public Vector<Nachricht> getNachrichten(Nutzer n) throws IllegalArgumentException{
		//return this.nachrichtMapper.findbyOwner(n);
	return null;
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
	
	public Unterhaltung createUnterhaltung (Nachricht refNachricht, String sender, String receiver) 
			throws IllegalArgumentException{
		Unterhaltung u = new Unterhaltung ();
		u.setNachrichten(refNachricht);
		u.setSender(sender);
		u.setReceiver(receiver);
		return unterhaltungMapper.insert(u);
		
		//
	}
	
	public void saveUnterhaltung (Unterhaltung Unterhaltung) throws IllegalArgumentException{
		unterhaltungMapper.update(Unterhaltung);
	}
	
	/**
	   * Auslesen von allen Unterhaltungen aus der Datenbank.
	      */
	public Vector<Unterhaltung> getAllUnterhaltungen() throws IllegalArgumentException{
		return this.unterhaltungMapper.findAll();
	}
	
	/**
	   * Auslesen von Unterhaltungen anhand der ID.
	      */
	public Unterhaltung getUnterhaltungbyId(int id)throws IllegalArgumentException{
		return this.unterhaltungMapper.findByKey(id);
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
	
	public Abonnement createAbonnement (String aboArt) throws IllegalArgumentException{
		Abonnement a = new Abonnement();
		a.setAboArt(aboArt);
		return abonnementMapper.insert(a);
		//
		}
	
	public void saveAbonnement (Abonnement Abo) throws IllegalArgumentException {
		abonnementMapper.update(Abo);
		//
	}
	
	public void deleteAbonnement (Abonnement Abo) throws IllegalArgumentException{
	//	
	}
	
	/**
	   * Auslesen aller Abonnements.
	      */
	public Vector<Abonnement> getAllAbonnements() throws IllegalArgumentException{
		return this.abonnementMapper.findAll();
	}
	
	/**
	   * Auslesen aller Abonnements anhand deren Id.
	      */
	public Abonnement getAbonnementbyId(int id)throws IllegalArgumentException{
		return this.abonnementMapper.findByKey(id);
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
	
	public Hashtag createHashtag (String name) throws IllegalArgumentException {
		Hashtag nam = new Hashtag ();
		nam.setName(name);
		return hashtagMapper.insert(nam);
	}
	
	public void saveHashtag (Hashtag Hashtag) throws IllegalArgumentException{
		hashtagMapper.update(Hashtag);
		//
	}
	
	public void deleteHashtag (Hashtag Hashtag)throws IllegalArgumentException{
		//
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
	
//	public Nutzerabonnement createAboNutzer (String benutzername)throws IllegalArgumentException{
//	Nutzerabonnement be = new Nutzerabonnement();
//	be.setBenutzername(benutzername);
//	return nutzerAboMapper.insert(be); 
//	Befehele fehlen noch in der Mapper Klasse
//}
	
	public void saveAboNutzer (Nutzerabonnement NutzerAbo)throws IllegalArgumentException{
		nutzerAboMapper.update(NutzerAbo);
		//
	}
	
	public void deleteAboNutzer (Nutzerabonnement NutzerAbo)throws IllegalArgumentException{	
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

	public void saveAboHashtag (Hashtagabonnement HashtagAbo)throws IllegalArgumentException{
	//hashtagMapper.update(HashtagAbo);
		
	//
}	

public void deleteAboHastag (Hashtagabonnement HashtagAbo)throws IllegalArgumentException{
	//hashtagMapper.update(HashtagAbo);

}
	
	/*
	   * ***************************************************************************
	   * ABSCHNITT, Ende: Methoden für HashtagAbo-Objekte
	   * ***************************************************************************
	   */

}
