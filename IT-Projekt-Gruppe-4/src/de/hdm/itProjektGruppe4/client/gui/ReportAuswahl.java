package de.hdm.itProjektGruppe4.client.gui;

//import java.text.ParseException;

//import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
//import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.VerticalPanel;
//import com.google.gwt.user.client.ui.Widget;
//import com.google.gwt.user.client.ui.HorizontalPanel;
//import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;

//import de.hdm.itProjektGruppe4.shared.ReportGenerator;
//import de.hdm.itProjektGruppe4.shared.ReportGeneratorAsync;
//import de.hdm.itProjektGruppe4.shared.bo.*;

public class ReportAuswahl extends VerticalPanel {
	
	// Elemente unserer Reports
	Button aboRepAnzeigenButton = new Button("Report anzeigen");
	Button nachRepAnzeigenButton = new Button("Report anzeigen");
	Button nutzSpezNachRepAnzeigenButton = new Button("Report anzeigen");
	Button zeitSpezNachRepAnzeigenButton = new Button("Report anzeigen");
	Button hashAboRepAnzeigenButton = new Button("Report anzeigen");
	Button nutzAboRepAnzeigenButton = new Button("Report anzeigen");

//	ReportGeneratorAsync rpAsync = GWT.create(ReportGenerator.class);
	
	public ReportAuswahl() {
		
		Grid eigenesRaster = new Grid(12, 12);
		this.add(eigenesRaster);
		
		Label abonnementRep = new Label("Alle Abonnements");
		Label nachrichtenRep = new Label("Alle Nachrichten ");
		Label nutzSpezNachRep = new Label("Nutzerspezifische Nachrichten");
		Label zeitSpezNachRep = new Label("Zeitraumspezifische Nachrichten");
		Label hashAboRep = new Label("Hashtagabonnements");
		Label nutzAboRep = new Label("Nutzerabonnements");

//		rpAsync.erstelleAlleAbonnementsReport(aboNutzer, aboHashtag, 
//				new AsyncCallback<String>() {
//			
//			public void onSuccess(String result) {
//
//				System.out.println("Anzahl Result: " + result.length());
//					
//			}
//
//			@Override
//			public void onFailure(Throwable caught) {
//				// TODO Auto-generated method stub
//				System.out.println("Fehler: " + caught.getMessage());
//			}
//		});


	
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
		
		//Aktionen der Buttons
		
		aboRepAnzeigenButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				sendeTextdatenWeiter();
				
			}

			private void sendeTextdatenWeiter() {
				
			}
		});
		

		
		showButtons();
	}

	//Zeigt Buttons an

	private void showButtons() {
			
	}
	
}