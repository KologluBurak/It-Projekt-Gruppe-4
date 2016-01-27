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
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.hdm.itProjektGruppe4.shared.MessagingAdministration;
import de.hdm.itProjektGruppe4.shared.MessagingAdministrationAsync;
import de.hdm.itProjektGruppe4.shared.bo.Hashtagabonnement;
import de.hdm.itProjektGruppe4.shared.bo.Nutzer;
import de.hdm.itProjektGruppe4.shared.bo.Nutzerabonnement;




		public class NutzeraboTabelle {

			HorizontalPanel hauptP = new HorizontalPanel();
			
			HorizontalPanel links = new HorizontalPanel();
			HorizontalPanel rechts = new HorizontalPanel();
			
			MessagingAdministrationAsync myAsync = GWT.create(MessagingAdministration.class);
			
			
			public Widget zeigeTabelle() {
				
				final FlexTable flexTable = new FlexTable();
				
								
		
				flexTable.setText(0, 1, "Abo");
				flexTable.setText(0, 2, "Entfernen");
				
				
				myAsync.getAllNutzerabonnementsByBeobachteteID(Cookies.getCookie("userID"),new AsyncCallback<ArrayList<Nutzerabonnement>>() {
					
					@Override
					public void onSuccess(ArrayList<Nutzerabonnement> result) {
						int zeileCounter = 1;
						
						for (final Nutzerabonnement na : result) {

							Button bModifizieren = new Button("Entfernen");
						
							Label beoID = new Label(na.getNutzerNickname().getNickname());
							Label followID = new Label(na.getNutzerNickname().getNickname());
							
							
							
//							flexTable.setText(zeileCounter, 0, Cookies.getCookie("userMail"));
							flexTable.setWidget(zeileCounter, 1, followID);
							flexTable.setWidget(zeileCounter, 2, bModifizieren);
							
							zeileCounter ++;
							
							bModifizieren.addClickHandler(new ClickHandler() {
								
								@Override
								public void onClick(ClickEvent event) {
								
									loeschenFollower(na);
									
								}
							});
					}
					}
					
					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Ein fehler ist aufgetreten: " + caught);
						
					}
				});
				
				flexTable.setCellPadding(4);
				
				 flexTable.addStyleName("nutzerabo");
				 flexTable.getCellFormatter().addStyleName(0, 1, "nutzeraboNumericColumn");
				 flexTable.getCellFormatter().addStyleName(0, 2, "nutzeraboNumericColumn");
				 flexTable.getCellFormatter().addStyleName(0, 3, "nutzeraboNumericColumn");
				 flexTable.getCellFormatter().addStyleName(0, 4, "nutzeraboNumericColumn");
				 
				
				 
				 final  FlexTable flexTable2 = new FlexTable();
				 
				 flexTable2.setText(0, 0, "Nickname");
				 flexTable2.setText(0, 1, "Folgen");
				 
		
				 myAsync.getAllNutzer(new AsyncCallback<ArrayList<Nutzer>>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onSuccess(ArrayList<Nutzer> result) {
						int zeileCounter = 1;
						
						for (final Nutzer nutzer : result){
							
							Button brechts = new Button("Folgen");
							
							Label nickname = new Label (String.valueOf(nutzer.getNickname()));
							
							flexTable2.setWidget(zeileCounter, 0, nickname);
							flexTable2.setWidget(zeileCounter, 1, brechts);
						
							zeileCounter ++;
							
							brechts.addClickHandler(new ClickHandler() {
								
								@Override
								public void onClick(ClickEvent event) {
									Nutzer beobachtete = new Nutzer();
									int id = Integer.parseInt(Cookies.getCookie("userID"));
									beobachtete.setId(id);
									folgenExistiert(beobachtete, nutzer);
								 
								}				
															
								});
								
								
								
							}
							
						}
				});
				 
				final FlexTable flexTable3 = new FlexTable();
				//flexTable3.setText(0, 0, "Nickname");
				flexTable3.setText(0, 1, "Nickname");
				
				myAsync.getAllNutzerabonnementsByBeobachteteID(Cookies.getCookie("userID"), new AsyncCallback<ArrayList<Nutzerabonnement>>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Fehler bei getAllNutzeraboonementsByBeobachteteID " + caught);
						
					}

					@Override
					public void onSuccess(ArrayList<Nutzerabonnement> result) {

						//Window.alert(result.size()+""); 
						int zeileCounter = 1;
						for (Nutzerabonnement na : result){
							
							//Label beoID = new Label(na.getNutzerNickname().getNickname());
							Label followID = new Label(na.getNutzerNickname().getNickname());
							
							
							
//							flexTable.setText(zeileCounter, 0, Cookies.getCookie("userMail"));
							flexTable3.setWidget(zeileCounter, 1, followID);
							
							zeileCounter ++;
							
						}
						
					}
				});
		
				links.add(flexTable);
				rechts.add(flexTable2);
				rechts.add(flexTable3);
				hauptP.add(links);
				hauptP.add(rechts);
				
				
				return hauptP;
				
	}

	
	private void folgenExistiert(final Nutzer derBeobachteteId, final Nutzer follower){
		myAsync.findAboByNutzerID(derBeobachteteId.getId(),follower.getId(), new AsyncCallback<Nutzerabonnement>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fehler folgenExistiert " + caught);
				
			}

			@Override
			public void onSuccess(Nutzerabonnement result) {
				
				if(result.getId() == 0){
					folgen(derBeobachteteId, follower);
				}
				
			}
		});
	}
	
	private void folgen(Nutzer derBeobachteteId, Nutzer follower) {
		myAsync.createNutzerabonnement(derBeobachteteId, follower, new AsyncCallback<Nutzerabonnement>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fehler bei " + caught);

			}

			@Override
			public void onSuccess(Nutzerabonnement result) {

			}
		});
		
	}
			
//			private void loeschenAbo(Nutzer nutzer) {
//				myAsync.delete(nutzer, new AsyncCallback<Void>() {
//
//					@Override
//					public void onFailure(Throwable caught) {
//						Window.alert("Fehler beim loeschen Nutzerfolgen" + caught);
//						
//					}
//
//					@Override
//					public void onSuccess(Void result) {
//						Window.alert("Fehler");
//						
//					}
//				});	
//		}
	
	public void loeschenFollower(Nutzerabonnement nutzerAbo){
		myAsync.delete(nutzerAbo, new AsyncCallback<Void>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Fehler bei loeschenFollower in NutzeraboTabelle "+ caught);
						
					}

					@Override
					public void onSuccess(Void result) {

						Window.alert("gelöscht!");
						
					}
		});
	}
}