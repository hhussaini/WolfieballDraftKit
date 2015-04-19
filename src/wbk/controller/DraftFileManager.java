/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wbk.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import wbk.data.Draft;

/**
 *
 * @author Hamza
 */
public interface DraftFileManager {
    public void                 saveDraft(Draft draftToSave) throws IOException;
    public void                 loadDraft(Draft draftToLoad, String coursePath) throws IOException;
    
}
