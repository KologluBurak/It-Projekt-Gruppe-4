package de.hdm.itProjektGruppe4.server.db;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.PreparedStatement;
import de.hdm.itProjektGruppe4.shared.bo.*;

/**
 * Mapper-Klasse, die <code>HashtagAbo</code>-Objekte auf eine relationale
 * Datenbank abbildet. Hierzu wird eine Reihe von Methoden zur Verfügung
 * gestellt, mit deren Hilfe z.B. Objekte gesucht, erzeugt, modifiziert und
 * gelöscht werden können. Das Mapping ist bidirektional. D.h., Objekte können
 * in DB-Strukturen und DB-Strukturen in Objekte umgewandelt werden.
 * 
 * 
 * @author Thies
 * @author Yücel
 * @author Oikonomou
 *
 */

public class HashtagAboMapper {

	/**
	 * Die Klasse HashtagAboMapper wird nur einmal instantiiert. Man spricht
	 * hierbei von einem sogenannten <b>Singleton</b>.
	 * 
	 * <p>
	 * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal
	 * für sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie
	 * speichert die einzige Instanz dieser Klasse.
	 * </p>
	 * 
	 * @see HashtagAbomapper()
	 */

	private static HashtagAboMapper hashtagAboMapper = null;

	/**
	 * Geschätzter Konstruktor - verhindert die Möglichkeit, mit
	 * <code>new</code> neue Instanzen dieser Klasse zu erzeugen.
	 */

	protected HashtagAboMapper() {

	}

	/**
	 * Diese statische Methode kann aufgrufen werden durch
	 * <code>HashtagAboMapper.hashtagAboMapper()</code>. Sie stellt die
	 * Singleton-Eigenschaft sicher, indem Sie dafür sorgt, dass nur eine
	 * einzige Instanz von <code>HashtagAboMapper</code> existiert.
	 * <p>
	 * 
	 * <b>Fazit:</b> HashtagAboMapper sollte nicht mittels <code>new</code>
	 * instantiiert werden, sondern stets durch Aufruf dieser statischen
	 * Methode.
	 * 
	 * @return DAS <code>HashtagAboMapper</code>-Objekt.
	 * @see HashtagAboMapper
	 */

	public static HashtagAboMapper hashtagAboMapper() {
		if (hashtagAboMapper == null) {
			hashtagAboMapper = new HashtagAboMapper();
		}
		return hashtagAboMapper;

	}

