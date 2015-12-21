package de.hdm.itProjektGruppe4.shared.report;

import java.util.Date;

/**
 * Report, der alle Konten alle Kunden darstellt.
 * Die Klasse trägt keine weiteren Attribute- und Methoden-Implementierungen,
 * da alles Notwendige schon in den Superklassen vorliegt. Ihre Existenz ist 
 * dennoch wichtig, um bestimmte Typen von Reports deklarieren und mit ihnen 
 * objektorientiert umgehen zu können.
 * 
 * @author Thies
 * @author Oikonomou
 */

public class InfosUeberNutzerspezifischeNachrichtenReport extends SimpleReport{

	private static final long serialVersionUID = 1L;
	private Date anfangszeitpunkt;
	private Date endzeitpunkt;
		
		
	public Date getAnfangszeitpunkt() {
		return anfangszeitpunkt;
	}
		
	public void setAnfangszeitpunkt(Date anfangszeitpunkt) {
		this.anfangszeitpunkt = anfangszeitpunkt;
	}
		
	public Date getEndzeitpunkt() {
		return endzeitpunkt;
	}
		
	public void setEndzeitpunkt(Date endzeitpunkt) {
		this.endzeitpunkt = endzeitpunkt;
	}

}