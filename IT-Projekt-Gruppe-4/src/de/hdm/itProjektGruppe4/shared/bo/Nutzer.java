package de.hdm.itProjektGruppe4.shared.bo;

/**
 * @author YÃ¼cel, Nguyen, Oikonomou
 */

public class Nutzer extends BusinessObject {
	
	/**
	 * Eindeutige SerialVersion Id. Wird zum Serialisieren der Klasse benï¿½tigt.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Die UserId des Benutzers
	 */
	private String userId;
	
	/**
	 * Der Username des Benutzers.
	 */
	private String userName;
	
	/**
	 * Die E-Mail-Adresse des Benutzers.
	 */
	private String email;
	
	/**
	 * Das Passwort des Benutzers
	 */
	private String passwort;
	
	/**
	 * Anmelde-Id um den Login per Google durchzufï¿½hren
	 */
	private String googleId;
	
	/**
	 * Prüft ob der Benutzer angemeldet ist
	 */
	private boolean istAngemeldet;

	/**
	 * Auslesen der UserId des Benutzers
	 */
	public String getUserId() {
		return userId;
	}
	
	/**
	 * Setzen der UserID des Benutzers
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * Auslesen des Benutzernamens des Users
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Setzen des Benutzernamens des Users
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	/**
	 * Auslesen der Email des Benutzers
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Setzen der E-Mail-Adresse
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Auslesen des Passworts
	 */
	public String getPasswort() {
		return passwort;
	}

	/**
	 * Setzen des Passworts
	 */
	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}

	/**
	 * Auslesen der Id vom Google-Account
	 */
	public String getGoogleId() {
		return googleId;
	}

	/**
	 * Setzen der Id vom Google-Account
	 */
	public void setGoogleId(String googleId) {
		this.googleId = googleId;
	}

	/**
	 * Prüft ob der Nutzer angemeldet ist
	 */
	public boolean istAngemeldet() {
		return istAngemeldet;
	}

	/**
	 * Setzen der Anmeldestatus
	 */
	public void setIstAngemeldet(boolean istAngemeldet) {
		this.istAngemeldet = istAngemeldet;
	}

	/**
	 * Erzeugen einer einfachen textuellen Darstellung der jeweiligen Instanz.
	 * Diese besteht aus dem Text, der durch die <code>toString()</code>-Methode
	 * der Superklasse erzeugt wird, ergï¿½nzt durch den Vor- und Nachnamen des
	 * jeweiligen Kunden.
	 */
	@Override
	public String toString() {
		return super.toString() + " " + this.userName;
	}
}
