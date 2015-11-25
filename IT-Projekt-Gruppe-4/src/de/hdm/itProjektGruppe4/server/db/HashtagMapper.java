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
	
  /**
   * Diese Methode ermöglicht das Ausgeben der Hashtag anhand deren ID aus der Datenbank. 
   * @param id
   * @return
   */
	
  public Hashtag findHashtagByKey(int id){
		
	  Connection con = DBConnection.connection();
	  
	  try {
		  Statement stmt = con.createStatement();
		  
		  ResultSet rs = stmt.executeQuery("SELECT id, name FROM hashtag " 
		  + "WHERE id=" + id + " ORDER by name");
		  

		  if (rs.next()) {
			  Hashtag hashtag = new Hashtag();
			  hashtag.setId(rs.getInt("hashtag_id"));
			  hashtag.setBezeichnung(rs.getString("Bezeichnung"));
			  return hashtag;
		  }
	   }
	  catch (SQLException e2) {
		  e2.printStackTrace();
		  return null;
		  
	  }
	  return null;
	}
  
  
  /**
   * Diese Methode ermöglicht es alle Hashtag aus der datenbank in einer Liste auszugeben.
   * @return
   */
  public ArrayList<Hashtag> findAllHashtags() {
	    Connection con = DBConnection.connection();

	    ArrayList<Hashtag> result = new ArrayList<Hashtag>();

	    try {
	      Statement stmt = con.createStatement();

	      ResultSet rs = stmt.executeQuery("SELECT (hashtag_id, bezeichnung) FROM hashtag"
	          + " ORDER BY hashtag_id");

	      
	      while (rs.next()) {
	        Hashtag hashtag = new Hashtag();
	        hashtag.setId(rs.getInt("hashtag_id"));
	        hashtag.setBezeichnung(rs.getString("bezeichnung"));
	       

	        
	        result.add(hashtag);
	      }
	    }
	    catch (SQLException e2) {
	      e2.printStackTrace();
	    }

	    return result;
	  }
  
  /**
   * Diese Methode ermöglicht eine Aktualisierung des Hashtagdatensatzes in der Datenbank.
   * @param hashtag
   * @return
   */
  public Hashtag updateHashtag(Hashtag hashtag) {
	    Connection con = DBConnection.connection();

	    try {
	      Statement stmt = con.createStatement();

	      stmt.executeUpdate("UPDATE hashtag " + "SET bezeichnung=\"" + hashtag.getBezeichnung()
	          + "\" " + "WHERE hashtag_id=" + hashtag.getId());

	    }
	    catch (SQLException e2) {
	      e2.printStackTrace();
	    }

	    return hashtag;
	  }
  
  /**
   * Diese Methode ermöglicht es einen Hashtag in der Datenbank anzulegen.
   * @param hashtagBezeichnung
   * @return
   */
  
  public Hashtag insertHashtagBezeichnung(Hashtag hashtagBezeichnung) {
	    Connection con = DBConnection.connection();

	    try {
	      Statement stmt = con.createStatement();

	      ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
	          + "FROM nachricht ");

	      if (rs.next()) {
	  
	        hashtagBezeichnung.setId(rs.getInt("maxid") + 1);

	        stmt = con.createStatement();

	        stmt.executeUpdate("INSERT INTO hashtag (hashtag_id, bezeichnung) " + "VALUES ("
	            + hashtagBezeichnung.getId() + "," + hashtagBezeichnung.getBezeichnung() + ")");
	      }
	    }
	    catch (SQLException e2) {
	      e2.printStackTrace();
	    }

	    return hashtagBezeichnung;
	  }
  
  /**
   * Diese Methode ermöglicht das Löschen eines Nutzer und dessen Referenzen zu anderen Klassen.
   * @param hashtagBezeichnung
   */
  public void deleteHashtagBezeichnung(Hashtag hashtagBezeichnung) {
	    Connection con = DBConnection.connection();

	    try {
	      Statement stmt = con.createStatement();

	      stmt.executeUpdate("DELETE FROM hashtag " + "WHERE hashtag_id=" + hashtagBezeichnung.getId());

	    }
	    catch (SQLException e2) {
	      e2.printStackTrace();
	    }
	 }
}

