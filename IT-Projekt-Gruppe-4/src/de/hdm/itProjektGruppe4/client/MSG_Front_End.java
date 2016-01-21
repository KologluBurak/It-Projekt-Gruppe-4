package de.hdm.itProjektGruppe4.client;

import java.util.ArrayList;









//import com.google.appengine.repackaged.com.google.common.util.concurrent.AsyncCallable;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itProjektGruppe4.client.gui.HashtagTabelle;
import de.hdm.itProjektGruppe4.client.gui.NachrichtenForm;
import de.hdm.itProjektGruppe4.client.gui.NutzeraboTabelle;
import de.hdm.itProjektGruppe4.client.gui.UnterhaltungsForm;
//import de.hdm.itProjektGruppe4.shared.bo.Abonnement;

//import de.hdm.itProjektGruppe4.shared.ReportGenerator.*;
import de.hdm.itProjektGruppe4.shared.MessagingAdministration;
import de.hdm.itProjektGruppe4.shared.MessagingAdministrationAsync;
import de.hdm.itProjektGruppe4.shared.bo.*;

public class MSG_Front_End {

	private VerticalPanel hauptpanel = new VerticalPanel();
	VerticalPanel vertipanel = new VerticalPanel();
	HorizontalPanel horipanel1 = new HorizontalPanel();
	HorizontalPanel links = new HorizontalPanel();
	VerticalPanel rechts = new VerticalPanel();
	TextBox textfeld = new TextBox();
	Button button = new Button();
	Label label = new Label();

	// Anzeigen von dem Menue

	public void anzeigenMenu(String email) {

		// Hier werden verschiedene Commands angelegt

		Command nachrichtenErstellen = new Command() {
			public void execute() {
				rechts.clear();
				rechts.add(new HTML("<h2> Hier koennen Sie die Nachricht verfassen</h2>"));
				NachrichtenForm nf = new NachrichtenForm();

				rechts.add(nf);
				// MessagingAdministrationAsync myAsync =
				// (MessagingAdministrationAsync)
				// GWT.create(MessagingAdministration.class);

				// myAsync.getAllNutzer(new AsyncCallback<ArrayList<Nutzer>>() {
				//
				// @Override
				// public void onFailure(Throwable caught) {
				// DialogBox d = new DialogBox();
				// d.setText("Fehler: " + caught);
				// d.show();
				// }
				//
				// @Override
				// public void onSuccess(ArrayList<Nutzer> result) {
				// DialogBox d = new DialogBox();
				// String nutzer = "Anzahl: "+result.size()+" ";
				//
				// for(Nutzer n : result){
				// nutzer = nutzer + n.getNickname() + " ";
				// }
				//
				// d.setText(nutzer);
				// d.show();
				//
				// }
				// });
				MessagingAdministrationAsync myAsync = (MessagingAdministrationAsync) GWT
						.create(MessagingAdministration.class);

			
//				myAsync.getHashtagAbonnementById(9, new AsyncCallback<Hashtagabonnement>() {
//
//					@Override
//					public void onFailure(Throwable caught) {
//						// TODO Auto-generated method stub
//						DialogBox d = new DialogBox();
//						d.setText("Fehler: " + caught);
//						d.show();
//					}
//
//					@Override
//					public void onSuccess(Hashtagabonnement result) {
//						// TODO Auto-generated method stub
//						DialogBox d = new DialogBox();
//						d.setText("Erfolgreiche Ausgabe:  " + result.getNutzerID());
//						d.show();
//					}
//				});
//				Unterhaltung unterhaltung = new Unterhaltung();
//				unterhaltung.setId(1);
//				
//				myAsync.getNachrichtenByUnterhaltung(unterhaltung, new AsyncCallback<ArrayList<Nachricht>>() {
//
//					@Override
//					public void onFailure(Throwable caught) {
//						// TODO Auto-generated method stub
//						DialogBox d = new DialogBox ();
//						d.setText("Fehler 2: " + caught);
//						d.show();
//					}
//
//					@Override
//					public void onSuccess(ArrayList<Nachricht> result) {
//						DialogBox d = new DialogBox ();
//						String text ="Ergebnis: "+ result.size()+ " ";
//						for (Nachricht n : result){
//							text = text + n.getId() + " "; 
//							break;
//						}
//					
//						d.setText(text);
//						d.show();
//					}
//					
//				});
				
				
			}
		};

		// Hier werden verschiedene Commands angezeigt

		Command hashtagAnzeigen = new Command() {
			public void execute() {
				rechts.clear();
				rechts.add(new HTML("<h2>Hier sehen Sie ihre Hashtagabonnements</h2>"));
				HashtagTabelle hashT = new HashtagTabelle();
				rechts.add(hashT.zeigeTabelle());

			}
		};

		Command nutzeraboAnzeigen = new Command() {
			public void execute() {
				rechts.clear();
				rechts.add(new HTML("<h2>Hier sehen Sie ihre Nutzerabonnements</h2>"));
				NutzeraboTabelle naboT = new NutzeraboTabelle();
				rechts.add(naboT.zeigeTabelle());

			}
		};

		Command unterhaltungAnzeigen = new Command() {
			public void execute() {
				rechts.clear();
				rechts.add(new HTML("<h2>Hier sehen Sie ihre Unterhaltungen</h2>"));
				UnterhaltungsForm uForm = new UnterhaltungsForm();
				rechts.add(uForm.zeigeTabelle());

			}
		};



		// Menuebar und verschiede Reiter + Commands

		hauptpanel.add(vertipanel);
		hauptpanel.add(horipanel1);
		MenuBar nachM = new MenuBar(true);
		nachM.addItem("Nachricht schreiben", nachrichtenErstellen);
		MenuBar untM = new MenuBar(true);
		untM.addItem("Unterhaltung Anzeigen", unterhaltungAnzeigen);
		MenuBar hasM = new MenuBar(true);
		hasM.addItem("Hashtagabonnement Anzeigen", hashtagAnzeigen);
		MenuBar aboM = new MenuBar(true);
		aboM.addItem("Nutzerabonnement Anzeigen", nutzeraboAnzeigen);
		

		MenuBar menu = new MenuBar();
		menu.setAutoOpen(true);
		menu.setAnimationEnabled(true);
		menu.addItem("Nachricht", nachM);
		menu.addItem("Unterhaltung", untM);
		menu.addItem("Hashtag Abonnement", hasM);
		menu.addItem("Nutzeraboonement", aboM);
		

		Label user = new Label ("Wilkommen zurueck "+email);
		vertipanel.add(user);

		horipanel1.add(rechts);
		vertipanel.add(menu);
		horipanel1.setSpacing(5);

		RootPanel.get("starter").add(hauptpanel);

	}
}
