package de.hdm.itProjektGruppe4.server;

import java.sql.Date;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.itProjektGruppe4.server.db.*;
import de.hdm.itProjektGruppe4.shared.*;
import de.hdm.itProjektGruppe4.shared.bo.*;

public class MessagingAdministrationImpl extends RemoteServiceServlet 
	implements MessagingAdministration {
	
	private NutzerMapper nutzerMapper = null;
	private AbonnementMapper abonnementMapper= null;
	private NutzerAboMapper nutzerAboMapper=null;
	private HashtagAboMapper hashtagAboMapper=null;
	private NachrichtMapper nachrichtMapper= null;
	private HashtagMapper hashtagMapper= null;
	private UnterhaltungMapper unterhaltungMapper= null;
	
	public MessagingAdministrationImpl()throws IllegalArgumentException{
		
	}
	
	public void init()throws IllegalArgumentException{
		this.nutzerMapper= NutzerMapper.nutzerMapper();
		this.abonnementMapper= AbonnementMapper.abonnementMapper();
		//this.nutzerAboMapper= NutzerAboMapper.nutzerAboMapper();
		//this.hashtagAboMapper= HastagaboMapeer.hashtagAboMapper;
		this.nachrichtMapper= NachrichtMapper.nachrichtMapper();
		this.hashtagMapper= HashtagMapper.hashtagMapper();
		this.unterhaltungMapper= UnterhaltungMapper.unterhaltungMapper();	
	}
	

	
	
	public void deleteAbonnement (Abonnement Abo) throws IllegalArgumentException{
	//	
	}
	public void setNachrichtEditedBy (Nachricht NachrichtEditedBy)throws IllegalArgumentException{
		//
	}
	public void saveNachricht (Nachricht Nachricht) throws IllegalArgumentException{
		//
	}
	public void deleteHashtag (Hashtag Hashtag)throws IllegalArgumentException{
		//
	}
	public void createNachricht (Nachricht Nachricht) throws IllegalArgumentException{
		//
	}
	public void createHashtag (Hashtag Hashtag) throws IllegalArgumentException {
		//
	}
	public void saveAbonnement (Abonnement Abo) throws IllegalArgumentException {
		//
	}
	
	  /**
	   * <p>
	   * Anlegen eines neuen Nutzers. Der neuer Nutzer wird in die Datenbank gespeichert.
	   * </p>
	      */
	
	public void createNutzer (String vorname, String googleId) throws IllegalArgumentException {
		Nutzer n = new Nutzer();
		n.setVorname(vorname);
		n.setGoogleId(googleId);
	}
	public void senden (Nachricht Senden) throws IllegalArgumentException {
		//
	}
	public void saveUnterhaltung (Unterhaltung Unterhaltung) throws IllegalArgumentException{
		
	}
	public void deleteNutzer (Nutzer Nutzer) throws IllegalArgumentException{
		//
	}
	public void createUnterhaltung (Unterhaltung Unterhaltung) throws IllegalArgumentException{
		//
	}
	public void setDateOfNachricht(Date DateOfNachricht) throws IllegalArgumentException{
		//
	}
	public void saveHashtag (Hashtag Hashtag) throws IllegalArgumentException{
		//
	}
	public void empfangen (Nachricht Empfangen) throws IllegalArgumentException{
		//
		}
	public void createAbonnement (Abonnement Abo) throws IllegalArgumentException{
		//
		}
	public void deleteNachricht (Nachricht Nachricht) throws IllegalArgumentException{
		//
	}
	
	public void createAboNutzer (Nutzerabonnement NutzerAbo)throws IllegalArgumentException{
		//
	}
	
	public void deleteAboNutzer (Nutzerabonnement NutzerAbo)throws IllegalArgumentException{
		//
	}

	public void saveAboNutzer (Nutzerabonnement NutzerAbo)throws IllegalArgumentException{
		//
	}
	public void createAboHashtag (Hashtagabonnement HashtagAbo)throws IllegalArgumentException{
		//
	}
	public void deleteAboHastag (Hashtagabonnement HashtagAbo)throws IllegalArgumentException{
		//
	}
	public void saveAboHashtag (Hashtagabonnement HashtagAbo)throws IllegalArgumentException{
		//
	}	
	/**
	   * Speichern eines Nutzer in der Datenbank.
	   */
	public void saveNutzer (Nutzer nutzer)throws IllegalArgumentException{
		nutzerMapper.update(nutzer);
	}

	

	
}
