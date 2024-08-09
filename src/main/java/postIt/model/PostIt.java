package postIt.model;

import postIt.DAO.ExceptionDAO;
import postIt.DAO.PostItDAO;

import java.util.Date;

public class PostIt {
    private String title;
    private String description;
    private Date deadline;

    public PostIt(String title, String description, Date deadline) {
        this.title = title;
        this.description = description;
        this.deadline = deadline;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String decription) {
        this.description = decription;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void savePostIt(PostIt postIt) throws ExceptionDAO {
        new PostItDAO().savePostIt(postIt);
    }
}
