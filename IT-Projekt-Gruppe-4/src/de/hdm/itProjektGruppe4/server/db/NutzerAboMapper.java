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

	public class NutzerAboMapper {
			 
		private static NutzerAboMapper nutzerAboMapper = null;
			
		protected NutzerAboMapper(){
				
		}
			
		public static NutzerAboMapper nutzerAboMapper(){
			if(nutzerAboMapper==null) {
			nutzerAboMapper= new NutzerAboMapper();
		}
		return nutzerAboMapper;
				
		}
		
	/**
	 * Diese Methode ermöglicht es eine Ausgabe über einen Nutzerabonnements in der Datenbank, anhand deren ID.
	 * @param id
	 * @return
	 */

	public Nutzerabonnement findNutzerAboByKey(int id){
			
		Connection con = DBConnection.connection();
			  
		try {
			Statement stmt = con.createStatement();
				  
			ResultSet rs = stmt.executeQuery("SELECT * FROM Nutzerabonnement " 
			+ "WHERE nutzerabo_id=" + id + " ORDER by nutzerabo_id");
				  

			if (rs.next()) {
			Nutzerabonnement nutzerAbonnement = new Nutzerabonnement();
				nutzerAbonnement.setId(rs.getInt("nutzerabo_id"));
				nutzerAbonnement.setNutzername(rs.getString("nutzername"));
					  
				return nutzerAbonnement;
				}
			}
		catch (SQLException e1) {
			e1.printStackTrace();
			return null;
				  
			}
			return null;
	}
		  
	/**
	 * Diese Methode ermöglicht eine Ausgabe über die Nutzerabonnements eines Nutzers, in einer Liste.
	 * @param id
	 * @return
	 */
		  
	public ArrayList<Nutzerabonnement> findNutzerAbonnementByNutzer(int id) {
		 Connection con = DBConnection.connection();
		 ArrayList <Nutzerabonnement> nutzerAboListe = new ArrayList<Nutzerabonnement> ();
			    
			try {
			    	
			   Statement stmt = con.createStatement();

			   ResultSet rs = stmt.executeQuery("SELECT * FROM Nutzerabonnement "
			        + "WHERE nutzerabo_id=" + id + " ORDER BY nutzerabo_id");


			  while (rs.next()) {
			  Nutzerabonnement nutzerabonnement = new Nutzerabonnement();
			  nutzerabonnement.setId(rs.getInt("nutzerabo_id"));
			  nutzerabonnement.setNutzername(rs.getString("nutzername"));


			    nutzerAboListe.add(nutzerabonnement);
			  }
			      
			}
			    
			catch (SQLException e1) {
			   e1.printStackTrace();
			   return null;
			 }

		return nutzerAboListe;
			    
	}

	/**
	 * Diese Methode ermöglicht es alle Nutzerabonnements aus der Datenbank in einer Liste auszugeben.
	 * @return
	 */
	  
	  public ArrayList<Nutzerabonnement> findAllNutzerabonnements() {
		    Connection con = DBConnection.connection();

		    ArrayList<Nutzerabonnement> allNutzerAbos = new ArrayList<Nutzerabonnement>();

		    try {
		      Statement stmt = con.createStatement();

		      ResultSet rs = stmt.executeQuery("SELECT * FROM Nutzerabonnement "
		          + " ORDER BY nutzerabo_id");

		      
		      while (rs.next()) {
		        Nutzerabonnement nutzerabonnement = new Nutzerabonnement();
		        nutzerabonnement.setId(rs.getInt("nutzerabo_id"));
		        nutzerabonnement.setNutzername(rs.getString("hashtagname"));

		        
		        allNutzerAbos.add(nutzerabonnement);
		      }
		    }
		    catch (SQLException e1) {
		      e1.printStackTrace();
		    }

		    return allNutzerAbos;
		  }
	  
	  /**
	   * Diese Methode ermöglicht eine Akutalisierung des Nutzerabodatensatzes in der Datenbank.
	   * @param nutzerabonnement
	   * @return
	   */
	  
	  public Nutzerabonnement updateNutzerabonnement(Nutzerabonnement nutzerabonnement) {
		    Connection con = DBConnection.connection();

		    try {
		      Statement stmt = con.createStatement();

		      stmt.executeUpdate("UPDATE Nutzerabonnement " + "SET nutzer_id=\"" 
		          + "WHERE nutzerabo_id=" + nutzerabonnement.getId());

		    }
		    catch (SQLException e1) {
		      e1.printStackTrace();
		    }

		    return nutzerabonnement;
		  }
	  
	  /**
	   * Diese Methode ermöglicht es einen Nutzerabonnement in der Datenbank anzulegen.
	   * @param hashtagabonnement
	   * @return
	   */
	  
	  public Nutzerabonnement insertNutzerabonnement(Nutzerabonnement nutzerabonnement) {
		    Connection con = DBConnection.connection();
		   
		    try {
		      Statement stmt = con.createStatement();

		      ResultSet rs = stmt.executeQuery("SELECT MAX(nutzerabo_id) AS maxid "
		          + "FROM nutzerabonnement ");

		      if (rs.next()) {
		    	  
		        nutzerabonnement.setId(rs.getInt("maxid") + 1);
		        nutzerabonnement.setErstellungsZeitpunkt(rs.getTimestamp("erstellungsdatum"));

		        stmt = con.createStatement();

		        stmt.executeUpdate("INSERT INTO Nutzerabonnement (nutzerabonnement_id, erstellungsdatum) " + "VALUES ("
		            + nutzerabonnement.getId() + "," + nutzerabonnement.getNutzername() + ")");
		      }
		    }
		    catch (SQLException e1) {
		      e1.printStackTrace();
		    }

		    return nutzerabonnement;
		  }
	  
	  /**
	   * Diese Methode ermöglicht das Löschen eines Nutzerabonnements und dessen Referenzen zu anderen Klassen
	   * @param nutzerabonnement
	   */
	  
	  public void deleteNutzerabonnement(Nutzerabonnement nutzerabonnement) {
		    Connection con = DBConnection.connection();

		    try {
		      Statement stmt = con.createStatement();

		      stmt.executeUpdate("DELETE FROM Nutzerabonnement " + "WHERE nutzerabo_id=" + nutzerabonnement.getId());
		     
		    }
		    catch (SQLException e1) {
		      e1.printStackTrace();
		    }
		 }
	}

