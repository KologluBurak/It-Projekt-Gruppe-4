package de.hdm.itProjektGruppe4.server.db;

import java.sql.*;
import java.util.ArrayList;

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
	
	/**
	 * Diese Methode ermöglicht es eine Ausgabe über die Abonnements in der Datenbank, anhand deren ID.
	 * @param id
	 * @return
	 */

	  public Abonnement findAbonnementByKey(int id){
		
		  Connection con = DBConnection.connection();
		  
		  try {
			  Statement stmt = con.createStatement();
			  
			  ResultSet rs = stmt.executeQuery("SELECT * FROM abonnement " 
			  + "WHERE abonnement_id=" + id + " ORDER by aboArt");
			  

			  if (rs.next()) {
				  Abonnement abonnement = new Abonnement();
				  abonnement.setId(rs.getInt("abonnement_id"));
				  abonnement.setAboArt(rs.getString("aboArt"));
				  
				  return abonnement;
			  }
		   }
		  catch (SQLException e2) {
			  e2.printStackTrace();
			  return null;
			  
		  }
		  return null;
		}
	  
	  /**
	   * Diese Methode ermöglicht eine Ausgabe über die Abonnements eines Nutzers, in einer Liste.
	   * @param id
	   * @return
	   */
	  
	  public ArrayList<Abonnement> findAbonnementByNutzer(int id) {
		    Connection con = DBConnection.connection();
		    ArrayList <Abonnement> nutzerAboListe = new ArrayList<Abonnement> ();
		    
		    try {
		    	
		      Statement stmt = con.createStatement();

		      ResultSet rs = stmt.executeQuery("SELECT nutzerAbo_id FROM abonnement "
		          + "WHERE abonnement_id=" + id + " ORDER BY aboArt");


		    while (rs.next()) {
		        Abonnement abonnement = new Abonnement();
				abonnement.setId(rs.getInt("abonnement_id"));
		        abonnement.setAboArt(rs.getString("aboArt"));
		        abonnement.setNutzerAbo(rs.getInt("nutzerAbo"));
		        
		        nutzerAboListe.add(abonnement);
		      }
		      
		    }
		    
		    catch (SQLException e2) {
		      e2.printStackTrace();
		      return null;
		    }

		    return nutzerAboListe;
		    
		  }
	  
	  /**
	   * Diese Methode ermöglicht eine Ausgabe über die Abonnements eines Hashtags, in einer Liste.
	   * @param id
	   * @return
	   */

	  public ArrayList<Abonnement> findAbonnementByHashtag(int id) {
		    Connection con = DBConnection.connection();
		    ArrayList <Abonnement> hashtagAboListe = new ArrayList<Abonnement> ();
		    
		    try {
		    	
		      Statement stmt = con.createStatement();

		      ResultSet rs = stmt.executeQuery("SELECT hashtagAboId FROM abonnement "
		          + "WHERE abonnement_id=" + id + " ORDER BY aboArt");


		      while (rs.next()) {
		        Abonnement abonnement = new Abonnement();
				abonnement.setId(rs.getInt("Abonnement_id"));
		        abonnement.setAboArt(rs.getString("aboArt"));
		        abonnement.setHashtagAbo(rs.getInt("hashtagAbo"));
		        
		        hashtagAboListe.add(abonnement);
		      }
		      
		    }
		    
		    catch (SQLException e2) {
		      e2.printStackTrace();
		      return null;
		    }

		    return hashtagAboListe;
		    
		  }

	  /**
	   * Diese Methode ermöglicht es alle Abonnements aus der Datenbank in einer Liste auszugeben.
	   * @return
	   */
	  
	  public ArrayList<Abonnement> findAllAbonnements() {
		    Connection con = DBConnection.connection();

		    ArrayList<Abonnement> result = new ArrayList<Abonnement>();

		    try {
		      Statement stmt = con.createStatement();

		      ResultSet rs = stmt.executeQuery("SELECT abonnement_id FROM abonnement "
		          + " ORDER BY abonnement_id");

		      
		      while (rs.next()) {
		        Abonnement abonnement = new Abonnement();
		        abonnement.setId(rs.getInt("abonnement_id"));
		        abonnement.setAboArt(rs.getString("aboArt"));
		        abonnement.setNutzerAbo(rs.getInt("nutzerAbo"));
		        abonnement.setHashtagAbo(rs.getInt("hashtagAbo"));

		        
		        result.add(abonnement);
		      }
		    }
		    catch (SQLException e2) {
		      e2.printStackTrace();
		    }

		    return result;
		  }
	  
	  /**
	   * Diese Methode ermöglicht eine Akutalisierung des Abonnementsdatensatzes in der Datenbank
	   * @param abonnement
	   * @return
	   */
	  
	  public Abonnement updateAbonnement(Abonnement abonnement) {
		    Connection con = DBConnection.connection();

		    try {
		      Statement stmt = con.createStatement();

		      stmt.executeUpdate("UPDATE abonnement " + "SET aboArt=\"" + abonnement.getAboArt()
		          + "\" " + "WHERE abonnement_id=" + abonnement.getId());

		    }
		    catch (SQLException e2) {
		      e2.printStackTrace();
		    }

		    return abonnement;
		  }
	  
	  /**
	   * Diese Methode ermöglicht es einen Abonnement in der Datenbank anzulegen.
	   * @param abonnement
	   * @return
	   */
	  
	  public Abonnement insertAbonnement(Abonnement abonnement) {
		    Connection con = DBConnection.connection();
		   
		    try {
		      Statement stmt = con.createStatement();

		      ResultSet rs = stmt.executeQuery("SELECT MAX(abonnement_id) AS maxid "
		          + "FROM abonnement ");

		      if (rs.next()) {
		    	  
		        abonnement.setId(rs.getInt("maxid") + 1);
		        abonnement.setErstellungsZeitpunkt(rs.getTimestamp("datum"));

		        stmt = con.createStatement();

		        stmt.executeUpdate("INSERT INTO abonnement (abonnement_id, datum, aboArt, nutzerAboId, hashtagAboId) " + "VALUES ("
		            + abonnement.getId() + "," + abonnement.getAboArt() + "," + abonnement.getNutzerAboId() + "," + abonnement.getHashtagAboId() + ")");
		      }
		    }
		    catch (SQLException e2) {
		      e2.printStackTrace();
		    }

		    return abonnement;
		  }
	  
	  /**
	   * Diese Methode ermöglicht das Löschen eines Abonnements und dessen Referenzen zu anderen Klassen
	   * @param abonnement
	   */
	  
	  public void deleteAbonnement(Abonnement abonnement) {
		    Connection con = DBConnection.connection();

		    try {
		      Statement stmt = con.createStatement();

		      stmt.executeUpdate("DELETE FROM abonnement " + "WHERE abonnement_id=" + abonnement.getId());
		     
		    }
		    catch (SQLException e2) {
		      e2.printStackTrace();
		    }
		 }
	}

