package de.hdm.itProjektGruppe4.shared;

import java.sql.Date;
import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.itProjektGruppe4.shared.bo.*;

/**
 * 
 * @author Thies
 * @author Yücel, Nguyen, Raue
 *
 */

public interface MessagingAdministration extends RemoteService {
		
	/*
	   * ***************************************************************************
	   * ABSCHNITT, Beginn: Initialisierung
	   * ***************************************************************************
	   */
	
	public void init() throws IllegalArgumentException;
	
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
	public Nutzer createNutzer (String vorname, String nachname, String passwort, String googleId) throws IllegalArgumentException;
	public void saveNutzer (Nutzer nutzer)throws IllegalArgumentException;
	public void deleteNutzer (int id) throws IllegalArgumentException;
	
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
	
	
	public void saveNachricht (Nachricht Nachricht) throws IllegalArgumentException;
	public void deleteNachricht (Nachricht Nachricht) throws IllegalArgumentException;
	public void setNachrichtEditedBy (Nachricht nachricht)throws IllegalArgumentException;
	public void setDateOfNachricht(Date DateOfNachricht) throws IllegalArgumentException;
	public void senden (Nachricht Senden) throws IllegalArgumentException;
	public void empfangen (Nachricht Empfangen) throws IllegalArgumentException;
	public Nachricht createNachricht (String text, String betreff) throws IllegalArgumentException;
	public Nachricht getNachrichtById(int id) throws IllegalArgumentException;
	public ArrayList<Nachricht> getAllNachrichten() throws IllegalArgumentException;
	
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
	
	public Unterhaltung createUnterhaltung (Nutzer sender, Nutzer receiver) throws IllegalArgumentException;
	public void saveUnterhaltung (Unterhaltung Unterhaltung) throws IllegalArgumentException;
	public ArrayList<Unterhaltung> getAllUnterhaltungen() throws IllegalArgumentException;
	public Unterhaltung getUnterhaltungbyId(int id)throws IllegalArgumentException;
	
	
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
	
	public Abonnement createAbonnement (Nutzerabonnement aboNutzer, Hashtagabonnement aboHashtag) throws IllegalArgumentException;
	public void saveAbonnement (Abonnement abo) throws IllegalArgumentException;
	//public void deleteAbonnement (Abonnement Abo) throws IllegalArgumentException;
	
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
	
	public Hashtag createHashtag (String bezeichnung) throws IllegalArgumentException;
	public void saveHashtag (Hashtag hashtag) throws IllegalArgumentException;
	public void deleteHashtag (Hashtag hashtag)throws IllegalArgumentException;
	
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
	
	public Nutzerabonnement createAboNutzer (int aboNutzerId, int derBeobachteteId)throws IllegalArgumentException;
	public void saveAboNutzer (Nutzerabonnement nutzerAbo)throws IllegalArgumentException;
	public void deleteAboNutzer (Nutzerabonnement nutzerAbo)throws IllegalArgumentException;
	
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
	
	//public void createAboHashtag (Hashtagabonnement HashtagAbo)throws IllegalArgumentException;
	public void saveAboHashtag (Hashtagabonnement hashtagAbo)throws IllegalArgumentException;
	public void deleteAboHastag (Hashtagabonnement hashtagAbo)throws IllegalArgumentException;
	
	/*
	   * ***************************************************************************
	   * ABSCHNITT, Ende: Methoden für HashtagAbo-Objekte
	   * ***************************************************************************
	   */
	








}
