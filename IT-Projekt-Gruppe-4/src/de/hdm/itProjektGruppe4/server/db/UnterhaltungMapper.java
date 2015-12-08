package de.hdm.itProjektGruppe4.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.sql.PreparedStatement;

import de.hdm.itProjektGruppe4.shared.bo.*;

/**
 * Mapper-Klasse, die <code>Unterhaltung</code>-Objekte auf eine relationale
 * Datenbank abbildet. Hierzu wird eine Reihe von Methoden zur Verf�gung
 * gestellt, mit deren Hilfe z.B. Objekte gesucht, erzeugt, modifiziert und
 * gel�scht werden k�nnen. Das Mapping ist bidirektional. D.h., Objekte k�nnen
 * in DB-Strukturen und DB-Strukturen in Objekte umgewandelt werden.
 * 
 * @author Kologlu
 * @author Oikonomou
 * @author Thies
 */
public class UnterhaltungMapper {

	/**
	 * Die Klasse UnterhaltungMapper wird nur einmal instantiiert. Man spricht
	 * hierbei von einem sogenannten <b>Singleton</b>.
	 * <p>
	 * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal
	 * f�r s�mtliche eventuellen Instanzen dieser Klasse vorhanden. Sie
	 * speichert die einzige Instanz dieser Klasse.
	 * 
	 * @see UnterhaltungMapper()
	 */
	private static UnterhaltungMapper unterhaltungMapper = null;

	/**
	 * Gesch�tzter Konstruktor - verhindert die M�glichkeit, mit
	 * <code>new</code> neue Instanzen dieser Klasse zu erzeugen.
	 */
	protected UnterhaltungMapper() {
	}

	/**
	 * Diese statische Methode kann aufgrufen werden durch
	 * <code>UnterhaltungMapper.unterhaltungMapper()</code>. Sie stellt die
	 * Singleton-Eigenschaft sicher, indem Sie daf�r sorgt, dass nur eine
	 * einzige Instanz von <code>UnterhaltungMapper</code> existiert.
	 * <p>
	 * 
	 * <b>Fazit:</b> UnterhaltungMapper sollte nicht mittels <code>new</code>
	 * instantiiert werden, sondern stets durch Aufruf dieser statischen
	 * Methode.
	 * 
	 * @return DAS <code>UnterhaltungMapper</code>-Objekt.
	 * @see unterhaltungMapper
	 */
	public static UnterhaltungMapper unterhaltungMapper() {
		if (unterhaltungMapper == null) {
			unterhaltungMapper = new UnterhaltungMapper();
		}
		return unterhaltungMapper;
	}

	/**
	 * Einf�gen eines <code>Unterhaltung</code>-Objekts in die Datenbank. Dabei
	 * wird auch der Prim�rschl�ssel des �bergebenen Objekts gepr�ft und ggf.
	 * berichtigt.
	 * 
	 * @param a
	 *            das zu speichernde Objekt
	 * @return das bereits �bergebene Objekt, jedoch mit ggf. korrigierter
	 *         <code>id</code>.
	 */
	public Unterhaltung insert(Unterhaltung unterhaltung) throws Exception {
		// DB-Verbindung herstellen
		Connection con = DBConnection.connection();
		try {
			// Insert-Statement erzeugen
			Statement stmt = con.createStatement();
			// Zun�chst wird geschaut welches der momentan h�chste
			// Prim�rschl�ssel ist
			ResultSet rs = stmt
					.executeQuery("SELECT MAX(unterhaltungID) AS maxid "
							+ "FROM unterhaltungen");

			// Wenn ein Datensatz gefunden wurde, wird auf diesen zugegriffen
			if (rs.next()) {
				int newID = rs.getInt("maxid");
				unterhaltung.setId(newID);

				PreparedStatement preStmt;
				preStmt = con
						.prepareStatement("INSERT INTO unterhaltungen(unterhaltungID, erstellungsZeitpunkt) VALUES(?, ?)");
				preStmt.setInt(1, newID);
				preStmt.setString(
						2,
						getSqlDateFormat(unterhaltung.getErstellungsZeitpunkt()));
				preStmt.executeUpdate();
				preStmt.close();
			}
			stmt.close();
			rs.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Datenbank fehler!" + e.toString());
		}
		return unterhaltung;
	}

