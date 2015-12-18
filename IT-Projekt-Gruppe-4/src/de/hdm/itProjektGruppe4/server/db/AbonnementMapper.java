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
	 * Diese Methode ermöglicht es einen Abonnement in der Datenbank anzulegen.
	 * 
	 * @param abonnement
	 * @return abonnement
	 */
	public Abonnement insertAbonnement(Abonnement abonnement) {
		// DB-Verbindung herstellen
		Connection con = DBConnection.connection();

		try {
			//Statement stmt = con.createStatement();
			//ResultSet rs = stmt.executeQuery("SELECT MAX(abonnementID) AS maxid "	+ "FROM abonnement ");

			//if (rs.next()) {

				String sql ="INSERT INTO `abonnements`(`abonnementID`, `datum`) VALUES (NULL, ?)";
			
				PreparedStatement preStmt;
				preStmt = con.prepareStatement(sql);
				preStmt.setString(1, abonnement.getErstellungsZeitpunkt().toString());
				preStmt.executeUpdate();
				preStmt.close();
			
			//}
			//stmt.close();
			//rs.close();
			//con.close();
		} catch(SQLException e1) {
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

		ArrayList<Abonnement> aboListe = new ArrayList<Abonnement>();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT abonnementID, erstellungsZeitpunkt FROM abonnements ORDER BY abonnementID");

			while (rs.next()) {
				Abonnement abonnement = new Abonnement();
				abonnement.setId(rs.getInt("abonnementID"));
				abonnement.setErstellungsZeitpunkt(rs
						.getDate("erstellungsZeitpunkt"));

				aboListe.add(abonnement);
			}
			stmt.close();
			rs.close();
			con.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return aboListe;
	}

}