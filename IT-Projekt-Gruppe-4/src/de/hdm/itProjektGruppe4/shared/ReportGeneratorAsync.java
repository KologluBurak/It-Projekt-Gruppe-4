package de.hdm.itProjektGruppe4.shared;

import java.util.Date;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.itProjektGruppe4.shared.bo.Abonnement;
import de.hdm.itProjektGruppe4.shared.bo.Hashtag;
import de.hdm.itProjektGruppe4.shared.bo.Nachricht;
import de.hdm.itProjektGruppe4.shared.bo.Nutzer;
import de.hdm.itProjektGruppe4.shared.report.AlleAbonnementsReport;
import de.hdm.itProjektGruppe4.shared.report.AlleNachrichtenReport;
import de.hdm.itProjektGruppe4.shared.report.HashtagspezifischeAbonnementsReport;
import de.hdm.itProjektGruppe4.shared.report.NutzerspezifischeAbonnementsReport;
import de.hdm.itProjektGruppe4.shared.report.NutzerspezifischeNachrichtenReport;
import de.hdm.itProjektGruppe4.shared.report.ZeitraumspezifischeNachrichtenReport;

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

	void erstelleAlleAbonnementsReport(Abonnement abonnement, Nutzer nutzer, Hashtag hashtag,
			AsyncCallback<AlleAbonnementsReport> callback);

	void erstelleAlleNachrichtenReport(Nutzer nutzer, Nachricht nachricht, Date von, Date bis,
			AsyncCallback<AlleNachrichtenReport> callback);

	void erstelleZeitraumspezifischeNachrichtenReport(Nachricht nachricht, Date anfangszeitpunkt, Date endzeitpunkt,
			AsyncCallback<ZeitraumspezifischeNachrichtenReport> callback);

	void erstelleNutzerspezifischeNachrichtenReport(Nachricht nachricht, Nutzer nutzer,
			AsyncCallback<NutzerspezifischeNachrichtenReport> callback);

	void erstelleNutzerspezifischeAbonnementsReport(Abonnement abonnement, Nutzer nutzer,
			AsyncCallback<NutzerspezifischeAbonnementsReport> callback);
	
	void erstelleHashtagspezifischeAbonnementsReport(Abonnement abonnement, Hashtag hashtag,
			AsyncCallback<HashtagspezifischeAbonnementsReport> callback);
	
	void init(AsyncCallback<Void> callback);

}