import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Course {
    String courseCode;
    String title;
    String description;
    int capacity;
    String schedule;
    int registeredStudents;

    Course(String courseCode, String title, String description, int capacity, String schedule) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.registeredStudents = 0;
    }

    boolean registerStudent() {
        if (registeredStudents < capacity) {
            registeredStudents++;
            return true;
        }
        return false;
    }

    boolean removeStudent() {
        if (registeredStudents > 0) {
            registeredStudents--;
            return true;
        }
        return false;
    }

    int availableSlots() {
        return capacity - registeredStudents;
    }

    @Override
    public String toString() {
        return "Course Code: " + courseCode + "\nTitle: " + title + "\nDescription: " + description +
                "\nCapacity: " + capacity + "\nSchedule: " + schedule + "\nAvailable Slots: " + availableSlots();
    }
}

class Student {
    String studentID;
    String name;
    ArrayList<Course> registeredCourses;

    Student(String studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    boolean registerCourse(Course course) {
        if (course.registerStudent()) {
            registeredCourses.add(course);
            return true;
        }
        return false;
    }

    boolean dropCourse(Course course) {
        if (registeredCourses.contains(course) && course.removeStudent()) {
            registeredCourses.remove(course);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Student ID: ").append(studentID).append("\nName: ").append(name).append("\nRegistered Courses:\n");
        for (Course course : registeredCourses) {
            sb.append(course.title).append("\n");
        }
        return sb.toString();
    }
}

public class RegistrationSystem {
    static HashMap<String, Course> courseDatabase = new HashMap<>();
    static HashMap<String, Student> studentDatabase = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Add some sample courses to the database
        courseDatabase.put("CS101", new Course("CS101", "Introduction to Computer Science", "Basics of CS", 30, "Mon-Wed 9-10 AM"));
        courseDatabase.put("MA101", new Course("MA101", "Calculus I", "Introduction to Calculus", 40, "Tue-Thu 11-12 AM"));

        // Add some sample students to the database
        studentDatabase.put("S001", new Student("S001", "John Doe"));
        studentDatabase.put("S002", new Student("S002", "Jane Smith"));

        while (true) {
            System.out.println("\nStudent Course Registration System");
            System.out.println("1. Display available courses");
            System.out.println("2. Register a student for a course");
            System.out.println("3. Drop a course for a student");
            System.out.println("4. Display student details");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // consume newline

            switch (choice) {
                case 1:
                    displayAvailableCourses();
                    break;
                case 2:
                    registerStudentForCourse(scanner);
                    break;
                case 3:
                    dropStudentCourse(scanner);
                    break;
                case 4:
                    displayStudentDetails(scanner);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    static void displayAvailableCourses() {
        System.out.println("\nAvailable Courses:");
        for (Course course : courseDatabase.values()) {
            System.out.println(course);
        }
    }

    static void registerStudentForCourse(Scanner scanner) {
        System.out.print("Enter student ID: ");
        String studentID = scanner.nextLine();
        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine();

        Student student = studentDatabase.get(studentID);
        Course course = courseDatabase.get(courseCode);

        if (student != null && course != null) {
            if (student.registerCourse(course)) {
                System.out.println("Student registered for the course successfully.");
            } else {
                System.out.println("Course is full. Registration failed.");
            }
        } else {
            System.out.println("Invalid student ID or course code.");
        }
    }

    static void dropStudentCourse(Scanner scanner) {
        System.out.print("Enter student ID: ");
        String studentID = scanner.nextLine();
        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine();

        Student student = studentDatabase.get(studentID);
        Course course = courseDatabase.get(courseCode);

        if (student != null && course != null) {
            if (student.dropCourse(course)) {
                System.out.println("Course dropped successfully.");
            } else {
                System.out.println("Student is not registered for this course.");
            }
        } else {
            System.out.println("Invalid student ID or course code.");
        }
    }

    static void displayStudentDetails(Scanner scanner) {
        System.out.print("Enter student ID: ");
        String studentID = scanner.nextLine();

        Student student = studentDatabase.get(studentID);

        if (student != null) {
            System.out.println(student);
        } else {
            System.out.println("Invalid student ID.");
        }
    }
}

