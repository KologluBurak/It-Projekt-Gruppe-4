package de.hdm.itProjektGruppe4.shared.report;

import java.io.Serializable;
import java.util.ArrayList;
import de.hdm.itProjektGruppe4.shared.report.Paragraph;
import de.hdm.itProjektGruppe4.shared.report.SimpleParagraph;

/**
 * Diese Klasse stellt eine Menge einzelner Absätze (
 * <code>SimpleParagraph</code>-Objekte) dar. Diese werden als Unterabschnitte
 * in einem <code>ArrayList</code> abgelegt verwaltet.
 * 
 * @author Thies
 * @author Yücel
 */
public class CompositeParagraph extends Paragraph implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  /**
   * Speicherort der Unterabschnitte.
   */
  private ArrayList<SimpleParagraph> subParagraphs = new ArrayList<SimpleParagraph>();

  /**
   * Einen Unterabschnitt hinzufügen.
   * 
   * @param p der hinzuzufügende Unterabschnitt.
   */
  public void addSubParagraph(SimpleParagraph p) {
    this.subParagraphs.add(p);
  }
  /**
   * Einen Unterabschnitt entfernen.
   * 
   * @param p der zu entfernende Unterabschnitt.
   */
  public void removeSubParagraph(SimpleParagraph p) {
    this.subParagraphs.remove(p);
  }

  /**
   * Auslesen sämtlicher Unterabschnitte.
   * 
   * @return <code>ArrayList</code>, der sämtliche Unterabschnitte enthält.
   */
  public ArrayList<SimpleParagraph> getSubParagraphs() {
    return this.subParagraphs;
  }

  /**
   * Auslesen der Anzahl der Unterabschnitte.
   * 
   * @return Anzahl der Unterabschnitte
   */
  public int getNumParagraphs() {
    return this.subParagraphs.size();
  }

  /**
   * Auslesen eines einzelnen Unterabschnitts.
   * 
   * @param i der Index des gewünschten Unterabschnitts (0 <= i <n), mit n =
   *          Anzahl der Unterabschnitte.
   * 
   * @return der gewünschte Unterabschnitt.
   */
  public SimpleParagraph getParagraphAt(int i) {
    return this.subParagraphs.get(i);
  }

  /**
   * Umwandeln eines <code>CompositeParagraph</code> in einen
   * <code>String</code>.
   */
  public String toString() {
	    /*
	     * Wir legen einen leeren Buffer an, in den wir sukzessive sämtliche
	     * String-Repräsentationen der Unterabschnitte eintragen.
	     */
	    StringBuffer result = new StringBuffer();

	    // Schleife über alle Unterabschnitte
	    for (int i = 0; i < this.subParagraphs.size(); i++) {
	      SimpleParagraph p = this.subParagraphs.get(i);

	      /*
	       * den jew. Unterabschnitt in einen String wandeln und an den Buffer hängen.
	       */
	      result.append(p.toString() + "\n");
	    }

	    /*
	     * Schließlich wird der Buffer in einen String umgewandelt und
	     * zurückgegeben.
	     */
	   return result.toString();
	}
}