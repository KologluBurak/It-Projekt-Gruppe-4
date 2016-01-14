package de.hdm.itProjektGruppe4.client.gui;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;







import de.hdm.itProjektGruppe4.shared.MessagingAdministration;
import de.hdm.itProjektGruppe4.shared.MessagingAdministrationAsync;
import de.hdm.itProjektGruppe4.shared.bo.Hashtagabonnement;



	public class HashtagTabelle {
		
		VerticalPanel hauptP = new VerticalPanel();
		TextBox hashtagTextBox = new TextBox();
		Button hinzuButton = new Button("Hinzufuegen");
		
		MessagingAdministrationAsync myAsync = GWT.create(MessagingAdministration.class);
		
		public Widget zeigeTabelle () {
			
			final FlexTable flexTable = new FlexTable();
			
			
			flexTable.setText(0, 1, "AboID");
			flexTable.setText(0, 2, "HashtagID");
			flexTable.setText(0, 3, "Entfernen");
			
			
			
			myAsync.getAllHashtagabonnements(Cookies.getCookie("userID"), new AsyncCallback<ArrayList<Hashtagabonnement>>() {
				
				@Override
				public void onSuccess(ArrayList<Hashtagabonnement> result) {
					int zeileCounter = 1;
					
					for (final Hashtagabonnement hta : result) {

						Button bModifizieren = new Button("Entfernen");
						
						//TODO Hier müsst hr erst eine Tabell erstellen und alle Daten reintun was in hta steht.

						Label aboID = new Label(String.valueOf(hta.getAbonnementID()));
						Label zeit = new Label(String.valueOf(hta.getErstellungsZeitpunkt()));
						Label hashtagID = new Label(hta.getHashtagBezeichnung().getBezeichnung());
						
						flexTable.setWidget(zeileCounter, 0, aboID);
						//flexTable.setWidget(zeileCounter, 1, zeit);
						flexTable.setWidget(zeileCounter, 1, hashtagID);
						flexTable.setWidget(zeileCounter, 2, bModifizieren);
						
						bModifizieren.addClickHandler(new ClickHandler(){

							@Override
							public void onClick(ClickEvent event) {
								// TODO hier kommt die Methode rein was mit der einen Zeile passieren soll
								
								// Beispiel zum sehen, dass es funktioniert abspielen bitt
								Window.alert("Ändern funktioniert mit der Hashtag ID von " + hta.getHashtagID()); 
								
							}
							
							
						});
					
						zeileCounter += 1;
				}
				
				}

				@Override
				public void onFailure(Throwable caught) {
					Window.alert("Ein fehler ist aufgetreten: " + caught);
					
				}});
			
			
			
			 flexTable.addStyleName("habo");
			 flexTable.getCellFormatter().addStyleName(0, 1, "haboNumericColumn");
		
			
			 
			 
			hauptP.add(flexTable);
			
			return hauptP;
			
		}

	}





