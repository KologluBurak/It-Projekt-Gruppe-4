package de.hdm.itProjektGruppe4.server.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import de.hdm.itProjektGruppe4.shared.bo.Nutzer;
import de.hdm.itProjektGruppe4.shared.bo.Unterhaltung;
import de.hdm.itProjektGruppe4.shared.bo.Unterhaltungsliste;

/**
 * Mapper-Klasse, die <code>Unterhaltungsliste</code>-Objekte auf eine
 * relationale Datenbank abbildet. Hierzu wird eine Reihe von Methoden zur
 * Verfügung gestellt, mit deren Hilfe z.B. Objekte gesucht, erzeugt,
 * modifiziert und gelöscht werden können. Das Mapping ist bidirektional. D.h.,
 * Objekte können in DB-Strukturen und DB-Strukturen in Objekte umgewandelt
 * werden.
 * 
 * @author thies
 * @author Kologlu
 * @author Yücel
 */

public class UnterhaltungslisteMapper {
	/**
	 * Die Klasse UnterhaltungslisteMapper wird nur einmal instantiiert. Man
	 * spricht hierbei von einem sogenannten <b>Singleton</b>.
	 * <p>
	 * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal
	 * für sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie
	 * speichert die einzige Instanz dieser Klasse.
	 * 
	 * @see UnterhaltungslisteMapper()
	 */
	private static UnterhaltungslisteMapper unterhaltungslisteMapper = null;

	/**
	 * Geschützter Konstruktor - verhindert die Möglichkeit, mit
	 * <code>new</code> neue Instanzen dieser Klasse zu erzeugen.
	 */
	protected UnterhaltungslisteMapper() {
	}

	/**
	 * Diese statische Methode kann aufgrufen werden durch
	 * <code>MarkierungslisteMapper.UnterhaltungslisteMapper()</code>. Sie
	 * stellt die Singleton-Eigenschaft sicher, indem Sie dafür sorgt, dass nur
	 * eine einzige Instanz von <code>UnterhaltungslisteMapper</code> existiert.
	 * <p>
	 * 
	 * <b>Fazit:</b> UnterhaltungslisteMapper sollte nicht mittels
	 * <code>new</code> instantiiert werden, sondern stets durch Aufruf dieser
	 * statischen Methode.
	 * 
	 * @return DAS <code>UnterhaltungslisteMapper</code>-Objekt.
	 * @see UnterhaltungslisteMapper
	 */
	public static UnterhaltungslisteMapper unterhaltungslisteMapper() {
		if (unterhaltungslisteMapper == null) {
			unterhaltungslisteMapper = new UnterhaltungslisteMapper();
		}
		return unterhaltungslisteMapper;
	}

	/**
	 * Zwischentabelle zum Verknüpfen der Unterhaltung zwischen Absender und
	 * Empfänger der Klasse Nutzer in der Datenbank
	 * 
	 * @param unterhaltungsliste
	 * @return unterhaltungsliste
	 * @throws IllegalArgumentException
	 */
	public Unterhaltungsliste insert(Unterhaltungsliste unterhaltungsliste)
			throws IllegalArgumentException {
		// DB-Verbindung herstellen
		Connection con = DBConnection.connection();
		try {
			String sql = "INSERT INTO `unterhaltungslisten`(`unterhaltungslisteID`, `unterhaltungID`, `absenderID`, `empfaengerID`)"
					+ "VALUES (NULL, ?, ?, ?)";

			PreparedStatement preStmt;
			preStmt = con.prepareStatement(sql);
			preStmt.setInt(1, unterhaltungsliste.getUnterhaltungID());
			preStmt.setInt(2, unterhaltungsliste.getAbsenderID());
			preStmt.setInt(3, unterhaltungsliste.getEmpfaengerID());
			preStmt.executeUpdate();
			preStmt.close();

		} catch (SQLException e2) {
			e2.printStackTrace();
			throw new IllegalArgumentException("Datenbank fehler!"
					+ e2.toString());
		}
		return unterhaltungsliste;
	}

