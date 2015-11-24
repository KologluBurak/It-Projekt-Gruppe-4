package de.hdm.itProjektGruppe4.shared;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.itProjektGruppe4.shared.bo.*;


@RemoteServiceRelativePath ("reportgenerator")
public interface ReportGenerator extends RemoteService {
	
	public Report alleNachrichtenAnzeigen(Nachricht pNachricht) throws IllegalArgumentException;
	
	public Report alleAbonnementsAnzeien(Abonnement pAbonnement) throws IllegalArgumentException;
	
	public Report zeitraumspezifischeNachrichtenAnzeigen (Nachricht pNachricht) throws IllegalArgumentException;
	
	public Report nutzerspezifischeAbonnementsAnzeigen (Abonnement pAbonnement) throws IllegalArgumentException;
	
	public Report hashtagspezifischeAbonnementsAnzeigen (Abonnement pAbonnement) throws IllegalArgumentException;
	
	public Report nutzerspezifischeNachrichtenAnzeigen (Nachricht pNachricht) throws IllegalArgumentException;
		
}
