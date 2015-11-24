package de.hdm.itProjektGruppe4.server.db;

import java.sql.*;
import java.util.Vector;

import de.hdm.itProjektGruppe4.shared.bo.Nutzer;



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
  * Suchen eines Kontos mit vorgegebener Kontonummer. Da diese eindeutig ist,
  * wird genau ein Objekt zur�ckgegeben.
  * 
  * @param id Primärschlüsselattribut (->DB)
  * @return Konto-Objekt, das dem übergebenen Schlüssel entspricht, null bei
  *         nicht vorhandenem DB-Tupel.
  */

  public Nutzer findByKey(int id){
	
	  Connection con = DBConnection.connection();
	  
	  try {
		  Statement stmt = con.createStatement();
		  
		  ResultSet rs = stmt.executeQuery("SELECT id, googleId FROM nutzer " 
		  + "WHERE id=" + id + " ORDER by googleId");
		  

		  if (rs.next()) {
			  Nutzer a = new Nutzer();
			  a.setId(rs.getInt("id"));
			  a.setGoogleId(rs.getString("googleId"));
			  return a;
		  }
	   }
	  catch (SQLException e2) {
		  e2.printStackTrace();
		  return null;
		  
	  }
	  return null;
	}
  
  public Nutzer findByVorname(int id) {
	    Connection con = DBConnection.connection();

	    try {
	    	
	      Statement stmt = con.createStatement();

	      ResultSet rs = stmt.executeQuery("SELECT id, googleId FROM nutzer "
	          + "WHERE id=" + id + " ORDER BY googleId");


	      if (rs.next()) {
	        Nutzer a = new Nutzer();
	        a.setVorname(rs.getString("vorname"));
	        a.setGoogleId(rs.getString("googleId"));
	        return a;
	      }
	    }
	    catch (SQLException e2) {
	      e2.printStackTrace();
	      return null;
	    }

	    return null;
	  }

  public Vector<Nutzer> findAll() {
	    Connection con = DBConnection.connection();

	    Vector<Nutzer> result = new Vector<Nutzer>();

	    try {
	      Statement stmt = con.createStatement();

	      ResultSet rs = stmt.executeQuery("SELECT id, googleId FROM nutzer "
	          + " ORDER BY id");

	      
	      while (rs.next()) {
	        Nutzer a = new Nutzer();
	        a.setId(rs.getInt("id"));
	        a.setGoogleId(rs.getString("googleId"));

	        
	        result.addElement(a);
	      }
	    }
	    catch (SQLException e2) {
	      e2.printStackTrace();
	    }

	    return result;
	  }
  
  public Nutzer update(Nutzer a) {
	    Connection con = DBConnection.connection();

	    try {
	      Statement stmt = con.createStatement();

	      stmt.executeUpdate("UPDATE nutzer " + "SET googleId=\"" + a.getGoogleId()
	          + "\" " + "WHERE id=" + a.getId());

	    }
	    catch (SQLException e2) {
	      e2.printStackTrace();
	    }

	    return a;
	  }
  
  
  public Nutzer insert(Nutzer a) {
	    Connection con = DBConnection.connection();

	    try {
	      Statement stmt = con.createStatement();

	      ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
	          + "FROM nutzer ");

	      if (rs.next()) {
	  
	        a.setId(rs.getInt("maxid") + 1);

	        stmt = con.createStatement();

	        stmt.executeUpdate("INSERT INTO accounts (id, owner) " + "VALUES ("
	            + a.getId() + "," + a.getGoogleId() + ")");
	      }
	    }
	    catch (SQLException e2) {
	      e2.printStackTrace();
	    }

	    return a;
	  }
  
  public void delete(Nutzer a) {
	    Connection con = DBConnection.connection();

	    try {
	      Statement stmt = con.createStatement();

	      stmt.executeUpdate("DELETE FROM nutzer " + "WHERE id=" + a.getId());

	    }
	    catch (SQLException e2) {
	      e2.printStackTrace();
	    }
	 }
}
