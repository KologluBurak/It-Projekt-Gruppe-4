package de.hdm.itProjektGruppe4.shared.bo;

/**
 * Eine Klasse Hashtagabonnement, die aus der Klasse Abonnement erbt.
 * @author Yücel
 * @author Oikonomou
 * @author Kologlu
 */

public class Hashtagabonnement extends Abonnement {

	/**
	 * Eindeutige SerialVersion Id. Wird zum Serialisieren der Klasse
	 * benötigt.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Fremschluesselbeziehung zur Klasse Hashtag
	 */
	private int hashtagID = 0;

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
	public int getHashtagID() {
		return hashtagID;
	}

	/**
	 * Setzen der ID des Hashtags
	 * 
	 * @param hastagID
	 */
	public void setHashtagID(int hashtagID) {
		this.hashtagID = hashtagID;
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
