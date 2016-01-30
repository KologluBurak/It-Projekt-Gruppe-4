package de.hdm.itProjektGruppe4.shared;

import java.sql.Date;
import java.util.ArrayList;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.itProjektGruppe4.shared.bo.*;


/**
 * <p>
 * Synchrone Schnittstelle für eine RPC-fähige Klasse zur Verwaltung der Messaging Funktionen.
 * </p>
 * 
 * @author Thies
 * @author Yücel
 * @author Nguyen
 * @author Raue
 *
 */

@RemoteServiceRelativePath("messagingAdmin")
public interface MessagingAdministration extends RemoteService {

	/*
	 * *************************************************************************
	 * ** ABSCHNITT, Beginn: Initialisierung
	 * ***************************************
	 * ************************************
	 */
	/**
	   * Initialisierung des Objekts. 
	   * @throws Exception
	   */
	public void init() throws IllegalArgumentException;

	/*
	 * *************************************************************************
	 * ** ABSCHNITT, Ende: Initialisierung
	 * *****************************************
	 * **********************************
	 */

	/*
	 * *************************************************************************
	 * ** ABSCHNITT, Beginn: Methoden für Nutzer-Objekte
	 * ***************************
	 * ************************************************
	 */
	
	/**
	 * Einen Nutzer anlegen.
	 * 
	 * @param vorname
	 * @param nachname
	 * @param email
	 * @param nickname
	 * @return Nutzer Objekt
	 * @throws Exception
	 */
	public Nutzer createNutzer(String vorname, String nachname, String email, String nickname)
			throws Exception;
	/**
	 * Löschen eines Nutzers 
	 * @param nutzer
	 * @throws Exception
	 */
	public void delete(Nutzer nutzer) throws Exception;

	/**
	 * Nutzer anahand der Id auslesen.
	 * @param id
	 * @return Nutzer
	 * @throws Exception
	 */
	public Nutzer getNutzerById(int id) throws Exception;
	
	/**
	 *  Nutzer anhand des Nicknames auslesen
	 * @param nickname
	 * @return Nutzer
	 * @throws Exception
	 */
	public Nutzer getNutzerByNickname(String nickname) throws Exception;

	/**
	 * Alle Nutzer auslesen
	 *
	 * @return Nutzer
	 * @throws Exception
	 */
	public ArrayList<Nutzer> getAllNutzer() throws Exception;

	
	public Nutzer update(Nutzer nutzer) throws Exception;
	/**
	 * Überpfrüfen ob der Nutzer schon existiert.
	 * @param email
	 * @return
	 * @throws Exception
	 */
	public Boolean userExist(String email) throws Exception;
	/*
	 * *************************************************************************
	 * ** ABSCHNITT, Beginn: Methoden für Nachricht-Objekte
	 * ************************
	 * ***************************************************
	 */
	
	/**
	 * Eine Nachricht wird erstellt.
	 * 
	 * @param text
	 * @param nickname
	 * @param empf
	 * @param unterhaltung
	 * @return Nachricht
	 * @throws Exception
	 */
	public Nachricht createNachricht(String text, String nickname, String empf, Unterhaltung unterhaltung)
			throws Exception;

	/**
	 * Alle Nachrichten eines Nutzers ausgeben.
	 * 
	 * @param nutzer
	 * @return ArrayList<Nachricht>
	 * @throws Exception
	 */
	public ArrayList<Nachricht> getAlleNachrichtenJeNutzer(Nutzer nutzer) throws Exception;

	/**
	 * Alle Nachrichten eines Zeitraums ausgeben.
	 * 
	 * @param von
	 * @param bis
	 * @return ArrayList<Nachricht>
	 * @throws Exception
	 */
	public ArrayList<Nachricht> getAlleNachrichtenJeZeitraum(String von, String bis) throws Exception;

	/**
	 * Löschen eineer Nachricht.
	 * 
	 * @param nachricht
	 * @throws Exception
	 */
	public void delete(Nachricht nachricht) throws Exception;

	/**
	 * Alle Nachrichten ausgeben
	 * 
	 * @return ArrayList <Nachricht>
	 * @throws Exception
	 */
	public ArrayList<Nachricht> getAllNachrichten() throws Exception;

	/**
	 * Nachricht anhand einer ID auslesen
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Nachricht getNachrichtById(int id) throws Exception;
	
	/**
	 * Nachrichten einer Unterhaltung auslesen.
	 * 
	 * @param unterhaltung
	 * @return ArrayList<Nachrichten>
	 * @throws Exception
	 */
	public ArrayList<Nachricht> getNachrichtenByUnterhaltung(Unterhaltung unterhaltung) throws Exception;

	/*
	 * *************************************************************************
	 * ** ABSCHNITT, Ende: Methoden für Nachricht-Objekte
	 * **************************
	 * *************************************************
	 */

