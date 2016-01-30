package de.hdm.itProjektGruppe4.client.gui;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dev.javac.asm.CollectAnnotationData.MyAnnotationArrayVisitor;
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
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.hdm.itProjektGruppe4.shared.MessagingAdministration;
import de.hdm.itProjektGruppe4.shared.MessagingAdministrationAsync;
import de.hdm.itProjektGruppe4.shared.bo.Nachricht;
import de.hdm.itProjektGruppe4.shared.bo.Nutzer;
import de.hdm.itProjektGruppe4.shared.bo.Unterhaltung;
import de.hdm.itProjektGruppe4.shared.bo.Unterhaltungsliste;


/**
 * Enthält Methoden und Elemente die für das anzeigen einer Unterhaltung nötig sind
 * @author DI Giovanni
 *
 */

public class UnterhaltungsForm {
	
	/**
	 * Erstellen von Panels 
	 */

	HorizontalPanel hauptP = new HorizontalPanel();
	HorizontalPanel links = new HorizontalPanel();
	HorizontalPanel rechts = new HorizontalPanel();
	
	
	/**
	 * Widgets und Panels werden erstellt 
	 */
	
	Label unterhaltung = new Label("Unterhaltung");
	Label nachricht = new Label("Nachricht");
	Label chatMit = new Label();
	
	Label chats = new Label("Unterhaltung");
	TextArea nachTa = new TextArea();
	Button sendenN = new Button("Senden");
	FlexTable uft = new FlexTable();
	FlexTable cft = new FlexTable();
	
	
	MessagingAdministrationAsync myAsync = GWT.create(MessagingAdministration.class);
	
	// Methode zeige Tabelle
	
	public Widget zeigeTabelle() {

		//Eigenes Raster
		
		Grid eigenesRasterLinks = new Grid(8, 8);
		
		eigenesRasterLinks.setWidget(1, 2, unterhaltung);
		eigenesRasterLinks.setWidget(6, 2, sendenN);
		eigenesRasterLinks.setWidget(4, 3, chatMit);
		eigenesRasterLinks.setWidget(4, 2, nachricht);
		
		
		eigenesRasterLinks.setWidget(5, 2, nachTa);
		nachTa.setWidth("400px");
		
		//Scroller Unterhaltung
		
		ScrollPanel scroller = new ScrollPanel(uft);
	    scroller.setSize("450px", "100px");
	    
	    // Rahmen, Gr��e Unterhaltung
	    
	    eigenesRasterLinks.setWidget(2, 2, scroller);
		uft.setBorderWidth(5);
		
//		uft.setText(0, 0, "Hallo wie gehts?");
//		uft.setText(1, 1, "Gut und selbst?");
//		uft.setText(2, 1, "Hast was gelernt heute?");
//		uft.setText(3, 1, "Happy Bday");
//		uft.setText(4, 0, "Danke!!");
//		uft.setText(5, 1, "Text2Text2Text2Te \n xt2Text2Text2Text2Text2Text \n"
//						+ "2Text2Text2Text \n 2Text2Text2Text2 \n Text2Text2");
//		uft.setText(6, 1, "Gute Nacht");
	    
	    
	    //Scroller Chats
		
	    ScrollPanel chatss = new ScrollPanel(cft);
	    chatss.setSize("300px", "100px");
	   
	    
	    // Rahmen, Gr��e Chats
	    Grid eigenesRasterRechts = new Grid(8, 8);
	    
	    eigenesRasterRechts.setWidget(1, 0, chats);
	    
	    eigenesRasterRechts.setWidget(2, 0, chatss);
	    
	    cft.setBorderWidth(1);
		cft.setText(0, 0, "EMail");
	    cft.setText(0, 1, "Anzeigen");
	    
	    
	    cft.addStyleName("unter");
	    cft.getCellFormatter().addStyleName(0, 0, "unterH");
	    cft.getCellFormatter().addStyleName(0, 1, "unterH");
	   
	    
		Nutzer n = new Nutzer();

		int id = Integer.parseInt(Cookies.getCookie("userID"));
		n.setId(id);
		n.setNickname(Cookies.getCookie("userMail"));

		/**
		 * Liest alle Daten aus der DB und füllt sie in die FlexTable
		 */
		
		myAsync.getAlleEmpfaengerByAbsender(n, new AsyncCallback<ArrayList<Nutzer>>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(ArrayList<Nutzer> result) {
				

				
				int z = 1;
				for(final Nutzer nutzer : result){
					//Window.alert(nutzer.getEmail());
					
					cft.setText(z, 0, nutzer.getEmail());
					 
					/**
					 * Button wird erstellt für das spätere Anzeigen von Unterhaltung
					 */
					
					Button anzeigen = new Button ("Anzeigen");
				    
				    
				    cft.setWidget(z, 1, anzeigen);
				    
				    /**
					 * Der erstellte Button wird aufgerufen + neuer Clickhanlder
					 */
				    anzeigen.addClickHandler(new ClickHandler() {
						
						@Override
						public void onClick(ClickEvent event) {
							for(int i = 0; i < uft.getRowCount(); i++){
								uft.setText(i, 0, "");
								uft.setText(i, 1, "");
							}
							
							chatMit.setText(nutzer.getEmail()); 
							findeUnterhaltung(Cookies.getCookie("userID"), nutzer.getEmail());
							//Window.alert(""+uft.getRowCount());
						}
					});
				    
//				    entfernen.addClickHandler(new ClickHandler() {
//						
//						@Override
//						public void onClick(ClickEvent event) {
//							loescheUnterhaltung(Cookies.getCookie("userID"), nutzer.getEmail());
//						}
//					});
				    
				    z++;
					
				}
				
			}
		});
	    
	    

	    
	
