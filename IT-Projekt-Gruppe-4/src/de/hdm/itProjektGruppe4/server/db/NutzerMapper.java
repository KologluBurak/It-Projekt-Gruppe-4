package de.hdm.itProjektGruppe4.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.sql.PreparedStatement;

import de.hdm.itProjektGruppe4.shared.bo.*;



/**
 * Mapper-Klasse, die <code>Nutzer</code>-Objekte auf eine relationale
 * Datenbank abbildet. Hierzu wird eine Reihe von Methoden zur Verf�gung
 * gestellt, mit deren Hilfe z.B. Objekte gesucht, erzeugt, modifiziert und
 * gel�scht werden k�nnen. Das Mapping ist bidirektional. D.h., Objekte k�nnen
 * in DB-Strukturen und DB-Strukturen in Objekte umgewandelt werden.
 * 
 * @author Kologlu
 * @author Oikonomou
 * @author Thies
 */
public class NutzerMapper {
	 
	/**
	   * Die Klasse NutzerMapper wird nur einmal instantiiert. Man spricht hierbei
	   * von einem sogenannten <b>Singleton</b>.
	   * <p>
	   * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal f�r
	   * s�mtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert die
	   * einzige Instanz dieser Klasse.
	   * 
	   * @see NutzerMapper()
	   */
	private static NutzerMapper nutzerMapper = null;
	
	/**
	   * Gesch�tzter Konstruktor - verhindert die M�glichkeit, mit <code>new</code>
	   * neue Instanzen dieser Klasse zu erzeugen.
	   */
	protected NutzerMapper(){
		
	}
	
	/**
	   * Diese statische Methode kann aufgrufen werden durch
	   * <code>NutzerMapper.nutzerMapper()</code>. Sie stellt die
	   * Singleton-Eigenschaft sicher, indem Sie daf�r sorgt, dass nur eine einzige
	   * Instanz von <code>NutzerMapper</code> existiert.
	   * <p>
	   * 
	   * <b>Fazit:</b> NutzerMapper sollte nicht mittels <code>new</code>
	   * instantiiert werden, sondern stets durch Aufruf dieser statischen Methode.
	   * 
	   * @return DAS <code>NutzerMapper</code>-Objekt.
	   * @see nutzerMapper
	   */
	public static NutzerMapper nutzerMapper(){
		if(nutzerMapper==null) {
			nutzerMapper= new NutzerMapper();
		}
		return nutzerMapper;
	}
	
