package de.hdm.itProjektGruppe4.server.db;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import de.hdm.itProjektGruppe4.shared.bo.*;

/**
 * Mapper-Klasse, die <code>Unterhaltung</code>-Objekte auf eine relationale
 * Datenbank abbildet. Hierzu wird eine Reihe von Methoden zur Verf�gung
 * gestellt, mit deren Hilfe z.B. Objekte gesucht, erzeugt, modifiziert und
 * gel�scht werden k�nnen. Das Mapping ist bidirektional. D.h., Objekte k�nnen
 * in DB-Strukturen und DB-Strukturen in Objekte umgewandelt werden.
 * 
 * @author Thies
 * @author Kologlu
 * @author Oikonomou
 * @author Yücel
 */
public class UnterhaltungMapper {

	/**
	 * Die Klasse UnterhaltungMapper wird nur einmal instantiiert. Man spricht
	 * hierbei von einem sogenannten <b>Singleton</b>.
	 * <p>
	 * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal
	 * f�r s�mtliche eventuellen Instanzen dieser Klasse vorhanden. Sie f�r
	 * s�mtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert
	 * die einzige Instanz dieser Klasse.
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
	 * @param unterhaltung
	 *            das zu speichernde Objekt
	 * @return das bereits �bergebene Objekt, jedoch mit ggf. korrigierter
	 *         <code>id</code>.
	 */
	public Unterhaltung insert(Unterhaltung unterhaltung)
			throws IllegalArgumentException {
		// DB-Verbindung herstellen
		Connection con = DBConnection.connection();
		try {
			//Überlegen ob auch andere Attribute dazugenommen werden kann?!
			String sql = "INSERT INTO `unterhaltungen`(`unterhaltungID`) VALUES (NULL, ?)";

			PreparedStatement preStmt;
			preStmt = con.prepareStatement(sql);

			preStmt.executeUpdate();
			preStmt.close();

			// con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Datenbank fehler!"
					+ e.toString());
		}
		return unterhaltung;
	}

	/**
	 * Löschen der Daten eines <code>Unterhaltung</code>-Objekts aus der
	 * Datenbank.
	 * 
	 * @param id das aus der DB zu l�schende "Objekt"
	 */
	public void delete(Unterhaltung unterhaltung)
			throws IllegalArgumentException {
		
		Connection con = DBConnection.connection();
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM unterhaltungen WHERE unterhaltungID="+ unterhaltung.getId());
			stmt.close();
			// con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Datenbank fehler!"
					+ e.toString());
		}
	}

	/**
	 * Diese Methode ermöglicht es alle Unterhaltungen mit all ihren beinhalteten Nachrichhten aus der Datenbank in
	 * einer Liste auszugeben.
	 * 
	 * @return
	 */
	public ArrayList<Unterhaltung> findAllUnterhaltungen() {
		Connection con = DBConnection.connection();
		ArrayList<Unterhaltung> allUnterhaltungen = new ArrayList<Unterhaltung>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT unterhaltungen.unterhaltungID, text, datum FROM unterhaltungen INNER JOIN nachrichten "
						+ "ON nachrichten.unterhaltungID=unterhaltungen.unterhaltungID ");

			while (rs.next()) {
				Unterhaltung unterhaltung = new Unterhaltung();
				unterhaltung.setId(rs.getInt("unterhaltungen.unterhaltungID"));

				allUnterhaltungen.add(unterhaltung);
			}
			stmt.close();
			rs.close();
			// con.close();

			return allUnterhaltungen;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Diese Methode ermöglicht es alle Nachrichten einer Unterhaltung anhand
	 * ihrer ID zu finden und anzuzeigen.
	 * 
	 * @param unterhaltung
	 * @return result
	 * @throws IllegalArgumentException
	 */
	public ArrayList<Nachricht> findNachrichtenByUnterhaltung(Unterhaltung unterhaltung)
			throws IllegalArgumentException {

		Connection con = DBConnection.connection();
		ArrayList<Nachricht> result = new ArrayList<Nachricht>();
		try {

			//Neu
			Statement stmt= con.createStatement();
			ResultSet rs= stmt.executeQuery("SELECT unterhaltungen.unterhaltungID, text, nachrichten.datum "
					+ "FROM unterhaltungen INNER JOIN nachrichten ON nachrichten.unterhaltungID=unterhaltungen.unterhaltungID "
					+ "WHERE unterhaltungen.unterhaltungID= "+ unterhaltung.getId());

			Nachricht nachricht = new Nachricht();
			while (rs.next()) {
				nachricht.setUnterhaltungID(rs.getInt("nachrichten.unterhaltungID"));
				nachricht.setId(rs.getInt("nachrichtID"));
				nachricht.setText(rs.getString("text"));
				nachricht.setErstellungsZeitpunkt(rs.getString("nachrichten.datum"));

				result.add(nachricht);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Datenbank fehler!"
					+ e.toString());
		}
		return result;
	}
}
