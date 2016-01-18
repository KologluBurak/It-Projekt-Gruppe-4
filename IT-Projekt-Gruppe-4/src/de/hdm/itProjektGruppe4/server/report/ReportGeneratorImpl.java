package de.hdm.itProjektGruppe4.server.report;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.itProjektGruppe4.shared.MessagingAdministration;
import de.hdm.itProjektGruppe4.server.MessagingAdministrationImpl;
import de.hdm.itProjektGruppe4.shared.ReportGenerator;
import de.hdm.itProjektGruppe4.shared.bo.*;
import de.hdm.itProjektGruppe4.shared.report.*;

@SuppressWarnings("serial")
public class ReportGeneratorImpl extends RemoteServiceServlet implements ReportGenerator {

	/**
	 * Ein ReportGenerator benötigt Zugriff auf die Messaging, da diese die
	 * essentiellen Methoden für die Koexistenz von Datenobjekten (vgl.
	 * bo-Package) bietet.
	 */
	private MessagingAdministration messagingadministration = null;

	/**
	 * <p>
	 * Ein <code>RemoteServiceServlet</code> wird unter GWT mittels
	 * <code>GWT.create(Klassenname.class)</code> Client-seitig erzeugt. Hierzu
	 * ist ein solcher No-Argument-Konstruktor anzulegen. Ein Aufruf eines
	 * anderen Konstruktors ist durch die Client-seitige Instantiierung durch
	 * <code>GWT.create(Klassenname.class)</code> nach derzeitigem Stand nicht
	 * möglich.
	 * </p>
	 * <p>
	 * Es bietet sich also an, eine separate Instanzenmethode zu erstellen, die
	 * Client-seitig direkt nach <code>GWT.create(Klassenname.class)</code>
	 * aufgerufen wird, um eine Initialisierung der Instanz vorzunehmen.
	 * </p>
	 */
	public ReportGeneratorImpl() throws IllegalArgumentException {

	}

	/**
	 * Initialsierungsmethode. Siehe dazu Anmerkungen zum
	 * No-Argument-Konstruktor.
	 * 
	 * @see #ReportGeneratorImpl()
	 */
	@Override
	public void init() throws IllegalArgumentException {
		/*
		 * Ein ReportGeneratorImpl-Objekt instantiiert für seinen Eigenbedarf
		 * eine MessagingAdministrationsImpl-Instanz.
		 */
		MessagingAdministrationImpl m = new MessagingAdministrationImpl();
		m.init();
		this.messagingadministration = m;
	}

	/**
	 * Auslesen der zugehörigen MessagingAdministration.
	 * 
	 * @return das MessagingAdministration Serviceobjekt
	 */
	protected MessagingAdministration getMessagingAdministration() {
		return this.messagingadministration;
	}

	/**
	 * Erstellen von <code>AlleAbonnementsReport</code>-Objekten.
	 * 
	 * @return
	 * @throws Exception 
	 */
	
	public AlleAbonnementsReport erstelleAlleAbonnementsReport()
			throws Exception {

		if (this.getMessagingAdministration() == null)
			return null;

		/*
		 * Zunächst legen wir uns einen leeren Report an.
		 */
		AlleAbonnementsReport result = new AlleAbonnementsReport();

		
		// Jeder Report hat einen Titel (Bezeichnung / Ueberschrift).
		result.setTitle("Alle Abonnements");

		/*
		 * Datum der Erstellung hinzufügen. new Date() erzeugt autom. einen
		 * "Timestamp" des Zeitpunkts der Instantiierung des Date-Objekts.
		 */
		result.setCreated(new Date());

		
		/*
		 * Nun werden sämtliche Daten der Abonnements ausgelesen und in die
		 * Tabelle eingetragen.
		 */
		ArrayList<Abonnement> alleAbonnements = this.messagingadministration.getAllAbonnements();

		for (Abonnement abonnement : alleAbonnements) {
			// Eine leere Zeile anlegen.
			Row accountRow = new Row();

			// Erste Spalte: Abonnement ID hinzufügen
			accountRow.addColumn(new Column(String.valueOf(abonnement.getId())));

			// und schließlich die Zeile dem Report hinzufügen.
			result.addRow(accountRow);
		
		
		  /*
	       * Anlegen des jew. Teil-Reports und Hinzufügen zum Gesamt-Report.
	       */
		 result.addSubReport(this.erstelleNutzerspezifischeAbonnementsReport(null));
		 result.addSubReport(this.erstelleHashtagspezifischeAbonnementsReport(null));
		    
	}

	/*
	 * Jetzt muss noch der fertige Report zurückgegeben werden.
	 */
	return result;
		
}
	/**
	 * Erstellen von <code>AlleNachrichtenReport</code>-Objekten.
	 * 
	 * @param von
	 * @param bis
	 * @return
	 * @throws Exception 
	 */
	
