package de.hdm.itProjektGruppe4.client;

import com.google.gwt.core.client.EntryPoint;

import de.hdm.itProjektGruppe4.client.gui.report.ReportForm;

public class IT_Projekt_Gruppe_4_Report implements EntryPoint{

	@Override
	public void onModuleLoad() {
		ReportForm report = new ReportForm();
		report.anzeigenR();

	}

}
