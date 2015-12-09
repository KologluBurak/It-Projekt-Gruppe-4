package de.hdm.itProjektGruppe4.shared.bo;

import java.util.ArrayList;

/**
 * @author Yücel
 * @author Oikonomou
 * @author Kologlu
 */

public class Nutzerabonnement extends BusinessObject {

	private static final long serialVersionUID = 1L;

	private Nutzer nutzer;
	/**
	 * Fremdschlüsselbeziehung zum Nutzer der Abonniert.
	 */
	private int aboNutzerId;

	/**
	 * Fremdschlüsselbeziehung zum Nutzer der Beobachtet werden.
	 */
	private int derBeobachteteId;

	/**
	 * Getter und Setter.
	 */
	public int getAboNutzerId() {
		return aboNutzerId;
	}

	public Nutzer getNutzer() {
		return nutzer;
	}

	public void setNutzer(Nutzer nutzer) {
		this.nutzer = nutzer;
	}

	public void setAboNutzerId(int aboNutzerId) {
		this.aboNutzerId = aboNutzerId;
	}

	public int getDerBeobachteteId() {
		return derBeobachteteId;
	}

	public void setDerBeobachteteId(int derBeobachteteId) {
		this.derBeobachteteId = derBeobachteteId;
	}

}
