package de.hdm.itProjektGruppe4.shared;

import java.sql.Date;
import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.itProjektGruppe4.shared.bo.Abonnement;
import de.hdm.itProjektGruppe4.shared.bo.Hashtag;
import de.hdm.itProjektGruppe4.shared.bo.Hashtagabonnement;
import de.hdm.itProjektGruppe4.shared.bo.Nachricht;
import de.hdm.itProjektGruppe4.shared.bo.Nutzer;
import de.hdm.itProjektGruppe4.shared.bo.Nutzerabonnement;
import de.hdm.itProjektGruppe4.shared.bo.Unterhaltung;

/**
 * Das asynchrone Gegenstück des Interface {@link MessagingAdministration}. Es wird
 * semiautomatisch durch das Google Plugin erstellt und gepflegt. Daher erfolgt
 * hier keine weitere Dokumentation. Für weitere Informationen siehe das
 * synchrone Interface {@link MessagingAdministration}.
 * 
 * @author Thies
 * @author Yücel, Nguyen, Raue
 */

public interface MessagingAdministrationAsync {

	/*
	   * ***************************************************************************
	   * ABSCHNITT, Beginn: Initialisierung
	   * ***************************************************************************
	   */
	void init(AsyncCallback<Void> callback);
	
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
	void saveNutzer(Nutzer nutzer, AsyncCallback<Void> callback);

	void createNutzer(String vorname, String nachname, String passwort,
			String googleId, AsyncCallback<Nutzer> callback);

	void deleteNutzer(int id, AsyncCallback<Void> callback);
	
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

	void saveNachricht(Nachricht Nachricht, AsyncCallback<Void> callback);

	void deleteNachricht(Nachricht Nachricht, AsyncCallback<Void> callback);

	void setNachrichtEditedBy(Nachricht nachricht, AsyncCallback<Void> callback);

	void setDateOfNachricht(Date DateOfNachricht, AsyncCallback<Void> callback);

	void senden(Nachricht Senden, AsyncCallback<Void> callback);

	void empfangen(Nachricht Empfangen, AsyncCallback<Void> callback);

	void createNachricht(String text, String betreff,
			AsyncCallback<Nachricht> callback);

	void getNachrichtById(int id, AsyncCallback<Nachricht> callback);

	void getAllNachrichten(AsyncCallback<ArrayList<Nachricht>> callback);

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
	void createUnterhaltung(Nutzer sender, Nutzer receiver,
			AsyncCallback<Unterhaltung> callback);
	
	void saveUnterhaltung(Unterhaltung Unterhaltung, AsyncCallback<Void> callback);


	void getAllUnterhaltungen(AsyncCallback<ArrayList<Unterhaltung>> callback);

	void getUnterhaltungbyId(int id, AsyncCallback<Unterhaltung> callback);

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
	void createAbonnement(Nutzerabonnement aboNutzer,
			Hashtagabonnement aboHashtag, AsyncCallback<Abonnement> callback);


	void saveAbonnement(Abonnement abo, AsyncCallback<Void> callback);
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
	void createHashtag(String bezeichnung, AsyncCallback<Hashtag> callback);
	void saveHashtag(Hashtag hashtag, AsyncCallback<Void> callback);

	void deleteHashtag(Hashtag hashtag, AsyncCallback<Void> callback);

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
	void createAboNutzer(int aboNutzerId, int derBeobachteteId,
			AsyncCallback<Nutzerabonnement> callback);

	void saveAboNutzer(Nutzerabonnement nutzerAbo, AsyncCallback<Void> callback);

	void deleteAboNutzer(Nutzerabonnement nutzerAbo,
			AsyncCallback<Void> callback);
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

	//public void createAboHashtag (Hashtagabonnement HashtagAbo)throws IllegalArgumentException;
	void saveAboHashtag(Hashtagabonnement hashtagAbo,
			AsyncCallback<Void> callback);

	void deleteAboHastag(Hashtagabonnement hashtagAbo,
			AsyncCallback<Void> callback);
	/*
	   * ***************************************************************************
	   * ABSCHNITT, Ende: Methoden für HashtagAbo-Objekte
	   * ***************************************************************************
	   */

	
	
}
