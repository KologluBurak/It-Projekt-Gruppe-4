package de.hdm.itProjektGruppe4.server.db;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import de.hdm.itProjektGruppe4.shared.bo.*;

/**
 * Mapper-Klasse, die <code>NutzerAbo</code>-Objekte auf eine relationale
 * Datenbank abbildet. Hierzu wird eine Reihe von Methoden zur Verf�gung
 * gestellt, mit deren Hilfe z.B. Objekte gesucht, erzeugt, modifiziert und
 * gel�scht werden k�nnen. Das Mapping ist bidirektional. D.h., Objekte k�nnen
 * in DB-Strukturen und DB-Strukturen in Objekte umgewandelt werden.
 * 
 * @author Kologlu
 * @author Oikonomou
 * @author Thies
 * @author Yücel
 */

public class NutzerAboMapper {

	/**
	 * Die Klasse NutzerAboMapper wird nur einmal instantiiert. Man spricht
	 * hierbei von einem sogenannten <b>Singleton</b>.
	 * <p>
	 * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal
	 * f�r s�mtliche eventuellen Instanzen dieser Klasse vorhanden. Sie
	 * speichert die einzige Instanz dieser Klasse.
	 * 
	 * @see NutzerAboMapper()
	 */
	private static NutzerAboMapper nutzerAboMapper = null;

	/**
	 * Gesch�tzter Konstruktor - verhindert die M�glichkeit, mit
	 * <code>new</code> neue Instanzen dieser Klasse zu erzeugen.
	 */
	protected NutzerAboMapper() {
	}

	/**
	 * Diese statische Methode kann aufgrufen werden durch
	 * <code>NutzerAboMapper.nutzerAboMapper()</code>. Sie stellt die
	 * Singleton-Eigenschaft sicher, indem Sie daf�r sorgt, dass nur eine
	 * einzige Instanz von <code>NutzerAboMapper</code> existiert.
	 * <p>
	 * 
	 * <b>Fazit:</b> NutzerAboMapper sollte nicht mittels <code>new</code>
	 * instantiiert werden, sondern stets durch Aufruf dieser statischen
	 * Methode.
	 * 
	 * @return DAS <code>NutzerAboMapper</code>-Objekt.
	 * @see nutzerAboMapper
	 */
	public static NutzerAboMapper nutzerAboMapper() {
		if (nutzerAboMapper == null) {
			nutzerAboMapper = new NutzerAboMapper();
		}
		return nutzerAboMapper;

	}

	/**
	 * Einf�gen eines <code>Nutzerabonnement</code>-Objekts in die Datenbank.
	 * Dabei wird auch der Prim�rschl�ssel des �bergebenen Objekts gepr�ft und
	 * ggf. berichtigt.
	 * 
	 * @param nutzerabonnement
	 *            das zu speichernde Objekt
	 * @return das bereits �bergebene Objekt, jedoch mit ggf. korrigierter
	 *         <code>id</code>.
	 */
	public Nutzerabonnement insert(Nutzerabonnement nutzerabonnement)
			throws IllegalArgumentException {
		// DB-Verbindung herstellen
		Connection con = DBConnection.connection();
		try {
			// Insert-Statement erzeugen
			//Statement stmt = con.createStatement();
			
			// Zun�chst wird geschaut welches der momentan h�chste
			// Prim�rschl�ssel ist
			//ResultSet rs = stmt.executeQuery("SELECT MAX(nutzerAboID) AS maxid FROM nutzerabonnements");

			// Wenn ein Datensatz gefunden wurde, wird auf diesen zugegriffen
			//if (rs.next()) {

				PreparedStatement preStmt;
				preStmt = con.prepareStatement("INSERT INTO nutzerabonnements, datum VALUES(null, ?)");
				preStmt.setString(1, nutzerabonnement.getErstellungsZeitpunkt().toString());
				preStmt.executeUpdate();
				preStmt.close();
			//}
			//stmt.close();
			//rs.close();
			//con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Datenbank fehler!"
					+ e.toString());
		}
		return nutzerabonnement;
	}

	/**
	 * Diese Methode ermöglicht das Löschen eines Nutzerabonnements und dessen
	 * Referenzen zu anderen Klassen
	 * 
	 * @param nutzerabonnement
	 */
	public void delete(Nutzerabonnement nutzerabonnement)
			throws IllegalArgumentException {
		// DB-Verbindung herstellen
		Connection con = DBConnection.connection();
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM nutzerabonnements WHERE nutzerAboID="+ nutzerabonnement.getId());
			stmt.close();
			//con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Datenbank fehler!"+ e.toString());
		}
	}

	/**
	 * Diese Methode ermöglicht es alle Nutzerabonnements aus der Datenbank in
	 * einer Liste auszugeben.
	 * 
	 * @return
	 */
	public ArrayList<Nutzerabonnement> findAllNutzerabonnements() {
		Connection con = DBConnection.connection();
		ArrayList<Nutzerabonnement> allNutzerabonnements = new ArrayList<Nutzerabonnement>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT * FROM nutzerabonnements ORDER BY nutzerAboID");

			while (rs.next()) {
				Nutzerabonnement nutzerabonnement = new Nutzerabonnement();
				nutzerabonnement.setId(rs.getInt("nutzerAboID"));
				nutzerabonnement.setErstellungsZeitpunkt(rs.getDate("datum"));
				
				allNutzerabonnements.add(nutzerabonnement);
			}
			stmt.close();
			rs.close();
			//con.close();
			
			return allNutzerabonnements;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Diese Methode ermöglicht es ein Nutzerabonnement anhand ihrer ID zu
	 * finden und anzuzeigen.
	 * 
	 * @param id
	 * @return
	 */
	public Nutzerabonnement findNutzerabonnementByID(int id) {
		Connection con = DBConnection.connection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT nutzerAboID, datum FROM nutzerabonnements "+ "WHERE nutzerAboID= "+ id
							+ " ORDER BY nutzerAboID");

			if (rs.next()) {
				Nutzerabonnement nutzerabo = new Nutzerabonnement();
				nutzerabo.setId(rs.getInt("nutzerAboID"));
				nutzerabo.setErstellungsZeitpunkt(rs.getDate("datum"));

				return nutzerabo;
			}
			stmt.close();
			rs.close();
			//con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Diese Methode ermöglicht es eine Ausgabe über einen Nutzerabonnements in
	 * der Datenbank, anhand deren ID.
	 * 
	 * @param id
	 * @return
	 */

	public ArrayList<Nutzerabonnement> findNutzerAbonnementByNutzer(Nutzer nutzer) {
		Connection con = DBConnection.connection();
		ArrayList<Nutzerabonnement> nutzerAboListe = new ArrayList<Nutzerabonnement>();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM nutzerabonnements "
					+ "WHERE nutzerAboID=" + nutzer.getId() + " ORDER BY nutzerAboID");

			while (rs.next()) {
				Nutzerabonnement nutzerabonnement = new Nutzerabonnement();
				nutzerabonnement.setId(rs.getInt("nutzerAboID"));
				nutzerabonnement.setErstellungsZeitpunkt(rs.getDate("datum"));

				nutzerAboListe.add(nutzerabonnement);
			}
		}
		catch (SQLException e1) {
			e1.printStackTrace();
			return null;
		}
		return nutzerAboListe;
	}
}
