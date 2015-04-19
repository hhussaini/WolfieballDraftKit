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
public class Hitter extends Player {
    
    final IntegerProperty runs;
    final IntegerProperty homeRuns;
    final IntegerProperty runsBattedIn;
    final IntegerProperty stolenBases;
    final DoubleProperty  battingAverage;
    
    
     public Hitter() {
         
        runs = new SimpleIntegerProperty(0);
        homeRuns = new SimpleIntegerProperty(0);
        runsBattedIn = new SimpleIntegerProperty(0);
        stolenBases = new SimpleIntegerProperty(0);
        battingAverage = new SimpleDoubleProperty(0);
        
        
    }
    
    public void reset() {
        setRuns(0);
        setHomeRuns(0);
        setRBI(0);
        setSB(0);
        setBA(0);
                
    }
    
    public int getRuns() {
        return runs.get();
    }
    
    public void setRuns(int initRuns) {
        runs.set(initRuns);
    }
    
    public IntegerProperty runsProperty() {
        return runs;
    }
    
    public int getHomeRuns() {
        return homeRuns.get();
    }
    
    public void setHomeRuns(int initHomeRuns) {
        homeRuns.set(initHomeRuns);
    }
    
    public IntegerProperty homeRunsProperty() {
        return homeRuns;
    }
    
    public int getRBI() {
        return runsBattedIn.get();
    }
    
    public void setRBI(int initRBI) {
        runsBattedIn.set(initRBI);
    }
    
    public IntegerProperty rbiProperty() {
        return runsBattedIn;
    }
    
    public int getSB() {
        return stolenBases.get();
    }
    
    public void setSB(int initSB) {
        stolenBases.set(initSB);
    }
    
    public IntegerProperty sbProperty() {
        return stolenBases;
    }
    
    public double getBA() {
        return battingAverage.get();
    }
    
    public void setBA(double initBA) {
        battingAverage.set(initBA);
    }
    
    public DoubleProperty baProperty() {
        return battingAverage;
    }
    
    
}
