/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wbk.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import properties_manager.PropertiesManager;


import wbk.controller.DraftEditController;
import wbk.controller.DraftFileManager;
import wbk.data.DraftDataView;

import wbk.data.DraftDataManager;
import wbk.controller.FileController;
import wbk.controller.PlayerEditController;
import wbk.data.Draft;
import wbk.data.Player;

import wolfieballdraftkit.WBK_PropertyType;
import static wolfieballdraftkit.WBK_StartupConstants.CLOSE_BUTTON_LABEL;
import static wolfieballdraftkit.WBK_StartupConstants.PATH_CSS;
import static wolfieballdraftkit.WBK_StartupConstants.PATH_IMAGES;


/**
 *
 * @author Hamza
 */
public class WBK_GUI implements DraftDataView {
    
    
   
    static final String PRIMARY_STYLE_SHEET = PATH_CSS + "csb_style.css";
    static final String CLASS_BORDERED_PANE = "bordered_pane";
    static final String CLASS_SUBJECT_PANE = "subject_pane";
    static final String CLASS_HEADING_LABEL = "heading_label";
    static final String CLASS_SUBHEADING_LABEL = "subheading_label";
    static final String CLASS_PROMPT_LABEL = "prompt_label";
    static final String EMPTY_TEXT = "";
    static final int LARGE_TEXT_FIELD_LENGTH = 50;
    static final int SMALL_TEXT_FIELD_LENGTH = 5;

    // THIS MANAGES ALL OF THE APPLICATION'S DATA
    DraftDataManager dataManager;

    // THIS MANAGES COURSE FILE I/O
    DraftFileManager draftFileManager;

    // THIS MANAGES EXPORTING OUR SITE PAGES
    //DraftSiteExporter siteExporter;

    // THIS HANDLES INTERACTIONS WITH FILE-RELATED CONTROLS
    FileController fileController;

    // THIS HANDLES INTERACTIONS WITH COURSE INFO CONTROLS
    //DraftEditController draftController;
    
    // THIS HANDLES REQUESTS TO ADD OR EDIT SCHEDULE STUFF
    PlayerEditController playerController;
    
    
    
    
    
    

    // THIS IS THE APPLICATION WINDOW
    Stage primaryStage;

    // THIS IS THE STAGE'S SCENE GRAPH
    Scene primaryScene;

    // THIS PANE ORGANIZES THE BIG PICTURE CONTAINERS FOR THE
    // APPLICATION GUI
    BorderPane wbkPane;
    
    // THIS IS THE TOP TOOLBAR AND ITS CONTROLS
    FlowPane fileToolbarPane;
    Button newDraftButton;
    Button loadDraftButton;
    Button saveDraftButton;
    Button exportDraftButton;
    Button exitButton;

    FlowPane gameToolbarPane;
    
    Button homeButton;
    Button playerButton;
    Button summaryButton;
    Button standingButton;
    Button teamButton;
    
    
    
    
    
    // WE'LL ORGANIZE OUR WORKSPACE COMPONENTS USING A BORDER PANE
    BorderPane workspacePane;
    boolean workspaceActivated;
    
    // WE'LL PUT THE WORKSPACE INSIDE A SCROLL PANE
    ScrollPane workspaceScrollPane;

    // WE'LL PUT THIS IN THE TOP OF THE WORKSPACE, IT WILL
    // HOLD TWO OTHER PANES FULL OF CONTROLS AS WELL AS A LABEL
    VBox topWorkspacePane;
    Label courseHeadingLabel;
    SplitPane topWorkspaceSplitPane;

    // THESE ARE THE CONTROLS FOR THE BASIC SCHEDULE PAGE HEADER INFO
    GridPane courseInfoPane;
    Label courseInfoLabel;
    Label courseSubjectLabel;
    ComboBox courseSubjectComboBox;
    Label courseNumberLabel;
    TextField courseNumberTextField;
    Label courseSemesterLabel;
    ComboBox courseSemesterComboBox;
    Label courseYearLabel;
    ComboBox courseYearComboBox;
    Label courseTitleLabel;
    TextField courseTitleTextField;
    Label instructorNameLabel;
    TextField instructorNameTextField;
    Label instructorURLLabel;
    TextField instructorURLTextField;

    // THESE ARE THE CONTROLS FOR SELECTING WHICH PAGES THE SCHEDULE
    // PAGE WILL HAVE TO LINK TO
    VBox pagesSelectionPane;
    Label pagesSelectionLabel;
    CheckBox indexPageCheckBox;
    CheckBox syllabusPageCheckBox;
    CheckBox schedulePageCheckBox;
    CheckBox hwsPageCheckBox;
    CheckBox projectsPageCheckBox;

    // SCHEDULE CONTROLS
    VBox schedulePane;
    VBox scheduleInfoPane;
    Label scheduleInfoHeadingLabel;
    SplitPane splitScheduleInfoPane;

    // THESE GUYS GO IN THE LEFT HALF OF THE splitScheduleInfoPane
    GridPane dateBoundariesPane;
    Label dateBoundariesLabel;
    Label startDateLabel;
 
    Label endDateLabel;
  

    // THESE GUYS GO IN THE RIGHT HALF OF THE splitScheduleInfoPane
    VBox lectureDaySelectorPane;
    Label lectureDaySelectLabel;
    CheckBox mondayCheckBox;
    CheckBox tuesdayCheckBox;
    CheckBox wednesdayCheckBox;
    CheckBox thursdayCheckBox;
    CheckBox fridayCheckBox;
    
    // THIS REGION IS FOR MANAGING SCHEDULE ITEMS OTHER THAN LECTURES AND HWS
    VBox scheduleItemsBox;
    HBox scheduleItemsToolbar;
    Button addScheduleItemButton;
    Button removeScheduleItemButton;
    Label scheduleItemsLabel;
   // TableView<ScheduleItem> scheduleItemsTable;
    TableColumn itemDescriptionsColumn;
    TableColumn itemDatesColumn;
    TableColumn linkColumn;
    
    
   
    VBox lectureBox;
    HBox lectureToolbar;
    Button addLectureButton;
    Button removeLectureButton;
    Label lectureLabel;
    //TableView<Lecture> lectureTable;
    TableColumn itemTopicsColumn;
    TableColumn itemSessionsColumn;
    Button moveUpButton;
    Button moveDownButton;
    
    
    
    VBox assignmentBox;
    HBox assignmentToolbar;
    Button addAssignmentButton;
    Button removeAssignmentButton;
    Label assignmentLabel;
    //TableView<Assignment> assignmentTable;
    TableColumn assignmentNameColumn;
    TableColumn assignmentDatesColumn;
    TableColumn assignmentTopicColumn;
    
    
    VBox playerBox;
    HBox playerTop;
    Label playerScreenLabel;
    TextField searchTextField;
    Label searchLabel;
    RadioButton all;
    RadioButton oneB;
    RadioButton cOne;
    RadioButton threeB;
    RadioButton twoB;
    RadioButton mi;
    RadioButton ss;
    RadioButton of;
    RadioButton u;
    RadioButton p;
    RadioButton c;
    HBox radioButtons;
    ToggleGroup buttons;
    
  
    TableView<Player> playerTable;
    TableColumn firstName;
    TableColumn lastName;
    TableColumn team;
    TableColumn position;
    TableColumn year;
    TableColumn rw;
    TableColumn hrsv;
    TableColumn rbik;
    TableColumn sbera;
    TableColumn bawhip;
    TableColumn estimatedValue;
    TableColumn notes;
    
    
    VBox fantasyTeamBox;
    VBox standingsBox;
    VBox summaryBox;
    VBox mlbTeamBox;
    
