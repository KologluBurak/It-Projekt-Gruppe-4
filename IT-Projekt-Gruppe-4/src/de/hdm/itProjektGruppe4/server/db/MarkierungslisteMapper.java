package de.hdm.itProjektGruppe4.server.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import de.hdm.itProjektGruppe4.shared.bo.Markierungsliste;
import de.hdm.itProjektGruppe4.shared.bo.Unterhaltungsliste;

/**
 * Mapper-Klasse, die <code>Markierungsliste</code>-Objekte auf eine relationale
 * Datenbank abbildet. Hierzu wird eine Reihe von Methoden zur Verfügung
 * gestellt, mit deren Hilfe z.B. Objekte gesucht, erzeugt, modifiziert und
 * gelöscht werden können. Das Mapping ist bidirektional. D.h., Objekte können
 * in DB-Strukturen und DB-Strukturen in Objekte umgewandelt werden.
 * 
 * @author Thies
 * @author Kologlu
 * @author Yücel
 * 
 */

public class MarkierungslisteMapper {

	/**
	 * Die Klasse MarkierungslisteMapper wird nur einmal instantiiert. Man
	 * spricht hierbei von einem sogenannten <b>Singleton</b>.
	 * <p>
	 * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal
	 * für sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie
	 * speichert die einzige Instanz dieser Klasse.
	 * 
	 * @see MarkierungslisteMapper()
	 */
	private static MarkierungslisteMapper markierungslisteMapper = null;

	/**
	 * Geschützter Konstruktor - verhindert die Möglichkeit, mit
	 * <code>new</code> neue Instanzen dieser Klasse zu erzeugen.
	 */
	protected MarkierungslisteMapper() {
	}

	/**
	 * Diese statische Methode kann aufgrufen werden durch
	 * <code>MarkierungslisteMapper.MarkierungslisteMapper()</code>. Sie stellt
	 * die Singleton-Eigenschaft sicher, indem Sie dafür sorgt, dass nur eine
	 * einzige Instanz von <code>MarkierungslisteMapper</code> existiert.
	 * <p>
	 * 
	 * <b>Fazit:</b> MarkierungslisteMapper sollte nicht mittels
	 * <code>new</code> instantiiert werden, sondern stets durch Aufruf dieser
	 * statischen Methode.
	 * 
	 * @return DAS <code>MarkierungslisteMapper</code>-Objekt.
	 * @see MarkierungslisteMapper
	 */
	public static MarkierungslisteMapper markierungslisteMapper() {
		if (markierungslisteMapper == null) {
			markierungslisteMapper = new MarkierungslisteMapper();
		}
		return markierungslisteMapper;
	}

	/**
	 * Zwischentabelle zum Verknüpfen von Nachrichten und Hashtags in der
	 * Datenbank
	 * 
	 * @param markierungsliste
	 * @return markierungsliste
	 * @throws Exception
	 */
	public Markierungsliste insert(Markierungsliste markierungsliste)
			throws Exception {
		// DB-Verbindung herstellen
		Connection con = DBConnection.connection();
		PreparedStatement preStmt = null;
		try {
			String sql = "INSERT INTO `markierungslisten`(`markierunglisteID`, `nachrichtID`, `hashtagID`) "
					+ "VALUES (NULL, ?, ?)";

			//PreparedStatement preStmt;
			preStmt = con.prepareStatement(sql);
			preStmt.setInt(1, markierungsliste.getNachrichtID());
			preStmt.setInt(2, markierungsliste.getHashtagID());
			preStmt.executeUpdate();
			//preStmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Datenbank fehler!"+ e.toString());
		}finally {
			DBConnection.closeAll(null, preStmt, con);
		}
		return markierungsliste;
	}
	
	/**
	 * 
	 * @param markierungsliste
	 * @throws Exception 
	 */
	public void delete(Markierungsliste markierungsliste) throws Exception{
		Connection con = DBConnection.connection();
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM markierungslisten " + "WHERE markierungslisteID="
					+ markierungsliste.getId());

		} catch (SQLException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Datenbank fehler!"+ e.toString());
		}finally {
			DBConnection.closeAll(null, stmt, con);
		}
	}

	/**
	 * 
	 * @param nachricht
	 * @return mliste
	 * @throws IllegalArgumentException
	 */
	public Markierungsliste findByNachricht(String nachricht)
			throws Exception {

		// DB-Verbindung herstellen
		Connection con = DBConnection.connection();
		Statement stmt = null;
		ResultSet rs = null;
		try {

			String sql = "SELECT *  FROM `markierungslisten` WHERE `nachrichtID` = " + nachricht;

			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				Markierungsliste mliste = new Markierungsliste();
				mliste.setId(rs.getInt("nutzerID"));
				mliste.setNachrichtID(rs.getInt("nachrichtID"));
				mliste.setHashtagID(rs.getInt("hashtagID"));

				return mliste;
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
	 * 
	 * @param hashtag
	 * @return mliste
	 * @throws Exception
	 */
	public Markierungsliste findByHashtag(String hashtag)
			throws Exception {

		// DB-Verbindung herstellen
		Connection con = DBConnection.connection();
		Statement stmt = null;
		ResultSet rs = null;
		try {

			String sql = "SELECT *  FROM `markierungslisten` WHERE `hashtagID` = " + hashtag;

			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				Markierungsliste mliste = new Markierungsliste();
				mliste.setId(rs.getInt("nutzerID"));
				mliste.setNachrichtID(rs.getInt("nachrichtID"));
				mliste.setHashtagID(rs.getInt("hashtagID"));

				return mliste;
			}
			
			//rs.close()

		} catch (SQLException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Datenbank fehler!"+ e.toString());
		}finally {
			DBConnection.closeAll(rs, stmt, con);
		}

		return null;
	}
}
