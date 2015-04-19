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
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Hamza
 */
public class Player {
    
    final StringProperty firstName;
    final StringProperty lastName;
    final StringProperty position;
    final IntegerProperty yearOfBirth;
    final StringProperty proTeam;
    final StringProperty notes;
    
    final IntegerProperty rw;
    final IntegerProperty hrsv;
    final IntegerProperty rbik;
    final DoubleProperty sbera;
    final DoubleProperty bawhip;
    final DoubleProperty estimatedValue;
    
   
    
    public Player() {
        firstName = new SimpleStringProperty("");
        lastName = new SimpleStringProperty("");
        proTeam = new SimpleStringProperty("");
        position = new SimpleStringProperty("");
        yearOfBirth = new SimpleIntegerProperty(0);
        notes = new SimpleStringProperty("");
        
        rw = new SimpleIntegerProperty(0);
        hrsv = new SimpleIntegerProperty(0);
        rbik = new SimpleIntegerProperty(0);
        sbera = new SimpleDoubleProperty(0);
        bawhip = new SimpleDoubleProperty(0);
        estimatedValue = new SimpleDoubleProperty(0);
        
        
    }
    
    public void reset() {
        setFirstName("");
        setLastName("");
        setTeam("");
        setPosition("");
        setYear(0);
        setNotes("");
        
        
        
    }
    
    public String getFirstName() {
        return firstName.get();
    }
    
    public void setFirstName(String initFirstName) {
        firstName.set(initFirstName);
    }
    
    public StringProperty firstNameProperty() {
        return firstName;
    }
    
    public String getLastName() {
        return lastName.get();
    }
    
    public void setLastName(String initLastName) {
        lastName.set(initLastName);
    }
    
    public StringProperty lastNameProperty() {
        return lastName;
    }
    
    public String getTeam() {
        return proTeam.get();
    }
    
    public void setTeam(String initTeam) {
        proTeam.set(initTeam);
    }
    
    public StringProperty teamProperty() {
        return proTeam;
    }
    
    public String getPosition() {
        return position.get();
    }
    
    public void setPosition(String initPosition) {
        position.set(initPosition);
    }
    
    public StringProperty positionProperty() {
        return position;
    }
    
    public int getYear() {
        return yearOfBirth.get();
    }
    
    public void setYear(int initYear) {
        yearOfBirth.set(initYear);
    }
    
    public IntegerProperty yearProperty() {
        return yearOfBirth;
    }
    
    public String getNotes() {
        return notes.get();
    }
    
    public void setNotes(String initNotes) {
        notes.set(initNotes);
    }
    
    public StringProperty notesProperty() {
        return notes;
    }
    
    
    public int getRw() {
        return rw.get();
    }
    
    public void setRw(int initRw) {
        rw.set(initRw);
    }
    
    public IntegerProperty rwProperty() {
        return rw;
    }
    
    
    public int getHrsv() {
        return hrsv.get();
    }
    
    public void setHrsv(int initHrsv) {
        hrsv.set(initHrsv);
    }
    
    public IntegerProperty hrsvProperty() {
        return hrsv;
    }
    
    
    public int getRbik() {
        return rbik.get();
    }
    
    public void setRbik(int initRbik) {
        rbik.set(initRbik);
    }
    
    public IntegerProperty rbikProperty() {
        return rbik;
    }
    
    
    public Double getSbera() {
        return sbera.get();
    }
    
    public void setSbera(Double initSbera) {
        sbera.set(initSbera);
    }
    
    public DoubleProperty sberaProperty() {
        return sbera;
    }
    
    
    public Double getBawhip() {
        return bawhip.get();
    }
    
    public void setBawhip(Double initBawhip) {
        bawhip.set(initBawhip);
    }
    
    public DoubleProperty bawhipProperty() {
        return bawhip;
    }
    
    
    public Double getEstimatedValue() {
        return estimatedValue.get();
    }
    
    public void setEstimatedValue(Double initEstimatedValue) {
        estimatedValue.set(initEstimatedValue);
    }
    
    public DoubleProperty estimatedValueProperty() {
        return estimatedValue;
    }
    
    
    
    
}
