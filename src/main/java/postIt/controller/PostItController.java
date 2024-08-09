package postIt.controller;

import postIt.DAO.ExceptionDAO;
import postIt.model.PostIt;

import java.util.Date;

public class PostItController {
    public boolean savePostIt(String title, String description, Date deadline) {
        if (title != null && !title.isEmpty() && description != null && !description.isEmpty() && deadline != null) {
            PostIt postIt = new PostIt(title, description, deadline);
            try {
                postIt.savePostIt(postIt);
                return true;
            } catch (ExceptionDAO e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }
}