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
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;











import de.hdm.itProjektGruppe4.shared.MessagingAdministration;
import de.hdm.itProjektGruppe4.shared.MessagingAdministrationAsync;
import de.hdm.itProjektGruppe4.shared.bo.Hashtag;
import de.hdm.itProjektGruppe4.shared.bo.Hashtagabonnement;



	public class HashtagTabelle {
		
		
		HorizontalPanel hauptP = new HorizontalPanel();
		HorizontalPanel links = new HorizontalPanel();
		HorizontalPanel rechts = new HorizontalPanel();
		
		FlexTable FlexTable = new FlexTable();
		TextBox hashtagTextBox = new TextBox();
		Button hinzuButton = new Button("Hinzufuegen");
		
		MessagingAdministrationAsync myAsync = GWT.create(MessagingAdministration.class);
		
		public Widget zeigeTabelle () {
			
			final FlexTable flexTable = new FlexTable();
			
			
			flexTable.setText(0, 0, "AboID");
			flexTable.setText(0, 1, "HashtagID");
			flexTable.setText(0, 2, "Entfernen");
			
			
			
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
								loeschenHashtag(hta);
								
								
								
								
								// Beispiel zum sehen, dass es funktioniert abspielen bitt
								Window.alert("Ändern funktioniert mit der Hashtag ID von " + hta.getHashtagID()); 
								
							}

							
							
							
						});
					
						zeileCounter += 1;
				}
				
					FlexTable.setText(0, 0, "ID");
					FlexTable.setText(0, 1, "Bezeichnung");
					FlexTable.setText(0, 2, "Folgen");
					
					
					myAsync.getAllHashtags(new AsyncCallback<ArrayList<Hashtag>>() {

						@Override
						public void onFailure(Throwable caught) {
							Window.alert("Fehler bei Anzeigen Hashtags" + caught);
							
						}

						@Override
						public void onSuccess(ArrayList<Hashtag> result) {
							int zeileCounterH = 1;
							
							for (final Hashtag ht : result){
								
								Button hfolgen = new Button("Folgen");
								
								Label hID = new Label(String.valueOf(ht.getId()));
								Label bez = new Label (String.valueOf(ht.getBezeichnung()));
								
								
								FlexTable.setWidget(zeileCounterH, 0, hID);
								FlexTable.setWidget(zeileCounterH, 1, bez);
								FlexTable.setWidget(zeileCounterH, 2, hfolgen);
								
								hfolgen.addClickHandler(new ClickHandler() {
									
									@Override
									public void onClick(ClickEvent event) {
										hashFolgen();
										
									}

									
									
								});
								zeileCounterH ++;
							}
							
						}
					});
					
					
					
					
					
					
				}

				@Override
				public void onFailure(Throwable caught) {
					Window.alert("Ein fehler ist aufgetreten: " + caught);
					
				}});
			
			
			
			 flexTable.addStyleName("habo");
			 flexTable.getCellFormatter().addStyleName(0, 1, "haboNumericColumn");
		
			
			 
			links.add(flexTable);
			rechts.add(FlexTable);
			
			hauptP.add(links);
			hauptP.add(rechts);
			
			return hauptP;
		
			
			
		}
		private void hashFolgen() {
			// TODO Auto-generated method stub
			
		}
		private void loeschenHashtag(Hashtagabonnement habo) {
			myAsync.delete(habo, new AsyncCallback<Void>() {

				@Override
				public void onFailure(Throwable caught) {
					Window.alert("Fehler beim loeschen Hashtagabonnement");
					
				}

				@Override
				public void onSuccess(Void result) {
					// TODO Auto-generated method stub
					
				}
			});
			
		}
		
	}





