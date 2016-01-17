package de.hdm.itProjektGruppe4.server;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.itProjektGruppe4.server.db.*;
import de.hdm.itProjektGruppe4.shared.*;
import de.hdm.itProjektGruppe4.shared.bo.*;

/**
 * <p>
 * Implementierungsklasse des Interface <code>MessagingAdministration</code>.
 * Diese Klasse ist <em>die</em> Klasse, die neben {@link ReportGeneratorImpl}
 * sï¿½mtliche Applikationslogik (oder engl. Business Logic) aggregiert. Sie ist
 * wie eine Spinne, die sï¿½mtliche Zusammenhï¿½nge in ihrem Netz (in unserem Fall
 * die Daten der Applikation) ï¿½berblickt und fï¿½r einen geordneten Ablauf und
 * dauerhafte Konsistenz der Daten und Ablï¿½ufe sorgt.
 * </p>
 * <p>
 * Die Applikationslogik findet sich in den Methoden dieser Klasse. Jede dieser
 * Methoden kann als <em>Transaction Script</em> bezeichnet werden. Dieser Name
 * lï¿½sst schon vermuten, dass hier analog zu Datenbanktransaktion pro
 * Transaktion gleiche mehrere Teilaktionen durchgefï¿½hrt werden, die das System
 * von einem konsistenten Zustand in einen anderen, auch wieder konsistenten
 * Zustand ï¿½berfï¿½hren. Wenn dies zwischenzeitig scheitern sollte, dann ist das
 * jeweilige Transaction Script dafï¿½r verwantwortlich, eine Fehlerbehandlung
 * durchzufï¿½hren.
 * </p>
 * <p>
 * Diese Klasse steht mit einer Reihe weiterer Datentypen in Verbindung. Dies
 * sind:
 * <ol>
 * <li>{@link MessagingAdministration}: Dies ist das <em>lokale</em> - also
 * Server-seitige - Interface, das die im System zur Verfï¿½gung gestellten
 * Funktionen deklariert.</li>
 * <li>{@link MessagingAdministrationAsync}:
 * <code>MessagingAminidstrationImpl</code> und
 * <code>MessagingAdministration</code> bilden nur die Server-seitige Sicht der
 * Applikationslogik ab. Diese basiert vollstï¿½ndig auf synchronen
 * Funktionsaufrufen. Wir mï¿½ssen jedoch in der Lage sein, Client-seitige
 * asynchrone Aufrufe zu bedienen. Dies bedingt ein weiteres Interface, das in
 * der Regel genauso benannt wird, wie das synchrone Interface, jedoch mit dem
 * zusï¿½tzlichen Suffix "Async". Es steht nur mittelbar mit dieser Klasse in
 * Verbindung. Die Erstellung und Pflege der Async Interfaces wird durch das
 * Google Plugin semiautomatisch unterstï¿½tzt. Weitere Informationen unter
 * {@link MessagingAdministrationAsync}.</li>
 * <li>{@link RemoteServiceServlet}: Jede Server-seitig instantiierbare und
 * Client-seitig ï¿½ber GWT RPC nutzbare Klasse muss die Klasse
 * <code>RemoteServiceServlet</code> implementieren. Sie legt die funktionale
 * Basis fï¿½r die Anbindung von <code>MessagingAdministrationImpl</code> an die
 * Runtime des GWT RPC-Mechanismus.</li>
 * </ol>
 * </p>
 * <p>
 * <b>Wichtiger Hinweis:</b> Diese Klasse bedient sich sogenannter
 * Mapper-Klassen. Sie gehï¿½ren der Datenbank-Schicht an und bilden die
 * objektorientierte Sicht der Applikationslogik auf die relationale
 * organisierte Datenbank ab. Zuweilen kommen "kreative" Zeitgenossen auf die
 * Idee, in diesen Mappern auch Applikationslogik zu realisieren. Siehe dazu
 * auch die Hinweise in {@link #delete(Customer)} Einzig nachvollziehbares
 * Argument fï¿½r einen solchen Ansatz ist die Steigerung der Performance
 * umfangreicher Datenbankoperationen. Doch auch dieses Argument zieht nur dann,
 * wenn wirklich groï¿½e Datenmengen zu handhaben sind. In einem solchen Fall
 * wï¿½rde man jedoch eine entsprechend erweiterte Architektur realisieren, die
 * wiederum sï¿½mtliche Applikationslogik in der Applikationsschicht isolieren
 * wï¿½rde. Also, keine Applikationslogik in die Mapper-Klassen "stecken" sondern
 * dies auf die Applikationsschicht konzentrieren!
 * </p>
 * <p>
 * Beachten Sie, dass sï¿½mtliche Methoden, die mittels GWT RPC aufgerufen werden
 * kï¿½nnen ein <code>throws Exception</code> in der
 * Methodendeklaration aufweisen. Diese Methoden dï¿½rfen also Instanzen von
 * {@link IllegalArgumentException} auswerfen. Mit diesen Exceptions kï¿½nnen z.B.
 * Probleme auf der Server-Seite in einfacher Weise auf die Client-Seite
 * transportiert und dort systematisch in einem Catch-Block abgearbeitet werden.
 * </p>
 * <p>
 * Es gibt sicherlich noch viel mehr ï¿½ber diese Klasse zu schreiben. Weitere
 * Infos erhalten Sie in der Lehrveranstaltung.
 * </p>
 * 
 * @see MessagingAdministration
 * @see MessagingAdministrationAsync
 * @see RemoteServiceServlet
 * 
 * @author Thies
 * @author Yï¿½cel
 * @author Nguyen
 * @author Raue
 * 
 *
 */

