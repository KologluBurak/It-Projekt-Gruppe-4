package de.hdm.itProjektGruppe4.shared;

import java.sql.Date;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.itProjektGruppe4.shared.bo.*;

/**
 * 
 * @author Yücel
 *
 */

public interface MessagingAdministration extends RemoteService {
		
	/*
	   * ***************************************************************************
	   * ABSCHNITT, Beginn: Initialisierung
	   * ***************************************************************************
	   */
	
	public void init() throws IllegalArgumentException;
	
	public void senden (Nachricht Senden) throws IllegalArgumentException;
	public void empfangen (Nachricht Empfangen) throws IllegalArgumentException;
	
	
	
	/*
	   * ***************************************************************************
	   * ABSCHNITT, Ende: Initialisierung
	   * ***************************************************************************
	   */
	
	/*
	   * ***************************************************************************
	   * ABSCHNITT, Beginn: Methoden für Nutzer-Objekte
	   * ***************************************************************************
	   */

	
	public Nutzer createNutzer (String vorname, String nachname, String passwort, String googleId) 
			throws IllegalArgumentException;
	public void deleteNutzer (Nutzer Nutzer) throws IllegalArgumentException;
	
	/*
	   * ***************************************************************************
	   * ABSCHNITT, Ende: Methoden für Nutzer-Objekte
	   * ***************************************************************************
	   */
	
	/*
	   * ***************************************************************************
	   * ABSCHNITT, Beginn: Methoden für Nachricht-Objekte
	   * ***************************************************************************
	   */
	
	public void createNachricht (Nachricht Nachricht) throws IllegalArgumentException;
	public void saveNachricht (Nachricht Nachricht) throws IllegalArgumentException;
	public void deleteNachricht (Nachricht Nachricht) throws IllegalArgumentException;
	
	public void setNachrichtEditedBy (Nachricht NachrichtEditedBy)throws IllegalArgumentException;
	public void setDateOfNachricht(Date DateOfNachricht) throws IllegalArgumentException;
	
	/*
	   * ***************************************************************************
	   * ABSCHNITT, Ende: Methoden für Nachricht-Objekte
	   * ***************************************************************************
	   */
	
	/*
	   * ***************************************************************************
	   * ABSCHNITT, Beginn: Methoden für Unterhaltung-Objekte
	   * ***************************************************************************
	   */	
	
	public void createUnterhaltung (Unterhaltung Unterhaltung) throws IllegalArgumentException;
	public void saveUnterhaltung (Unterhaltung Unterhaltung) throws IllegalArgumentException;
	
	/*
	   * ***************************************************************************
	   * ABSCHNITT, Ende: Methoden für Unterhaltung-Objekte
	   * ***************************************************************************
	   */
	
	/*
	   * ***************************************************************************
	   * ABSCHNITT, Beginn: Methoden für Abonnement-Objekte
	   * ***************************************************************************
	   */
	
	public void createAbonnement (Abonnement Abo) throws IllegalArgumentException;
	public void saveAbonnement (Abonnement Abo) throws IllegalArgumentException;
	public void deleteAbonnement (Abonnement Abo) throws IllegalArgumentException;
	
	/*
	   * ***************************************************************************
	   * ABSCHNITT, Ende: Methoden für Abonnement-Objekte
	   * ***************************************************************************
	   */
	
	/*
	   * ***************************************************************************
	   * ABSCHNITT, Beginn: Methoden für Hashtag-Objekte
	   * ***************************************************************************
	   */
	
	public void createHashtag (Hashtag Hashtag) throws IllegalArgumentException;
	public void saveHashtag (Hashtag Hashtag) throws IllegalArgumentException;
	public void deleteHashtag (Hashtag Hashtag)throws IllegalArgumentException;
	
	/*
	   * ***************************************************************************
	   * ABSCHNITT, Ende: Methoden für Hashtag-Objekte
	   * ***************************************************************************
	   */
	
	/*
	   * ***************************************************************************
	   * ABSCHNITT, Beginn: Methoden für NutzerAbo-Objekte
	   * ***************************************************************************
	   */
	
	public void createAboNutzer (Nutzerabonnement NutzerAbo)throws IllegalArgumentException;
	public void saveAboNutzer (Nutzerabonnement NutzerAbo)throws IllegalArgumentException;
	public void deleteAboNutzer (Nutzerabonnement NutzerAbo)throws IllegalArgumentException;
	
	/*
	   * ***************************************************************************
	   * ABSCHNITT, Ende: Methoden für NutzerAbo-Objekte
	   * ***************************************************************************
	   */
	
	/*
	   * ***************************************************************************
	   * ABSCHNITT, Beginn: Methoden für HashtagAbo-Objekte
	   * ***************************************************************************
	   */
	
	public void createAboHashtag (Hashtagabonnement HashtagAbo)throws IllegalArgumentException;
	public void saveAboHashtag (Hashtagabonnement HashtagAbo)throws IllegalArgumentException;
	public void deleteAboHastag (Hashtagabonnement HashtagAbo)throws IllegalArgumentException;
	
	/*
	   * ***************************************************************************
	   * ABSCHNITT, Ende: Methoden für HashtagAbo-Objekte
	   * ***************************************************************************
	   */
	








}
