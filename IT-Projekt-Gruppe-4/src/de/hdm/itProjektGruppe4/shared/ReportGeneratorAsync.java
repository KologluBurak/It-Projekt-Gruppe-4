package de.hdm.itProjektGruppe4.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.itProjektGruppe4.shared.bo.*;
import de.hdm.itProjektGruppe4.shared.report.*;

/**
 * Das asynchrone Gegenstück des Interface {@link ReportGenerator}. Es wird
 * semiautomatisch durch das Google Plugin erstellt und gepflegt. Daher erfolgt
 * hier keine weitere Dokumentation. Für weitere Informationen siehe das
 * synchrone Interface {@link ReportGenerator}.
 * 
 * @author thies
 * @author Oikonomou
 */

public interface ReportGeneratorAsync {

	void erstelleAlleAbonnementsReport(AsyncCallback<AlleAbonnementsReport> callback);

	void erstelleAlleNachrichtenReport(AsyncCallback<AlleNachrichtenReport> callback);

	void erstelleZeitraumspezifischeNachrichtenReport(Nachricht nachricht, String von, String bis,
			AsyncCallback<ZeitraumspezifischeNachrichtenReport> callback);


	void erstelleNutzerspezifischeNachrichtenReport(Nachricht nachricht, Nutzer nutzer,
			AsyncCallback<NutzerspezifischeNachrichtenReport> callback);


	void erstelleNutzerspezifischeAbonnementsReport(Nutzerabonnement nutzerabonnement,
			AsyncCallback<NutzerspezifischeAbonnementsReport> callback);

	
	void erstelleHashtagspezifischeAbonnementsReport(Hashtagabonnement hashtagabonnement,
			AsyncCallback<HashtagspezifischeAbonnementsReport> callback);
	

	void init(AsyncCallback<Void> callback);

}