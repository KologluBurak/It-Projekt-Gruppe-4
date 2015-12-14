package de.hdm.itProjektGruppe4.client;

    import com.google.gwt.user.client.Command;
	import com.google.gwt.user.client.ui.Button;
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

	public class MSG_Front_End {

		private VerticalPanel hauptpanel = new VerticalPanel ();
		VerticalPanel vertipanel = new VerticalPanel ();
		HorizontalPanel horipanel1 = new HorizontalPanel();
		HorizontalPanel links = new HorizontalPanel();
		VerticalPanel rechts = new VerticalPanel();
		TextBox textfeld = new TextBox();
		Button button = new Button();
		Label label = new Label ();
		
		
		//Anzeigen von dem Menue
		
		public void anzeigenMenu () {
			
			//Hier werden verschiedene Commands angelegt
			
			Command nachrichtenErstellen = new Command(){
				public void execute () {
					rechts.clear();
					rechts.add(new HTML("<h2> Hier koennen Sie die Nachricht verfassen</h2>"));
					NachrichtenForm nf = new NachrichtenForm();
					rechts.add(nf);
						}};
			
			//Hier werden verschiedene Commands angezeigt
						
			Command hashtagAnzeigen = new Command() {
				public void execute() {
					rechts.clear();
					rechts.add(new HTML("Hier sehen Sie ihre Hashtagabonnements"));
					HashtagTabelle hashT = new HashtagTabelle ();
					rechts.add(hashT.zeigeTabelle());
					
				}
			};
			
			Command nutzeraboAnzeigen = new Command() {
				public void execute() {
				rechts.clear();
				rechts.add(new HTML("Hier sehen Sie ihre Nutzerabonnements"));
				NutzeraboTabelle naboT = new NutzeraboTabelle();
				rechts.add(naboT.zeigeTabelle());
					
				}
			};
						
			Command unterhaltungAnzeigen = new Command() {
				public void execute() {
				rechts.clear();
				rechts.add(new HTML("Hier sehen Sie ihre Unterhaltungen"));
				UnterhaltungsForm uForm = new UnterhaltungsForm();
				rechts.add(uForm.zeigeTabelle());
				
						}
			};
						
			//Menuebar und verschiede Reiter + Commands
			
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
			MenuBar repM = new MenuBar(true);
			
			MenuBar menu = new MenuBar();
			menu.setAutoOpen(true);
			menu.setAnimationEnabled(true);
			menu.addItem("Nachricht", nachM);
			menu.addItem("Unterhaltung", untM);
			menu.addItem("Hashtag Abonnement", hasM);
			menu.addItem("Nutzeraboonement", aboM);
			menu.addItem("Report", repM);
			
			
			vertipanel.add(new HTML("Unreal Messenger GR"));
			
			horipanel1.add(rechts);
			vertipanel.add(menu);
			horipanel1.setSpacing(5);
			
			RootPanel.get("starter").add(hauptpanel);
			
		}
	}
	

