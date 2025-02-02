import java.util.*;

// Student Class
class Student {
    private String name;
    private String id;
    private Map<Course, Integer> enrolledCourses;

    public Student(String name, String id) {
        this.name = name;
        this.id = id;
        this.enrolledCourses = new HashMap<>();
    }

    public void enrollInCourse(Course course) {
        if (course.isEnrollmentOpen()) {
            enrolledCourses.put(course, null); 
            course.incrementEnrollment();
        } else {
            throw new IllegalArgumentException("Course is full!");
        }
    }

    public void assignGrade(Course course, int grade) {
        if (enrolledCourses.containsKey(course)) {
            enrolledCourses.put(course, grade);
        } else {
            throw new IllegalArgumentException("Student not enrolled in this course!");
        }
    }

    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public Map<Course, Integer> getEnrolledCourses() { return enrolledCourses; }
}

// Course Class
class Course {
    private String courseCode;
    private String courseName;
    private int maxCapacity;
    private int currentEnrollment;
    private static int totalEnrolledStudents = 0;

    public Course(String courseCode, String courseName, int maxCapacity) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.maxCapacity = maxCapacity;
        this.currentEnrollment = 0;
    }

    public boolean isEnrollmentOpen() {
        return currentEnrollment < maxCapacity;
    }

    public void incrementEnrollment() {
        if (isEnrollmentOpen()) {
            currentEnrollment++;
            totalEnrolledStudents++;
        } else {
            throw new IllegalStateException("Maximum capacity reached!");
        }
    }

    public static int getTotalEnrolledStudents() {
        return totalEnrolledStudents;
    }

    // Getters
    public String getCourseCode() { return courseCode; }
    public String getCourseName() { return courseName; }
    public int getMaxCapacity() { return maxCapacity; }
}

// CourseManagement Class
class CourseManagement {
    private static List<Course> courses = new ArrayList<>();
    private static Map<Student, Double> overallGrades = new HashMap<>();

    public static void addCourse(String courseCode, String courseName, int maxCapacity) {
        courses.add(new Course(courseCode, courseName, maxCapacity));
    }

    public static void enrollStudent(Student student, Course course) {
        student.enrollInCourse(course);
    }

    public static void assignGrade(Student student, Course course, int grade) {
        student.assignGrade(course, grade);
    }

    public static double calculateOverallGrade(Student student) {
        Map<Course, Integer> grades = student.getEnrolledCourses();
        double total = 0;
        int count = 0;
        for (Integer grade : grades.values()) {
            if (grade != null) {
                total += grade;
                count++;
            }
        }
        double overallGrade = (count > 0) ? total / count : 0;
        overallGrades.put(student, overallGrade);
        return overallGrade;
    }

    public static List<Course> getCourses() {
        return courses;
    }
}

// Administrator Interface
public class AdministratorInterface {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Student> students = new ArrayList<>();

        while (true) {
            System.out.println("\nCourse Enrollment and Grade Management System");
            System.out.println("1. Add Course");
            System.out.println("2. Enroll Student");
            System.out.println("3. Assign Grade");
            System.out.println("4. Calculate Overall Grade");
            System.out.println("5. View Total Enrolled Students");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter course code: ");
                        String courseCode = scanner.nextLine();
                        System.out.print("Enter course name: ");
                        String courseName = scanner.nextLine();
                        System.out.print("Enter max capacity: ");
                        int maxCapacity = scanner.nextInt();
                        CourseManagement.addCourse(courseCode, courseName, maxCapacity);
                        System.out.println("Course added successfully!");
                        break;

                    case 2:
                        System.out.print("Enter student name: ");
                        String studentName = scanner.nextLine();
                        System.out.print("Enter student ID: ");
                        String studentId = scanner.nextLine();
                        Student student = new Student(studentName, studentId);
                        students.add(student);

                        System.out.println("Available Courses:");
                        List<Course> availableCourses = CourseManagement.getCourses();
                        for (int i = 0; i < availableCourses.size(); i++) {
                            System.out.printf("%d. %s (%s)%n", i + 1, availableCourses.get(i).getCourseName(), availableCourses.get(i).getCourseCode());
                        }
                        System.out.print("Choose a course to enroll (by number): ");
                        int courseIndex = scanner.nextInt() - 1;
                        Course courseToEnroll = availableCourses.get(courseIndex);
                        CourseManagement.enrollStudent(student, courseToEnroll);
                        System.out.println("Student enrolled successfully!");
                        break;

                    case 3:
                        System.out.println("Students:");
                        for (int i = 0; i < students.size(); i++) {
                            System.out.printf("%d. %s (%s)%n", i + 1, students.get(i).getName(), students.get(i).getId());
                        }
                        System.out.print("Choose a student (by number): ");
                        int studentIndex = scanner.nextInt() - 1;
                        Student selectedStudent = students.get(studentIndex);

                        System.out.println("Courses:");
                        for (int i = 0; i < CourseManagement.getCourses().size(); i++) {
                            System.out.printf("%d. %s (%s)%n", i + 1, CourseManagement.getCourses().get(i).getCourseName(), CourseManagement.getCourses().get(i).getCourseCode());
                        }
                        System.out.print("Choose a course to assign grade (by number): ");
                        int courseToGradeIndex = scanner.nextInt() - 1;
                        Course courseToGrade = CourseManagement.getCourses().get(courseToGradeIndex);

                        System.out.print("Enter grade: ");
                        int grade = scanner.nextInt();
                        CourseManagement.assignGrade(selectedStudent, courseToGrade, grade);
                        System.out.println("Grade assigned successfully!");
                        break;

                    case 4:
                        System.out.println("Students:");
                        for (int i = 0; i < students.size(); i++) {
                            System.out.printf("%d. %s (%s)%n", i + 1, students.get(i).getName(), students.get(i).getId());
                        }
                        System.out.print("Choose a student (by number): ");
                        int selectedStudentIndex = scanner.nextInt() - 1;
                        Student studentForGrade = students.get(selectedStudentIndex);
                        double overallGrade = CourseManagement.calculateOverallGrade(studentForGrade);
                        System.out.printf("Overall Grade for %s: %.2f%n", studentForGrade.getName(), overallGrade);
                        break;

                    case 5:
                        int totalEnrolled = Course.getTotalEnrolledStudents();
                        System.out.println("Total Enrolled Students: " + totalEnrolled);
                        break;

                    case 6:
                        System.out.println("Exiting program. Goodbye!");
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid choice. Try again.");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
