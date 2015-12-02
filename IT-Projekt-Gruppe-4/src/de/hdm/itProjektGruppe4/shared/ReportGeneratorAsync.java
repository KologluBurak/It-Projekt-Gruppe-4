package de.hdm.itProjektGruppe4.shared;

import java.util.ArrayList;
import java.sql.Timestamp;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.itProjektGruppe4.shared.bo.*;
import de.hdm.itProjektGruppe4.report.*;
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

	void init(AsyncCallback<Void> callback);
}
