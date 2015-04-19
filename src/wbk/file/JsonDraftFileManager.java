/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wbk.file;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.json.JsonWriter;
import wbk.controller.DraftFileManager;
import wbk.data.Draft;
import wbk.data.Hitter;
import wbk.data.Pitcher;
import wbk.data.Player;

/**
 *
 * @author Hamza
 */


public class JsonDraftFileManager implements DraftFileManager {
    String JSON_SUBJECTS = "subjects";
    String JSON_SUBJECT = "subject";
    String JSON_NUMBER = "number";
    String JSON_TITLE = "title";
    String JSON_SEMESTER = "semester";
    //String JSON_YEAR = "year";
    String JSON_SECTION = "section";
    String JSON_PAGES = "pages";
    String JSON_STARTING_MONDAY = "startingMonday";
    String JSON_ENDING_FRIDAY = "endingFriday";
    String JSON_MONTH = "month";
    String JSON_DAY = "day";
    String JSON_INSTRUCTOR = "instructor";
    String JSON_INSTRUCTOR_NAME = "instructorName";
    String JSON_HOMEPAGE_URL = "homepageURL";
    String JSON_LECTURE_DAYS = "lectureDays";
    String JSON_SCHEDULE_ITEMS = "scheduleItems";
    String JSON_LECTURES = "lectures";
    String JSON_HWS = "hws";
    String JSON_SCHEDULE_ITEM_DESCRIPTION = "description";
    String JSON_SCHEDULE_ITEM_DATE = "date";
    String JSON_SCHEDULE_ITEM_LINK = "link";
    String JSON_LECTURE_TOPIC = "topic";
    String JSON_LECTURE_SESSIONS = "sessions";
    String JSON_ASSIGNMENT_NAME = "name";
    String JSON_ASSIGNMENT_TOPICS = "topics";
    String JSON_ASSIGNMENT_DATE = "date";
    String JSON_EXT = ".json";
    String SLASH = "/";
    
    
    String JSON_FIRST_NAME = "firstName";
    String JSON_LAST_NAME = "lastName";
    String JSON_POSITION = "position";
    String JSON_YEAR = "yearOfBirth";
    String JSON_RW = "rw";
    String JSON_HRSV = "hrsv";
    String JSON_RBIK = "rbik";
    String JSON_SBERA = "sbera";
    String JSON_BAWHIP = "bawhip";
    String JSON_ESTIMATED_VALUE = "estimatedValue";
    String JSON_NOTES = "notes";

