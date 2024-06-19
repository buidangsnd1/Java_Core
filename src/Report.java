
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author admin
 */
public class Report {
 private String id;
    private String studentName;
    private String courseName;
    private int totalCourseName;

    public Report() {
    }

    public Report(String id, String studentName, String courseName, int totalCourseName) {
        this.id = id;
        this.studentName = studentName;
        this.courseName = courseName;
        this.totalCourseName = totalCourseName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getTotalCourseName() {
        return totalCourseName;
    }

    public void setTotalCourseName(int totalCourseName) {
        this.totalCourseName = totalCourseName;
    }

  

   
}
