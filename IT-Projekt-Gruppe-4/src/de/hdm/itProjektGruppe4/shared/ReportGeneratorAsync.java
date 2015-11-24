package de.hdm.itProjektGruppe4.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.itProjektGruppe4.shared.bo.*;
import de.hdm.itProjektGruppe4.shared.report.*;

public interface ReportGeneratorAsync {
	
	void AlleNachrichtenAnzeigenReport(
	     AsyncCallback<alleNachrichtenAnzeigen> callback);
	
	void AlleAbonnementsAnzeigenReport(
		 AsyncCallback<AlleAbonnementsAnzeigen> callback);
	
	void ZeitraumspezifischeNachrichtenAnzeigenReport(
		 AsyncCallback<ZeitraumspezifischeNachrichtenAnzeigen> callback);
	
	void NutzerspezifischeAbonnementsAnzeigenReport(
		 AsyncCallback<NutzerspezifischeAbonnementsAnzeigen> callback);
	
	void HashtagsspezifischeAbonnementsAnzeigenReport(
		 AsyncCallback<HashtagsspezifischeAbonnementsAnzeigen> callback);
	
	void NutzerspezifischeNachrichtenAnzeigenReport(
		 AsyncCallback<NutzerspezifischeNachrichtenAnzeigen> callback);
	
	void init (AsyncCallback<Void> callback);

}
