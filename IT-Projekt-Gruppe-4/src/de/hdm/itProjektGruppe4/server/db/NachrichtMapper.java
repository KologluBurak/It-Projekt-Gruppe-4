package de.hdm.itProjektGruppe4.server.db;

import java.sql.*;
import java.util.Vector;

import de.hdm.itProjektGruppe4.shared.bo.Nachricht;



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
	
  
  public Nachricht findByKey(int id){
		
	  Connection con = DBConnection.connection();
	  
	  try {
		  Statement stmt = con.createStatement();
		  
		  ResultSet rs = stmt.executeQuery("SELECT id, text, betreff FROM nachricht " 
		  + "WHERE id=" + id + " ORDER by betreff");
		  

		  if (rs.next()) {
			  Nachricht n = new Nachricht();
			  n.setId(rs.getInt("id"));
			  n.setText(rs.getString("text"));
			  n.setBetreff(rs.getString("betreff"));
			  return n;
		  }
	   }
	  catch (SQLException e2) {
		  e2.printStackTrace();
		  return null;
		  
	  }
	  return null;
	}
  
  public Vector<Nachricht> findAll() {
	    Connection con = DBConnection.connection();

	    Vector<Nachricht> result = new Vector<Nachricht>();

	    try {
	      Statement stmt = con.createStatement();

	      ResultSet rs = stmt.executeQuery("SELECT id, text, betreff FROM nachricht "
	          + " ORDER BY id");

	      
	      while (rs.next()) {
	        Nachricht n = new Nachricht();
	        n.setId(rs.getInt("id"));
	        n.setText(rs.getString("text"));
	        n.setBetreff(rs.getString("betreff"));

	        
	        result.addElement(n);
	      }
	    }
	    catch (SQLException e2) {
	      e2.printStackTrace();
	    }

	    
	    
	    
	    return result;
	  }
  
  public Nachricht update(Nachricht n) {
	    Connection con = DBConnection.connection();

	    try {
	      Statement stmt = con.createStatement();

	      stmt.executeUpdate("UPDATE nachricht " + "SET text=\"" + n.getText()
	          + "\" " + "WHERE id=" + n.getId());

	    }
	    catch (SQLException e2) {
	      e2.printStackTrace();
	    }
	    
	    DBConnection.closeCon();
	    return n;
	  }

  public Nachricht insert(Nachricht n) {
	    Connection con = DBConnection.connection();

	    try {
	      Statement stmt = con.createStatement();

	      ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
	          + "FROM nachricht ");

	      if (rs.next()) {
	  
	        n.setId(rs.getInt("maxid") + 1);

	        stmt = con.createStatement();

	        stmt.executeUpdate("INSERT INTO nachricht (id, text, betreff) " + "VALUES ("
	            + n.getId() + "," + n.getText() + "," + n.getBetreff() + ")");
	      }
	    }
	    catch (SQLException e2) {
	      e2.printStackTrace();
	    }

	    return n;
	  }
  
  public void delete(Nachricht n) {
	    Connection con = DBConnection.connection();

	    try {
	      Statement stmt = con.createStatement();

	      stmt.executeUpdate("DELETE FROM nachricht " + "WHERE id=" + n.getId());

	    }
	    catch (SQLException e2) {
	      e2.printStackTrace();
	    }
	 }
}

