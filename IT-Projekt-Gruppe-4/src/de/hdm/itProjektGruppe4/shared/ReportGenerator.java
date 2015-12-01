package de.hdm.itProjektGruppe4.shared;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.itProjektGruppe4.shared.bo.*;
import de.hdm.itProjektGruppe4.shared.report.InfosVonAllenNachrichtenReport;
import de.hdm.itProjektGruppe4.shared.report.InfosVonNachrichtenReport;
import de.hdm.itProjektGruppe4.shared.report.Report;
import de.hdm.itProjektGruppe4.shared.MessagingAdministration;
import de.hdm.thies.bankProjekt.shared.bo.Customer;
import de.hdm.thies.bankProjekt.shared.report.AllAccountsOfAllCustomersReport;
import de.hdm.thies.bankProjekt.shared.report.AllAccountsOfCustomerReport;


/**
 * <p>
 * Synchrone Schnittstelle für eine RPC-fähige Klasse zur Erstellung von
 * Reports. Diese Schnittstelle benutzt die gleiche Realisierungsgrundlage wir
 * das Paar {@link BankAdministration} und {@lBankAdministrationImplImpl}. Zu
 * technischen Erläuterung etwa bzgl. GWT RPC bzw. {@link RemoteServiceServlet}
 * siehe {@link BankAdministration} undBankAdministrationImpltungImpl}.
 * </p>
 * <p>
 * Ein ReportGenerator bietet die Möglichkeit, eine Menge von Berichten
 * (Reports) zu erstellen, die Menge von Daten bzgl. bestimmter Sachverhalte des
 * Systems zweckspezifisch darstellen.
 * </p>
 * <p>
 * Die Klasse bietet eine Reihe von <code>create...</code>-Methoden, mit deren
 * Hilfe die Reports erstellt werden können. Jede dieser Methoden besitzt eine
 * dem Anwendungsfall entsprechende Parameterliste. Diese Parameter benötigt der
 * der Generator, um den Report erstellen zu können.
 * </p>
 * <p> 
 * Bei neu hinzukommenden Bedarfen an Berichten, kann diese Klasse auf einfache
 * Weise erweitert werden. Hierzu können zusätzliche <code>create...</code>
 * -Methoden implementiert werden. Die bestehenden Methoden bleiben davon
 * unbeeinflusst, so dass bestehende Programmlogik nicht verändert werden muss.
 * </p>
 * 
 * @author thies
 * @author Yücel 
 */


@RemoteServiceRelativePath ("reportgenerator")
public interface ReportGenerator extends RemoteService {
	
	public void init() throws IllegalArgumentException;
	
	
	
	public Report alleNachrichtenAnzeigen(Nachricht nachricht) throws IllegalArgumentException;
	
	public Report alleAbonnementsAnzeien(Abonnement abonnement) throws IllegalArgumentException;
	
	public Report zeitraumspezifischeNachrichtenAnzeigen (Nachricht nachricht) throws IllegalArgumentException;
	
	public Report nutzerspezifischeAbonnementsAnzeigen (Abonnement abonnement) throws IllegalArgumentException;
	
	public Report hashtagspezifischeAbonnementsAnzeigen (Abonnement abonnement) throws IllegalArgumentException;
	
	public Report nutzerspezifischeNachrichtenAnzeigen (Nachricht nachricht) throws IllegalArgumentException;
		
	}
