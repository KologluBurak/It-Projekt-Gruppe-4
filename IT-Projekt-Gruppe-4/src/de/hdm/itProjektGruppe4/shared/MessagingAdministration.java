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
	 * ***************************************************************************
	 * ABSCHNITT, Beginn: Initialisierung
	 * ***************************************
	 * ************************************
	 */

	public void init() throws IllegalArgumentException;

	/*
	 * ***************************************************************************
	 * ABSCHNITT, Ende: Initialisierung
	 * *****************************************
	 * **********************************
	 */

	/*
	 * ***************************************************************************
	 * ABSCHNITT, Beginn: Methoden für Nutzer-Objekte
	 * ***************************
	 * ************************************************
	 */
	public Nutzer createNutzer(String vorname, String nachname, String email,
			String nickname) throws IllegalArgumentException;

	public void delete(Nutzer nutzer) throws IllegalArgumentException;

	public Nutzer getNutzerById(int id) throws IllegalArgumentException;

	public Nutzer getNutzerByNickname(String nickname)
			throws IllegalArgumentException;

	public ArrayList<Nutzer> getAllNutzer() throws IllegalArgumentException;

	public Nutzer update(Nutzer nutzer) throws IllegalArgumentException;

	/*
	 * ***************************************************************************
	 * ABSCHNITT, Beginn: Methoden für Nachricht-Objekte
	 * ************************
	 * ***************************************************
	 */

	public Nachricht createNachricht(String text, String nickname,
			Unterhaltung unterhaltung) throws IllegalArgumentException;

	public ArrayList<Nachricht> getAlleNachrichtenJeNutzer(Nutzer nutzer)
			throws IllegalArgumentException;

	public ArrayList<Nachricht> getAlleNachrichtJeZeitraum(String von,
			String bis);

	public void delete(Nachricht nachricht) throws IllegalArgumentException;

	public ArrayList<Nachricht> getAllNachrichten()
			throws IllegalArgumentException;

	public Nachricht getNachrichtByID(int id) throws IllegalArgumentException;

	public ArrayList<Nachricht> getNachrichtenByUnterhaltung(
			Unterhaltung unterhaltung) throws IllegalArgumentException;

	/*
	 * ***************************************************************************
	 * ABSCHNITT, Ende: Methoden für Nachricht-Objekte
	 * **************************
	 * *************************************************
	 */

	/*
	 * ***************************************************************************
	 * ABSCHNITT, Beginn: Methoden für Unterhaltung-Objekte
	 * *********************
	 * ******************************************************
	 */

	public Unterhaltung createUnterhaltung(Date datum)
			throws IllegalArgumentException;

	//public ArrayList<Unterhaltung> getAllUnterhaltungen() throws IllegalArgumentException;

	public void delete(Unterhaltung unterhaltung)
			throws IllegalArgumentException;

	public Unterhaltung update(Unterhaltung unterhaltung)
			throws IllegalArgumentException;

	/*
	 * ***************************************************************************
	 * ABSCHNITT, Ende: Methoden für Unterhaltung-Objekte
	 * ***********************
	 * ****************************************************
	 */

	/*
	 * ***************************************************************************
	 * ABSCHNITT, Beginn: Methoden für Abonnement-Objekte
	 * ***********************
	 * ****************************************************
	 */

	public Abonnement createAbonnement(int id, Date erstellungsZeitpunkt)
			throws IllegalArgumentException;

	public Abonnement getAbonnementById(int id) throws IllegalArgumentException;

	public ArrayList<Abonnement> getAllAbonnements()
			throws IllegalArgumentException;

	public Abonnement updateAbonnement(Abonnement abonnement)
			throws IllegalArgumentException;

	/*
	 * ***************************************************************************
	 * ABSCHNITT, Ende: Methoden für Abonnement-Objekte
	 * *************************
	 * **************************************************
	 */

	/*
	 * ***************************************************************************
	 * ABSCHNITT, Beginn: Methoden für Hashtag-Objekte
	 * **************************
	 * *************************************************
	 */

	public ArrayList<Hashtag> getAllHashtags() throws IllegalArgumentException;

	public Hashtag createHashtag(String bezeichnung)
			throws IllegalArgumentException;

	public Hashtag getHashtagById(int id) throws IllegalArgumentException;

	public void delete(Hashtag hashtag) throws IllegalArgumentException;

	public Hashtag update(Hashtag hashtag) throws IllegalArgumentException;

	/*
	 * ***************************************************************************
	 * ABSCHNITT, Ende: Methoden für Hashtag-Objekte
	 * ****************************
	 * ***********************************************
	 */

	/*
	 * ***************************************************************************
	 * ABSCHNITT, Beginn: Methoden für NutzerAbo-Objekte
	 * ************************
	 * ***************************************************
	 */

	public Nutzerabonnement createNutzerabonnement(Nutzer derBeobachtete,
			Nutzer follower) throws IllegalArgumentException;

	public ArrayList<Nutzerabonnement> getAllNutzerabonnements()
			throws IllegalArgumentException;

	public Nutzerabonnement getNutzerabonnemntById(int id)
			throws IllegalArgumentException;

	public void delete(Nutzerabonnement nutzerabonnement)
			throws IllegalArgumentException;

	public ArrayList<Nutzerabonnement> getNutzerAbonnementByNutzer(Nutzer nutzer)
			throws IllegalArgumentException;

	/*
	 * ***************************************************************************
	 * ABSCHNITT, Ende: Methoden für NutzerAbo-Objekte
	 * **************************
	 * *************************************************
	 */

	/*
	 * ***************************************************************************
	 * ABSCHNITT, Beginn: Methoden für HashtagAbo-Objekte
	 * ***********************
	 * ****************************************************
	 */

	public Hashtagabonnement createHashtagAbonnement(Hashtag bezeichnung)
			throws IllegalArgumentException;

	public void delete(Hashtagabonnement hashtagAbo)
			throws IllegalArgumentException;

	public ArrayList<Hashtagabonnement> getAllHashtagabonnements(int id)
			throws IllegalArgumentException;

	public Hashtagabonnement getHashtagAboById(int id)
			throws IllegalArgumentException;

	public ArrayList<Hashtagabonnement> getHashtagabonnementByNutzer(int id)
			throws IllegalArgumentException;

	/*
	 * ***************************************************************************
	 * ABSCHNITT, Ende: Methoden für HashtagAbo-Objekte
	 * *************************
	 * **************************************************
	 */

	/*
	 * ***************************************************************************
	 * ABSCHNITT, Beginn: Methoden für Markierungsliste-Objekte
	 * *****************
	 * **********************************************************
	 */

	public Markierungsliste createMarkierungsliste(String text, String hashtag)
			throws IllegalArgumentException;

	/*
	 * ***************************************************************************
	 * ABSCHNITT, Ende: Methoden für Markierungsliste-Objekte
	 * *******************
	 * ********************************************************
	 */

	/*
	 * ***************************************************************************
	 * ABSCHNITT, Beginn: Methoden für Unterhaltungssliste-Objekte
	 * **************
	 * *************************************************************
	 */

	public Unterhaltungsliste createUnterhaltungsliste(Unterhaltung u,
			String sender, String empf) throws IllegalArgumentException;

	public Unterhaltungsliste getByAbsender(Nutzer nutzer)
			throws IllegalArgumentException;

	public Unterhaltungsliste getByEmpfaenger(Nutzer nutzer)
			throws IllegalArgumentException;

	public Unterhaltungsliste getByUnterhaltung(Unterhaltung unterhaltung)
			throws IllegalArgumentException;
	/*
	 * ***************************************************************************
	 * ABSCHNITT, Ende: Methoden für Unterhaltungssliste-Objekte
	 * ****************
	 * ***********************************************************
	 */

}
