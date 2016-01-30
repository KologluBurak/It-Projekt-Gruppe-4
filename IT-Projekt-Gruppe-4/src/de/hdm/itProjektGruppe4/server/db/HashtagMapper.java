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
	 * @return hashtag
	 * @throws Exception
	 */
	public Hashtag insert(Hashtag hashtag) 
			throws Exception {
		// DB-Verbindung herstellen
		Connection con = DBConnection.connection();
		PreparedStatement preStmt = null;

		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy/mm/dd HH:mm:ss");
			Date date = new Date();
			String sql= "INSERT INTO `hashtags`(`hashtagID`, `bezeichnung`, `datum`) VALUES (NULL, ?, ?)";

			preStmt = con.prepareStatement(sql);
			preStmt.setString(1, hashtag.getBezeichnung());
			preStmt.setString(2, dateFormat.format(date));
			preStmt.executeUpdate();

			// con.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Datenbank fehler!"
					+ e.toString());
		}finally {
			DBConnection.closeAll(null, preStmt, con);
	}
		return hashtag;
	}

	/**
	 * Diese Methode ermöglicht das Löschen eines Hashtags
	 * 
	 * @param hashtag
	 * @throws Exception
	 */
	public void delete(Hashtag hashtag) 
			throws Exception {
		Connection con = DBConnection.connection();
		Statement stmt = null;

		try {
			stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM hashtags " + "WHERE hashtagID="+ hashtag.getId());

		} catch (SQLException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Datenbank fehler!"
					+ e.toString());
		}finally {
			DBConnection.closeAll(null, stmt, con);
	}
	}

	/**
	 * Diese Methode ermöglicht es alle Hashtags aus der datenbank in einer Liste
	 * auszugeben.
	 * 
	 * @return allHashtags
	 * @throws Exception
	 */
	public ArrayList<Hashtag> findAllHashtags()
			throws Exception {
		Connection con = DBConnection.connection();
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Hashtag> allHashtags = new ArrayList<Hashtag>();

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM hashtags");

			while (rs.next()) {
				Hashtag hashtag = new Hashtag();
				hashtag.setId(rs.getInt("hashtagID"));
				hashtag.setBezeichnung(rs.getString("bezeichnung"));
				hashtag.setErstellungsZeitpunkt(rs.getString("datum"));

				allHashtags.add(hashtag);
			}
			//stmt.close();
			//rs.close();
			// con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Datenbank fehler!"
					+ e.toString());
		}finally {
			DBConnection.closeAll(rs, stmt, con);
	}
		return allHashtags;
	}

	/**
	 * Diese Methode ermöglicht das Ausgeben der Hashtag anhand deren ID aus der
	 * Datenbank.
	 * 
	 * @param id
	 * @return hashtag
	 * @throws Exception
	 */
	public Hashtag findHashtagByID(int id) 
			throws Exception {
		// DB-Verbindung herstellen
		Connection con = DBConnection.connection();
		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM hashtags "
					+ "WHERE hashtagID=" + id);

			// Wenn ein Datensatz gefunden wurde, wird auf diesen zugegriffen
			if (rs.next()) {
				Hashtag hashtag = new Hashtag();
				hashtag.setId(rs.getInt("hashtagID"));
				hashtag.setBezeichnung(rs.getString("bezeichnung"));
				hashtag.setErstellungsZeitpunkt(rs.getString("datum"));
				return hashtag;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Datenbank fehler!"+ e.toString());
		}finally {
			DBConnection.closeAll(rs, stmt, con);
	}
		return null;
	}

	/**
	 * Auslesen eines Hashtags durch den Text
	 * @param text
	 * @return hashtag
	 * @throws Exception
	 */
	public Hashtag findHashtagByText(String text) 
			throws Exception {
		// DB-Verbindung herstellen
		Connection con = DBConnection.connection();
		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM hashtags "
					+ "WHERE bezeichnung= '" + text + "'");

			//System.out.println(rs.getStatement());
			System.out.println("Text von findHahtagByText " +text);
			// Wenn ein Datensatz gefunden wurde, wird auf diesen zugegriffen
			if (rs.next()) {
				Hashtag hashtag = new Hashtag();
				hashtag.setId(rs.getInt("hashtagID"));
				hashtag.setBezeichnung(rs.getString("bezeichnung"));
				hashtag.setErstellungsZeitpunkt(rs.getString("datum"));
				return hashtag;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Datenbank fehler!"+ e.toString());
		}finally {
			DBConnection.closeAll(rs, stmt, con);
	}
		return null;
	}
	
	/**
	 * Auslesen der letzten ID in der Tabelle Hashtag
	 * 
	 * @return u
	 * @throws Exception
	 */
	public Hashtag getMaxID() throws Exception{
		Connection con = DBConnection.connection();
		Statement stmt = null; 
		Hashtag h = new Hashtag();
		System.out.println("Beginn getMaxID");
		try{
			String sql = "SELECT MAX(`hashtagID`) AS hashtagID FROM `hashtags`";
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			if(rs.next()){
				
				h.setId(rs.getInt("hashtagID"));
				System.out.println("getMax ID in If in hashtagMapper: "+rs); 
				//return u;
			}
			System.out.println("getMax ID nach IF in hashtagMapper: "+rs); 
		}catch (Exception e){
			e.printStackTrace();
			System.out.println("getMaxID " + e.toString());

			throw new IllegalArgumentException("Datenbank fehler!"
					+ e.toString());
		}finally{
			DBConnection.closeAll(null, stmt, con );
		}
		
		return h;
	}
}