package de.hdm.itProjektGruppe4.server.db;

import java.sql.*;

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

