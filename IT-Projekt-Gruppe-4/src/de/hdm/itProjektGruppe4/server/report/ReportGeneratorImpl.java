package de.hdm.itProjektGruppe4.server.report;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.itProjektGruppe4.shared.MessagingAdministration;
import de.hdm.itProjektGruppe4.server.MessagingAdministrationImpl;
import de.hdm.itProjektGruppe4.server.db.*;
import de.hdm.itProjektGruppe4.shared.ReportGenerator;
import de.hdm.itProjektGruppe4.shared.bo.*;
import de.hdm.itProjektGruppe4.shared.report.*;




@SuppressWarnings("serial")
public abstract class ReportGeneratorImpl extends RemoteServiceServlet
implements ReportGenerator {

	/**
	 * Ein ReportGenerator benötigt Zugriff auf die Messaging, da diese die
	 * essentiellen Methoden für die Koexistenz von Datenobjekten (vgl.
	 * bo-Package) bietet.
	 */
	protected MessagingAdministration messagingadministration = null;

	/**
	 * <p>
	 * Ein <code>RemoteServiceServlet</code> wird unter GWT mittels
	 * <code>GWT.create(Klassenname.class)</code> Client-seitig erzeugt. Hierzu
	 * ist ein solcher No-Argument-Konstruktor anzulegen. Ein Aufruf eines anderen
	 * Konstruktors ist durch die Client-seitige Instantiierung durch
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
	 * Initialsierungsmethode. Siehe dazu Anmerkungen zum No-Argument-Konstruktor.
	 * 
	 * @see #ReportGeneratorImpl()
	 */
	public void init() throws IllegalArgumentException {
		/*
		 * Ein ReportGeneratorImpl-Objekt instantiiert für seinen Eigenbedarf eine
		 * MessagingAdministrationsImpl-Instanz.
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
	 * Erstellen von <code>InfosVonAllenAbonnementsReport</code>-Objekten.
	 * 
	 * @param user das Abonnementobjekt bzgl. dessen der Report erstellt werden soll.
	 * @return 
	 * @return der fertige Report
	 */
	
	public String erstelleInfosVonAllenAbonnementsReport (Abonnement aboNutzer, Abonnement aboHashtag) 
		throws IllegalArgumentException {
	
		if (this.getMessagingAdministration() == null)
			return null;

		/*
		 * Zunächst legen wir uns einen leeren Report an.
		 */
		InfosVonAllenAbonnementsReport result = new InfosVonAllenAbonnementsReport();

		// Jeder Report hat einen Titel (Bezeichnung / úberschrift).
//		result.setTitle("Alle Abonnements" + aboNutzer.getAboNutzer() + 
//				" " + aboHashtag.getAboHashtag());

		/*
		 * Datum der Erstellung hinzufügen. new Date() erzeugt autom. einen
		 * "Timestamp" des Zeitpunkts der Instantiierung des Date-Objekts.
		 */
		result.setCreated(new Date());

		/*
		 * Ab hier erfolgt die Zusammenstellung der Kopfdaten (die Dinge, die oben
		 * auf dem Report stehen) des Reports. Die Kopfdaten sind mehrzeilig, daher
		 * die Verwendung von CompositeParagraph.
		 */
		CompositeParagraph header = new CompositeParagraph();
		
		// Den Abonnenten aufnehmen
		// raus gemacht header.addSubParagraph(new SimpleParagraph(aboNutzer.getAbonnent() + ", "));
		
		// Den abonnierten Nutzer aufnehmen
		// raus gemacht header.addSubParagraph(new SimpleParagraph(aboNutzer.getAbonnierterNutzer() + ", "));
		
		// Den abonniertes Hashtag aufnehmen 
		// raus gemachtheader.addSubParagraph(new SimpleParagraph(aboHashtag.getAbonniertesHashtag() + ", "));
		
		
		// Hinzufügen der zusammengestellten Kopfdaten zu dem Report
		result.setHeaderData(header);

			/**
			 * Ab hier erfolgt ein zeilenweises Hinzufügen von Abonnement-Informationen.
			 */

			/*
			 * Zunächst legen wir eine Kopfzeile für die Abonnementinfos-Tabelle an.
			 */
			Row headline = new Row();

			/*
			 * Wir wollen Zeilen mit 2 Spalten in der Tabelle erzeugen. In die erste
			 * Spalte schreiben wir den jeweiligen Abonnement, in die zweite Spalte schreiben wir den Anfangszeitpunkt und 
			 * in die letzte Zeile schreiben wir den Endzeitpunkt.In der Kopfzeile legen wir also entsprechende
			 * Überschriften ab.
			 */
			headline.addColumn(new Column("Nutzerabonnements"));
			headline.addColumn(new Column("Hashtagabonnements"));

			// Hinzufügen der Kopfzeile
			result.addRow(headline);

			/*
			 * Nun werden sämtliche Daten der Abonnements ausgelesen und in die Tabelle eingetragen.
			 */
			ArrayList<Abonnement> abonnements = AbonnementMapper.abonnementMapper().findAllAbonnements();
			
			
			for (Abonnement abonnement : abonnements) {
				// Eine leere Zeile anlegen.
				Row accountRow = new Row();

				// Erste Spalte: Abonnement ID hinzufügen
				accountRow.addColumn(new Column(String.valueOf(abonnement.getId())));

				// Zweite Spalte: Abonutzer hinzufügen
				//raus gemacht accountRow.addColumn(new Column(String.valueOf(abonnement.getAboNutzer())));
	
				//Dritte Spalte: Abohashtag hinzfügen
				// raus gemacht accountRow.addColumn(new Column(String.valueOf(abonnement.getAboHashtag())));
	
				// und schließlich die Zeile dem Report hinzufügen.
				result.addRow(accountRow);
			}

			/**
			 * Übergebe den erstellten Report dem HTMLReportWriter um HTML zu erzeugen
			 */
			HTMLReportWriter writer = new HTMLReportWriter();
			writer.process1(result);
    
			/**
			 * Zum Schluss müssen wir noch den fertigen HTML-Report zurückgeben.
			 */
    return writer.getReportText();
}
  
/**
* Erstellen von <code>InfosVonAllenNachrichten</code>-Objekten.
* 
* @return der fertige Report
*/
public String erstelleInfosVonAllenNachrichtenReport(Nutzer nutzer, Unterhaltung unterhaltung, Nachricht n, String von, String bis) 
		throws IllegalArgumentException {
	
		if (this.getMessagingAdministration() == null)
			return null;

		/*
		 * Zunächst legen wir uns einen leeren Report an.
		 */
		InfosVonAllenNachrichtenReport result = new InfosVonAllenNachrichtenReport();
		
		// Jeder Report hat einen Titel (Bezeichnung / überschrift).
		result.setTitle("Alle Nachrichten");


		/*
		 * Datum der Erstellung hinzufügen. new Date() erzeugt autom. einen
		 * "Timestamp" des Zeitpunkts der Instantiierung des Date-Objekts.
		 */
		result.setCreated(new Date());

		/*
	     * Ab hier erfolgt die Zusammenstellung der Kopfdaten (die Dinge, die oben
	     * auf dem Report stehen) des Reports. Die Kopfdaten sind mehrzeilig, daher
	     * die Verwendung von CompositeParagraph.
	     */
	    CompositeParagraph header = new CompositeParagraph();

	    
	    // Zeitraum der Report Suchanfrage in die Kopfzeile hinzufügen
	     
	    header.addSubParagraph(new SimpleParagraph("Zeitraum Von: " + von + " Bis: "
	        + bis));

	    // Name und Vorname des Nutzers aufnehmen
	    header.addSubParagraph(new SimpleParagraph(nutzer.getVorname() + ", "
	        + nutzer.getNachname()));

	    // Email-Adresse aufnehmen
	    header.addSubParagraph(new SimpleParagraph("Email:" + nutzer.getEmail()));
	    
	    // Den Zeitraum aufnehmen
	 	header.addSubParagraph(new SimpleParagraph("Zeitraum Von: " + bis + " Bis: "
	 		        + bis));

	    // Hinzufügen der zusammengestellten Kopfdaten zu dem Report
	    result.setHeaderData(header);

	    /**
	     * Zunächst legen wir eine Kopfzeile für die Nachrichten-Tabelle an.
	     */
	    Row headline = new Row();

	    /**
	     * Wir wollen Zeilen mit 3 Spalten in der Tabelle erzeugen. In die erste
	     * Spalte schreiben wir die den jeweiligen Unterhaltungszeitraum (Erstellungszeitraum). 
	     * Die zweite den Nachrichtenabsender und in der dritten Spalte dann den Nachrichtenempfänger.
	     * In der Kopfzeile legen wir also entsprechende überschriften ab.
	     */
	    
	    headline.addColumn(new Column("Unterhaltungszeitraum"));
	    headline.addColumn(new Column("Nachrichtenabsender"));
	    headline.addColumn(new Column("Nachrichtenempfänger"));
	    
	  
	    // Hinzufügen der Kopfzeile
	    result.addRow(headline);   
	    
	    Row accountRow = new Row();
	    

/*
 * Nun müssen sämtliche Nachrichten-Objekte ausgelesen werden. Anschließend wir
 * für jedes Nachrichtenobjekt r ein Aufruf von InfosVonAllenNachrichten durchgeführt und somit jeweils ein
 * InfosVonAllenNachrichten-Objekt erzeugt. Diese Objekte werden sukzessive der result-Variable hinzugefügt. 
 * Sie ist vom Typ InfosVonAllenNachrichten Report, welches eine Subklasse von CompositeReport ist.
 */
		//ArrayList<Unterhaltung> alleNachrichten = UnterhaltungMapper.unterhaltungMapper().getUnterhaltungsZeitraum(anfangszeitpunkt, endzeitpunkt);
	    ArrayList<Nachricht> alleNachrichten = NachrichtMapper.nachrichtMapper().alleNachrichtenJeZeitraum(von, bis);

		//for (Unterhaltung u : alleNachrichten) {
	    for (Nachricht nachricht : alleNachrichten) {

			// Erste Spalte: Unterhaltungs ID hinzufügen
			accountRow.addColumn(new Column(String.valueOf(unterhaltung.getId())));

			// Zweite Spalte: Nachrichtenabsender hinzufügen
			// raus gemacht accountRow.addColumn(new Column(String.valueOf(unterhaltung.getSender())));

			//Dritte Spalte: Nachrichtenempfänger hinzfügen
			// raus gemacht accountRow.addColumn(new Column(String.valueOf(unterhaltung.getReceiver())));

			// Vierte Spalte Nachrichten ID hinzufügen
			accountRow.addColumn(new Column(String.valueOf(nachricht.getId())));

		
			// und schließlich die Zeile dem Report hinzufügen.
			result.addRow(accountRow);
		}

		/**
		 * Übergebe den erstellten Report dem HTMLReportWriter um HTML zu erzeugen
		 */
		HTMLReportWriter writer = new HTMLReportWriter();
		writer.process2(result);

		/**
		 * Zum Schluss müssen wir noch den fertigen HTML-Report zurückgeben.
		 */
		return writer.getReportText();
	}
}

