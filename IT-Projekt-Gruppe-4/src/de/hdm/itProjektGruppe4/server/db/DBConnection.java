package de.hdm.itProjektGruppe4.server.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Statement;

import com.google.appengine.api.utils.SystemProperty;


public class DBConnection {
	//Variablen für den Verbindungsaufbau
		private static Connection con = null;
		//private static String googleUrl = "jdbc:google:mysql://mss-grp-41-1174:mss-grp41-itprojekt04/messaging_administration?user=root";
	    //private static String localUrl = "jdbc:mysql://173.194.235.100:3306/messaging_administration?user=root";
	    //Verbindung mit der lokalen Datenbank in Xampp
	    private static String localUrl = "jdbc:mysql://localhost:3306/messaging_administration?user=root";

	    public static Connection connection() {
	    	//System.out.println("Connection aufgerufen!");
	        // Wenn es bisher keine Conncetion zur DB gab, ...
	        if (con == null) {
	            String url = "";
	            try {
//	                if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {
//	                    Class.forName("com.mysql.jdbc.GoogleDriver");
//	                    url = googleUrl;
//	                    System.out.println("url von googleDB:"+url);
//	                } else {
	            	try{
	                    Class.forName("com.mysql.jdbc.Driver");
	                    url = localUrl;
	                    System.out.println("URL von DB: "+url);
	                }catch(Exception e){
	                    System.err.println("Treiber konnte nicht erstellt werden.");
	                	con = null;
	                    e.printStackTrace();
	                }
	                	con = DriverManager.getConnection(url);
//	                } 
	            } catch (Exception e) {
	                System.err.println("Datenbank konnte nicht geladen werden");
	            	con = null;
	                e.printStackTrace();
	            }
	        }
	        return con;
	    }
	    
	    /**
		 * Schließt das ResultSet, das Statement und die Connection
		 * @param rs
		 * @param stmt
		 * @param con
		 * @throws Exception
		 */
		public static void closeAll(ResultSet rs, Statement stmt, Connection con) throws Exception {
//			try {
//				if (rs != null) {
//					rs.close();
//				}
//				if (stmt != null) {
//					stmt.close();
//				} 
//				if (con != null && con.isClosed()) {
//					con.close();
//				}
//				
//			} catch (Exception e) {
//				e.printStackTrace();
//				throw new Exception("Connection close Fehler!" + e.toString());
//			}
		}
}