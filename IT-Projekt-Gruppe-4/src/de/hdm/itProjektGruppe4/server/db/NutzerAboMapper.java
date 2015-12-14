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
			Statement stmt = con.createStatement();
			// Zun�chst wird geschaut welches der momentan h�chste
			// Prim�rschl�ssel ist
			ResultSet rs = stmt
					.executeQuery("SELECT MAX(nutzerAboID) AS maxid "
							+ "FROM nutzerabonnements");

			// Wenn ein Datensatz gefunden wurde, wird auf diesen zugegriffen
			if (rs.next()) {
				int newID = rs.getInt("maxid");
				nutzerabonnement.setId(newID);

				PreparedStatement preStmt;
				preStmt = con
						.prepareStatement("INSERT INTO nutzerabonnements, erstellungsZeitpunkt VALUES(?, ?)");
				preStmt.setInt(1, newID);
				preStmt.setString(2, getSqlDateFormat(nutzerabonnement
						.getErstellungsZeitpunkt()));
				preStmt.executeUpdate();
				preStmt.close();
			}
			stmt.close();
			rs.close();
			con.close();
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
			stmt.executeUpdate("DELETE FROM nutzerabonnements WHERE nutzerAboID="
					+ nutzerabonnement.getId());
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Datenbank fehler!"
					+ e.toString());
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
					.executeQuery("SELECT * FROM unterhaltungen ORDER BY unterhaltungID");

			while (rs.next()) {
				Nutzerabonnement nutzerabonnement = new Nutzerabonnement();
				nutzerabonnement.setId(rs.getInt("unterhaltungID"));
				nutzerabonnement.setErstellungsZeitpunkt(rs
						.getDate("erstellungsZeitpunkt"));
				allNutzerabonnements.add(nutzerabonnement);
			}
			stmt.close();
			rs.close();
			con.close();
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
	public Unterhaltung findUnterhaltungById(int id) {
		Connection con = DBConnection.connection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT unterhaltungID, erstellungsZeitpunkt FROM unterhaltungen "
							+ "WHERE unterhaltung_id= "
							+ id
							+ " ORDER BY unterhaltung_id");

			if (rs.next()) {
				Unterhaltung unterhaltung = new Unterhaltung();
				unterhaltung.setId(rs.getInt("unterhaltung_id"));
				unterhaltung.setErstellungsZeitpunkt(rs
						.getDate("erstellungsZeitpunkt"));

				return unterhaltung;
			}
			stmt.close();
			rs.close();
			con.close();
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

	public ArrayList<Nutzerabonnement> findNutzerAbonnementByNutzer(String von,
			String bis, int id) {
		Connection con = DBConnection.connection();
		ArrayList<Nutzerabonnement> nutzerAboListe = new ArrayList<Nutzerabonnement>();

		try {

			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM nutzerabonnements "
					+ "WHERE nutzerAboID=" + id + " ORDER BY nutzerAboID");

			while (rs.next()) {
				Nutzerabonnement nutzerabonnement = new Nutzerabonnement();
				nutzerabonnement.setId(rs.getInt("nutzerID"));
				nutzerabonnement.setDerBeobachteteID(rs
						.getInt("derBeobachteteID"));
				nutzerabonnement.setErstellungsZeitpunkt(rs
						.getTimestamp("datum"));

				nutzerAboListe.add(nutzerabonnement);
			}

		}

		catch (SQLException e1) {
			e1.printStackTrace();
			return null;
		}

		return null; // nutzerAboListe;

	}

	/**
	 * Wandelt aus einem Date Objekt einen String in passendem SQL Übergabe
	 * Format.
	 * 
	 * @param date
	 *            Date das konvertiert werden soll
	 * @return String mit Date im Format yyyy-MM-dd HH:mm:ss
	 */
	private String getSqlDateFormat(Date date) {
		String result = "";
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		result = dateFormat.format(date);
		return result;
	}
}
