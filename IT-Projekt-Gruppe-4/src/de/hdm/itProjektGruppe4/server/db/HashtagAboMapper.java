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
	 * @throws Exception
	 */
	public Hashtagabonnement insert(Hashtagabonnement hashtagabonnement) throws Exception {
		// DB-Verbindung herstellen
		Connection con = DBConnection.connection();
		PreparedStatement preStmt = null;

		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy/mm/dd HH:mm:ss");
			Date date = new Date();
			String sql = "INSERT INTO `hashtagabonnements`(`hashtagAboID`, `datum`, `hashtagID`, `abonnementID`, `nutzerID`) "
					+ "VALUES (NULL, ?, ?, ?, ?)";

			//PreparedStatement preStmt;
			preStmt = con.prepareStatement(sql);
			preStmt.setString(1, dateFormat.format(date));
			preStmt.setInt(2, hashtagabonnement.getHashtagID());
			preStmt.setInt(3, 1); //hashtagabonnement.getAbonnementID());
			preStmt.setInt(4, hashtagabonnement.getNutzerID());
			System.out.println(sql + " " + dateFormat.format(date) + " " + hashtagabonnement.getHashtagID() + " " + hashtagabonnement.getNutzerID());
			preStmt.executeUpdate();

			// con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Datenbank fehler!" + e.toString());
		}finally {
			DBConnection.closeAll(null, preStmt, con);
		}
		return hashtagabonnement;
	}

	/**
	 * Diese Methode ermöglicht das Löschen eines Hashtagabonnements und dessen
	 * Referenzen zu anderen Klassen
	 * 
	 * @param hashtagabonnement
	 * @throws Exception
	 */
	public void delete(Hashtagabonnement hashtagabonnement) throws Exception {
		Connection con = DBConnection.connection();
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM hashtagabonnements " + "WHERE hashtagAboID=" + hashtagabonnement.getId());

		} catch (SQLException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Datenbank fehler!" + e.toString());
		}finally {
			DBConnection.closeAll(null, stmt, con);
		}
	}

	
	/**
	 * Diese Methode ermöglicht es alle Hashtags zu finden
	 * 
	 * @return alleHashtagabonnements
	 * @throws Exception
	 */
	public ArrayList<Hashtagabonnement> findAllHashtagabonnements(String userID) 
			throws Exception {
		Connection con = DBConnection.connection();
		Statement stmt= null;
		ResultSet rs = null;
		ArrayList<Hashtagabonnement> alleHashtagabonnements = new ArrayList<Hashtagabonnement>();
		try {
			 stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM hashtagabonnements INNER JOIN hashtags ON hashtagabonnements.hashtagID = hashtags.hashtagID WHERE hashtagabonnements.nutzerID = " + userID);

			while (rs.next()) {
				Hashtag bez = new Hashtag();
				bez.setBezeichnung(rs.getString("bezeichnung"));
				Hashtagabonnement hashtagAbonnement = new Hashtagabonnement();
				hashtagAbonnement.setId(rs.getInt("hashtagAboID"));
				hashtagAbonnement.setErstellungsZeitpunkt(rs.getString("datum"));
				hashtagAbonnement.setHashtagBezeichnung(bez);
				hashtagAbonnement.setAbonnementID(rs.getInt("abonnementID"));
				hashtagAbonnement.setNutzerID(rs.getInt("nutzerID"));
				hashtagAbonnement.setHashtagID(rs.getInt("hashtagID"));

				
				alleHashtagabonnements.add(hashtagAbonnement);
			}
//			stmt.close();
//			rs.close();
			//con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Datenbank fehler!" + e.toString());
		}finally {
			DBConnection.closeAll(rs, stmt, con );
		}
		return alleHashtagabonnements;

	}

	/**
	 * Diese Methode ermöglicht es eine Ausgabe über einen Hashtagabonnements in
	 * der Datenbank, anhand deren ID.
	 * 
	 * @param id
	 * @return hashtagAbonnement
	 * @throws Exception
	 */
	public Hashtagabonnement findHashtagAbonnementByID(int id) 
			throws Exception {
		// DB-Verbindung herstellen
		Connection con = DBConnection.connection();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			// Insert-Statement erzeugen
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM hashtagabonnements " + "WHERE hashtagAboID=" + id);

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
		}finally {
			DBConnection.closeAll(rs, stmt, con);
		}
		return null;
	}

	/**
	 * Diese Methode ermöglicht es eine Ausgabe über einen Hashtagabonnements in
	 * der Datenbank, anhand deren ID.
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Hashtagabonnement findHashtagAbonnementByHashtagID(int id) throws Exception {

		// DB-Verbindung herstellen
		Connection con = DBConnection.connection();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			// Insert-Statement erzeugen
			stmt = con.createStatement();

			/*
			 * Zunächst wird geschaut welches der momentan höchste
			 * Primärschlüssel ist
			 */
			rs = stmt.executeQuery("SELECT * FROM hashtagabonnements INNER JOIN hashtags "
					+ "ON hashtagabonnements.hashtagID = hashtags.hashtagID "
					+ "WHERE hashtagabonnements.hashtagID = "+ id );

			System.out.println(rs.getStatement());
			System.out.println(id);
			// Wenn ein Datensatz gefunden wurde, wird auf diesen zugegriffen
			if (rs.next()) {
				Hashtag bezeichnung = new Hashtag();
				bezeichnung.setBezeichnung(rs.getString("bezeichnung"));
	
				Hashtagabonnement hashtagAbonnement = new Hashtagabonnement();
				hashtagAbonnement.setId(rs.getInt("hashtagAboID"));
				hashtagAbonnement.setErstellungsZeitpunkt(rs.getString("datum"));
				hashtagAbonnement.setHashtagBezeichnung(bezeichnung);
				hashtagAbonnement.setAbonnementID(rs.getInt("abonnementID"));
				hashtagAbonnement.setNutzerID(rs.getInt("nutzerID"));

				return hashtagAbonnement;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Datenbank fehler!" + e.toString());
		}finally {
			DBConnection.closeAll(rs, stmt, con);
		}
		return null;
	}

	/**
	 * Diese Methode ermöglicht es eine Ausgabe über einen Hashtagabonnements in
	 * der Datenbank, anhand deren ID.
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Hashtagabonnement> findAlleHashtagAbonnementByHashtagID(int id) throws Exception {

		// DB-Verbindung herstellen
		Connection con = DBConnection.connection();
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Hashtagabonnement> hsa = new ArrayList<Hashtagabonnement>();
		try {
			// Insert-Statement erzeugen
			stmt = con.createStatement();

			/*
			 * Zunächst wird geschaut welches der momentan höchste
			 * Primärschlüssel ist
			 */
			rs = stmt.executeQuery("SELECT * FROM hashtagabonnements INNER JOIN hashtags "
					+ "ON hashtagabonnements.hashtagID = hashtags.hashtagID "
					+ "WHERE hashtagabonnements.hashtagID = "+ id );

			System.out.println(rs.getStatement());
			System.out.println(id);
			// Wenn ein Datensatz gefunden wurde, wird auf diesen zugegriffen
			while (rs.next()) {
				Hashtag bezeichnung = new Hashtag();
				bezeichnung.setBezeichnung(rs.getString("bezeichnung"));
	
				Hashtagabonnement hashtagAbonnement = new Hashtagabonnement();
				hashtagAbonnement.setId(rs.getInt("hashtagAboID"));
				hashtagAbonnement.setErstellungsZeitpunkt(rs.getString("datum"));
				hashtagAbonnement.setHashtagBezeichnung(bezeichnung);
				hashtagAbonnement.setAbonnementID(rs.getInt("abonnementID"));
				hashtagAbonnement.setNutzerID(rs.getInt("nutzerID"));

				hsa.add(hashtagAbonnement);
				
			}
			
			return hsa;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Datenbank fehler!" + e.toString());
		}finally {
			DBConnection.closeAll(rs, stmt, con);
		}
		//return hsa;
	}
	
	/**
	 * Diese Methode ermöglicht es eine Ausgabe über einen Hashtagabonnements in
	 * der Datenbank, anhand deren ID.
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */

	public Hashtagabonnement findHashtagAbonnementByAbonnementID(int id) throws Exception {

		// DB-Verbindung herstellen
		Connection con = DBConnection.connection();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			// Insert-Statement erzeugen
			stmt = con.createStatement();

			/*
			 * Zunächst wird geschaut welches der momentan höchste
			 * Primärschlüssel ist
			 */
			rs = stmt.executeQuery("SELECT * FROM hashtagabonnements " + "WHERE abonnementID=" + id);

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
		}finally {
			DBConnection.closeAll(rs, stmt, con);
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

	public ArrayList<Hashtagabonnement> findHashtagAbonnementByNutzerID(int id) throws Exception {
		Connection con = DBConnection.connection();
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Hashtagabonnement> hashtagAboListe = new ArrayList<Hashtagabonnement>();

		try {

			stmt = con.createStatement();

			rs = stmt.executeQuery("SELECT * FROM hashtagabonnements " + "WHERE nutzerID=" + id);

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
		}finally {
			DBConnection.closeAll(rs, stmt, con);
		}

		return hashtagAboListe;
	}
	
	/**
	 * Diese Methode ermöglicht eine Ausgabe über die Hashtagabonnements eines
	 * Nutzers, in einer Liste.
	 * 
	 * @param id
	 * @return hashtagAboListe
	 */

	public Hashtagabonnement findHashtagAbonnementByNutzerIDHashtagID(int id, int id2) throws Exception {
		Connection con = DBConnection.connection();
		Statement stmt = null;
		ResultSet rs = null;
		//ArrayList<Hashtagabonnement> hashtagAboListe = new ArrayList<Hashtagabonnement>();

		try {

			stmt = con.createStatement();

			rs = stmt.executeQuery("SELECT * FROM hashtagabonnements WHERE nutzerID=" + id + " AND hashtagID= " + id2);

			if (rs.next()) {
				Hashtagabonnement hashtagAbonnement = new Hashtagabonnement();
				hashtagAbonnement.setId(rs.getInt("hashtagAboID"));
				hashtagAbonnement.setErstellungsZeitpunkt(rs.getString("datum"));
				hashtagAbonnement.setHashtagID(rs.getInt("hashtagID"));
				hashtagAbonnement.setNutzerID(rs.getInt("nutzerID"));
				hashtagAbonnement.setAbonnementID(rs.getInt("abonnementID"));
				
				return hashtagAbonnement;
			}

		}
		catch (SQLException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Datenbank fehler!" + e.toString());
		}finally {
			DBConnection.closeAll(rs, stmt, con);
		}

		return null;
	}

}