	public AlleNachrichtenReport erstelleAlleNachrichtenReport() 
			throws Exception {

		if (this.getMessagingAdministration() == null)
			return null;

		/*
		 * Zunächst legen wir uns einen leeren Report an.
		 */
		AlleNachrichtenReport result = new AlleNachrichtenReport();

		// Jeder Report hat einen Titel (Bezeichnung / überschrift).
		result.setTitle("Alle Nachrichten");

		/*
		 * Datum der Erstellung hinzufügen. new Date() erzeugt autom. einen
		 * "Timestamp" des Zeitpunkts der Instantiierung des Date-Objekts.
		 */
		result.setCreated(new Date());

		/*
		 * Nun müssen sämtliche Nachrichten-Objekte ausgelesen werden.
		 * Anschließend wir für jedes Nachrichtenobjekt r ein Aufruf von
		 * InfosVonAllenNachrichten durchgeführt und somit jeweils ein
		 * InfosVonAllenNachrichten-Objekt erzeugt. Diese Objekte werden
		 * sukzessive der result-Variable hinzugefügt. Sie ist vom Typ
		 * InfosVonAllenNachrichten Report, welches eine Subklasse von
		 * CompositeReport ist.
		 */
		ArrayList<Nachricht> alleNachrichten = this.messagingadministration.getAllNachrichten();

		for (Nachricht nachricht : alleNachrichten) {

			// Eine leere Zeile anlegen.
			Row accountRow = new Row();

			// Erste Spalte: Unterhaltungs ID hinzufügen
			accountRow.addColumn(new Column(String.valueOf(nachricht.getId())));

			// Zweite Spalte: Nachrichtenabsender hinzufügen
			accountRow.addColumn(new Column(String.valueOf(nachricht.getAbsender())));

			// Dritte Spalte: Nachrichtenabsender hinzufügen
			accountRow.addColumn(new Column(String.valueOf(nachricht.getErstellungsZeitpunkt())));
			
			// und schließlich die Zeile dem Report hinzufügen.
			result.addRow(accountRow);
		
			} 
//			/*
//		     * Anlegen des jew. Teil-Reports und Hinzufügen zum Gesamt-Report.
//		     */
//			 result.addSubReport(this.erstelleNutzerspezfischeNachrichtenReport());
//			 result.addSubReport(this.erstelleZeitraumspezifischeNachrichtenReport());	    
//		
		return result;
	}
	
	/**
	 * Erstellen von <code>ZeitraumspezifischeNachrichtenReport</code>-Objekten.
	 * 
	 * @param von
	 * @param bis
	 * @return
	 * @throws Exception 
	 */

	public ZeitraumspezifischeNachrichtenReport erstelleZeitraumspezifischeNachrichtenReport (Nachricht nachricht, String von, String bis)
			throws Exception {

		if (this.getMessagingAdministration() == null)
			return null;

		/*
		 * Zunächst legen wir uns einen leeren Report an.
		 */
		ZeitraumspezifischeNachrichtenReport result = new ZeitraumspezifischeNachrichtenReport();

		// Jeder Report hat einen Titel (Bezeichnung / úberschrift).
		result.setTitle("Zeitraumspezifische Nachrichten");

		/*
		 * Datum der Erstellung hinzufügen. new Date() erzeugt autom. einen
		 * "Timestamp" des Zeitpunkts der Instantiierung des Date-Objekts.
		 */
		result.setCreated(new Date());

		/*
		 * Ab hier erfolgt die Zusammenstellung der Kopfdaten (die Dinge, die
		 * oben auf dem Report stehen) des Reports. Die Kopfdaten sind
		 * mehrzeilig, daher die Verwendung von CompositeParagraph.
		 */
		CompositeParagraph header = new CompositeParagraph();

		// Die Nachrichten aufzunehmen
		header.addSubParagraph(new SimpleParagraph(nachricht.getId() + ", "));

		// Den Zeitraum aufzunehmen
		header.addSubParagraph(new SimpleParagraph(nachricht.getErstellungsZeitpunkt() + ", " 
		+ nachricht.getErstellungsZeitpunkt() + ", "));
		
		// Hinzufügen der zusammengestellten Kopfdaten zu dem Report
		result.setHeaderData(header);

		/**
		 * Ab hier erfolgt ein zeilenweises Hinzufügen von
		 * Abonnement-Informationen.
		 */

		/*
		 * Zunächst legen wir eine Kopfzeile für die Abonnementinfos-Tabelle an.
		 */
		Row headline = new Row();

		/*
		 * Wir wollen Zeilen mit 2 Spalten in der Tabelle erzeugen. In die erste
		 * Spalte schreiben wir den jeweiligen Abonnement, in die zweite Spalte
		 * schreiben wir den Anfangszeitpunkt und in die letzte Zeile schreiben
		 * wir den Endzeitpunkt.In der Kopfzeile legen wir also entsprechende
		 * Überschriften ab.
		 */
		headline.addColumn(new Column("Nachrichten"));
		headline.addColumn(new Column("Zeitraum"));
		
		// Hinzufügen der Kopfzeile
		result.addRow(headline);

		/*
		 * Nun werden sämtliche Daten der Nachrichten je Zeitraum ausgelesen und in die
		 * Tabelle eingetragen.
		 */
		ArrayList<Nachricht> nachrichtenJeZeitraum = this.messagingadministration.getAlleNachrichtenJeZeitraum(von, bis);

		for (Nachricht nachrichtenZ : nachrichtenJeZeitraum) {
			// Eine leere Zeile anlegen.
			Row accountRow = new Row();

			// Erste Spalte: Nachricht ID hinzufügen
			accountRow.addColumn(new Column(String.valueOf(nachrichtenZ.getId())));
			
			// Zweite Spalte: Zeitraum hinzufügen
			accountRow.addColumn(new Column(String.valueOf(nachrichtenZ.getErstellungsZeitpunkt())));

			// und schließlich die Zeile dem Report hinzufügen.
			result.addRow(accountRow);
		}

	/*
	 * Jetzt muss noch der fertige Report zurückgegeben werden.
	 */
	return result;
	
	}
	
