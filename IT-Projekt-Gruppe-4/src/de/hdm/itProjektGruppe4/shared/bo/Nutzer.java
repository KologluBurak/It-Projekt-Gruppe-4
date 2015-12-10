package de.hdm.itProjektGruppe4.shared.bo;

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

	/**
	 * Ausgabe des Vornamens
	 * 
	 * @return
	 */
	public String getVorname() {
		return vorname;
	}

	/**
	 * Setzen des Vornamens
	 * 
	 * @param vorname
	 */
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	/**
	 * Ausgabe des Nachnamens
	 * 
	 * @return
	 */
	public String getNachname() {
		return nachname;
	}

	/**
	 * Setzen des Nachnamens
	 * 
	 * @param nachname
	 */
	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	/**
	 * Ausgabe der E-Mail-Adresse
	 * 
	 * @return
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Setzen der E-Mail-Adresse
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Ausgabe des Nicknames
	 * 
	 * @return
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * Setzen des Nicknames
	 * 
	 * @param nickname
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * Erzeugen einer einfachen textuellen Darstellung der jeweiligen Instanz.
	 * Diese besteht aus dem Text, der durch die <code>toString()</code>-Methode
	 * der Superklasse erzeugt wird, erg�nzt durch den Vor- und Nachnamen, des
	 * Nicknamen und der E-Mail-Adresse des jeweiligen Nutzers.
	 */
	@Override
	public String toString() {
		return super.toString() + " " + this.nickname + " " + this.vorname + " " + this.nachname + " " + this.email
				+ " ";
	}
}