	/**
	 * Die Methode ermöglicht das Löschen der Unterhaltungslisten aus der Datenbank
	 * und dessen Referenzen zu anderen Klassen
	 * 
	 * @param unterhaltungsliste
	 */
	public void delete(Unterhaltungsliste unterhaltungsliste){
		Connection con = DBConnection.connection();
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM unterhaltungslisten " + "WHERE unterhaltungslisteID="
					+ unterhaltungsliste.getId());

		} catch (SQLException e2) {
			e2.printStackTrace();
			throw new IllegalArgumentException("Datenbank fehler!"+ e2.toString());
		}
	}
	
	/**
	 * Die Methode ermöglicht das Finden eines Absenders aus der Datenbank
	 * über den Parameter
	 * 
	 * @param absenderNickname
	 * @return uliste
	 * @throws IllegalArgumentException
	 */
	public Unterhaltungsliste findByAbsender(Nutzer absenderNickname)
			throws IllegalArgumentException {

		// DB-Verbindung herstellen
		Connection con = DBConnection.connection();
		try {

			String sql = "SELECT *  FROM `unterhaltungslisten` WHERE `absenderID` = " + absenderNickname.getId();

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				Unterhaltungsliste uliste = new Unterhaltungsliste();
				uliste.setId(rs.getInt("unterhaltungslisteID"));
				uliste.setUnterhaltungID(rs.getInt("unterhaltungID"));
				uliste.setAbsenderID(rs.getInt("absenderID"));
				uliste.setEmpfaengerID(rs.getInt("empfaengerID"));

				return uliste;
			}

		} catch (SQLException e2) {
			e2.printStackTrace();
			throw new IllegalArgumentException("Datenbank fehler!"
					+ e2.toString());
		}

		return null;
	}

	/**
	 * Die Methode ermöglicht das Finden eines Empfängers aus der Datenbank
	 * über den Parameter
	 * 
	 * @param empfaengerNickname
	 * @return uliste
	 * @throws IllegalArgumentException
	 */
	public Unterhaltungsliste findByEmpfaenger(Nutzer empfaengerNickname)
			throws IllegalArgumentException {

		// DB-Verbindung herstellen
		Connection con = DBConnection.connection();
		try {

			String sql = "SELECT *  FROM `unterhaltungslisten` WHERE `empfaengerID` = " + empfaengerNickname.getId();


			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				Unterhaltungsliste uliste = new Unterhaltungsliste();
				uliste.setId(rs.getInt("unterhaltungslisteID"));
				uliste.setUnterhaltungID(rs.getInt("unterhaltungID"));
				uliste.setAbsenderID(rs.getInt("absenderID"));
				uliste.setEmpfaengerID(rs.getInt("empfaengerID"));

				return uliste;
			}

		} catch (SQLException e2) {
			e2.printStackTrace();
			throw new IllegalArgumentException("Datenbank fehler!"
					+ e2.toString());
		}

		return null;
	}

	/**
	 * Die Methode ermöglicht das Finden einer Unterhaltung über den Parameter
	 * 
	 * @param unterhaltung
	 * @return unterhaltungsliste
	 * @throws IllegalArgumentException
	 */
	public Unterhaltungsliste findByUnterhaltung(Unterhaltung unterhaltung)
			throws IllegalArgumentException {
		// DB-Verbindung herstellen
		Connection con = DBConnection.connection();
		try {
			String sql = "SELECT *  FROM `unterhaltungslisten` WHERE `unterhaltungID` = "
					+ unterhaltung.getId();

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				Unterhaltungsliste unterhaltungsliste = new Unterhaltungsliste();
				unterhaltungsliste.setId(rs.getInt("unterhaltungsliste"));
				unterhaltungsliste.setUnterhaltungID(rs.getInt("unterhaltungID"));
				unterhaltungsliste.setAbsenderID(rs.getInt("absenderID"));
				unterhaltungsliste.setEmpfaengerID(rs.getInt("empfaengerID"));

				return unterhaltungsliste;
			}

		} catch (SQLException e2) {
			e2.printStackTrace();
			throw new IllegalArgumentException("Datenbank fehler!"
					+ e2.toString());
		}
		return null;
	}
}