	/**
	 * Erstellen von <code>NutzerspezifischeNachrichtenReport</code>-Objekten.
	 * 
	 * @param nutzer
	 * @return
	 * @throws Exception 
	 */
	public NutzerspezifischeNachrichtenReport erstelleNutzerspezifischeNachrichtenReport (Nachricht nachricht, Nutzer nutzer)
			throws Exception {

		if (this.getMessagingAdministration() == null)
			return null;

		/*
		 * Zunächst legen wir uns einen leeren Report an.
		 */
		NutzerspezifischeNachrichtenReport result = new NutzerspezifischeNachrichtenReport();

		// Jeder Report hat einen Titel (Bezeichnung / úberschrift).
		result.setTitle("Nutzerspezifische Nachrichten");

		/*
		 * Datum der Erstellung hinzufügen. new Date() erzeugt autom. einen
		 * "Timestamp" des Zeitpunkts der Instantiierung des Date-Objekts.
		 */
		result.setCreated(new Date());

		/*
		 * Ab hier erfolgt die Zusammenstellung der Kopfdaten (die Dinge, die
		 * oben auf dem Report stehen) des Reports. Die Kopfdaten sind
		 * mehrzeilig, daher die Verwendung von CompositeParagraph.
		 */
		CompositeParagraph header = new CompositeParagraph();

		// Die Nachrichten aufnehmen
		header.addSubParagraph(new SimpleParagraph(nachricht.getId() + ", "));

		// Den Nutzer aufnehmen
		header.addSubParagraph(new SimpleParagraph(nachricht.getAbsender() + ", "));

		// Hinzufügen der zusammengestellten Kopfdaten zu dem Report
		result.setHeaderData(header);

		/**
		 * Ab hier erfolgt ein zeilenweises Hinzufügen von
		 * Nachrichten je Nutzer Informationen.
		 */

		/*
		 * Zunächst legen wir eine Kopfzeile für die Abonnementinfos-Tabelle an.
		 */
		Row headline = new Row();

		/*
		 * Wir wollen Zeilen mit 2 Spalten in der Tabelle erzeugen. In die erste
		 * Spalte schreiben wir den jeweiligen Abonnement, in die zweite Spalte
		 * schreiben wir den Anfangszeitpunkt und in die letzte Zeile schreiben
		 * wir den Endzeitpunkt.In der Kopfzeile legen wir also entsprechende
		 * Überschriften ab.
		 */
		headline.addColumn(new Column("Nachricht"));
		headline.addColumn(new Column("Nutzer"));
		
		// Hinzufügen der Kopfzeile
		result.addRow(headline);

		/*
		 * Nun werden sämtliche Daten der Nachrichten je Nutzer ausgelesen und in die
		 * Tabelle eingetragen.
		 */
		ArrayList<Nachricht> nachrichtenJeNutzer = this.messagingadministration.getAlleNachrichtenJeNutzer(nutzer);

		for (Nachricht nachrichtenN : nachrichtenJeNutzer) {
			// Eine leere Zeile anlegen.
			Row accountRow = new Row();

			// Erste Spalte: Nachricht ID hinzufügen
			accountRow.addColumn(new Column(String.valueOf(nachrichtenN.getId())));
			
			// Zweite Spalte: Nutzer hinzufügen
			accountRow.addColumn(new Column(String.valueOf(nachrichtenN.getAbsender())));

			// und schließlich die Zeile dem Report hinzufügen.
			result.addRow(accountRow);
		}

	/*
	 * Jetzt muss noch der fertige Report zurückgegeben werden.
	 */
	return result;
	
	}
	
