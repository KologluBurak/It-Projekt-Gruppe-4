package de.hdm.itProjektGruppe4.server.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import de.hdm.itProjektGruppe4.shared.bo.Markierungsliste;

/**
 * Mapper-Klasse, die <code>Markierungsliste</code>-Objekte auf eine relationale
 * Datenbank abbildet. Hierzu wird eine Reihe von Methoden zur Verfügung
 * gestellt, mit deren Hilfe z.B. Objekte gesucht, erzeugt, modifiziert und
 * gelöscht werden können. Das Mapping ist bidirektional. D.h., Objekte können
 * in DB-Strukturen und DB-Strukturen in Objekte umgewandelt werden.
 * 
 * @author Kologlu
 * @author Thies
 */

public class MarkierungslisteMapper {

	/**
	 * Die Klasse MarkierungslisteMapper wird nur einmal instantiiert. Man spricht
	 * hierbei von einem sogenannten <b>Singleton</b>.
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
	 * <code>MarkierungslisteMapper.MarkierungslisteMapper()</code>. Sie stellt die
	 * Singleton-Eigenschaft sicher, indem Sie dafür sorgt, dass nur eine
	 * einzige Instanz von <code>MarkierungslisteMapper</code> existiert.
	 * <p>
	 * 
	 * <b>Fazit:</b> MarkierungslisteMapper sollte nicht mittels <code>new</code>
	 * instantiiert werden, sondern stets durch Aufruf dieser statischen
	 * Methode.
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
	 * Zwischentabelle zum Verknüpfen von Nachrichten und Hashtags in der Datenbank
	 * 
	 * @param markierungsliste
	 * @return markierungsliste
	 * @throws IllegalArgumentException
	 */
	public Markierungsliste insert(Markierungsliste markierungsliste)
			throws IllegalArgumentException{
		// DB-Verbindung herstellen
		Connection con = DBConnection.connection();
		
		try {
			String sql ="INSERT INTO `markierungslisten`(`markierunglisteID`, `nachrichtID`, `hashtagID`) "
					+ "VALUES (NULL, ?, ?)";
			
			PreparedStatement preStmt;
			preStmt = con.prepareStatement(sql);
			preStmt.setInt(1, markierungsliste.getNachrichtID());
			preStmt.setInt(2, markierungsliste.getHashtagID());
			preStmt.executeUpdate();
			preStmt.close();
			
		} catch (SQLException e2) {
			e2.printStackTrace();
			throw new IllegalArgumentException("Datenbank fehler!"+ e2.toString());
		}
		return markierungsliste;
	}
	
	
	
}
