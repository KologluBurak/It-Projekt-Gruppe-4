package de.hdm.itProjektGruppe4.shared;

import java.util.Date;

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


	void erstelleInfosVonAllenNachrichtenReport(String von,
			String bis,AsyncCallback<InfosVonAllenNachrichtenReport> callback);

	void erstelleInfosVonAllenAbonnementsReport(Abonnement aboNutzer, Abonnement aboHashtag,
			AsyncCallback<String> callback);

	void erstelleInfosUeberZeitraumspezifischeNachrichtenReport(Nachricht nachricht, Date anfangszeitpunkt, 
			Date endzeitpunkt, AsyncCallback<InfosUeberZeitraumspezifischeNachrichtenReport> callback);

	void erstelleInfosUeberNutzerspezifischeNachrichtenReport(Nachricht nachricht, Nutzer nutzer,
			AsyncCallback<InfosUeberNutzerspezifischeNachrichtenReport> callback);
	
	void erstelleInfosUeberNutzerspezifischeAbonnementsReport(Abonnement abonnement, Nutzer nutzer,
			AsyncCallback<InfosUeberNutzerspezifischeAbonnementsReport> callback);

	void erstelleInfosUeberHashtagspezifischeAbonnementsReport(Abonnement abonnement, Hashtag hashtag,
			AsyncCallback<InfosUeberHashtagspezifischeAbonnementsReport> callback);

	void init(AsyncCallback<Void> callback);

}
