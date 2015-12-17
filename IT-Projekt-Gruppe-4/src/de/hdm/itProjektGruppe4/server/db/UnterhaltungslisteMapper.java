package de.hdm.itProjektGruppe4.server.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import de.hdm.itProjektGruppe4.shared.bo.Unterhaltungsliste;

/**
 * Mapper-Klasse, die <code>Unterhaltungsliste</code>-Objekte auf eine relationale
 * Datenbank abbildet. Hierzu wird eine Reihe von Methoden zur Verfügung
 * gestellt, mit deren Hilfe z.B. Objekte gesucht, erzeugt, modifiziert und
 * gelöscht werden können. Das Mapping ist bidirektional. D.h., Objekte können
 * in DB-Strukturen und DB-Strukturen in Objekte umgewandelt werden.
 * 
 * @author Kologlu
 * @author thies
 */

public class UnterhaltungslisteMapper {
	/**
	 * Die Klasse UnterhaltungslisteMapper wird nur einmal instantiiert. Man spricht
	 * hierbei von einem sogenannten <b>Singleton</b>.
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
	 * <code>MarkierungslisteMapper.UnterhaltungslisteMapper()</code>. Sie stellt die
	 * Singleton-Eigenschaft sicher, indem Sie dafür sorgt, dass nur eine
	 * einzige Instanz von <code>UnterhaltungslisteMapper</code> existiert.
	 * <p>
	 * 
	 * <b>Fazit:</b> UnterhaltungslisteMapper sollte nicht mittels <code>new</code>
	 * instantiiert werden, sondern stets durch Aufruf dieser statischen
	 * Methode.
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
	 * Zwischentabelle zum Verknüpfen der Unterhaltung zwischen Absender und Empfänger der Klasse Nutzer in der Datenbank
	 * 
	 * @param unterhaltungsliste
	 * @return unterhaltungsliste
	 * @throws IllegalArgumentException
	 */
	public Unterhaltungsliste insert(Unterhaltungsliste unterhaltungsliste)
			throws IllegalArgumentException{
		// DB-Verbindung herstellen
		Connection con = DBConnection.connection();
		try {
			String sql ="INSERT INTO `unterhaltungslisten`(`unterhaltungslisteID`, `unterhaltungID`, `absenderID`, `empfaengerID`)" 
					+ "VALUES (NULL, ?, ?, ?)";
			
			PreparedStatement preStmt;
			preStmt = con.prepareStatement(sql);
			preStmt.setInt(1, unterhaltungsliste.getUnterhaltundID());
			preStmt.setInt(2, unterhaltungsliste.getAbsenderID());
			preStmt.setInt(3, unterhaltungsliste.getEmpfaengerID());
			preStmt.executeUpdate();
			preStmt.close();
			
		} catch (SQLException e2) {
			e2.printStackTrace();
			throw new IllegalArgumentException("Datenbank fehler!"+ e2.toString());
		}
		return unterhaltungsliste;
	}
	
	
}
