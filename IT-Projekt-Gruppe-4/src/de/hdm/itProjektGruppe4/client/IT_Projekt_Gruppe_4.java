package de.hdm.itProjektGruppe4.client;


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
import de.hdm.itProjektGruppe4.shared.bo.LoginInfo;

public class IT_Projekt_Gruppe_4 implements EntryPoint {

	  private LoginInfo loginInfo = null;
	  private VerticalPanel loginPanel = new VerticalPanel();
	  private Label loginLabel = new Label(
	      "Please sign in to your Google Account to access the itprojekt application.");
	  private Anchor signInLink = new Anchor("Sign In");
	
	   public void onModuleLoad() {
			 // DialogBox d1 = new DialogBox();
	    	 // d1.setText("On Module Load funktioniert!");
	    	 //d1.show();
		    // Check login status using login service.
		    LoginServiceAsync loginService = GWT.create(LoginService.class);
		    loginService.login(GWT.getHostPageBaseURL(), new AsyncCallback<LoginInfo>() {
		      public void onFailure(Throwable error) {
		    	  
		    	//  DialogBox d = new DialogBox();
		    	//  d.setText("Fehler: " + error.getMessage());
		    	//  d.show();
		      }

		      public void onSuccess(LoginInfo result) {
		        
		 //   	  DialogBox d = new DialogBox();
		 //   	  d.setText("Angemeldet: " + result.getNickname());
		 //   	  d.show();
		    	  
		    	  loginInfo = result;
		        if(loginInfo.isLoggedIn()) {
		        	// TODO Logik zum überprüfen von User: ist User mit Email in DB? Wenn ja -> zeige loadView -> nein dann lege User in Db
		          loadView();
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
		