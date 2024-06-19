
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author admin
 */
public class Main {

    private GetInputValidation input = new GetInputValidation();
    private BO_ManagerStudents manager = new BO_ManagerStudents();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        Main main = new Main();
        main.display();
    }

    public void display() throws Exception {
        while (true) {
            displayMenu();
            int option = input.getOption("Enter your option: ", "Option[1-4]", 1, 5);
            switch (option) {
                case 1:
                    userCreateStudent();
                    break;
                case 2:
                    userFindAndSort();
                    break;
                case 3:
                    userUpdateOrDelete();
                    break;
                case 4:
                    userReport();
                    break;
                case 5:
                    System.exit(0);
                    break;
            }
        }

    }

    public void displayMenu() {
        System.out.println("========= WELCOME TO STUDENT MANAGEMENT =========\n"
                + "1.	Create\n"
                + "2.	Find and Sort\n"
                + "3.	Update/Delete\n"
                + "4.	Report\n"
                + "5.      Exit");
    }

    private void userCreateStudent() throws Exception {
        while (true) {
            if (manager.listStudentManagement.size() > 9) {
                boolean yesorno = input.checkInputYN("Do you want to continue [Y/N]: ");
                if (!yesorno) {
                    break;
                }
            }
            String id = input.getString("Enter id: ", "Invalid id: ", "^[a-zA-Z0-9 ]+$");
            Student student = manager.checkIdExist(id);
            String name;
            if (student == null) {
                name = input.getString("Enter name: ", "Invalid name: ", "^[a-zA-Z ]+$");
            } else {
                name = student.getStudentName();
            }
            String semester = input.getString("Enter semester: ", "Invalid semester: ", "^[a-zA-Z0-9 ]+$");
            String course = input.checkInputCourse("Enter course: ");
            if (manager.checkStudentExist(id, name, semester, course)) {
                manager.createStudent(id, name, semester, course);
                System.out.println("Create Succesful");
                break;
            } else {
                System.out.println("This record does existed");
            }
        }
    }

    private void userFindAndSort() {
        String name = input.getString("Enter name: ", "Invalid. Enter Again: ", "[A-Za-z\\s]+");
        name = name.toLowerCase();

        System.out.format("%-15s%-15s%-15s\n", "Name", "Semester", "Course Name");
        boolean found = manager.findAndSortStudent(name);

        if (!found) {
            System.out.println("Not found");
        }
    }

    private void userUpdateOrDelete() {
        String id = input.getString("Enter id: ", "Invalid id: ", "^[a-zA-Z0-9 ]+$");
        ArrayList<Student> listStudentFindById = manager.getListStudentById(id);

        if (listStudentFindById.isEmpty()) {
            System.out.println("Student Not Found");
            return;
        }
        Student student = getStudentByListFound(listStudentFindById);
        System.out.println("Student Found");
        System.out.format("%-15s%-15s%-15s\n", "Student Name", "Semester", "Course Name");
        student.display();
        if (input.checkInputUD("Do you want continue [U/D]: ")) {
            String name = student.getStudentName();
            String semester = student.getSemester();
            String courseName = student.getCourseName();
            boolean update = false;
            while (!update) {
                String newName = input.getString("Enter name: ", "Invalid name: ", "^[a-zA-Z ]*$");
                if (!newName.isEmpty()) {
                    name = newName;
                }
                String newSemester = input.getString("Enter semester: ", "Invalid semester: ", "^[a-zA-Z0-9 ]*$");
                if (!newSemester.isEmpty()) {
                    semester = newSemester;
                }
                String newCourseName = input.checkInputCourse("Enter course (Press Enter to keep the current course):", courseName);
                if (!newCourseName.isEmpty()) {
                    courseName = newCourseName;
                }

                boolean updateSuccessful = manager.updateOrDeleteStudent(student, id, name, semester, courseName);
                if (updateSuccessful) {
                    System.out.println("Update Successful");
                    update = true;
                    break;
                } else {
                    System.out.println("Duplicate record. Enter again");
                }
            }
        } else {
            manager.listStudentManagement.remove(student);
            System.out.println("Delete Successful");
        }
    }

    public Student getStudentByListFound(ArrayList<Student> listStudentFindById) {
        System.out.println("List Student Found");
        int count = 0;
        System.out.format("%-10s%-15s%-15s%-15s\n", "Number", "Student Name", "Semester", "Course Name");
        for (Student student : listStudentFindById) {
            System.out.format("%-10d%-20s%-15s%-15s\n", count,
                    student.getStudentName(), student.getSemester(),
                    student.getCourseName());
            count++;
        }
        int choice = input.getOption("Enter choice: ", "Invalid. Enter again: ", 0, listStudentFindById.size() - 1);
        return listStudentFindById.get(choice);
    }

    private void userReport() {
        if (manager.listStudentManagement.isEmpty()) {
            System.err.println("List is empty.");
            return;
        }
        List<Report> listReport = manager.getListReport(manager.listStudentManagement);
        System.out.format("%-20s%-20s%-20s\n", "Student Name", "Course Name", "Total");
        for (Report report : listReport) {
            System.out.format("%-20s%-20s%-20s\n", report.getStudentName(), report.getCourseName(), report.getTotalCourseName());
        }
    }

}
