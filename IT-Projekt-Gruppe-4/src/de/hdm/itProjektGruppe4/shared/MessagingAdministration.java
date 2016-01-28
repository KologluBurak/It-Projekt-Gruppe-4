package de.hdm.itProjektGruppe4.shared;

import java.sql.Date;
import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.itProjektGruppe4.shared.bo.*;

/**
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
	public Nutzer createNutzer(String vorname, String nachname, String email, String nickname)
			throws Exception;

	public void delete(Nutzer nutzer) throws Exception;

	public Nutzer getNutzerById(int id) throws Exception;

	public Nutzer getNutzerByNickname(String nickname) throws Exception;

	public ArrayList<Nutzer> getAllNutzer() throws Exception;

	public Nutzer update(Nutzer nutzer) throws Exception;

	public Boolean userExist(String email) throws Exception;
	/*
	 * *************************************************************************
	 * ** ABSCHNITT, Beginn: Methoden für Nachricht-Objekte
	 * ************************
	 * ***************************************************
	 */

	public Nachricht createNachricht(String text, String nickname, String empf, Unterhaltung unterhaltung)
			throws Exception;

	public ArrayList<Nachricht> getAlleNachrichtenJeNutzer(Nutzer nutzer) throws Exception;

	public ArrayList<Nachricht> getAlleNachrichtenJeZeitraum(String von, String bis) throws Exception;

	public void delete(Nachricht nachricht) throws Exception;

	public ArrayList<Nachricht> getAllNachrichten() throws Exception;

	public Nachricht getNachrichtById(int id) throws Exception;

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

	public Unterhaltung createUnterhaltung(Date datum) throws Exception;
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

	public Abonnement createAbonnement(int id, String erstellungsZeitpunkt) throws Exception;

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

	public ArrayList<Hashtag> getAllHashtags() throws Exception;

	public Hashtag createHashtag(String bezeichnung) throws Exception;

	public Hashtag getHashtagByHashtag(String bez) throws Exception;

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

	public Nutzerabonnement createNutzerabonnement(Nutzer derBeobachtete, Nutzer follower)
			throws Exception;

	public ArrayList<Nutzerabonnement> getAllNutzerabonnements(String userID) throws Exception;
	
	public ArrayList<Nutzerabonnement> getAllNutzerabonnementsByBeobachteteID(String userID) throws Exception;

	public Nutzerabonnement getNutzerabonnementById(int id) throws Exception;

	public void delete(Nutzerabonnement nutzerabonnement) throws Exception;

	public ArrayList<Nutzerabonnement> getNutzerAbonnementByNutzer(Nutzer nutzer) throws Exception;

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

	Hashtagabonnement createHashtagAbonnement(Hashtagabonnement bezeichnung) throws Exception;

	public void delete(Hashtagabonnement hashtagAbo) throws Exception;

	public ArrayList<Hashtagabonnement> getAllHashtagabonnements(String userID) throws Exception;

	public Hashtagabonnement getHashtagAbonnementById(int id) throws Exception;

	public ArrayList<Hashtagabonnement> getHashtagabonnementByNutzerId(int nutzer) throws Exception;

	public Hashtagabonnement getHashtagAbonnementByHashtagId(String text) throws Exception;

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

}
