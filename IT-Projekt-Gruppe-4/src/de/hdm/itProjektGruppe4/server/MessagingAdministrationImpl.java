package de.hdm.itProjektGruppe4.server;

import java.sql.Date;
import java.util.ArrayList;






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
	public Nutzer createNutzer (String vorname, String nachname, String email, String nickname) 
			throws IllegalArgumentException {
		Nutzer n = new Nutzer();
		n.setVorname(vorname);
		n.setNachname(nachname);
		n.setEmail(email);
		n.setNickname(nickname);
		return this.nutzerMapper.insert(n);

	}
	
	/**
	   * Löschen eines Nutzers. Der Nutzer wird in der Datenbank gelöscht.
	      */	

	public void delete (Nutzer nutzer) throws IllegalArgumentException{
		/*
		 * zugehörende nachrichten werden auch gelöscht
		 
		ArrayList <Nachricht> nachrichten = this.getAlleNachrichtbyNutzer(nutzer);
		
		/*
		 * Die Verbindung zum Abonnement wird aufgelöst. 
		
		ArrayList <Nutzerabonnement> nutzerabo = this.findAbonnementByNutzer(nutzer);
		if (nutzerabo!=null){
			for (Nutzerabonnement nabo : nutzerabo){
				this.delete(nabo);
			}
			
			/*
			 * Die Nachrichten die eine Verbindung zum Nutzer haben werden gelöscht.
			 
			
		if (nachrichten != null){
			for (Nachricht n : nachrichten){
				this.delete(n);
			}
		} 
		this.nutzerMapper.delete(nutzer);
		
		} */
		
	}
	
	/**
	 * Auslesen aller Nutzer Objekte
	 */
		
	public ArrayList<Nutzer> findAllNutzer() throws IllegalArgumentException{
		return this.nutzerMapper.findAllNutzer();
	}
	
	/**
	 * Auslesen eines Nutzers anhand seines Nachnames.
	 */

	public Nutzer getNutzerByNachname(String nachname)throws IllegalArgumentException{
		return this.nutzerMapper.findNutzerByNachname(nachname);
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
	
	/**
	 * Anlegen einer neuen Nachricht. Die Nachricht wird in der Datenbank gespeichert.
	 */
	public Nachricht createNachricht (String text) 
			throws IllegalArgumentException{
		Nachricht na = new Nachricht ();
		na.setText(text);	
		return this.nachrichtMapper.insert(na);
	}

	
	
	/**
	   * Auslesen aller Nachrichten aus der Datenbank.
	      */
	public ArrayList<Nachricht> getAllNachrichten() 
			throws IllegalArgumentException{
		return this.nachrichtMapper.findAllNachrichten();
	}
	
	/**
	   * Auslesen Nachrichten anhand der Id.
	      */
	public Nachricht getNachrichtbyId(int id)
			throws IllegalArgumentException{
		return this.nachrichtMapper.findNachrichtById(id);
	}

	/** 
	 * Auslesen von Nachrichten eines Nutzers.
	 */
	public ArrayList <Nachricht> getAlleNachrichtbyNutzer(Nutzer nutzer)
			throws IllegalArgumentException{
		return this.nachrichtMapper.alleNachrichtenJeNutzer(nutzer);
	}
	
	/**
	 * Auslesen von Nachrichten in einer Unterhaltung
	 */
	public ArrayList <Nachricht> findNachrichtenByUnterhaltung(Nachricht nachricht)
			throws IllegalArgumentException{
		return this.nachrichtMapper.findNachrichtenByUnterhaltung(nachricht);
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
	
	/**
	 * Anlegen einer Unterhaltung. Die Unterhaltung wird in der Datenbank gespeichert.
	 */
	public Unterhaltung createUnterhaltung (Nutzer sender, Nutzer receiver) 
			throws IllegalArgumentException{
		Unterhaltung u = new Unterhaltung ();
		u.setSender(sender);
		u.setReceiver(receiver);
		return unterhaltungMapper.insert(u);
		
	}
	
	/**
	   * Auslesen von allen Unterhaltungen aus der Datenbank.
	      */
	public ArrayList<Unterhaltung> getAllUnterhaltungen() 
			throws IllegalArgumentException{
		return this.unterhaltungMapper.findAllUnterhaltungen();
	}
	
	/**
	   * Auslesen von Unterhaltungen anhand der ID.
	      */
	public Unterhaltung getUnterhaltungbyId(int id)
			throws IllegalArgumentException{
		return this.unterhaltungMapper.findUnterhaltungById(id);
	}
	
	/**
	 * Löschen einer Unterhaltung. Hierbei werden die Nutzer,
	 * die in der Unterhaltung teilgenommen haben nicht gelöscht.
	 */
	public Unterhaltung delete(Unterhaltung u){
		/*
		 * Zugehörige Nachrichten von der Unterhaltung werden gelöscht
		 
	ArrayList <Nachricht> nachrichten = this.findNachrichtenByUnterhaltung(u);
	
	
	if (nachrichten != null){
		for (Nachricht n: nachrichten){
			this.delete(n);
		}
	}
	return this.unterhaltungMapper.delete(u); */
		return null;
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
	
	public Abonnement createAbonnement(Nutzerabonnement aboNutzer, Hashtagabonnement aboHashtag) 
			throws IllegalArgumentException{
		Abonnement a = new Abonnement();
		//a.setAboNutzer(aboNutzer);
		//a.setAboHashtag(aboHashtag);
		return abonnementMapper.insertAbonnement(a);
		//
		}
	
	
	/**
	   * Auslesen aller Abonnements.
	      */
	public ArrayList<Abonnement> getAllAbonnements() 
			throws IllegalArgumentException{
		return this.abonnementMapper.findAllAbonnements();
	}
	
	/**
	   * Auslesen aller Abonnements anhand deren Id.
	      */
	public Abonnement getAbonnementbyId(int id)
			throws IllegalArgumentException{
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
	/** 
	 * Anlegen eines Hashtags
	 */
	public Hashtag createHashtag (String bezeichnung) 
			throws IllegalArgumentException {
		Hashtag hash = new Hashtag ();
		hash.setBezeichnung(bezeichnung);
		return hashtagMapper.insert(hash);
	}
	
	/**
	 * Speichern eines geänderten Hashtags
	 */
	public void save (Hashtag hashtag) 
			throws IllegalArgumentException{
		hashtagMapper.update(hashtag);
		//
	}
	
	/**
	 * LÖschen eines Hashtags
	 */
	public void delete (Hashtag hashtag)
			throws IllegalArgumentException{
		/*
		 * zugehörende Hashtagabo wird nicht gelöscht, 
		 * da der Nutzer der das gelöschte Hashtag wieder hinzufügen muss
		 */
		hashtagMapper.delete(hashtag);
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
	
	/*
	 * Anlegen eines Nutzerabonnements
	 */
	public Nutzerabonnement createNutzerabonnement (Nutzer derBeobachtete, Nutzer follower)
			throws IllegalArgumentException{
	Nutzerabonnement nutzabo = new Nutzerabonnement();
	 //nutzabo.getDerBeobachteteId();
	 //nutzabo.getAboNutzerId();
	return nutzerAboMapper.insert(nutzabo); 
}
	
	/*
	 * Löschen eines Nutzerabonnement
	 */
	public void delete (Nutzerabonnement nutzerAbo)throws IllegalArgumentException{	
		nutzerAboMapper.delete(nutzerAbo);;
		
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
	
	/**
	 * Anlegen eines Hashtagabonnements.
	 */
	public Hashtagabonnement createHashtagAbonnement (Hashtag bezeichnung)throws IllegalArgumentException{
		Hashtagabonnement b = new Hashtagabonnement();
		//b.getAboHashtagId();
		return hashtagAboMapper.insert(b);	
		}


	/**
	 * Löschen eines Hashtagsabonnement.
	 */
	public void delete (Hashtagabonnement hashtagAbo)
			throws IllegalArgumentException{
		hashtagAboMapper.delete(hashtagAbo);

}

	@Override
	public void senden(Nachricht Senden) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void empfangen(Nachricht Empfangen) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	/*
	   * ***************************************************************************
	   * ABSCHNITT, Ende: Methoden für HashtagAbo-Objekte
	   * ***************************************************************************
	   */

}
