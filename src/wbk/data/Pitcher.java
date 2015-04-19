/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wbk.data;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author Hamza
 */
public class Pitcher extends Player {
    
    final IntegerProperty walks;
    final IntegerProperty saves;
    final IntegerProperty strikeOuts;
    final DoubleProperty earnedRunAverage;
    final DoubleProperty  whip;
    
    
     public Pitcher() {
         
        walks = new SimpleIntegerProperty(0);
        saves = new SimpleIntegerProperty(0);
        strikeOuts = new SimpleIntegerProperty(0);
        earnedRunAverage = new SimpleDoubleProperty(0);
        whip = new SimpleDoubleProperty(0);
        
        
    }
    
    public void reset() {
        setWalks(0);
        setSaves(0);
        setStrikeOuts(0);
        setERA(0);
        setWHIP(0);
                
    }
    
    public int getWalks() {
        return walks.get();
    }
    
    public void setWalks(int initWalks) {
        walks.set(initWalks);
    }
    
    public IntegerProperty walksProperty() {
        return walks;
    }
    
    public int getSaves() {
        return saves.get();
    }
    
    public void setSaves(int initSaves) {
        saves.set(initSaves);
    }
    
    public IntegerProperty savesProperty() {
        return saves;
    }
    
    public int getStrikeOuts() {
        return strikeOuts.get();
    }
    
    public void setStrikeOuts(int initStrikeOuts) {
        strikeOuts.set(initStrikeOuts);
    }
    
    public IntegerProperty strikeOutsProperty() {
        return strikeOuts;
    }
    
    public double getERA() {
        return earnedRunAverage.get();
    }
    
    public void setERA(int initERA) {
        earnedRunAverage.set(initERA);
    }
    
    public DoubleProperty eraProperty() {
        return earnedRunAverage;
    }
    
    public double getWHIP() {
        return whip.get();
    }
    
    public void setWHIP(double initWHIP) {
        whip.set(initWHIP);
    }
    
    public DoubleProperty whipProperty() {
        return whip;
    }
    
}
