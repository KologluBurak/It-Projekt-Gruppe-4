package de.hdm.itProjektGruppe4.shared;

import java.sql.Date;
import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.itProjektGruppe4.shared.bo.*;

/**
 * 
 * @author Thies
 * @author Yücel, Nguyen, Raue
 *
 */
 
@RemoteServiceRelativePath("messagingAdmin")
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
	public Nutzer createNutzer(String vorname, String nachname, String email, String nickname) throws IllegalArgumentException;
	public void delete (Nutzer nutzer) throws IllegalArgumentException;
	public ArrayList<Nutzer> findAllNutzer() throws IllegalArgumentException;
	Nutzer getNutzerByNachname(String nachname);
	
	/*
	   * ***************************************************************************
	   * ABSCHNITT, Beginn: Methoden für Nachricht-Objekte
	   * ***************************************************************************
	   */
	
	public Nachricht createNachricht (String text, String nickname, Unterhaltung unterhaltung) throws IllegalArgumentException;
	public ArrayList<Nachricht> getAllNachrichten() throws IllegalArgumentException;
	public ArrayList <Nachricht> getAlleNachrichtbyNutzer(Nutzer nutzer);
	//public ArrayList <Nachricht> findNachrichtenByUnterhaltung(Nachricht nachricht, Unterhaltung unterhaltung);
	public ArrayList <Nachricht> findNachrichtenByUnterhaltung(Unterhaltung unterhaltung);
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
	public ArrayList<Unterhaltung> getAllUnterhaltungen() throws IllegalArgumentException;
	public Unterhaltung getUnterhaltungbyId(int id)throws IllegalArgumentException;
	public void delete(Unterhaltung u);
	
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
	
	
	public ArrayList<Abonnement> getAllAbonnements();
	public Abonnement getAbonnementbyId(int id);
	
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
	public void save (Hashtag hashtag) throws IllegalArgumentException;
	public void delete (Hashtag hashtag)throws IllegalArgumentException;
	
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
	
	public Nutzerabonnement createNutzerabonnement (Nutzer derBeobachtete, Nutzer follower)throws IllegalArgumentException;
	public void delete (Nutzerabonnement nutzerAbo)throws IllegalArgumentException;
	public ArrayList<Nutzerabonnement> findNutzerAbonnementByNutzer (Nutzer nutzer) throws IllegalArgumentException;
	
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
	
	
	public Hashtagabonnement createHashtagAbonnement (Hashtag bezeichnung)throws IllegalArgumentException;
	public void delete(Hashtagabonnement hashtagAbo)throws IllegalArgumentException;

	/*
	   * ***************************************************************************
	   * ABSCHNITT, Ende: Methoden für HashtagAbo-Objekte
	   * ***************************************************************************
	   */
	
}
