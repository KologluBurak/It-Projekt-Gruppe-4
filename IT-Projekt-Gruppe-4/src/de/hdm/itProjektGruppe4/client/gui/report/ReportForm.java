package de.hdm.itProjektGruppe4.client.gui.report;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itProjektGruppe4.shared.MessagingAdministration;
import de.hdm.itProjektGruppe4.shared.MessagingAdministrationAsync;
import de.hdm.itProjektGruppe4.shared.bo.Abonnement;
import de.hdm.itProjektGruppe4.shared.bo.Hashtag;
import de.hdm.itProjektGruppe4.shared.bo.Hashtagabonnement;
import de.hdm.itProjektGruppe4.shared.bo.Nachricht;
import de.hdm.itProjektGruppe4.shared.bo.Nutzer;
import de.hdm.itProjektGruppe4.shared.bo.Nutzerabonnement;

/**
 * Enthält Methoden und Elemente die für das ausgeben des Reports benötigt werden
 * @author Di Giovanni
 *
 */


public class ReportForm {
	
	/**
	 * Widgets und Panels werden erstellt 
	 */
	
	HorizontalPanel haupt = new HorizontalPanel();
	VerticalPanel root = new VerticalPanel();
	
	VerticalPanel rechtO = new VerticalPanel();
	VerticalPanel linksU = new VerticalPanel();
	VerticalPanel mitteU = new VerticalPanel();
	VerticalPanel rechtsU = new VerticalPanel();
	VerticalPanel inhaltPanel = new VerticalPanel();
	
	FlexTable inhalt = new FlexTable();
	Label inhalt1 = new Label("Geben Sie bitte eine EMail ein!");
	Label inhalt2 = new Label("Geben Sie das Datum ein TT/MM/JJJJ!");
	Label inhalt3 = new Label("Geben Sie ein #Hashtag ein!");
	Label inhalt4 = new Label("Geben Sie bitte eine EMail ein!");
	
	
	TextBox tb = new TextBox();
	Button tbb = new Button ("Los");
	
	TextBox tb2 = new TextBox();
	TextBox tb21 = new TextBox();
	Button tbb2 = new Button("Los");
	
	TextBox tb3 = new TextBox();
	Button tbb3 = new Button("Los");
	
	TextBox tb4 = new TextBox();
	Button tbb4 = new Button("Los");

	Button nutzSpezNachRepAnzeigenButton = new Button("Nachrichten von Nutzer");
	Button zeitSpezNachRepAnzeigenButton = new Button("Nachrichten nach Zeit");
	Button hashAboRepAnzeigenButton = new Button("HashtagAbo anzeigen");
	Button nutzAboRepAnzeigenButton = new Button("NutzerAbo anzeigen");

	MessagingAdministrationAsync myAsync = GWT.create(MessagingAdministration.class);
	
public void anzeigenR() {
	
	/**
	 * Labels werden erstellt 
	 */
	
	Label nutzSpezNachRep = new Label("Nutzerspezifische");
	Label zeitSpezNachRep = new Label("Zeitraumspezifisch");
	Label hashAboRep = new Label("Hashtagabonnements");
	Label nutzAboRep = new Label("Nutzerabonnements");
	
	/**
	 * erstellen von mehreren Grids für eine bessere Übersicht
	 */
	
	Grid gridRechtsO = new Grid(3, 3);
	gridRechtsO.setWidget(0, 2, nutzSpezNachRep);
	gridRechtsO.setWidget(2, 2, nutzSpezNachRepAnzeigenButton);
	//gridRechtsO.setWidget(3, 2, tbb);
	rechtO.add(gridRechtsO);
	rechtO.setStylePrimaryName("rechtO");
    rechtO.setBorderWidth(10);
	
	Grid gridLinksU = new Grid(3, 3);
	gridLinksU.setWidget(0, 2, zeitSpezNachRep);
	gridLinksU.setWidget(2, 2, zeitSpezNachRepAnzeigenButton);
	linksU.add(gridLinksU);
	linksU.setStylePrimaryName("linksU");
	linksU.setBorderWidth(10);
	
	Grid gridMitteU = new Grid(3, 3);
	gridMitteU.setWidget(0, 2, hashAboRep);
	gridMitteU.setWidget(2, 2, hashAboRepAnzeigenButton);
	mitteU.add(gridMitteU);
	mitteU.setStylePrimaryName("mitteU");
	mitteU.setBorderWidth(10);
	
	
	Grid gridRechtsU = new Grid(3, 3);
	gridRechtsU.setWidget(0, 2, nutzAboRep);
	gridRechtsU.setWidget(2, 2, nutzAboRepAnzeigenButton);
	rechtsU.add(gridRechtsU);
	rechtsU.setStylePrimaryName("rechtsU");
	rechtsU.setBorderWidth(10);

	/**
	 * Der erstellte Button wird aufgerufen + neuer Clickhanlder
	 */
	
	nutzSpezNachRepAnzeigenButton.addClickHandler(new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			inhalt.clear();
			inhalt.setWidget(0, 0, inhalt1);
			inhalt.setWidget(1, 0, tb);
			inhalt.setWidget(2, 0, tbb);
			nutzerSpezNach();
			
		}
	});
	
	/**
	 * Der erstellte Button wird aufgerufen + neuer Clickhanlder
	 */
	
	zeitSpezNachRepAnzeigenButton.addClickHandler(new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			inhalt.clear();
			inhalt.setWidget(0, 0, inhalt2);
			inhalt.setWidget(1, 0, tb2);
			inhalt.setWidget(2, 0, tb21);
			inhalt.setWidget(3, 0, tbb2);
			zeitSpezNach();
			
		}
	});
	
	/**
	 * Der erstellte Button wird aufgerufen + neuer Clickhanlder
	 */
	
	hashAboRepAnzeigenButton.addClickHandler(new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			inhalt.clear();
			inhalt.setWidget(0, 0, inhalt3);
			inhalt.setWidget(1,0, tb3);
			inhalt.setWidget(2, 0, tbb3);
			alleHashAbosAneigen();
			
		}
	});
	
	/**
	 * Der erstellte Button wird aufgerufen + neuer Clickhanlder
	 */
	
	nutzAboRepAnzeigenButton.addClickHandler(new ClickHandler() {

		@Override
		public void onClick(ClickEvent event) {
			inhalt.clear();
			inhalt.setWidget(0, 0, inhalt4);
			inhalt.setWidget(1,0, tb4);
			inhalt.setWidget(2, 0, tbb4);
			alleNutzerAbosAnzeigen();
		}
	});
	
	/**
	 * Hier werden die einzelnen Panel zusammengeführt und das root panel zurück geben
	 */

	haupt.add(rechtO);
	haupt.add(linksU);
	haupt.add(mitteU);
	haupt.add(rechtsU);
	//inhaltPanel.add(inhalt);
	
	root.add(haupt);
	root.add(inhalt);
	
	/*
     * Das VerticalPanel wird einem DIV-Element namens "Starter" in der
     * zugeh�rigen HTML-Datei zugewiesen und erh�lt so seinen Darstellungsort.
     */
	
	RootPanel.get("starterReport").add(root);
}

