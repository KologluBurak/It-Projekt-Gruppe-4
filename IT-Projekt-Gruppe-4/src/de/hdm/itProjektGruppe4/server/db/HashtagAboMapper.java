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

	public class HashtagAboMapper {
			 
		private static HashtagAboMapper hashtagAboMapper = null;
			
		protected HashtagAboMapper(){
				
		}
			
		public static HashtagAboMapper hashtagAboMapper(){
			if(hashtagAboMapper==null) {
			hashtagAboMapper= new HashtagAboMapper();
		}
		return hashtagAboMapper;
				
		}
		
	/**
	 * Diese Methode ermöglicht es eine Ausgabe über einen Hashtagabonnements in der Datenbank, anhand deren ID.
	 * @param id
	 * @return
	 */

	public Hashtagabonnement findHashtagAboByKey(int id){
			
		Connection con = DBConnection.connection();
			  
		try {
			Statement stmt = con.createStatement();
				  
			ResultSet rs = stmt.executeQuery("SELECT * FROM Hashtagabonnement " 
			+ "WHERE hashtagabo_id=" + id + " ORDER by hashtagabo_id");
				  

			if (rs.next()) {
			Hashtagabonnement hashtagAbonnement = new Hashtagabonnement();
				hashtagAbonnement.setId(rs.getInt("hashtagabo_id"));
					  
				return hashtagAbonnement;
				}
			}
		catch (SQLException e1) {
			e1.printStackTrace();
			return null;
				  
			}
			return null;
	}
		  
	/**
	 * Diese Methode ermöglicht eine Ausgabe über die Hashtagabonnements eines Nutzers, in einer Liste.
	 * @param id
	 * @return
	 */
		  
	public ArrayList<Hashtagabonnement> findHashtagAbonnementByNutzer(int id) {
		 Connection con = DBConnection.connection();
		 ArrayList <Hashtagabonnement> hashtagAboListe = new ArrayList<Hashtagabonnement> ();
			    
			try {
			    	
			   Statement stmt = con.createStatement();

			   ResultSet rs = stmt.executeQuery("SELECT * FROM Hashtagabonnement "
			        + "WHERE hashtagabo_id=" + id + " ORDER BY hashtagabo_id");


			  while (rs.next()) {
			  Hashtagabonnement hashtagabonnement = new Hashtagabonnement();
			  hashtagabonnement.setId(rs.getInt("hashtagabo_id"));


			    hashtagAboListe.add(hashtagabonnement);
			  }
			      
			}
			    
			catch (SQLException e1) {
			   e1.printStackTrace();
			   return null;
			 }

		return hashtagAboListe;
			    
	}

	/**
	 * Diese Methode ermöglicht es alle Hashtagabonnements aus der Datenbank in einer Liste auszugeben.
	 * @return
	 */
	  
	  public ArrayList<Hashtagabonnement> findAllHastagabonnements() {
		    Connection con = DBConnection.connection();

		    ArrayList<Hashtagabonnement> allHashtagAbos = new ArrayList<Hashtagabonnement>();

		    try {
		      Statement stmt = con.createStatement();

		      ResultSet rs = stmt.executeQuery("SELECT abonnement_id FROM abonnement "
		          + " ORDER BY abonnement_id");

		      
		      while (rs.next()) {
		        Hashtagabonnement hashtagabonnement = new Hashtagabonnement();
		        hashtagabonnement.setId(rs.getInt("hashtagabo_id"));
		        hashtagabonnement.setName(rs.getString("hashtagname"));

		        
		        allHashtagAbos.add(hashtagabonnement);
		      }
		    }
		    catch (SQLException e1) {
		      e1.printStackTrace();
		    }

		    return allHashtagAbos;
		  }
	  
	  /**
	   * Diese Methode ermöglicht eine Akutalisierung des Hashtagabodatensatzes in der Datenbank.
	   * @param hashtagabonnement
	   * @return
	   */
	  
	  public Hashtagabonnement updateHashtagabonnement(Hashtagabonnement hashtagabonnement) {
		    Connection con = DBConnection.connection();

		    try {
		      Statement stmt = con.createStatement();

		      stmt.executeUpdate("UPDATE Hashtagabonnement " + "SET hashtag_id=\"" 
		          + "WHERE hashtagabo_id=" + hashtagabonnement.getId());

		    }
		    catch (SQLException e1) {
		      e1.printStackTrace();
		    }

		    return hashtagabonnement;
		  }
	  
	  /**
	   * Diese Methode ermöglicht es einen Hashtagabonnement in der Datenbank anzulegen.
	   * @param hashtagabonnement
	   * @return
	   */
	  
	  public Hashtagabonnement insertHashtagabonnement(Hashtagabonnement hashtagabonnement) {
		    Connection con = DBConnection.connection();
		   
		    try {
		      Statement stmt = con.createStatement();

		      ResultSet rs = stmt.executeQuery("SELECT MAX(hashtagabo_id) AS maxid "
		          + "FROM hashtagabonnement ");

		      if (rs.next()) {
		    	  
		        hashtagabonnement.setId(rs.getInt("maxid") + 1);
		        hashtagabonnement.setErstellungsZeitpunkt(rs.getTimestamp("erstellungsdatum"));

		        stmt = con.createStatement();

		        stmt.executeUpdate("INSERT INTO Hashtagabonnement (hashtagabonnement_id, erstellungsdatum) " + "VALUES ("
		            + hashtagabonnement.getId() + "," + hashtagabonnement.getId() + ")");
		      }
		    }
		    catch (SQLException e1) {
		      e1.printStackTrace();
		    }

		    return hashtagabonnement;
		  }
	  
	  /**
	   * Diese Methode ermöglicht das Löschen eines Hashtagabonnements und dessen Referenzen zu anderen Klassen
	   * @param hashtagabonnement
	   */
	  
	  public void deleteHashtagabonnement(Hashtagabonnement hashtagabonnement) {
		    Connection con = DBConnection.connection();

		    try {
		      Statement stmt = con.createStatement();

		      stmt.executeUpdate("DELETE FROM Hashtagabonnement " + "WHERE hashtagabo_id=" + hashtagabonnement.getId());
		     
		    }
		    catch (SQLException e1) {
		      e1.printStackTrace();
		    }
		 }
	}

