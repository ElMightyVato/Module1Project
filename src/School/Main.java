package School;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Student> students = new ArrayList<>();
    private static ArrayList<Teacher> teachers = new ArrayList<>();
    private static ArrayList<Course> courses = new ArrayList<>();

    private static int studentIdCounter = 1;
    private static int teacherIdCounter = 1;
    private static int courseIdCounter = 100;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n--- School Management System Menu ---");
            System.out.println("1. Add Student");
            System.out.println("2. Add Teacher");
            System.out.println("3. Add Course");
            System.out.println("4. Assign Course to Student");
            System.out.println("5. Assign Course to Teacher");
            System.out.println("6. View Student by ID");
            System.out.println("7. View All Students and Teachers");
            System.out.println("8. View All Courses");
            System.out.println("9. Update Student");
            System.out.println("10. Delete Student");
            System.out.println("11. Exit");
            System.out.println("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addStudent(scanner);
                    break;
                case 2:
                    addTeacher(scanner);
                    break;
                case 3:
                    addCourse(scanner);
                    break;
                case 4:
                    assignCourseToStudent(scanner);
                    break;
                case 5:
                    assignCourseToTeacher(scanner);
                    break;
                case 6:
                    viewStudentById(scanner);
                    break;
                case 7:
                    viewAllStudentsAndTeachers();
                    break;
                case 8:
                    viewAllCourses();
                    break;
                case 9:
                    updateStudent(scanner);
                    break;
                case 10:
                    deleteStudent(scanner);
                    break;
                case 11:
                    System.out.println("Exiting Program.");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }

    private static void addStudent(Scanner scanner) {
        try {
            System.out.print("Enter student name: ");
            String name = scanner.nextLine();

            System.out.print("Enter student age: ");
            int age = scanner.nextInt();
            scanner.nextLine(); // Remember to buffer!

            System.out.print("Enter student email: ");
            String email = scanner.nextLine();

            System.out.println("Enter grade (1-12): ");
            int grade = scanner.nextInt();
            scanner.nextLine();

            int studentId = studentIdCounter++;

            Student student = new Student(name, age, email, grade, studentId);
            students.add(student);

            System.out.println("Student added Successfully! ID: " + studentId);
        } catch (Exception e) {
            System.out.println("Error adding student: " + e.getMessage());
            scanner.nextLine();
        }
    }

    private static void addTeacher(Scanner scanner) {
        try {
            System.out.println("Enter teacher name: ");
            String name = scanner.nextLine();

            System.out.println("Enter teacher age: ");
            int age = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Enter teacher email: ");
            String email = scanner.nextLine();

            System.out.println("Enter subject: ");
            String subject = scanner.nextLine();

            int teacherId = teacherIdCounter++;

            Teacher teacher = new Teacher(name, age, email, teacherId, subject);
            teachers.add(teacher);

            System.out.println("Teacher added successfully! ID: " + teacherId);
        } catch (Exception e) {
            System.out.println("Error adding teacher: " + e.getMessage());
            scanner.nextLine();
        }
    }

    private static void addCourse(Scanner scanner) {
        try {
            System.out.println("Enter course name: ");
            String courseName = scanner.nextLine();

            int courseId = courseIdCounter++;

            Course course = new Course(courseName, courseId);
            courses.add(course);

            System.out.println("Course added successfully! ID: " + courseId);
        } catch (Exception e) {
            System.out.println("Error adding course: " + e.getMessage());
        }
    }

    private static void assignCourseToStudent(Scanner scanner) {
        System.out.println("Enter student ID: ");
        int studentId = scanner.nextInt();
        scanner.nextLine();

        Student student = null;
        for (Student s : students) {
            if (s.getStudentId() == studentId) {
                student = s;
                break;
            }
        }

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.println("Available Courses:");
        for (Course course : courses) {
            System.out.println(course.getCourseId() + ": " + course.getCourseName());
        }

        System.out.println("Enter course ID to assign: ");
        int courseId = scanner.nextInt();
        scanner.nextLine();

        Course courseToAssign = null;
        for (Course c : courses) {
            if (c.getCourseId() == courseId) {
                courseToAssign = c;
                break;
            }
        }

        if (courseToAssign == null) {
            System.out.println("Course not found.");
            return;
        }

        boolean success = student.addCourse(courseToAssign);
        if (success) {
            System.out.println("Course assigned to student.");
        }
    }

    private static void assignCourseToTeacher (Scanner scanner) {
        System.out.println("Enter teacher ID: ");
        int teacherId = scanner.nextInt();
        scanner.nextLine();

        Teacher teacher = null;
        for (Teacher t : teachers) {
            if (t.getTeacherId() == teacherId) {
                teacher = t;
                break;
            }
        }

        if (teacher == null) {
            System.out.println("Teacher not found.");
            return;
        }

        System.out.println("Available Courses:");
        for (Course course : courses) {
            System.out.println(course.getCourseId() + ": " + course.getCourseName());
        }

        System.out.println("Enter course ID to assign: ");
        int courseId = scanner.nextInt();
        scanner.nextLine();

        Course courseToAssign = null;
        for (Course c : courses) {
            if (c.getCourseId() == courseId) {
                courseToAssign = c;
                break;
            }
        }

        if (courseToAssign == null) {
            System.out.println("Course could not be found.");
            return;
        }

        boolean success = teacher.addCourse(courseToAssign);
        if (success) {
            System.out.println("Course assigned to teacher.");
        }
    }

    private static void viewStudentById (Scanner scanner){
        System.out.println("Enter student ID to view: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Student student = null;
        for (Student s : students) {
            if (s.getStudentId() == id) {
                student = s;
                break;
            }
        }

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.println("\n--- Student Info ---");
        System.out.println("Name: " + student.getName());
        System.out.println("Age: " + student.getAge());
        System.out.println("Email: " + student.getEmail());
        System.out.println("Grade: " + student.getGrade());
        System.out.println("ID: " + student.getStudentId());

        System.out.println("Courses:");
        if (student.getCourses().isEmpty()) {
            System.out.println("  No courses assigned.");
        } else {
            for (Course c : student.getCourses()) {
                System.out.println(" " + c.getCourseName() + " (ID: " + c.getCourseId() + ")");
            }
        }
    }

    private static void viewAllStudentsAndTeachers () {
        System.out.println("\n--- All Students ---");
        if (students.isEmpty()) {
            System.out.println("No students available.");
        } else {
            for (Student s : students) {
                System.out.println(s);
            }
        }

        System.out.println("\n--- All Teachers ---");
        if (teachers.isEmpty()) {
            System.out.println("No teachers avaialbe.");
        } else {
            for (Teacher t : teachers) {
                System.out.println(t);
            }
        }
    }

    private static void viewAllCourses () {
        System.out.println("\n--- All Courses ----");
        if (courses.isEmpty()) {
            System.out.println("No courses available.");
        } else {
            for (Course c : courses) {
                System.out.println("Course ID: " + c.getCourseId() + ", Name: " + c.getCourseName());
            }
        }
    }

    private static void updateStudent (Scanner scanner){
        System.out.println("Enter student ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Student student = null;
        for (Student s : students) {
            if (s.getStudentId() == id) {
                student = s;
                break;
            }
        }

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        try {
            System.out.print("Enter new name (current: " + student.getName() + "): ");
            String name = scanner.nextLine();
            student.setName(name);

            System.out.print("Enter new age (current: " + student.getAge() + "): ");
            int age = scanner.nextInt();
            scanner.nextLine();
            student.setAge(age);

            System.out.print("Enter new email (current: " + student.getEmail() + "): ");
            String email = scanner.nextLine();
            student.setEmail(email);

            System.out.print("Enter new grade (current: " + student.getGrade() + "): ");
            int grade = scanner.nextInt();
            scanner.nextLine();
            student.setGrade(grade);

            System.out.println("Student updated successfully.");
        } catch (Exception e) {
            System.out.println("Error updating student: " + e.getMessage());
            scanner.nextLine();
        }
    }

    private static void deleteStudent(Scanner scanner) {
        System.out.print("Enter student ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Student studentToRemove = null;
        for (Student s : students) {
            if (s.getStudentId() == id) {
                studentToRemove = s;
                break;
            }
        }

        if (studentToRemove == null) {
            System.out.println("Student not found.");
            return;
        }

        students.remove(studentToRemove);
        System.out.println("Student removed successfully.");
    }
}