    Label fantasyTeamLabel;
    Label standingsLabel;
    Label summaryLabel;
    Label mlbTeamLabel;
    
    
       
    // AND TABLE COLUMNS
    static final String COL_FIRSTNAME = "First Name";
    static final String COL_LASTNAME = "Last Name";
    static final String COL_TEAM = "Team";
    static final String COL_POSITION = "Position";
    static final String COL_YEAR = "Year Of Birth";
    static final String COL_RW = "R/W";
    static final String COL_HRSV = "HR/SV";
    static final String COL_RBIK = "RBI/K";
    static final String COL_SBERA = "SB/ERA";
    static final String COL_BAWHIP = "BA/WHIP";
    static final String COL_ESTIMATEDVALUE = "Estimated Value";
    static final String COL_NOTES = "Notes";
    
    // HERE ARE OUR DIALOGS
    MessageDialog messageDialog;
    YesNoCancelDialog yesNoCancelDialog;
    
    /**
     * Constructor for making this GUI, note that it does not initialize the UI
     * controls. To do that, call initGUI.
     *
     * @param initPrimaryStage Window inside which the GUI will be displayed.
     */
    public WBK_GUI(Stage initPrimaryStage) {
        primaryStage = initPrimaryStage;
    }

    /**
     * Accessor method for the data manager.
     *
     * @return The CourseDataManager used by this UI.
     */
    public DraftDataManager getDataManager() {
        return dataManager;
    }

    /**
     * Accessor method for the file controller.
     *
     * @return The FileController used by this UI.
     */
    public FileController getFileController() {
        return fileController;
    }

    /**
     * Accessor method for the course file manager.
     *
     * @return The CourseFileManager used by this UI.
     */
    public DraftFileManager getCourseFileManager() {
       return draftFileManager;
    }

    /**
     * Accessor method for the site exporter.
     *
     * @return The CourseSiteExporter used by this UI.
     */
   // public CourseSiteExporter getSiteExporter() {
   //     return siteExporter;
   // }

    /**
     * Accessor method for the window (i.e. stage).
     *
     * @return The window (i.e. Stage) used by this UI.
     */
    public Stage getWindow() {
        return primaryStage;
    }
    
    public MessageDialog getMessageDialog() {
        return messageDialog;
    }
    
    public YesNoCancelDialog getYesNoCancelDialog() {
        return yesNoCancelDialog;
    }

    /**
     * Mutator method for the data manager.
     *
     * @param initDataManager The CourseDataManager to be used by this UI.
     */
    public void setDataManager(DraftDataManager initDataManager) {
        dataManager = initDataManager;
    }

    /**
     * Mutator method for the course file manager.
     *
     * @param initCourseFileManager The CourseFileManager to be used by this UI.
     */
    public void setDraftFileManager(DraftFileManager initCourseFileManager) {
       draftFileManager = initCourseFileManager;
    }

    /**
     * Mutator method for the site exporter.
     *
     * @param initSiteExporter The CourseSiteExporter to be used by this UI.
     */
    //public void setSiteExporter(CourseSiteExporter initSiteExporter) {
    //    siteExporter = initSiteExporter;
    //}

    /**
     * This method fully initializes the user interface for use.
     *
     * @param windowTitle The text to appear in the UI window's title bar.
     * @param subjects The list of subjects to choose from.
     * @throws IOException Thrown if any initialization files fail to load.
     */
    public void initGUI(String windowTitle) throws IOException {
        // INIT THE DIALOGS
        initDialogs();
        
        // INIT THE TOOLBAR
        initFileToolbar();

        // INIT THE CENTER WORKSPACE CONTROLS BUT DON'T ADD THEM
        // TO THE WINDOW YET
        initWorkspace();

        // NOW SETUP THE EVENT HANDLERS
        initEventHandlers();

        // AND FINALLY START UP THE WINDOW (WITHOUT THE WORKSPACE)
        initWindow(windowTitle);
    }

    /**
     * When called this function puts the workspace into the window,
     * revealing the controls for editing a Course.
     */
    public void activateWorkspace() {
        if (!workspaceActivated) {
            // PUT THE WORKSPACE IN THE GUI
            wbkPane.setCenter(workspacePane);
            workspaceActivated = true;
        }
    }
    
    /**
     * This function takes all of the data out of the courseToReload 
     * argument and loads its values into the user interface controls.
     * 
     * @param courseToReload The Course whose data we'll load into the GUI.
     */
    //@Override
    public void reloadDraft(Draft draftToReload) {
        // FIRST ACTIVATE THE WORKSPACE IF NECESSARY
        if (!workspaceActivated) {
            activateWorkspace();
        }

        // WE DON'T WANT TO RESPOND TO EVENTS FORCED BY
        // OUR INITIALIZATION SELECTIONS
        //draftController.enable(false);

        // FIRST LOAD ALL THE BASIC COURSE INFO
        /*courseSubjectComboBox.setValue(courseToReload.getSubject());
        courseNumberTextField.setText("" + courseToReload.getNumber());
        courseSemesterComboBox.setValue(courseToReload.getSemester());
        courseYearComboBox.setValue(courseToReload.getYear());
        courseTitleTextField.setText(courseToReload.getTitle());
        instructorNameTextField.setText(courseToReload.getInstructor().getName());
        instructorURLTextField.setText(courseToReload.getInstructor().getHomepageURL());
        indexPageCheckBox.setSelected(courseToReload.hasCoursePage(CoursePage.INDEX));
        syllabusPageCheckBox.setSelected(courseToReload.hasCoursePage(CoursePage.SYLLABUS));
        schedulePageCheckBox.setSelected(courseToReload.hasCoursePage(CoursePage.SCHEDULE));
        hwsPageCheckBox.setSelected(courseToReload.hasCoursePage(CoursePage.HWS));
        projectsPageCheckBox.setSelected(courseToReload.hasCoursePage(CoursePage.PROJECTS));

        // THEN THE DATE PICKERS
        LocalDate startDate = courseToReload.getStartingMonday();
        startDatePicker.setValue(startDate);
        LocalDate endDate = courseToReload.getEndingFriday();
        endDatePicker.setValue(endDate);

        // THE LECTURE DAY CHECK BOXES
        mondayCheckBox.setSelected(courseToReload.hasLectureDay(DayOfWeek.MONDAY));
        tuesdayCheckBox.setSelected(courseToReload.hasLectureDay(DayOfWeek.TUESDAY));
        wednesdayCheckBox.setSelected(courseToReload.hasLectureDay(DayOfWeek.WEDNESDAY));
        thursdayCheckBox.setSelected(courseToReload.hasLectureDay(DayOfWeek.THURSDAY));
        fridayCheckBox.setSelected(courseToReload.hasLectureDay(DayOfWeek.FRIDAY));
        
        */
        // THE SCHEDULE ITEMS TABLE
       
        // THE LECTURES TABLE
        
        // THE HWS TABLE

        // NOW WE DO WANT TO RESPOND WHEN THE USER INTERACTS WITH OUR CONTROLS
        //fileController.enable(true);
    }

    
    /**
     * This method is used to activate/deactivate toolbar buttons when
     * they can and cannot be used so as to provide foolproof design.
     * 
     * @param saved Describes whether the loaded Course has been saved or not.
     */
    public void updateToolbarControls(boolean saved) {
        // THIS TOGGLES WITH WHETHER THE CURRENT COURSE
        // HAS BEEN SAVED OR NOT
        saveDraftButton.setDisable(saved);

        // ALL THE OTHER BUTTONS ARE ALWAYS ENABLED
        // ONCE EDITING THAT FIRST COURSE BEGINS
        loadDraftButton.setDisable(false);
        exportDraftButton.setDisable(false);
        
        
        homeButton.setDisable(false);
        playerButton.setDisable(false);
        summaryButton.setDisable(false);
        standingButton.setDisable(false);
        teamButton.setDisable(false);
        

        // NOTE THAT THE NEW, LOAD, AND EXIT BUTTONS
        // ARE NEVER DISABLED SO WE NEVER HAVE TO TOUCH THEM
    }

