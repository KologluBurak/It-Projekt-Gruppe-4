package de.hdm.itProjektGruppe4.shared.bo;

/**
* Eine Klasse Unterhaltungsliste, die aus der Klasse BusinessObject erbt.
* @author Yücel
* @author Kologlu
*
*/


public class Unterhaltungsliste extends BusinessObject {

	/**
	 * Eindeutige SerialVersion Id. Wird zum Serialisieren der Klasse
	 * benötigt.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Fremdschlüsselbeziehung zur Klasse Unterhaltung
	 */
	private int unterhaltundID;
	
	/**
	 * Fremdschlüsselbeziehung zur Klasse Nutzer
	 */
	private int absenderID;
	
	/**
	 * Fremdschlüsselbeziehung zur Klasse Nutzer
	 */
	private int empfaengerID;

	/**
	 * Ausgabe der UnterhaltungID
	 * @return unterhaltungID
	 */
	public int getUnterhaltundID() {
		return unterhaltundID;
	}

	/**
	 * Setzen der UnterhaltungID
	 * @param unterhaltundID
	 */
	public void setUnterhaltundID(int unterhaltundID) {
		this.unterhaltundID = unterhaltundID;
	}

	/**
	 * Ausgabe der AbsenderID
	 * @return absenderID
	 */
	public int getAbsenderID() {
		return absenderID;
	}

	/**
	 * Setzen der AbsenderID
	 * @param absenderID
	 */
	public void setAbsenderID(int absenderID) {
		this.absenderID = absenderID;
	}

	/**
	 * Ausgabe der EmpfaengerID
	 * @return empfaengerID
	 */
	public int getEmpfaengerID() {
		return empfaengerID;
	}

	/**
	 * Setzen der EmpfaengerID
	 * @param empfaengerID
	 */
	public void setEmpfaengerID(int empfaengerID) {
		this.empfaengerID = empfaengerID;
	}
	
	
}
