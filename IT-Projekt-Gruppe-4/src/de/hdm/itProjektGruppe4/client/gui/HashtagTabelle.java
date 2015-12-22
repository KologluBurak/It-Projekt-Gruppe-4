package de.hdm.itProjektGruppe4.client.gui;




	import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.hdm.itProjektGruppe4.shared.MessagingAdministration;
import de.hdm.itProjektGruppe4.shared.MessagingAdministrationAsync;
import de.hdm.itProjektGruppe4.shared.bo.Hashtag;


	public class HashtagTabelle {
		
		VerticalPanel hauptP = new VerticalPanel();
		String hashtag = new String();
		String text = new String ();
		String entfernen = new String();
		Button hAbohin = new Button("Hashtag Hinzufuegen");
		
		MessagingAdministrationAsync myAsync = GWT.create(MessagingAdministration.class);
		
			public Widget zeigeTabelle() {
				
				CellTable<HashtagTabelle> htabelle = new CellTable<HashtagTabelle>();
				htabelle.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
				
				// Hinzuf�gen einer Spalte Hashtag
		    	
		        TextColumn<HashtagTabelle> hashColumn = new TextColumn<HashtagTabelle>() {
		         
		     
		          public String getValue(HashtagTabelle object) {
		            return object.hashtag;
		          }
		        };
		        htabelle.addColumn(hashColumn, "Hashtagbezeichnung");
		        
		        // Hinzuf�gen einer Spalte Text
		    	
		        TextColumn<HashtagTabelle> TextColumn = new TextColumn<HashtagTabelle>() {
		         
		     
		          public String getValue(HashtagTabelle object) {
		            return object.text;
		          }
		        };
		        htabelle.addColumn(TextColumn, "Anzuzeigener Text");
		        
		     // Hinzuf�gen einer Spalte entfernen
		        
		        TextColumn<HashtagTabelle> entfernenColumn = new TextColumn<HashtagTabelle>() {
		            
		   
		          public String getValue(HashtagTabelle object) {
		            return object.entfernen;
		          }
		        };
		        htabelle.addColumn(entfernenColumn, "Entfernen");
		        
		        //Clickhandler
		        
		        hAbohin.addClickHandler(new ClickHandler() {
					
					@Override
					public void onClick(ClickEvent event) {
						hinzufuegenhashtag();
					}

					private void hinzufuegenhashtag() {
						myAsync.getAllHashtags(new AsyncCallback<ArrayList<Hashtag>>() {
							
							@Override
							public void onSuccess(ArrayList<Hashtag> result) {
								
								
							}
							
							@Override
							public void onFailure(Throwable caught) {
								
								
							}
						});
						
					}
				});
		        
		        
		     hauptP.add(htabelle);
		     hauptP.add(hAbohin);
			return hauptP;
			
		}
				
			}
		
			
	       
	    	