public class MessagingAdministrationImpl extends RemoteServiceServlet implements MessagingAdministration {

	private NutzerMapper nutzerMapper = null;
	private AbonnementMapper abonnementMapper = null;
	private NutzerAboMapper nutzerAboMapper = null;
	private HashtagAboMapper hashtagAboMapper = null;
	private NachrichtMapper nachrichtMapper = null;
	private HashtagMapper hashtagMapper = null;
	private UnterhaltungMapper unterhaltungMapper = null;
	private UnterhaltungslisteMapper unterhaltungslisteMapper = null;
	private MarkierungslisteMapper markierungslisteMapper = null;

	/**
	 * No-Argument Konstruktor
	 */
	public MessagingAdministrationImpl() throws IllegalArgumentException {

	}

	/*
	 * *************************************************************************
	 * ** ABSCHNITT, Beginn: Initialisierung
	 * ***************************************
	 * ************************************
	 */
	public void init() throws IllegalArgumentException {
		this.nutzerMapper = NutzerMapper.nutzerMapper();
		this.abonnementMapper = AbonnementMapper.abonnementMapper();
		this.nutzerAboMapper = NutzerAboMapper.nutzerAboMapper();
		this.hashtagAboMapper = HashtagAboMapper.hashtagAboMapper();
		this.nachrichtMapper = NachrichtMapper.nachrichtMapper();
		this.hashtagMapper = HashtagMapper.hashtagMapper();
		this.unterhaltungMapper = UnterhaltungMapper.unterhaltungMapper();
		this.unterhaltungslisteMapper = UnterhaltungslisteMapper.unterhaltungslisteMapper();
		this.markierungslisteMapper = MarkierungslisteMapper.markierungslisteMapper();
	}

	/*
	 * *************************************************************************
	 * ** ABSCHNITT, Ende: Initialisierung
	 * *****************************************
	 * **********************************
	 */

	/*
	 * *************************************************************************
	 * ** ABSCHNITT, Beginn: Methoden fï¿½r Nutzer-Objekte
	 * ***************************
	 * ************************************************
	 */

	/**
	 * Anlegen eines neuen Nutzers. Der neuer Nutzer wird in die Datenbank
	 * gespeichert.
	 */
	public Nutzer createNutzer(String vorname, String nachname, String email, String nickname)
			throws Exception {

		if (!userExist(email)) {
			System.out.println("User Existiert: " + userExist(email));
			Nutzer n = new Nutzer();
			n.setVorname(vorname);
			n.setNachname(nachname);
			n.setEmail(email);
			n.setNickname(nickname);
			return this.nutzerMapper.insert(n);
		}
		return null;

	}

