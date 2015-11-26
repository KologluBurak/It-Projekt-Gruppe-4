package de.hdm.itProjektGruppe4.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.itProjektGruppe4.shared.bo.Abonnement;
import de.hdm.itProjektGruppe4.shared.bo.Nachricht;
import de.hdm.itProjektGruppe4.shared.report.Report;
import de.hdm.itProjektGruppe4.shared.ReportGenerator;

/**
 * Das asynchrone Gegenstück des Interface {@link ReportGenerator}. Es wird
 * semiautomatisch durch das Google Plugin erstellt und gepflegt. Daher erfolgt
 * hier keine weitere Dokumentation. Für weitere Informationen siehe das
 * synchrone Interface {@link ReportGenerator}.
 * 
 * @author thies, Yücel
 */

public interface ReportGeneratorAsync {

void init(AsyncCallback<Void> callback);

void alleNachrichtenAnzeigen(Nachricht nachricht, AsyncCallback<Report> callback);

void alleAbonnementsAnzeien(Abonnement abonnement,
		AsyncCallback<Report> callback);

void zeitraumspezifischeNachrichtenAnzeigen(Nachricht nachricht,
		AsyncCallback<Report> callback);

void nutzerspezifischeAbonnementsAnzeigen(Abonnement abonnement,
		AsyncCallback<Report> callback);

void hashtagspezifischeAbonnementsAnzeigen(Abonnement abonnement,
		AsyncCallback<Report> callback);

void nutzerspezifischeNachrichtenAnzeigen(Nachricht nachricht,
		AsyncCallback<Report> callback);

}
