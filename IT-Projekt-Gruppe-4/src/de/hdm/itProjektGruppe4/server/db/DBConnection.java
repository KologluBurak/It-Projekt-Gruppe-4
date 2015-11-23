package de.hdm.itProjektGruppe4.server.db;

import java.sql.Connection;
import java.sql.DriverManager;
import com.google.appengine.api.utils.SystemProperty;

public class DBConnection {
	private static Connection con = null;
	private static String googleUrl = null; /*Muss noch entsprechend geändert werden*/
    private static String localUrl = null;/*Muss noch entsprechend geändert werden*/
    
    public static Connection connection() {
        // Wenn es bisher keine Conncetion zur DB gab, ...
        if (con == null) {
            String url = null;
            try {
                if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {
                    Class.forName("com.mysql.jdbc.GoogleDriver");
                    url = googleUrl;
                } else {
                    Class.forName("com.mysql.jdbc.Driver");
                    url = localUrl;
                }
                con = DriverManager.getConnection(url);
            } catch (Exception e) {
                con = null;
                e.printStackTrace();
            }
        }
        return con;
    }
}
