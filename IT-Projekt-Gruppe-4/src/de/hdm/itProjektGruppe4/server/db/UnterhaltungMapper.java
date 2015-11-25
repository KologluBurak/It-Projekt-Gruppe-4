package de.hdm.itProjektGruppe4.server.db;

import java.sql.*;

import java.util.ArrayList;
import de.hdm.itProjektGruppe4.shared.bo.*;


/**
 * @author Kologlu
 * @author Oikonomou
 * @author Thies
 *
 */


public class UnterhaltungMapper {
	
	private static UnterhaltungMapper unterhaltungMapper=null;
	
	protected UnterhaltungMapper() {
	} 
	
	public static UnterhaltungMapper unterhaltungMapper() {
	    if (unterhaltungMapper == null) {
	      unterhaltungMapper = new UnterhaltungMapper();
	    }
	    return unterhaltungMapper;
	  }
	
	
	/**
	 * Diese Methode ermöglicht es eine Unterhaltung anhand ihrer ID das Auszugeben.
	 * @param id
	 * @return
	 */
	
	 public Unterhaltung findUnterhaltungByKey(int id) {
		    Connection con = DBConnection.connection();
		    try {
		      Statement stmt = con.createStatement();
		      ResultSet rs = stmt.executeQuery("SELECT (unterhaltung_id, sender, receiver) FROM unterhaltung "
		          + "WHERE unterhaltung_id=" + id + " ORDER BY receiver");
		      if (rs.next()) {
		        Unterhaltung unterhaltung = new Unterhaltung();
		        unterhaltung.setId(rs.getInt("id"));
		        return unterhaltung;
		      }
		    }
		    catch (SQLException e2) {
		      e2.printStackTrace();
		      return null;
		    }

		    return null;
		  }
	 
	/**
	 * Diese Methode ermöglicht es alle Unterhaltungen aus der Datenbank in einer Liste auszugeben.
	 * @return
	 */
	
	public ArrayList<Unterhaltung> findAllUnterhaltungen() {
		    Connection con = DBConnection.connection();
		    ArrayList<Unterhaltung> unterhaltungListe = new ArrayList<Unterhaltung>();

		    try {
		      Statement stmt = con.createStatement();
		      ResultSet rs = stmt.executeQuery("SELECT id FROM unterhaltung "
		          + " ORDER BY id");
		      while (rs.next()) {
		        Unterhaltung unterhaltung = new Unterhaltung();
		        unterhaltung.setId(rs.getInt("id"));
		        unterhaltungListe.add(unterhaltung);
		      }
		    }
		    catch (SQLException e2) {
		      e2.printStackTrace();
		    }
		    return unterhaltungListe;
		  }
	
	/**
	 * Diese Methode ermöglicht es eine Unterhaltung in der Datenbank anzulegen.
	 * @param unterhaltung
	 * @return
	 */
	 
	 public Unterhaltung insertUnterhaltung(Unterhaltung unterhaltung) {
		    Connection con = DBConnection.connection();

		    try {
		      Statement stmt = con.createStatement();
		      ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
		          + "FROM unterhaltung ");

		      if (rs.next()) {
		        unterhaltung.setId(rs.getInt("maxid") + 1);
		        unterhaltung.setErstellungsZeitpunkt(rs.getTimestamp("datum"));
		        stmt = con.createStatement();
		        stmt.executeUpdate("INSERT INTO unterhaltung (id, sender, receiver) " + "VALUES ("
		            + unterhaltung.getId() + "," + unterhaltung.getSender() + "," + unterhaltung.getReceiver() + ")") ;
		      }
		    }
		    catch (SQLException e2) {
		      e2.printStackTrace();
		    }
		    return unterhaltung;
	  }
	 
	 /**
	  * Diese Methode ermöglicht eine Akutalisierung des Unterhaltungsdatensatzes in der Datenbank.
	  * @param unterhaltung
	  * @return
	  */
	 
	 public Unterhaltung updateUnterhaltung(Unterhaltung unterhaltung){
		 Connection con = DBConnection.connection();
		    try {
		      Statement stmt = con.createStatement();
		      stmt.executeUpdate("UPDATE unterhaltung SET sender= "+ unterhaltung.getSender() + "," + "receiver=" + unterhaltung.getReceiver() + "," + "lastEdited=" + unterhaltung.getLastEdited()
		          + "," + "WHERE unterhaltung_id=" + unterhaltung.getId()); 
		    }
		    catch (SQLException e2) {
		      e2.printStackTrace();
		    }
		    return unterhaltung;
	 }
	 
	 /**
	  * Diese Methode ermöglicht es eine Unterhaltung aus der Datenbank zu löschen.
	  * @param unterhaltung
	  */
	 
	 public void deleteUnterhaltung(Unterhaltung unterhaltung) {
		    Connection con = DBConnection.connection();
		    
		    try {
		      
		    	Statement stmt = con.createStatement();
		      
		    	stmt.executeUpdate("DELETE FROM Unterhaltung " + "WHERE unterhaltung_id=" + unterhaltung.getId());
		    }
		    
		    catch (SQLException e2) {
		      e2.printStackTrace();
		    }
		    return;
		  }
}
