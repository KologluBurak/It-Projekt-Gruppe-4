package de.hdm.itProjektGruppe4.shared.bo;

/**
 *
 * @author Yücel
 * @author Oikonomou
 * @author Kologlu
 */

public class Hashtagabonnement extends Abonnement {

	/**
	 * Eindeutige SerialVersion Id. Wird zum Serialisieren der Klasse
	 * ben�tigt.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Fremschluesselbeziehung zur Hashtag Klasse Eindeutige ID der Hashtags,
	 * die abonniert wurden
	 */
	private int hastagID = 0;

	/**
	 * Fremschluesselbeziehung zur abstrakten Klasse Abonnement
	 */
	private int abonnementID = 0;

	/**
	 * Fremdschluesselbeziehung zur Nutzer Klasse
	 */
	private int nutzerID = 0;

	/**
	 * Ausgabe der Hashtag ID
	 * 
	 * @return hashtagID
	 */
	public int getHastagID() {
		return hastagID;
	}

	/**
	 * Setzen der ID des Hashtags
	 * 
	 * @param hastagID
	 */
	public void setHastagID(int hastagID) {
		this.hastagID = hastagID;
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
	 * Setzen der ID des Abonnemnts
	 * 
	 * @param abonnementID
	 */
	public void setAbonnementID(int abonnementID) {
		this.abonnementID = abonnementID;
	}

	/**
	 * Ausgabe der Nutzer ID
	 * 
	 * @return nutzerID
	 */
	public int getNutzerID() {
		return nutzerID;
	}

	/**
	 * Setzen der ID des Nutzers
	 * 
	 * @param nutzerID
	 */
	public void setNutzerID(int nutzerID) {
		this.nutzerID = nutzerID;
	}
}
