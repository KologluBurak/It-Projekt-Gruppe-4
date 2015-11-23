package de.hdm.itProjektGruppe4.server.db;

import java.sql.*;
import java.util.Vector;

import de.hdm.itProjektGruppe4.shared.bo.*;

public class UnterhaltungMapper {
	private static UnterhaltungMapper unterhaltungMapper=null;
	
	protected UnterhaltungMapper() {
	} 
	
	public static UnterhaltungMapper unterhaltungMapper() {
	    if (unterhaltungMapper == null) {
	      unterhaltungMapper = new UnterhaltungMapper();
	    }
	    return unterhaltungMapper;
	  }
	
	
	
	
}
