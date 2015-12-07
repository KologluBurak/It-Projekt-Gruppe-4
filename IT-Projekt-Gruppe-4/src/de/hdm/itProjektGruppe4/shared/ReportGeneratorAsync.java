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


	void erstelleInfosVonAllenNachrichtenReport(Date anfangszeitpunkt,
			Date endzeitpunkt,AsyncCallback<InfosVonAllenNachrichtenReport> callback);

	void erstelleInfosVonAllenAbonnementsReport(Date anfangszeitpunkt,
			Date endzeitpunkt, AsyncCallback<InfosVonAllenAbonnementsReport> callback);

	void erstelleInfosÜberZeitraumspezifischeNachrichtenReport(Nachricht nachricht, Date anfangszeitpunkt, 
			Date endzeitpunkt, AsyncCallback<InfosÜberZeitraumspezifischeNachrichtenReport> callback);

	void erstelleInfosÜberNutzerspezifischeNachrichtenReport(Nachricht nachricht, Nutzer nutzer,
			AsyncCallback<InfosÜberNutzerspezifischeNachrichtenReport> callback);
	
	void erstelleInfosÜberNutzerspezifischeAbonnementsReport(Abonnement abonnement, Nutzer nutzer,
			AsyncCallback<InfosÜberNutzerspezifischeAbonnementsReport> callback);

	void erstelleInfosÜberHashtagspezifischeAbonnementsReport(Abonnement abonnement, Hashtag hashtag,
			AsyncCallback<InfosÜberHashtagspezifischeAbonnementsReport> callback);

	void init(AsyncCallback<Void> callback);

}
