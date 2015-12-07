package de.hdm.itProjektGruppe4.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;



import java.sql.PreparedStatement;
import de.hdm.itProjektGruppe4.shared.bo.*;



/**
 * Mapper-Klasse, die <code>Nachricht</code>-Objekte auf eine relationale
 * Datenbank abbildet. Hierzu wird eine Reihe von Methoden zur Verfügung
 * gestellt, mit deren Hilfe z.B. Objekte gesucht, erzeugt, modifiziert und
 * gelöscht werden können. Das Mapping ist bidirektional. D.h., Objekte können
 * in DB-Strukturen und DB-Strukturen in Objekte umgewandelt werden.
 * 
 * @author Kologlu
 * @author Oikonomou
 * @author Thies
 */
public class NachrichtMapper {
	
	/**
	   * Die Klasse NachrichtMapper wird nur einmal instantiiert. Man spricht hierbei
	   * von einem sogenannten <b>Singleton</b>.
	   * <p>
	   * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal für
	   * sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert die
	   * einzige Instanz dieser Klasse.
	   * 
	   * @see NachrichtMapper()
	   */
	private static NachrichtMapper nachrichtMapper = null;
	
	/**
	   * Geschützter Konstruktor - verhindert die Möglichkeit, mit <code>new</code>
	   * neue Instanzen dieser Klasse zu erzeugen.
	   */
	protected NachrichtMapper(){
		
	}
	
	/**
	   * Diese statische Methode kann aufgrufen werden durch
	   * <code>NachrichtMapper.nachrichtMapper()</code>. Sie stellt die
	   * Singleton-Eigenschaft sicher, indem Sie dafür sorgt, dass nur eine einzige
	   * Instanz von <code>NachrichtMapper</code> existiert.
	   * <p>
	   * 
	   * <b>Fazit:</b> NachrichtMapper sollte nicht mittels <code>new</code>
	   * instantiiert werden, sondern stets durch Aufruf dieser statischen Methode.
	   * 
	   * @return DAS <code>NachrichtMapper</code>-Objekt.
	   * @see nachrichtMapper
	   */
	public static NachrichtMapper nachrichtMapper(){
		if(nachrichtMapper==null) {
			nachrichtMapper= new NachrichtMapper();
		}
		return nachrichtMapper;
		
	}
	
	/**
	 * Diese Methode ermÃ¶glicht es eine Nachricht in der Datenbank anzulegen.
	 * @param nachricht
	 * @return
	 */
	public Nachricht insert(Nachricht nachricht) throws Exception{
		//DB-Verbindung herstellen
		Connection con=DBConnection.connection();
		try{
			//Insert-Statement erzeugen
			Statement stmt=con.createStatement();
			
			//Zunächst wird geschaut welches der momentan höchste Primärschlüssel ist
			ResultSet rs=stmt.executeQuery("SELECT MAX(nachrichtID) AS maxid FROM nachrichten");
			
			//Wenn Datensatz gefunden wurde, wird auf diesen zugegriffen
			if(rs.next()){
				int newID=rs.getInt("maxid");
				nachricht.setId(newID);
				
				PreparedStatement preStmt;
				preStmt=con.prepareStatement("INSERT INTO nachrichten "
						+"(nachrichtID, betreff, text, erstellungsZeitpunkt) VALUES(?, ?, ?, ?)");
				preStmt.setInt(1, newID);
				preStmt.setString(2, nachricht.getBetreff());
				preStmt.setString(3, nachricht.getText());
				preStmt.setString(4, getSqlDateFormat(nachricht.getErstellungsZeitpunkt()));
				preStmt.executeUpdate();
				preStmt.close();
			}
			stmt.close();
			rs.close();
			con.close();
		}
		catch(SQLException e){
			e.printStackTrace();
			throw new Exception("Datenbank fehler!" + e.toString());
		}
		return nachricht;
	}
	