	/**
	 * ï¿½ndern von Attributen eines Nutzers.
	 */

	public Nutzer update(Nutzer nutzer) throws Exception {
		return nutzerMapper.update(nutzer);
	}

	/**
	 * Lï¿½schen eines Nutzers. Der Nutzer wird in der Datenbank gelï¿½scht.
	 * zugehï¿½rende nachrichten werden auch gelï¿½scht
	 */
	public void delete(Nutzer nutzer) throws Exception {

		ArrayList<Nachricht> nachrichten = this.nachrichtMapper.alleNachrichtenJeNutzer(nutzer);
		/*
		 * Die Verbindung zum Abonnement wird aufgelï¿½st.
		 */
		Nutzerabonnement nutzerabo = this.nutzerAboMapper.findNutzerAbonnementByID(nutzer.getId());

		// if (nutzerabo != null) {
		// for (Nutzerabonnement nabo : nutzerabo) {
		this.delete(nutzerabo);
		// }

		/*
		 * Die Nachrichten die eine Verbindung zum Nutzer haben werden gelï¿½scht.
		 */
		if (nachrichten != null) {
			for (Nachricht n : nachrichten) {
				this.delete(n);
			}
		}
		this.nutzerMapper.delete(nutzer);

	}

	/**
	 * Auslesen aller Nutzer Objekte
	 */
	public ArrayList<Nutzer> getAllNutzer() throws Exception {
		return this.nutzerMapper.findAllNutzer();
	}

	/**
	 * Auslesen eines Nutzers anhand seines Nickname.
	 */
	public Nutzer getNutzerByNickname(String nickname) throws Exception {
		return this.nutzerMapper.findNutzerByNickname(nickname);
	}

	/**
	 * Auslesen eines Nutzers anhand seiner ID.
	 */
	public Nutzer getNutzerById(int id) throws Exception {
		return this.nutzerMapper.findNutzerById(id);
	}

	/*
	 * *************************************************************************
	 * ** ABSCHNITT, Ende: Methoden fï¿½r Nutzer-Objekte
	 * *****************************
	 * **********************************************
	 */

	/*
	 * *************************************************************************
	 * ** ABSCHNITT, Beginn: Methoden fï¿½r Nachricht-Objekte
	 * ************************
	 * ***************************************************
	 */

	/**
	 * Anlegen einer neuen Nachricht. Die Nachricht wird in der Datenbank
	 * gespeichert.
	 * @throws Exception 
	 */
	public Nachricht createNachricht(String text, String nickname, String empf, Unterhaltung unterhaltung)
			throws Exception {

		Nachricht na = new Nachricht();
		// NutzerID durch emailadresse heraussuchen
		Nutzer absender = new Nutzer();

		// erster Fremdschlï¿½ssel
		absender = this.nutzerMapper.findNutzerByNickname(nickname);

	    //Nutzer empfaenger = new Nutzer();

		Unterhaltungsliste unterhaltungsliste = new Unterhaltungsliste();

		// zweiter Fremdschlüssel
		unterhaltungsliste = this.unterhaltungslisteMapper.findByAbsender(absender);
		// this.unterhaltungslisteMapper.findByAbsender(absender);

		// 3. Fremschlüssel
		Nutzer empfaenger =  this.nutzerMapper.findNutzerByNickname(empf);

		System.out.println(absender.getId() + " " + unterhaltung.getId());

		na.setNutzerID(absender.getId());
		na.setUnterhaltungID(unterhaltung.getId());

		na.setText(text);
		return this.nachrichtMapper.insert(na);
	}

	public void delete(Nachricht nachricht) throws Exception {
		this.nachrichtMapper.delete(nachricht);
	}

	/**
	 * Auslesen aller Nachrichten aus der Datenbank.
	 */
	public ArrayList<Nachricht> getAllNachrichten() throws Exception {
		return null; // this.nachrichtMapper.findAllNachrichten();
	}