    /**
     * This method saves all the data associated with a course to
     * a JSON file.
     * 
     * @param courseToSave The course whose data we are saving.
     * 
     * @throws IOException Thrown when there are issues writing
     * to the JSON file.
     */
//    @Override
//    public void saveCourse(Course courseToSave) throws IOException {
//        // BUILD THE FILE PATH
//        String courseListing = "" + courseToSave.getSubject() + courseToSave.getNumber();
//        String jsonFilePath = PATH_COURSES + SLASH + courseListing + JSON_EXT;
//        
//        // INIT THE WRITER
//        OutputStream os = new FileOutputStream(jsonFilePath);
//        JsonWriter jsonWriter = Json.createWriter(os);  
//        
//        // MAKE A JSON ARRAY FOR THE PAGES ARRAY
//        JsonArray pagesJsonArray = makePagesJsonArray(courseToSave.getPages());
//        
//        // AND AN OBJECT FOR THE INSTRUCTOR
//        JsonObject instructorJsonObject = makeInstructorJsonObject(courseToSave.getInstructor());
//        
//        // ONE FOR EACH OF OUR DATES
//        JsonObject startingMondayJsonObject = makeLocalDateJsonObject(courseToSave.getStartingMonday());
//        JsonObject endingFridayJsonObject = makeLocalDateJsonObject(courseToSave.getEndingFriday());
//        
//        // THE LECTURE DAYS ARRAY
//        JsonArray lectureDaysJsonArray = makeLectureDaysJsonArray(courseToSave.getLectureDays());
//        
//        // THE SCHEDULE ITEMS ARRAY
//        JsonArray scheduleItemsJsonArray = makeScheduleItemsJsonArray(courseToSave.getScheduleItems());
//        
//        // THE LECTURES ARRAY
//        JsonArray lecturesJsonArray = makeLecturesJsonArray(courseToSave.getLectures());
//        
//        // THE HWS ARRAY
//        JsonArray hwsJsonArray = makeHWsJsonArray(courseToSave.getAssignments());
//        
//        // NOW BUILD THE COURSE USING EVERYTHING WE'VE ALREADY MADE
//        JsonObject courseJsonObject = Json.createObjectBuilder()
//                                    .add(JSON_SUBJECT, courseToSave.getSubject().toString())
//                                    .add(JSON_NUMBER, courseToSave.getNumber())
//                                    .add(JSON_TITLE, courseToSave.getTitle())
//                                    .add(JSON_SEMESTER, courseToSave.getSemester().toString())
//                                    .add(JSON_YEAR, courseToSave.getYear())
//                                    .add(JSON_PAGES, pagesJsonArray)
//                                    .add(JSON_INSTRUCTOR, instructorJsonObject)
//                                    .add(JSON_STARTING_MONDAY, startingMondayJsonObject)
//                                    .add(JSON_ENDING_FRIDAY, endingFridayJsonObject)
//                                    .add(JSON_LECTURE_DAYS, lectureDaysJsonArray)
//                                    .add(JSON_SCHEDULE_ITEMS, scheduleItemsJsonArray)
//                                    .add(JSON_LECTURES, lecturesJsonArray)
//                                    .add(JSON_HWS, hwsJsonArray)
//                .build();
//        
//        // AND SAVE EVERYTHING AT ONCE
//        jsonWriter.writeObject(courseJsonObject);
//    }
//    
//    /**
//     * Loads the courseToLoad argument using the data found in the json file.
//     * 
//     * @param courseToLoad Course to load.
//     * @param jsonFilePath File containing the data to load.
//     * 
//     * @throws IOException Thrown when IO fails.
//     */
//    @Override
//    public void loadCourse(Course courseToLoad, String jsonFilePath) throws IOException {
//        // LOAD THE JSON FILE WITH ALL THE DATA
//        JsonObject json = loadJSONFile(jsonFilePath);
//        
//        // NOW LOAD THE COURSE
//        courseToLoad.setSubject(Subject.valueOf(json.getString(JSON_SUBJECT)));
//        courseToLoad.setNumber(json.getInt(JSON_NUMBER));
//        courseToLoad.setSemester(Semester.valueOf(json.getString(JSON_SEMESTER)));
//        courseToLoad.setYear(json.getInt(JSON_YEAR));
//        courseToLoad.setTitle(json.getString(JSON_TITLE));
//        
//        // GET THE PAGES TO INCLUDE 
//        courseToLoad.clearPages();
//        JsonArray jsonPagesArray = json.getJsonArray(JSON_PAGES);
//        for (int i = 0; i < jsonPagesArray.size(); i++)
//            courseToLoad.addPage(CoursePage.valueOf(jsonPagesArray.getString(i)));
//        
//        // GET THE LECTURE DAYS TO INCLUDE
//        courseToLoad.clearLectureDays();
//        JsonArray jsonLectureDaysArray = json.getJsonArray(JSON_LECTURE_DAYS);
//        for (int i = 0; i < jsonLectureDaysArray.size(); i++)
//            courseToLoad.addLectureDay(DayOfWeek.valueOf(jsonLectureDaysArray.getString(i)));
//
//        // LOAD AND SET THE INSTRUCTOR
//        JsonObject jsonInstructor = json.getJsonObject(JSON_INSTRUCTOR);
//        Instructor instructor = new Instructor( jsonInstructor.getString(JSON_INSTRUCTOR_NAME),
//                                                jsonInstructor.getString(JSON_HOMEPAGE_URL));
//        courseToLoad.setInstructor(instructor);
//        
//        // GET THE STARTING MONDAY
//        JsonObject startingMonday = json.getJsonObject(JSON_STARTING_MONDAY);
//        int year = startingMonday.getInt(JSON_YEAR);
//        int month = startingMonday.getInt(JSON_MONTH);
//        int day = startingMonday.getInt(JSON_DAY);
//        courseToLoad.setStartingMonday(LocalDate.of(year, month, day));
//
//        // GET THE ENDING FRIDAY
//        JsonObject endingFriday = json.getJsonObject(JSON_ENDING_FRIDAY);
//        year = endingFriday.getInt(JSON_YEAR);
//        month = endingFriday.getInt(JSON_MONTH);
//        day = endingFriday.getInt(JSON_DAY);
//        courseToLoad.setEndingFriday(LocalDate.of(year, month, day));
//        
//        // GET THE SCHEDULE ITEMS
//        courseToLoad.clearScheduleItems();
//        JsonArray jsonScheduleItemsArray = json.getJsonArray(JSON_SCHEDULE_ITEMS);
//        for (int i = 0; i < jsonScheduleItemsArray.size(); i++) {
//            JsonObject jso = jsonScheduleItemsArray.getJsonObject(i);
//            ScheduleItem si = new ScheduleItem();
//            si.setDescription(jso.getString(JSON_SCHEDULE_ITEM_DESCRIPTION));
//            JsonObject jsoDate = jso.getJsonObject(JSON_SCHEDULE_ITEM_DATE);
//            year = jsoDate.getInt(JSON_YEAR);
//            month = jsoDate.getInt(JSON_MONTH);
//            day = jsoDate.getInt(JSON_DAY);            
//            si.setDate(LocalDate.of(year, month, day));
//            si.setLink(jso.getString(JSON_SCHEDULE_ITEM_LINK));
//            
//            // ADD IT TO THE COURSE
//            courseToLoad.addScheduleItem(si);
//        }
    
