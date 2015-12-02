package de.hdm.itProjektGruppe4.server.report;

import java.util.ArrayList;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.itProjektGruppe4.server.db.*;
import de.hdm.itProjektGruppe4.server.MessangingAdministrationImpl;
import de.hdm.itProjektGruppe4.shared.InfosVonAbonnementsReport;
import de.hdm.itProjektGruppe4.shared.InfosVonAllenAbonnementsReport;
import de.hdm.itProjektGruppe4.shared.InfosVonNachrichtenReport;
import de.hdm.itProjektGruppe4.shared.ReportGenerator;
import de.hdm.itProjektGruppe4.shared.bo.*;
import java.sql.Timestamp;


public class ReportGeneratorImpl extends RemoteServiceServlet
implements ReportGenerator {

/**

* Ein ReportGenerator benötigt Zugriff auf die BankAdministration, da diese die
* essentiellen Methoden für die Koexistenz von Datenobjekten (vgl.
* bo-Package) bietet.
*/
protected MessagingAdministration messaging = null;

/**
* <p>
* Ein <code>RemoteServiceServlet</code> wird unter GWT mittels
* <code>GWT.create(Klassenname.class)</code> Client-seitig erzeugt. Hierzu
* ist ein solcher No-Argument-Konstruktor anzulegen. Ein Aufruf eines anderen
* Konstruktors ist durch die Client-seitige Instantiierung durch
* <code>GWT.create(Klassenname.class)</code> nach derzeitigem Stand nicht
* m√∂glich.
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
 * Ein ReportGeneratorImpl-Objekt instantiiert f√ºr seinen Eigenbedarf eine
 * BankVerwaltungImpl-Instanz.
 */
MessagingAdministrationImpl messaging = new MessagingAdministrationImpl();
messaging.init();
this.messaging = messaging;
}

/**
* Auslesen der zugeh√∂rigen PinnwandVerwaltung (interner Gebrauch).
* 
* @return das PinnwandVerwaltungServiceobjekt
*/
protected MessagingAdministration getMessagingAdministration() {
return this.messaging;
}

/**
* Erstellen von <code>InfosVonUserReport</code>-Objekten.
* 
* @param user das Userobjekt bzgl. dessen der Report erstellt werden soll.
* @return der fertige Report
*/
public abstract InfosVonAbonnementsReport erstelleInfosVonAbonnementsReport(Abonnement abonnement, Timestamp anfangszeitpunkt, Timestamp endzeitpunkt) 
		throws IllegalArgumentException; {

if (this.getMessagingAdministration() == null)
  return null;

/*
 * Zunächst legen wir uns einen leeren Report an.
 */
InfosVonAbonnementsReport result = new InfosVonAbonnementsReport();

// Jeder Report hat einen Titel (Bezeichnung / √úberschrift).
result.setTitle("Infos von Abonnement");

/*
 * Datum der Erstellung hinzuf√ºgen. new Date() erzeugt autom. einen
 * "Timestamp" des Zeitpunkts der Instantiierung des Date-Objekts.
 */
result.setCreated(new Date());

/*
 * Ab hier erfolgt die Zusammenstellung der Kopfdaten (die Dinge, die oben
 * auf dem Report stehen) des Reports. Die Kopfdaten sind mehrzeilig, daher
 * die Verwendung von CompositeParagraph.
 */
CompositeParagraph header = new CompositeParagraph();

// Name und Vorname des Users aufnehmen
header.addSubParagraph(new SimpleParagraph(Nutzer.getNachname() + ", "
    + user.getVorname()));

// Anfangszeitpunkt aufnehmen
header.addSubParagraph(new SimpleParagraph("Anfangszeitpunkt: " + result.getAnfangszeitpunkt()));


// Endzeitpunkt aufnehmen
header.addSubParagraph(new SimpleParagraph("Endzeitpunkt: " + result.getEndzeitpunkt()));


// Hinzuf√ºgen der zusammengestellten Kopfdaten zu dem Report
result.setHeaderData(header);

/*
 * Ab hier erfolgt ein zeilenweises Hinzuf√ºgen von Konto-Informationen.
 */

/*
 * Zun√§chst legen wir eine Kopfzeile f√ºr die Kundeninfos-Tabelle an.
 */
Row headline = new Row();

/*
 * Wir wollen Zeilen mit 3 Spalten in der Tabelle erzeugen. In die erste
 * Spalte schreiben wir den jeweiligen User, in die zweite Spalte schreiben wir den Anfangszeitpunkt und 
 * in die letzte Zeile schreiben wir den Endzeitpunkt.
 *  In der Kopfzeile legen wir also entsprechende
 * √úberschriften ab.
 */
headline.addColumn(new Column("Nutzerabonnements"));
headline.addColumn(new Column("Hashtagabonnements"));
headline.addColumn(new Column("Nutzerspezische Nachrichten"));
headline.addColumn(new Column("Zeitraumspezifische Nachrichten"));

// Hinzuf√ºgen der Kopfzeile
result.addRow(headline);

/*
 * Nun werden s√§mtliche Daten der User ausgelesen und deren Anfangszeitpunkt und
 * Endzeitpunkt in die Tabelle eingetragen.
 */
ArrayList<Abonnement> abonnement = this.messaging.erstelleInfosVonAbonnementsReport(abonnement1);

for (Abonnement abonnement : abonnement) {
  // Eine leere Zeile anlegen.
  Row accountRow = new Row();

  // Erste Spalte: Kontonummer hinzuf√ºgen
  accountRow.addColumn(new Column(String.valueOf(abonnement.getId())));

  // Zweite Spalte: Anfangszeitpunkt hinzuf¸gen
  accountRow.addColumn(new Column(String.valueOf(this.InfosVonAbonnements
      .getAnfangszeitpunkt(abonnement))));

  // und schlie√ülich die Zeile dem Report hinzuf√ºgen.
  result.addRow(accountRow);
}

/*
 * Zum Schluss m√ºssen wir noch den fertigen Report zur√ºckgeben.
 */
return result;
}

