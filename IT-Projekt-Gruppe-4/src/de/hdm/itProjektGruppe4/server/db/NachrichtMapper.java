package de.hdm.itProjektGruppe4.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.sql.PreparedStatement;

import de.hdm.itProjektGruppe4.shared.bo.*;

/**
 * Mapper-Klasse, die <code>Nachricht</code>-Objekte auf eine relationale
 * Datenbank abbildet. Hierzu wird eine Reihe von Methoden zur Verf�gung
 * gestellt, mit deren Hilfe z.B. Objekte gesucht, erzeugt, modifiziert und
 * gel�scht werden k�nnen. Das Mapping ist bidirektional. D.h., Objekte k�nnen
 * in DB-Strukturen und DB-Strukturen in Objekte umgewandelt werden.
 * 
 * 
 * @author Thies
 * @author Kologlu
 * @author Oikonomou
 * @author Yücel
 */
public class NachrichtMapper {

	/**
	 * Die Klasse NachrichtMapper wird nur einmal instantiiert. Man spricht
	 * hierbei von einem sogenannten <b>Singleton</b>.
	 * <p>
	 * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal
	 * f�r s�mtliche eventuellen Instanzen dieser Klasse vorhanden. Sie
	 * speichert die einzige Instanz dieser Klasse.
	 * 
	 * @see NachrichtMapper()
	 */
	private static NachrichtMapper nachrichtMapper = null;

	/**
	 * Geschützter Konstruktor - verhindert die M�glichkeit, mit
	 * <code>new</code> neue Instanzen dieser Klasse zu erzeugen.
	 */
	protected NachrichtMapper() {

	}

	/**
	 * Diese statische Methode kann aufgrufen werden durch
	 * <code>NachrichtMapper.nachrichtMapper()</code>. Sie stellt die
	 * Singleton-Eigenschaft sicher, indem Sie daf�r sorgt, dass nur eine
	 * einzige Instanz von <code>NachrichtMapper</code> existiert.
	 * <p>
	 * 
	 * <b>Fazit:</b> NachrichtMapper sollte nicht mittels <code>new</code>
	 * instantiiert werden, sondern stets durch Aufruf dieser statischen
	 * Methode.
	 * 
	 * @return DAS <code>NachrichtMapper</code>-Objekt.
	 * @see nachrichtMapper
	 */
	public static NachrichtMapper nachrichtMapper() {
		if (nachrichtMapper == null) {
			nachrichtMapper = new NachrichtMapper();
		}
		return nachrichtMapper;

	}

	/**
	 * Diese Methode ermöglicht es eine Nachricht in der Datenbank anzulegen.
	 * 
	 * @param nachricht
	 * @return
	 */
	public Nachricht insert(Nachricht nachricht)
			throws IllegalArgumentException {
		// DB-Verbindung herstellen
		Connection con = DBConnection.connection();

		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Date date = new Date();
			nachricht.setErstellungsZeitpunkt(dateFormat.format(date).toString());
			
			String sql= "INSERT INTO `nachrichten` (`nachrichtID`, `text`, `datum`, `unterhaltungID`, `nutzerID`) "
					+ "VALUES (NULL, ?, ?, ?, ?)";

			PreparedStatement preStmt;
			preStmt = con.prepareStatement(sql);
			preStmt.setString(1, nachricht.getText());
			preStmt.setString(2, dateFormat.format(date));
			preStmt.setInt(3, nachricht.getNutzerID());
			preStmt.setInt(4, nachricht.getUnterhaltungID());
			preStmt.executeUpdate();
			preStmt.close();
	
			// con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Datenbank fehler!"+ e.toString());
		}
		return nachricht;
	}

	/**
	 * Diese Methode ermöglicht das Löschen einer Nachricht
	 * 
	 * @param nachricht
	 */
	public void delete(Nachricht nachricht) throws IllegalArgumentException {
		Connection con = DBConnection.connection();
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM nachrichten "
					+ "WHERE nachrichtID=" + nachricht.getId());

		} catch (SQLException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Datenbank fehler!"+ e.toString());
		}
	}

