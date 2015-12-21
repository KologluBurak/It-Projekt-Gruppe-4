package de.hdm.itProjektGruppe4.shared.bo;

import java.util.Date;

/**
 * Eine Klasse Unterhaltung, die aus der Klasse BusinessObject erbt.
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
	private String zuletztBearbeitet;

	/**
	 * Ausgabe der zuletzt bearbeiteten Zeit
	 * @return zuletztBearbeitet
	 */
	public String getZuletztBearbeitet() {
		return zuletztBearbeitet;
	}

	/**
	 * Setzen der zuletzt bearbeiteten Zeit
	 * @param datum
	 */
	public void setZuletztBearbeitet(String datum) {
		this.zuletztBearbeitet = datum;
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
	
	public void setSender(Nutzer n){
		
	}
	
	public void setReceiver(Nutzer n){
		
	}
}
