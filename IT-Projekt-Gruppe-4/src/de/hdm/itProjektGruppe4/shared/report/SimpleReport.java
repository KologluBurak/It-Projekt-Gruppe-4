package de.hdm.itProjektGruppe4.shared.report;

import java.util.ArrayList;
import de.hdm.itProjektGruppe4.shared.report.Column;
import de.hdm.itProjektGruppe4.shared.report.Report;
import de.hdm.itProjektGruppe4.shared.report.Row;

/**
 * <p>
 * Ein einfacher Report, der neben den Informationen der Superklasse <code>
 * Report</code> eine Tabelle mit "Positionsdaten" aufweist. Die Tabelle greift
 * auf zwei Hilfsklassen namens <code>Row</code> und <code>Column</code> zurück.
 * </p>
 * <p>
 * Die Positionsdaten sind vergleichbar mit der Liste der Bestellpositionen
 * eines Bestellscheins. Dort werden in eine Tabelle zeilenweise Eintragung z.B.
 * bzgl. Artikelnummer, Artikelbezeichnung, Menge, Preis vorgenommen.
 * </p>
 * 
 * @see Row
 * @see Column
 * @author Thies
 * @author Yücel
 */
public abstract class SimpleReport extends Report {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  /**
   * Tabelle mit Positionsdaten. Die Tabelle wird zeilenweise in diesem
   * <code>ArrayList</code> abgelegt.
   */
  private ArrayList<Row> table = new ArrayList<Row>();

  /**
   * Hinzufügen einer Zeile.
   * 
   * @param r die hinzuzufügende Zeile
   */
  public void addRow(Row r) {
    this.table.add(r);
  }

  /**
   * Entfernen einer Zeile.
   * 
   * @param r die zu entfernende Zeile.
   */
  public void removeRow(Row r) {
    this.table.remove(r);
  }

  /**
   * Auslesen sämtlicher Positionsdaten.
   * 
   * @return die Tabelle der Positionsdaten
   */
  public ArrayList<Row> getRows() {
    return this.table;
  }
}