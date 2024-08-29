import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SimpleCourseManagementSystem {

    // Class to represent a Course
    static class Course {
        String courseCode;
        String title;
        String description;
        int capacity;
        String schedule;
        List<Student> registeredStudents;

        public Course(String courseCode, String title, String description, int capacity, String schedule) {
            this.courseCode = courseCode;
            this.title = title;
            this.description = description;
            this.capacity = capacity;
            this.schedule = schedule;
            this.registeredStudents = new ArrayList<>();
        }

        public int getAvailableSlots() {
            return capacity - registeredStudents.size();
        }

        public boolean registerStudent(Student student) {
            if (getAvailableSlots() > 0) {
                registeredStudents.add(student);
                return true;
            }
            return false;
        }

        public boolean removeStudent(Student student) {
            return registeredStudents.remove(student);
        }

        @Override
        public String toString() {
            return "Course Code: " + courseCode + ", Title: " + title + ", Description: " + description +
                   ", Capacity: " + capacity + ", Schedule: " + schedule + ", Available Slots: " + getAvailableSlots();
        }
    }

    // Class to represent a Student
    static class Student {
        String studentID;
        String name;
        List<Course> registeredCourses;

        public Student(String studentID, String name) {
            this.studentID = studentID;
            this.name = name;
            this.registeredCourses = new ArrayList<>();
        }

        public void registerCourse(Course course) {
            if (course.registerStudent(this)) {
                registeredCourses.add(course);
            } else {
                System.out.println("Course " + course.title + " is full!");
            }
        }

        public void dropCourse(Course course) {
            if (registeredCourses.remove(course)) {
                course.removeStudent(this);
            } else {
                System.out.println("You are not registered in " + course.title);
            }
        }
    }

    // Main method to handle the course management system
    public static void main(String[] args) {
        List<Course> courses = new ArrayList<>();
        List<Student> students = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        // Adding some courses
        courses.add(new Course("CS101", "Introduction to Programming", "Learn the basics of programming.", 30, "Mon 9:00 - 11:00"));
        courses.add(new Course("CS102", "Data Structures", "Introduction to data structures.", 25, "Wed 10:00 - 12:00"));

        // Adding some students
        students.add(new Student("S001", "Alice"));
        students.add(new Student("S002", "Bob"));

        // Simple UI loop
        while (true) {
            System.out.println("\nCourse Management System");
            System.out.println("1. List Available Courses");
            System.out.println("2. Register for a Course");
            System.out.println("3. Drop a Course");
            System.out.println("4. Add a New Student");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            if (option == 1) {
                // List Available Courses
                System.out.println("\nAvailable Courses:");
                for (Course course : courses) {
                    System.out.println(course);
                }
            } else if (option == 2) {
                // Register for a Course
                System.out.print("Enter Student ID: ");
                String studentID = scanner.nextLine();
                Student student = findStudentById(students, studentID);

                if (student != null) {
                    System.out.print("Enter Course Code: ");
                    String courseCode = scanner.nextLine();
                    Course course = findCourseByCode(courses, courseCode);

                    if (course != null) {
                        student.registerCourse(course);
                    } else {
                        System.out.println("Course not found.");
                    }
                } else {
                    System.out.println("Student not found.");
                }
            } else if (option == 3) {
                // Drop a Course
                System.out.print("Enter Student ID: ");
                String studentID = scanner.nextLine();
                Student student = findStudentById(students, studentID);

                if (student != null) {
                    System.out.print("Enter Course Code: ");
                    String courseCode = scanner.nextLine();
                    Course course = findCourseByCode(courses, courseCode);

                    if (course != null) {
                        student.dropCourse(course);
                    } else {
                        System.out.println("Course not found.");
                    }
                } else {
                    System.out.println("Student not found.");
                }
            } else if (option == 4) {
                // Add a New Student
                System.out.print("Enter Student ID: ");
                String studentID = scanner.nextLine();
                System.out.print("Enter Student Name: ");
                String name = scanner.nextLine();

                if (findStudentById(students, studentID) == null) {
                    students.add(new Student(studentID, name));
                    System.out.println("Student added successfully.");
                } else {
                    System.out.println("Student ID already exists.");
                }
            } else if (option == 5) {
                // Exit
                System.out.println("Exiting the system.");
                break;
            } else {
                System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }

    // Helper method to find a Course by its code
    private static Course findCourseByCode(List<Course> courses, String courseCode) {
        for (Course course : courses) {
            if (course.courseCode.equals(courseCode)) {
                return course;
            }
        }
        return null;
    }

    // Helper method to find a Student by their ID
    private static Student findStudentById(List<Student> students, String studentID) {
        for (Student student : students) {
            if (student.studentID.equals(studentID)) {
                return student;
            }
        }
        return null;
    }
}
