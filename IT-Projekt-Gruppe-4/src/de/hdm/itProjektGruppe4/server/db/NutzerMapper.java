package de.hdm.itProjektGruppe4.server.db;

import java.sql.*;

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

}
