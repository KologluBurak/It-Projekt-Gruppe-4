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
 * Mapper-Klasse, die <code>Hashtag</code>-Objekte auf eine relationale
 * Datenbank abbildet. Hierzu wird eine Reihe von Methoden zur Verfügung
 * gestellt, mit deren Hilfe z.B. Objekte gesucht, erzeugt, modifiziert und
 * geloescht werden können. Das Mapping ist bidirektional. D.h., Objekte können
 * in DB-Strukturen und DB-Strukturen in Objekte umgewandelt werden.
 * 
 * 
 * @author Thies
 * @author Kologlu
 * @author Oikonomou
 * @author Yücel
 */

public class HashtagMapper {

	/**
	 * Die Klasse HashtagMapper wird nur einmal instantiiert. Man spricht
	 * hierbei von einem sogenannten <b>Singleton</b>.
	 * <p>
	 * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal
	 * für sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie
	 * speichert die einzige Instanz dieser Klasse.
	 * 
	 * @see HashtagMapper()
	 */

	private static HashtagMapper hashtagMapper = null;

	/**
	 * Geschützter Konstruktor - verhindert die Moeglichkeit, mit
	 * <code>new</code> neue Instanzen dieser Klasse zu erzeugen.
	 */

	protected HashtagMapper() {
	}

	/**
	 * Diese statische Methode kann aufgrufen werden durch
	 * <code>HashtagMapper.hashtagMapper()</code>. Sie stellt die
	 * Singleton-Eigenschaft sicher, indem Sie dafür sorgt, dass nur eine
	 * einzige Instanz von <code>HashtagMapper</code> existiert.
	 * <p>
	 * 
	 * <b>Fazit:</b> HashtagMapper sollte nicht mittels <code>new</code>
	 * instantiiert werden, sondern stets durch Aufruf dieser statischen
	 * Methode.
	 * 
	 * @return DAS <code>HashtagMapper</code>-Objekt.
	 * @see hashtagMapper
	 */

	public static HashtagMapper hashtagMapper() {
		if (hashtagMapper == null) {
			hashtagMapper = new HashtagMapper();
		}
		return hashtagMapper;
	}

	/**
	 * Diese Methode ermöglicht es einen Hashtag in der Datenbank anzulegen.
	 * 
	 * @param hashtag
	 * @return
	 * @throws IllegalArgumentException
	 */
	public Hashtag insert(Hashtag hashtag) throws IllegalArgumentException {
		// DB-Verbindung herstellen
		Connection con = DBConnection.connection();

		try {
			// Statement stmt = con.createStatement();
			// ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
			// + "FROM nachricht ");

			DateFormat dateFormat = new SimpleDateFormat("yyyy/mm/dd HH:mm:ss");
			Date date = new Date();
			String sql= "INSERT INTO `hashtags`(`hashtagID`, `bezeichnung`, `datum`) VALUES (NULL, ?, ?)";

			System.out.println(sql);
			
			PreparedStatement preStmt;
			preStmt = con.prepareStatement(sql);
			preStmt.setString(1, dateFormat.format(date));//nachricht.getErstellungsZeitpunkt().toString());
			preStmt.setString(2, hashtag.getBezeichnung());
			preStmt.executeUpdate();

			// }
			// stmt.close();
			// rs.close();
			// con.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Datenbank fehler!"
					+ e.toString());
		}
		return hashtag;
	}

	/**
	 * Diese Methode ermöglicht das Löschen eines Nutzer und dessen Referenzen
	 * zu anderen Klassen.
	 * 
	 * @param hashtag
	 * @throws IllegalArgumentException
	 */
	public void delete(Hashtag hashtag) throws IllegalArgumentException {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM hashtag " + "WHERE hashtagID="
					+ hashtag.getId());

		} catch (SQLException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Datenbank fehler!"
					+ e.toString());
		}
	}

	/**
	 * Diese Methode ermöglicht es alle Hashtags aus der datenbank in einer Liste
	 * auszugeben.
	 * 
	 * @return
	 * @throws IllegalArgumentException
	 */
	public ArrayList<Hashtag> findAllHashtags()
			throws IllegalArgumentException {
		Connection con = DBConnection.connection();
		ArrayList<Hashtag> allHashtags = new ArrayList<Hashtag>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt
					.executeQuery("SELECT (hashtagID, bezeichnung) FROM hashtag");

			while (rs.next()) {
				Hashtag hashtag = new Hashtag();
				hashtag.setId(rs.getInt("hashtagID"));
				hashtag.setBezeichnung(rs.getString("bezeichnung"));

				allHashtags.add(hashtag);
			}
			stmt.close();
			rs.close();
			// con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Datenbank fehler!"
					+ e.toString());
		}
		return allHashtags;
	}

	/**
	 * Diese Methode ermöglicht das Ausgeben der Hashtag anhand deren ID aus der
	 * Datenbank.
	 * 
	 * @param id
	 * @return
	 * @throws IllegalArgumentException
	 */
	public Hashtag findHashtagByID(int id) throws IllegalArgumentException {
		// DB-Verbindung herstellen
		Connection con = DBConnection.connection();

		try {
			// Insert-Statement erzeugen
			Statement stmt = con.createStatement();
			// Zunaechst wird geschaut welches der momentan hoechste
			// Primaerschluessel ist
			ResultSet rs = stmt.executeQuery("SELECT hashtagID, bezeichnung FROM hashtag "
					+ "WHERE hashtagID=" + id);

			// Wenn ein Datensatz gefunden wurde, wird auf diesen zugegriffen
			if (rs.next()) {
				Hashtag hashtag = new Hashtag();
				hashtag.setId(rs.getInt("hashtagID"));
				hashtag.setBezeichnung(rs.getString("Bezeichnung"));
				return hashtag;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Datenbank fehler!"
					+ e.toString());

		}
		return null;
	}
}