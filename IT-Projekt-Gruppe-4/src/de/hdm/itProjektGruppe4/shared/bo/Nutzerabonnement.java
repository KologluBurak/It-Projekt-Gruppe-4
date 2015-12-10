package de.hdm.itProjektGruppe4.shared.bo;

/**
 * @author YÃ¼cel
 * @author Oikonomou
 * @author Kologlu
 */

public class Nutzerabonnement extends BusinessObject {

	/**
	 * Eindeutige SerialVersion Id. Wird zum Serialisieren der Klasse
	 * benï¿½tigt.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Fremdschlüsselbeziehung zu abonnierten Nutzer
	 */
	private int followerID = 0;

	/**
	 * Fremdschlüsselbeziehung zu abonnierenden Nutzer
	 */
	private int derBeobachteteID = 0;

	/**
	 * Fremdschluesselbeziehung zur abstrakten Klasse Abonnement
	 */
	private int abonnementID = 0;

	/**
	 * Ausgabe der Follower ID
	 * 
	 * @return followerID
	 */
	public int getFollowerID() {
		return followerID;
	}

	/**
	 * Setzen der Follower ID
	 * 
	 * @param followerID
	 */
	public void setFollowerID(int followerID) {
		this.followerID = followerID;
	}

	/**
	 * Ausgabe des Beobachteten ID
	 * 
	 * @return derBeobachteteID
	 */
	public int getDerBeobachteteID() {
		return derBeobachteteID;
	}

	/**
	 * Setzen des Beobachteten ID
	 * 
	 * @param derBeobachteteID
	 */
	public void setDerBeobachteteID(int derBeobachteteID) {
		this.derBeobachteteID = derBeobachteteID;
	}

	/**
	 * Ausgabe der Abonnement ID
	 * 
	 * @return abonnementID
	 */
	public int getAbonnementID() {
		return abonnementID;
	}

	/**
	 * Setzen der Abonnement ID
	 * 
	 * @param abonnementID
	 */
	public void setAbonnementID(int abonnementID) {
		this.abonnementID = abonnementID;
	}
}
