package de.hdm.itProjektGruppe4.client.gui;

    import com.google.gwt.event.dom.client.ClickEvent;
	import com.google.gwt.event.dom.client.ClickHandler;
	import com.google.gwt.user.client.ui.Button;
	import com.google.gwt.user.client.ui.Grid;
	import com.google.gwt.user.client.ui.HorizontalPanel;
	import com.google.gwt.user.client.ui.Label;
	import com.google.gwt.user.client.ui.TextArea;
	import com.google.gwt.user.client.ui.TextBox;
	import com.google.gwt.user.client.ui.VerticalPanel;

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

		
		
		public NachrichtenForm(){
			
			Grid eigenesRaster = new Grid (8, 8);
			this.add(eigenesRaster);
			
			Label nachrichtB = new Label("Nachricht");
			Label hashTB = new Label("Hashtag");
			Label empfH = new Label("Empfaenger Hinzufuegen");
			
			eigenesRaster.setWidget(0, 0, nachrichtB);
			eigenesRaster.setWidget(1, 0, nachta);
			eigenesRaster.setWidget(3, 0, hashTB);
			eigenesRaster.setWidget(4, 0, hashtagFenster);
			eigenesRaster.setWidget(2, 0, sendeButton);
			eigenesRaster.setWidget(5, 0, hashtagHinzu);
			eigenesRaster.setWidget(0, 5, empfH);
			eigenesRaster.setWidget(1, 5, empfaengerHinzuf);
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

	

