package de.hdm.itProjektGruppe4.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class UnterhaltungsForm {

	VerticalPanel hauptP = new VerticalPanel();
	
	//HorizontalPanel rechts = new HorizontalPanel();
	
	Label unterhaltung = new Label("Unterhaltung");
	Label nachricht = new Label("Nachricht");
	Label chats = new Label("Chats");
	TextArea nachTa = new TextArea();
	Button sendenN = new Button("Senden");
	FlexTable uft = new FlexTable();
	FlexTable cft = new FlexTable();
	
	
	// Methode zeige Tabelle
	
	public Widget zeigeTabelle() {

		//Eigenes Raster
		
		Grid eigenesRaster = new Grid(8, 8);
		
		eigenesRaster.setWidget(1, 2, unterhaltung);
		eigenesRaster.setWidget(6, 2, sendenN);
		
		eigenesRaster.setWidget(4, 2, nachricht);
		eigenesRaster.setWidget(1, 7, chats);
		
		eigenesRaster.setWidget(5, 2, nachTa);
		nachTa.setWidth("400px");
		
		//Scroller Unterhaltung
		
		ScrollPanel scroller = new ScrollPanel(uft);
	    scroller.setSize("450px", "100px");
	    
	    // Rahmen, Größe Unterhaltung
	    
	    eigenesRaster.setWidget(2, 2, scroller);
		uft.setBorderWidth(5);
	
		
		uft.setText(0, 0, "Hallo wie gehts?");
		uft.setText(1, 1, "Gut und selbst?");
		uft.setText(2, 1, "Hast was gelernt heute?");
		uft.setText(3, 1, "Happy Bday");
		uft.setText(4, 0, "Danke!!");
		uft.setText(5, 1, "Text2Text2Text2Te \n xt2Text2Text2Text2Text2Text \n"
						+ "2Text2Text2Text \n 2Text2Text2Text2 \n Text2Text2");
		uft.setText(6, 1, "Gute Nacht");
	    
	    
	    //Scroller Chats
		
	    ScrollPanel chatss = new ScrollPanel(cft);
	    chatss.setSize("100px", "100px");
	   
	    
	    // Rahmen, Größe Chats
	    
	    eigenesRaster.setWidget(2, 7, chatss);
	    
	    cft.setBorderWidth(1);
		cft.setText(0, 0, "Billy");
	    cft.setText(1, 0, "Duc");
	    cft.setText(2, 0, "Xunus");
	    cft.setText(3, 0, "Selimo");
	    cft.setText(4, 0, "Daniel");
	    cft.setText(5, 0, "DanielT");
	    
	  //Clickhandler
	    
	    
	    
	    
		
		// adden von Raster und VerticalPanel rechts
	    
		hauptP.add(eigenesRaster);
		//hauptP.add(rechts);
		return hauptP;
		
		
		
	}
	
	
}