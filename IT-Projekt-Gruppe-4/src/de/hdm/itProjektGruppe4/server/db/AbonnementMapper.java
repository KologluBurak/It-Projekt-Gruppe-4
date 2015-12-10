package de.hdm.itProjektGruppe4.server.db;

import java.sql.*;
import java.util.ArrayList;

import de.hdm.itProjektGruppe4.shared.bo.Abonnement;
import de.hdm.itProjektGruppe4.shared.bo.Nutzerabonnement;
import de.hdm.itProjektGruppe4.shared.bo.Hashtagabonnement;
		
	/**
	 * @author Kologlu
	 * @author Oikonomou
	 * @author Thies
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
		   * Diese Methode ermöglicht eine Akutalisierung des Abonnementsdatensatzes in der Datenbank
		   * @param abonnement
		   * @return
		   */
		  
		  public Abonnement updateAbonnement(Abonnement abonnement) {
			    Connection con = DBConnection.connection();

			    try {
			      Statement stmt = con.createStatement();

			      stmt.executeUpdate("UPDATE abonnement " + "SET aboArt=\""  
			           + "WHERE abonnement_id=" + abonnement.getId());

			    }
			    catch (SQLException e1) {
			      e1.printStackTrace();
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

			        stmt.executeUpdate("INSERT INTO abonnement (abonnement_id, datum,) " + "VALUES ("
			            + abonnement.getId() + ")");
			      }
			    }
			    catch (SQLException e1) {
			      e1.printStackTrace();
			   }

			    return abonnement;
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
			  + "WHERE abonnementID=" + id + " ORDER by abonnementID");
			  

			  if (rs.next()) {
				  Abonnement abonnement = new Abonnement();
				  abonnement.setId(rs.getInt("abonnement_id"));

				  return abonnement;
			  }
		   }
		  catch (SQLException e1) {
			  e1.printStackTrace();
			  return null;
			  
		  }
		  return null;
		}
	
/**
 * Diese Methode ermöglicht es alle Abonnements aus der Datenbank in einer Liste auszugeben.
 * @return
 */
	  
	  
	  public ArrayList<Abonnement> findAllAbonnements() {
		    Connection con = DBConnection.connection();

		    ArrayList<Abonnement> aboListe = new ArrayList<Abonnement>();

		    try {
		      Statement stmt = con.createStatement();
		      ResultSet rs = stmt.executeQuery("SELECT abonnementID, erstellungsZeitpunkt FROM abonnement ORDER BY abonnementID");

		      while (rs.next()) {
		        Abonnement abonnement = new Abonnement();
		        abonnement.setId(rs.getInt("abonnementID"));
		        abonnement.setErstellungsZeitpunkt(rs.getDate("erstellungsZeitpunkt"));

		        aboListe.add(abonnement);
		      }
		      stmt.close();
		      rs.close();
		      con.close();
		    }
		    catch (SQLException e1) {
		      e1.printStackTrace();
		    }
		    return aboListe;
		  }


		/**
		 * Diese Methode ermöglicht es eine Ausgabe über einen Nutzerabonnements in der Datenbank, anhand deren ID.
		 * @param id
		 * @return
		 */

		public ArrayList<Abonnement> findNutzerAbonnementByNutzer(String von, String bis, int id) {
			 Connection con = DBConnection.connection();
			 ArrayList <Nutzerabonnement> nutzerAboListe = new ArrayList<Nutzerabonnement> ();
				    
				try {
				    	
				   Statement stmt = con.createStatement();

				   ResultSet rs = stmt.executeQuery("SELECT * FROM Abonnement "
				        + "WHERE nutzerabo_id=" + id + " ORDER BY nutzerabo_id");


				  while (rs.next()) {
				  Nutzerabonnement abonnement = new Nutzerabonnement();
				  abonnement.setId(rs.getInt("nutzer_id"));
				  abonnement.setAboNutzerId(rs.getInt("abonnierterNutzer"));
				  abonnement.setNutzerabo(rs.getString("nutzername"));
				  abonnement.setErstellungsZeitpunkt(rs.getTimestamp("datum"));

				    nutzerAboListe.add(abonnement);
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
		  public ArrayList<Abonnement> findAllNutzerabonnements() {
			    Connection con = DBConnection.connection();

			    ArrayList<Abonnement> allNutzerAbos = new ArrayList<Abonnement>();

			    try {
			      Statement stmt = con.createStatement();
			      ResultSet rs = stmt.executeQuery("SELECT * FROM Abonnement "
			          + " ORDER BY nutzerabo_id");

			      
			      while (rs.next()) {
			        Abonnement abonnement = new Abonnement();
			        abonnement.setId(rs.getInt("nutzerabo_id"));
			        abonnement.setNutzerabo(rs.getString("nutzername"));
			        
			        allNutzerAbos.add(abonnement);
					}
			    }
			    catch (SQLException e1) {
			      e1.printStackTrace();
			    }

			    return allNutzerAbos;
			  }
}