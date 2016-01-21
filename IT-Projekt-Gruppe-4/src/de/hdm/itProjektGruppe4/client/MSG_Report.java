package de.hdm.itProjektGruppe4.client;

import com.google.gwt.core.client.EntryPoint;

import de.hdm.itProjektGruppe4.client.gui.report.ReportForm;

public class MSG_Report implements EntryPoint {



@Override
public void onModuleLoad() {
	// TODO Auto-generated method stub
	ReportForm report = new ReportForm();
	report.anzeigenR();
	
}
}
