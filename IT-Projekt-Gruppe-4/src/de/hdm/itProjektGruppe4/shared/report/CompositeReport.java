
package de.hdm.itProjektGruppe4.shared.report;

import java.io.Serializable;
import java.util.ArrayList;


/**
* Ein zusammengesetzter Report. Dieser Report kann aus einer Menge von 
* Teil-Reports (vgl. Attribut <code>subReports</code>) bestehen.
* @author Yücel
* @author thies
*/

public abstract class CompositeReport extends Report implements Serializable{
		
	private static final long serialVersionUID = 1L;
		
	/**
	 * Die Menge der Teil-Reports.
	 */
	private ArrayList<Report> subReports = new ArrayList<Report>();
		
	/**
	 * Hinzufügen eines Teil-Reports.
	 * @param r der hinzuzufügende Teil-Report.
		*/
		
	public void addSubReport(Report r) {
		this.subReports.add(r);
	}

	/**
	 * Entfernen eines Teil-Reports.
	 * @param r der zu entfernende Teil-Report.
	 */
	public void removeSubReport(Report r) {
		this.subReports.remove(r);
	}

	/**
	 * Auslesen der Anzahl von Teil-Reports.
	 * @return int Anzahl der Teil-Reports.
	 */
	public int getNumSubReports() {
		return this.subReports.size();
	}

	/**
	 * Auslesen eines einzelnen Teil-Reports.
	 * @param i Position des Teilreports. Bei n Elementen läuft der Index i von 0
	 * bis n-1.
	 * 
	 * @return Position des Teil-Reports.
	 */	
	public Report getSubReportAt(int i) {
		return this.subReports.get(i);
	}
}
