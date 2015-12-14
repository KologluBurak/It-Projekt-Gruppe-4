package de.hdm.itProjektGruppe4.client.gui;




	import com.google.gwt.user.cellview.client.CellTable;
	import com.google.gwt.user.cellview.client.TextColumn;
	import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
	import com.google.gwt.user.client.ui.Button;
	import com.google.gwt.user.client.ui.VerticalPanel;
	import com.google.gwt.user.client.ui.Widget;


	public class HashtagTabelle {
		
		VerticalPanel hauptP = new VerticalPanel();
		String hashtag = new String();
		String text = new String ();
		String entfernen = new String();
		Button hAbohin = new Button("Hashtag Hinzufuegen");
		
		
		
			public Widget zeigeTabelle() {
				
				CellTable<HashtagTabelle> htabelle = new CellTable<HashtagTabelle>();
				htabelle.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
				
				// Hinzufügen einer Spalte Hashtag
		    	
		        TextColumn<HashtagTabelle> hashColumn = new TextColumn<HashtagTabelle>() {
		         
		     
		          public String getValue(HashtagTabelle object) {
		            return object.hashtag;
		          }
		        };
		        htabelle.addColumn(hashColumn, "Hashtagbezeichnung");
		        
		        // Hinzufügen einer Spalte Text
		    	
		        TextColumn<HashtagTabelle> TextColumn = new TextColumn<HashtagTabelle>() {
		         
		     
		          public String getValue(HashtagTabelle object) {
		            return object.text;
		          }
		        };
		        htabelle.addColumn(TextColumn, "Anzuzeigener Text");
		        
		     // Hinzufügen einer Spalte entfernen
		        
		        TextColumn<HashtagTabelle> entfernenColumn = new TextColumn<HashtagTabelle>() {
		            
		   
		          public String getValue(HashtagTabelle object) {
		            return object.entfernen;
		          }
		        };
		        htabelle.addColumn(entfernenColumn, "Entfernen");
		        
		     hauptP.add(htabelle);
		     hauptP.add(hAbohin);
			return hauptP;
			
		}
				
			}
		
			
	       
	    	

