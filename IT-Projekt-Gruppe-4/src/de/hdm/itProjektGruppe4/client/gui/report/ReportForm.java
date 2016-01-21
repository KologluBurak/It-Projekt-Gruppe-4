package de.hdm.itProjektGruppe4.client.gui.report;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;




public class ReportForm {

	Button aboRepAnzeigenButton = new Button("Report anzeigen");
	Button nachRepAnzeigenButton = new Button("Report anzeigen");
	Button nutzSpezNachRepAnzeigenButton = new Button("Report anzeigen");
	Button zeitSpezNachRepAnzeigenButton = new Button("Report anzeigen");
	Button hashAboRepAnzeigenButton = new Button("Report anzeigen");
	Button nutzAboRepAnzeigenButton = new Button("Report anzeigen");


public void anzeigenR() {
	
	
	Grid eigenesRaster = new Grid(12, 12);
	
	
	Label abonnementRep = new Label("Alle Abonnements");
	Label nachrichtenRep = new Label("Alle Nachrichten ");
	Label nutzSpezNachRep = new Label("Nutzerspezifische Nachrichten");
	Label zeitSpezNachRep = new Label("Zeitraumspezifische Nachrichten");
	Label hashAboRep = new Label("Hashtagabonnements");
	Label nutzAboRep = new Label("Nutzerabonnements");
	
	
	eigenesRaster.setWidget(1, 2, abonnementRep);
	eigenesRaster.setWidget(2, 2, aboRepAnzeigenButton);
	eigenesRaster.setWidget(1, 7, nachrichtenRep);
	eigenesRaster.setWidget(2, 7, nachRepAnzeigenButton);
	eigenesRaster.setWidget(4, 2, nutzSpezNachRep);
	eigenesRaster.setWidget(5, 2, nutzSpezNachRepAnzeigenButton);
	eigenesRaster.setWidget(4, 7, zeitSpezNachRep);
	eigenesRaster.setWidget(5, 7, zeitSpezNachRepAnzeigenButton);
	eigenesRaster.setWidget(7, 2, hashAboRep);
	eigenesRaster.setWidget(8, 2, hashAboRepAnzeigenButton);
	eigenesRaster.setWidget(7, 7, nutzAboRep);
	eigenesRaster.setWidget(8, 7, nutzAboRepAnzeigenButton);
	
	
	
}

}