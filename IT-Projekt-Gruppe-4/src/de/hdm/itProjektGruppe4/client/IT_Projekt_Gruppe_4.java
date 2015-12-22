package de.hdm.itProjektGruppe4.client;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itProjektGruppe4.shared.LoginService;
import de.hdm.itProjektGruppe4.shared.LoginServiceAsync;
import de.hdm.itProjektGruppe4.shared.MessagingAdministration;
import de.hdm.itProjektGruppe4.shared.MessagingAdministrationAsync;
import de.hdm.itProjektGruppe4.shared.bo.LoginInfo;
import de.hdm.itProjektGruppe4.shared.bo.Nachricht;
import de.hdm.itProjektGruppe4.shared.bo.Nutzer;
import de.hdm.itProjektGruppe4.shared.bo.Unterhaltung;

public class IT_Projekt_Gruppe_4 implements EntryPoint {

	  private LoginInfo loginInfo = null;
	  private VerticalPanel loginPanel = new VerticalPanel();
	  private Label loginLabel = new Label(
	      "Bitte Melden Sie sich mit Ihren Google Account, um einen Zugriff auf die App zu haben.");

	  private Anchor signInLink = new Anchor("Sign In");
	   public void onModuleLoad() {

		    // Check login status using login service.
		    LoginServiceAsync loginService = GWT.create(LoginService.class);
		    loginService.login(GWT.getHostPageBaseURL(), new AsyncCallback<LoginInfo>() {
		      public void onFailure(Throwable error) {
		    	  
		    	//  DialogBox d = new DialogBox();
		    	//  d.setText("Fehler: " + error.getMessage());
		    	//  d.show();
		      }

		      public void onSuccess(LoginInfo result) {
				
		    	loginInfo = result;
		        if(loginInfo.isLoggedIn()) {
		        	MessagingAdministrationAsync myAsync = GWT.create(MessagingAdministration.class);
		        	
			        	myAsync.createNutzer("Murat", "Mustermann", loginInfo.getEmailAddress(), loginInfo.getNickname(), new AsyncCallback<Nutzer>() {

							@Override
							public void onFailure(Throwable caught) {
								// TODO Auto-generated method stub
								DialogBox d = new DialogBox();
								d.setText("fehler: " + caught);
								d.show();
							}

							@Override
							public void onSuccess(Nutzer result) {
								// TODO Auto-generated method stub
//								DialogBox d = new DialogBox();
//								d.setText("Gespeichert");
//								d.show();
								loadView();
							}
						});
					

		        } else {
		          loadLogin();
		        }
		      }
		    });
		   
	   }
	   
	   private void loadLogin() {
		    // Assemble login panel.
		    signInLink.setHref(loginInfo.getLoginUrl());
		    loginPanel.add(loginLabel);
		    loginPanel.add(signInLink);
		    RootPanel.get("starter").add(loginPanel);
		  }
	   
	   public void loadView(){


		   MSG_Front_End neu = new MSG_Front_End();
			neu.anzeigenMenu();
			
	   }
	   
}
		