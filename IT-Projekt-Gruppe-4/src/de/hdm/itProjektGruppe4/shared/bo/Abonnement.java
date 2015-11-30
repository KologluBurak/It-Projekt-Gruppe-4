package de.hdm.itProjektGruppe4.shared.bo;

/**
 *
 *@author Yücel
 *@author Oikonomou
 */

public class Abonnement extends BusinessObject {
	
	private static final long serialVersionUID = 1L;

	
////	private int id;
////	private String nutzerabo, hashtagabo;
//	private Hashtag hashtag;
//	private Nutzer abonnent;
//	private Nutzer abonnierterNutzer;
	private Nutzerabonnement aboNutzer;
	private Hashtagabonnement aboHashtag;
	
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
	
	
////	public int getId() {
////		return id;
////	}
////
////	
////	public void setId(int id) {
////		this.id = id;
////	}
////
////
////	public String getNutzerabo() {
////		return nutzerabo;
////	}
////
////
////	public void setNutzerabo(String nutzerabo) {
////		this.nutzerabo = nutzerabo;
////	}
////
////
////	public String getHashtagabo() {
////		return hashtagabo;
////	}
//
//
//	public void setHashtagabo(String hashtagabo) {
//		this.hashtagabo = hashtagabo;
//	}
//	public Nutzer getAbonnent() {
//		return abonnent;
//	}
//
//
//	public void setAbonnent(Nutzer abonnent) {
//		this.abonnent = abonnent;
//	}
//
//
//	public Nutzer getAbonnierterNutzer() {
//		return abonnierterNutzer;
//	}
//
//
//	public void setAbonnierterNutzer(Nutzer abonnierterNutzer) {
//		this.abonnierterNutzer = abonnierterNutzer;
//	}
//	
//	public Hashtag getHashtag() {
//		return hashtag;
//	}
//
//	public void setHashtag(Hashtag hashtag) {
//		this.hashtag = hashtag;
//	}


}