    @Override
    public void loadDraft(Draft draftToLoad, String jsonFilePath) throws IOException
    {
        JsonObject json = loadJSONFile(jsonFilePath);
        
        JsonArray jsonLecturesArray = json.getJsonArray("Hitters");
        
        for (int i = 0; i < jsonLecturesArray.size(); i++) {
            JsonObject jso = jsonLecturesArray.getJsonObject(i);
            Player p = new Player();
            Hitter h = new Hitter();
            Pitcher pi = new Pitcher();
            
            p.setFirstName(jso.getString("FIRST_NAME"));
            p.setLastName(jso.getString("LAST_NAME"));
            p.setTeam(jso.getString("TEAM"));
            p.setPosition(jso.getString("QP"));
            p.setYear(Integer.parseInt(jso.getString("YEAR_OF_BIRTH")));
            p.setRw(Integer.parseInt(jso.getString("R")));
            p.setHrsv(Integer.parseInt(jso.getString("HR")));
            p.setRbik(Integer.parseInt(jso.getString("RBI")));
            p.setSbera(Double.parseDouble(jso.getString("SB")));
            
            Double hits = Double.parseDouble(jso.getString("SB"));
            Double atBat = Double.parseDouble(jso.getString("AB"));
            
            p.setBawhip(hits/atBat);
            
            //p.setEstimatedValue(Double.parseDouble(jso.getString(JSON_ESTIMATED_VALUE)));
            
            p.setNotes(jso.getString("NOTES"));
            
                        
            // ADD IT TO THE COURSE
            draftToLoad.addPlayer(p);
        }
        
        
        JsonObject json2 = loadJSONFile("./data/Pitcher.json");
        
        JsonArray jsonLecturesArray2 = json2.getJsonArray("Pitchers");
        
        for (int i = 0; i < jsonLecturesArray2.size(); i++) {
            JsonObject jso = jsonLecturesArray2.getJsonObject(i);
            Player p = new Player();
            
            p.setFirstName(jso.getString("FIRST_NAME"));
            p.setLastName(jso.getString("LAST_NAME"));
            p.setTeam(jso.getString("TEAM"));
            p.setPosition("P");
            p.setYear(Integer.parseInt(jso.getString("YEAR_OF_BIRTH")));
            p.setRw(Integer.parseInt(jso.getString("W")));
            p.setHrsv(Integer.parseInt(jso.getString("SV")));
            p.setRbik(Integer.parseInt(jso.getString("K")));
            
            Double er = Double.parseDouble(jso.getString("ER"));
            Double ip = Double.parseDouble(jso.getString("IP"));
            
            p.setSbera(er/(ip*9));
           
            Double w = Double.parseDouble(jso.getString("W"));
            Double h = Double.parseDouble(jso.getString("H"));
            
         
            p.setBawhip((w+h)/ip);
            
            //p.setEstimatedValue(Double.parseDouble(jso.getString(JSON_ESTIMATED_VALUE)));
            
            p.setNotes(jso.getString("NOTES"));
            
                        
            // ADD IT TO THE COURSE
            draftToLoad.addPlayer(p);
        }
        
        
        
    }
    