	/**
	 * Auslesen aller Nachrichten einer Unterhaltung aus der Datenbank.
	 */
	public ArrayList<Nachricht> getNachrichtenByUnterhaltung(Unterhaltung unterhaltung)
			throws Exception {
		return this.unterhaltungMapper.findNachrichtenByUnterhaltung(unterhaltung);
	}

	/**
	 * Auslesen Nachrichten anhand der Id.
	 */

	public Nachricht getNachrichtById(int id) throws Exception {
		return this.nachrichtMapper.findNachrichtById(id);
	}

	/**
	 * Auslesen von Nachrichten eines Nutzers.
	 */
	public ArrayList<Nachricht> getAlleNachrichtenJeNutzer(Nutzer nutzer) throws Exception {
		return this.nachrichtMapper.alleNachrichtenJeNutzer(nutzer);
	}

	/**
	 * Auslesen von Nachrichten innerhalb eines Zeitraums
	 */
	public ArrayList<Nachricht> getAlleNachrichtenJeZeitraum(String von, String bis) throws Exception {
		return this.nachrichtMapper.alleNachrichtenJeZeitraum(von, bis);
	}

	/*
	 * *************************************************************************
	 * ** ABSCHNITT, Ende: Methoden fï¿½r Nachricht-Objekte
	 * **************************
	 * *************************************************
	 */

	/*
	 * *************************************************************************
	 * ** ABSCHNITT, Beginn: Methoden fï¿½r Unterhaltung-Objekte
	 * *********************
	 * ******************************************************
	 */

	/**
	 * Anlegen einer Unterhaltung. Die Unterhaltung wird in der Datenbank
	 * gespeichert.
	 */
	public Unterhaltung createUnterhaltung(Date datum) throws Exception {
		Unterhaltung u = new Unterhaltung();

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

		u.setZuletztBearbeitet(dateFormat.format(datum));
		u.setErstellungsZeitpunkt(dateFormat.format(datum));
		return this.unterhaltungMapper.insert();

		// u.setErstellungsZeitpunkt(datum);
		// return unterhaltungMapper.insert(datum);

	}
	
	public Unterhaltung getMaxID() throws Exception{
//		this.unterhaltungMapper.insert();
		System.out.println("anfang ovn getMaxID in Impl");
		Date d = null; 
		//createUnterhaltung(d);
		System.out.println(this.unterhaltungMapper.getMaxID());
		return this.unterhaltungMapper.getMaxID();
	}

	public Boolean userExist(String email) throws Exception {

		Boolean exist = false;
		Nutzer nutzer = this.nutzerMapper.findNutzerByEmail(email);

		// System.out.println(nutzer.getEmail() + " "+ nutzer.getId());

		if (nutzer != null) {
			exist = true;
			System.out.println("true");
		}

		return exist;
	}

	/**
	 * Lï¿½schen einer Unterhaltung. Hierbei werden die Nutzer, die in der
	 * Unterhaltung teilgenommen haben nicht gelï¿½scht.
	 * @throws Exception 
	 */
	public void delete(Unterhaltung unterhaltung) throws Exception {
		/*
		 * Zugehï¿½rige Nachrichten von der Unterhaltung werden gelï¿½scht
		 */
		ArrayList<Nachricht> nachrichten = this.getNachrichtenByUnterhaltung(unterhaltung);

		if (nachrichten != null) {
			for (Nachricht n : nachrichten) {
				this.delete(n);
			}
		}
		this.unterhaltungMapper.delete(unterhaltung);
	}

	/**
	 * Auslesen von allen Unterhaltungen aus der Datenbank.
	 * 
	 * public ArrayList<Nachticht> getAllUnterhaltungen(Unterhaltung
	 * unterhaltung) throws Exception { return
	 * this.unterhaltungMapper.findNachrichtenByUnterhaltung(unterhaltung); }
	 */

	/*
	 * *************************************************************************
	 * ** ABSCHNITT, Ende: Methoden fï¿½r Unterhaltung-Objekte
	 * ***********************
	 * ****************************************************
	 */

