package de.hdm.itProjektGruppe4.shared.bo;

import java.util.ArrayList;

/** 
 * Eine Klasse Nachricht, die aus der Klasse BusinessObject erbt.
 * @author Yücel, Nguyen, Oikonomou, Kologlu
 * 
 */

public class Nachricht extends BusinessObject {

	/**
	 * Eindeutige SerialVersion Id. Wird zum Serialisieren der Klasse
	 * benötigt.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Textinhalt der Nachricht
	 */
	private String text;

	/**
	 * Fremdschlüsselbeziehung zur Klasse Nutzer
	 */
	private int nutzerID;
	
	/**
	 * Fremdschlüsselbeziehung zur Klasse Unterhaltung
	 */
	private int unterhaltungID;
	
	/**
	 * Absender einer Nachricht
	 */
	private Nutzer absender;
	
	/**
	 * Ausgabe der NutzerID
	 * @return nutzerID
	 */
	public int getNutzerID() {
		return nutzerID;
	}

	/**
	 * Setzen der NutzerID
	 * @param nutzerID
	 */
	public void setNutzerID(int nutzerID) {
		this.nutzerID = nutzerID;
	}

	/**
	 * Ausgabe der UnterhaltungsID
	 * @return unterhaltungID
	 */
	public int getUnterhaltungID() {
		return unterhaltungID;
	}

	/**
	 * Setzen der UnterhaltungsID
	 * @param unterhaltungID
	 */
	public void setUnterhaltungID(int unterhaltungID) {
		this.unterhaltungID = unterhaltungID;
	}

	/**
	 * Empfaenger einer Nachricht
	 */
	private ArrayList<Nutzer> empfaenger;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	/**
	 * Ausgabe des Absenders
	 * 
	 * @return absender
	 */
	public Nutzer getAbsender() {
		return absender;
	}

	/**
	 * Setzen des Absenders
	 * 
	 * @param absender
	 */
	public void setAbsender(Nutzer absender) {
		this.absender = absender;
	}

	/**
	 * Ausgabe der Empfaenger als Liste vom Typ ArrayList<>
	 * @return empfaenger
	 */
	public ArrayList<Nutzer> getEmpfaenger() {
		ArrayList<Nutzer> allEmpfaenger = new ArrayList<Nutzer>();
		for (Nutzer nutzer : this.empfaenger) {
			allEmpfaenger.add(nutzer);
		}
		return empfaenger;
	}

	/**
	 * Setzen der Empfaenger in Listen, falls an mehrere Nutzer gesendet wird,
	 * vom Typ Nutzer
	 * @param empfaenger
	 */
	public void setEmpfaenger(Nutzer empfaenger) {
		if (empfaenger != null) {
			this.empfaenger.add(empfaenger);
		}
	}
}
