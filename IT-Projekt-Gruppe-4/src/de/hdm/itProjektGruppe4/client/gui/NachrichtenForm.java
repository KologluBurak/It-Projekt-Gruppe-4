package de.hdm.itProjektGruppe4.client.gui;

    import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itProjektGruppe4.shared.MessagingAdministration;
import de.hdm.itProjektGruppe4.shared.MessagingAdministrationAsync;
import de.hdm.itProjektGruppe4.shared.bo.Abonnement;
import de.hdm.itProjektGruppe4.shared.bo.Hashtag;
import de.hdm.itProjektGruppe4.shared.bo.Nachricht;
import de.hdm.itProjektGruppe4.shared.bo.Nutzer;

	public class NachrichtenForm extends VerticalPanel{

		//Elemente unserer Nachrichten _GUI
		
		HorizontalPanel nachrichtenButtonpan = new HorizontalPanel();
		TextBox nachrichtFenster = new TextBox();
		Button sendeButton = new Button ("senden");
		Button empfaengerHinzuf = new Button ("Empfaenger Hinzufuegen");
		Button empfaengerEntfernen = new Button ("Empfänger Entfernen");
		TextBox hashtagFenster = new TextBox();
		Button hashtagHinzu = new Button("Hashtag Hinzufuegen");
		TextArea nachta = new TextArea();
		FlexTable flexTable = new FlexTable();

		MessagingAdministrationAsync myAsync = GWT.create(MessagingAdministration.class);
		
		
		// Konstruktor
		public NachrichtenForm(){
			
			Grid eigenesRaster = new Grid (8, 8);
			this.add(eigenesRaster);
			
			Label nachrichtB = new Label("Nachricht");
			Label hashTB = new Label("Hashtag");
			Label empfH = new Label("Empfaenger Hinzufuegen");
			
			
			//Clickhandler
			
			hashtagHinzu.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					
					erstelleHashtag();
					
				}});
			
			sendeButton.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
//					erstelleNachricht();
					
				}

				
			});
			
			
			
			
//			myAsync.getAllAbonnements(new AsyncCallback<ArrayList<Abonnement>>() {
//				
//				@Override
//				public void onSuccess(ArrayList<Abonnement> result) {
//
//					System.out.println("Anzahl Result: " + result.size());
//					int i = 1;
//					for(final Abonnement abo : result){
//						
//						flexTable.setText(0, 0, "Nickname");
//						flexTable.setText(0, 1, "Datum");
//						flexTable.setText(i, 0, String.valueOf(abo.getId()));
//						i++;
//					}
//				}
//				
//				@Override
//				public void onFailure(Throwable caught) {
//					// TODO Auto-generated method stub
//					System.out.println("Fehler: " + caught.getMessage());
//				}
//			});
			
			eigenesRaster.setWidget(0, 0, nachrichtB);
			eigenesRaster.setWidget(1, 0, nachta);
			eigenesRaster.setWidget(3, 0, hashTB);
			eigenesRaster.setWidget(4, 0, hashtagFenster);
			eigenesRaster.setWidget(2, 0, sendeButton);
			eigenesRaster.setWidget(5, 0, hashtagHinzu);
			eigenesRaster.setWidget(0, 5, flexTable);
			nachrichtFenster.setWidth("500px");
			
			
			flexTable.setText(0, 0, "Vorname");
			flexTable.setText(0, 1, "Nickname");
			flexTable.setText(0, 2, "Hinzufuegen");
			
			myAsync.getAllNutzer(new AsyncCallback<ArrayList<Nutzer>>() {

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onSuccess(ArrayList<Nutzer> result) {
					int zeileCounter = 1;
					
					for (final Nutzer nutzer : result) {
						
						Button bModifizieren = new Button("hinzufuegen");
						
						
						Label vornameID = new Label (String.valueOf(nutzer.getVorname()));
						Label nicknameID = new Label (String.valueOf(nutzer.getNickname()));
						
						
						flexTable.setWidget(zeileCounter, 0, vornameID);
						flexTable.setWidget(zeileCounter, 1, nicknameID);
						flexTable.setWidget(zeileCounter, 2, bModifizieren);
						
						empfaengerHinzuf.addClickHandler(new ClickHandler() {
							
							@Override
							public void onClick(ClickEvent event) {
//								empfaengerHinzu();
								
							}

							
						});
					}
					
				}
			});
			
		}
		
		// Methoden
		
		public void erstelleHashtag(){
		
		myAsync.createHashtag(hashtagFenster.getText(), new AsyncCallback<Hashtag>() {
 
			@Override
			public void onFailure(Throwable caught) {
				DialogBox d = new DialogBox();
				d.setText("Fehler:" + caught);
				d.show();
				
			}

			@Override
			public void onSuccess(Hashtag result) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
	
//	private void erstelleNachricht() {
//		
//		myAsync.createNachricht(text, nickname, unterhaltung, callback); {
//
//			@Override
//			public void onFailure(Throwable caught) {
//				DialogBox d = new DialogBox();
//				d.setText("Fehler:" + caught);
//				d.show();
//				
//			}
//
//			@Override
//			public void onSuccess(Nachricht result) {
//				// TODO Auto-generated method stub
//				
//			}
//		});
		
//	}
//		private void empfaengerHinzu() {
//			myAsync.getAllNutzer(new AsyncCallback<ArrayList<Nutzer>>() {
//			});
//			
//		}
	
	}

	

