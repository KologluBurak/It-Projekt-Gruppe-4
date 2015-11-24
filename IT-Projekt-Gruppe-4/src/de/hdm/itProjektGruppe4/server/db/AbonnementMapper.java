package de.hdm.itProjektGruppe4.server.db;

import java.sql.*;
import java.util.Vector;

import de.hdm.itProjektGruppe4.shared.bo.Abonnement;

	
	/**
	 * 
	 * @author Oikonomou
	 * @author Thies
	 *
	 */

	public class AbonnementMapper {
		 
		private static AbonnementMapper abonnementMapper = null;
		
		protected AbonnementMapper(){
			
		}
		
		public static AbonnementMapper abonnementMapper(){
			if(abonnementMapper==null) {
				abonnementMapper= new AbonnementMapper();
			}
			return abonnementMapper;
			
		}
		

	  public Abonnement findByKey(int id){
		
		  Connection con = DBConnection.connection();
		  
		  try {
			  Statement stmt = con.createStatement();
			  
			  ResultSet rs = stmt.executeQuery("SELECT id FROM abonnement " 
			  + "WHERE id=" + id + " ORDER by aboArt");
			  

			  if (rs.next()) {
				  Abonnement a = new Abonnement();
				  a.setId(rs.getInt("id"));
				  a.setAboArt(rs.getString("aboArt"));
				  return a;
			  }
		   }
		  catch (SQLException e2) {
			  e2.printStackTrace();
			  return null;
			  
		  }
		  return null;
		}
	  
	  public Abonnement findByAbo(int id) {
		    Connection con = DBConnection.connection();

		    try {
		    	
		      Statement stmt = con.createStatement();

		      ResultSet rs = stmt.executeQuery("SELECT id FROM abonnement "
		          + "WHERE id=" + id + " ORDER BY aboArt");


		      if (rs.next()) {
		        Abonnement a = new Abonnement();
				a.setId(rs.getInt("id"));
		        a.setAboArt(rs.getString("aboArt"));
		        return a;
		      }
		    }
		    catch (SQLException e2) {
		      e2.printStackTrace();
		      return null;
		    }

		    return null;
		  }

	  public Vector<Abonnement> findAll() {
		    Connection con = DBConnection.connection();

		    Vector<Abonnement> result = new Vector<Abonnement>();

		    try {
		      Statement stmt = con.createStatement();

		      ResultSet rs = stmt.executeQuery("SELECT id FROM abonnement "
		          + " ORDER BY id");

		      
		      while (rs.next()) {
		        Abonnement a = new Abonnement();
		        a.setId(rs.getInt("id"));
		        a.setAboArt(rs.getString("aboArt"));

		        
		        result.addElement(a);
		      }
		    }
		    catch (SQLException e2) {
		      e2.printStackTrace();
		    }

		    return result;
		  }
	  
	  public Abonnement update(Abonnement a) {
		    Connection con = DBConnection.connection();

		    try {
		      Statement stmt = con.createStatement();

		      stmt.executeUpdate("UPDATE abonnement " + "SET aboArt=\"" + a.getAboArt()
		          + "\" " + "WHERE id=" + a.getId());

		    }
		    catch (SQLException e2) {
		      e2.printStackTrace();
		    }

		    return a;
		  }
	  
	  
	  public Abonnement insert(Abonnement a) {
		    Connection con = DBConnection.connection();

		    try {
		      Statement stmt = con.createStatement();

		      ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
		          + "FROM abonnement ");

		      if (rs.next()) {
		  
		        a.setId(rs.getInt("maxid") + 1);

		        stmt = con.createStatement();

		        stmt.executeUpdate("INSERT INTO abonnement (id, aboArt) " + "VALUES ("
		            + a.getId() + "," + a.getAboArt() + ")");
		      }
		    }
		    catch (SQLException e2) {
		      e2.printStackTrace();
		    }

		    return a;
		  }
	  
	  public void delete(Abonnement a) {
		    Connection con = DBConnection.connection();

		    try {
		      Statement stmt = con.createStatement();

		      stmt.executeUpdate("DELETE FROM abonnement " + "WHERE id=" + a.getId());

		    }
		    catch (SQLException e2) {
		      e2.printStackTrace();
		    }
		 }
	}