    /**
     * This function loads all the values currently in the user interface
     * into the course argument.
     * 
     * @param course The course to be updated using the data from the UI controls.
     */
    
    /*
    public void updateCourseInfo(Course course) {
        course.setSubject(Subject.valueOf(courseSubjectComboBox.getSelectionModel().getSelectedItem().toString()));
        course.setNumber(Integer.parseInt(courseNumberTextField.getText()));
        course.setSemester(Semester.valueOf(courseSemesterComboBox.getSelectionModel().getSelectedItem().toString()));
        course.setYear((int) courseYearComboBox.getSelectionModel().getSelectedItem());
        course.setTitle(courseTitleTextField.getText());
        Instructor instructor = course.getInstructor();
        instructor.setName(instructorNameTextField.getText());
        instructor.setHomepageURL(instructorURLTextField.getText());
        updatePageUsingCheckBox(indexPageCheckBox, course, CoursePage.INDEX);
        updatePageUsingCheckBox(syllabusPageCheckBox, course, CoursePage.SYLLABUS);
        updatePageUsingCheckBox(schedulePageCheckBox, course, CoursePage.SCHEDULE);
        updatePageUsingCheckBox(hwsPageCheckBox, course, CoursePage.HWS);
        updatePageUsingCheckBox(projectsPageCheckBox, course, CoursePage.PROJECTS);
        course.setStartingMonday(startDatePicker.getValue());
        course.setEndingFriday(endDatePicker.getValue());
        course.selectLectureDay(DayOfWeek.MONDAY, mondayCheckBox.isSelected());
        course.selectLectureDay(DayOfWeek.TUESDAY, tuesdayCheckBox.isSelected());
        course.selectLectureDay(DayOfWeek.WEDNESDAY, wednesdayCheckBox.isSelected());
        course.selectLectureDay(DayOfWeek.THURSDAY, thursdayCheckBox.isSelected());
        course.selectLectureDay(DayOfWeek.FRIDAY, fridayCheckBox.isSelected());
    }
    */

    /****************************************************************************/
    /* BELOW ARE ALL THE PRIVATE HELPER METHODS WE USE FOR INITIALIZING OUR GUI */
    /****************************************************************************/
    
    private void initDialogs() {
        messageDialog = new MessageDialog(primaryStage, CLOSE_BUTTON_LABEL);
        yesNoCancelDialog = new YesNoCancelDialog(primaryStage);
    }
    
    /**
     * This function initializes all the buttons in the toolbar at the top of
     * the application window. These are related to file management.
     */
    private void initFileToolbar() {
        
        fileToolbarPane = new FlowPane();
        
        gameToolbarPane = new FlowPane();
        
        // HERE ARE OUR FILE TOOLBAR BUTTONS, NOTE THAT SOME WILL
        // START AS ENABLED (false), WHILE OTHERS DISABLED (true)
        newDraftButton = initChildButton(fileToolbarPane, WBK_PropertyType.NEW_COURSE_ICON, WBK_PropertyType.NEW_COURSE_TOOLTIP, false);
        loadDraftButton = initChildButton(fileToolbarPane, WBK_PropertyType.LOAD_COURSE_ICON, WBK_PropertyType.LOAD_COURSE_TOOLTIP, false);
        saveDraftButton = initChildButton(fileToolbarPane, WBK_PropertyType.SAVE_COURSE_ICON, WBK_PropertyType.SAVE_COURSE_TOOLTIP, true);
        exportDraftButton = initChildButton(fileToolbarPane, WBK_PropertyType.EXPORT_PAGE_ICON, WBK_PropertyType.EXPORT_PAGE_TOOLTIP, true);
        exitButton = initChildButton(fileToolbarPane, WBK_PropertyType.EXIT_ICON, WBK_PropertyType.EXIT_TOOLTIP, false);
        
        homeButton = initChildButton(gameToolbarPane, WBK_PropertyType.HOME_ICON, WBK_PropertyType.HOME_TOOLTIP, true);
        playerButton = initChildButton(gameToolbarPane, WBK_PropertyType.PLAYER_ICON, WBK_PropertyType.PLAYER_TOOLTIP, true);
        summaryButton = initChildButton(gameToolbarPane, WBK_PropertyType.SUMMARY_ICON, WBK_PropertyType.SUMMARY_TOOLTIP, true);
        standingButton = initChildButton(gameToolbarPane, WBK_PropertyType.STANDING_ICON, WBK_PropertyType.STANDING_TOOLTIP, true);
        teamButton = initChildButton(gameToolbarPane, WBK_PropertyType.TEAM_ICON, WBK_PropertyType.TEAM_TOOLTIP, true);
        
        
        
    }

    // CREATES AND SETS UP ALL THE CONTROLS TO GO IN THE APP WORKSPACE
    private void initWorkspace() throws IOException {
        // THE WORKSPACE HAS A FEW REGIONS, THIS 
        // IS FOR BASIC COURSE EDITING CONTROLS
       // initBasicCourseInfoControls();

        // THIS IS FOR SELECTING PAGE LINKS TO INCLUDE
        //initPageSelectionControls();

        // THE TOP WORKSPACE HOLDS BOTH THE BASIC COURSE INFO
        // CONTROLS AS WELL AS THE PAGE SELECTION CONTROLS
        initTopWorkspace();

        // THIS IS FOR MANAGING SCHEDULE EDITING
        //initScheduleItemsControls();

        // THIS HOLDS ALL OUR WORKSPACE COMPONENTS, SO NOW WE MUST
        // ADD THE COMPONENTS WE'VE JUST INITIALIZED
        workspacePane = new BorderPane();
       //workspacePane.setTop(topWorkspacePane);
        workspacePane.setCenter(fantasyTeamBox);
        workspacePane.getStyleClass().add(CLASS_BORDERED_PANE);
        
        // AND NOW PUT IT IN THE WORKSPACE
        

        // NOTE THAT WE HAVE NOT PUT THE WORKSPACE INTO THE WINDOW,
        // THAT WILL BE DONE WHEN THE USER EITHER CREATES A NEW
        // COURSE OR LOADS AN EXISTING ONE FOR EDITING
        workspaceActivated = false;
    }
    
