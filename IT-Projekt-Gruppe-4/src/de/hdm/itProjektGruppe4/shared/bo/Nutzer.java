package de.hdm.itProjektGruppe4.shared.bo;

import java.util.ArrayList;

/**
 * @author Yücel, Nguyen, Oikonomou, kologlu
 */

public class Nutzer extends BusinessObject {

	/**
	 * Eindeutige SerialVersion Id. Wird zum Serialisieren der Klasse
	 * ben�tigt.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Vorname des Nutzers
	 */
	private String vorname;

	/**
	 * Nachname des Nutzers
	 */
	private String nachname;

	/**
	 * E-Mail-Adresse der Nutzers
	 */
	private String email;

	/**
	 * Nickname der Nutzers
	 */
	private String nickname;

	private ArrayList<Abonnement> abo = new ArrayList<Abonnement>();

	/**
	 * Setter- und getter
	 */
	public String getVorname() {
		return vorname;
	}

	public ArrayList<Abonnement> getAbo() {
		return abo;
	}

	public void setAbo(ArrayList<Abonnement> abo) {
		this.abo = abo;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * Erzeugen einer einfachen textuellen Darstellung der jeweiligen Instanz.
	 * Diese besteht aus dem Text, der durch die <code>toString()</code>-Methode
	 * der Superklasse erzeugt wird, erg�nzt durch den Vor- und Nachnamen des
	 * jeweiligen Kunden.
	 */
	@Override
	public String toString() {
		return super.toString() + " " + this.nickname + " " + this.vorname + " " + this.nachname + " " + this.email
				+ " ";
	}
}
