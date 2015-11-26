package de.hdm.itProjektGruppe4.shared.bo;

/**
 *@author Yücel
 *@author Oikonomou
 */

public class Hashtagabonnement extends BusinessObject {
	
	private static final long serialVersionUID = 1L;

	private String name;
	private Hashtag hashtag;
	
	//Methodenkörper
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Hashtag getHashtag() {
		return hashtag;
	}

	public void setHashtag(Hashtag hashtag) {
		this.hashtag = hashtag;
	}

}
