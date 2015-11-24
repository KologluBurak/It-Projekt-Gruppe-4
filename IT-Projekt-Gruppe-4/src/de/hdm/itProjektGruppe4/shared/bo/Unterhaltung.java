package de.hdm.itProjektGruppe4.shared.bo;

public class Unterhaltung extends BusinessObject{
	
	private static final long serialVersionUID = 1L;
	
	private Nachricht refNachricht;
	
	public void setNachrichten(Nachricht nachricht){
		refNachricht= nachricht;
	}
	
	public Nachricht nachrichtAnzeigen(){
		return refNachricht; //muss noch veraendert werden
	}
	
	public void nachrichtHinzufuegen (Nachricht phinzufuegen){
		//
	}
}