    // INITIALIZES THE TOP PORTION OF THE WORKWPACE UI
    private void initTopWorkspace() {
        // HERE'S THE SPLIT PANE, ADD THE TWO GROUPS OF CONTROLS
        
        
        //playerTop.getItems().add(courseInfoPane);
        

        // THE TOP WORKSPACE PANE WILL ONLY DIRECTLY HOLD 2 THINGS, A LABEL
        // AND A SPLIT PANE, WHICH WILL HOLD 2 ADDITIONAL GROUPS OF CONTROLS
        playerBox = new VBox();
        playerBox.getStyleClass().add(CLASS_BORDERED_PANE);
        
        
        playerTop = new HBox();
        playerScreenLabel = initLabel(WBK_PropertyType.PLAYER_SCREEN, CLASS_HEADING_LABEL);
        
    
        
        fantasyTeamBox = new VBox();
        fantasyTeamBox.getStyleClass().add(CLASS_BORDERED_PANE);
        fantasyTeamLabel = initLabel(WBK_PropertyType.FANTASY_TEAM_SCREEN, CLASS_HEADING_LABEL);
        fantasyTeamBox.getChildren().add(fantasyTeamLabel);
        
        standingsBox = new VBox();
        standingsBox.getStyleClass().add(CLASS_BORDERED_PANE);
        standingsLabel = initLabel(WBK_PropertyType.STANDINGS_SCREEN, CLASS_HEADING_LABEL);
        standingsBox.getChildren().add(standingsLabel);
        
        summaryBox = new VBox();
        summaryBox.getStyleClass().add(CLASS_BORDERED_PANE);
        summaryLabel = initLabel(WBK_PropertyType.SUMMARY_SCREEN, CLASS_HEADING_LABEL);
        summaryBox.getChildren().add(summaryLabel);
        
        mlbTeamBox = new VBox();
        mlbTeamBox.getStyleClass().add(CLASS_BORDERED_PANE);
        mlbTeamLabel = initLabel(WBK_PropertyType.MLB_TEAM_SCREEN, CLASS_HEADING_LABEL);
        mlbTeamBox.getChildren().add(mlbTeamLabel);
        
        
        
        
        
        
        addLectureButton = initChildButton(playerTop, WBK_PropertyType.ADD_ICON, WBK_PropertyType.ADD_ITEM_TOOLTIP, false);
        removeLectureButton = initChildButton(playerTop, WBK_PropertyType.MINUS_ICON, WBK_PropertyType.REMOVE_ITEM_TOOLTIP, false);
        
        
        searchTextField = new TextField();       
        searchTextField.setPrefColumnCount(LARGE_TEXT_FIELD_LENGTH);
        searchTextField.setText(EMPTY_TEXT);
        searchTextField.setEditable(true);
        
        searchLabel = initLabel(WBK_PropertyType.SEARCH, CLASS_SUBHEADING_LABEL);
        
        radioButtons = new HBox();
        
        all = new RadioButton("All");
        all.setId("ALL");
        oneB = new RadioButton("1B");
        oneB.setId("1B");
        cOne = new RadioButton("C1");
        cOne.setId("C1");
        threeB = new RadioButton("3B");
        threeB.setId("3B");
        twoB = new RadioButton("2B");
        twoB.setId("2B");
        mi = new RadioButton("MI");
        mi.setId("MI");
        ss = new RadioButton("SS");
        ss.setId("SS");
        of = new RadioButton("OF");
        of.setId("OF");
        u = new RadioButton("U");
        u.setId("U");
        p = new RadioButton("P");
        p.setId("P");
        c = new RadioButton("C");
        c.setId("C");
        buttons = new ToggleGroup();
        
        all.setToggleGroup(buttons);
        oneB.setToggleGroup(buttons);
        cOne.setToggleGroup(buttons);
        threeB.setToggleGroup(buttons);
        twoB.setToggleGroup(buttons);
        mi.setToggleGroup(buttons);
        ss.setToggleGroup(buttons);
        of.setToggleGroup(buttons);
        u.setToggleGroup(buttons);
        p.setToggleGroup(buttons);
        c.setToggleGroup(buttons);
        
         
        
        radioButtons.getChildren().addAll(all, oneB, cOne, threeB, twoB, mi, ss, of, u, p, c);
        
        
        
        playerTable = new TableView();
        
        
        firstName = new TableColumn(COL_FIRSTNAME);
        lastName = new TableColumn(COL_LASTNAME);
        year = new TableColumn(COL_YEAR);
        team = new TableColumn(COL_TEAM);
        rw = new TableColumn(COL_RW);
        rbik = new TableColumn(COL_RBIK);
        sbera = new TableColumn(COL_SBERA);
        bawhip = new TableColumn(COL_BAWHIP);
        estimatedValue = new TableColumn(COL_ESTIMATEDVALUE);
        notes = new TableColumn(COL_NOTES);
        position = new TableColumn(COL_POSITION);
        hrsv = new TableColumn(COL_HRSV);
        
        firstName.setCellValueFactory(new PropertyValueFactory<String, String>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<String, String>("lastName"));
        year.setCellValueFactory(new PropertyValueFactory<Integer, String>("year"));
        team.setCellValueFactory(new PropertyValueFactory<String, String>("team"));
        position.setCellValueFactory(new PropertyValueFactory<String, String>("position"));
        rw.setCellValueFactory(new PropertyValueFactory<Integer, String>("rw"));
        rbik.setCellValueFactory(new PropertyValueFactory<Integer, String>("rbik"));
        sbera.setCellValueFactory(new PropertyValueFactory<Double, String>("sbera"));
        bawhip.setCellValueFactory(new PropertyValueFactory<Double, String>("bawhip"));
        estimatedValue.setCellValueFactory(new PropertyValueFactory<Double, String>("estimatedValue"));
        notes.setCellValueFactory(new PropertyValueFactory<String, String>("notes"));
        hrsv.setCellValueFactory(new PropertyValueFactory<Integer, String>("hrsv"));
        
        
        
        
        
        
        
        playerTable.getColumns().add(firstName);
        playerTable.getColumns().add(lastName);
        playerTable.getColumns().add(team);
        playerTable.getColumns().add(position);
        playerTable.getColumns().add(year);
        playerTable.getColumns().add(rw);
        playerTable.getColumns().add(hrsv);
        playerTable.getColumns().add(rbik);
        playerTable.getColumns().add(sbera);
        playerTable.getColumns().add(bawhip);
        playerTable.getColumns().add(estimatedValue);
        playerTable.getColumns().add(notes);
        playerTable.setItems(dataManager.getDraft().getPlayers());
        
        
        
        playerTop.getChildren().add(searchLabel);
        playerTop.getChildren().add(searchTextField);
         
        
        playerBox.getChildren().add(playerScreenLabel);
        playerBox.getChildren().add(playerTop);
        playerBox.getChildren().add(radioButtons);
        playerBox.getChildren().add(playerTable);
        
        // HERE'S THE LABEL
       // courseHeadingLabel = initChildLabel(topWorkspacePane, WBK_PropertyType.COURSE_HEADING_LABEL, CLASS_HEADING_LABEL);

        // AND NOW ADD THE SPLIT PANE
        //topWorkspacePane.getChildren().add(topWorkspaceSplitPane);
    }

