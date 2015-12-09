package de.hdm.itProjektGruppe4.shared;


import java.util.Date;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.itProjektGruppe4.shared.bo.*;
import de.hdm.itProjektGruppe4.shared.report.*;


/**
 * <p>
 * Synchrone Schnittstelle für eine RPC-fähige Klasse zur Erstellung von
 * Reports. Diese Schnittstelle benutzt die gleiche Realisierungsgrundlage wir
 * das Paar {@link MessagingAdministration} und {@link MessagingAdministrationImpl}. Zu
 * technischen Erläuterung etwa bzgl. GWT RPC bzw. {@link RemoteServiceServlet}
 * siehe {@link MessagingAdministration} und {@link MessagingAdministrationImpl}.
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
 * @author Oikonomou 
 */


public interface ReportGenerator extends RemoteService {

	/**
	   * Initialisierung des Objekts. Diese Methode ist vor dem Hintergrund von GWT
	   * RPC zusätzlich zum No Argument Constructor der implementierenden Klasse
	   * MessagingAdministrationImpl} notwendig. Bitte diese Methode direkt nach der
	   * Instantiierung aufrufen.
	   * 
	   * @throws IllegalArgumentException
	   */
	
	public void init() throws IllegalArgumentException;
	
	public abstract InfosVonAllenNachrichtenReport erstelleInfosVonAllenNachrichtenReport(Date anfangszeitpunkt, Date endzeitpunkt)
	throws IllegalArgumentException;
	
	public abstract InfosVonAllenAbonnementsReport erstelleInfosVonAllenAbonnementsReport(Date anfangszeitpunkt, Date endzeitpunkt)
	throws IllegalArgumentException;
	
	public abstract InfosÜberZeitraumspezifischeNachrichtenReport erstelleInfosÜberZeitraumspezifischeNachrichtenReport (Nachricht nachricht, Date anfangszeitpunkt, Date endzeitpunkt) 
	throws IllegalArgumentException;
	
	public abstract InfosÜberNutzerspezifischeNachrichtenReport erstelleInfosÜberNutzerspezifischeNachrichtenReport (Nachricht nachricht, Nutzer nutzer) 
	throws IllegalArgumentException;
	
	public abstract InfosÜberNutzerspezifischeAbonnementsReport erstelleInfosÜberNutzerspezifischeAbonnementsReport (Abonnement abonnement, Nutzer nutzer) 
	throws IllegalArgumentException;
	
	public abstract InfosÜberHashtagspezifischeAbonnementsReport erstelleInfosÜberHashtagspezifischeAbonnementsReport (Abonnement abonnement, Hashtag hashtag) 
	throws IllegalArgumentException;
		
}

