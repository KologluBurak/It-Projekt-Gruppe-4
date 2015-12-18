package de.hdm.itProjektGruppe4.client.gui;

    import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
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
		
		
		
		public NachrichtenForm(){
			
			Grid eigenesRaster = new Grid (8, 8);
			this.add(eigenesRaster);
			
			Label nachrichtB = new Label("Nachricht");
			Label hashTB = new Label("Hashtag");
			Label empfH = new Label("Empfaenger Hinzufuegen");
			
			myAsync.getAllAbonnements(new AsyncCallback<ArrayList<Abonnement>>() {
				
				@Override
				public void onSuccess(ArrayList<Abonnement> result) {

					System.out.println("Anzahl Result: " + result.size());
					int i = 1;
					for(final Abonnement abo : result){
						
						flexTable.setText(0, 0, "Nickname");
						flexTable.setText(0, 1, "Datum");
						flexTable.setText(i, 0, String.valueOf(abo.getId()));
						i++;
					}
				}
				
				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					System.out.println("Fehler: " + caught.getMessage());
				}
			});
			
			eigenesRaster.setWidget(0, 0, nachrichtB);
			eigenesRaster.setWidget(1, 0, nachta);
			eigenesRaster.setWidget(3, 0, hashTB);
			eigenesRaster.setWidget(4, 0, hashtagFenster);
			eigenesRaster.setWidget(2, 0, sendeButton);
			eigenesRaster.setWidget(5, 0, hashtagHinzu);
			eigenesRaster.setWidget(0, 5, empfH);
			eigenesRaster.setWidget(1, 5, empfaengerHinzuf);
			eigenesRaster.setWidget(2, 5, flexTable);
			nachrichtFenster.setWidth("500px");
			
			//Aktionen der Buttons
			
			sendeButton.addClickHandler(new ClickHandler() {
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

	

