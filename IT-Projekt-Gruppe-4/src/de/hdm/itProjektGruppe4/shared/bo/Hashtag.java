package de.hdm.itProjektGruppe4.shared.bo;

/**
 * @author Yücel, Nguyen, Oikonomou, Kologlu
 *
 */

public class Hashtag extends BusinessObject {

	/**
	 * Eindeutige SerialVersion Id. Wird zum Serialisieren der Klasse
	 * ben�tigt.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Name/Bezeichnung des Hashtags
	 */
	private String bezeichnung;

	/**
	 * Ausgabe der Bezeichnung
	 * 
	 * @return bezeichnung
	 */
	public String getBezeichnung() {
		return bezeichnung;
	}

	/**
	 * Setzen der Bezeichnung
	 * 
	 * @param bezeichnung
	 */
	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}
}
