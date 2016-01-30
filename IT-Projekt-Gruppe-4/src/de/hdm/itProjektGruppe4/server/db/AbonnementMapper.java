package de.hdm.itProjektGruppe4.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import de.hdm.itProjektGruppe4.shared.bo.*;

/**
 * Mapper-Klasse, die <code>Abonnement</code>-Objekte auf eine relationale
 * Datenbank abbildet. Hierzu wird eine Reihe von Methoden zur Verfügung
 * gestellt, mit deren Hilfe z.B. Objekte gesucht, erzeugt, modifiziert und
 * geloescht werden können. Das Mapping ist bidirektional. D.h., Objekte können
 * in DB-Strukturen und DB-Strukturen in Objekte umgewandelt werden.
 * 
 * @author Thies
 * @author Kologlu
 * @author Oikonomou
 * @author Yücel
 */

public class AbonnementMapper {

	/**
	 * Die Klasse AbonnementMapper wird nur einmal instantiiert. Man spricht
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
	
	private static AbonnementMapper abonnementMapper = null;

	/**
	 * Geschuetzter Konstruktor - verhindert die Möglichkeit, mit
	 * <code>new</code> neue Instanzen dieser Klasse zu erzeugen.
	 */
	protected AbonnementMapper() {
	}
	
	/**
	 * Diese statische Methode kann aufgrufen werden durch
	 * <code>AbonnementMapper.abonnementMapper()</code>. Sie stellt die
	 * Singleton-Eigenschaft sicher, indem Sie dafür sorgt, dass nur eine
	 * einzige Instanz von <code>AbonnementMapper</code> existiert.
	 * <p>
	 * 
	 * <b>Fazit:</b> AbonnementMapper sollte nicht mittels <code>new</code>
	 * instantiiert werden, sondern stets durch Aufruf dieser statischen
	 * Methode.
	 * 
	 * @return DAS <code>AbonnementMapper</code>-Objekt.
	 * @see AbonnementMapper
	 */

	public static AbonnementMapper abonnementMapper() {
		if (abonnementMapper == null) {
			abonnementMapper = new AbonnementMapper();
		}
		return abonnementMapper;

	}

	/** 
	 * Diese Methode ermöglicht es einen Abonnement in der Datenbank anzulegen.
	 * 
	 * @param abonnement
	 * @return
	 * @throws Exception
	 */
	public Abonnement insertAbonnement(Abonnement abonnement) 
			throws Exception {
		// DB-Verbindung herstellen
		Connection con = DBConnection.connection();
		PreparedStatement preStmt = null;

		try {
			String sql = "INSERT INTO `abonnements`(`abonnementID`) VALUES (NULL)";

			preStmt = con.prepareStatement(sql);
			preStmt.executeUpdate();
			preStmt.close();
			
			// con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Datenbank fehler!" + e.toString());
		}finally {
			DBConnection.closeAll(null, preStmt, con);
		}

		return abonnement;
	}
	
	/**
	 * Diese Methode ermöglicht das Löschen eines Abonnements
	 * 
	 * @param abonnement
	 * @throws Exception
	 */
	public void delete(Abonnement abonnement) 
			throws Exception {
		Connection con = DBConnection.connection();
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM abonnements " + "WHERE abonnementID=" + abonnement.getId());

		} catch (SQLException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Datenbank fehler!" + e.toString());
		}finally {
			DBConnection.closeAll(null, stmt, con);
		}
	}


//	/**
//	 * Diese Methode ermöglicht es eine Ausgabe über die Abonnements in der
//	 * Datenbank, anhand deren ID.
//	 * 
//	 * @param id
//	 * @return
//	 * @throws IllegalArgumentException
//	 */
//	
//	public Abonnement findAbonnementById(int id) throws IllegalArgumentException {
//		Connection con = DBConnection.connection();
//		try {
//			Statement stmt = con.createStatement();
//
//			ResultSet rs = stmt.executeQuery("SELECT * FROM abonnements " + "WHERE abonnementID='" + id + "'");
//
//
//			if (rs.next()) {
//				Abonnement abonnement = new Abonnement();
//				abonnement.setId(rs.getInt("abonnementID"));
//				abonnement.setErstellungsZeitpunkt(rs.getString("datum"));
//				
//				return abonnement;
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//			throw new IllegalArgumentException("Datenbank fehler!" + e.toString());
//
//		}
//		return null;
//	}

	/**
	 * Diese Methode ermöglicht es alle Abonnements aus der Datenbank in einer
	 * Liste auszugeben.
	 * 
	 * @return aboListe
	 * @throws Exception
	 */
	
	public ArrayList<Abonnement> findAllAbonnements() throws Exception {
		Connection con = DBConnection.connection();
		Statement stmt = null;
		ResultSet rs = null;

		ArrayList<Abonnement> aboListe = new ArrayList<Abonnement>();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM abonnements ORDER BY abonnementID");

			while (rs.next()) {
				Abonnement abonnement = new Abonnement();
				abonnement.setId(rs.getInt("abonnementID"));

				aboListe.add(abonnement);
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
		return aboListe;
	}
}
