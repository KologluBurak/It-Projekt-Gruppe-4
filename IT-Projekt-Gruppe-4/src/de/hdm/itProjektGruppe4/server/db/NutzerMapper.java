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
 * Mapper-Klasse, die <code>Nutzer</code>-Objekte auf eine relationale Datenbank
 * abbildet. Hierzu wird eine Reihe von Methoden zur Verfügung gestellt, mit
 * deren Hilfe z.B. Objekte gesucht, erzeugt, modifiziert und gelöscht werden
 * können. Das Mapping ist bidirektional. D.h., Objekte können in DB-Strukturen
 * und DB-Strukturen in Objekte umgewandelt werden.
 * 
 * 
 * @author Thies
 * @author Kologlu
 * @author Oikonomou
 * 
 */
public class NutzerMapper {

	/**
	 * Die Klasse NutzerMapper wird nur einmal instantiiert. Man spricht hierbei
	 * von einem sogenannten <b>Singleton</b>.
	 * <p>
	 * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal
	 * für sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie
	 * speichert die einzige Instanz dieser Klasse.
	 * 
	 * @see NutzerMapper()
	 */
	private static NutzerMapper nutzerMapper = null;

	/**
	 * Geschützter Konstruktor - verhindert die Möglichkeit, mit
	 * <code>new</code> neue Instanzen dieser Klasse zu erzeugen.
	 */
	protected NutzerMapper() {

	}

	/**
	 * Diese statische Methode kann aufgrufen werden durch
	 * <code>NutzerMapper.nutzerMapper()</code>. Sie stellt die
	 * Singleton-Eigenschaft sicher, indem Sie dafür sorgt, dass nur eine
	 * einzige Instanz von <code>NutzerMapper</code> existiert.
	 * <p>
	 * 
	 * <b>Fazit:</b> NutzerMapper sollte nicht mittels <code>new</code>
	 * instantiiert werden, sondern stets durch Aufruf dieser statischen
	 * Methode.
	 * 
	 * @return DAS <code>NutzerMapper</code>-Objekt.
	 * @see nutzerMapper
	 */
	public static NutzerMapper nutzerMapper() {
		if (nutzerMapper == null) {
			nutzerMapper = new NutzerMapper();
		}
		return nutzerMapper;
	}