	/**
	  * Diese Methode ermÃ¶glicht eine Akutalisierung des Unterhaltungsdatensatzes in der Datenbank.
	  * @param unterhaltung
	  * @return
	  */
	public Nachricht update(Nachricht nachricht) throws Exception{
		Connection con = DBConnection.connection();
		try{
			PreparedStatement preStmt;
			preStmt=con.prepareStatement("UPDATE nachrichten SET betreff=?, "
					+"text=?, erstellungsZeitpunkt=? WHERE nachrichtID=" +nachricht.getId());
			preStmt.setString(1, nachricht.getBetreff());
			preStmt.setString(2, nachricht.getText());
			preStmt.setString(3, getSqlDateFormat(nachricht.getErstellungsZeitpunkt()));
			preStmt.setInt(4, nachricht.getId());
			preStmt.executeUpdate();
			preStmt.close();
		}
		catch(SQLException e){
			e.printStackTrace();
			throw new Exception("Datenbank fehler!" + e.toString());
		}
		return nachricht;
	}
	
	/**
	   * Löschen der Daten eines <code>Unterhaltung</code>-Objekts aus der Datenbank.
	   * 
	   * @param nachricht das aus der DB zu löschende "Objekt"
	   */
	public void delete(Nachricht nachricht) throws Exception{
		Connection con = DBConnection.connection();
		try{
			Statement stmt = con.createStatement();
			
			// Statement ausfuellen und als Query an die DB schicken
			stmt.executeUpdate("DELETE FROM nachrichten WHERE nachricht_id="+nachricht.getId());
			stmt.close();
		}
		catch(SQLException e){
			e.printStackTrace();
			throw new Exception("Datenbank fehler!" + e.toString());
		}
	}
	
	/**
	 * Diese Methode ermÃ¶glicht es alle Nachrichten aus der Datenbank in einer Liste auszugeben.
	 * @return
	 */
	public ArrayList<Nachricht> findAllNachrichten() throws Exception{
		Connection con = DBConnection.connection();
		ArrayList<Nachricht> allNachrichten=new ArrayList<Nachricht>();
		try{
			Statement stmt=con.createStatement();
			ResultSet rs= stmt.executeQuery("SELECT * FROM nachrichten ORDER BY nachrichtID");
			
			while(rs.next()){
				Nachricht nachricht=new Nachricht();
				nachricht.setId(rs.getInt("nachrichtID"));
				nachricht.setBetreff(rs.getString("betreff"));
				nachricht.setText(rs.getString("text"));
				nachricht.setErstellungsZeitpunkt(rs.getDate("erstellungsZeitpunkt"));
				
				allNachrichten.add(nachricht);
			}
			stmt.close();
			rs.close();
			con.close();
		}
		catch(SQLException e){
			e.printStackTrace();
			throw new Exception("Datenbank fehler!" + e.toString());
		}
		return allNachrichten;
	}
	
	/**
	 * Diese Methode ermÃ¶glicht es alle Nachrichten einer Unterhaltung anhand ihrer ID zu finden und anzuzeigen.
	 * @param unterhaltung
	 * @return
	 */
	public ArrayList<Nachricht>findNachrichtenByUnterhaltung(Nachricht nachricht) throws Exception{
		Connection con=DBConnection.connection();
		ArrayList<Nachricht> result = new ArrayList<Nachricht>();
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT unterhaltungID, unterhaltungen.erstellungsZeitpunkt, nachrichten.betreff, nachrichten.text "
					+"nachrichten.erstellungsZeitpunkt FROM nachrichten INNER JOIN unterhaltungen "
					+"ON nachrichtID="+nachricht.getId()+" ON nachrichten.unterhaltungID=unterhaltung.unterhaltungID "
					+"ORDER BY unterhaltung.erstellungsZeitpunkt");
			
