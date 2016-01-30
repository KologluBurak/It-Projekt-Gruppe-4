package de.hdm.itProjektGruppe4.client.gui;

    import java.sql.Date;
import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.Cookies;

import de.hdm.itProjektGruppe4.shared.MessagingAdministration;
import de.hdm.itProjektGruppe4.shared.MessagingAdministrationAsync;
import de.hdm.itProjektGruppe4.shared.bo.Abonnement;
import de.hdm.itProjektGruppe4.shared.bo.Hashtag;
import de.hdm.itProjektGruppe4.shared.bo.Hashtagabonnement;
import de.hdm.itProjektGruppe4.shared.bo.Markierungsliste;
import de.hdm.itProjektGruppe4.shared.bo.Nachricht;
import de.hdm.itProjektGruppe4.shared.bo.Nutzer;
import de.hdm.itProjektGruppe4.shared.bo.Nutzerabonnement;
import de.hdm.itProjektGruppe4.shared.bo.Unterhaltung;
import de.hdm.itProjektGruppe4.shared.bo.Unterhaltungsliste;

/**
 * Enthält Methoden und Elemente die für das schreiben einer Nachricht erfolderlich sind
 * @author Di Giovanni
 *
 */


	public class NachrichtenForm extends VerticalPanel{

		/**
		 * Widgets und Panels werden erstellt 
		 */
		
		HorizontalPanel nachrichtenButtonpan = new HorizontalPanel();
	
		Button sendeButton = new Button ("senden");
		Button empfaengerEntfernen = new Button ("Empfaenger Entfernen");
		TextBox hashtagFenster = new TextBox();
		Button hashtagHinzu = new Button("Hashtag Hinzufuegen");
		TextArea nachta = new TextArea();
		FlexTable flexTable = new FlexTable();
		Label uCheck = new Label ();
		Label an = new Label("Senden an");
		

		Nutzer empf = new Nutzer();
		MessagingAdministrationAsync myAsync = GWT.create(MessagingAdministration.class);
		
		
		// Konstruktor
		public NachrichtenForm(){
			
			
			/**
			 * ein grid wird erstellt für eine bessere übersicht
			 */
			
			Grid eigenesRaster = new Grid (9, 9);
			this.add(eigenesRaster);
			
			Label nachrichtB = new Label("Nachricht");
			Label hashTB = new Label("Hashtag");
			Label empfH = new Label("Empfaenger Hinzufuegen");

			//Clickhandler
			
			hashtagHinzu.addClickHandler(new ClickHandler() {
			
				@Override
				public void onClick(ClickEvent event) {
//					Window.alert("done");
					erstelleHashtag();
					
				}});
			
			sendeButton.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
//					Unterhaltung unterhaltung = new Unterhaltung();
					int anf = 0;
					String userCheck = "";
					//Window.alert(uCheck.getText().length()+"");
					if(uCheck.getText() != "" && hashtagFenster.getText()== ""){
						for(int i = 1; i <= uCheck.getText().length(); i++){
							if(uCheck.getText().charAt(i) == ','){
								userCheck = uCheck.getText().substring(anf, i).toString();
								anf = i + 2;
								pruefeUnterhaltung(Cookies.getCookie("userID"), userCheck);
								//Window.alert(userCheck + " anf " + anf); 
							}
						}
					}else if(uCheck.getText() == "" && hashtagFenster.getText() != ""){
						for(int i = 1; i <= hashtagFenster.getText().length(); i++){
							if(hashtagFenster.getText().charAt(i) == ','){
								userCheck = hashtagFenster.getText().substring(anf, i).toString();
								anf = i + 2;
//								Window.alert(userCheck + " anf " + anf); 
								pruefeUnterhaltung(Cookies.getCookie("userID"), userCheck);
							}
						}
					}
					else if(uCheck.getText() == "" && hashtagFenster.getText() == "") {
						//Window.alert("Else in Click");
						pruefeUnterhaltung(Cookies.getCookie("userID"), userCheck);
					}
//					else if(uCheck.getText() != "" && hashtagFenster.getText() != "") {
//						//Window.alert("Else in Click");
//						
//						pruefeUnterhaltung(Cookies.getCookie("userID"), userCheck);
//					}
					
					//nach Nachricht versenden wird Textfeld geleert.
					
					//nachta.setText("");
					//setUnterhaltung();

//					unterhaltung.setId(getMaxID().getId());
//					Window.alert(unterhaltung.getId() + " " + Cookies.getCookie("userMail") +" " + uCheck.getText());
//					setUListe(unterhaltung, Cookies.getCookie("userMail"), uCheck.getText());
//					erstelleNachricht(nachta.getText(), Cookies.getCookie("userMail"), uCheck.getText(), unterhaltung);					
					//getMaxID(); //nachta.getText(), uCheck.getText());
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
			
			/**
			 * hier werden die einzelnen Widgets in das grid platziert
			 */
			
			eigenesRaster.setWidget(0, 0, nachrichtB);
			eigenesRaster.setWidget(1, 0, nachta);
			eigenesRaster.setWidget(4, 0, hashTB);
			eigenesRaster.setWidget(5, 0, hashtagFenster);
			eigenesRaster.setWidget(2, 0, sendeButton);
//			eigenesRaster.setWidget(6, 0, hashtagHinzu);
			eigenesRaster.setWidget(8, 0, flexTable);
			eigenesRaster.setWidget(1, 1, uCheck);
			eigenesRaster.setWidget(0, 1, an);
			eigenesRaster.setWidget(0, 5, empfaengerEntfernen);
			
			nachta.setWidth("300px");
			nachta.setHeight("100px");
			
			flexTable.setBorderWidth(5);
			
			flexTable.setText(0, 0, "ID");
			flexTable.setText(0, 2, "E-Mail");
			flexTable.setText(0, 3, "Hinzufuegen");
			
			 /**
			  * Style für die CSS
			  */
			
			flexTable.getCellFormatter().addStyleName(0, 0, "nachr");
			flexTable.getCellFormatter().addStyleName(0, 2, "nachr");
			flexTable.getCellFormatter().addStyleName(0, 3, "nachr");
			
			empfaengerEntfernen.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					empfEntfernen();
					
				}
			});
			
			/**
			 * Liest alle Daten aus der DB und füllt sie in die FlexTable
			 */
			
			myAsync.getAllNutzer(new AsyncCallback<ArrayList<Nutzer>>() {

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					Window.alert("Fehlermeldung NachrichtenForm: "+caught); 
				}

				@Override
				public void onSuccess(ArrayList<Nutzer> result) {
					int zeileCounter = 1;
					ArrayList<String> kontakte = new ArrayList<String>();
					for (final Nutzer nutzer : result) {
						if(nutzer.getEmail() != Cookies.getCookie("userMail")){
							
							/**
							 * Button wird erstellt der später hinzufügen soll
							 */
							Button bModifizieren = new Button("hinzufuegen");
							
							final Label checked = new Label("");
							Label id = new Label(String.valueOf(nutzer.getId()));
							Label nicknameID = new Label (String.valueOf(nutzer.getEmail()));
							
							
							flexTable.setWidget(zeileCounter, 0, id);
							flexTable.setWidget(zeileCounter, 1, checked); 
							flexTable.setWidget(zeileCounter, 2, nicknameID);
							flexTable.setWidget(zeileCounter, 3, bModifizieren);
							
	//						empf = new Nutzer();
	//						empf.setId(nutzer.getId());
	//						empf.setEmail(nutzer.getEmail());
	//						empf.setNachname(nutzer.getNachname());
	//						empf.setVorname(nutzer.getVorname());
	//						empf.setNickname(nutzer.getNickname());
							
							
							/**
							 * Der erstellte Button wird aufgerufen + neuer Clickhanlder
							 */
							bModifizieren.addClickHandler(new ClickHandler() {
								
								private String userCheck;
	
								@Override
								public void onClick(ClickEvent event) {
									//Window.alert("klick");
	//								empfaengerHinzu();
									
	//								
	//								if(bModifizieren.getText() == "hinzufuegen"){
	//									bModifizieren.setText("hinzugefuegt");
	//								}else{
	//									bModifizieren.setText("hinzufuegen");
	//								}
									if(checked.getText() != "X"){
										setEmpf(nutzer.getEmail());
										checked.setText("X"); 
									}
									//kontakte.add(nutzer.getEmail());
	//								int anf = 0;
	//								String userCheck = "";
	//								int laenge = uCheck.getText().length();
	//								for(int i = 1; i <= uCheck.getText().length(); i++){
	//									if(uCheck.getText().charAt(i) == ','){
	//										userCheck = uCheck.getText().substring(anf, i);
	//										
	//										anf = i + 2;
	//										Window.alert(userCheck + " anf " + anf); 
	//									}
	//									else if(laenge - anf > 0){
	//										userCheck = uCheck.getText().substring(anf, laenge);
	//										
	//										anf = i + 2;
	//										Window.alert(userCheck + " anf letzte " + anf);
	//										break;
	//									}
										
	//								}
								}
	
								
	
							});
			
							zeileCounter++;
						
						}
					}
					
				}
			});
			
		}
		
		/**
		 * Methode die überprüft ob hashtag schon existiert 
		 */
		
	public void pruefeHashtag(final String text){
		myAsync.getHashtagByHashtag(text, new AsyncCallback<Hashtag>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fehler bei pruefeHashtag " + caught); 
			}

			@Override
			public void onSuccess(Hashtag result) {
				if(result == null){
					erstelleHashtag(text);
				}
			}
		});
	}
		
	/**
	 * Methode die es ermöglicht ein hashtag zu erstellen
	 */
	
	public void erstelleHashtag(String text){
		Window.alert(text + " Text erstelle Hashtag");
		if (text != ""){
			myAsync.createHashtag(text, new AsyncCallback<Hashtag>() {
	 
				@Override
				public void onFailure(Throwable caught) {
					DialogBox d = new DialogBox();
					d.setText("Fehler 1:" + caught);
					d.show();
					
				}
	
				@Override
				public void onSuccess(Hashtag result) {
					// TODO Auto-generated method stub
					
				}
			});
		}
		
	}
	/**
	 * Methode die es ermöglicht ein hashtag zu erstellen
	 */
	
	
	public void erstelleHashtag(){
		
		myAsync.createHashtag(hashtagFenster.getText(), new AsyncCallback<Hashtag>() {
 
			@Override
			public void onFailure(Throwable caught) {
				DialogBox d = new DialogBox();
				d.setText("Fehler 1:" + caught);
				d.show();
				
			}

			@Override
			public void onSuccess(Hashtag result) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
		private void empfaengerHinzu() {
			Window.alert("Klick!");
			
		}
	public void setEmpf(String user){
		if (uCheck.getText() == ""){
			uCheck.setText(user + ", ");
		}else{
			uCheck.setText(uCheck.getText() + user + ", ");
		}
	}
	
	/**
	 * Methode die es ermöglicht eine Nachricht zu erstellen
	 */
	
	
	private void erstelleNachricht(final String text, String nickname, final String empf, Unterhaltung unterhaltung, final String hashtagText) {
		
		myAsync.createNachricht(text, nickname, empf, unterhaltung, new AsyncCallback<Nachricht>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Fehler bei CreateNachticht in NachrichtenForm: " + caught);
						
					}

					@Override
					public void onSuccess(Nachricht result) {
						
						Nachricht nachricht = new Nachricht();
						nachricht.setId(result.getId());
						if(hashtagFenster.getText() != ""){
							Hashtag hashtag = new Hashtag();
							int anf2 = 0;
							//String hashtagCheck = "";
							
//							for(int i = 1; i <= hashtagFenster.getText().length(); i++){
//								
//								if(hashtagFenster.getText().charAt(i) == ','){
//									
//									//hashtagCheck = hashtagFenster.getText().substring(anf2, i).toString();
//									anf2 = i + 2;
//									Window.alert(hashtagFenster.getText().substring(anf2, i).toString() + " int " + i + " anf2 " +anf2); 
									//if(hashtagFenster.getText().substring(anf2, i).toString() != ""){
										pruefeHashtag(hashtagText); //hashtagFenster.getText());
										hashtag.setBezeichnung(hashtagText);
										setMarkierungsliste(nachricht, hashtag);
//									}
//								}
//							}
							//erstelleHashtag("");
						}

						//Window.alert("Nachricht versendet an " + empf + " Text " + text);
					}
		});
	}
	
	/**
	 * Methode für das erstellen von Markierungsliste (setzen von hashtag wert und nachricht wert)
	 */
	
	
	private void setMarkierungsliste(Nachricht nachricht, Hashtag hashtag){
		Window.alert("Nachrichten und Hashtagid "+ nachricht.getId() + " " + hashtag.getBezeichnung());
		myAsync.createMarkierungsliste(nachricht, hashtag, new AsyncCallback<Markierungsliste>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fehler in der setMarkierungsliste in NachrichtenForm " + caught);
				
			}

			@Override
			public void onSuccess(Markierungsliste result) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	/**
	 * 
	 * Methode für das Fragen für die höchste Unterhaltung
	 */
	
	private void getMaxID(final String empfaenger){

		myAsync.getMaxID(new AsyncCallback<Unterhaltung>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fehler in getMaxID in NachrichtenForm " + caught);
			}

			@Override
			public void onSuccess(Unterhaltung result) {
				
				String empf = empfaenger;
				String text = nachta.getText();
				
				setUListe(result, Cookies.getCookie("userMail"), empf);
				setUListe(result, empf, Cookies.getCookie("userMail"));
				
				//ArrayList<Hashtag> hashtag = new ArrayList<Hashtag>();

				erstelleNachricht(text, Cookies.getCookie("userMail"), empf, result, "");

				//Window.alert("Ihre Nachricht wurde gesendet!");

			}
		});
	}
	
	/**
	 * Methode für das erstellen von Unterhaltung
	 */
	
	private void setUnterhaltung(){
		Date datum = new Date(0);
		myAsync.createUnterhaltung(datum , new AsyncCallback<Unterhaltung>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fehler in setUnterhaltung in NachrichtenForm " + caught);
				
			}

			@Override
			public void onSuccess(Unterhaltung result) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	/**
	 * 
	 * Methode für das erstellen von einer Unterhaltungsliste
	 */
	
	private void setUListe(Unterhaltung u, String absender, String empf){
		myAsync.createUnterhaltungsliste(u, absender, empf, new AsyncCallback<Unterhaltungsliste>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fehler in setUListe in NachrichtenForm " + caught);
			}

			@Override
			public void onSuccess(Unterhaltungsliste result) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	/**
	 * 
	 * Methode  für die überprüfung der Unterhaltung
	 */
	private void pruefeUnterhaltung(String absender, final String empfaenger){
//		Window.alert("Beim pruefeUnterhaltung" + empfaenger);;
		if(empfaenger !="" && uCheck.getText() != ""){
//			Window.alert("If " + " " + absender + " " + empfaenger);
			myAsync.getUnterhaltung(absender, empfaenger, new AsyncCallback<Unterhaltungsliste>() {

				

				@Override
				public void onFailure(Throwable caught) {
					Window.alert("Fehler in pruefeUnterhaltung in NachrichtenForm " + caught);
				}
	
				@Override
				public void onSuccess(Unterhaltungsliste result) {
					String empf = empfaenger;
					//Window.alert("ID "+result.getUnterhaltungID());
					if(result.getUnterhaltungID() > 0 && hashtagFenster.getText() == ""){
						//Wenn Unterhaltung bereits besteht
						Unterhaltung u = new Unterhaltung();
						u.setId(result.getUnterhaltungID());
						//Window.alert("Unterhaltung besteht bereits.ID "+result.getUnterhaltungID());
	
						erstelleNachricht(nachta.getText(), Cookies.getCookie("userMail"), empf, u, "");
						Window.alert("Nachricht verschickt.");
						//nachta.setText("");
					} else if( hashtagFenster.getText() == "") {
						//Window.alert("Neue Unterhaltung wurde erstellt.ID "+result.getUnterhaltungID());
						//Wenn Unterhaltung nicht existiert
						setUnterhaltung();
						getMaxID(empf);
						Window.alert("Nachricht verschickt.");
						
					} 
					
				}
			});
		}else if( hashtagFenster.getText() != "" && uCheck.getText() == ""){
//			Window.alert("weiter gehts mit getHashtagAbso");
			
			getHashtagAbos(empfaenger); //hashtagFenster.getText().toString());
		}else if( hashtagFenster.getText() == "" && uCheck.getText() == ""){
			//Window.alert("weiter gehts mit getAbos");
			getAbos();
			
		}
//		else if (hashtagFenster.getText() != "" && uCheck.getText() != ""){
//			int anf = 0;
//			String userCheck = "";
//			for(int i = 1; i <= uCheck.getText().length(); i++){
//				if(uCheck.getText().charAt(i) == ','){
//					userCheck = uCheck.getText().substring(anf, i).toString();
//					final String userEmpf = uCheck.getText().substring(anf, i).toString();;
//					anf = i + 2;
////					Window.alert(userCheck + " Absender " + absender + " Empf�nger " + empfaenger);
//					myAsync.getUnterhaltung(absender, userCheck, new AsyncCallback<Unterhaltungsliste>() {
//						
//						@Override
//						public void onFailure(Throwable caught) {
//							Window.alert("Fehler in pruefeUnterhaltung in NachrichtenForm 2 " + caught);
//						}
//
//						@Override
//						public void onSuccess(Unterhaltungsliste result) {
//							String empf = userEmpf;
//
////							Window.alert("ID "+result.getUnterhaltungID());
//							if(result.getUnterhaltungID() > 0){
//								//Wenn Unterhaltung bereits besteht
//								Unterhaltung u = new Unterhaltung();
//								u.setId(result.getUnterhaltungID());
////								Window.alert("Unterhaltung besteht bereits.ID "+result.getUnterhaltungID());
//
//								erstelleNachricht(nachta.getText(), Cookies.getCookie("userMail"), empf, u);
//								//nachta.setText("");
//							} else if(result.getUnterhaltungID() == 0){
////								Window.alert("Neue Unterhaltung wurde erstellt.ID "+result.getUnterhaltungID());
//								//Wenn Unterhaltung nicht existiert
//								setUnterhaltung();
//								getMaxID(userEmpf);
//							} 
//						}
//					});
//				}
//			}

//			int anf2 = 0;
//			for(int i = 1; i <= hashtagFenster.getText().length(); i++){
//				Window.alert(hashtagFenster.getText().charAt(i)+" " + i);
//				if(hashtagFenster.getText().charAt(i) == ','){
//					userCheck = hashtagFenster.getText().substring(anf2, i).toString();
//					anf2 = i + 2;
//					Window.alert(hashtagFenster.getText().substring(anf2, i).toString() + " int " + i); 
//					getHashtagAbos(hashtagFenster.getText().substring(anf2, i).toString());
//				}
//			}
//		}
	}
	
	/**
	 * 
	 * Methode für das ausgeben des Hashtag bei der Hashtag ID
	 */
	
	private void getHashtagAbos(final String text){
//		Window.alert(text + " getHashtagAbos");
		myAsync.getHashtagAbonnementByHashtagId(text, new AsyncCallback<ArrayList<Hashtagabonnement>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fehler bei getHashtagAbos " + caught);
			}

			@Override
			public void onSuccess(ArrayList<Hashtagabonnement> result) {
				
				for(Hashtagabonnement ha : result){
					Nutzer empf = new Nutzer();
					int id = Integer.parseInt(Cookies.getCookie("userID"));
					
					if(ha.getNutzerID() != id){
						empf.setId(ha.getNutzerID());
						Nutzer absender = new Nutzer();
						absender.setNickname(Cookies.getCookie("userMail"));
						//Window.alert("gethashtagAbos");
						getNutzerHashtagName(absender, empf, text);
					}
				}
			}
		});
	}
	
	/**
	 * 
	 * Methode für das ausgeben von Nutzer bei seiner id
	 */
	
	private void getNutzerHashtagName(final Nutzer absender, final Nutzer empf, final String hashtagText){
		myAsync.getNutzerById(empf.getId(), new AsyncCallback<Nutzer>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fehler bei getNutzerName " + caught);
				
			}

			@Override
			public void onSuccess(Nutzer result) {
				//Window.alert(result.getNickname());
				unterhaltungHashtagExistenz(Cookies.getCookie("userID"), result.getEmail(), hashtagText);
				
			}
		});
	}
	
	/**
	 * Methdode 
	 * 
	 */
	private void unterhaltungHashtagExistenz(String absender, final String empfaenger, final String hashtagText){
		myAsync.getUnterhaltung(absender, empfaenger, new AsyncCallback<Unterhaltungsliste>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fehler bei unterhaltungExistenz " +caught);
				
			}

			@Override
			public void onSuccess(Unterhaltungsliste result) {
				String empf = empfaenger;
				//Window.alert("ID "+result.getUnterhaltungID());
				if(result.getUnterhaltungID() > 0 && hashtagFenster.getText() != ""){
					//Wenn Unterhaltung bereits besteht
					Unterhaltung u = new Unterhaltung();
					u.setId(result.getUnterhaltungID());
//					Window.alert("Unterhaltung besteht bereits.ID "+result.getUnterhaltungID());
//					Window.alert("Nachricht an " + empf + " Nachricht " + nachta.getText());

					erstelleNachricht(nachta.getText(), Cookies.getCookie("userMail"), empf, u, hashtagText);
					//nachta.setText("");
				} else {
					//Window.alert("Neue Unterhaltung wurde erstellt.ID "+result.getUnterhaltungID());
					//Wenn Unterhaltung nicht existiert
					setUnterhaltung();
					getMaxID(empf);
					//nachta.setText("");ht
				}
				
			}
		});
	}

	
	/**
	 *  Methode für das ausgeben aller Nutzerabonnements
	 */
	
	private void getAbos(){
		myAsync.getAllNutzerabonnements(Cookies.getCookie("userID"), new AsyncCallback<ArrayList<Nutzerabonnement>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fehler bei getAbos " + caught);	
			}

			@Override
			public void onSuccess(ArrayList<Nutzerabonnement> result) {
				
				for(Nutzerabonnement na : result){
					
					Nutzer nutzer = new Nutzer();
					nutzer.setId(na.getDerBeobachteteID());
					Nutzer absender = new Nutzer();
					absender.setNickname(Cookies.getCookie("userMail"));
					getNutzerAboName(absender, nutzer);
				}
				
			}
		});
	}
	
	
	/**
	 * 
	 * Methode
	 */
	
	private void getNutzerAboName(final Nutzer absender, final Nutzer empf){
		myAsync.getNutzerById(empf.getId(), new AsyncCallback<Nutzer>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fehler bei getNutzerName " + caught);
				
			}

			@Override
			public void onSuccess(Nutzer result) {
				//Window.alert(result.getNickname());
				unterhaltungAboExistenz(Cookies.getCookie("userID"), result.getEmail());
				
			}
		});
		
	}
		
