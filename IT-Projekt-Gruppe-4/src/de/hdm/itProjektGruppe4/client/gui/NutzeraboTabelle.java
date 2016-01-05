package de.hdm.itProjektGruppe4.client.gui;


import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.hdm.itProjektGruppe4.shared.MessagingAdministration;
import de.hdm.itProjektGruppe4.shared.MessagingAdministrationAsync;




		public class NutzeraboTabelle {

			VerticalPanel hauptP = new VerticalPanel();
			
			MessagingAdministrationAsync myAsync = GWT.create(MessagingAdministration.class);
			
			
			public Widget zeigeTabelle() {
				
				final FlexTable flexTable = new FlexTable();
								
				
				flexTable.setText(0, 1, "Nickname");
				flexTable.setText(0, 2, "Nachname");
				flexTable.setText(0, 3, "Vorname");
				flexTable.setText(0, 4, "E-Mail Adresse");
				flexTable.setText(0, 5, "Entfernen");
				
				flexTable.setCellPadding(4);
				
				 flexTable.addStyleName("nutzerabo");
				 flexTable.getCellFormatter().addStyleName(0, 1, "nutzeraboNumericColumn");
				 flexTable.getCellFormatter().addStyleName(0, 2, "nutzeraboNumericColumn");
				 flexTable.getCellFormatter().addStyleName(0, 3, "nutzeraboNumericColumn");
				 flexTable.getCellFormatter().addStyleName(0, 4, "nutzeraboNumericColumn");
				
				hauptP.add(flexTable);
				return hauptP;
				
			}
			
		}