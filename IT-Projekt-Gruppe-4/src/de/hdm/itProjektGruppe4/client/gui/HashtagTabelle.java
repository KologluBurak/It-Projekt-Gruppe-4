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
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;


















import de.hdm.itProjektGruppe4.shared.MessagingAdministration;
import de.hdm.itProjektGruppe4.shared.MessagingAdministrationAsync;
import de.hdm.itProjektGruppe4.shared.bo.Hashtag;
import de.hdm.itProjektGruppe4.shared.bo.Hashtagabonnement;
import de.hdm.itProjektGruppe4.shared.bo.Nutzer;

/**
 * Zeigt mehrere Tabellen mit den erforderlichen Daten an, hier in Verbindung mit Hashtags
 * 
 * @author Di Giovanni
 *
 */

	public class HashtagTabelle {
		
		/**
		 * Erstellen von Panels 
		 */
		
		HorizontalPanel hauptP = new HorizontalPanel();
		HorizontalPanel links = new HorizontalPanel();
		HorizontalPanel rechts = new HorizontalPanel();
		
		/**
		 * Erstellung von verschiedenen Widgets
		 */
		
		FlexTable FlexTable = new FlexTable();
		TextBox hashtagTextBox = new TextBox();
		Button hinzuButton = new Button("Hinzufuegen");
		
		MessagingAdministrationAsync myAsync = GWT.create(MessagingAdministration.class);
		
		/**
		 * Stellt die Tabellen da
		 * Panel + FlexTable
		 */
		
		public Widget zeigeTabelle () {
			
			/**
			 * Erstellt ein FlexTable für die Anzeige der Abonnierten Hashtags
			 */
			
			final FlexTable flexTable = new FlexTable();

			flexTable.setText(0, 0, "ID");
			flexTable.setText(0, 1, "HashtagID");
			flexTable.setText(0, 2, "Entfernen");

			/**
			 * Liest alle Daten aus der DB und füllt sie in die FlexTable
			 */
			
			myAsync.getAllHashtagabonnements(Cookies.getCookie("userID"), new AsyncCallback<ArrayList<Hashtagabonnement>>() {

				@Override
				public void onSuccess(ArrayList<Hashtagabonnement> result) {
					int zeileCounter = 1;

					for (final Hashtagabonnement hta : result) {

						/**
						 * Button wird erstellt der später entfernen soll
						 */
						
						Button bModifizieren = new Button("Entfernen");

						//TODO Hier m�sst hr erst eine Tabell erstellen und alle Daten reintun was in hta steht.

						Label aboID = new Label(String.valueOf(hta.getHashtagID()));
						Label zeit = new Label(String.valueOf(hta.getErstellungsZeitpunkt()));
						Label hashtagID = new Label(hta.getHashtagBezeichnung().getBezeichnung());

						flexTable.setWidget(zeileCounter, 0, aboID);
						//flexTable.setWidget(zeileCounter, 1, zeit);
						flexTable.setWidget(zeileCounter, 1, hashtagID);
						flexTable.setWidget(zeileCounter, 2, bModifizieren);

						/**
						 * Der erstellte Button wird aufgerufen + neuer Clickhanlder
						 */
						
						bModifizieren.addClickHandler(new ClickHandler(){

							@Override
							public void onClick(ClickEvent event) {
								loeschenHashtag(hta);

								// Beispiel zum sehen, dass es funktioniert abspielen bitt
//								Window.alert("�ndern funktioniert mit der Hashtag ID von " + hta.getHashtagID()); 
							}

						});

						zeileCounter += 1;
				}

					/**
					 * Scroller wird erstellt für den Flextable
					 */
					
					ScrollPanel scroller = new ScrollPanel(FlexTable);
				    scroller.setSize("250px", "450px");
				    
				    // Rahmen, Gr��e Unterhaltung
				    
				    rechts.add(scroller);
					FlexTable.setBorderWidth(5);
					
					
					FlexTable.setText(0, 0, "ID");
					FlexTable.setText(0, 1, "Bezeichnung");
					FlexTable.setText(0, 2, "Folgen");
					
					 /**
					  * Style für die CSS
					  */
					
					FlexTable.addStyleName("habo");
					FlexTable.getCellFormatter().addStyleName(0, 0, "haboColumn");
					FlexTable.getCellFormatter().addStyleName(0, 1, "haboColumn");
					FlexTable.getCellFormatter().addStyleName(0, 2, "haboColumn");

					/**
					 * Liest alle Daten aus der DB und füllt sie in die FlexTable
					 */
					
					myAsync.getAllHashtags(new AsyncCallback<ArrayList<Hashtag>>() {

						@Override
						public void onFailure(Throwable caught) {
							Window.alert("Fehler bei Anzeigen Hashtags" + caught);
						}

						@Override
						public void onSuccess(ArrayList<Hashtag> result) {
							int zeileCounterH = 1;
							
							for (final Hashtag ht : result){
								
							
								
								/**
								 * Button wird erstellt der später entfernen soll
								 */
								
								Button hfolgen = new Button("Folgen");
								
								Label hID = new Label(String.valueOf(ht.getId()));
								Label bez = new Label (String.valueOf(ht.getBezeichnung()));
								
								
								FlexTable.setWidget(zeileCounterH, 0, hID);
								FlexTable.setWidget(zeileCounterH, 1, bez);
								FlexTable.setWidget(zeileCounterH, 2, hfolgen);
								
								
								/**
								 * Der erstellte Button wird aufgerufen + neuer Clickhanlder
								 */
								
								hfolgen.addClickHandler(new ClickHandler() {

									@Override
									public void onClick(ClickEvent event) {
										Hashtagabonnement hta = new Hashtagabonnement();
										hta.setHashtagID(ht.getId());
										hta.setAbonnementID(0); 
										int nid = Integer.parseInt(Cookies.getCookie("userID"));
										hta.setNutzerID(nid);
										Nutzer nutzer = new Nutzer();
										nutzer.setId(nid);
										
										Hashtag hashtag = new Hashtag();
										hashtag.setId(ht.getId());
										hashtagaboExistiert(nutzer, hashtag, hta);

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
			
			
			
			 
			/**
			 * Hier werden die einzelnen Panel zusammengeführt und das Hauptpanel zurück geben
			 */
			
			 
			links.add(flexTable);
			rechts.add(FlexTable);
			
			hauptP.add(links);
			hauptP.add(rechts);
			
			return hauptP;
		
			
			
		}
		
		/**
		 * 
		 * Methode die überprüft, ob man dem Hashtag schon folgt
		 * 
		 */
		
		private void hashtagaboExistiert(Nutzer nutzer, Hashtag hashtag, final Hashtagabonnement bezeichnung){

			myAsync.getHashtagabonnementByNutzerIdHashtagID(nutzer.getId(), hashtag.getId(), new AsyncCallback<Hashtagabonnement>(){

				@Override
				public void onFailure(Throwable caught) {
					Window.alert("Fehler beim �berpr�fen der Hashtagabonnement " + caught);
				}

				@Override
				public void onSuccess(Hashtagabonnement result) {
					if(result.getId() == 0){
						hashFolgen(bezeichnung);
					}
					
				}
				
			});
		}
		
		/**
		 * 
		 * Methode die das Folgen eines Hashtags ermöglicht
		 * 
		 */
		
		private void hashFolgen(Hashtagabonnement bezeichnung) {
			myAsync.createHashtagAbonnement(bezeichnung, new AsyncCallback<Hashtagabonnement>() {

				@Override
				public void onFailure(Throwable caught) {
					Window.alert("Fehler bei hashFolgen " + caught);
					
				}

				@Override
				public void onSuccess(Hashtagabonnement result) {
					
					
				}
			});
			
		}
		
		/**
		 * 
		 * Methode die das Löschen eines Hashtags ermöglicht
		 * 
		 */
		
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





