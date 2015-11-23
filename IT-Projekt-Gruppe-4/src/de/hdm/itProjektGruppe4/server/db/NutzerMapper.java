package de.hdm.itProjektGruppe4.server.db;

import java.sql.*;

import de.hdm.itProjektGruppe4.shared.bo.Nutzer;

/**
 * 
 * @author Oikonomou
 *
 */

public class NutzerMapper {
	 
	private static NutzerMapper nutzerMapper = null;
	
	protected NutzerMapper(){
		
	}
	
	public static NutzerMapper nutzerMapper(){
		if(nutzerMapper==null) {
			nutzerMapper= new NutzerMapper();
		}
		return nutzerMapper;
		
	}
	
 /**
  * Suchen eines Kontos mit vorgegebener Kontonummer. Da diese eindeutig ist,
  * wird genau ein Objekt zur�ckgegeben.
  * 
  * @param id Primärschlüsselattribut (->DB)
  * @return Konto-Objekt, das dem übergebenen Schlüssel entspricht, null bei
  *         nicht vorhandenem DB-Tupel.
  */

  public Nutzer findByKey(int id){
	
	  Connection con = DBConnection.connection();
	  
	  try {
		  Statement stmt = con.createStatement();
		  
		  ResultSet rs = stmt.executeQuery("SELECT id, googleId FROM nutzer " 
		  + "WHERE id=" + id + " ORDER by googleID");
		  

		  if (rs.next()) {
			  Nutzer a = new Nutzer();
			  a.setId(rs.getInt("id"));
			  a.setGoogleID(rs.getInt("googleID"));
			  return a;
		  }
	   }
	  catch (SQLException e2) {
		  e2.printStackTrace();
		  return null;
		  
	  }
	  return null;
	}

}
