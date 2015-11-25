package de.hdm.itProjektGruppe4.shared.bo;

/**
 *@author Yücel
 *@author Oikonomou
 */

public class Abonnement extends BusinessObject {
	
	private static final long serialVersionUID = 1L;

	private String aboArt;
	private int nutzerAboId, hashtagAboId, hashtagAbo, nutzerAbo;
	private Nutzer nutzer;
	private Hashtag hashtag;
	


	public String getAboArt() {
		return aboArt;
	}
	
	public void setAboArt(String aboArt) {
		this.aboArt = aboArt;
	}
	
	public int getNutzerAboId() {
		return nutzerAboId;
	}

	public void setNutzerAboId(int nutzerAboId) {
		this.nutzerAboId = nutzerAboId;
	}

	public int getHashtagAboId() {
		return hashtagAboId;
	}

	public void setHashtagAboId(int hashtagAboId) {
		this.hashtagAboId = hashtagAboId;

	}
	
	public int getHashtagAbo() {
		return hashtagAbo;
	}

	public void setHashtagAbo(int hashtagAbo) {
		this.hashtagAbo = hashtagAbo;
	}

	public int getNutzerAbo() {
		return nutzerAbo;
	}

	public void setNutzerAbo(int nutzerAbo) {
		this.nutzerAbo = nutzerAbo;
	}
	
	public Nutzer getNutzer() {
		return nutzer;
	}

	public void setNutzer(Nutzer nutzer) {
		this.nutzer = nutzer;
	}

	public Hashtag getHashtag() {
		return hashtag;
	}

	public void setHashtag(Hashtag hashtag) {
		this.hashtag = hashtag;
	}

}