	/**
	 * Erstellen von <code>NutzerspezifischeAbonnementsReport</code>-Objekten.
	 * 
	 * @return
	 * @throws Exception 
	 */
	public NutzerspezifischeAbonnementsReport erstelleNutzerspezifischeAbonnementsReport (Nutzerabonnement nutzerabonnement)
			throws Exception {

		if (this.getMessagingAdministration() == null)
			return null;

		/*
		 * Zunächst legen wir uns einen leeren Report an.
		 */
		NutzerspezifischeAbonnementsReport result = new NutzerspezifischeAbonnementsReport();

		// Jeder Report hat einen Titel (Bezeichnung / úberschrift).
		result.setTitle("Nutzerspezifische Abonnements");

		/*
		 * Datum der Erstellung hinzufügen. new Date() erzeugt autom. einen
		 * "Timestamp" des Zeitpunkts der Instantiierung des Date-Objekts.
		 */
		result.setCreated(new Date());

		/*
		 * Ab hier erfolgt die Zusammenstellung der Kopfdaten (die Dinge, die
		 * oben auf dem Report stehen) des Reports. Die Kopfdaten sind
		 * mehrzeilig, daher die Verwendung von CompositeParagraph.
		 */
		CompositeParagraph header = new CompositeParagraph();

		// Den Nutzerabo aufnehmen
		header.addSubParagraph(new SimpleParagraph(nutzerabonnement.getId() + ", "));

		// Das Abonnement aufnehmen
		header.addSubParagraph(new SimpleParagraph(nutzerabonnement.getAbonnementID() + ", "));

		// Den Beobachteten Nutzer aufnehmen
		header.addSubParagraph(new SimpleParagraph(nutzerabonnement.getDerBeobachteteID() + ", "));

		// Den Follower aufnehmen
		header.addSubParagraph(new SimpleParagraph(nutzerabonnement.getFollowerID() + ", "));

		// Hinzufügen der zusammengestellten Kopfdaten zu dem Report
		result.setHeaderData(header);

		/**
		 * Ab hier erfolgt ein zeilenweises Hinzufügen von
		 * Abonnement-Informationen.
		 */

		/*
		 * Zunächst legen wir eine Kopfzeile für die Abonnementinfos-Tabelle an.
		 */
		Row headline = new Row();

		/*
		 * Wir wollen Zeilen mit 2 Spalten in der Tabelle erzeugen. In die erste
		 * Spalte schreiben wir den jeweiligen Abonnement, in die zweite Spalte
		 * schreiben wir den Anfangszeitpunkt und in die letzte Zeile schreiben
		 * wir den Endzeitpunkt.In der Kopfzeile legen wir also entsprechende
		 * Überschriften ab.
		 */
		headline.addColumn(new Column("Nutzerabo"));
		headline.addColumn(new Column("Abonnement"));
		headline.addColumn(new Column("DerBeobachtete Nutzer"));
		headline.addColumn(new Column("Follower"));

		// Hinzufügen der Kopfzeile
		result.addRow(headline);

		/*
		 * Nun werden sämtliche Daten der Nutzerabonnements ausgelesen und in die
		 * Tabelle eingetragen.
		 */
		ArrayList<Nutzerabonnement> AbonnementsJeNutzer = this.messagingadministration.getAllNutzerabonnements(null);

		for (Nutzerabonnement nutzerabonnements : AbonnementsJeNutzer) {
			// Eine leere Zeile anlegen.
			Row accountRow = new Row();

			// Erste Spalte: NutzerAbo ID hinzufügen
			accountRow.addColumn(new Column(String.valueOf(nutzerabonnements.getId())));
			
			// Zweite Spalte: Nutzerabonnement hinzufügen
			accountRow.addColumn(new Column(String.valueOf(nutzerabonnements.getAbonnementID())));

			// Dritte Spalte: Den Beobachteten hinzufügen
			accountRow.addColumn(new Column(String.valueOf(nutzerabonnements.getDerBeobachteteID())));
			
			// Vierte Spalte: Follower hinzufügen
			accountRow.addColumn(new Column(String.valueOf(nutzerabonnement.getFollowerID())));
			
			// und schließlich die Zeile dem Report hinzufügen.
			result.addRow(accountRow);
		}

	/*
	 * Jetzt muss noch der fertige Report zurückgegeben werden.
	 */
	return result;
	
	}
	
