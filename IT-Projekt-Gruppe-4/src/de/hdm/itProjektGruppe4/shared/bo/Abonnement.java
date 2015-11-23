package de.hdm.itProjektGruppe4.shared.bo;

public class Abonnement extends BusinessObject {
	
	private static final long serialVersionUID = 1L;

	private static Abonnement aboArt;


	//Methodenkörper
	public void aboAnzeigen (){
	
}
	public void aboHinzufuegen () {
	
}
	public void aboEntfernen () {
	
}
	
	public static Abonnement getAboArt() {
	return aboArt;
}
	public static void setAboArt(Abonnement aboArt) {
	Abonnement.aboArt = aboArt;
}

}
