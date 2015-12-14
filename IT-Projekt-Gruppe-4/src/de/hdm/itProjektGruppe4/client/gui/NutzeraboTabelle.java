package de.hdm.itProjektGruppe4.client.gui;



	import com.google.gwt.user.cellview.client.CellTable;
	import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
	import com.google.gwt.user.cellview.client.TextColumn;
	import com.google.gwt.user.client.Window;
	import com.google.gwt.user.client.ui.Button;
	import com.google.gwt.user.client.ui.VerticalPanel;
	import com.google.gwt.user.client.ui.Widget;
	import com.google.gwt.view.client.SelectionChangeEvent;
	import com.google.gwt.view.client.SingleSelectionModel;



	public class NutzeraboTabelle {

		VerticalPanel hauptP = new VerticalPanel();
		String nachname = new String();
		String vorname = new String();
		String eMail = new String();
		String nickname = new String ();
		String entfernen = new String();
		Button nAbohin = new Button("Nutzer Hinzufuegen");
		
		
		
			public Widget zeigeTabelle() {
		
			CellTable<NutzeraboTabelle> tabelle = new CellTable<NutzeraboTabelle>();
			
	    	tabelle.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
	       
	    	// Hinzufügen einer Spalte Nachname
	    	
	        TextColumn<NutzeraboTabelle> nnameColumn = new TextColumn<NutzeraboTabelle>() {
	         
	     
	          public String getValue(NutzeraboTabelle object) {
	            return object.nachname;
	          }
	        };
	        tabelle.addColumn(nnameColumn, "Nachname");
	        
	        // Hinzufügen einer Spalte Vorname
	    	
	        TextColumn<NutzeraboTabelle> vnameColumn = new TextColumn<NutzeraboTabelle>() {
	         
	     
	          public String getValue(NutzeraboTabelle object) {
	            return object.vorname;
	          }
	        };
	        tabelle.addColumn(vnameColumn, "Vorname");
	        
	     // Hinzufügen einer Spalte Email
	        
	        TextColumn<NutzeraboTabelle> eMailColumn = new TextColumn<NutzeraboTabelle>() {
	            
	   
	          public String getValue(NutzeraboTabelle object) {
	            return object.eMail;
	          }
	        };
	        tabelle.addColumn(eMailColumn, "EMail");
	        
	     // Hinzufügen einer Spalte Nickname
	        
	        TextColumn<NutzeraboTabelle> nicknameColumn = new TextColumn<NutzeraboTabelle>() {
	            
	       
	          public String getValue(NutzeraboTabelle object) {
	            return object.nickname;
	          }
	        };
	        tabelle.addColumn(nicknameColumn, "Nickname");
	        
	        // Hinzufügen einer Spalte emtfernen
	        
	        TextColumn<NutzeraboTabelle> entfernenColumn = new TextColumn<NutzeraboTabelle>() {
	            
	       
	          public String getValue(NutzeraboTabelle object) {
	            return object.entfernen;
	          }
	        };
	        tabelle.addColumn(entfernenColumn, "Entfernen");
	        
	        
	        // Add a selection model to handle user selection.
	        
	        final SingleSelectionModel<NutzeraboTabelle> selectionModel = new SingleSelectionModel<NutzeraboTabelle>();
	        tabelle.setSelectionModel(selectionModel);
	        
	        selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
	          
	        	public void onSelectionChange(SelectionChangeEvent event) {
	            NutzeraboTabelle selected = selectionModel.getSelectedObject();
	            if (selected != null) {
	              Window.alert("You selected: " + selected.nickname);
	            }
	          }
	        });
	    
			
			{
				
			  hauptP.add(tabelle);
			  hauptP.add(nAbohin);
			    return hauptP;
			    
			}}}
			    
			    
		


	