	/**
	 * Erstellen von <code>HashtagspezifischeAbonnementsReport</code>-Objekten.
	 * 
	 * @return
	 * @throws Exception 
	 */
	public HashtagspezifischeAbonnementsReport erstelleHashtagspezifischeAbonnementsReport (Hashtagabonnement hashtagabonnement)
			throws Exception {

		if (this.getMessagingAdministration() == null)
			return null;

		/*
		 * Zunächst legen wir uns einen leeren Report an.
		 */
		HashtagspezifischeAbonnementsReport result = new HashtagspezifischeAbonnementsReport();

		// Jeder Report hat einen Titel (Bezeichnung / úberschrift).
		result.setTitle("Hashtagspezifische Abonnements");

		/*
		 * Datum der Erstellung hinzufügen. new Date() erzeugt autom. einen
		 * "Timestamp" des Zeitpunkts der Instantiierung des Date-Objekts.
		 */
		result.setCreated(new Date());

		/*
		 * Ab hier erfolgt die Zusammenstellung der Kopfdaten (die Dinge, die
		 * oben auf dem Report stehen) des Reports. Die Kopfdaten sind
		 * mehrzeilig, daher die Verwendung von CompositeParagraph.
		 */
		CompositeParagraph header = new CompositeParagraph();

		// Den Abonnenten aufnehmen
		header.addSubParagraph(new SimpleParagraph(hashtagabonnement.getId() + ", "));

		// Das Hashtag aufnehmen
		header.addSubParagraph(new SimpleParagraph(hashtagabonnement.getHashtagID() + ", "));

		// Das Abonnement aufnehmen
		header.addSubParagraph(new SimpleParagraph(hashtagabonnement.getAbonnementID() + ", "));

		// Den Nutzer aufnehmen
		header.addSubParagraph(new SimpleParagraph(hashtagabonnement.getNutzerID() + ", "));

		// Hinzufügen der zusammengestellten Kopfdaten zu dem Report
		result.setHeaderData(header);

		/**
		 * Ab hier erfolgt ein zeilenweises Hinzufügen von
		 * Abonnement-Informationen.
		 */

		/*
		 * Zunächst legen wir eine Kopfzeile für die Abonnementinfos-Tabelle an.
		 */
		Row headline = new Row();

		/*
		 * Wir wollen Zeilen mit 2 Spalten in der Tabelle erzeugen. In die erste
		 * Spalte schreiben wir den jeweiligen Abonnement, in die zweite Spalte
		 * schreiben wir den Anfangszeitpunkt und in die letzte Zeile schreiben
		 * wir den Endzeitpunkt.In der Kopfzeile legen wir also entsprechende
		 * Überschriften ab.
		 */
		headline.addColumn(new Column("Abonnements je Hashtag"));

		// Hinzufügen der Kopfzeile
		result.addRow(headline);

		/*
		 * Nun werden sämtliche Daten der Abonnements ausgelesen und in die
		 * Tabelle eingetragen.
		 */
		ArrayList<Hashtagabonnement> AbonnementsJeHashtag = this.messagingadministration.getAllHashtagabonnements(null);

		for (Hashtagabonnement hashtagabonnements : AbonnementsJeHashtag) {
			// Eine leere Zeile anlegen.
			Row accountRow = new Row();

			// Erste Spalte: Hashtagabo ID hinzufügen
			accountRow.addColumn(new Column(String.valueOf(hashtagabonnements.getId())));
			
			// Zweite Spalte: Hashtag hinzufügen
			accountRow.addColumn(new Column(String.valueOf(hashtagabonnements.getHashtagID())));
			
			// Dritte Spalte: Hashtagabonnement hinzufügen
			accountRow.addColumn(new Column(String.valueOf(hashtagabonnements.getAbonnementID())));

			// Vierte Spalte: Den Nutzer hinzufügen
			accountRow.addColumn(new Column(String.valueOf(hashtagabonnements.getNutzerID())));
			
			// und schließlich die Zeile dem Report hinzufügen.
			result.addRow(accountRow);
		}

	/*
	 * Jetzt muss noch der fertige Report zurückgegeben werden.
	 */
	return result;
	
	}

}