/**
 * Mathode für das ausbeben aller abonnements
 */

public void alleAbos() {
	
	myAsync.getAllAbonnements(new AsyncCallback<ArrayList<Abonnement>>() {
		
		@Override
		public void onSuccess(ArrayList<Abonnement> result) {

		}
		
		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Fehler bei Alle Abonnements" + caught);
			
		}
	});

	
	
}

	public void alleNachrichten(){
		
	}
	
	/**
	 * Methode für die Auswahl von Nachrichten von einem Nutzer
	 */
	
	public void nutzerSpezNach(){
		
		/**
		 * Der erstellte Button wird aufgerufen + neuer Clickhandler
		 */
		
		tbb.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				String nickname = tb.getText();
				inhalt.clear();
				
				myAsync.getNachrichtByNickname(nickname, new AsyncCallback<ArrayList<Nachricht>>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Fehler bei Nutzer spezifische Nahrichten " + caught);
					}

					@Override
					public void onSuccess(ArrayList<Nachricht> result) {
						int zeilenCounter = 0;
						for(Nachricht n : result) {
							inhalt.setText(zeilenCounter, 0, n.getText());
							zeilenCounter++;
						}
					}
				});
			}
		});
	}
	
	/**
	 * Methode für die Auswahl von Nachrichten nach Zeit
	 */
	
	public void zeitSpezNach(){
		
		
		tbb2.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				String von = tb2.getText();
				String bis = tb21.getText();
				inhalt.clear();
				
				myAsync.getAlleNachrichtenJeZeitraum(von, bis, new AsyncCallback<ArrayList<Nachricht>>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onSuccess(ArrayList<Nachricht> result) {
						int zeilenCounter = 1;
						
						for(Nachricht n : result){
							inhalt.setText(zeilenCounter, 0, n.getText());
							zeilenCounter ++;
						}
						
					}
				});
				
			}
		});
		
	}
	
	/**
	 * Methode für die Auswahl von wem ein Hashtag gefolgt wird
	 */
	
	public void alleHashAbosAneigen(){
		
		tbb3.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				String hashtag = tb3.getText();
				inhalt.clear();
				myAsync.getNutzerByHashtagAbo(hashtag, new AsyncCallback<ArrayList<Nutzer>>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Ein Fehler ist aufgetreten! " + caught);
						
					}

					@Override
					public void onSuccess(ArrayList<Nutzer> result) {
						int zeilenCounter = 1;
						for(Nutzer n : result ){
							inhalt.setText(zeilenCounter, 0, n.getNickname());
							zeilenCounter++;
						}
					}
				});

				
			}
		});
		
	}
//		
//		inhalt.setText(0, 0, "ID");
//		inhalt.setText(0, 1, "Bezeichnung");
//		
//
//		myAsync.getAllHashtags(new AsyncCallback<ArrayList<Hashtag>>() {
//
//			@Override
//			public void onFailure(Throwable caught) {
//				Window.alert("Fehler bei Anzeigen Hashtags" + caught);
//			}
//
//			@Override
//			public void onSuccess(ArrayList<Hashtag> result) {
//				int zeileCounterH = 1;
//				
//				for (final Hashtag ht : result){
//
//					Label hID = new Label(String.valueOf(ht.getId()));
//					Label bez = new Label (String.valueOf(ht.getBezeichnung()));
//					
//					inhalt.setWidget(zeileCounterH, 0, hID);
//					inhalt.setWidget(zeileCounterH, 1, bez);
//				
//					zeileCounterH ++;
//				}
//				
//			}
//		});
//	}
	
	/**
	 * Methode für die Auwahl von Nutzerabos die der eingeloggte User abonniert hat
	 */
	
	public void alleNutzerAbosAnzeigen(){
		
		tbb4.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				myAsync.getAllNutzerabonnementsByBeobachteteMail(tb4.getText(), new AsyncCallback<ArrayList<Nutzerabonnement>>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Fehler beim anzeigen eines Nutzerabos " + caught);
						
					}

					@Override
					public void onSuccess(ArrayList<Nutzerabonnement> result) {

						int zeilenCounter = 1;

						for(Nutzerabonnement na : result){
							inhalt.setText(zeilenCounter, 0, na.getNutzerNickname().getNickname());
							zeilenCounter ++;
						}
						
					}
				});
				
			}
		});
	}
	
}