/**
 * Methode für das entfernen von empfänger bei ucheck
 */
	
	private void empfEntfernen() {
		uCheck.setText("");
		myAsync.getAllNutzer(new AsyncCallback<ArrayList<Nutzer>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fehler beim Aufrufen der Nutzer " + caught); 
				
			}

			@Override
			public void onSuccess(ArrayList<Nutzer> result) {
				uCheck.setText(""); 
				
				myAsync.getAllNutzer(new AsyncCallback<ArrayList<Nutzer>>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						Window.alert("Fehlermeldung NachrichtenForm: "+caught); 
					}

					@Override
					public void onSuccess(ArrayList<Nutzer> result) {
						int zeileCounter = 1;
						ArrayList<String> kontakte = new ArrayList<String>();
						for (final Nutzer nutzer : result) {
							if(nutzer.getEmail() != Cookies.getCookie("userMail")){
								Button bModifizieren = new Button("hinzufuegen");
								final Label checked = new Label("");
								Label id = new Label(String.valueOf(nutzer.getId()));
								Label nicknameID = new Label (String.valueOf(nutzer.getEmail()));
								
								
								flexTable.setWidget(zeileCounter, 0, id);
								flexTable.setWidget(zeileCounter, 1, checked); 
								flexTable.setWidget(zeileCounter, 2, nicknameID);
								flexTable.setWidget(zeileCounter, 3, bModifizieren);

								bModifizieren.addClickHandler(new ClickHandler() {
									
									private String userCheck;
		
									@Override
									public void onClick(ClickEvent event) {

										if(checked.getText() != "X"){
											setEmpf(nutzer.getEmail());
											checked.setText("X"); 
										}

									}
		
									
		
								});
				
								zeileCounter++;
							
							}
						}
						
					}
				});
			}
		});
	}

	/**
	 * 
	 * Methode
	 */
	
	private void unterhaltungAboExistenz(String absender, final String empfaenger){
		//Window.alert("unterhaltungAboExistenz Parameter " + absender + " " + empfaenger);
		myAsync.getUnterhaltung(absender, empfaenger, new AsyncCallback<Unterhaltungsliste>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fehler bei unterhaltungExistenz " +caught);
				
			}

			@Override
			public void onSuccess(Unterhaltungsliste result) {
				String empf = empfaenger;
//				Window.alert("ID "+result.getUnterhaltungID());
				if(result.getUnterhaltungID() > 0 && hashtagFenster.getText() == ""){
					//Wenn Unterhaltung bereits besteht
					Unterhaltung u = new Unterhaltung();
					u.setId(result.getUnterhaltungID());
					//Window.alert("Unterhaltung besteht bereits.ID "+result.getUnterhaltungID());
					
//					Window.alert("Nachricht an " + empf + " Nachricht " + nachta.getText());

					erstelleNachricht(nachta.getText(), Cookies.getCookie("userMail"), empf, u, "");
					//nachta.setText("");
				} else {
//					Window.alert("Neue Unterhaltung wurde erstellt.ID "+result.getUnterhaltungID());
					//Wenn Unterhaltung nicht existiert
					setUnterhaltung();
					getMaxID(empf);
					//nachta.setText("");
				}
			}
		});
	}
}