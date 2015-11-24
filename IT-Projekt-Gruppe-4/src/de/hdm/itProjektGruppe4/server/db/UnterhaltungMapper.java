package de.hdm.itProjektGruppe4.server.db;

import java.sql.*;
import java.util.Vector;

import de.hdm.itProjektGruppe4.shared.bo.*;

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
	
	 public Unterhaltung findByKey(int id) {
		    Connection con = DBConnection.connection();
		    try {
		      Statement stmt = con.createStatement();
		      ResultSet rs = stmt.executeQuery("SELECT id, sender, receiver FROM Unterhaltung "
		          + "WHERE id=" + id + " ORDER BY receiver");
		      if (rs.next()) {
		        Unterhaltung u = new Unterhaltung();
		        u.setId(rs.getInt("id"));
		        return u;
		      }
		    }
		    catch (SQLException e2) {
		      e2.printStackTrace();
		      return null;
		    }

		    return null;
		  }
	
	public Vector<Unterhaltung> findAll() {
		    Connection con = DBConnection.connection();
		    Vector<Unterhaltung> result = new Vector<Unterhaltung>();

		    try {
		      Statement stmt = con.createStatement();
		      ResultSet rs = stmt.executeQuery("SELECT id FROM Unterhaltung "
		          + " ORDER BY id");
		      while (rs.next()) {
		        Unterhaltung u = new Unterhaltung();
		        u.setId(rs.getInt("id"));
		        result.addElement(u);
		      }
		    }
		    catch (SQLException e2) {
		      e2.printStackTrace();
		    }
		    return result;
		  }
	 
	 public Unterhaltung insert(Unterhaltung u) {
		    Connection con = DBConnection.connection();

		    try {
		      Statement stmt = con.createStatement();
		      ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
		          + "FROM Unterhaltung ");

		      if (rs.next()) {
		        u.setId(rs.getInt("maxid") + 1);
		        stmt = con.createStatement();
		        stmt.executeUpdate("INSERT INTO Unterhaltung (id, sender, receiver) " + "VALUES ("
		            + u.getId() + "," + u.getSender() + "," + u.getReceiver() + ")") ;
		      }
		    }
		    catch (SQLException e2) {
		      e2.printStackTrace();
		    }
		    return u;
	  }
	 
	 public Unterhaltung update(Unterhaltung u){
		 Connection con = DBConnection.connection();
		    try {
		      Statement stmt = con.createStatement();
		      stmt.executeUpdate("UPDATE Unterhaltung SET " + "sender="
		          + u.getSender() + "," + "receiver=" + u.getReceiver() + "," + "lastEdited=" + u.getLastEdited()
		          + "," + "WHERE id=" + u.getId()); 
		    }
		    catch (SQLException e2) {
		      e2.printStackTrace();
		    }
		    return u;
	 }
	 
	 public void delete(Unterhaltung u) {
		    Connection con = DBConnection.connection();
		    try {
		      Statement stmt = con.createStatement();
		      stmt.executeUpdate("DELETE FROM Unterhaltung " + "WHERE id=" + u.getId());
		    }
		    catch (SQLException e2) {
		      e2.printStackTrace();
		    }
		  }
	 
	 public void deleteChatOfSender(Unterhaltung u) {
		    Connection con = DBConnection.connection();
		    try {
		      Statement stmt = con.createStatement();
		      stmt.executeUpdate("DELETE sender FROM Unterhaltung " + "WHERE sender=" + u.getId());
		      
		    }
		    catch (SQLException e2) {
		      e2.printStackTrace();
		    }
	 }
	 
	 public void deleteChatOfReceiver(Unterhaltung u){
		 	Connection con = DBConnection.connection();
		 	try {
		 		Statement stmt = con.createStatement();
		 		stmt.executeUpdate("DELETE receiver FROM Unterhaltung " + "WHERE receiver=" + u.getId());
		 		
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
	 }
}