	/**
	   * Einf�gen eines <code>Nutzer</code>-Objekts in die Datenbank. Dabei wird
	   * auch der Prim�rschl�ssel des �bergebenen Objekts gepr�ft und ggf.
	   * berichtigt.
	   * 
	   * @param a das zu speichernde Objekt
	   * @return das bereits �bergebene Objekt, jedoch mit ggf. korrigierter
	   *         <code>id</code>.
	   */
	public Nutzer insert(Nutzer nutzer) throws IllegalArgumentException{
		//DB-Verbindung herstellen
		Connection con = DBConnection.connection();
		try{
			//Insert-Statement erzeugen
			Statement stmt = con.createStatement();
			//Zun�chst wird geschaut welches der momentan h�chste Prim�rschl�ssel ist
			ResultSet rs=stmt.executeQuery("SELECT MAX(nutzerID) AS maxid "+"FROM nutzer");
			
			//Wenn ein Datensatz gefunden wurde, wird auf diesen zugegriffen
			if(rs.next()){
				int newID=rs.getInt("maxid");
				nutzer.setId(newID);
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Date d = null;
				try {
					d = sdf.parse("21/12/2015");
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				nutzer.setErstellungsZeitpunkt(d);
				
				PreparedStatement preStmt;
				preStmt=con.prepareStatement("INSERT INTO `nutzer`(`nutzerID`, `vorname`, `nachname`, `email`, `nickname`, `datum`) VALUES ( null,?,?,?,?,?)");
				//preStmt.setInt(1, newID);
				preStmt.setString(1, nutzer.getVorname());
				preStmt.setString(2, nutzer.getNachname());
				preStmt.setString(3, nutzer.getEmail());
				preStmt.setString(4, nutzer.getNickname());
				preStmt.setString(5, getSqlDateFormat(nutzer.getErstellungsZeitpunkt()));
				preStmt.executeUpdate();
				preStmt.close();
			}
			stmt.close();
			rs.close();
			//con.close();
		}
		catch(SQLException e){
			e.printStackTrace();
			throw new IllegalArgumentException("Datenbank fehler!" + e.toString());
		}
		return nutzer;
	}
	
	/**
	   * Wiederholtes Schreiben eines Objekts in die Datenbank.
	   * 
	   * @param unterhaltung das Objekt, das in die DB geschrieben werden soll
	   * @return das als Parameter �bergebene Objekt
	   */
	public Nutzer update(Nutzer nutzer) throws IllegalArgumentException{
		Connection con=DBConnection.connection();
		try{
			PreparedStatement preStmt;
			preStmt=con.prepareStatement("UPDATE nutzer SET vorname=?, "
					+ "nachname=?, email=?, nickname=?, erstellungsZeitpunkt=? WHERE nutzerID="+nutzer.getId());
			preStmt.setString(1, nutzer.getVorname());
			preStmt.setString(2, nutzer.getNachname());
			preStmt.setString(3, nutzer.getEmail());
			preStmt.setString(4, nutzer.getNickname());
			preStmt.setString(5, getSqlDateFormat(nutzer.getErstellungsZeitpunkt()));
			preStmt.executeUpdate();
			preStmt.close();
			con.close();
		}
		catch(SQLException e){
			e.printStackTrace();
			throw new IllegalArgumentException("Datenbank fehler!" + e.toString());
		}
		return nutzer;
	}
	
	/**
	   * L�schen der Daten eines <code>Unterhaltung</code>-Objekts aus der Datenbank.
	   * 
	   * @param a das aus der DB zu l�schende "Objekt"
	   */
	public void delete(Nutzer nutzer) throws IllegalArgumentException{
		Connection con =DBConnection.connection();
		try{
			Statement stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM nutzer WHERE userID="+nutzer.getId());
			stmt.close();
			con.close();
		}
		catch(SQLException e){
			e.printStackTrace();
			throw new IllegalArgumentException("Datenbank fehler!" + e.toString());
		}
	}
	
	/**
	 * Diese Methode ermöglicht es alle Nutzer aus der Datenbank in einer Liste zu finden und anzuzeigen.
	 * @return alleNutzer
	 */
	public ArrayList<Nutzer> findAllNutzer() throws IllegalArgumentException{
		Connection con=DBConnection.connection();
		ArrayList<Nutzer> alleNutzer = new ArrayList<Nutzer>();
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT userID, vorname, nachname, email, nickname, erstellungsZeitpunkt "
					+ "FROM nutzer ORDER BY userID");
		
			while(rs.next()){
				Nutzer nutzer = new Nutzer();
				nutzer.setId(rs.getInt("userID"));
				nutzer.setVorname(rs.getString("vorname"));
				nutzer.setNachname(rs.getString("nachname"));
				nutzer.setEmail(rs.getString("email"));
				nutzer.setNickname(rs.getString("nickname"));
				nutzer.setErstellungsZeitpunkt(rs.getDate("erstellungsZeitpunkt"));
			
				alleNutzer.add(nutzer);
			}
			stmt.close();
			rs.close();
			con.close();
		}
		catch(SQLException e){
			e.printStackTrace();
			throw new IllegalArgumentException("Datenbank fehler!" + e.toString());
		}
		return alleNutzer;
	}
	
	/**
	 * Diese Methode ermöglicht einen Nutzer anhand seines Nachnamens zu finden und anzuzeigen.
	 * @return uebergebener Paramater
	 */
	public Nutzer findNutzerByNachname(String nachname) throws IllegalArgumentException{
		Connection con=DBConnection.connection();
		
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM nutzer WHERE nachname='" + nachname + "' ORDER BY nachname");
			
			if(rs.next()){
				Nutzer nutzer=new Nutzer();
				nutzer.setId(rs.getInt("nutzerID"));
				nutzer.setVorname(rs.getString("vorname"));
				nutzer.setNachname(rs.getString("nachname"));
				nutzer.setEmail(rs.getString("email"));
				nutzer.setNickname(rs.getString("nickname"));
				nutzer.setErstellungsZeitpunkt(rs.getDate("datum"));
				
				return nutzer;
			}
		}
		catch(SQLException e){
			e.printStackTrace();
			throw new IllegalArgumentException("Datenbank fehler!" + e.toString());
		}
		return null;
	}
	
	/**
	 * Diese Methode ermöglicht einen Nutzer anhand des Prim�rschl�ssels zu finden und anzuzeigen.
	 * @return uebergebener Paramater
	 */
	public Nutzer findNutzerById(int userID) throws IllegalArgumentException{
		Connection con=DBConnection.connection();
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT userID, vorname, nachname, email, nickname, erstellungsZeitpunkt "
					+ "FROM nutzer WHERE userID="+userID+" ORDER BY userID");
			
			if(rs.next()){
				Nutzer nutzer=new Nutzer();
				nutzer.setId(rs.getInt("userID"));
				nutzer.setVorname(rs.getString("vorname"));
				nutzer.setNachname(rs.getString("nachname"));
				nutzer.setEmail(rs.getString("email"));
				nutzer.setNickname(rs.getString("nickname"));
				nutzer.setErstellungsZeitpunkt(rs.getDate("erstellungsZeitpunkt"));
				
				return nutzer;
			}
		}
		catch(SQLException e){
			e.printStackTrace();
			throw new IllegalArgumentException("Datenbank fehler!" + e.toString());
		}
		return null;
	}
	
	/**
	 * Diese Methode ermöglicht es alle Nachrichten eines Nutzers anhand des Prim�rschl�ssels zu finden und anzuzeigen.
	 * @return uebergebener Paramater
	 */
	public int countNachrichtenFromNutzer(Nutzer nutzer) throws IllegalArgumentException{
		Connection con=DBConnection.connection();
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT COUNT(userID) AS AnzahlNachrichten FROM nachrichten "+
			"WHERE userID="+nutzer.getId());
			
			return rs.getInt("AnzahlNachrichten");
		}
		catch(SQLException e){
			e.printStackTrace();
			throw new IllegalArgumentException("Datenbank fehler!" + e.toString());
		}
	}
	
	/**
	 * Diese Methode ermöglicht es alle Unterhaltungen eines Nutzers anhand des Prim�rschl�ssels zu finden und anzuzeigen.
	 * @return uebergebener Paramater
	 */
	public int countUnterhaltungenFromNutzer(Nutzer nutzer) throws IllegalArgumentException{
		Connection con=DBConnection.connection();
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT COUNT((absenderID)+(empfaengerID)) AS AnzahlUnterhaltungen FROM unterhaltungslisten "+
			"WHERE userID="+nutzer.getId());
			
			return rs.getInt("AnzahlUnterhaltungen");
		}
		catch(SQLException e){
			e.printStackTrace();
			throw new IllegalArgumentException("Datenbank fehler!" + e.toString());
		}
	}
	
	/**
	 * Diese Methode ermöglicht es alle HashtagAbos eines Nutzers anhand des Prim�rschl�ssels zu finden und anzuzeigen.
	 * @return uebergebener Paramater
	 */
	public int countHashtagAbosFromNutzer(Nutzer nutzer) throws IllegalArgumentException{
		Connection con=DBConnection.connection();
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT COUNT(hashtagAboID) AS AnzahlHashtagAbonnements FROM hashtagabonnements "+
			"WHERE hashtagAboID="+nutzer.getId());
			
			return rs.getInt("AnzahlHashtagAbonnements");
		}
		catch(SQLException e){
			e.printStackTrace();
			throw new IllegalArgumentException("Datenbank fehler!" + e.toString());
		}
	}
	
	/**
	 * Diese Methode ermöglicht es alle abonnierten Nutzer eines Nutzers anhand des Prim�rschl�ssels zu finden und anzuzeigen.
	 * @return uebergebener Paramater
	 */
	public int countAbonnierteNutzer(Nutzer nutzer) throws Exception{
		Connection con=DBConnection.connection();
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT COUNT(nutzerAboID) AS AnzahlAbonnierterNutzer FROM interessenprofile "+
			"WHERE userID="+nutzer.getId());
			
			return rs.getInt("AnzahlAbonnierterNutzer");
		}
		catch(SQLException e){
			e.printStackTrace();
			throw new Exception("Datenbank fehler!" + e.toString());
		}
	}
	
	/**
	 * Diese Methode ermöglicht es alle Follower eines Nutzers anhand des Prim�rschl�ssels zu finden und anzuzeigen.
	 * @return uebergebener Paramater
	 */
	public int countFollowerFromNutzer(Nutzer nutzer) throws Exception{
		Connection con=DBConnection.connection();
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT COUNT(nutzerAboID) AS AnzahlFollower FROM interessensprofile "+
			"WHERE nutzerAboID="+nutzer.getId());
			
			return rs.getInt("AnzahlFollower");
		}
		catch(SQLException e){
			e.printStackTrace();
			throw new Exception("Datenbank fehler!" + e.toString());
		}
	}
	
	/**
	 * Wandelt aus einem Date Objekt einen String in passendem SQL Übergabe
	 * Format.
	 * 
	 * @param date
	 *            Date das konvertiert werden soll
	 * @return String mit Date im Format yyyy-MM-dd HH:mm:ss
	 */
	private String getSqlDateFormat(Date date) {
		String result = "";
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		result = dateFormat.format(date);
		return result;
	}
}