    // INITIALIZES THE CONTROLS IN THE LEFT HALF OF THE TOP WORKSPACE
    
    
    private void initBasicCourseInfoControls() throws IOException {
        // THESE ARE THE CONTROLS FOR THE BASIC SCHEDULE PAGE HEADER INFO
        // WE'LL ARRANGE THEM IN THE LEFT SIDE IN A VBox
        courseInfoPane = new GridPane();

        // FIRST THE HEADING LABEL
        courseInfoLabel = initGridLabel(courseInfoPane, WBK_PropertyType.COURSE_INFO_LABEL, CLASS_SUBHEADING_LABEL, 0, 0, 4, 1);

       // THEN CONTROLS FOR CHOOSING THE SUBJECT
       courseSubjectLabel = initGridLabel(courseInfoPane, WBK_PropertyType.COURSE_SUBJECT_LABEL, CLASS_PROMPT_LABEL, 0, 1, 1, 1);
       courseSubjectComboBox = initGridComboBox(courseInfoPane, 1, 1, 1, 1);
 
       
        // THEN CONTROLS FOR UPDATING THE COURSE NUMBER
        courseNumberLabel = initGridLabel(courseInfoPane, WBK_PropertyType.COURSE_NUMBER_LABEL, CLASS_PROMPT_LABEL, 2, 1, 1, 1);
        courseNumberTextField = initGridTextField(courseInfoPane, SMALL_TEXT_FIELD_LENGTH, EMPTY_TEXT, true, 3, 1, 1, 1);

        // THEN THE COURSE SEMESTER
        courseSemesterLabel = initGridLabel(courseInfoPane, WBK_PropertyType.COURSE_SEMESTER_LABEL, CLASS_PROMPT_LABEL, 0, 2, 1, 1);
        courseSemesterComboBox = initGridComboBox(courseInfoPane, 1, 2, 1, 1);
        ObservableList<String> semesterChoices = FXCollections.observableArrayList();
      
        courseSemesterComboBox.setItems(semesterChoices);

        // THEN THE COURSE YEAR
        courseYearLabel = initGridLabel(courseInfoPane, WBK_PropertyType.COURSE_YEAR_LABEL, CLASS_PROMPT_LABEL, 2, 2, 1, 1);
        courseYearComboBox = initGridComboBox(courseInfoPane, 3, 2, 1, 1);
        ObservableList<Integer> yearChoices = FXCollections.observableArrayList();
       
        courseYearComboBox.setItems(yearChoices);

        // THEN THE COURSE TITLE
        courseTitleLabel = initGridLabel(courseInfoPane, WBK_PropertyType.COURSE_TITLE_LABEL, CLASS_PROMPT_LABEL, 0, 3, 1, 1);
        courseTitleTextField = initGridTextField(courseInfoPane, LARGE_TEXT_FIELD_LENGTH, EMPTY_TEXT, true, 1, 3, 3, 1);

        // THEN THE INSTRUCTOR NAME
        instructorNameLabel = initGridLabel(courseInfoPane, WBK_PropertyType.INSTRUCTOR_NAME_LABEL, CLASS_PROMPT_LABEL, 0, 4, 1, 1);
        instructorNameTextField = initGridTextField(courseInfoPane, LARGE_TEXT_FIELD_LENGTH, EMPTY_TEXT, true, 1, 4, 3, 1);

        // AND THE INSTRUCTOR HOMEPAGE
        instructorURLLabel = initGridLabel(courseInfoPane, WBK_PropertyType.INSTRUCTOR_URL_LABEL, CLASS_PROMPT_LABEL, 0, 5, 1, 1);
        instructorURLTextField = initGridTextField(courseInfoPane, LARGE_TEXT_FIELD_LENGTH, EMPTY_TEXT, true, 1, 5, 3, 1);
   }

    // INITIALIZES THE CONTROLS IN THE RIGHT HALF OF THE TOP WORKSPACE
    private void initPageSelectionControls() {
        // THESE ARE THE CONTROLS FOR SELECTING WHICH PAGES THE SCHEDULE
        // PAGE WILL HAVE TO LINK TO
        pagesSelectionPane = new VBox();
        pagesSelectionPane.getStyleClass().add(CLASS_SUBJECT_PANE);
        pagesSelectionLabel = initChildLabel(pagesSelectionPane, WBK_PropertyType.PAGES_SELECTION_HEADING_LABEL, CLASS_SUBHEADING_LABEL);
        //indexPageCheckBox = initChildCheckBox(pagesSelectionPane, CourseSiteExporter.INDEX_PAGE);
        //syllabusPageCheckBox = initChildCheckBox(pagesSelectionPane, CourseSiteExporter.SYLLABUS_PAGE);
       // schedulePageCheckBox = initChildCheckBox(pagesSelectionPane, CourseSiteExporter.SCHEDULE_PAGE);
        //hwsPageCheckBox = initChildCheckBox(pagesSelectionPane, CourseSiteExporter.HWS_PAGE);
        //projectsPageCheckBox = initChildCheckBox(pagesSelectionPane, CourseSiteExporter.PROJECTS_PAGE);
    }
    
    
    
