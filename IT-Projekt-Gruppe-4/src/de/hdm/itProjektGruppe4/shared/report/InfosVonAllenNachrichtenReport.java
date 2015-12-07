package de.hdm.itProjektGruppe4.shared.report;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Report, der alle Konten alle Kunden darstellt.
 * Die Klasse tr#gt keine weiteren Attribute- und Methoden-Implementierungen,
 * da alles Notwendige schon in den Superklassen vorliegt. Ihre Existenz ist 
 * dennoch wichtig, um bestimmte Typen von Reports deklarieren und mit ihnen 
 * objektorientiert umgehen zu können.
 * 
 * @author Thies
 * @author Yücel
 * @author Oikonomou
 */

public class InfosVonAllenNachrichtenReport extends CompositeReport 
implements Serializable{


	
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

	public ArrayList<Row> getRows() {
		// TODO Auto-generated method stub
		return null;
	}

	public void addRow(Row headline) {
		// TODO Auto-generated method stub
		
	}
	
//	public String getTitle() {
//		// TODO Auto-generated method stub
//		return null; 
//	}  
//
//	public Object getHeaderData() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	public Paragraph getImprint() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	public Object getCreated() {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
}