	/*
	 * *************************************************************************
	 * ** ABSCHNITT, Beginn: Methoden für Unterhaltung-Objekte
	 * *********************
	 * ******************************************************
	 */
	/**
	 * Eine Unterhaltung erstellen.
	 * 
	 * @param datum
	 * @return Unterhaltung
	 * @throws Exception
	 */
	public Unterhaltung createUnterhaltung(Date datum) throws Exception;
	
	/**
	 * Die neueste Unterhltung auslesen.
	 * @return Unterhaltung
	 * @throws Exception
	 */
	public Unterhaltung getMaxID() throws Exception;

	// public ArrayList<Unterhaltung> getAllUnterhaltungen() throws
	// Exception;

	public void delete(Unterhaltung unterhaltung) throws Exception;

	/*
	 * *************************************************************************
	 * ** ABSCHNITT, Ende: Methoden für Unterhaltung-Objekte
	 * ***********************
	 * ****************************************************
	 */

	/*
	 * *************************************************************************
	 * ** ABSCHNITT, Beginn: Methoden für Abonnement-Objekte
	 * ***********************
	 * ****************************************************
	 */
	/**
	 * Abo erstellen
	 * 
	 * @param id
	 * @param erstellungsZeitpunkt
	 * @return Abonnement
	 * @throws Exception
	 */
	public Abonnement createAbonnement(int id, String erstellungsZeitpunkt) throws Exception;

	/**
	 * Alle Abos auslesen
	 * 
	 * @return ArrayList<Abonnement>
	 * @throws Exception
	 */
	public ArrayList<Abonnement> getAllAbonnements() throws Exception;

	/*
	 * *************************************************************************
	 * ** ABSCHNITT, Ende: Methoden für Abonnement-Objekte
	 * *************************
	 * **************************************************
	 */

	/*
	 * *************************************************************************
	 * ** ABSCHNITT, Beginn: Methoden für Hashtag-Objekte
	 * **************************
	 * *************************************************
	 */

	/**
	 * Alle Hashtags auslesen.
	 * 
	 * @return ArrayList <Hashtag>
	 * @throws Exception
	 */
	public ArrayList<Hashtag> getAllHashtags() throws Exception;

	/**
	 * Hashtag erstellen
	 * 
	 * @param bezeichnung
	 * @return Hashtag
	 * @throws Exception
	 */
	public Hashtag createHashtag(String bezeichnung) throws Exception;

	/**
	 * Hashtag anhand der Bezeichnung auslesen
	 * 
	 * @param bez
	 * @return Hashtag
	 * @throws Exception
	 */
	public Hashtag getHashtagByHashtag(String bez) throws Exception;

	/**
	 *  Löschen eines Hashtags.
	 *  
	 * @param hashtag
	 * @throws Exception
	 */
	public void delete(Hashtag hashtag) throws Exception;

	/*
	 * *************************************************************************
	 * ** ABSCHNITT, Ende: Methoden für Hashtag-Objekte
	 * ****************************
	 * ***********************************************
	 */

	/*
	 * *************************************************************************
	 * ** ABSCHNITT, Beginn: Methoden für NutzerAbo-Objekte
	 * ************************
	 * ***************************************************
	 */

	/**
	 * Nutzerabo erstellen.
	 * 
	 * @param derBeobachtete
	 * @param follower
	 * @return Nutzerabonnement
	 * @throws Exception
	 */
	public Nutzerabonnement createNutzerabonnement(Nutzer derBeobachtete, Nutzer follower)
			throws Exception;

	/**
	 * Alle Nutzerabonnements auslesen.
	 *  
	 * @param userID
	 * @return ArrayList<Nutzerabonnement>
	 * @throws Exception
	 */
	public ArrayList<Nutzerabonnement> getAllNutzerabonnements(String userID) throws Exception;
	
	/**
	 * Auslesen aller Nutzer die, die Rolle des Beobachtenen haben.
	 * @param userID
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Nutzerabonnement> getAllNutzerabonnementsByBeobachteteID(String userID) throws Exception;

	/**
	 * Nutzerabonnement anhand der ID auslesen.
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Nutzerabonnement getNutzerabonnementById(int id) throws Exception;

	/**
	 * Nutzerabonnement löschen.
	 * @param nutzerabonnement
	 * @throws Exception
	 */
	public void delete(Nutzerabonnement nutzerabonnement) throws Exception;

	/**
	 * Nutzerabonnement anhand eines Nutzers auslesen.
	 * @param nutzer
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Nutzerabonnement> getNutzerAbonnementByNutzer(Nutzer nutzer) throws Exception;

	/**
	 * Auslesen aller Nutzer die, die Rolle des Followers haben.
	 * @param id
	 * @return ArrayList<Nutzerabonnement>
	 * @throws Exception
	 */
	public ArrayList<Nutzerabonnement> getNutzerabonnementByFollowerId(int id) throws Exception;

	public ArrayList<Nutzerabonnement> getNutzerabonnementByDerBeobachteteId(int id) throws Exception;

