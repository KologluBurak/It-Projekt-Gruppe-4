package de.hdm.itProjektGruppe4.client.gui;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class UnterhaltungsForm {

	VerticalPanel hauptP = new VerticalPanel();

	Label unterhaltung = new Label("Unterhaltung");
	Label nachricht = new Label("Nachricht");
	Label chats = new Label("Chats");
	TextArea nachTa = new TextArea();
	Button neueN = new Button("Neue Nachricht");
	Button sendenN = new Button("Senden");

	public Widget zeigeTabelle() {

		Grid eigenesRaster = new Grid(8, 8);
		eigenesRaster.setWidget(1, 2, unterhaltung);
		eigenesRaster.setWidget(3, 0, neueN);
		eigenesRaster.setWidget(6, 3, sendenN);
		eigenesRaster.setWidget(5, 2, nachricht);
		eigenesRaster.setWidget(1, 7, chats);
		eigenesRaster.setWidget(5, 3, nachTa);

		hauptP.add(eigenesRaster);
		return hauptP;

	}
}