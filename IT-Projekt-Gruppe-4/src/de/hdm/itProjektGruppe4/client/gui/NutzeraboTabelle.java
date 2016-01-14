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
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.hdm.itProjektGruppe4.shared.MessagingAdministration;
import de.hdm.itProjektGruppe4.shared.MessagingAdministrationAsync;
import de.hdm.itProjektGruppe4.shared.bo.Hashtagabonnement;
import de.hdm.itProjektGruppe4.shared.bo.Nutzerabonnement;




		public class NutzeraboTabelle {

			VerticalPanel hauptP = new VerticalPanel();
			
			MessagingAdministrationAsync myAsync = GWT.create(MessagingAdministration.class);
			
			
			public Widget zeigeTabelle() {
				
				final FlexTable flexTable = new FlexTable();
								
				
				
				flexTable.setText(0, 0, "Your Nickname");
				flexTable.setText(0, 1, "Follower");
				flexTable.setText(0, 2, "Entfernen");
				
				
				myAsync.getAllNutzerabonnements(Cookies.getCookie("userID"),new AsyncCallback<ArrayList<Nutzerabonnement>>() {
					
					@Override
					public void onSuccess(ArrayList<Nutzerabonnement> result) {
						int zeileCounter = 1;
						
						for (final Nutzerabonnement na : result) {

							Button bModifizieren = new Button("Entfernen");
						
							Label beoID = new Label(na.getNutzerNickname().getNickname());
							Label followID = new Label(na.getNutzerNickname().getNickname());
							
							
							
							flexTable.setText(zeileCounter, 0, Cookies.getCookie("userMail"));
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
				
				hauptP.add(flexTable);
				return hauptP;
				
	}
	
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