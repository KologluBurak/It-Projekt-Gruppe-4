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
 * sämtliche Applikationslogik (oder engl. Business Logic) aggregiert. Sie ist
 * wie eine Spinne, die saemtliche Zusammenhänge in ihrem Netz (in unserem Fall
 * die Daten der Applikation) überblickt und für einen geordneten Ablauf und
 * dauerhafte Konsistenz der Daten und Abläufe sorgt.
 * </p>
 * <p>
 * Die Applikationslogik findet sich in den Methoden dieser Klasse. Jede dieser
 * Methoden kann als <em>Transaction Script</em> bezeichnet werden. Dieser Name
 * lässt schon vermuten, dass hier analog zu Datenbanktransaktion pro
 * Transaktion gleiche mehrere Teilaktionen durchgeführt werden, die das System
 * von einem konsistenten Zustand in einen anderen, auch wieder konsistenten
 * Zustand überführen. Wenn dies zwischenzeitig scheitern sollte, dann ist das
 * jeweilige Transaction Script dafür verwantwortlich, eine Fehlerbehandlung
 * durchzuführen.
 * </p>
 * <p>
 * Diese Klasse steht mit einer Reihe weiterer Datentypen in Verbindung. Dies
 * sind:
 * <ol>
 * <li>{@link MessagingAdministration}: Dies ist das <em>lokale</em> - also
 * Server-seitige - Interface, das die im System zur Verf�gung gestellten
 * Funktionen deklariert.</li>
 * <li>{@link MessagingAdministrationAsync}:
 * <code>MessagingAminidstrationImpl</code> und
 * <code>MessagingAdministration</code> bilden nur die Server-seitige Sicht der
 * Applikationslogik ab. Diese basiert vollständig auf synchronen
 * Funktionsaufrufen. Wir müssen jedoch in der Lage sein, Client-seitige
 * asynchrone Aufrufe zu bedienen. Dies bedingt ein weiteres Interface, das in
 * der Regel genauso benannt wird, wie das synchrone Interface, jedoch mit dem
 * zusaetzlichen Suffix "Async". Es steht nur mittelbar mit dieser Klasse in
 * Verbindung. Die Erstellung und Pflege der Async Interfaces wird durch das
 * Google Plugin semiautomatisch unterstützt. Weitere Informationen unter
 * {@link MessagingAdministrationAsync}.</li>
 * <li>{@link RemoteServiceServlet}: Jede Server-seitig instantiierbare und
 * Client-seitig über GWT RPC nutzbare Klasse muss die Klasse
 * <code>RemoteServiceServlet</code> implementieren. Sie legt die funktionale
 * Basis für die Anbindung von <code>MessagingAdministrationImpl</code> an die
 * Runtime des GWT RPC-Mechanismus.</li>
 * </ol>
 * </p>
 * <p>
 * <b>Wichtiger Hinweis:</b> Diese Klasse bedient sich sogenannter
 * Mapper-Klassen. Sie gehören der Datenbank-Schicht an und bilden die
 * objektorientierte Sicht der Applikationslogik auf die relationale
 * organisierte Datenbank ab. Zuweilen kommen "kreative" Zeitgenossen auf die
 * Idee, in diesen Mappern auch Applikationslogik zu realisieren. Siehe dazu
 * auch die Hinweise in {@link #delete(Customer)} Einzig nachvollziehbares
 * Argument für einen solchen Ansatz ist die Steigerung der Performance
 * umfangreicher Datenbankoperationen. Doch auch dieses Argument zieht nur dann,
 * wenn wirklich große Datenmengen zu handhaben sind. In einem solchen Fall
 * würde man jedoch eine entsprechend erweiterte Architektur realisieren, die
 * wiederum sämtliche Applikationslogik in der Applikationsschicht isolieren
 * würde. Also, keine Applikationslogik in die Mapper-Klassen "stecken" sondern
 * dies auf die Applikationsschicht konzentrieren!
 * </p>
 * <p>
 * Beachten Sie, dass s�mtliche Methoden, die mittels GWT RPC aufgerufen werden
 * können ein <code>throws Exception</code> in der
 * Methodendeklaration aufweisen. Diese Methoden dürfen also Instanzen von
 * {@link IllegalArgumentException} auswerfen. Mit diesen Exceptions können z.B.
 * Probleme auf der Server-Seite in einfacher Weise auf die Client-Seite
 * transportiert und dort systematisch in einem Catch-Block abgearbeitet werden.
 * </p>
 * <p>
 * Es gibt sicherlich noch viel mehr über diese Klasse zu schreiben. Weitere
 * Infos erhalten Sie in der Lehrveranstaltung.
 * </p>
 * 
 * @see MessagingAdministration
 * @see MessagingAdministrationAsync
 * @see RemoteServiceServlet
 * 
 * @author Thies
 * @author Yuecel
 * @author Nguyen
 * @author Raue
 * 
 *
 */

@SuppressWarnings("serial")
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
	 * ** ABSCHNITT, Beginn: Methoden für Nutzer-Objekte
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
	 * Ändern von Attributen eines Nutzers.
	 */

	public Nutzer update(Nutzer nutzer) throws Exception {
		return nutzerMapper.update(nutzer);
	}

	/**
	 * Löschen eines Nutzers. Der Nutzer wird in der Datenbank gelöscht.
	 * zugehörende nachrichten werden auch gelöscht
	 */
	public void delete(Nutzer nutzer) throws Exception {

		ArrayList<Nachricht> nachrichten = this.nachrichtMapper.alleNachrichtenJeNutzer(nutzer);
		/*
		 * Die Verbindung zum Abonnement wird aufgel�st.
		 */
		Nutzerabonnement nutzerabo = this.nutzerAboMapper.findNutzerAbonnementByID(nutzer.getId());

		// if (nutzerabo != null) {
		// for (Nutzerabonnement nabo : nutzerabo) {
		this.delete(nutzerabo);
		// }

		/*
		 * Die Nachrichten die eine Verbindung zum Nutzer haben werden gel�scht.
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
		return this.nutzerMapper.findNutzerByEmail(nickname);
	}

	/**
	 * Auslesen eines Nutzers anhand seiner ID.
	 */
	public Nutzer getNutzerById(int id) throws Exception {
		return this.nutzerMapper.findNutzerById(id);
	}
	
	/**
	 * Prüfen ob Nutzer schon existiert.
	 */
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

	/*
	 * *************************************************************************
	 * ** ABSCHNITT, Ende: Methoden für Nutzer-Objekte
	 * *****************************
	 * **********************************************
	 */

	/*
	 * *************************************************************************
	 * ** ABSCHNITT, Beginn: Methoden für Nachricht-Objekte
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

		// erster Fremdschlüssel
		absender = this.nutzerMapper.findNutzerByEmail(nickname);

	    //Nutzer empfaenger = new Nutzer();

		Unterhaltungsliste unterhaltungsliste = new Unterhaltungsliste();

		// zweiter Fremdschlüssel
		unterhaltungsliste = this.unterhaltungslisteMapper.findByAbsender(absender);
		// this.unterhaltungslisteMapper.findByAbsender(absender);

		// 3. Fremschlüssel
		Nutzer empfaenger =  this.nutzerMapper.findNutzerByEmail(empf);

		System.out.println(absender.getId() + " " + unterhaltung.getId());

		na.setNutzerID(absender.getId());
		na.setUnterhaltungID(unterhaltung.getId());

		na.setText(text);
		 this.nachrichtMapper.insert(na);
		 return nachrichtMapper.getMaxID();
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
	 * ** ABSCHNITT, Ende: Methoden für Nachricht-Objekte
	 * **************************
	 * *************************************************
	 */

	/*
	 * *************************************************************************
	 * ** ABSCHNITT, Beginn: Methoden für Unterhaltung-Objekte
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
	
	/**
	 * Auslesen der zuletzt erstellten Unterhaltung
	 */
	public Unterhaltung getMaxID() throws Exception{
//		this.unterhaltungMapper.insert();
		System.out.println("anfang ovn getMaxID in Impl");
		Date d = null; 
		//createUnterhaltung(d);
		System.out.println(this.unterhaltungMapper.getMaxID());
		return this.unterhaltungMapper.getMaxID();
	}



	/**
	 * Löschen einer Unterhaltung. Hierbei werden die Nutzer, die in der
	 * Unterhaltung teilgenommen haben nicht gel�scht.
	 * @throws Exception 
	 */
	public void delete(Unterhaltung unterhaltung) throws Exception {
		/*
		 * Zugehörige Nachrichten von der Unterhaltung werden gel�scht
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
	 * ** ABSCHNITT, Ende: Methoden für Unterhaltung-Objekte
	 * ***********************
	 * ****************************************************
	 */

	/*
	 * *************************************************************************
	 * ** ABSCHNITT, Beginn: Methoden für Abonnement-Objekte
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
	 * ** ABSCHNITT, Ende: Methoden f�r Abonnement-Objekte
	 * *************************
	 * **************************************************
	 */

	/*
	 * *************************************************************************
	 * ** ABSCHNITT, Beginn: Methoden f�r Hashtag-Objekte
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
	 * Löschen eines Hashtags
	 */
	public void delete(Hashtag hashtag) throws Exception {
		/*
		 * Die Verbindung zum Hashtagabonnement wird aufgel�st.
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
	public Hashtag getHashtagByHashtag(String bez) throws Exception {
		return this.hashtagMapper.findHashtagByText(bez);
	}

	/*
	 * *************************************************************************
	 * ** ABSCHNITT, Ende: Methoden für Hashtag-Objekte
	 * ****************************
	 * ***********************************************
	 */

	/*
	 * *************************************************************************
	 * ** ABSCHNITT, Beginn: Methoden für NutzerAbo-Objekte
	 * ************************
	 * ***************************************************
	 */

	/**
	 * Anlegen eines Nutzerabonnements
	 */
	public Nutzerabonnement createNutzerabonnement(Nutzer derBeobachtete, Nutzer follower)
			throws Exception {

		Nutzerabonnement nutzabo = new Nutzerabonnement();
		nutzabo.setDerBeobachteteID(derBeobachtete.getId());
		nutzabo.setFollowerID(follower.getId());
		return nutzerAboMapper.insert(nutzabo);
	}
	
	/**
	 * Auslesen der Nutzerabonnenten anhand deren Nutzer ID
	 */
	public Nutzerabonnement findAboByNutzerID(int id, int id2) throws Exception {
		
		if(this.nutzerAboMapper.findAboByNutzerID(id, id2) == null){
			Nutzerabonnement na = new Nutzerabonnement();
			na.setId(0);
			return na;
		}
		return this.nutzerAboMapper.findAboByNutzerID(id, id2); 
	}

	/**
	 * Löschen eines Nutzerabonnement
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
	 * Auslesen der Nutzerabonnenten anhand der BeobachtetenID
	 */
	public ArrayList<Nutzerabonnement> getAllNutzerabonnementsByBeobachteteID(String userID) throws Exception {
		return this.nutzerAboMapper.findAllNutzerabonnementsByBeobacheteteID(userID);
	}
	
	public ArrayList<Nutzerabonnement> getAllNutzerabonnementsByBeobachteteMail(String userMail) throws Exception {
		Nutzer nutzer = new Nutzer();
		nutzer = this.nutzerMapper.findNutzerByEmail(userMail);
		
		return this.nutzerAboMapper.findAllNutzerabonnementsByBeobacheteteID(nutzer.getId());
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
	 * ** ABSCHNITT, Ende: Methoden für NutzerAbo-Objekte
	 * **************************
	 * *************************************************
	 */

	/*
	 * *************************************************************************
	 * ** ABSCHNITT, Beginn: Methoden für HashtagAbo-Objekte
	 * ***********************
	 * ****************************************************
	 */

	/**
	 * Anlegen eines Hashtagabonnements.
	 */
	public Hashtagabonnement createHashtagAbonnement(Hashtagabonnement bezeichnung) throws Exception {
		//Hashtagabonnement b = new Hashtagabonnement();
		//b.setHashtagID(bezeichnung.getId());
		return this.hashtagAboMapper.insert(bezeichnung);
	}

	/**
	 * Löschen eines Hashtagsabonnement.
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
	
	public Hashtagabonnement getHashtagabonnementByNutzerIdHashtagID(int nutzer, int hashtag) throws Exception {
		
		if(this.hashtagAboMapper.findHashtagAbonnementByNutzerIDHashtagID(nutzer, hashtag) == null){
			Hashtagabonnement ha = new Hashtagabonnement();
			ha.setId(0);
			return ha;
		}
		
		return this.hashtagAboMapper.findHashtagAbonnementByNutzerIDHashtagID(nutzer, hashtag);
	}

	public ArrayList<Nutzer> getNutzerByHashtagAbo(String text) throws Exception {
		int hid = 0;
		ArrayList<Nutzer> alleNutzer = new ArrayList <Nutzer>();
		//Hashtag h = 
		hid = this.hashtagMapper.findHashtagByText(text).getId();
		ArrayList<Hashtagabonnement> hsa = this.hashtagAboMapper.findAlleHashtagAbonnementByHashtagID(hid);
		
		for(Hashtagabonnement hs : hsa){
			Nutzer n = new Nutzer();
			n = this.getNutzerById(hs.getNutzerID());
			System.out.println(n.getNickname());
			alleNutzer.add(n);
		}
		return alleNutzer;
	}
	/**
	 * Auslesen eines Hashtagsabonnement anhand der Hashtag-ID
	 */
	public ArrayList<Hashtagabonnement> getHashtagAbonnementByHashtagId(String text) throws Exception {
		int hid = 0;
		System.out.println("getHashtagAbonnementByHashtagId "+hid);
		Hashtag h = this.hashtagMapper.findHashtagByText(text);
		System.out.println("Objekt "+ text); 

		if(h == null){
			System.out.println(text + " Existiert nicht");
			Hashtag hashtag = new Hashtag();
			hashtag.setBezeichnung(text); 
			this.hashtagMapper.insert(hashtag);
			hid = this.hashtagMapper.findHashtagByText(text).getId();
		}else{
			System.out.println(text + " Existiert");
			hid = this.hashtagMapper.findHashtagByText(text).getId();
		}

		return this.hashtagAboMapper.findHashtagAbonnementByHashtagID(hid);
	}

	/**
	 * Auslesen eines Hashtagsabonnement anhand der Abonnement-ID
	 */
	public Hashtagabonnement getHashtagAbonnementByAbonnementId(int id) throws Exception {
		return this.hashtagAboMapper.findHashtagAbonnementByAbonnementID(id);
	}

	/*
	 * *************************************************************************
	 * ** ABSCHNITT, Ende: Methoden für HashtagAbo-Objekte
	 * *************************
	 * **************************************************
	 */

	/*
	 * *************************************************************************
	 * ** ABSCHNITT, Beginn: Methoden für Markierungsliste-Objekte
	 * ***********************
	 * ****************************************************
	 */

	/*
	 * *************************************************************************
	 * ** ABSCHNITT, Ende: Methoden für Markierungsliste-Objekte
	 * *************************
	 * **************************************************
	 */

	/**
	 * Anlegen einer Markierungsliste.
	 */
	public Markierungsliste createMarkierungsliste(Nachricht text, Hashtag hashtag) throws Exception {
		Markierungsliste markierungsliste = new Markierungsliste();
		
		markierungsliste.setNachrichtID(text.getId());
//		String existiertID = this.hashtagMapper.findHashtagByText(hashtag.getBezeichnung()).getBezeichnung();
//		
//		if(existiertID == ""){
//			this.hashtagMapper.insert(hashtag);
//			System.out.println("hashtag hinzugef�gt");
//		}
		
		int hID = this.hashtagMapper.findHashtagByText(hashtag.getBezeichnung()).getId();
		System.out.println("hashtag ID " + hID);
		markierungsliste.setHashtagID(hID);
		return this.markierungslisteMapper.insert(markierungsliste);

	}
	/*
	 * *************************************************************************
	 * ** ABSCHNITT, Beginn: Methoden für Unterhaltungsliste-Objekte
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
		System.out.println("Empf�nger "+empfaenger.getId() + " " + empf);
		
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
	 * Auslesen einer Unterhaltungsliste anhand des Empf�ngers.
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
		empf = this.nutzerMapper.findNutzerByEmail(empfaenger);
		System.out.println("UnterhaltungID "+this.unterhaltungslisteMapper.pruefeUnterhaltung(absender, empf.getId()).getUnterhaltungID());
		return this.unterhaltungslisteMapper.pruefeUnterhaltung(absender, empf.getId());
	}
	
	
	public void deleteUnterhaltungsliste (String absender, String empfaenger) throws Exception {
		
		Nutzer empf = new Nutzer();
		empf = this.nutzerMapper.findNutzerByEmail(empfaenger);
		System.out.println("UnterhaltungID "+this.unterhaltungslisteMapper.pruefeUnterhaltung(absender, empf.getId()).getUnterhaltungID());
		
		Unterhaltungsliste unterhaltungsliste = this.unterhaltungslisteMapper.pruefeUnterhaltung(absender, empf.getId());
		
		this.unterhaltungslisteMapper.delete(unterhaltungsliste); 
	}
	
	/**
	 * Auslesen der Nachricht anhand des Nicknames.
	 */
	public ArrayList<Nachricht> getNachrichtByNickname(String nickname ) throws Exception {
		Nutzer nutzer = this.nutzerMapper.findNutzerByEmail(nickname);
		return this.nachrichtMapper.alleNachrichtenJeNutzer(nutzer);
	}

	/*
	 * *************************************************************************
	 * ** ABSCHNITT, Ende: Methoden Unterhaltungsliste-Objekte
	 * *************************
	 * **************************************************
	 */

}
