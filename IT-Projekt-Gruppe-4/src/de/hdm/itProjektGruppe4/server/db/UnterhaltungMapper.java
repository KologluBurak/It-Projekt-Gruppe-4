package de.hdm.itProjektGruppe4.server.db;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import de.hdm.itProjektGruppe4.shared.bo.*;

/**
 * Mapper-Klasse, die <code>Unterhaltung</code>-Objekte auf eine relationale
 * Datenbank abbildet. Hierzu wird eine Reihe von Methoden zur Verfügung
 * gestellt, mit deren Hilfe z.B. Objekte gesucht, erzeugt, modifiziert und
 * gel�scht werden können. Das Mapping ist bidirektional. D.h., Objekte können
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
	 * für sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie für
	 * sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert
	 * die einzige Instanz dieser Klasse.
	 * 
	 * @see UnterhaltungMapper()
	 */
	private static UnterhaltungMapper unterhaltungMapper = null;

	/**
	 * Geschützter Konstruktor - verhindert die M�glichkeit, mit
	 * <code>new</code> neue Instanzen dieser Klasse zu erzeugen.
	 */
	protected UnterhaltungMapper() {
	}

	/**
	 * Diese statische Methode kann aufgrufen werden durch
	 * <code>UnterhaltungMapper.unterhaltungMapper()</code>. Sie stellt die
	 * Singleton-Eigenschaft sicher, indem Sie dafür sorgt, dass nur eine
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
	 * Einfügen eines <code>Unterhaltung</code>-Objekts in die Datenbank. Dabei
	 * wird auch der Primärschlüssel des übergebenen Objekts geprüft und ggf.
	 * berichtigt.
	 * 
	 * @param unterhaltung
	 * @return das bereits übergebene Objekt, jedoch mit ggf. korrigierter
	 *         <code>id</code>.
	 */
	public Unterhaltung insert()
			throws Exception{
		// DB-Verbindung herstellen
		Connection con = DBConnection.connection();
		Statement stmt= null;
		Unterhaltung u = new Unterhaltung();
		System.out.println("Anfang von insert in UnterhaltungMapper");
		try {
			String sql = "INSERT INTO `unterhaltungen`(`unterhaltungID`) VALUES (NULL)";

			PreparedStatement preStmt;
			preStmt = con.prepareStatement(sql);
			System.out.println("Try UnterhaltungMapper Insert " + preStmt);
			preStmt.executeUpdate();
			preStmt.close();
			// con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Datenbank fehler!"
					+ e.toString());
		}finally {
			DBConnection.closeAll(null, stmt, con );
		}
		return null;
	}

	public Unterhaltung getMaxID() throws Exception{
		Connection con = DBConnection.connection();
		Statement stmt = null; 
		Unterhaltung u = new Unterhaltung();
		System.out.println("Beginn getMaxID");
		try{
			String sql = "SELECT MAX(`unterhaltungID`) AS unterhaltungID FROM `unterhaltungen`";
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			System.out.println("getMax ID in UnterhalungMapper: "+rs); 
			if(rs.next()){
				
				u.setId(rs.getInt("unterhaltungID"));
				System.out.println("getMax ID in If in UnterhalungMapper: "+rs); 
				//return u;
			}
			System.out.println("getMax ID nach IF in UnterhalungMapper: "+rs); 
		}catch (Exception e){
			e.printStackTrace();
			System.out.println("getMaxID " + e.toString());

			throw new IllegalArgumentException("Datenbank fehler!"
					+ e.toString());
			
		}finally{
			DBConnection.closeAll(null, stmt, con );
		}
		
		return u;
	}

	/**
	 * Löschen der Daten eines <code>Unterhaltung</code>-Objekts aus der
	 * Datenbank.
	 * 
	 * @param unterhaltung
	 * @throws Exception
	 */
	public void delete(Unterhaltung unterhaltung)
			throws Exception{
		
		Connection con = DBConnection.connection();
		Statement stmt= null;
		try {
			stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM unterhaltungen WHERE unterhaltungID="+ unterhaltung.getId());
			stmt.close();
			
			// con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Datenbank fehler!"
					+ e.toString());
		}finally {
			DBConnection.closeAll(null, stmt, con );
		}
	}
	
	/**
	 * Diese Methode ermöglicht es alle Nachrichten einer Unterhaltung anhand
	 * ihrer ID zu finden und anzuzeigen.
	 * 
	 * @param unterhaltung
	 * @return result
	 * @throws Exception
	 */
	public ArrayList<Nachricht> findNachrichtenByUnterhaltung(Unterhaltung unterhaltung)
			throws Exception{

		Connection con = DBConnection.connection();
		Statement stmt= null;
		ArrayList<Nachricht> result = new ArrayList<Nachricht>();
		ResultSet rs= null;
		try {
			stmt= con.createStatement();
			rs= stmt.executeQuery("SELECT * FROM unterhaltungen INNER JOIN nachrichten "
					+ "ON nachrichten.unterhaltungID=unterhaltungen.unterhaltungID "
					+ "WHERE unterhaltungen.unterhaltungID= "+ unterhaltung.getId() +" ORDER BY nachrichten.datum");

			while (rs.next()) {
				Nachricht nachricht = new Nachricht();
				nachricht.setId(rs.getInt("nachrichtID"));
				nachricht.setUnterhaltungID(rs.getInt("unterhaltungen.unterhaltungID"));
				nachricht.setText(rs.getString("text"));
				nachricht.setErstellungsZeitpunkt(rs.getString("datum"));
				nachricht.setNutzerID(rs.getInt("nutzerID"));

				result.add(nachricht);
			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Datenbank fehler!"+ e.toString());
		}finally {
			DBConnection.closeAll(rs, stmt, con );
		}
		return result;
	}
}