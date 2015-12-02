package de.hdm.itProjektGruppe4.shared;

import java.util.ArrayList;
import java.sql.Timestamp;

import com.google.gwt.user.client.rpc.AsyncCallback;

<<<<<<< HEAD
import de.hdm.itProjektGruppe4.shared.bo.Abonnement;
import de.hdm.itProjektGruppe4.shared.bo.Nachricht;
import de.hdm.itProjektGruppe4.shared.bo.Nutzer;
import de.hdm.itProjektGruppe4.shared.report.InfosVonAllenNachrichtenReport;
import de.hdm.itProjektGruppe4.shared.report.InfosVonNachrichtenReport;
import de.hdm.itProjektGruppe4.shared.report.Report;
import de.hdm.itProjektGruppe4.shared.ReportGenerator;

=======
import de.hdm.itProjektGruppe4.shared.bo.*;
import de.hdm.itProjektGruppe4.report.*;
>>>>>>> refs/heads/vasili
/**
 * Das asynchrone Gegenstück des Interface {@link ReportGenerator}. Es wird
 * semiautomatisch durch das Google Plugin erstellt und gepflegt. Daher erfolgt
 * hier keine weitere Dokumentation. Für weitere Informationen siehe das
 * synchrone Interface {@link ReportGenerator}.
 * 
 * @author thies, Yücel
 */

public interface ReportGeneratorAsync {


	void erstelleInfosVonAllenNachrichtenReport(Timestamp anfangszeitpunkt,
			Timestamp endzeitpunkt,
			AsyncCallback<InfosVonAllenNachrichtenReport> callback);

	void erstelleInfosVonAllenAbonnementsReport(Timestamp anfangszeitpunkt,
			Timestamp endzeitpunkt, AsyncCallback<InfosVonAllenAbonnementsReport> callback);

	void erstelleInfosVonNachrichtenReport(Nachricht nachricht,
			Timestamp anfangszeitpunkt, Timestamp endzeitpunkt,
			AsyncCallback<InfosVonNachrichtenReport> callback);

	void erstelleInfosVonAbonnementsReport(Abonnement abonnement, Timestamp anfangszeitpunkt,
			Timestamp endzeitpunkt, AsyncCallback<InfosVonAbonnementsReport> callback);

<<<<<<< HEAD
void hashtagspezifischeAbonnementsAnzeigen(Abonnement abonnement,
		AsyncCallback<Report> callback);

void nutzerspezifischeNachrichtenAnzeigen(Nachricht nachricht,
		AsyncCallback<Report> callback);

void createAllMessagesOfUserReport(
		AsyncCallback<InfosVonNachrichtenReport> callback);

void createAllMessagesOfAllUsersReport(Nutzer n,
		AsyncCallback<InfosVonAllenNachrichtenReport> callback);

=======
	void init(AsyncCallback<Void> callback);
>>>>>>> refs/heads/vasili
}
