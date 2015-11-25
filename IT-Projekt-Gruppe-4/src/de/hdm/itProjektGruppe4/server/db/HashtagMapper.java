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

public class HashtagMapper {
	 
	private static HashtagMapper hashtagMapper = null;
	
	protected HashtagMapper(){
		
	}
	
	public static HashtagMapper hashtagMapper(){
		if(hashtagMapper==null) {
			hashtagMapper= new HashtagMapper();
		}
		return hashtagMapper;
		
	}
	
  
  public Hashtag findByKey(int id){
		
	  Connection con = DBConnection.connection();
	  
	  try {
		  Statement stmt = con.createStatement();
		  
		  ResultSet rs = stmt.executeQuery("SELECT id, name FROM hashtag " 
		  + "WHERE id=" + id + " ORDER by name");
		  

		  if (rs.next()) {
			  Hashtag h = new Hashtag();
			  h.setId(rs.getInt("id"));
			  h.setName(rs.getString("name"));
			  return h;
		  }
	   }
	  catch (SQLException e2) {
		  e2.printStackTrace();
		  return null;
		  
	  }
	  return null;
	}
  
  public Vector<Hashtag> findAll() {
	    Connection con = DBConnection.connection();

	    Vector<Hashtag> result = new Vector<Hashtag>();

	    try {
	      Statement stmt = con.createStatement();

	      ResultSet rs = stmt.executeQuery("SELECT id, name, FROM hashtag"
	          + " ORDER BY id");

	      
	      while (rs.next()) {
	        Hashtag h = new Hashtag();
	        h.setId(rs.getInt("id"));
	        h.setName(rs.getString("name"));
	       

	        
	        result.addElement(h);
	      }
	    }
	    catch (SQLException e2) {
	      e2.printStackTrace();
	    }

	    return result;
	  }
  
  public Hashtag update(Hashtag h) {
	    Connection con = DBConnection.connection();

	    try {
	      Statement stmt = con.createStatement();

	      stmt.executeUpdate("UPDATE hashtag " + "SET name=\"" + h.getName()
	          + "\" " + "WHERE id=" + h.getId());

	    }
	    catch (SQLException e2) {
	      e2.printStackTrace();
	    }

	    return h;
	  }
  
  
  public Hashtag insert(Hashtag h) {
	    Connection con = DBConnection.connection();

	    try {
	      Statement stmt = con.createStatement();

	      ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
	          + "FROM nachricht ");

	      if (rs.next()) {
	  
	        h.setId(rs.getInt("maxid") + 1);

	        stmt = con.createStatement();

	        stmt.executeUpdate("INSERT INTO hashtag (id, name) " + "VALUES ("
	            + h.getId() + "," + h.getName() + ")");
	      }
	    }
	    catch (SQLException e2) {
	      e2.printStackTrace();
	    }

	    return h;
	  }
  
  public void delete(Hashtag h) {
	    Connection con = DBConnection.connection();

	    try {
	      Statement stmt = con.createStatement();

	      stmt.executeUpdate("DELETE FROM hashtag " + "WHERE id=" + h.getId());

	    }
	    catch (SQLException e2) {
	      e2.printStackTrace();
	    }
	 }
}

