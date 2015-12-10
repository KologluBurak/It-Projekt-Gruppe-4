package de.hdm.itProjektGruppe4.shared.bo;

import java.util.Date;

/**
 * 
 * @author Yuecel, Nguyen, Kologlu
 *
 */

public class Unterhaltung extends BusinessObject{
	
	/**
	 * Eindeutige SerialVersion Id. Wird zum Serialisieren der Klasse
	 * ben�tigt.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Referenz Nachricht die hinzugefuegt werden soll
	 */
	private String refNachricht;

	/**
	 * Gibt an, wann das letzte Mal in die jeweilige Unterhaltung
	 * geschrieben wurde
	 */
	private Date zuletztBearbeitet;

	/**
	 * Ausgabe der zuletzt bearbeiteten Zeit
	 * @return zuletztBearbeitet
	 */
	public Date getZuletztBearbeitet() {
		return zuletztBearbeitet;
	}

	/**
	 * Setzen der zuletzt bearbeiteten Zeit
	 * @param zuletztBearbeitet
	 */
	public void setZuletztBearbeitet(Date zuletztBearbeitet) {
		this.zuletztBearbeitet = zuletztBearbeitet;
	}

	/**
	 * Ausgabe der referenzierten Nachricht
	 * @return refNachricht
	 */
	public String getRefNachricht() {
		return refNachricht;
	}

	/**
	 * Setzen der referenzierten Nachricht
	 * @param refNachricht
	 */
	public void setRefNachricht(String refNachricht) {
		this.refNachricht = refNachricht;
	}
}
