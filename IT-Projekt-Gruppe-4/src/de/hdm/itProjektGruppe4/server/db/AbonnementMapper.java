package de.hdm.itProjektGruppe4.server.db;

import java.sql.*;
import java.util.ArrayList;

import de.hdm.itProjektGruppe4.shared.bo.Abonnement;
import de.hdm.itProjektGruppe4.shared.bo.Nutzerabonnement;
import de.hdm.itProjektGruppe4.shared.bo.Hashtagabonnement;

/**
 * 
 * @author Thies
 * @author Kologlu
 * @author Oikonomou
 * @author Yücel
 */

public class AbonnementMapper {

	private static AbonnementMapper abonnementMapper = null;

	protected AbonnementMapper() {

	}

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
	 * @return abonnement
	 */
	public Abonnement insertAbonnement(Abonnement abonnement)
			throws IllegalArgumentException {
		// DB-Verbindung herstellen
		Connection con = DBConnection.connection();

		try {
			// Statement stmt = con.createStatement();
			// ResultSet rs =
			// stmt.executeQuery("SELECT MAX(abonnementID) AS maxid " +
			// "FROM abonnement ");

			// if (rs.next()) {

			String sql = "INSERT INTO `abonnements`(`abonnementID`, `datum`) VALUES (NULL, ?)";

			PreparedStatement preStmt;
			preStmt = con.prepareStatement(sql);
			preStmt.setString(1, abonnement.getErstellungsZeitpunkt()
					.toString());
			preStmt.executeUpdate();
			preStmt.close();

			// }
			// stmt.close();
			// rs.close();
			// con.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
			throw new IllegalArgumentException("Datenbank fehler!"
					+ e1.toString());
		}

		return abonnement;
	}


	/**
	 * Diese Methode ermöglicht eine Akutalisierung des Abonnementsdatensatzes
	 * in der Datenbank
	 * 
	 * @param abonnement
	 * @return abonnement
	 */
	public Abonnement updateAbonnement(Abonnement abonnement)
			throws IllegalArgumentException {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE abonnement " + "SET aboArt=\""
					+ "WHERE abonnementID=" + abonnement.getId());

		} catch (SQLException e1) {
			e1.printStackTrace();
			throw new IllegalArgumentException("Datenbank fehler!"
					+ e1.toString());
		}

		return abonnement;
	}


	/**
	 * Diese Methode ermöglicht es eine Ausgabe über die Abonnements in der
	 * Datenbank, anhand deren ID.
	 * 
	 * @param id
	 * @return
	 */

	public Abonnement findAbonnementByKey(int id)
			throws IllegalArgumentException {

		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM abonnement "
					+ "WHERE abonnementID=" + id + " ORDER by abonnementID");

			if (rs.next()) {
				Abonnement abonnement = new Abonnement();
				abonnement.setId(rs.getInt("abonnementID"));

				return abonnement;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
			throw new IllegalArgumentException("Datenbank fehler!"
					+ e1.toString());

		}
		return null;
	}

	/**
	 * Diese Methode ermöglicht es alle Abonnements aus der Datenbank in einer
	 * Liste auszugeben.
	 * 
	 * @return
	 */

	public ArrayList<Abonnement> findAllAbonnements()
			throws IllegalArgumentException {
		Connection con = DBConnection.connection();

		ArrayList<Abonnement> aboListe = new ArrayList<Abonnement>();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT * FROM abonnements ORDER BY abonnementID");

			while (rs.next()) {
				Abonnement abonnement = new Abonnement();
				abonnement.setId(rs.getInt("abonnementID"));
				abonnement.setErstellungsZeitpunkt(rs
						.getString("datum"));

				aboListe.add(abonnement);
			}
			stmt.close();
			rs.close();
			con.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
			throw new IllegalArgumentException("Datenbank fehler!"
					+ e1.toString());
		}
		return aboListe;
	}

}
