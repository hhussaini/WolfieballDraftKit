/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wolfieballdraftkit;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import properties_manager.PropertiesManager;
import wbk.data.DraftDataManager;
import wbk.error.ErrorHandler;
import wbk.file.JsonDraftFileManager;
import static wolfieballdraftkit.WBK_PropertyType.*;


import wbk.gui.WBK_GUI;
import static wolfieballdraftkit.WBK_StartupConstants.PATH_DATA;
import static wolfieballdraftkit.WBK_StartupConstants.PROPERTIES_FILE_NAME;
import static wolfieballdraftkit.WBK_StartupConstants.PROPERTIES_SCHEMA_FILE_NAME;
import xml_utilities.InvalidXMLFileFormatException;


/**
 *
 * @author Hamza
 */
public class WolfieballDraftKit extends Application {
    
    
    WBK_GUI gui;
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        
        
        ErrorHandler eH = ErrorHandler.getErrorHandler();
        eH.initMessageDialog(primaryStage);
        
        
        JsonDraftFileManager jsonFileManager = new JsonDraftFileManager();
        
        boolean success = loadProperties();
        
        if (success) {
            
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        String appTitle = props.getProperty(PROP_APP_TITLE);    
            
            
        gui = new WBK_GUI(primaryStage);
        gui.setDraftFileManager(jsonFileManager);
                
        
        DraftDataManager dataManager = new DraftDataManager(gui); 
        gui.setDataManager(dataManager);
        
        
        gui.initGUI(appTitle);
        
        }
        
    }

    
    public boolean loadProperties() {
        try {
            // LOAD THE SETTINGS FOR STARTING THE APP
            PropertiesManager props = PropertiesManager.getPropertiesManager();
            props.addProperty(PropertiesManager.DATA_PATH_PROPERTY, PATH_DATA);
            
            props.loadProperties(PROPERTIES_FILE_NAME, PROPERTIES_SCHEMA_FILE_NAME);
            return true;
       } catch (InvalidXMLFileFormatException ixmlffe) {
            // SOMETHING WENT WRONG INITIALIZING THE XML FILE
            ErrorHandler eH = ErrorHandler.getErrorHandler();
            eH.handlePropertiesFileError();
            return false;
        }        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
