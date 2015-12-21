package de.hdm.itProjektGruppe4.server.db;

import java.sql.*;
import java.util.ArrayList;

import de.hdm.itProjektGruppe4.shared.bo.Abonnement;
import de.hdm.itProjektGruppe4.shared.bo.Nutzerabonnement;
import de.hdm.itProjektGruppe4.shared.bo.Hashtagabonnement;

/**
 * @author Kologlu
 * @author Oikonomou
 * @author Thies
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
	 * Diese Methode ermöglicht eine Akutalisierung des Abonnementsdatensatzes
	 * in der Datenbank
	 * 
	 * @param abonnement
	 * @return
	 */

	public Abonnement updateAbonnement(Abonnement abonnement) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE abonnement " + "SET aboArt=\""
					+ "WHERE abonnementID=" + abonnement.getId());

		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		return abonnement;
	}

	/**
	 * Diese Methode ermöglicht es einen Abonnement in der Datenbank anzulegen.
	 * 
	 * @param abonnement
	 * @return
	 */

	public Abonnement insertAbonnement(Abonnement abonnement) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt
					.executeQuery("SELECT MAX(abonnementID) AS maxid "
							+ "FROM abonnement ");

			if (rs.next()) {

				abonnement.setId(rs.getInt("maxid") + 1);
				abonnement.setErstellungsZeitpunkt(rs.getTimestamp("datum"));

				stmt = con.createStatement();

				stmt.executeUpdate("INSERT INTO abonnement (abonnementID, datum,) "
						+ "VALUES (" + abonnement.getId() + ")");
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
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

	public Abonnement findAbonnementByKey(int id) {

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
			return null;

		}
		return null;
	}

	/**
	 * Diese Methode ermöglicht es alle Abonnements aus der Datenbank in einer
	 * Liste auszugeben.
	 * 
	 * @return
	 */

	public ArrayList<Abonnement> findAllAbonnements() {
		Connection con = DBConnection.connection();

		System.out.println("con erstellt");
		ArrayList<Abonnement> aboListe = new ArrayList<Abonnement>();

		try {
			System.out.println("try anfang");
			Statement stmt = con.createStatement();
			
			System.out.println("stmt erstellt");
			ResultSet rs = stmt
					.executeQuery("SELECT * FROM abonnements ORDER BY abonnementID");

			System.out.println("SQL vergeben");
			while (rs.next()) {
				Abonnement abonnement = new Abonnement();
				abonnement.setId(rs.getInt("abonnementID"));
				abonnement.setErstellungsZeitpunkt(rs
						.getDate("datum"));

				aboListe.add(abonnement);
			}
//			stmt.close();
//			rs.close();
//			con.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return aboListe;
	}

}