/**
* Erstellen von <code>AllAccountsOfAllCustomersReport</code>-Objekten.
* 
* @return der fertige Report
*/
public abstract InfosVonAllenAbonnementsReport erstelleInfosVonAllenAbonnementsReport(Timestamp anfangszeitpunkt, Timestamp endzeitpunkt)
		throws IllegalArgumentException; {

if (this.getMessaging() == null)
  return null;

/*
 * Zun√§chst legen wir uns einen leeren Report an.
 */
InfosVonAllenAbonnementsReport result = new InfosVonAllenAbonnementsReport();

// Jeder Report hat einen Titel (Bezeichnung / √ºberschrift).
result.setTitle("Alle Abonnements aller Nutzer");

// Imressum hinzuf√ºgen
this.addImprint(result);

/*
 * Datum der Erstellung hinzuf√ºgen. new Date() erzeugt autom. einen
 * "Timestamp" des Zeitpunkts der Instantiierung des Date-Objekts.
 */
result.setCreated(new Date());

/*
 * Da AllAccountsOfAllCustomersReport-Objekte aus einer Sammlung von
 * AllAccountsOfCustomerReport-Objekten besteht, ben√∂tigen wir keine
 * Kopfdaten f√ºr diesen Report-Typ. Wir geben einfach keine Kopfdaten an...
 */

/*
 * Nun m√ºssen s√§mtliche Kunden-Objekte ausgelesen werden. Anschlie√üend wir
 * f√ºr jedes Kundenobjekt c ein Aufruf von
 * createAllAccountsOfCustomerReport(c) durchgef√ºhrt und somit jeweils ein
 * AllAccountsOfCustomerReport-Objekt erzeugt. Diese Objekte werden
 * sukzessive der result-Variable hinzugef√ºgt. Sie ist vom Typ
 * AllAccountsOfAllCustomersReport, welches eine Subklasse von
 * CompositeReport ist.
 */
ArrayList<Abonnement> alleAbonnements = this.messaging.getAlleAbonnements();

for (Abonnement abonnement : alleAbonnements) {
  /*
   * Anlegen des jew. Teil-Reports und Hinzuf√ºgen zum Gesamt-Report.
   */
  result.addSubReport(this.erstelleInfosVonAllenAbonnementsReport(abonnement));
}

/*
 * Zu guter Letzt m√ºssen wir noch den fertigen Report zur√ºckgeben.
 */
return result;
}

}