	/*
	 * *************************************************************************
	 * ** ABSCHNITT, Beginn: Methoden fï¿½r Abonnement-Objekte
	 * ***********************
	 * ****************************************************
	 */
	/**
	 * Anlegen eines Abonnements. Das Abonnement wird in der Datenbank
	 * gespeichert.
	 */
	public Abonnement createAbonnement(int id, String erstellungsZeitpunkt) throws Exception {
		Abonnement abo = new Abonnement();
		abo.setId(id);
		abo.setErstellungsZeitpunkt(erstellungsZeitpunkt);
		return this.abonnementMapper.insertAbonnement(abo);
	}

	/**
	 * Auslesen aller Abonnements.
	 */
	public ArrayList<Abonnement> getAllAbonnements() throws Exception {
		return this.abonnementMapper.findAllAbonnements();
	}

	/*
	 * *************************************************************************
	 * ** ABSCHNITT, Ende: Methoden fï¿½r Abonnement-Objekte
	 * *************************
	 * **************************************************
	 */

	/*
	 * *************************************************************************
	 * ** ABSCHNITT, Beginn: Methoden fï¿½r Hashtag-Objekte
	 * **************************
	 * *************************************************
	 */

	/**
	 * Anlegen eines Hashtags
	 */
	public Hashtag createHashtag(String bezeichnung) throws Exception {
		Hashtag hash = new Hashtag();
		hash.setBezeichnung(bezeichnung);
		return hashtagMapper.insert(hash);
	}

	/**
	 * Lï¿½schen eines Hashtags
	 */
	public void delete(Hashtag hashtag) throws Exception {
		/*
		 * Die Verbindung zum Hashtagabonnement wird aufgelï¿½st.
		 */
		// //Muss noch verbessert werden
		// ArrayList<Hashtagabonnement> hashtagabo =
		// this.getHashtagAbonnementByNutzer(hashtag);
		//
		//
		// if (hashtagabo != null) {
		// for (Hashtagabonnement hashabo : hashtagabo) {
		// this.delete(hashabo);
		// }
		hashtagMapper.delete(hashtag);
	}

	/**
	 * Auslesen aller Hashtags.
	 */
	public ArrayList<Hashtag> getAllHashtags() throws Exception {
		return this.hashtagMapper.findAllHashtags();
	}

	/**
	 * Auslesen eines Hashtag anhand seiner ID.
	 */
	public Hashtag getHashtagById(int id) throws Exception {
		return this.hashtagMapper.findHashtagByID(id);
	}

	/*
	 * *************************************************************************
	 * ** ABSCHNITT, Ende: Methoden fï¿½r Hashtag-Objekte
	 * ****************************
	 * ***********************************************
	 */

	/*
	 * *************************************************************************
	 * ** ABSCHNITT, Beginn: Methoden fï¿½r NutzerAbo-Objekte
	 * ************************
	 * ***************************************************
	 */

	/*
	 * Anlegen eines Nutzerabonnements
	 */
	public Nutzerabonnement createNutzerabonnement(Nutzer derBeobachtete, Nutzer follower)
			throws Exception {

		Nutzerabonnement nutzabo = new Nutzerabonnement();
		nutzabo.setDerBeobachteteID(derBeobachtete.getId());
		nutzabo.setFollowerID(follower.getId());
		return nutzerAboMapper.insert(nutzabo);
	}

	/*
	 * Lï¿½schen eines Nutzerabonnement
	 */
	public void delete(Nutzerabonnement nutzerAbo) throws Exception {
		nutzerAboMapper.delete(nutzerAbo);
		;

	}

	/**
	 * Auslesen von allen Nutzerabonnements.
	 */
	public ArrayList<Nutzerabonnement> getAllNutzerabonnements(String userID) throws Exception {
		return this.nutzerAboMapper.findAllNutzerabonnements(userID);
	}

	/**
	 * Auslesen eines Nutzerabonnements anhand seiner ID.
	 */
	public Nutzerabonnement getNutzerabonnementById(int id) throws Exception {
		return this.nutzerAboMapper.findNutzerAbonnementByID(id);
	}

	/**
	 * Auslesen von Nutzer die die Rolle des des Beobachteten haben .
	 */
	public ArrayList<Nutzerabonnement> getNutzerAbonnementByNutzer(Nutzer nutzer) throws Exception {
		return this.nutzerAboMapper.findNutzerAbonnementByAbonnementID(nutzer.getId());
	}

