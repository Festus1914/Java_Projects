import java.util.ArrayList;
import java.util.Scanner;

public class StudentManagement {

    // Nested Student class
    static class Student {
        private int id;
        private String name;
        private int age;
        private String grade;

        // Constructor
        public Student(int id, String name, int age, String grade) {
            this.id = id;
            this.name = name;
            this.age = age;
            this.grade = grade;
        }

        // Getter and Setter methods
        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

        @Override
        public String toString() {
            return "ID: " + id + ", Name: " + name + ", Age: " + age + ", Grade: " + grade;
        }
    }

    // Variables to manage students
    private static ArrayList<Student> studentList = new ArrayList<>();
    private static int totalStudents = 0;

    // Add a new student
    public static void addStudent() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Enter Student ID: ");
            int id = Integer.parseInt(scanner.nextLine());
            System.out.println("Enter Student Name: ");
            String name = scanner.nextLine();
            System.out.println("Enter Student Age: ");
            int age = Integer.parseInt(scanner.nextLine());
            System.out.println("Enter Student Grade: ");
            String grade = scanner.nextLine();

            Student student = new Student(id, name, age, grade);
            studentList.add(student);
            totalStudents++;
            System.out.println("Student added successfully!");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter numeric values for ID and Age.");
        }
    }

    // Update an existing student's information
    public static void updateStudent() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Enter Student ID to update: ");
            int id = Integer.parseInt(scanner.nextLine());

            Student student = findStudentById(id);
            if (student != null) {
                System.out.println("Current Name: " + student.getName());
                System.out.print("Enter new name (Leave blank to keep current): ");
                String name = scanner.nextLine();
                if (!name.isBlank()) student.setName(name);

                System.out.println("Current Age: " + student.getAge());
                System.out.print("Enter new age (Leave blank to keep current): ");
                String ageInput = scanner.nextLine();
                if (!ageInput.isBlank()) student.setAge(Integer.parseInt(ageInput));

                System.out.println("Current Grade: " + student.getGrade());
                System.out.print("Enter new grade (Leave blank to keep current): ");
                String grade = scanner.nextLine();
                if (!grade.isBlank()) student.setGrade(grade);

                System.out.println("Student information updated successfully!");
            } else {
                System.out.println("Student with ID " + id + " not found.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter numeric values for ID and Age.");
        }
    }

    // View all student details
    public static void viewStudents() {
        if (studentList.isEmpty()) {
            System.out.println("No students found.");
        } else {
            System.out.println("\nList of Students:");
            for (Student student : studentList) {
                System.out.println(student);
            }
        }
    }

    // Helper method to find student by ID
    private static Student findStudentById(int id) {
        for (Student student : studentList) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    // Main menu
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nStudent Record Management System");
            System.out.println("1. Add New Student");
            System.out.println("2. Update Student Information");
            System.out.println("3. View Student Details");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    addStudent();
                    break;
                case "2":
                    updateStudent();
                    break;
                case "3":
                    viewStudents();
                    break;
                case "4":
                    System.out.println("Exiting the program. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice! Please select a valid option.");
            }
        }
    }
}