	/**
	 * Diese Methode ermöglicht es einen Hashtagabonnement in der Datenbank
	 * anzulegen.
	 * 
	 * @param hashtagabonnement
	 * @return hashtagabonnement
	 * @throws IllegalArgumentException
	 */
	public Hashtagabonnement insert(Hashtagabonnement hashtagabonnement) throws IllegalArgumentException {
		// DB-Verbindung herstellen
		Connection con = DBConnection.connection();

		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy/mm/dd HH:mm:ss");
			Date date = new Date();
			String sql = "INSERT INTO `hashtagabonnements`(`hashtagAboID`, `datum`, `hashtagID`, `abonnementID`, `nutzerID`) "
					+ "VALUES (NULL, ?, ?, ?, ?)";
			
			PreparedStatement preStmt;
			preStmt = con.prepareStatement(sql);
			preStmt.setString(1, dateFormat.format(date));
			preStmt.setInt(2, hashtagabonnement.getHashtagID());
			preStmt.setInt(3, hashtagabonnement.getAbonnementID());
			preStmt.setInt(4, hashtagabonnement.getNutzerID());
			preStmt.executeUpdate();

			// con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Datenbank fehler!" + e.toString());
		}
		return hashtagabonnement;
	}

	/**
	 * Diese Methode ermöglicht das Löschen eines Hashtagabonnements und dessen
	 * Referenzen zu anderen Klassen
	 * 
	 * @param hashtagabonnement
	 * @throws IllegalArgumentException
	 */
	public void delete(Hashtagabonnement hashtagabonnement) throws IllegalArgumentException {
		Connection con = DBConnection.connection();
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM hashtagabonnements " + "WHERE hashtagAboID=" + hashtagabonnement.getId());

		} catch (SQLException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Datenbank fehler!" + e.toString());
		}
	}

	/**
	 * Diese Methode ermöglicht es alle Hashtags zu finden
	 * 
	 * @return alleHashtagabonnements
	 * @throws IllegalArgumentException
	 */
	public ArrayList<Hashtagabonnement> findAllHashtagabonnements() 
			throws IllegalArgumentException {

		Connection con = DBConnection.connection();
		ArrayList<Hashtagabonnement> alleHashtagabonnements = new ArrayList<Hashtagabonnement>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM hashtagabonnements ORDER BY hashtagAboID");

			while (rs.next()) {
				Hashtagabonnement hashtagAbonnement = new Hashtagabonnement();
				hashtagAbonnement.setId(rs.getInt("hashtagAboID"));
				hashtagAbonnement.setErstellungsZeitpunkt(rs.getString("datum"));

				hashtagAbonnement.setAbonnementID(rs.getInt("abonnementID"));
				hashtagAbonnement.setNutzerID(rs.getInt("nutzerID"));
				hashtagAbonnement.setHashtagID(rs.getInt("hashtagID"));

				
				alleHashtagabonnements.add(hashtagAbonnement);
			}
			stmt.close();
			rs.close();
			// con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Datenbank fehler!" + e.toString());
		}
		return alleHashtagabonnements;

	}

	/**
	 * Diese Methode ermöglicht es eine Ausgabe über einen Hashtagabonnements in
	 * der Datenbank, anhand deren ID.
	 * 
	 * @param id
	 * @return hashtagAbonnement
	 * @throws IllegalArgumentException
	 */
	public Hashtagabonnement findHashtagAbonnementByID(int id) 
			throws IllegalArgumentException {
		// DB-Verbindung herstellen
		Connection con = DBConnection.connection();
		try {
			// Insert-Statement erzeugen
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM hashtagabonnements " + "WHERE hashtagAboID=" + id);

			// Wenn ein Datensatz gefunden wurde, wird auf diesen zugegriffen
			if (rs.next()) {
				Hashtagabonnement hashtagAbonnement = new Hashtagabonnement();
				hashtagAbonnement.setId(rs.getInt("hashtagAboID"));
				hashtagAbonnement.setErstellungsZeitpunkt(rs.getString("datum"));
				hashtagAbonnement.setHashtagID(rs.getInt("hashtagID"));
				hashtagAbonnement.setAbonnementID(rs.getInt("abonnementID"));
				hashtagAbonnement.setNutzerID(rs.getInt("nutzerID"));

				return hashtagAbonnement;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Datenbank fehler!" + e.toString());

		}
		return null;
	}

	/**
	 * Diese Methode ermöglicht es eine Ausgabe über einen Hashtagabonnements in
	 * der Datenbank, anhand deren ID.
	 * 
	 * @param id
	 * @return
	 * @throws IllegalArgumentException
	 */
	public Hashtagabonnement findHashtagAbonnementByHashtagID(int id) throws IllegalArgumentException {

		// DB-Verbindung herstellen
		Connection con = DBConnection.connection();

		try {
			// Insert-Statement erzeugen
			Statement stmt = con.createStatement();

			/*
			 * Zunächst wird geschaut welches der momentan höchste
			 * Primärschlüssel ist
			 */
			ResultSet rs = stmt.executeQuery("SELECT * FROM hashtagabonnements " + "WHERE hashtagID=" + id);

			// Wenn ein Datensatz gefunden wurde, wird auf diesen zugegriffen
			if (rs.next()) {
				Hashtagabonnement hashtagAbonnement = new Hashtagabonnement();
				hashtagAbonnement.setId(rs.getInt("hashtagAboID"));
				hashtagAbonnement.setErstellungsZeitpunkt(rs.getString("datum"));
				hashtagAbonnement.setHashtagID(rs.getInt("hashtagID"));
				hashtagAbonnement.setAbonnementID(rs.getInt("abonnementID"));
				hashtagAbonnement.setNutzerID(rs.getInt("nutzerID"));

				return hashtagAbonnement;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Datenbank fehler!" + e.toString());

		}
		return null;
	}

	/**
	 * Diese Methode ermöglicht es eine Ausgabe über einen Hashtagabonnements in
	 * der Datenbank, anhand deren ID.
	 * 
	 * @param id
	 * @return
	 * @throws IllegalArgumentException
	 */

	public Hashtagabonnement findHashtagAbonnementByAbonnementID(int id) throws IllegalArgumentException {

		// DB-Verbindung herstellen
		Connection con = DBConnection.connection();

		try {
			// Insert-Statement erzeugen
			Statement stmt = con.createStatement();

			/*
			 * Zunächst wird geschaut welches der momentan höchste
			 * Primärschlüssel ist
			 */
			ResultSet rs = stmt.executeQuery("SELECT * FROM hashtagabonnements " + "WHERE abonnementID=" + id);

			// Wenn ein Datensatz gefunden wurde, wird auf diesen zugegriffen
			if (rs.next()) {
				Hashtagabonnement hashtagAbonnement = new Hashtagabonnement();
				hashtagAbonnement.setId(rs.getInt("hashtagAboID"));
				hashtagAbonnement.setErstellungsZeitpunkt(rs.getString("datum"));
				hashtagAbonnement.setHashtagID(rs.getInt("hashtagID"));
				hashtagAbonnement.setNutzerID(rs.getInt("nutzerID"));
				hashtagAbonnement.setAbonnementID(rs.getInt("abonnementID"));
				
				return hashtagAbonnement;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Datenbank fehler!" + e.toString());

		}
		return null;
	}

	/**
	 * Diese Methode ermöglicht eine Ausgabe über die Hashtagabonnements eines
	 * Nutzers, in einer Liste.
	 * 
	 * @param id
	 * @return hashtagAboListe
	 */

	public ArrayList<Hashtagabonnement> findHashtagAbonnementByNutzerID(int id) throws IllegalArgumentException {
		Connection con = DBConnection.connection();
		ArrayList<Hashtagabonnement> hashtagAboListe = new ArrayList<Hashtagabonnement>();

		try {

			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM hashtagabonnements " + "WHERE nutzerID=" + id);

			while (rs.next()) {
				Hashtagabonnement hashtagAbonnement = new Hashtagabonnement();
				hashtagAbonnement.setId(rs.getInt("hashtagAboID"));
				hashtagAbonnement.setErstellungsZeitpunkt(rs.getString("datum"));
				hashtagAbonnement.setHashtagID(rs.getInt("hashtagID"));
				hashtagAbonnement.setNutzerID(rs.getInt("nutzerID"));
				hashtagAbonnement.setAbonnementID(rs.getInt("abonnementID"));

				hashtagAboListe.add(hashtagAbonnement);
			}

		}
		catch (SQLException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Datenbank fehler!" + e.toString());
		}

		return hashtagAboListe;
	}
}