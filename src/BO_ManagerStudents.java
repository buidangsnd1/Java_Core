
import java.util.ArrayList;
import java.util.Collections;
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
public class BO_ManagerStudents {

    protected List<Student> listStudentManagement;

    public BO_ManagerStudents() {
        if (listStudentManagement == null) {
            listStudentManagement = new ArrayList<>();
        }
    }

    public void createStudent(String id, String name, String semester, String courseName) throws Exception {
        try {
            if (checkStudentExist(id, name, semester, courseName)) {
                Student newStudent = new Student(id, name, semester, courseName);
                listStudentManagement.add(newStudent);

            }
        } catch (Exception e) {
            throw new Exception("Create Fail: " + e);
        }

    }

    public boolean findAndSortStudent(String name) {
        name = name.toLowerCase();
        boolean isExited = false;
        Collections.sort(listStudentManagement);
        for (Student student : listStudentManagement) {
            if (student.getStudentName().toLowerCase().contains(name)) {
                student.display();
                isExited = true;
            }
        }
        return isExited;
    }

    public boolean updateOrDeleteStudent(Student student, String id, String name, String semester, String course) {
        if (!checkStudentExist(id, name, semester, course)) {
            return false;
        }

        if (!name.equalsIgnoreCase(student.getStudentName())) {
            for (Student change_name : listStudentManagement) {
                if (change_name.getId().equals(id)) {
                    change_name.setStudentName(name);
                }
            }
        }
        student.setId(id);
        student.setStudentName(name);
        student.setSemester(semester);
        student.setCourseName(course);
        return true; // Trả về true nếu cập nhật thành công
    }

    public void reportStudent() {
        List<Report> listReport = getListReport(listStudentManagement);
    }

    public List<Report> getListReport(List<Student> listStudent) {
    List<Report> listReport = new ArrayList<>();
    for (Student student : listStudent) {
        Report report = new Report();
        boolean found = false;

        for (Report reportDuplicate : listReport) {
            // Thay đổi điều kiện kiểm tra trùng lặp
            if (student.getCourseName().equalsIgnoreCase(reportDuplicate.getCourseName())
                    && student.getStudentName().equalsIgnoreCase(reportDuplicate.getStudentName())
                    && student.getId().equalsIgnoreCase(reportDuplicate.getId())) {
                reportDuplicate.setTotalCourseName(reportDuplicate.getTotalCourseName() + 1);
                found = true;
                break;
            }
        }

        if (!found) {
            // Thêm ID sinh viên vào report
            report.setId(student.getId());
            report.setStudentName(student.getStudentName());
            report.setCourseName(student.getCourseName());
            report.setTotalCourseName(1);
            listReport.add(report);
        }
    }
    return listReport;
}


    public boolean checkStudentExist(String id, String name, String semester, String courseName) {
        for (Student student : listStudentManagement) {
            if (id.equalsIgnoreCase(student.getId()) && name.equalsIgnoreCase(student.getStudentName())
                    && semester.equalsIgnoreCase(student.getSemester()) && courseName.equalsIgnoreCase(student.getCourseName())) {
                return false;
            }
        }
        return true;
    }

    public Student checkIdExist(String id) {
        for (Student student : listStudentManagement) {
            if (student.getId().equalsIgnoreCase(id)) {
                return student;
            }
        }
        return null;
    }

    public ArrayList<Student> getListStudentById(String id) {
        ArrayList<Student> getListStudentById = new ArrayList<>();
        for (Student student : listStudentManagement) {
            if (student.getId().equalsIgnoreCase(id)) {
                getListStudentById.add(student);
            }
        }
        return getListStudentById;
    }
}