    @Override
    public void saveDraft(Draft draftToLoad) throws IOException
    {
        
    }
//        
//        // GET THE HWS
//        JsonArray jsonHWsArray = json.getJsonArray(JSON_HWS);
//        courseToLoad.clearHWs();
//        for (int i = 0; i < jsonHWsArray.size(); i++) {
//            JsonObject jso = jsonHWsArray.getJsonObject(i);
//            Assignment a = new Assignment();
//            a.setName(jso.getString(JSON_ASSIGNMENT_NAME));
//            JsonObject jsoDate = jso.getJsonObject(JSON_ASSIGNMENT_DATE);
//            year = jsoDate.getInt(JSON_YEAR);
//            month = jsoDate.getInt(JSON_MONTH);
//            day = jsoDate.getInt(JSON_DAY);            
//            a.setDate(LocalDate.of(year, month, day));
//            a.setTopics(jso.getString(JSON_ASSIGNMENT_TOPICS));
//            
//            // ADD IT TO THE COURSE
//            courseToLoad.addAssignment(a);
//        }
//    }
//    
//    /**
//     * This function saves the last instructor to a json file. This provides 
//     * a convenience to the user, who is likely always the same instructor.
//     * @param lastInstructor Instructor to save.
//     * @param jsonFilePath File in which to put the data.
//     * @throws IOException Thrown when I/O fails.
//     */
//    @Override
//    public void saveLastInstructor(Instructor lastInstructor, String jsonFilePath) throws IOException {
//        OutputStream os = new FileOutputStream(jsonFilePath);
//        JsonWriter jsonWriter = Json.createWriter(os); 
//        JsonObject instructorJsonObject = makeInstructorJsonObject(lastInstructor);
//        jsonWriter.writeObject(instructorJsonObject);
//    }
//    
//    /**
//     * Loads an instructor from the provided file, returning a constructed
//     * object to represent it.
//     * @param filePath Path of json file containing instructor data.
//     * @return A constructed Instructor initialized with the data from the file
//     * @throws IOException Thrown when I/O fails.
//     */
//    @Override
//    public Instructor loadLastInstructor(String filePath) throws IOException {
//        JsonObject json = loadJSONFile(filePath);
//        return buildInstructorJsonObject(json);
//    }
    
