package de.hdm.itProjektGruppe4.shared;

import java.sql.Date;

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
 * @author thies
 * @author Yücel
 */

public interface MessagingAdministrationAsync {
	
	/*
	   * ***************************************************************************
	   * ABSCHNITT, Beginn: Initialisierung
	   * ***************************************************************************
	   */

	void senden(Nachricht Senden, AsyncCallback<Void> callback);

	void empfangen(Nachricht Empfangen, AsyncCallback<Void> callback);

	

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
	
	void createNutzer(Nutzer Nutzer, AsyncCallback<Void> callback);

	void deleteNutzer(Nutzer Nutzer, AsyncCallback<Void> callback);

	
	
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
	
	void createNachricht(Nachricht Nachricht, AsyncCallback<Void> callback);

	void saveNachricht(Nachricht Nachricht, AsyncCallback<Void> callback);
	
	void deleteNachricht(Nachricht Nachricht, AsyncCallback<Void> callback);

	void setDateOfNachricht(Date DateOfNachricht, AsyncCallback<Void> callback);

	void setNachrichtEditedBy(Nachricht NachrichtEditedBy,
			AsyncCallback<Void> callback);

	
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
	
	void createUnterhaltung(Unterhaltung Unterhaltung,
			AsyncCallback<Void> callback);

	void saveUnterhaltung(Unterhaltung Unterhaltung,
			AsyncCallback<Void> callback);

	
	
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
	
	void createAbonnement(Abonnement Abo, AsyncCallback<Void> callback);

	void saveAbonnement(Abonnement Abo, AsyncCallback<Void> callback);
	
	void deleteAbonnement(Abonnement Abo, AsyncCallback<Void> callback);

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
	
	void createHashtag(Hashtag Hashtag, AsyncCallback<Void> callback);

	void saveHashtag(Hashtag Hashtag, AsyncCallback<Void> callback);

	void deleteHashtag(Hashtag Hashtag, AsyncCallback<Void> callback);

		
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
	
	void createAboNutzer(Nutzerabonnement NutzerAbo,
			AsyncCallback<Void> callback);

	void saveAboNutzer(Nutzerabonnement NutzerAbo, AsyncCallback<Void> callback);

	void deleteAboNutzer(Nutzerabonnement NutzerAbo,
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
	
	void createAboHashtag(Hashtagabonnement HashtagAbo,
			AsyncCallback<Void> callback);

	void saveAboHashtag(Hashtagabonnement HashtagAbo,
			AsyncCallback<Void> callback);

	void deleteAboHastag(Hashtagabonnement HashtagAbo,
			AsyncCallback<Void> callback);
	
	/*
	   * ***************************************************************************
	   * ABSCHNITT, Ende: Methoden für HashtagAbo-Objekte
	   * ***************************************************************************
	   */
	
}