//	/**
//	 * Diese Methode ermöglicht es alle Nachrichten eines Nutzers in einer
//	 * Liste auszugeben.
//	 * 
//	 * @return allNachrichten
//	 */
//	public ArrayList<Nachricht> findAllNachrichten(Nutzer nutzer)
//			throws IllegalArgumentException {
//		Connection con = DBConnection.connection();
//		ArrayList<Nachricht> allNachrichten = new ArrayList<Nachricht>();
//		try {
//			Statement stmt = con.createStatement();
//			ResultSet rs = stmt.executeQuery("SELECT nachrichtID, nickname, text, nachrichten.datum FROM nutzer INNER JOIN nachrichten "
//					+ "ON nutzer.nutzerID = nachrichten.nutzerID WHERE nutzer.nutzerID =" +nutzer.getId());
//
//			while (rs.next()) {
//				Nutzer absender = new Nutzer();
//				absender.setNickname(rs.getString("nickname"));
//				
//				Nachricht nachricht = new Nachricht();
//				nachricht.setId(rs.getInt("nachrichtID"));
//				nachricht.setAbsender(absender);
//				nachricht.setText(rs.getString("text"));
//				nachricht.setErstellungsZeitpunkt(rs.getString("nachrichten.datum"));
//
//				allNachrichten.add(nachricht);
//			}
//			stmt.close();
//			rs.close();
//			//con.close();
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//			throw new IllegalArgumentException("Datenbank fehler!"
//					+ e.toString());
//		}
//		return allNachrichten;
//	}

	/**
	 * Diese Methode ermöglicht es einen Nutzer anhand seiner ID das Auszugeben.
	 * 
	 * @param id
	 * @return nachricht
	 * @throws IllegalArgumentException
	 */
	public Nachricht findNachrichtById(int id)
			throws IllegalArgumentException {
		Connection con = DBConnection.connection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT nachrichtID, text, datum FROM nachrichten "+ "WHERE nachrichtID=" + id);

			if (rs.next()) {
				Nachricht nachricht = new Nachricht();
				nachricht.setId(rs.getInt("nachrichtID"));
				nachricht.setText(rs.getString("text"));
				nachricht.setErstellungsZeitpunkt(rs.getString("datum"));
				
				return nachricht;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Datenbank fehler!"+ e.toString());
		}
		return null;
	}

	/**
	 * Diese Methode ermöglicht es alle Nachrichten eines Nutzers auszugeben.
	 * 
	 * @param nutzer
	 * @return nachrichtenJeNutzer
	 */
	public ArrayList<Nachricht> alleNachrichtenJeNutzer(Nutzer nutzer)
			throws IllegalArgumentException {
		Connection con = DBConnection.connection();
		ArrayList<Nachricht> nachrichtenJeNutzer = new ArrayList<Nachricht>();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT nickname, text, nachrichten.datum FROM nutzer INNER JOIN nachrichten "
					+ "ON nutzer.nutzerID = nachrichten.nutzerID WHERE nutzer.nutzerID=" +nutzer.getId());

			while (rs.next()) {
				
				Nutzer absender = new Nutzer();
				absender.setNickname(rs.getString("nickname"));
				
				Nachricht nachricht = new Nachricht();
				nachricht.setId(rs.getInt("nachrichtID"));
				nachricht.setAbsender(absender);
				nachricht.setText(rs.getString("text"));
				nachricht.setErstellungsZeitpunkt(rs.getString("nachrichten.datum"));

				nachrichtenJeNutzer.add(nachricht);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Datenbank fehler!"+ e.toString());
		}
		return nachrichtenJeNutzer;
	}

	/**
	 * Diese Methode ermöglicht es alle Nachrichten eines bestimmten Zeitraums zu übergeben auszugeben.
	 * 
	 * @param von
	 * @param bis
	 * @return nachrichtenJeZeitraum
	 */
	public ArrayList<Nachricht> alleNachrichtenJeZeitraum(String von, String bis)
			throws IllegalArgumentException {
		Connection con = DBConnection.connection();
		ArrayList<Nachricht> nachrichtenJeZeitraum = new ArrayList<Nachricht>();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT text, datum FROM nachrichten WHERE datum BETWEEN " + von + " AND " + bis + "");

			while (rs.next()) {
				Nachricht nachricht = new Nachricht();
				nachricht.setText(rs.getString("text"));
				nachricht.setErstellungsZeitpunkt(rs.getString("datum"));

				nachrichtenJeZeitraum.add(nachricht);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Datenbank fehler!"+ e.toString());
		}
		return nachrichtenJeZeitraum;
	}
}