	/**
	 * Auslesen eines Nutzerabonnements anhand der Follower-ID
	 */
	public ArrayList<Nutzerabonnement> getNutzerabonnementByFollowerId(int id) throws Exception {
		return this.nutzerAboMapper.findNutzerAbonnementByFollowerID(id);
	}

	/**
	 * Auslesen eines Nutzerabonnements anhand der derBeobachteteID
	 */
	public ArrayList<Nutzerabonnement> getNutzerabonnementByDerBeobachteteId(int id) throws Exception {
		return this.nutzerAboMapper.findNutzerAbonnementByDerBeobachteteID(id);
	}

	/**
	 * Auslesen eines Nutzerabonnements anhand der abonnementID
	 */
	public ArrayList<Nutzerabonnement> getNutzerabonnementByAbonnementId(int id) throws Exception {
		return this.nutzerAboMapper.findNutzerAbonnementByAbonnementID(id);
	}
	/*
	 * *************************************************************************
	 * ** ABSCHNITT, Ende: Methoden fï¿½r NutzerAbo-Objekte
	 * **************************
	 * *************************************************
	 */

	/*
	 * *************************************************************************
	 * ** ABSCHNITT, Beginn: Methoden fï¿½r HashtagAbo-Objekte
	 * ***********************
	 * ****************************************************
	 */

	/**
	 * Anlegen eines Hashtagabonnements.
	 */
	public Hashtagabonnement createHashtagAbonnement(Hashtag bezeichnung) throws Exception {
		Hashtagabonnement b = new Hashtagabonnement();
		b.setHashtagID(bezeichnung.getId());
		return this.hashtagAboMapper.insert(b);
	}

	/**
	 * Lï¿½schen eines Hashtagsabonnement.
	 */
	public void delete(Hashtagabonnement hashtagAbo) throws Exception {
		this.hashtagAboMapper.delete(hashtagAbo);

	}

	/**
	 * Auslesen aller Hashtagabonnements.
	 */
	public ArrayList<Hashtagabonnement> getAllHashtagabonnements(String userID) throws Exception {
		return this.hashtagAboMapper.findAllHashtagabonnements(userID);
	}

	/**
	 * Auslesen eines Hashtagabonnements anhand einer Id.
	 */
	public Hashtagabonnement getHashtagAbonnementById(int id) throws Exception {
		return this.hashtagAboMapper.findHashtagAbonnementByID(id);
	}

	/**
	 * Auslesen eines Hashtagabonnements anhand des Nutzers.
	 */
	public ArrayList<Hashtagabonnement> getHashtagabonnementByNutzerId(int nutzer) throws Exception {
		return this.hashtagAboMapper.findHashtagAbonnementByNutzerID(nutzer);
	}

	/**
	 * Auslesen eines Hashtagsabonnement anhand der Hashtag-ID
	 */
	public Hashtagabonnement getHashtagAbonnementByHashtagId(int id) throws Exception {
		return this.hashtagAboMapper.findHashtagAbonnementByHashtagID(id);
	}

	/**
	 * Auslesen eines Hashtagsabonnement anhand der Abonnement-ID
	 */
	public Hashtagabonnement getHashtagAbonnementByAbonnementId(int id) throws Exception {
		return this.hashtagAboMapper.findHashtagAbonnementByAbonnementID(id);
	}

	/*
	 * *************************************************************************
	 * ** ABSCHNITT, Ende: Methoden fï¿½r HashtagAbo-Objekte
	 * *************************
	 * **************************************************
	 */

	/*
	 * *************************************************************************
	 * ** ABSCHNITT, Beginn: Methoden fï¿½r Markierungsliste-Objekte
	 * ***********************
	 * ****************************************************
	 */

	/*
	 * *************************************************************************
	 * ** ABSCHNITT, Ende: Methoden fï¿½r Markierungsliste-Objekte
	 * *************************
	 * **************************************************
	 */