	  //Clickhandler für nachricht senden 
	     
	    sendenN.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				if (nachTa.getText() != ""){
					nachrichtVerschicken(Cookies.getCookie("userID"), chatMit.getText());
				}
				
			}
		});
	    
	    
		
	    /**
		 * Hier werden die einzelnen Panel zusammengeführt und das Hauptpanel zurück geben
		 */
	    
		links.add(eigenesRasterLinks);
		rechts.add(eigenesRasterRechts);
		
		hauptP.add(links);
		hauptP.add(rechts);
		return hauptP;
		
		
		
	}
	
	//Methode wird nciht benutzt
	
	private void loescheUnterhaltung(String absender, String empfaenger){
		Window.alert(absender + " " + empfaenger);
		myAsync.deleteUnterhaltungsliste(absender, empfaenger, new AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Beim löschen ist ein Fehler aufgetreten! " + caught);
				
			}

			@Override
			public void onSuccess(Void result) {
				Window.alert("Löschen erfolgreich!");
				
			}
		});
		
		
//		(absender, empfaenger, new AsyncCallback<Void>() {
//
//			@Override
//			public void onFailure(Throwable caught) {
//				Window.alert("Beim l�schen ist ein Fehler aufgetreten! " + caught);
//			}
//
//			@Override
//			public void onSuccess(Integer result) {
//				if(result == 1){
//					Window.alert("L�schen erfolgreich!");
//				}
//			}
//		});
	}
	
	/**
	 * 
	 *Methode die für das finden der richtigen Unterhalugn benötigt wird
	 */
	
	private void findeUnterhaltung(String absender, String empfaenger){
		//Window.alert("Absender " + absender + " Empf�nger " + empfaenger);
		myAsync.getUnterhaltung(absender, empfaenger, new AsyncCallback<Unterhaltungsliste>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fehler in findeUnterhaltung in UnterhaltungsForm "+caught);
				
			}

			@Override
			public void onSuccess(Unterhaltungsliste result) {
				Unterhaltung u = new Unterhaltung();
				u.setId(result.getUnterhaltungID());
				zeigeUnterhaltung(u);
				
			}
			
		});
	}
	
	/**
	 * Methode für das verschicken der Nachricht
	 * 
	 */
	
	private void nachrichtVerschicken(final String absender, final String empfaenger){
		//Window.alert("Absender " + absender + " Empf�nger " + empfaenger);
		myAsync.getUnterhaltung(absender, empfaenger, new AsyncCallback<Unterhaltungsliste>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fehler in nachrichtVerschicken in UnterhaltungsForm "+caught);
				
			}

			@Override
			public void onSuccess(Unterhaltungsliste result) {
				final Unterhaltung u = new Unterhaltung();
				u.setId(result.getUnterhaltungID());
				//Window.alert(nachTa.getText());
				erstelleNachricht(nachTa.getText(), Cookies.getCookie("userMail"), empfaenger, u);
				
			}
			
		});
	}
	
	/**
	 * 
	 * Methode für das erstellen einer nachricht (TextArea)
	 *
	 */
	private void erstelleNachricht(String text, String nickname, String empf, Unterhaltung unterhaltung) {
		//Window.alert(text + " " + nickname + " " + " " + empf + " " + unterhaltung.getId());
		myAsync.createNachricht(text, nickname, empf, unterhaltung, new AsyncCallback<Nachricht>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Fehler bei CreateNachticht in NachrichtenForm: " + caught);
						
					}

					@Override
					public void onSuccess(Nachricht result) {
						nachTa.setValue("");
					}
		});
	}
	
	/**
	 * 
	 * Methode für das für das anzeigen der Unterhaltung
	 */
	
	private void zeigeUnterhaltung(Unterhaltung unterhaltung){
		
		myAsync.getNachrichtenByUnterhaltung(unterhaltung, new AsyncCallback<ArrayList<Nachricht>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fehler in zeigeUnterhaltung in UnterhaltungsForm "+caught);
				
			}

			@Override
			public void onSuccess(ArrayList<Nachricht> result) {

				
				int z =0;
				for(Nachricht n : result){
					if(String.valueOf(n.getNutzerID()) == Cookies.getCookie("userID")){
						uft.setText(z, 1, n.getText());
					}else {
						uft.setText(z, 0, n.getText());
					}
					z++;
				}
				
				
//				uft.setText(0, 0, "Hallo wie gehts?");
//				uft.setText(1, 1, "Gut und selbst?");
//				uft.setText(2, 1, "Hast was gelernt heute?");
//				uft.setText(3, 1, "Happy Bday");
//				uft.setText(4, 0, "Danke!!");
//				uft.setText(5, 1, "Text2Text2Text2Te \n xt2Text2Text2Text2Text2Text \n"
//								+ "2Text2Text2Text \n 2Text2Text2Text2 \n Text2Text2");
//				uft.setText(6, 1, "Gute Nacht");
				
			}
		});
	}
	
}