			while(rs.next()){
				nachricht.setId(rs.getInt(""));
				nachricht.setErstellungsZeitpunkt(rs.getDate("nachrichten.erstellungsZeitpunkt"));
				
				result.add(nachricht);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
			throw new Exception("Datenbank fehler!" + e.toString());
		}
		return result;
	}
	
  /**
   * Diese Methode ermÃ¶glicht es einen Nutzer anhand seiner ID das Auszugeben.
   * @param id
   * @return
   */
  public Nachricht findNachrichtById(int id){
	  Connection con = DBConnection.connection();
	  try {
		  Statement stmt = con.createStatement();
		  ResultSet rs = stmt.executeQuery("SELECT nachrichtID, betreff, text, erstellungsZeitpunkt FROM nachrichten " 
		  + "WHERE nachrichtID=" + id + " ORDER by nachrichtID");
		  

		  if (rs.next()) {
			  Nachricht nachricht = new Nachricht();
			  nachricht.setId(rs.getInt("nachrichtID"));
			  nachricht.setBetreff(rs.getString("betreff"));
			  nachricht.setText(rs.getString("text"));
			  nachricht.setErstellungsZeitpunkt(rs.getTimestamp("erstellungsZeitpunkt"));
			  return nachricht;
		  }
	   }
	  catch (SQLException e2) {
		  e2.printStackTrace();
		  return null;
		  
	  }
	  return null;
	}
 
  /**
   * Diese Methode ermÃ¶glicht es alle Nachricht eines Nutzers auszugeben.
   * @param nutzer
   * @param von
   * @param bis
   * @param sortierung
   * @return
   */
  
  public ArrayList<Nachricht> alleNachrichtenJeNutzer(Nutzer nutzer, String von, String bis) {
		Connection con = DBConnection.connection();
		ArrayList <Nachricht> nachrichtenJeNutzer= new ArrayList<Nachricht>();

		try{
			Statement stmt = con.createStatement();
			ResultSet rs=stmt.executeQuery("SELECT * FROM nachrichten WHERE nutzerID =" +nutzer.getId()+
					" AND Datum BETWEEN " + von + " AND " + bis + "");

			while (rs.next()) {
		        Nachricht nachricht = new Nachricht();
		        nachricht.setId(rs.getInt("nutzerID"));
		        nachricht.setBetreff(rs.getString("betreff"));
		        nachricht.setText(rs.getString("text"));
		        nachricht.setErstellungsZeitpunkt(rs.getTimestamp("erstellungsZeitpunkt"));
		    
		        nachrichtenJeNutzer.add(nachricht);
			}		
		}
		catch (SQLException e) {
	    	e.printStackTrace();
	    	return null;
		}
		return nachrichtenJeNutzer;
  }
  
  /**
   * Diese Methode ermÃ¶glicht es alle Nachrichten je Zeitraum auszugeben.
   * @param von
   * @param bis
   * @return
   */
  public ArrayList<Nachricht> alleNachrichtenJeZeitraum(String von, String bis) {
		Connection con = DBConnection.connection();
		ArrayList <Nachricht> nachrichtenJeZeitraum= new ArrayList<Nachricht>();
		
		try{
			Statement stmt = con.createStatement();
			ResultSet rs=stmt.executeQuery("SELECT * FROM Nachricht WHERE Datum BETWEEN " + von + " AND " + bis + "");

			while (rs.next()) {
		        Nachricht nachricht = new Nachricht();
		        nachricht.setId(rs.getInt("nachrichtID"));
		        nachricht.setBetreff(rs.getString("betreff"));
		        nachricht.setText(rs.getString("text"));
		        nachricht.setErstellungsZeitpunkt(rs.getTimestamp("erstellungsZeitpunkt"));
		      
		        nachrichtenJeZeitraum.add(nachricht);
			}		
		}
		catch (SQLException e) {
	    	e.printStackTrace();
	    	return null;
		}
		return nachrichtenJeZeitraum;
	 }
  
	/**
	 * Wandelt aus einem Date Objekt einen String in passendem SQL Ãœbergabe
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