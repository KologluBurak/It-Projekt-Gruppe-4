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




public class ReportForm {
	
	HorizontalPanel haupt = new HorizontalPanel();
	VerticalPanel root = new VerticalPanel();
	VerticalPanel linksO	= new VerticalPanel();
	VerticalPanel mitteO = new VerticalPanel();
	VerticalPanel rechtO = new VerticalPanel();
	VerticalPanel linksU = new VerticalPanel();
	VerticalPanel mitteU = new VerticalPanel();
	VerticalPanel rechtsU = new VerticalPanel();
	VerticalPanel inhaltPanel = new VerticalPanel();
	
	FlexTable ftLinksO = new FlexTable();
	FlexTable ftMitteO = new FlexTable();
	FlexTable ftRechtsO = new FlexTable();
	FlexTable ftLinksU = new FlexTable();
	FlexTable ftMitteU = new FlexTable();
	FlexTable inhalt = new FlexTable();
	FlexTable ftRechtsU = new FlexTable();
	
	TextBox tb = new TextBox();
	Button tbb = new Button ("Go");
	
	TextBox tb2 = new TextBox();
	TextBox tb21 = new TextBox();
	Button tbb2 = new Button("Go");
	
	TextBox tb3 = new TextBox();
	Button tbb3 = new Button("Go");
	
	TextBox tb4 = new TextBox();
	Button tbb4 = new Button("Go");

	Button aboRepAnzeigenButton = new Button("Alle Abos anzeigen");
	Button nachRepAnzeigenButton = new Button("Alle Nachrichten anzeigen");
	Button nutzSpezNachRepAnzeigenButton = new Button("MSG by Nutzer ID anzeigen");
	Button zeitSpezNachRepAnzeigenButton = new Button("MSG by Zeit anzeigen");
	Button hashAboRepAnzeigenButton = new Button("HashtagAbo anzeigen");
	Button nutzAboRepAnzeigenButton = new Button("NutzerAbo anzeigen");

	MessagingAdministrationAsync myAsync = GWT.create(MessagingAdministration.class);
	
public void anzeigenR() {
	
	Grid eigenesRaster = new Grid(12, 12);
	
	Label abonnementRep = new Label("Alle Abonnements");
	Label nachrichtenRep = new Label("Alle Nachrichten ");
	Label nutzSpezNachRep = new Label("Nutzerspezifische Nachrichten");
	Label zeitSpezNachRep = new Label("Zeitraumspezifische Nachrichten");
	Label hashAboRep = new Label("Hashtagabonnements");
	Label nutzAboRep = new Label("Nutzerabonnements");
	
	
	Grid gridLinksO = new Grid(3, 3);
	gridLinksO.setWidget(1, 2, abonnementRep);
	gridLinksO.setWidget(2, 2, aboRepAnzeigenButton);
	linksO.add(gridLinksO);
	
	Grid gridMitteO = new Grid(3, 3);
	gridMitteO.setWidget(1, 2, nachrichtenRep);
	gridMitteO.setWidget(2, 2, nachRepAnzeigenButton);
	mitteO.add(gridMitteO);
	
	Grid gridRechtsO = new Grid(4, 4);
	gridRechtsO.setWidget(1, 2, nutzSpezNachRep);
	gridRechtsO.setWidget(2, 2, nutzSpezNachRepAnzeigenButton);
	//gridRechtsO.setWidget(3, 2, tbb);
	rechtO.add(gridRechtsO);
	
	Grid gridLinksU = new Grid(3, 3);
	gridLinksU.setWidget(1, 2, zeitSpezNachRep);
	gridLinksU.setWidget(2, 2, zeitSpezNachRepAnzeigenButton);
	linksU.add(gridLinksU);
	
	Grid gridMitteU = new Grid(3, 3);
	gridMitteU.setWidget(1, 2, hashAboRep);
	gridMitteU.setWidget(2, 2, hashAboRepAnzeigenButton);
	mitteU.add(gridMitteU);
	
	
	Grid gridRechtsU = new Grid(3, 3);
	gridRechtsU.setWidget(1, 2, nutzAboRep);
	gridRechtsU.setWidget(2, 2, nutzAboRepAnzeigenButton);
	rechtsU.add(gridRechtsU);

	
	aboRepAnzeigenButton.addClickHandler(new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			
			alleAbos();
		}
	});
	
	nachRepAnzeigenButton.addClickHandler(new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			alleNachrichten();
			
		}
	});
	
	nutzSpezNachRepAnzeigenButton.addClickHandler(new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			inhalt.clear();
			inhalt.setWidget(0,0, tb);
			inhalt.setWidget(1, 0, tbb);
			nutzerSpezNach();
			
		}
	});
	
	zeitSpezNachRepAnzeigenButton.addClickHandler(new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			inhalt.clear();
			inhalt.setWidget(0,0, tb2);
			inhalt.setWidget(1, 0, tb21);
			inhalt.setWidget(2, 0, tbb2);
			zeitSpezNach();
			
		}
	});
	
	hashAboRepAnzeigenButton.addClickHandler(new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			inhalt.clear();
			inhalt.setWidget(0,0, tb3);
			inhalt.setWidget(1, 0, tbb3);
			alleHashAbosAneigen();
			
		}
	});
	
	nutzAboRepAnzeigenButton.addClickHandler(new ClickHandler() {

		@Override
		public void onClick(ClickEvent event) {
			inhalt.clear();
			inhalt.setWidget(0,0, tb4);
			inhalt.setWidget(1, 0, tbb4);
			alleNutzerAbosAnzeigen();
		}
	});
	
	linksO.add(ftLinksO);
	mitteO.add(ftMitteO);
	rechtO.add(ftRechtsO);
	linksU.add(ftLinksU);
	mitteU.add(ftMitteU);
	rechtsU.add(ftRechtsU);
	
	
	haupt.add(linksO);
	haupt.add(mitteO);
	haupt.add(rechtO);
	haupt.add(linksU);
	haupt.add(mitteU);
	haupt.add(rechtsU);
	//inhaltPanel.add(inhalt);
	root.add(haupt);
	root.add(inhalt);
	
	RootPanel.get("starterReport").add(root);
}

//methoden

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
	
	public void nutzerSpezNach(){
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
	
	public void alleNutzerAbosAnzeigen(){
		
	}
	
}
