package de.hdm.itProjektGruppe4.server.db;

import java.sql.*;
import java.util.ArrayList;
import de.hdm.itProjektGruppe4.shared.bo.*;

/**
 * Mapper-Klasse, die <code>HashtagAbo</code>-Objekte auf eine relationale
 * Datenbank abbildet. Hierzu wird eine Reihe von Methoden zur Verfügung
 * gestellt, mit deren Hilfe z.B. Objekte gesucht, erzeugt, modifiziert und
 * gelöscht werden können. Das Mapping ist bidirektional. D.h., Objekte können
 * in DB-Strukturen und DB-Strukturen in Objekte umgewandelt werden.
 * 
 * @author Oikonomou
 * @author Thies
 * @author Yücel
 *
 */

public class HashtagAboMapper {

	/**
	 * Die Klasse HashtagAboMapper wird nur einmal instantiiert. Man spricht
	 * hierbei von einem sogenannten <b>Singleton</b>.
	 * <p>
	 * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal
	 * für sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie
	 * speichert die einzige Instanz dieser Klasse.
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
	 */
	public Hashtagabonnement insert(Hashtagabonnement hashtagabonnement)
			throws IllegalArgumentException {
		// DB-Verbindung herstellen
		Connection con = DBConnection.connection();

		try {
			//Statement stmt = con.createStatement();
			//ResultSet rs = stmt.executeQuery("SELECT MAX(hashtagaboID) AS maxid "+ "FROM hashtagabonnement ");

			//if (rs.next()) {
				
				String sql = "INSERT INTO `hashtagabonnements`(`hashtagAboID`, `datum`, `hashtagID`, `abonnementID`, `nutzerID`) "
							+ "VALUES (NULL, ?, ?, ?, ?)";
			
				PreparedStatement preStmt;
				preStmt = con.prepareStatement(sql);
				preStmt.setString(1, hashtagabonnement.getErstellungsZeitpunkt().toString());
				preStmt.setInt(2, hashtagabonnement.getHastagID());
				preStmt.setInt(3, hashtagabonnement.getAbonnementID());
				preStmt.setInt(4, hashtagabonnement.getNutzerID());
				preStmt.executeUpdate();
				preStmt.close();
			
			//}
			//stmt.close();
			//rs.close();
			//con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Datenbank fehler!"+ e.toString());
		}

		return hashtagabonnement;
	}

	/**
	 * Diese Methode ermöglicht eine Akutalisierung des Hashtagabodatensatzes in
	 * der Datenbank.
	 * 
	 * @param hashtagabonnement
	 * @return hashtagabonnement
	 */

	public Hashtagabonnement update(Hashtagabonnement hashtagabonnement)
			throws IllegalArgumentException {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE Hashtagabonnement " + "SET hashtagID=\""
					+ "WHERE hashtagaboID=" + hashtagabonnement.getId());

		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		return hashtagabonnement;
	}

	/**
	 * Diese Methode ermöglicht das Löschen eines Hashtagabonnements und dessen
	 * Referenzen zu anderen Klassen
	 * 
	 * @param hashtagabonnement
	 */

	public void delete(Hashtagabonnement hashtagabonnement)
			throws IllegalArgumentException {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM Hashtagabonnement "
					+ "WHERE hashtagaboID=" + hashtagabonnement.getId());

		} catch (SQLException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Datenbank fehler!"
					+ e.toString());
		}
	}

	/**
	 * Diese Methode ermöglicht es alle Hashtags zu finden
	 * 
	 * @param hashtagabonnement
	 * @return
	 */
	public ArrayList<Hashtagabonnement> findAllHashtagabonnements(int id) {
		// DB-Verbindung herstellen
		Connection con = DBConnection.connection();
		ArrayList<Hashtagabonnement> alleHashtagabonnements = new ArrayList<Hashtagabonnement>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Hashtagabonnement "
					+ "WHERE hashtagaboID" + id + " ORDER by hashtagaboID");

			while (rs.next()) {
				Hashtagabonnement hashtagAbonnement = new Hashtagabonnement();
				hashtagAbonnement.setId(rs.getInt("hashtagaboID"));

				alleHashtagabonnements.add(hashtagAbonnement);

			}
			stmt.close();
			rs.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alleHashtagabonnements;

	}

	/**
	 * Diese Methode ermöglicht es eine Ausgabe über einen Hashtagabonnements in
	 * der Datenbank, anhand deren ID.
	 * 
	 * @param id
	 * @return
	 */

	public Hashtagabonnement findHashtagAboByID(int id) {

		// DB-Verbindung herstellen
		Connection con = DBConnection.connection();

		try {
			// Insert-Statement erzeugen
			Statement stmt = con.createStatement();

			/*
			 * Zunächst wird geschaut welches der momentan höchste
			 * Primärschlüssel ist
			 */
			ResultSet rs = stmt.executeQuery("SELECT * FROM Hashtagabonnement "
					+ "WHERE hashtagAboID=" + id + " ORDER by hashtagAboID");

			// Wenn ein Datensatz gefunden wurde, wird auf diesen zugegriffen
			if (rs.next()) {
				Hashtagabonnement hashtagAbonnement = new Hashtagabonnement();
				hashtagAbonnement.setId(rs.getInt("hashtagAboID"));

				return hashtagAbonnement;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
			return null;

		}
		return null;
	}

	/**
	 * Diese Methode ermöglicht eine Ausgabe über die Hashtagabonnements eines
	 * Nutzers, in einer Liste.
	 * 
	 * @param id
	 * @return
	 */

	public ArrayList<Hashtagabonnement> findHashtagAbonnementByNutzer(int id) {
		Connection con = DBConnection.connection();
		ArrayList<Hashtagabonnement> hashtagAboListe = new ArrayList<Hashtagabonnement>();

		try {

			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM Hashtagabonnement "
					+ "WHERE hashtagAboID=" + id + " ORDER BY hashtagAboID");

			while (rs.next()) {
				Hashtagabonnement hashtagabonnement = new Hashtagabonnement();
				hashtagabonnement.setId(rs.getInt("hashtagAboID"));

				hashtagAboListe.add(hashtagabonnement);
			}

		}

		catch (SQLException e1) {
			e1.printStackTrace();

			return null;
		}

		return hashtagAboListe;

	}

}
