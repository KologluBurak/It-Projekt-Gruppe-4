package de.hdm.itProjektGruppe4.server.db;

import java.sql.*;
import java.util.ArrayList;

import de.hdm.itProjektGruppe4.shared.bo.*;



/**
 * 
 * @author Oikonomou
 * @author Thies
 *
 */

public class NachrichtMapper {
	
	private static NachrichtMapper nachrichtMapper = null;
	
	protected NachrichtMapper(){
		
	}
	
	public static NachrichtMapper nachrichtMapper(){
		if(nachrichtMapper==null) {
			nachrichtMapper= new NachrichtMapper();
		}
		return nachrichtMapper;
		
	}
	
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
}