	/**
	 * Einfügen eines <code>Nutzer</code>-Objekts in die Datenbank. Dabei wird
	 * auch der Primärschlüssel des übergebenen Objekts geprüft und ggf.
	 * berichtigt.
	 * 
	 * @param a
	 *            das zu speichernde Objekt
	 * @return das bereits übergebene Objekt, jedoch mit ggf. korrigierter
	 *         <code>id</code>.
	 * @throws Exception        
	 */
	public Nutzer insert(Nutzer nutzer)
			throws Exception {
		// DB-Verbindung herstellen
		Connection con = DBConnection.connection();
		PreparedStatement preStmt=null;
		try {
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Date date = new Date();
			nutzer.setErstellungsZeitpunkt(dateFormat.format(date).toString());

			String sql = "INSERT INTO `nutzer`(`nutzerID`, `vorname`, `nachname`, `email`, `nickname`, `datum`) "
					+ "VALUES (NULL, ?, ?, ?, ?, ?)";

//			PreparedStatement preStmt;
			preStmt = con.prepareStatement(sql);
			preStmt.setString(1, nutzer.getVorname());
			preStmt.setString(2, nutzer.getNachname());
			preStmt.setString(3, nutzer.getEmail());
			preStmt.setString(4, nutzer.getNickname());
			preStmt.setString(5, dateFormat.format(date));
			preStmt.executeUpdate();
			//preStmt.close();

			// con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Datenbank fehler!"
					+ e.toString());
		}finally {
			DBConnection.closeAll(null, preStmt, con);
		}
		return nutzer;
	}

	/**
	 * Wiederholtes Schreiben eines bereits verfügbaren Objekts in die Datenbank.
	 * 
	 * @param nutzer
	 * @return nutzer
	 * @throws Exception
	 */
	public Nutzer update(Nutzer nutzer) throws Exception {
		Connection con = DBConnection.connection();
		PreparedStatement preStmt=null;
		try {
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Date date = new Date();
			nutzer.setErstellungsZeitpunkt(dateFormat.format(date).toString());
			
//			PreparedStatement preStmt;
			preStmt = con.prepareStatement("UPDATE nutzer SET vorname=?, "
							+ "nachname=?, email=?, nickname=?, datum=? WHERE nutzerID="+ nutzer.getId());
			
			preStmt.setString(1, nutzer.getVorname());
			preStmt.setString(2, nutzer.getNachname());
			preStmt.setString(3, nutzer.getEmail());
			preStmt.setString(4, nutzer.getNickname());
			preStmt.setString(5, dateFormat.format(date));
			preStmt.executeUpdate();
//			preStmt.close();
			
			// con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Datenbank fehler!"
					+ e.toString());
		}finally {
			DBConnection.closeAll(null, preStmt, con);
		}
		return nutzer;
	}

	/**
	 * Löschen der Daten eines <code>Unterhaltung</code>-Objekts aus der
	 * Datenbank.
	 * 
	 * @param nutzer
	 * @throws Exception
	 */
	public void delete(Nutzer nutzer) throws Exception {
		Connection con = DBConnection.connection();
		Statement stmt= null;
		try {
			stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM nutzer WHERE nutzerID="+ nutzer.getId());
			stmt.close();
			
			// con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Datenbank fehler!"+ e.toString());
		}finally {
			DBConnection.closeAll(null, stmt, con);
		}
	}

	/**
	 * Diese Methode ermöglicht es alle Nutzer aus der Datenbank in einer Liste
	 * zu finden und anzuzeigen.
	 * 
	 * @return nutzer
	 * @throws Exception
	 */
	public ArrayList<Nutzer> findAllNutzer() throws Exception {
		Connection con = DBConnection.connection();
		Statement stmt= null;
		ResultSet rs=null;
		ArrayList<Nutzer> alleNutzer = new ArrayList<Nutzer>();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM nutzer ORDER BY nutzerID");

			while (rs.next()) {
				Nutzer nutzer = new Nutzer();
				nutzer.setId(rs.getInt("nutzerID"));
				nutzer.setVorname(rs.getString("vorname"));
				nutzer.setNachname(rs.getString("nachname"));
				nutzer.setEmail(rs.getString("email"));
				nutzer.setNickname(rs.getString("nickname"));
				nutzer.setErstellungsZeitpunkt(rs.getString("datum"));

				alleNutzer.add(nutzer);
			}
//			stmt.close();
//			rs.close();
			// con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Datenbank fehler!"
					+ e.toString());
		}finally {
			DBConnection.closeAll(rs, stmt, con);
		}
		return alleNutzer;
	}

	/**
	 * Diese Methode ermöglicht einen Nutzer anhand seines Nachnamens zu finden
	 * und anzuzeigen.
	 * 
	 * @param nickname
	 * @return nutzer
	 * @throws Exception
	 */
	public Nutzer findNutzerByNickname(String nickname)
			throws Exception {
		Connection con = DBConnection.connection();
		Statement stmt= null;
		ResultSet rs=null;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM nutzer WHERE nickname='"
							+ nickname + "'");

			if (rs.next()) {
				Nutzer nutzer = new Nutzer();
				nutzer.setId(rs.getInt("nutzerID"));
				nutzer.setVorname(rs.getString("vorname"));
				nutzer.setNachname(rs.getString("nachname"));
				nutzer.setEmail(rs.getString("email"));
				nutzer.setNickname(rs.getString("nickname"));
				nutzer.setErstellungsZeitpunkt(rs.getString("datum"));

				//System.out.println(nutzer.getId());
				return nutzer;
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
	 * Diese Methode ermöglicht einen Nutzer anhand seiner Email zu finden
	 * und anzuzeigen.
	 * 
	 * @param email
	 * @return nutzer
	 * @throws Exception
	 */
	public Nutzer findNutzerByEmail(String email)
			throws Exception {
		Connection con = DBConnection.connection();
		Statement stmt= null;
		ResultSet rs= null;

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM nutzer WHERE email='"+ email + "'");

			if (rs.next()) {
				Nutzer nutzer = new Nutzer();
				nutzer.setId(rs.getInt("nutzerID"));
				nutzer.setVorname(rs.getString("vorname"));
				nutzer.setNachname(rs.getString("nachname"));
				nutzer.setEmail(rs.getString("email"));
				nutzer.setNickname(rs.getString("nickname"));
				nutzer.setErstellungsZeitpunkt(rs.getString("datum"));

				return nutzer;
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
	 * Diese Methode ermöglicht einen Nutzer anhand des Prim�rschl�ssels zu
	 * finden und anzuzeigen.
	 * 
	 * @param nutzerID
	 * @return nutzer
	 * @throws Exception
	 */
	public Nutzer findNutzerById(int nutzerID) throws Exception {
		Connection con = DBConnection.connection();
		Statement stmt= null;
		ResultSet rs= null;
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM nutzer WHERE nutzerID='"+nutzerID+"'");
			
			if(rs.next()){
				Nutzer nutzer=new Nutzer();
				nutzer.setId(rs.getInt("nutzerID"));
				nutzer.setVorname(rs.getString("vorname"));
				nutzer.setNachname(rs.getString("nachname"));
				nutzer.setEmail(rs.getString("email"));
				nutzer.setNickname(rs.getString("nickname"));
				nutzer.setErstellungsZeitpunkt(rs.getString("datum"));

				return nutzer;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Datenbank fehler!"+ e.toString());
		}finally {
			DBConnection.closeAll(rs, stmt, con);
		}
		return null;
	}
}
