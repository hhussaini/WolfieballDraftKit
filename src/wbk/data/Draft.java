/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wbk.data;

import java.time.LocalDate;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Hamza
 */
public class Draft {
    
    final StringProperty name;
    final StringProperty topics;
   
    public static final String DEFAULT_NAME = "<ENTER NAME>";
    public static final String DEFAULT_TOPICS = "<ENTER TOPICS>";
    
    
     ObservableList<Player> players;
    
    
    public Draft() {
        
        name = new SimpleStringProperty(DEFAULT_NAME);
        topics = new SimpleStringProperty(DEFAULT_NAME);
        players = FXCollections.observableArrayList();
    }
    
    public void reset() {
       
     
    }
    
    public void addPlayer(Player p) {
        players.add(p);
    }
    
    public ObservableList<Player> getPlayers() {
        return players;
    }

    public void removePlayer(Player playerToRemove) {
        players.remove(playerToRemove);
    }
    
    
}
