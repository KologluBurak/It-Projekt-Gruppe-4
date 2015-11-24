package de.hdm.itProjektGruppe4.server.db;

import java.sql.*;
import java.util.Vector;

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
	
  public Nutzer findByKey(int id){
	
	  Connection con = DBConnection.connection();
	  
	  try {
		  Statement stmt = con.createStatement();
		  
		  ResultSet rs = stmt.executeQuery("SELECT id, googleId FROM nutzer " 
		  + "WHERE id=" + id + " ORDER by googleId");
		  

		  if (rs.next()) {
			  Nutzer n = new Nutzer();
			  n.setId(rs.getInt("id"));
			  n.setGoogleId(rs.getString("googleId"));
			  return n;
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
	        Nutzer n = new Nutzer();
	        n.setVorname(rs.getString("vorname"));
	        n.setGoogleId(rs.getString("googleId"));
	        return n;
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
	        Nutzer n = new Nutzer();
	        n.setId(rs.getInt("id"));
	        n.setGoogleId(rs.getString("googleId"));

	        
	        result.addElement(n);
	      }
	    }
	    catch (SQLException e2) {
	      e2.printStackTrace();
	    }

	    return result;
	  }
  
  public Nutzer update(Nutzer n) {
	    Connection con = DBConnection.connection();

	    try {
	      Statement stmt = con.createStatement();

	      stmt.executeUpdate("UPDATE nutzer " + "SET googleId=\"" + n.getGoogleId()
	          + "\" " + "WHERE id=" + n.getId());

	    }
	    catch (SQLException e2) {
	      e2.printStackTrace();
	    }

	    return n;
	  }
  
  
  public Nutzer insert(Nutzer n) {
	    Connection con = DBConnection.connection();

	    try {
	      Statement stmt = con.createStatement();

	      ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
	          + "FROM nutzer ");

	      if (rs.next()) {
	  
	        n.setId(rs.getInt("maxid") + 1);

	        stmt = con.createStatement();

	        stmt.executeUpdate("INSERT INTO nutzer (id, googleid) " + "VALUES ("
	            + n.getId() + "," + n.getGoogleId() + ")");
	      }
	    }
	    catch (SQLException e2) {
	      e2.printStackTrace();
	    }

	    return n;
	  }
  
   public void delete(Nutzer n) {
	    Connection con = DBConnection.connection();

	    try {
	      Statement stmt = con.createStatement();

	      stmt.executeUpdate("DELETE FROM nutzer " + "WHERE id=" + n.getId());

	    }
	    catch (SQLException e2) {
	      e2.printStackTrace();
	    }
	 }
}