	public ArrayList<Nutzerabonnement> getNutzerabonnementByAbonnementId(int id) throws Exception;
	/*
	 * *************************************************************************
	 * ** ABSCHNITT, Ende: Methoden für NutzerAbo-Objekte
	 * **************************
	 * *************************************************
	 */

	/*
	 * *************************************************************************
	 * ** ABSCHNITT, Beginn: Methoden für HashtagAbo-Objekte
	 * ***********************
	 * ****************************************************
	 */
	
	/**
	 * Erstellen eines Hastagabonnement
	 * @param bezeichnung
	 * @return
	 * @throws Exception
	 */
	public Hashtagabonnement createHashtagAbonnement(Hashtagabonnement bezeichnung) throws Exception;

	/**
	 * Löschen eines Hashtagsabonnements
	 * @param hashtagAbo
	 * @throws Exception
	 */
	public void delete(Hashtagabonnement hashtagAbo) throws Exception;

	/**
	 * Alle Hashtagsabonnements auslesen
	 * @param userID
	 * @return ArrayList<Hashtagabonnement>
	 * @throws Exception
	 */
	public ArrayList<Hashtagabonnement> getAllHashtagabonnements(String userID) throws Exception;

	/**
	 * Hashtagabonnement anhand der ID auslesen.
	 * 
	 * @param id
	 * @return ArrayList<Hashtagabonnement>
	 * @throws Exception
	 */
	public Hashtagabonnement getHashtagAbonnementById(int id) throws Exception;

	/**
	 * Hashtagabonnement anhand der NutzerId auslesen.
	 * 
	 * @param nutzer
	 * @return ArrayList<Hashtagabonnement>
	 * @throws Exception
	 */
	public ArrayList<Hashtagabonnement> getHashtagabonnementByNutzerId(int nutzer) throws Exception;

	/**
	 * Hashtagabonnement anhand der HashtagID auslesen.
	 * 
	 * @param text
	 * @return ArrayList<Hashtagabonnement>
	 * @throws Exception
	 */
	public ArrayList<Hashtagabonnement> getHashtagAbonnementByHashtagId(String text) throws Exception;

	/**
	 * Hashtagabonnement anhand der AbonnementID auslesen.
	 * 
	 * @param id
	 * @return Hashtagabonnement
	 * @throws Exception
	 */
	public Hashtagabonnement getHashtagAbonnementByAbonnementId(int id) throws Exception;

	/*
	 * *************************************************************************
	 * ** ABSCHNITT, Ende: Methoden für HashtagAbo-Objekte
	 * *************************
	 * **************************************************
	 */

	/*
	 * *************************************************************************
	 * ** ABSCHNITT, Beginn: Methoden für Markierungsliste-Objekte
	 * *****************
	 * **********************************************************
	 */
	/**
	 * Zwischentabelle, in der in einer Nachricht mehrere Hashtags verwenden werden kann.
	 * @param text
	 * @param hashtag
	 * @return Markierungsliste
	 * @throws Exception
	 */
	public Markierungsliste createMarkierungsliste(Nachricht text, Hashtag hashtag) throws Exception;

	/*
	 * *************************************************************************
	 * ** ABSCHNITT, Ende: Methoden für Markierungsliste-Objekte
	 * *******************
	 * ********************************************************
	 */

	/*
	 * *************************************************************************
	 * ** ABSCHNITT, Beginn: Methoden für Unterhaltungssliste-Objekte
	 * **************
	 * *************************************************************
	 */

	public Unterhaltungsliste createUnterhaltungsliste(Unterhaltung u, String sender, String empf)
			throws Exception;

	public Unterhaltungsliste getByAbsender(Nutzer absenderNickname) throws Exception;
	
	public ArrayList<Nutzer> getAlleEmpfaengerByAbsender(Nutzer absender) throws Exception;
	
	public Unterhaltungsliste getByEmpfaenger(Nutzer empfaengerNickname) throws Exception;

	public Unterhaltungsliste getByUnterhaltung(Unterhaltung unterhaltung) throws Exception;
	
	public Unterhaltungsliste getUnterhaltung(String absender, String empfaenger) throws Exception;
	
	public void deleteUnterhaltungsliste (String absender, String empfaenger) throws Exception;
	/*
	 * *************************************************************************
	 * ** ABSCHNITT, Ende: Methoden für Unterhaltungssliste-Objekte
	 * ****************
	 * ***********************************************************
	 */
	
	public Nutzerabonnement findAboByNutzerID(int id, int id2) throws Exception;
	
	public ArrayList<Nachricht> getNachrichtByNickname(String nickname ) throws Exception;
	
	public ArrayList<Nutzer> getNutzerByHashtagAbo(String text) throws Exception;
	public Hashtagabonnement getHashtagabonnementByNutzerIdHashtagID(int nutzer, int hashtag) throws Exception;
	public ArrayList<Nutzerabonnement> getAllNutzerabonnementsByBeobachteteMail(String userMail) throws Exception;

}
