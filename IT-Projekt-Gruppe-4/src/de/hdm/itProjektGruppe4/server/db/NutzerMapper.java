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

public class NutzerMapper {
	 
	private static NutzerMapper nutzerMapper = null;
	
	protected NutzerMapper(){
		
	}
	
	public static NutzerMapper nutzerMapper(){
		if(nutzerMapper==null) {
			nutzerMapper= new NutzerMapper();
		}
		return nutzerMapper;
		
	}
	
/**
 * Diese Methode ermöglicht das Ausgeben der Nutzer anhand deren ID aus der Datenbank.
 * @param id
 * @return
 */
	
  public Nutzer findNutzerByKey(int id){
	
	  Connection con = DBConnection.connection();
	  
	  try {
		  Statement stmt = con.createStatement();
		  
		  ResultSet rs = stmt.executeQuery("SELECT id, googleId FROM nutzer " 
		  + "WHERE id=" + id + " ORDER by googleId");
		  

		  if (rs.next()) {
			  Nutzer nutzer = new Nutzer();
			  nutzer.setId(rs.getInt("nutzerId"));
			  nutzer.setGoogleId(rs.getString("googleId"));
			  return nutzer;
		  }
	   }
	  catch (SQLException e2) {
		  e2.printStackTrace();
		  return null;
		  
	  }
	  return null;
	}
  
  /**
   * Diese Methode gibt die registrierten Nutzer in der Datenbank anhand dem Vornamen aus. 
   * @param vorname
   * @return
   */
  
  public Nutzer findNutzerByVorname(String vorname) {
	    Connection con = DBConnection.connection();

	    try {
	    	
	      Statement stmt = con.createStatement();

	      ResultSet rs = stmt.executeQuery("SELECT * FROM Nutzer "
	          + "WHERE Vorname=" + vorname + " ORDER BY id");


	      if (rs.next()) {
	        Nutzer nutzer = new Nutzer();
	        nutzer.setId(rs.getInt("nutzer_id"));
	        nutzer.setVorname(rs.getString("vorname"));
	        nutzer.setGoogleId(rs.getString("googleId"));
	        return nutzer;
	      }
	    }
	    catch (SQLException e2) {
	      e2.printStackTrace();
	      return null;
	    }

	    return null;
	  }
  
  /**d
   * Diese Methode gibt die registrierten Nutzer aus der Datenbank anhand dem Nachname aus. 
   * @param nachname
   * @return
   */
  public Nutzer findNutzerByNachname(String nachname) {
	    Connection con = DBConnection.connection();

	    try {
	    	
	      Statement stmt = con.createStatement();

	      ResultSet rs = stmt.executeQuery("SELECT * FROM Nutzer "
		          + "WHERE Nachname=" + nachname + " ORDER BY id");



	      if (rs.next()) {
	    	  
	        Nutzer nutzer = new Nutzer();
	        nutzer.setId(rs.getInt("nutzer_id"));
	        nutzer.setNachname(rs.getString("nachname"));
	        nutzer.setGoogleId(rs.getString("googleId"));
	        return nutzer;
	      }
	    }
	    catch (SQLException e2) {
	      e2.printStackTrace();
	      return null;
	    }

	    return null;
	  }

  /**
   * Diese Methode ermöglicht es alle registrierten Nutzer aus der datenbank in einer Liste auszugeben.
   * @return
   */
  public ArrayList<Nutzer> findAllNutzer() {
	    Connection con = DBConnection.connection();

	    ArrayList<Nutzer> allNutzer = new ArrayList<Nutzer>();

	    try {
	      Statement stmt = con.createStatement();

	      ResultSet rs = stmt.executeQuery("SELECT id * FROM nutzer "
	          + " ORDER BY id");

	      
	      while (rs.next()) {
	        Nutzer nutzer = new Nutzer();
	        nutzer.setId(rs.getInt("nutzer_id"));
	        nutzer.setErstellungsZeitpunkt(rs.getTimestamp("datum"));
	        nutzer.setVorname(rs.getString("vorname"));
	        nutzer.setNachname(rs.getString("nachname"));
	        nutzer.setPasswort(rs.getString("passwort"));
	        nutzer.setGoogleId(rs.getString("googleId"));

	        
	        allNutzer.add(nutzer);
	      }
	      
	      return allNutzer;
	    }
	    catch (SQLException e2) {
	      e2.printStackTrace();
	    }

	    return allNutzer;

  }
  
  
  /**
   * Diese Methode ermöglicht eine Aktualisierung des Nutzerdatensatzes in der Datenbank.
   * @param n
   * @return
   */
  public Nutzer updateNutzer(Nutzer nutzer) {
	    Connection con = DBConnection.connection();

	    try {
	      Statement stmt = con.createStatement();

	      stmt.executeUpdate("UPDATE nutzer " + "SET vorname=\"" + nutzer.getVorname() + "\", " + "nachname=\"" + nutzer.getNachname() +
	    		  "\", " + "googleId=\"" + nutzer.getGoogleId() + "\" " + "WHERE nutzer_id=" + nutzer.getId());

	    }
	    catch (SQLException e2) {
	      e2.printStackTrace();
	    }

	    return nutzer;
	  }
  
  /**
   * Diese Methode ermöglicht es einen Nutzer in der Datenbank anzulegen.
   * @param n
   * @return
   */
  
  public Nutzer insertNutzer(Nutzer nutzer) {
	    Connection con = DBConnection.connection();

	    try {
	      Statement stmt = con.createStatement();

	      ResultSet rs = stmt.executeQuery("SELECT MAX(nutzer_id) AS maxid "
	          + "FROM Nutzer ");

	      if (rs.next()) {
	  
	        nutzer.setId(rs.getInt("maxid") + 1);
	        nutzer.setErstellungsZeitpunkt(rs.getTimestamp("datum"));

	        stmt = con.createStatement();

	        stmt.executeUpdate("INSERT INTO Nutzer " + "VALUES ("
	            + nutzer.getId() + "," + nutzer.getVorname() + nutzer.getNachname() + "," + nutzer.getPasswort() + "," + nutzer.getGoogleId() + ")");
	      }
	    }
	    catch (SQLException e2) {
	      e2.printStackTrace();
	    }

	    return nutzer;
	  }
  
  /**
   * Diese Methode ermöglicht das Löschen eines Nutzer und dessen Referenzen zu anderen Klassen
   * @param n
   */
   public void deleteNutzer(Nutzer nutzer) {
	    Connection con = DBConnection.connection();

	    try {
	      Statement stmt = con.createStatement();

	      stmt.executeUpdate("DELETE FROM Abonnement " + "WHERE nutzer_id=" + nutzer.getId());
	      stmt.executeUpdate("DELETE FROM Hashtag " + "WHERE nutzer_id=" + nutzer.getId());
	      stmt.executeUpdate("DELETE FROM Nachricht " + "WHERE nutzer_id=" + nutzer.getId());
	      stmt.executeUpdate("DELETE FROM Unterhaltung " + "WHERE nutzer_id=" + nutzer.getId());
	      stmt.executeUpdate("DELETE FROM Nutzer " + "WHERE nutzer_id=" + nutzer.getId());


	    }
	    catch (SQLException e2) {
	      e2.printStackTrace();
	    }
	 }
}
