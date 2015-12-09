package de.hdm.itProjektGruppe4.server.db;

import java.sql.*;
import java.util.ArrayList;

import de.hdm.itProjektGruppe4.shared.bo.*;

/**
 * Mapper-Klasse, die <code>Hashtag</code>-Objekte auf eine relationale
 * Datenbank abbildet. Hierzu wird eine Reihe von Methoden zur Verfügung
 * gestellt, mit deren Hilfe z.B. Objekte gesucht, erzeugt, modifiziert und
 * gel�scht werden können. Das Mapping ist bidirektional. D.h., Objekte können
 * in DB-Strukturen und DB-Strukturen in Objekte umgewandelt werden.
 * 
 * @author Kologlu
 * @author Oikonomou
 * @author Thies
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
	 * Geschätzter Konstruktor - verhindert die M�glichkeit, mit
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
	 * @param hashtagBezeichnung
	 * @return
	 */

	public Hashtag insert(Hashtag hashtagBezeichnung)throws IllegalArgumentException {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
					+ "FROM nachricht ");

			if (rs.next()) {

				hashtagBezeichnung.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				stmt.executeUpdate("INSERT INTO hashtag (hashtagID, bezeichnung) "
						+ "VALUES ("
						+ hashtagBezeichnung.getId()
						+ ","
						+ hashtagBezeichnung.getBezeichnung() + ")");
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
			throw new IllegalArgumentException("Datenbank fehler!" + e2.toString());
		}

		return hashtagBezeichnung;
	}

	/**
	 * Diese Methode ermöglicht eine Aktualisierung des Hashtagdatensatzes in
	 * der Datenbank.
	 * 
	 * @param hashtag
	 * @return
	 */
	public Hashtag update(Hashtag hashtag)throws IllegalArgumentException {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE hashtag " + "SET bezeichnung=\""
					+ hashtag.getBezeichnung() + "\" " + "WHERE hashtagID="
					+ hashtag.getId());

		} catch (SQLException e2) {
			e2.printStackTrace();
			throw new IllegalArgumentException("Datenbank fehler!" + e2.toString());
		}

		return hashtag;
	}

	/**
	 * Diese Methode ermöglicht das Löschen eines Nutzer und dessen Referenzen
	 * zu anderen Klassen.
	 * 
	 * @param hashtagBezeichnung
	 */
	public void delete(Hashtag hashtagBezeichnung)throws IllegalArgumentException {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM hashtag " + "WHERE hashtagID="
					+ hashtagBezeichnung.getId());

		} catch (SQLException e2) {
			e2.printStackTrace();
			throw new IllegalArgumentException("Datenbank fehler!" + e2.toString());
		}
	}

	/**
	 * Diese Methode ermöglicht es alle Hashtag aus der datenbank in einer Liste
	 * auszugeben.
	 * 
	 * @return
	 */
	public ArrayList<Hashtag> findAllHashtags() {
		Connection con = DBConnection.connection();

		ArrayList<Hashtag> result = new ArrayList<Hashtag>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt
					.executeQuery("SELECT (hashtagID, bezeichnung) FROM hashtag"
							+ " ORDER BY hashtagID");

			while (rs.next()) {
				Hashtag hashtag = new Hashtag();
				hashtag.setId(rs.getInt("hashtagID"));
				hashtag.setBezeichnung(rs.getString("bezeichnung"));

				result.add(hashtag);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return result;
	}

	/**
	 * Diese Methode ermöglicht das Ausgeben der Hashtag anhand deren ID aus der
	 * Datenbank.
	 * 
	 * @param id
	 * @return
	 */

	public Hashtag findHashtagByID(int id) {
		// DB-Verbindung herstellen
		Connection con = DBConnection.connection();

		try {
			// Insert-Statement erzeugen
			Statement stmt = con.createStatement();
			// Zun�chst wird geschaut welches der momentan h�chste
			// Prim�rschl�ssel ist
			ResultSet rs = stmt.executeQuery("SELECT id, name FROM hashtag "
					+ "WHERE id=" + id + " ORDER by name");

			// Wenn ein Datensatz gefunden wurde, wird auf diesen zugegriffen
			if (rs.next()) {
				Hashtag hashtag = new Hashtag();
				hashtag.setId(rs.getInt("hashtagID"));
				hashtag.setBezeichnung(rs.getString("Bezeichnung"));
				return hashtag;
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
			return null;

		}
		return null;
	}

}
