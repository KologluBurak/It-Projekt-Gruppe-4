package de.hdm.itProjektGruppe4.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.sql.PreparedStatement;

import de.hdm.itProjektGruppe4.shared.bo.*;



/**
 * @author kologlu
 * @author Oikonomou
 * @author Thies
 *
 */

public class NutzerMapper {
	 
	//Deklaration der Klassenvariable
	private static NutzerMapper nutzerMapper = null;
	
	//Geschützter Konstruktor
	protected NutzerMapper(){
		
	}
	
	//NutzerMapper-Objekt erzeugen
	public static NutzerMapper nutzerMapper(){
		if(nutzerMapper==null) {
			nutzerMapper= new NutzerMapper();
		}
		return nutzerMapper;
	}
	
	public Nutzer insert(Nutzer nutzer){
		if (nutzer == null) {
			throw new IllegalArgumentException(
					"Ãœbergebenes Objekt an insert() ist NULL.");
		}
		//DB-Verbindung herstellen
		Connection con=DBConnection.connection();
		try{
			//Insert-Statement erzeugen
			Statement stmt = con.createStatement();
			//Zunächst wird geschaut welches der momentan höchste Primärschlüssel ist
			ResultSet rs = stmt.executeQuery("SELECT MAX(nutzer_id) AS maxID"+" FROM nutzer");
				
			//Wenn ein Datensatz gefunden wurde, wird auf diesen zugegriffen
			if(rs.next()){
				int newId = rs.getInt("maxID") + 1;
				nutzer.setId(newId);
					
				PreparedStatement preStmt;
				preStmt=con.prepareStatement("INSERT INTO nutzer "
							+"(nutzer_id, vorname, nachname, passwort, googleId)"
							+" VALUES (?, ?, ?, ?, ?)");
					
				preStmt.setInt(1, newId);
				preStmt.setString(2, nutzer.getVorname());
				preStmt.setString(3, nutzer.getNachname());
				preStmt.setString(4, nutzer.getPasswort());
				preStmt.setString(5, nutzer.getGoogleId());
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
		return nutzer;
	}
	
	public Nutzer update(Nutzer nutzer){
		if (nutzer == null) {
			throw new IllegalArgumentException(
					"Ãœbergebenes Objekt an insert() ist NULL.");
		}
		Connection con=DBConnection.connection();
		try{
			PreparedStatement preStmt;
			preStmt=con.prepareStatement("UPDATE nutzer SET vorname=?,"
					+ " nachname=?, passwort=?, googleID=? WHERE nutzer_id=?");
			
			preStmt.setString(1, nutzer.getVorname());
			preStmt.setString(2, nutzer.getNachname());
			preStmt.setString(3, nutzer.getPasswort());
			preStmt.setString(4, nutzer.getGoogleId());
			preStmt.setInt(5, nutzer.getId());
			preStmt.executeUpdate();
			preStmt.close();
			con.close();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	return nutzer;
	}
	
	public void deleteByKey(int id){
		Connection con =DBConnection.connection();
		try{
			Statement stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM nutzer WHERE nutzer_id="+id);
			stmt.close();
			con.close();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}

	public ArrayList<Nutzer> findAllNutzer(){
		Connection con=DBConnection.connection();
		ArrayList<Nutzer> allNutzer = new ArrayList<Nutzer>();
		Nutzer nutzer=new Nutzer();
			try{
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM nutzer ORDER BY nutzer_id");
			
				while(rs.next()){
					nutzer.setId(rs.getInt("nutzer_id"));
					nutzer.setVorname(rs.getString("vorname"));
					nutzer.setNachname(rs.getString("nachname"));
					nutzer.setPasswort(rs.getString("passwort"));
					nutzer.setGoogleId(rs.getString("googleId"));
				
					allNutzer.add(nutzer);
				}
				stmt.close();
				rs.close();
				con.close();
				return allNutzer;
			}
			catch(SQLException e){
				e.printStackTrace();
			}
		return null;
	}
	
	//Diese Methode ermÃ¶glicht das Ausgeben der Nutzer anhand deren ID aus der Datenbank.
	public ArrayList<Nutzer> findNutzerByName(String vorname, String nachname){
		Connection con = DBConnection.connection();
		ArrayList<Nutzer> allNutzerByName=new ArrayList<Nutzer>();
		Nutzer nutzer=new Nutzer();
			try{
				Statement stmt=con.createStatement();
				ResultSet rs=stmt.executeQuery("SELECT * FROM nutzer WHERE vorname="
						+ "'"+vorname+"'" + "AND" + " nachname="+ "'"+nachname+"'" + " ORDER BY nutzer_id");
							
				while(rs.next()){
					nutzer.setId(rs.getInt("nutzer_id"));
					nutzer.setVorname(rs.getString("vorname"));
					nutzer.setNachname(rs.getString("nachname"));
					nutzer.setPasswort(rs.getString("passwort"));
					nutzer.setGoogleId(rs.getString("googleId"));
					
					allNutzerByName.add(nutzer);
				}
				stmt.close();
				rs.close();
				con.close();
				return allNutzerByName;
			}
			catch(SQLException e){
				e.printStackTrace();
			}
		return null;
	}	
}