    /**
     * Saves the subjects list to a json file.
     * @param subjects List of Subjects to save.
     * @param jsonFilePath Path of json file.
     * @throws IOException Thrown when I/O fails.
     */
//    @Override
//    public void saveSubjects(List<Object> subjects, String jsonFilePath) throws IOException {
//        JsonObject arrayObject = buildJsonArrayObject(subjects);
//        OutputStream os = new FileOutputStream(jsonFilePath);
//        JsonWriter jsonWriter = Json.createWriter(os);  
//        jsonWriter.writeObject(arrayObject);        
//    }
//    
//    /**
//     * Loads subjects from the json file.
//     * @param jsonFilePath Json file containing the subjects.
//     * @return List full of Subjects loaded from the file.
//     * @throws IOException Thrown when I/O fails.
//     */
//    @Override
//    public ArrayList<String> loadSubjects(String jsonFilePath) throws IOException {
//        ArrayList<String> subjectsArray = loadArrayFromJSONFile(jsonFilePath, JSON_SUBJECTS);
//        ArrayList<String> cleanedArray = new ArrayList();
//        for (String s : subjectsArray) {
//            // GET RID OF ALL THE QUOTE CHARACTERS
//            s = s.replaceAll("\"", "");
//            cleanedArray.add(s);
//        }
//        return cleanedArray;
//    }
//    
//    // AND HERE ARE THE PRIVATE HELPER METHODS TO HELP THE PUBLIC ONES
//    
//    // LOADS A JSON FILE AS A SINGLE OBJECT AND RETURNS IT
    private JsonObject loadJSONFile(String jsonFilePath) throws IOException {
        InputStream is = new FileInputStream(jsonFilePath);
        JsonReader jsonReader = Json.createReader(is);
        JsonObject json = jsonReader.readObject();
        jsonReader.close();
        is.close();
        return json;
    }    
//    
//    // LOADS AN ARRAY OF A SPECIFIC NAME FROM A JSON FILE AND
//    // RETURNS IT AS AN ArrayList FULL OF THE DATA FOUND
//    private ArrayList<String> loadArrayFromJSONFile(String jsonFilePath, String arrayName) throws IOException {
//        JsonObject json = loadJSONFile(jsonFilePath);
//        ArrayList<String> items = new ArrayList();
//        JsonArray jsonArray = json.getJsonArray(arrayName);
//        for (JsonValue jsV : jsonArray) {
//            items.add(jsV.toString());
//        }
//        return items;
//    }
//    
//    // MAKES AND RETURNS A JSON OBJECT FOR THE PROVIDED SCHEDULE ITEM
//    private JsonObject makeScheduleItemJsonObject(ScheduleItem scheduleItem) {
//        JsonObject date = makeLocalDateJsonObject(scheduleItem.getDate());
//        JsonObject jso = Json.createObjectBuilder().add(JSON_SCHEDULE_ITEM_DESCRIPTION, scheduleItem.getDescription())
//                                                    .add(JSON_SCHEDULE_ITEM_DATE, date)
//                                                    .add(JSON_SCHEDULE_ITEM_LINK, scheduleItem.getLink())
//                                                    .build();
//        return jso;
//    }
//    
//    // MAKES AND RETURNS A JSON OBJECT FOR THE PROVIDED LECTURE
//    private JsonObject makeLectureJsonObject(Lecture lecture) {
//        JsonObject jso = Json.createObjectBuilder().add(JSON_LECTURE_TOPIC, lecture.getTopic())
//                                                    .add(JSON_LECTURE_SESSIONS, lecture.getSessions())
//                                                    .build();
//        return jso;
//    }
//    
//    // MAKES AND RETURNS A JSON OBJECT FOR THE PROVIDED ASSIGNMENT
//    private JsonObject makeAssignmentJsonObject(Assignment assignment) {
//        JsonObject dateJSO = makeLocalDateJsonObject(assignment.getDate());
//        JsonObject jso = Json.createObjectBuilder().add(JSON_ASSIGNMENT_NAME, assignment.getName())
//                                                    .add(JSON_ASSIGNMENT_TOPICS, assignment.getTopics())
//                                                    .add(JSON_ASSIGNMENT_DATE, dateJSO)
//                                                    .build();
//        return jso;
//    }
//    
//    // MAKES AND RETURNS A JSON OBJECT FOR THE PROVIDED INSTRUCTOR
//    private JsonObject makeInstructorJsonObject(Instructor instructor) {
//        JsonObject jso = Json.createObjectBuilder().add(JSON_INSTRUCTOR_NAME, instructor.getName())
//                                                   .add(JSON_HOMEPAGE_URL, instructor.getHomepageURL())
//                                                   .build(); 
//        return jso;                
//    }
//
//    // MAKES AND RETURNS A JSON OBJECT FOR THE PROVIDED DATE
//    private JsonObject makeLocalDateJsonObject(LocalDate dateToSave) {
//        JsonObject jso = Json.createObjectBuilder().add(JSON_YEAR, dateToSave.getYear())
//                                                   .add(JSON_MONTH, dateToSave.getMonthValue())
//                                                   .add(JSON_DAY, dateToSave.getDayOfMonth())
//                                                   .build(); 
//        return jso;
//    }
//    
//    // BUILDS AND RETURNS THE INSTRUCTOR FOUND IN THE JSON OBJECT
//    public Instructor buildInstructorJsonObject(JsonObject json) {
//        Instructor instructor = new Instructor( json.getString(JSON_INSTRUCTOR_NAME),
//                                                    json.getString(JSON_HOMEPAGE_URL));
//        return instructor;
//    }
//
//    // BUILDS AND RETURNS A JsonArray CONTAINING ALL THE PAGES FOR THIS COURSE
//    public JsonArray makePagesJsonArray(List<CoursePage> data) {
//        JsonArrayBuilder jsb = Json.createArrayBuilder();
//        for (CoursePage cP : data) {
//           jsb.add(cP.toString());
//        }
//        JsonArray jA = jsb.build();
//        return jA;        
//    }
//
//    // BUILDS AND RETURNS A JsonArray CONTAINING ALL THE LECTURE DAYS FOR THIS COURSE
//    public JsonArray makeLectureDaysJsonArray(List<DayOfWeek> data) {
//        JsonArrayBuilder jsb = Json.createArrayBuilder();
//        for (DayOfWeek dow : data) {
//            jsb.add(dow.toString());
//        }
//        JsonArray jA = jsb.build();
//        return jA;
//    }
//    
//    // MAKE AN ARRAY OF SCHEDULE ITEMS
//    private JsonArray makeScheduleItemsJsonArray(ObservableList<ScheduleItem> data) {
//        JsonArrayBuilder jsb = Json.createArrayBuilder();
//        for (ScheduleItem si : data) {
//            jsb.add(makeScheduleItemJsonObject(si));
//        }
//        JsonArray jA = jsb.build();
//        return jA;
//    }
//    
//    // MAKE AN ARRAY OF LECTURE ITEMS
//    private JsonArray makeLecturesJsonArray(ObservableList<Lecture> data) {
//        JsonArrayBuilder jsb = Json.createArrayBuilder();
//        for (Lecture l : data) {
//            jsb.add(makeLectureJsonObject(l));
//        }
//        JsonArray jA = jsb.build();
//        return jA;
//    }
//    
//    // MAKE AN ARRAY OF ASSIGNMENTS
//    public JsonArray makeHWsJsonArray(ObservableList<Assignment> data) {
//        JsonArrayBuilder jsb = Json.createArrayBuilder();
//        for (Assignment a : data) {
//            jsb.add(this.makeAssignmentJsonObject(a));
//        }
//        JsonArray jA = jsb.build();
//        return jA;
//    }
//
//    // BUILDS AND RETURNS A JsonArray CONTAINING THE PROVIDED DATA
//    public JsonArray buildJsonArray(List<Object> data) {
//        JsonArrayBuilder jsb = Json.createArrayBuilder();
//        for (Object d : data) {
//           jsb.add(d.toString());
//        }
//        JsonArray jA = jsb.build();
//        return jA;
//    }
//
//    // BUILDS AND RETURNS A JsonObject CONTAINING A JsonArray
//    // THAT CONTAINS THE PROVIDED DATA
//    public JsonObject buildJsonArrayObject(List<Object> data) {
//        JsonArray jA = buildJsonArray(data);
//        JsonObject arrayObject = Json.createObjectBuilder().add(JSON_SUBJECTS, jA).build();
//        return arrayObject;
//    }
}

