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

	void deleteAbonnement(Abonnement Abo, AsyncCallback<Void> callback);

	void setNachrichtEditedBy(Nachricht NachrichtEditedBy,
			AsyncCallback<Void> callback);

	void saveNachricht(Nachricht Nachricht, AsyncCallback<Void> callback);

	void deleteHashtag(Hashtag Hashtag, AsyncCallback<Void> callback);

	void createHashtag(Hashtag Hashtag, AsyncCallback<Void> callback);

	void createNachricht(Nachricht Nachricht, AsyncCallback<Void> callback);

	void saveAbonnement(Abonnement Abo, AsyncCallback<Void> callback);

	void createNutzer(Nutzer Nutzer, AsyncCallback<Void> callback);

	void senden(Nachricht Senden, AsyncCallback<Void> callback);

	void saveUnterhaltung(Unterhaltung Unterhaltung,
			AsyncCallback<Void> callback);

	void setDateOfNachricht(Date DateOfNachricht, AsyncCallback<Void> callback);

	void deleteNutzer(Nutzer Nutzer, AsyncCallback<Void> callback);

	void createUnterhaltung(Unterhaltung Unterhaltung,
			AsyncCallback<Void> callback);

	void saveHashtag(Hashtag Hashtag, AsyncCallback<Void> callback);

	void empfangen(Nachricht Empfangen, AsyncCallback<Void> callback);

	void createAbonnement(Abonnement Abo, AsyncCallback<Void> callback);

	void deleteNachricht(Nachricht Nachricht, AsyncCallback<Void> callback);

	void createAboNutzer(Nutzerabonnement NutzerAbo,
			AsyncCallback<Void> callback);

	void deleteAboNutzer(Nutzerabonnement NutzerAbo,
			AsyncCallback<Void> callback);

	void saveAboNutzer(Nutzerabonnement NutzerAbo, AsyncCallback<Void> callback);

	void createAboHashtag(Hashtagabonnement HashtagAbo,
			AsyncCallback<Void> callback);

	void deleteAboHastag(Hashtagabonnement HashtagAbo,
			AsyncCallback<Void> callback);

	void saveAboHashtag(Hashtagabonnement HashtagAbo,
			AsyncCallback<Void> callback);

}