	/**
	 * Wiederholtes Schreiben eines Objekts in die Datenbank.
	 * 
	 * @param unterhaltung
	 *            das Objekt, das in die DB geschrieben werden soll
	 * @return das als Parameter �bergebene Objekt
	 */
	public Unterhaltung update(Unterhaltung unterhaltung) throws Exception {
		Connection con = DBConnection.connection();
		try {
			PreparedStatement preStmt;
			preStmt = con
					.prepareStatement("UPDATE nutzer SET erstellungsZeitpunkt=? WHERE unterhaltungID="
							+ unterhaltung.getId());
			preStmt.setString(1,
					getSqlDateFormat(unterhaltung.getErstellungsZeitpunkt()));
			preStmt.executeUpdate();
			preStmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Datenbank fehler!" + e.toString());
		}
		return unterhaltung;
	}

	/**
	 * L�schen der Daten eines <code>Unterhaltung</code>-Objekts aus der
	 * Datenbank.
	 * 
	 * @param id
	 *            das aus der DB zu l�schende "Objekt"
	 */
	public void delete(int id) throws Exception {
		Connection con = DBConnection.connection();
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM unterhaltung WHERE unterhaltungID="
					+ id);
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Datenbank fehler!" + e.toString());
		}
	}

	/**
	 * Diese Methode ermöglicht es alle Unterhaltungen aus der Datenbank in
	 * einer Liste auszugeben.
	 * 
	 * @return
	 */
	public ArrayList<Unterhaltung> findAllUnterhaltungen() {
		Connection con = DBConnection.connection();
		ArrayList<Unterhaltung> allUnterhaltungen = new ArrayList<Unterhaltung>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT * FROM unterhaltungen ORDER BY unterhaltung_id");

			while (rs.next()) {
				Unterhaltung unterhaltung = new Unterhaltung();
				unterhaltung.setId(rs.getInt("unterhaltung_id"));
				unterhaltung.setErstellungsZeitpunkt(rs
						.getDate("erstellungsZeitpunkt"));

				allUnterhaltungen.add(unterhaltung);
			}
			stmt.close();
			rs.close();
			con.close();
			return allUnterhaltungen;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Diese Methode ermöglicht es eine Unterhaltung anhand ihrer ID zu finden
	 * und anzuzeigen.
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
	 * Diese Methode ermöglicht es alle beteiligten Nutzer einer Unterhaltung
	 * anhand ihrer ID zu finden und anzuzeigen.
	 * 
	 * @param unterhaltung
	 * @return
	 */
	public int countNutzerFromUnterhaltung(Unterhaltung unterhaltung)
			throws Exception {
		Connection con = DBConnection.connection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT COUNT((absenderID)+(empfaengerID)) AS AnzahlAllerNutzerEinerUnterhaltung "
							+ "FROM unterhaltungslisten WHERE unterhaltungID="
							+ unterhaltung.getId());

			return rs.getInt("AnzahlAllerNutzerEinerUnterhaltung");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Datenbank fehler!" + e.toString());
		}
	}

	public int countNachrichtenFromUnterhaltung(Unterhaltung unterhaltung)
			throws Exception {
		Connection con = DBConnection.connection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT COUNT( AS AnzahlAllerNutzerEinerUnterhaltung "
							+ "FROM unterhaltungslisten WHERE unterhaltungID="
							+ unterhaltung.getId());

			return rs.getInt("AnzahlAllerNutzerEinerUnterhaltung");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Datenbank fehler!" + e.toString());
		}
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