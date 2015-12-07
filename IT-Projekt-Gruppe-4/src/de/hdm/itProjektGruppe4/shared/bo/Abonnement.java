package de.hdm.itProjektGruppe4.shared.bo;

/**
 *@author Kologlu
 *@author Yücel
 *@author Oikonomou
 */

public class Abonnement extends BusinessObject {
	
	private static final long serialVersionUID = 1L;


	private Nutzerabonnement aboNutzer;
	private Hashtagabonnement aboHashtag;
	private Hashtag abonniertesHashtag;
	private Nutzer abonnent;
	private Nutzer abonnierterNutzer;
	
	public Nutzerabonnement getAboNutzer() {
		return aboNutzer;
	}
	
	public void setAboNutzer(Nutzerabonnement aboNutzer) {
		this.aboNutzer = aboNutzer;
	}
	
	public Hashtagabonnement getAboHashtag() {
		return aboHashtag;
	}
	
	public void setAboHashtag(Hashtagabonnement aboHashtag) {
		this.aboHashtag = aboHashtag;
	}

	public Hashtag getAbonniertesHashtag() {
		return abonniertesHashtag;
	}
	public void setAbonniertesHashtag(Hashtag abonniertesHashtag) {
		this.abonniertesHashtag = abonniertesHashtag;
	}
	public Nutzer getAbonnent() {
		return abonnent;
	}

	public void setAbonnent(Nutzer abonnent) {
		this.abonnent = abonnent;
	}

	public Nutzer getAbonnierterNutzer() {
		return abonnierterNutzer;
	}

	public void setAbonnierterNutzer(Nutzer abonnierterNutzer) {
		this.abonnierterNutzer = abonnierterNutzer;
	}
	
}
