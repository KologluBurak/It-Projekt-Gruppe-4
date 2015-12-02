package de.hdm.itProjektGruppe4.shared;

import java.util.ArrayList;
import java.sql.Timestamp;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.itProjektGruppe4.shared.bo.*;
import de.hdm.itProjektGruppe4.report.*;


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
 * @author thies, Yücel
 */


@RemoteServiceRelativePath ("reportgenerator")
public interface ReportGenerator extends RemoteService {
	
//	public void init() throws IllegalArgumentException;
//	
//	public ArrayList<Nutzer> findAllNutzer();
//	
//	public ArrayList<Hashtag> findAllHashtags();
//	
//	public ArrayList<Nachricht> findAllNachrichten();
//	
//	public ArrayList<Abonnement> findAllAbonnements();
//	
//	public ArrayList<Nachricht> alleNachrichtenJeZeitraum(String von, String bis);
//			
//	public ArrayList<Nachricht> alleNachrichtenJeNutzer(Nutzer nutzer, String von, String bis);
//	
//	public ArrayList<Abonnement> findAllHastagabonnements(Hashtag hashtag, String von, String bis); 
//			
//	public ArrayList<Abonnement> findAllNutzerabonnements(Nutzer nutzer, String von, String bis);
//	

	public void init() throws IllegalArgumentException;
	
	public abstract InfosVonNachrichtenReport erstelleInfosVonNachrichtenReport(Nachricht nachricht, Timestamp anfangszeitpunkt, Timestamp endzeitpunkt)
	throws IllegalArgumentException;
	
	public abstract InfosVonAbonnementsReport erstelleInfosVonAbonnementsReport(Abonnement abonnement, Timestamp anfangszeitpunkt, Timestamp endzeitpunkt) 
	throws IllegalArgumentException;
	
	public abstract InfosVonAllenNachrichtenReport erstelleInfosVonAllenNachrichtenReport(Timestamp anfangszeitpunkt, Timestamp endzeitpunkt)
	throws IllegalArgumentException;
	
	public abstract InfosVonAllenAbonnementsReport erstelleInfosVonAllenAbonnementsReport(Timestamp anfangszeitpunkt, Timestamp endzeitpunkt)
	throws IllegalArgumentException;
}


	
