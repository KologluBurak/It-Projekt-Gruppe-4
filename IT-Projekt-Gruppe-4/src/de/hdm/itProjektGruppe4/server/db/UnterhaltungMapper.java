package de.hdm.itProjektGruppe4.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.sql.PreparedStatement;

import de.hdm.itProjektGruppe4.shared.bo.*;


/**
 * @author Kologlu
 * @author Oikonomou
 * @author Thies
 *
 */


public class UnterhaltungMapper {
	//Deklaration der Klassenvariable
	private static UnterhaltungMapper unterhaltungMapper=null;
	
	//Geschützter Konstruktor
	protected UnterhaltungMapper() {
	} 
	
	//NutzerMapper-Objekt erzeugen
	public static UnterhaltungMapper unterhaltungMapper() {
	    if (unterhaltungMapper == null) {
	      unterhaltungMapper = new UnterhaltungMapper();
	    }
	    return unterhaltungMapper;
	}
	
	/**
	 * Diese Methode ermÃ¶glicht es eine Unterhaltung in der Datenbank anzulegen.
	 * @param unterhaltung
	 * @return
	 */
	public Unterhaltung insert(Unterhaltung unterhaltung){
		if (unterhaltung == null) {
			throw new IllegalArgumentException(
					"Ãœbergebenes Objekt an insert() ist NULL.");
		}
		//DB-Verbindung herstellen
		Connection con=DBConnection.connection();
		try{
			//Insert-Statement erzeugen
			Statement stmt = con.createStatement();
			//Zunächst wird geschaut welches der momentan höchste Primärschlüssel ist
			ResultSet rs = stmt.executeQuery("SELECT MAX(unterhaltung_id) AS maxID"+" FROM unterhaltungen");
				
			//Wenn ein Datensatz gefunden wurde, wird auf diesen zugegriffen
			if(rs.next()){
				int newId = rs.getInt("maxID") + 1;
				unterhaltung.setId(newId);
					
				PreparedStatement preStmt;
				preStmt=con.prepareStatement("INSERT INTO unterhaltungen "
							+"(unterhaltung_id, zuletzt_Bearbeitet)"
							+" VALUES (?, ?)");
					
				preStmt.setInt(1, newId);
				preStmt.setString(2, getSqlDateFormat(unterhaltung.getLastEdited()));
				preStmt.executeUpdate();
				preStmt.close();
			}
			stmt.close();
			rs.close();
			con.close();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return unterhaltung;
	}
	
	 /**
	  * Diese Methode ermÃ¶glicht eine Akutalisierung des Unterhaltungsdatensatzes in der Datenbank.
	  * @param unterhaltung
	  * @return
	  */
	public Unterhaltung update(Unterhaltung unterhaltung){
		if (unterhaltung == null) {
			throw new IllegalArgumentException(
				"Ãœbergebenes Objekt an insert() ist NULL.");
		}
		//DB-Verbindung herstellen
		Connection con=DBConnection.connection();
		try{
			PreparedStatement preStmt;
			preStmt=con.prepareStatement("UPDATE unterhaltungen SET zuletzt_Bearbeitet=? WHERE nutzer_id=?");
			
			preStmt.setString(1, getSqlDateFormat(unterhaltung.getLastEdited()));
			preStmt.setInt(2, unterhaltung.getId());
			preStmt.executeUpdate();
			preStmt.close();
			con.close();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return unterhaltung;	
	}
	
	/**
	  * Diese Methode ermÃ¶glicht es eine Unterhaltung aus der Datenbank zu lÃ¶schen.
	  * @param unterhaltung
	  */
	public void deleteById(int id){
		Connection con =DBConnection.connection();
		try{
			Statement stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM nutzer WHERE unterhaltung_id="+id);
			stmt.close();
			con.close();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Diese Methode ermÃ¶glicht es alle Unterhaltungen aus der Datenbank in einer Liste auszugeben.
	 * @return
	 */
	public ArrayList<Unterhaltung> findAllUnterhaltungen(){
		Connection con=DBConnection.connection();
		ArrayList<Unterhaltung> allUnterhaltungen = new ArrayList<Unterhaltung>();
		Unterhaltung unterhaltung=new Unterhaltung();
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM unterhaltungen ORDER BY unterhaltung_id");
	
			while(rs.next()){
				unterhaltung.setId(rs.getInt("unterhaltung_id"));

				Timestamp timestamp=rs.getTimestamp("zuletzt_Bearbeitet");
				if(timestamp!=null){
					Date zuletzt_Bearbeitet=new java.util.Date(timestamp.getTime());
					unterhaltung.setLastEdited(zuletzt_Bearbeitet);
				}
				
				allUnterhaltungen.add(unterhaltung);
			}
			stmt.close();
			rs.close();
			con.close();
			return allUnterhaltungen;
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Diese Methode ermÃ¶glicht es eine Unterhaltung anhand ihrer ID das Auszugeben.
	 * @param id
	 * @return
	 */
	public Unterhaltung findUnterhaltungByKey(int id){
		Connection con=DBConnection.connection();
		Unterhaltung unterhaltung=new Unterhaltung();
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM unterhaltungen WHERE unterhaltung_id= " +id+ " ORDER BY unterhaltung_id");
		
			if(rs.next()){
				unterhaltung.setId(rs.getInt("unterhaltung_id"));
				
				Timestamp timestamp=rs.getTimestamp("zuletzt_Bearbeitet");
				if(timestamp!=null){
					Date zuletzt_Bearbeitet=new java.util.Date(timestamp.getTime());
					unterhaltung.setLastEdited(zuletzt_Bearbeitet);
				}
				return unterhaltung;
			}
			stmt.close();
			rs.close();
			con.close();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}
	
	private String getSqlDateFormat(Date date) {
		String result = "";
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		result = dateFormat.format(date);
		return result;
	}
}	 