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
 * wie eine Spinne, die sämtliche Zusammenhänge in ihrem Netz (in unserem Fall
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
 * Server-seitige - Interface, das die im System zur Verfügung gestellten
 * Funktionen deklariert.</li>
 * <li>{@link MessagingAdministrationAsync}:
 * <code>MessagingAminidstrationImpl</code> und
 * <code>MessagingAdministration</code> bilden nur die Server-seitige Sicht der
 * Applikationslogik ab. Diese basiert vollständig auf synchronen
 * Funktionsaufrufen. Wir müssen jedoch in der Lage sein, Client-seitige
 * asynchrone Aufrufe zu bedienen. Dies bedingt ein weiteres Interface, das in
 * der Regel genauso benannt wird, wie das synchrone Interface, jedoch mit dem
 * zusätzlichen Suffix "Async". Es steht nur mittelbar mit dieser Klasse in
 * Verbindung. Die Erstellung und Pflege der Async Interfaces wird durch das
 * Google Plugin semiautomatisch unterstützt. Weitere Informationen unter
 * {@link MessagingAdministrationAsync}.</li>
 * <li> {@link RemoteServiceServlet}: Jede Server-seitig instantiierbare und
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
 * Beachten Sie, dass sämtliche Methoden, die mittels GWT RPC aufgerufen werden
 * können ein <code>throws IllegalArgumentException</code> in der
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
 * @author Yücel
 * @author Nguyen
 * @author Raue
 * 
 *
 */

public class MessagingAdministrationImpl extends RemoteServiceServlet implements
		MessagingAdministration {

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
	 * ***************************************************************************
	 * ABSCHNITT, Beginn: Initialisierung
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
	 * ***************************************************************************
	 * ABSCHNITT, Ende: Initialisierung
	 * *****************************************
	 * **********************************
	 */

	/*
	 * ***************************************************************************
	 * ABSCHNITT, Beginn: Methoden für Nutzer-Objekte
	 * ***************************
	 * ************************************************
	 */

	/**
	 * Anlegen eines neuen Nutzers. Der neuer Nutzer wird in die Datenbank
	 * gespeichert.
	 */
	public Nutzer createNutzer(String vorname, String nachname, String email,
			String nickname) throws IllegalArgumentException {
		
		if (!userExist(email)){
			System.out.println("User Existiert: "+userExist(email));
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
	
	public Nutzer update(Nutzer nutzer)throws IllegalArgumentException{
		return nutzerMapper.update(nutzer);
	}

	/**
	 * Löschen eines Nutzers. Der Nutzer wird in der Datenbank gelöscht.
	 * zugehörende nachrichten werden auch gelöscht
	 */
	public void delete(Nutzer nutzer) throws IllegalArgumentException {

		ArrayList<Nachricht> nachrichten = this
				.nachrichtMapper.alleNachrichtenJeNutzer(nutzer);
		/*
		 * Die Verbindung zum Abonnement wird aufgelöst.
		 */
		Nutzerabonnement nutzerabo = 
				this.nutzerAboMapper.findNutzerAbonnementByID(nutzer.getId());

//		if (nutzerabo != null) {
//			for (Nutzerabonnement nabo : nutzerabo) {
				this.delete(nutzerabo);
//			}

			/* Die Nachrichten die eine Verbindung zum Nutzer haben werden
			* gelöscht.
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
	public ArrayList<Nutzer> getAllNutzer() throws IllegalArgumentException {
		return this.nutzerMapper.findAllNutzer();
	}

	/**
	 * Auslesen eines Nutzers anhand seines Nickname.
	 */
	public Nutzer getNutzerByNickname(String nickname)
			throws IllegalArgumentException {
		return this.nutzerMapper.findNutzerByNickname(nickname);
	}
	

	
	/**
	 * Auslesen eines Nutzers anhand seiner ID.
	 */
	public Nutzer getNutzerById (int id)
			throws IllegalArgumentException{
		return this.nutzerMapper.findNutzerById(id);
	}

	/*
	 * ***************************************************************************
	 * ABSCHNITT, Ende: Methoden für Nutzer-Objekte
	 * *****************************
	 * **********************************************
	 */

	/*
	 * ***************************************************************************
	 * ABSCHNITT, Beginn: Methoden für Nachricht-Objekte
	 * ************************
	 * ***************************************************
	 */

	/**
	 * Anlegen einer neuen Nachricht. Die Nachricht wird in der Datenbank
	 * gespeichert.
	 */
	public Nachricht createNachricht(String text, String nickname, Unterhaltung unterhaltung)
			throws IllegalArgumentException {
		
		Nachricht na = new Nachricht();
		// NutzerID durch emailadresse heraussuchen
		Nutzer absender = new Nutzer();
		
		// erster Fremdschlüssel
		absender = this.nutzerMapper.findNutzerByNickname(nickname);
		
		//Nutzer empfaenger = new Nutzer();
		
		//Unterhaltungsliste unterhaltungsliste = new Unterhaltungsliste();
		
		// zweiter Fremdschlüssel
		//unterhaltungsliste = this.unterhaltungslisteMapper.findByAbsender(absender);
		
		// 3. Fremschlüssel
		// empfaenger = this.nutzerMapper.findNutzerByNickname(empfaengerNickname);
		
		System.out.println(absender.getId() + " " + unterhaltung.getId());
		
		na.setNutzerID(absender.getId());
		na.setUnterhaltungID(unterhaltung.getId());
		
		na.setText(text);
		return this.nachrichtMapper.insert(na);
	}

	public void delete(Nachricht nachricht) throws IllegalArgumentException {
		this.nachrichtMapper.delete(nachricht);
	}

	/**
	 * Auslesen aller Nachrichten aus der Datenbank.
	 */
	public ArrayList<Nachricht> getAllNachrichten()
			throws IllegalArgumentException {
		return null; //this.nachrichtMapper.findAllNachrichten();
	}
	
	/**
	 * Auslesen aller Nachrichten einer Unterhaltung aus der Datenbank.
	 */
	public ArrayList<Nachricht> getNachrichtenByUnterhaltung(Unterhaltung unterhaltung)
			throws IllegalArgumentException{
		return this.unterhaltungMapper.findNachrichtenByUnterhaltung(unterhaltung);
	}
	
	/**
	 * Auslesen Nachrichten anhand der Id.
	 */
	
	public Nachricht getNachrichtById(int id) throws IllegalArgumentException {
		return this.nachrichtMapper.findNachrichtById(id);
	}
	


	/**
	 * Auslesen von Nachrichten eines Nutzers.
	 */
	public ArrayList<Nachricht> getAlleNachrichtenJeNutzer(Nutzer nutzer)
			throws IllegalArgumentException {
		return this.nachrichtMapper.alleNachrichtenJeNutzer(nutzer);
	}

	/**
	 * Auslesen von Nachrichten innerhalb eines Zeitraums
	 */
	public ArrayList<Nachricht>getAlleNachrichtenJeZeitraum(String von, String bis)
			throws IllegalArgumentException{
		return this.nachrichtMapper.alleNachrichtenJeZeitraum(von, bis);
	}
	

	
	
	

	/*
	 * ***************************************************************************
	 * ABSCHNITT, Ende: Methoden für Nachricht-Objekte
	 * **************************
	 * *************************************************
	 */

	/*
	 * ***************************************************************************
	 * ABSCHNITT, Beginn: Methoden für Unterhaltung-Objekte
	 * *********************
	 * ******************************************************
	 */

	/**
	 * Anlegen einer Unterhaltung. Die Unterhaltung wird in der Datenbank
	 * gespeichert.
	 */
	public Unterhaltung createUnterhaltung(Date datum)
			throws IllegalArgumentException {
		Unterhaltung u = new Unterhaltung();
//		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		
		u.setZuletztBearbeitet(dateFormat.format(datum));
		u.setErstellungsZeitpunkt(dateFormat.format(datum));
		return this.unterhaltungMapper.insert(u);

//		u.setErstellungsZeitpunkt(datum);
//		return unterhaltungMapper.insert(datum);

	}

	public Boolean userExist(String email) throws IllegalArgumentException{
		
		Boolean exist = false;
		Nutzer nutzer = this.nutzerMapper.findNutzerByEmail(email);
		
		//System.out.println(nutzer.getEmail() + " "+ nutzer.getId());
		
		if(nutzer != null){
			exist = true;
			System.out.println("true");
		}

		
		return exist;
	}
	
	
	/**
	 * Löschen einer Unterhaltung. Hierbei werden die Nutzer, die in der
	 * Unterhaltung teilgenommen haben nicht gelöscht.
	 */
	public void delete(Unterhaltung unterhaltung) {
		/*
		 * Zugehörige Nachrichten von der Unterhaltung werden gelöscht
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
	 
	public ArrayList<Nachticht> getAllUnterhaltungen(Unterhaltung unterhaltung)
			throws IllegalArgumentException {
		return this.unterhaltungMapper.findNachrichtenByUnterhaltung(unterhaltung);
	}*/

	

	/*
	 * ***************************************************************************
	 * ABSCHNITT, Ende: Methoden für Unterhaltung-Objekte
	 * ***********************
	 * ****************************************************
	 */

	/*
	 * ***************************************************************************
	 * ABSCHNITT, Beginn: Methoden für Abonnement-Objekte
	 * ***********************
	 * ****************************************************
	 */
	/**
	 * Anlegen eines Abonnements. Das Abonnement wird in der Datenbank
	 * gespeichert.
	 */
	public Abonnement createAbonnement(int id, String erstellungsZeitpunkt)
			throws IllegalArgumentException{
		Abonnement abo= new Abonnement();
		abo.setId(id);
		abo.setErstellungsZeitpunkt(erstellungsZeitpunkt);
		return this.abonnementMapper.insertAbonnement(abo);
	}
	

	/**
	 * Auslesen aller Abonnements.
	 */
	public ArrayList<Abonnement> getAllAbonnements()
			throws IllegalArgumentException {
		return this.abonnementMapper.findAllAbonnements();
	}

	/**
	 * Auslesen aller Abonnements anhand deren Id.
	 */
	public Abonnement getAbonnementById(int id) throws IllegalArgumentException {
		return this.abonnementMapper.findAbonnementById(id);
	}

	/*
	 * ***************************************************************************
	 * ABSCHNITT, Ende: Methoden für Abonnement-Objekte
	 * *************************
	 * **************************************************
	 */

	/*
	 * ***************************************************************************
	 * ABSCHNITT, Beginn: Methoden für Hashtag-Objekte
	 * **************************
	 * *************************************************
	 */
	
	/**
	 * Anlegen eines Hashtags
	 */
	public Hashtag createHashtag(String bezeichnung)
			throws IllegalArgumentException {
		Hashtag hash = new Hashtag();
		hash.setBezeichnung(bezeichnung);
		return hashtagMapper.insert(hash);
	}
	

	/**
	 * LÖschen eines Hashtags
	 */
	public void delete(Hashtag hashtag) 
			throws IllegalArgumentException {
		/*
		 * Die Verbindung zum Hashtagabonnement wird aufgelöst.
		 */
//		//Muss noch verbessert werden
//		ArrayList<Hashtagabonnement> hashtagabo = this.getHashtagAbonnementByNutzer(hashtag);
//						
//		
//		if (hashtagabo != null) {
//			for (Hashtagabonnement hashabo : hashtagabo) {
//				this.delete(hashabo);
//			}
		hashtagMapper.delete(hashtag);
		}	
	
	
	/**
	 * Auslesen aller Hashtags.
	 */
	public ArrayList<Hashtag>getAllHashtags()
			throws IllegalArgumentException{
		return this.hashtagMapper.findAllHashtags();
	}
	
	/**
	 * Auslesen eines Hashtag anhand seiner ID.
	 */
	public Hashtag getHashtagById(int id)
			throws IllegalArgumentException{
		return this.hashtagMapper.findHashtagByID(id);
	}


	/*
	 * ***************************************************************************
	 * ABSCHNITT, Ende: Methoden für Hashtag-Objekte
	 * ****************************
	 * ***********************************************
	 */

	/*
	 * ***************************************************************************
	 * ABSCHNITT, Beginn: Methoden für NutzerAbo-Objekte
	 * ************************
	 * ***************************************************
	 */

	/*
	 * Anlegen eines Nutzerabonnements
	 */
	public Nutzerabonnement createNutzerabonnement(Nutzer derBeobachtete,
			Nutzer follower) throws IllegalArgumentException {
		
		 Nutzerabonnement nutzabo = new Nutzerabonnement();
		 nutzabo.setDerBeobachteteID(derBeobachtete.getId());
		 nutzabo.setFollowerID(follower.getId());
		return nutzerAboMapper.insert(nutzabo);
	}

	/*
	 * Löschen eines Nutzerabonnement
	 */
	public void delete(Nutzerabonnement nutzerAbo)
			throws IllegalArgumentException {
		nutzerAboMapper.delete(nutzerAbo);
		;

	}
	
	/**
	 * Auslesen von allen Nutzerabonnements.
	 */
	public ArrayList<Nutzerabonnement> getAllNutzerabonnements()
			throws IllegalArgumentException {
		return this.nutzerAboMapper.findAllNutzerabonnements();
	}
	
	/**
	 * Auslesen eines Nutzerabonnements anhand seiner ID.
	 */
	public Nutzerabonnement getNutzerabonnementById(int id)
			throws IllegalArgumentException {
		return this.nutzerAboMapper.findNutzerAbonnementByID(id);
	}
	
	
	/**
	 * Auslesen von Nutzer die die Rolle des des Beobachteten haben .
	 */
	public ArrayList<Nutzerabonnement> getNutzerAbonnementByNutzer(
			Nutzer nutzer) throws IllegalArgumentException {
		return this.nutzerAboMapper.findNutzerAbonnementByAbonnementID(nutzer.getId());
	}

	/*
	 * ***************************************************************************
	 * ABSCHNITT, Ende: Methoden für NutzerAbo-Objekte
	 * **************************
	 * *************************************************
	 */

	/*
	 * ***************************************************************************
	 * ABSCHNITT, Beginn: Methoden für HashtagAbo-Objekte
	 * ***********************
	 * ****************************************************
	 */

	/**
	 * Anlegen eines Hashtagabonnements.
	 */
	public Hashtagabonnement createHashtagAbonnement(Hashtag bezeichnung)
			throws IllegalArgumentException {
		Hashtagabonnement b = new Hashtagabonnement();
		b.setHashtagID(bezeichnung.getId());
		return this.hashtagAboMapper.insert(b);
	}

	/**
	 * Löschen eines Hashtagsabonnement.
	 */
	public void delete(Hashtagabonnement hashtagAbo)
			throws IllegalArgumentException {
		this.hashtagAboMapper.delete(hashtagAbo);

	}
	
	/**
	 * Auslesen aller Hashtagabonnements.
	 */
	public ArrayList<Hashtagabonnement> getAllHashtagabonnements()
			throws IllegalArgumentException {
		return this.hashtagAboMapper.findAllHashtagabonnements();	
	}

	/**
	 * Auslesen eines Hashtagabonnements anhand einer Id.
	 */
	public Hashtagabonnement getHashtagAboById(int id)
			throws IllegalArgumentException {
		return this.hashtagAboMapper.findHashtagAbonnementByID(id);
	}

	/**
	 * Auslesen eines Hashtagabonnements anhand des Nutzers.
	 */
	public ArrayList<Hashtagabonnement> getHashtagabonnementByNutzer(Nutzer nutzer)
			throws IllegalArgumentException{
//		return this.hashtagAboMapper.findHashtagAbonnementByNutzer(nutzer);
		return null;
	}
	


	/*
	 * ***************************************************************************
	 * ABSCHNITT, Ende: Methoden für HashtagAbo-Objekte
	 * *************************
	 * **************************************************
	 */
	
	/*
	 * ***************************************************************************
	 * ABSCHNITT, Beginn: Methoden für Markierungsliste-Objekte
	 * ***********************
	 * ****************************************************
	 */
	
	/*
	 * ***************************************************************************
	 * ABSCHNITT, Ende: Methoden für Markierungsliste-Objekte
	 * *************************
	 * **************************************************
	 */
	
	/**
	 * Anlegen einer Markierungsliste.
	 */
	public Markierungsliste createMarkierungsliste(String text,String hashtag)
			throws IllegalArgumentException{
		Markierungsliste markierungsliste= new Markierungsliste();
		Nachricht nachricht= new Nachricht();
		Hashtag hash= new Hashtag();
		//muss noch gemacht werden
		return null;
		
	}
	/*
	 * ***************************************************************************
	 * ABSCHNITT, Beginn: Methoden für Unterhaltungsliste-Objekte
	 * ***********************
	 * ****************************************************
	 */
	
	/**
	 * Anlegen einer Unterhaltungsliste.
	 */
	public Unterhaltungsliste createUnterhaltungsliste(Unterhaltung u, String sender, String empf)
			throws IllegalArgumentException{
		Unterhaltungsliste UListe= new Unterhaltungsliste();
		Unterhaltung unterhaltung = new Unterhaltung();
		Nutzer absender = new Nutzer();
		Nutzer empfaenger = new Nutzer();
		//muss noch gemacht werden
		return null;

		
	}

	/**
	 * Auslesen einer Unterhaltungsliste anhand des Absender.
	 */
	public Unterhaltungsliste getByAbsender(String absenderNickname) 
			throws IllegalArgumentException {
		//nickanme die Id ermitteln und das muss man dan in die findByAbsender weiter geben.
		return this.unterhaltungslisteMapper.findByAbsender(absenderNickname);
	}
	
	
	/**
	 * Auslesen einer Unterhaltungsliste anhand des Empfängers.
	 */
	public Unterhaltungsliste getByEmpfaenger(String empfaengerNickname) 
			throws IllegalArgumentException {
		//nickanme die Id ermitteln und das muss man dan in die findByAbsender weiter geben.
		return this.unterhaltungslisteMapper.findByEmpfaenger(empfaengerNickname);
	}
	
	/**
	 * Auslesen einer Unterhaltungsliste anhand einer Unterhaltung.
	 */
	public Unterhaltungsliste getByUnterhaltung(Unterhaltung unterhaltung)
			throws IllegalArgumentException{
		return this.unterhaltungslisteMapper.findByUnterhaltung(unterhaltung);
	}



	/*
	 * ***************************************************************************
	 * ABSCHNITT, Ende: Methoden Unterhaltungsliste-Objekte
	 * *************************
	 * **************************************************
	 */

}
