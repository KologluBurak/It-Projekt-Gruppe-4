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
	public Nutzer createNutzer (String vorname, String nachname, String passwort, String googleId) 
			throws IllegalArgumentException {
		Nutzer n = new Nutzer();
		n.setVorname(vorname);
		n.setNachname(nachname);
		n.setPasswort(passwort);
		n.setGoogleId(googleId);
		return this.nutzerMapper.insertNutzer(n);
		
	}
	
	public void saveNutzer (Nutzer nutzer)throws IllegalArgumentException{
		this.nutzerMapper.updateNutzer(nutzer);
	}
	
	public void deleteNutzer (Nutzer nutzer) throws IllegalArgumentException{
		nutzerMapper.deleteNutzer(nutzer);
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
	   * Auslesen aller Nachrichten eines Nutzers.
	      */
	
	//public ArrayList<Nachricht> getNachrichten(Nutzer n, String von, String bis, int sort) throws IllegalArgumentException{
		//return this.nachrichtMapper.alleNachrichtenJeNutzer(n, von, bis, sort);
	//}
	
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
	
	public Unterhaltung createUnterhaltung (String sender, String receiver) 
			throws IllegalArgumentException{
		Unterhaltung u = new Unterhaltung ();
		u.setSender(sender);
		u.setReceiver(receiver);
		return unterhaltungMapper.insertUnterhaltung(u);
		
	}
	
	public void saveUnterhaltung (Unterhaltung unterhaltung) throws IllegalArgumentException{
		unterhaltungMapper.updateUnterhaltung(unterhaltung);
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
	
	//public void deleteAbonnement (Abonnement Abo) throws IllegalArgumentException{
		
		
	
	
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
	
	public void deleteHashtag (Hashtag Hashtag)throws IllegalArgumentException{
		hashtagMapper.deleteHashtagBezeichnung(Hashtag);
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
		nutzerAboMapper.updateNutzerabonnement(NutzerAbo);
		//
	}
	
	public void deleteAboNutzer (Nutzerabonnement NutzerAbo)throws IllegalArgumentException{	
		nutzerAboMapper.deleteNutzerabonnement(NutzerAbo);
		
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
		hashtagAboMapper.updateHashtagabonnement(HashtagAbo);
	//
}	

	public void deleteAboHastag (Hashtagabonnement HashtagAbo)throws IllegalArgumentException{
		hashtagAboMapper.deleteHashtagabonnement(HashtagAbo);

}

	@Override
	public void createNutzer(Nutzer Nutzer) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createNachricht(Nachricht Nachricht)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createUnterhaltung(Unterhaltung Unterhaltung)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createAbonnement(Abonnement Abo)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAbonnement(Abonnement Abo)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createHashtag(Hashtag Hashtag) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createAboNutzer(Nutzerabonnement NutzerAbo)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createAboHashtag(Hashtagabonnement HashtagAbo)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}
	
	/*
	   * ***************************************************************************
	   * ABSCHNITT, Ende: Methoden für HashtagAbo-Objekte
	   * ***************************************************************************
	   */

}