    // INITIALIZE THE SCHEDULE ITEMS CONTROLS
    /*private void initScheduleItemsControls() {
        // FOR THE LEFT
        dateBoundariesPane = new GridPane();
        dateBoundariesLabel = initGridLabel(dateBoundariesPane, WBK_PropertyType.DATE_BOUNDARIES_LABEL, CLASS_SUBHEADING_LABEL, 0, 0, 1, 1);
        startDateLabel = initGridLabel(dateBoundariesPane, WBK_PropertyType.STARTING_MONDAY_LABEL, CLASS_PROMPT_LABEL, 0, 1, 1, 1);
        
        endDateLabel = initGridLabel(dateBoundariesPane, WBK_PropertyType.ENDING_FRIDAY_LABEL, CLASS_PROMPT_LABEL, 0, 2, 1, 1);
     

        // THIS ONE IS ON THE RIGHT
        lectureDaySelectorPane = new VBox();
        lectureDaySelectLabel = initChildLabel(lectureDaySelectorPane, WBK_PropertyType.LECTURE_DAY_SELECT_LABEL, CLASS_SUBHEADING_LABEL);
        //mondayCheckBox = initChildCheckBox(lectureDaySelectorPane, CourseSiteExporter.MONDAY_HEADER);
        //tuesdayCheckBox = initChildCheckBox(lectureDaySelectorPane, CourseSiteExporter.TUESDAY_HEADER);
        //wednesdayCheckBox = initChildCheckBox(lectureDaySelectorPane, CourseSiteExporter.WEDNESDAY_HEADER);
        //thursdayCheckBox = initChildCheckBox(lectureDaySelectorPane, CourseSiteExporter.THURSDAY_HEADER);
        //fridayCheckBox = initChildCheckBox(lectureDaySelectorPane, CourseSiteExporter.FRIDAY_HEADER);

        // THIS SPLITS THE TOP
        splitScheduleInfoPane = new SplitPane();
        splitScheduleInfoPane.getItems().add(dateBoundariesPane);
        splitScheduleInfoPane.getItems().add(lectureDaySelectorPane);
        
        // NOW THE CONTROLS FOR ADDING SCHEDULE ITEMS
        scheduleItemsBox = new VBox();
        scheduleItemsToolbar = new HBox();
        scheduleItemsLabel = initLabel(CSB_PropertyType.SCHEDULE_ITEMS_HEADING_LABEL, CLASS_SUBHEADING_LABEL);
        addScheduleItemButton = initChildButton(scheduleItemsToolbar, CSB_PropertyType.ADD_ICON, CSB_PropertyType.ADD_ITEM_TOOLTIP, false);
        removeScheduleItemButton = initChildButton(scheduleItemsToolbar, CSB_PropertyType.MINUS_ICON, CSB_PropertyType.REMOVE_ITEM_TOOLTIP, false);
        scheduleItemsTable = new TableView();
        scheduleItemsBox.getChildren().add(scheduleItemsLabel);
        scheduleItemsBox.getChildren().add(scheduleItemsToolbar);
        scheduleItemsBox.getChildren().add(scheduleItemsTable);
        scheduleItemsBox.getStyleClass().add(CLASS_BORDERED_PANE);
        
        // NOW SETUP THE TABLE COLUMNS
        itemDescriptionsColumn = new TableColumn(COL_DESCRIPTION);
        itemDatesColumn = new TableColumn(COL_DATE);
        linkColumn = new TableColumn(COL_LINK);
        
        // AND LINK THE COLUMNS TO THE DATA
        itemDescriptionsColumn.setCellValueFactory(new PropertyValueFactory<String, String>("description"));
        
        linkColumn.setCellValueFactory(new PropertyValueFactory<URL, String>("link"));
        scheduleItemsTable.getColumns().add(itemDescriptionsColumn);
        scheduleItemsTable.getColumns().add(itemDatesColumn);
        scheduleItemsTable.getColumns().add(linkColumn);
        scheduleItemsTable.setItems(dataManager.getCourse().getScheduleItems());
        
                        
        
        itemTopicsColumn = new TableColumn(COL_TOPICS);
        itemSessionsColumn = new TableColumn(COL_SESSIONS);
        
        itemTopicsColumn.setCellValueFactory(new PropertyValueFactory<String, String>("topic"));
        itemSessionsColumn.setCellValueFactory(new PropertyValueFactory<Integer, String>("sessions"));
        
        
        lectureBox = new VBox();
        lectureToolbar = new HBox();
        lectureLabel = initLabel(WBK_PropertyType.LECTURES_HEADING_LABEL, CLASS_SUBHEADING_LABEL);
        addLectureButton = initChildButton(lectureToolbar, WBK_PropertyType.ADD_ICON, WBK_PropertyType.ADD_ITEM_TOOLTIP, false);
        removeLectureButton = initChildButton(lectureToolbar, WBK_PropertyType.MINUS_ICON, WBK_PropertyType.REMOVE_ITEM_TOOLTIP, false);
        
        moveUpButton = initChildButton(lectureToolbar, WBK_PropertyType.MOVE_UP_ICON, WBK_PropertyType.MOVE_UP_LECTURE_TOOLTIP, false);
        moveDownButton = initChildButton(lectureToolbar, WBK_PropertyType.MOVE_DOWN_ICON, WBK_PropertyType.MOVE_DOWN_LECTURE_TOOLTIP, false);
        
        //lectureTable = new TableView();
        lectureBox.getChildren().add(lectureLabel);
        lectureBox.getChildren().add(lectureToolbar);
        //lectureBox.getChildren().add(lectureTable);
        lectureBox.getStyleClass().add(CLASS_BORDERED_PANE);
        
       // lectureTable.getColumns().add(itemTopicsColumn);
       // lectureTable.getColumns().add(itemSessionsColumn);
       // lectureTable.setItems(dataManager.getCourse().getLectures());
       
        
        
        
        
        
        
        assignmentBox = new VBox();
        assignmentToolbar = new HBox();
        assignmentLabel = initLabel(CSB_PropertyType.HWS_HEADING_LABEL, CLASS_SUBHEADING_LABEL);
        addAssignmentButton = initChildButton(assignmentToolbar, CSB_PropertyType.ADD_ICON, CSB_PropertyType.ADD_ITEM_TOOLTIP, false);
        removeAssignmentButton = initChildButton(assignmentToolbar, CSB_PropertyType.MINUS_ICON, CSB_PropertyType.REMOVE_ITEM_TOOLTIP, false);
        assignmentTable = new TableView();
        assignmentBox.getChildren().add(assignmentLabel);
        assignmentBox.getChildren().add(assignmentToolbar);
        assignmentBox.getChildren().add(assignmentTable);
        assignmentBox.getStyleClass().add(CLASS_BORDERED_PANE);
        
        // NOW SETUP THE TABLE COLUMNS
        assignmentNameColumn = new TableColumn(COL_NAME);
        assignmentDatesColumn = new TableColumn(COL_DATE);
        assignmentTopicColumn = new TableColumn(COL_TOPIC);
        
        // AND LINK THE COLUMNS TO THE DATA
        assignmentNameColumn.setCellValueFactory(new PropertyValueFactory<String, String>("name"));
        assignmentTopicColumn.setCellValueFactory(new PropertyValueFactory<URL, String>("topics"));
        assignmentDatesColumn.setCellValueFactory(new PropertyValueFactory<LocalDate, String>("date"));
        assignmentTable.getColumns().add(assignmentNameColumn);
        assignmentTable.getColumns().add(assignmentTopicColumn);
        assignmentTable.getColumns().add(assignmentDatesColumn);
        assignmentTable.setItems(dataManager.getCourse().getAssignments());
        
        
        
        
         
        
        // NOW LET'S ASSEMBLE ALL THE CONTAINERS TOGETHER
        
        // THIS IS FOR STUFF IN THE TOP OF THE SCHEDULE PANE, WE NEED TO PUT TWO THINGS INSIDE
        scheduleInfoPane = new VBox();

        // FIRST OUR SCHEDULE HEADER
        scheduleInfoHeadingLabel = initChildLabel(scheduleInfoPane, WBK_PropertyType.SCHEDULE_HEADING_LABEL, CLASS_HEADING_LABEL);

        // AND THEN THE SPLIT PANE
        scheduleInfoPane.getChildren().add(splitScheduleInfoPane);

        // FINALLY, EVERYTHING IN THIS REGION ULTIMATELY GOES INTO schedulePane
        schedulePane = new VBox();
        schedulePane.getChildren().add(scheduleInfoPane);
        schedulePane.getChildren().add(scheduleItemsBox);
        schedulePane.getChildren().add(lectureBox);
        schedulePane.getChildren().add(assignmentBox);
        schedulePane.getStyleClass().add(CLASS_BORDERED_PANE);
    }
    */

    
    
