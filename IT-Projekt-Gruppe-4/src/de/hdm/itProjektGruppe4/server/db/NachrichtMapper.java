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
 * Datenbank abbildet. Hierzu wird eine Reihe von Methoden zur Verf�gung
 * gestellt, mit deren Hilfe z.B. Objekte gesucht, erzeugt, modifiziert und
 * gel�scht werden k�nnen. Das Mapping ist bidirektional. D.h., Objekte k�nnen
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
	   * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal f�r
	   * s�mtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert die
	   * einzige Instanz dieser Klasse.
	   * 
	   * @see NachrichtMapper()
	   */
	private static NachrichtMapper nachrichtMapper = null;
	
	/**
	   * Gesch�tzter Konstruktor - verhindert die M�glichkeit, mit <code>new</code>
	   * neue Instanzen dieser Klasse zu erzeugen.
	   */
	protected NachrichtMapper(){
		
	}
	
	/**
	   * Diese statische Methode kann aufgrufen werden durch
	   * <code>NachrichtMapper.nachrichtMapper()</code>. Sie stellt die
	   * Singleton-Eigenschaft sicher, indem Sie daf�r sorgt, dass nur eine einzige
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
	 * Diese Methode ermöglicht es eine Nachricht in der Datenbank anzulegen.
	 * @param nachricht
	 * @return
	 */
	public Nachricht insert(Nachricht nachricht){
		if (nachricht == null) {
			throw new IllegalArgumentException(
					"Übergebenes Objekt an insert() ist NULL.");
		}
		//DB-Verbindung herstellen
		Connection con=DBConnection.connection();
		try{
			//Insert-Statement erzeugen
			Statement stmt=con.createStatement();
			
			//Zun�chst wird geschaut welches der momentan h�chste Prim�rschl�ssel ist
			ResultSet rs=stmt.executeQuery("SELECT MAX(nachricht_id) AS maxID FROM nachrichten");
			
			//Wenn ein Datensatz gefunden wurde, wird auf diesen zugegriffen
			if(rs.next()){
				int newId = rs.getInt("maxID") + 1;
				nachricht.setNachrichtId(newId);
				
				PreparedStatement preStmt;
				preStmt=con.prepareStatement("INSERT INTO nachrichten "
							+"(nachricht_id, betreff, text, unterhaltung_id)"
							+" VALUES (?, ?, ?, ?, ?");
				
				preStmt.setInt(1, nachricht.getNachrichtId());
				preStmt.setString(2, nachricht.getBetreff());
				preStmt.setString(3, nachricht.getText());
				preStmt.setInt(4, nachricht.getId());
				preStmt.executeUpdate();
				preStmt.close();
			}
			rs.close();
			stmt.close();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return nachricht;
	}
	
	/**
	  * Diese Methode ermöglicht eine Akutalisierung des Unterhaltungsdatensatzes in der Datenbank.
	  * @param unterhaltung
	  * @return
	  */
	public Nachricht update(Nachricht nachricht){
		if(nachricht==null){
			throw new IllegalArgumentException(
					"Übergebenes Objekt an update() ist NULL.");
		}
		//DB-Verbindung herstellen
		Connection con = DBConnection.connection();
		try{
			PreparedStatement preStmt;
			preStmt=con.prepareStatement("UPDATE nachrichten SET betreff=?, "
						+"text=?, unterhaltung_id=? WHERE nachricht_id=?");
			
			preStmt.setString(1, nachricht.getBetreff());
			preStmt.setString(2, nachricht.getText());
			preStmt.setInt(3, nachricht.getId());
			preStmt.setInt(4, nachricht.getNachrichtId());
			preStmt.executeUpdate();
			preStmt.close();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return nachricht;
	}
	
	/**
	   * L�schen der Daten eines <code>Unterhaltung</code>-Objekts aus der Datenbank.
	   * 
	   * @param a das aus der DB zu l�schende "Objekt"
	   */
	public void delete(Nachricht nachricht){
		//DB-Verbindung holen
		Connection con = DBConnection.connection();
		
		try{
			Statement stmt = con.createStatement();
			
			// Statement ausfuellen und als Query an die DB schicken
			stmt.executeUpdate("DELETE FROM nachrichten WHERE nachricht_id="+nachricht.getNachrichtId());
			stmt.close();
			
			UnterhaltungMapper.unterhaltungMapper().delete(nachricht);
			
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	
	
	
	
}	
	
	
	
/*	
	
	
	
	
	
  /**
   * Diese Methode ermöglicht es einen Nutzer anhand seiner ID das Auszugeben.
   * @param id
   * @return
   */
  public Nachricht findNachrichtByKey(int id){
		
	  Connection con = DBConnection.connection();
	  
	  try {
		  Statement stmt = con.createStatement();
		  
		  ResultSet rs = stmt.executeQuery("SELECT id, text, betreff FROM nachricht " 
		  + "WHERE id=" + id + " ORDER by betreff");
		  

		  if (rs.next()) {
			  Nachricht nachricht = new Nachricht();
			  nachricht.setId(rs.getInt("nachricht_id"));
			  nachricht.setErstellungsZeitpunkt(rs.getTimestamp("datum"));
			  nachricht.setText(rs.getString("text"));
			  nachricht.setBetreff(rs.getString("betreff"));
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
   * Diese Methode ermöglicht es alle angelegten Nachrichten aus der Datenbank in einer Liste auszugeben.
   * @return
   */
  public ArrayList<Nachricht> findAllNachrichten(int id) {
	    Connection con = DBConnection.connection();
	    
	    ArrayList<Nachricht> result = new ArrayList<Nachricht>();

	    try {
	      Statement stmt = con.createStatement();

	      ResultSet rs = stmt.executeQuery("SELECT * FROM nachricht "
	          + " ORDER BY nachricht_id");

	      
	      while (rs.next()) {
	        Nachricht nachricht = new Nachricht();
	        nachricht.setId(rs.getInt("nachricht_id"));
	        nachricht.setErstellungsZeitpunkt(rs.getTimestamp("datum"));
	        nachricht.setText(rs.getString("text"));
	        nachricht.setBetreff(rs.getString("betreff"));

	        
	        result.add(nachricht);
	      }
	      
	    }
	    catch (SQLException e2) {
	      e2.printStackTrace();
	    }
	    
	    return result;
	  }
  
  /**
   * Diese Methode ermöglicht eine Akutalisierung des Nachrichtendatensatzes in der Datenbank
   * @param nachricht
   * @return
   */
  
  public Nachricht updateNachricht(Nachricht nachricht) {
	    Connection con = DBConnection.connection();

	    try {
	      Statement stmt = con.createStatement();

	      stmt.executeUpdate("UPDATE nachricht " + "SET Text=\"" + nachricht.getText()
	          + "\" " + "WHERE id=" + nachricht.getId());

	    }
	    catch (SQLException e2) {
	      e2.printStackTrace();
	    }
	    
	    DBConnection.closeConnection();
	    return nachricht;
	  }

  /**
   * Diese Methode ermöglicht es eine Nachricht in der Datenbank anzulegen.
   * @param nachricht
   * @return
   */
  public Nachricht insertNachricht(Nachricht nachricht) {
	    Connection con = DBConnection.connection();

	    try {
	      Statement stmt = con.createStatement();

	      ResultSet rs = stmt.executeQuery("SELECT MAX(nachricht_id) AS maxid "
	          + "FROM nachricht ");

	      if (rs.next()) {
	  
	        nachricht.setId(rs.getInt("maxid") + 1);
	        nachricht.setErstellungsZeitpunkt(rs.getTimestamp("datum"));

	        stmt = con.createStatement();

	        stmt.executeUpdate("INSERT INTO nachricht (nachricht_id, text, betreff, datum) " 
	        + "VALUES (" + nachricht.getId() + "," + nachricht.getText() + "," + nachricht.getErstellungsZeitpunkt() + "");
	      }
	    }
	    catch (SQLException e2) {
	      e2.printStackTrace();
	    }

	    return nachricht;
	  }
  
  /**
   * Diese Methode ermöglicht das editieren einer Nachricht aus der Datenbank.
   * @param nachricht
   * @return
   */
  
  public Nachricht editNachricht(Nachricht nachricht){

		Connection con = DBConnection.connection();

	    try {
	      Statement stmt = con.createStatement();

	      stmt.executeUpdate("UPDATE Kommentar SET Text=\"" + nachricht.getText() + "\" WHERE Kommentar_ID= " + nachricht.getId());


	    }
	    catch (SQLException e) {
	      e.printStackTrace();
	    }

	    return nachricht;
	}
  
  /**
   * Diese Methode ermöglicht es eine Nachricht aus der Datenbank zu löschen.
   * @param nachricht
   */
  
  public void deleteNachricht(Nachricht nachricht) {
	    Connection con = DBConnection.connection();

	    try {
	      Statement stmt = con.createStatement();

	      stmt.executeUpdate("DELETE FROM nachricht " + "WHERE nachricht_id=" + nachricht.getId());

	    }
	    catch (SQLException e2) {
	      e2.printStackTrace();
	    }
	    return;
  }
  
  /**
   * Diese Methode ermöglicht es alle Nachricht eines Nutzers auszugeben.
   * @param nutzer
   * @param von
   * @param bis
   * @param sortierung
   * @return
   */
  
  public ArrayList<Nachricht> alleNachrichtenJeNutzer(Nutzer nutzer, String von, String bis, int id) {
		Connection con = DBConnection.connection();
		ArrayList <Nachricht> nachrichtenJeNutzer= new ArrayList<Nachricht>();

		try{
			Statement stmt = con.createStatement();
			String sql = "SELECT * FROM Nachricht WHERE nutzer_ID =" + nutzer.getId() + " AND Datum between " + von + " AND " + bis + "";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				
		        Nachricht nachricht = new Nachricht();
		        nachricht.setId(rs.getInt("nutzer_ID"));
		        nachricht.setErstellungsZeitpunkt(rs.getTimestamp("datum"));
		        nachricht.setText(rs.getString("Text"));
		    
		        nachrichtenJeNutzer.add(nachricht);
			}
			return nachrichtenJeNutzer;		
		}
		   catch (SQLException e) {
	    		e.printStackTrace();
	    		return null;
		    }
  
  }
  
  /**
   * Diese Methode ermöglicht es alle Nachrichten je Zeitraum auszugeben.
   * @param von
   * @param bis
   * @return
   */
  public ArrayList<Nachricht> alleNachrichtenJeZeitraum(String von, String bis, int id) {
		Connection con = DBConnection.connection();
		ArrayList <Nachricht> nachrichtenJeZeitraum= new ArrayList<Nachricht>();
		
		
		try{
			Statement stmt = con.createStatement();
			String sql = "SELECT * FROM Nachricht WHERE Datum between " + von + " AND " + bis + "";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
		        Nachricht nachricht = new Nachricht();
		        nachricht.setId(rs.getInt("nachricht_ID"));
		        nachricht.setErstellungsZeitpunkt(rs.getTimestamp("Datum"));
		        nachricht.setText(rs.getString("Text"));
		      
		        nachrichtenJeZeitraum.add(nachricht);
			}
			return nachrichtenJeZeitraum;		
		}
		   catch (SQLException e) {
	    		e.printStackTrace();
	    		return null;
		    }				
	 }