	/**
	 * Anlegen einer Markierungsliste.
	 */
	public Markierungsliste createMarkierungsliste(String text, String hashtag) throws Exception {
		Markierungsliste markierungsliste = new Markierungsliste();
		Nachricht nachricht = new Nachricht();
		Hashtag hash = new Hashtag();
		// muss noch gemacht werden
		return null;

	}
	/*
	 * *************************************************************************
	 * ** ABSCHNITT, Beginn: Methoden fï¿½r Unterhaltungsliste-Objekte
	 * ***********************
	 * ****************************************************
	 */

	/**
	 * Anlegen einer Unterhaltungsliste.
	 */
	public Unterhaltungsliste createUnterhaltungsliste(Unterhaltung u, String sender, String empf)
			throws Exception {
		
		Unterhaltungsliste UListe = new Unterhaltungsliste();
		Nutzer absender = new Nutzer();
		System.out.println("createUnterhaltungsliste "+u.getId());
		absender.setId(this.nutzerMapper.findNutzerByEmail(sender).getId());
		System.out.println("Absender "+absender.getId() + " " + sender);
		
		Nutzer empfaenger = new Nutzer();
		empfaenger.setId(this.nutzerMapper.findNutzerByEmail(empf).getId());
		System.out.println("Empfänger "+empfaenger.getId() + " " + empf);
		
		UListe.setUnterhaltungID(u.getId()); 
		UListe.setAbsenderID(absender.getId());
		UListe.setEmpfaengerID(empfaenger.getId());
		
		return this.unterhaltungslisteMapper.insert(UListe);

	}

	/**
	 * Auslesen einer Unterhaltungsliste anhand des Absender.
	 */
	public Unterhaltungsliste getByAbsender(Nutzer absenderNickname) throws Exception {
		// nickanme die Id ermitteln und das muss man dan in die findByAbsender
		// weiter geben.
		return this.unterhaltungslisteMapper.findByAbsender(absenderNickname);
	}

	/**
	 * Auslesen einer Unterhaltungsliste anhand des Empfï¿½ngers.
	 */
	public Unterhaltungsliste getByEmpfaenger(Nutzer empfaengerNickname) throws Exception {
		// nickanme die Id ermitteln und das muss man dan in die findByAbsender
		// weiter geben.
		return this.unterhaltungslisteMapper.findByEmpfaenger(empfaengerNickname);
	}
	
	public ArrayList<Nutzer> getAlleEmpfaengerByAbsender(Nutzer absender) throws Exception{
		
		ArrayList<Nutzer> alleEmpf = new ArrayList<Nutzer>();
		
		for (Unterhaltungsliste ul : unterhaltungslisteMapper.findAlleEmpfaengerByAbsender(absender)){
			Nutzer n = new Nutzer();
			n.setNickname(this.nutzerMapper.findNutzerById(ul.getEmpfaengerID()).getNickname());
			n.setId(ul.getEmpfaengerID()); 
			n.setEmail(this.nutzerMapper.findNutzerById(ul.getEmpfaengerID()).getEmail());
			System.out.println(n.getNickname() + " ID " + n.getId());
			alleEmpf.add(n);
		}
		System.out.println(alleEmpf);
		return alleEmpf;
	}

	/**
	 * Auslesen einer Unterhaltungsliste anhand einer Unterhaltung.
	 */
	public Unterhaltungsliste getByUnterhaltung(Unterhaltung unterhaltung) throws Exception {
		return this.unterhaltungslisteMapper.findByUnterhaltung(unterhaltung);
	}

	@Override
	public Unterhaltungsliste getUnterhaltung(String absender, String empfaenger)
			throws Exception {
	
		Nutzer empf = new Nutzer();
		empf = this.nutzerMapper.findNutzerByNickname(empfaenger);
		System.out.println("UnterhaltungID "+this.unterhaltungslisteMapper.pruefeUnterhaltung(absender, empf.getId()).getUnterhaltungID());
		return this.unterhaltungslisteMapper.pruefeUnterhaltung(absender, empf.getId());
	}
	
	

	/*
	 * *************************************************************************
	 * ** ABSCHNITT, Ende: Methoden Unterhaltungsliste-Objekte
	 * *************************
	 * **************************************************
	 */

}