    // INITIALIZE THE WINDOW (i.e. STAGE) PUTTING ALL THE CONTROLS
    // THERE EXCEPT THE WORKSPACE, WHICH WILL BE ADDED THE FIRST
    // TIME A NEW Course IS CREATED OR LOADED
    private void initWindow(String windowTitle) {
        // SET THE WINDOW TITLE
        primaryStage.setTitle(windowTitle);

        // GET THE SIZE OF THE SCREEN
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        // AND USE IT TO SIZE THE WINDOW
        primaryStage.setX(bounds.getMinX());
        primaryStage.setY(bounds.getMinY());
        primaryStage.setWidth(bounds.getWidth());
        primaryStage.setHeight(bounds.getHeight());

        // ADD THE TOOLBAR ONLY, NOTE THAT THE WORKSPACE
        // HAS BEEN CONSTRUCTED, BUT WON'T BE ADDED UNTIL
        // THE USER STARTS EDITING A COURSE
        wbkPane = new BorderPane();
        wbkPane.setTop(fileToolbarPane);
        wbkPane.setBottom(gameToolbarPane);
      
        primaryScene = new Scene(wbkPane);

        // NOW TIE THE SCENE TO THE WINDOW, SELECT THE STYLESHEET
        // WE'LL USE TO STYLIZE OUR GUI CONTROLS, AND OPEN THE WINDOW
        primaryScene.getStylesheets().add(PRIMARY_STYLE_SHEET);
        primaryStage.setScene(primaryScene);
        primaryStage.show();
    }

    
    // INIT ALL THE EVENT HANDLERS
    
    
    private void initEventHandlers() throws IOException {
        // FIRST THE FILE CONTROLS
        fileController = new FileController(messageDialog, yesNoCancelDialog, draftFileManager);
        
        newDraftButton.setOnAction(e -> {
            fileController.handleNewDraftRequest(this);
        });
        loadDraftButton.setOnAction(e -> {
            fileController.handleLoadDraftRequest(this);
        });
        saveDraftButton.setOnAction(e -> {
            fileController.handleSaveDraftRequest(this, dataManager.getDraft());
        });
        exportDraftButton.setOnAction(e -> {
            fileController.handleExportDraftRequest(this);
            //progressTest(theStage, dataManager.getCourse());
        });
        exitButton.setOnAction(e -> {
            fileController.handleExitRequest(this);
        });
        
        //Created event handlers for every radio button
        all.setOnAction(e -> {
            fileController.changeTable(this, all, playerTable);
        });
        
        u.setOnAction(e -> {
            fileController.changeTable(this, u, playerTable);
        });
        
        p.setOnAction(e -> {
            fileController.changeTable(this, p, playerTable);
        });
        
        oneB.setOnAction(e -> {
            fileController.changeTable(this, oneB, playerTable);
        });
        
        cOne.setOnAction(e -> {
            fileController.changeTable(this, cOne, playerTable);
        });
        
        twoB.setOnAction(e -> {
            fileController.changeTable(this, twoB, playerTable);
        });
        
        threeB.setOnAction(e -> {
            fileController.changeTable(this, threeB, playerTable);
        });
        
        mi.setOnAction(e -> {
            fileController.changeTable(this, mi, playerTable);
        });
        
        ss.setOnAction(e -> {
            fileController.changeTable(this, ss, playerTable);
        });
        
        of.setOnAction(e -> {
            fileController.changeTable(this, of, playerTable);
        });
        
        c.setOnAction(e -> {
            fileController.changeTable(this, c, playerTable);
        });
        
        homeButton.setOnAction(e -> {
            workspacePane.setCenter(fantasyTeamBox);
        });
        
        playerButton.setOnAction(e -> {
            workspacePane.setCenter(playerBox);
        });
        
        summaryButton.setOnAction(e -> {
            workspacePane.setCenter(summaryBox);
        });
        
        standingButton.setOnAction(e -> {
            workspacePane.setCenter(standingsBox);
        });
        
        teamButton.setOnAction(e -> {
            workspacePane.setCenter(mlbTeamBox);
        });
        
        
        registerTextFieldController(searchTextField);
        /*
        // THEN THE COURSE EDITING CONTROLS
        courseController = new CourseEditController();
        courseSubjectComboBox.setOnAction(e -> {
            courseController.handleCourseChangeRequest(this);
        });
        courseSemesterComboBox.setOnAction(e -> {
            courseController.handleCourseChangeRequest(this);
        });
        courseYearComboBox.setOnAction(e -> {
            courseController.handleCourseChangeRequest(this);
        });
        indexPageCheckBox.setOnAction(e -> {
            courseController.handleCourseChangeRequest(this);
        });
        syllabusPageCheckBox.setOnAction(e -> {
            courseController.handleCourseChangeRequest(this);
        });
        schedulePageCheckBox.setOnAction(e -> {
            courseController.handleCourseChangeRequest(this);
        });
        hwsPageCheckBox.setOnAction(e -> {
            courseController.handleCourseChangeRequest(this);
        });
        projectsPageCheckBox.setOnAction(e -> {
            courseController.handleCourseChangeRequest(this);
        });

        */
        // TEXT FIELDS HAVE A DIFFERENT WAY OF LISTENING FOR TEXT CHANGES
        //registerTextFieldController(courseNumberTextField);
        //registerTextFieldController(courseTitleTextField);
       

    
        // AND NOW THE SCHEDULE ITEM ADDING AND EDITING CONTROLS
        /*scheduleController = new ScheduleEditController(primaryStage, dataManager.getCourse(), messageDialog, yesNoCancelDialog);
        
        addScheduleItemButton.setOnAction(e -> {
            scheduleController.handleAddScheduleItemRequest(this);
        });
        removeScheduleItemButton.setOnAction(e -> {
            scheduleController.handleRemoveScheduleItemRequest(this, scheduleItemsTable.getSelectionModel().getSelectedItem());
        });
        
        
        //all button listeners added here
        addLectureButton.setOnAction(e -> {
            scheduleController.handleAddLectureRequest(this);
        });
        removeLectureButton.setOnAction(e -> {
            scheduleController.handleRemoveLectureRequest(this, lectureTable.getSelectionModel().getSelectedItem());
        });
        
        moveUpButton.setOnAction(e -> {
            scheduleController.handleMoveUpLectureRequest(this, lectureTable.getSelectionModel().getSelectedItem());
        });
        moveDownButton.setOnAction(e -> {
            scheduleController.handleMoveDownLectureRequest(this, lectureTable.getSelectionModel().getSelectedItem());
        });
        
        addAssignmentButton.setOnAction(e -> {
            scheduleController.handleAddAssignmentRequest(this);
        });
        removeLectureButton.setOnAction(e -> {
            scheduleController.handleRemoveAssignmentRequest(this, assignmentTable.getSelectionModel().getSelectedItem());
        });
        
        */
        
        //Helps in creating dialog for user to change notes of player
        playerController = new PlayerEditController(primaryStage, dataManager.getDraft(), messageDialog, yesNoCancelDialog);
        // AND NOW THE SCHEDULE ITEMS TABLE
        playerTable.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                // OPEN UP THE SCHEDULE ITEM EDITOR
                Player p = playerTable.getSelectionModel().getSelectedItem();
                playerController.handleEditPlayerRequest(this, p);
            }
        });
        
        /*
        lectureTable.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                // OPEN UP THE SCHEDULE ITEM EDITOR
                Lecture li = lectureTable.getSelectionModel().getSelectedItem();
                scheduleController.handleEditLectureRequest(this, li);
            }
        });
        
         assignmentTable.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                // OPEN UP THE SCHEDULE ITEM EDITOR
                Assignment ai = assignmentTable.getSelectionModel().getSelectedItem();
                scheduleController.handleEditAssignmentRequest(this, ai);
            }
        });
        
        */
        
    }
    
    //method for using the search bar, sorts the playertable 
    public void handleSortList() {
         
        Draft draft = this.getDataManager().getDraft();
        ObservableList<Player> p = draft.getPlayers();
        ObservableList<Player> newP = FXCollections.observableArrayList();
        String text = searchTextField.getText();
        
        for (int i = 0; i < p.size(); i++)
        {
            if (p.get(i).getFirstName().toLowerCase().startsWith(text.toLowerCase()) || p.get(i).getLastName().toLowerCase().startsWith(text.toLowerCase()))
            {
                
                newP.add(p.get(i));
                
                
            }
        }
        
        
        playerTable.setItems(newP);
         
     }

    // REGISTER THE EVENT LISTENER FOR A TEXT FIELD
   
    private void registerTextFieldController(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            handleSortList();
        });
    }
    
    // INIT A BUTTON AND ADD IT TO A CONTAINER IN A TOOLBAR
    private Button initChildButton(Pane toolbar, WBK_PropertyType icon, WBK_PropertyType tooltip, boolean disabled) {
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        String imagePath = "file:" + PATH_IMAGES + props.getProperty(icon.toString());
        Image buttonImage = new Image(imagePath);
        Button button = new Button();
        button.setDisable(disabled);
        button.setGraphic(new ImageView(buttonImage));
        Tooltip buttonTooltip = new Tooltip(props.getProperty(tooltip.toString()));
        button.setTooltip(buttonTooltip);
        toolbar.getChildren().add(button);
        return button;
    }
    
    // INIT A LABEL AND SET IT'S STYLESHEET CLASS
    private Label initLabel(WBK_PropertyType labelProperty, String styleClass) {
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        String labelText = props.getProperty(labelProperty);
        Label label = new Label(labelText);
        label.getStyleClass().add(styleClass);
        return label;
    }

    // INIT A LABEL AND PLACE IT IN A GridPane INIT ITS PROPER PLACE
    private Label initGridLabel(GridPane container, WBK_PropertyType labelProperty, String styleClass, int col, int row, int colSpan, int rowSpan) {
        Label label = initLabel(labelProperty, styleClass);
        container.add(label, col, row, colSpan, rowSpan);
        return label;
    }

    // INIT A LABEL AND PUT IT IN A TOOLBAR
    private Label initChildLabel(Pane container, WBK_PropertyType labelProperty, String styleClass) {
        Label label = initLabel(labelProperty, styleClass);
        container.getChildren().add(label);
        return label;
    }

    // INIT A COMBO BOX AND PUT IT IN A GridPane
    private ComboBox initGridComboBox(GridPane container, int col, int row, int colSpan, int rowSpan) throws IOException {
        ComboBox comboBox = new ComboBox();
        container.add(comboBox, col, row, colSpan, rowSpan);
        return comboBox;
    }

    // LOAD THE COMBO BOX TO HOLD Course SUBJECTS
    private void loadSubjectComboBox(ArrayList<String> subjects) {
        for (String s : subjects) {
            courseSubjectComboBox.getItems().add(s);
        }
    }

    // INIT A TEXT FIELD AND PUT IT IN A GridPane
    private TextField initGridTextField(GridPane container, int size, String initText, boolean editable, int col, int row, int colSpan, int rowSpan) {
        TextField tf = new TextField();
        tf.setPrefColumnCount(size);
        tf.setText(initText);
        tf.setEditable(editable);
        container.add(tf, col, row, colSpan, rowSpan);
        return tf;
    }

   
   
    // INIT A CheckBox AND PUT IT IN A TOOLBAR
    private CheckBox initChildCheckBox(Pane container, String text) {
        CheckBox cB = new CheckBox(text);
        container.getChildren().add(cB);
        return cB;
    }

   
    
    // LOADS CHECKBOX DATA INTO A Course OBJECT REPRESENTING A CoursePage
    /*private void updatePageUsingCheckBox(CheckBox cB, Draft draft, CoursePage cP) {
        if (cB.isSelected()) {
            course.selectPage(cP);
        } else {
            course.unselectPage(cP);
        }
    } 
    */
    
    /*
    //Everything here and below loads up the progress bar and what is said on top of it
    ProgressBar bar;
    ProgressIndicator indicator;
    Button button;
    Label processLabel;
    int numTasks = 0;
    Stage theStage = new Stage();
    int index = 0;
    
    
    private void progressTest(Stage theStage, Course courseToExport) 
    {

        VBox box = new VBox();

        HBox toolbar = new HBox();
        bar = new ProgressBar(0);      
        indicator = new ProgressIndicator(0);
        indicator.setStyle("font-size: 36pt");
        toolbar.getChildren().add(bar);
        toolbar.getChildren().add(indicator);
        
        
        processLabel = new Label();
        processLabel.setFont(new Font("Serif", 36));
        box.getChildren().add(processLabel);
        box.getChildren().add(toolbar);
       
        
        
        Scene scene = new Scene(box);
        theStage.setScene(scene);

      
                Task<Void> task = new Task<Void>() {
                    int task = numTasks++;
                    double max = courseToExport.getPages().size();
                    double perc;
                    @Override
                    protected Void call() throws Exception {
                        for (int i = 0; i <= max; i++) {
                           
                            perc = i/max;
                            
                            // THIS WILL BE DONE ASYNCHRONOUSLY VIA MULTITHREADING
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    bar.setProgress(perc);
                                    indicator.setProgress(perc);
                                    if (index < max)
                                    {
                                        processLabel.setText("Exporting " + courseToExport.getPages().get(index).toString() + " Completed");
                                        index++;
                                    }
                                }
                            });

                            // SLEEP EACH FRAME
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException ie) {
                                ie.printStackTrace();
                            }
                        }
                        index = 0;
                        return null;
                        
                    }
                };
                // THIS GETS THE THREAD ROLLING
                Thread thread = new Thread(task);
                thread.start();
                task.setOnSucceeded(e -> {
                    
                    fileController.handleExportCourseRequest(this);
                    theStage.hide();
                }
                
                
                
                );
              
        theStage.show();
    
    
    
}
    */

   
    

  
}