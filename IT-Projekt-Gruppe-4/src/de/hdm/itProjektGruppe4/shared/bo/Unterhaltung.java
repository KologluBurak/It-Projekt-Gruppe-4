package de.hdm.itProjektGruppe4.shared.bo;

public class Unterhaltung {
	private Nachricht refNachricht;
	
	public void setNachrichten(Nachricht nachricht){
		refNachricht= nachricht;
	}
	
	public Nachricht nachrichtAnzeigen(){
		return refNachricht; //muss noch verändert werden
	}
	
	public void nachrichtHinzufuegen (Nachricht phinzufuegen){
		//
	}
}
