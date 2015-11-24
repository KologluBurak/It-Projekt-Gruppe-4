package de.hdm.itProjektGruppe4.shared.bo;

public class Unterhaltung extends BusinessObject{
	
	private static final long serialVersionUID = 1L;
	
	private Nachricht refNachricht;
	private Unterhaltung lastEdited;
	
	public void setNachrichten(Nachricht nachricht){
		refNachricht= nachricht;
	}
	
	public Nachricht nachrichtAnzeigen(){
		return refNachricht; //muss noch veraendert werden
	}
	
	public void nachrichtHinzufuegen (Nachricht hinzufuegen){
		//
	}

	public Unterhaltung getLastEdited() {
		return lastEdited;
	}

	public void setLastEdited(Unterhaltung lastEdited) {
		this.lastEdited = lastEdited;
	}
	
}
