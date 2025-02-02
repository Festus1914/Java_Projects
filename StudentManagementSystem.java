import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class StudentManagementSystem {
    private JFrame frame;
    private JTable studentTable;
    private DefaultTableModel studentTableModel;
    private JComboBox<String> courseDropdown;
    private JComboBox<String> studentDropdown;
    private DefaultTableModel enrollmentTableModel;
    private DefaultTableModel gradeTableModel;

    private ArrayList<Student> students = new ArrayList<>();
    private ArrayList<String> courses = new ArrayList<>();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(StudentManagementSystem::new);
    }

    public StudentManagementSystem() {
        initializeData();
        initializeUI();
    }

    private void initializeData() {
        // Predefined courses
        courses.add("Math");
        courses.add("Science");
        courses.add("History");

        // Add some initial students
        students.add(new Student("S001", "Alice Brown", "alice@example.com"));
        students.add(new Student("S002", "Bob Smith", "bob@example.com"));
    }

    private void initializeUI() {
        frame = new JFrame("Student Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 700);
        frame.setLayout(new BorderLayout());

        JTabbedPane tabbedPane = new JTabbedPane();

        // Tabs
        tabbedPane.add("Student Management", createStudentManagementPanel());
        tabbedPane.add("Course Enrollment", createCourseEnrollmentPanel());
        tabbedPane.add("Grade Management", createGradeManagementPanel());

        frame.add(tabbedPane, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private JPanel createStudentManagementPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        studentTableModel = new DefaultTableModel(new String[]{"ID", "Name", "Email"}, 0);
        studentTable = new JTable(studentTableModel);

        // Populate table with initial data
        for (Student student : students) {
            studentTableModel.addRow(new Object[]{student.id, student.name, student.email});
        }

        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField emailField = new JTextField();
        JButton addButton = new JButton("Add Student");
        JButton updateButton = new JButton("Update Student");

        inputPanel.add(new JLabel("ID:"));
        inputPanel.add(idField);
        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Email:"));
        inputPanel.add(emailField);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);

        addButton.addActionListener(e -> {
            String id = idField.getText().trim();
            String name = nameField.getText().trim();
            String email = emailField.getText().trim();

            if (id.isEmpty() || name.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "All fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                students.add(new Student(id, name, email));
                studentTableModel.addRow(new Object[]{id, name, email});
                idField.setText("");
                nameField.setText("");
                emailField.setText("");
                updateStudentDropdown();
            }
        });

        updateButton.addActionListener(e -> {
            int selectedRow = studentTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(frame, "Select a student to update.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String id = (String) studentTableModel.getValueAt(selectedRow, 0);
            String name = (String) studentTableModel.getValueAt(selectedRow, 1);
            String email = (String) studentTableModel.getValueAt(selectedRow, 2);

            JTextField idFieldUpdate = new JTextField(id);
            JTextField nameFieldUpdate = new JTextField(name);
            JTextField emailFieldUpdate = new JTextField(email);

            JPanel updatePanel = new JPanel(new GridLayout(3, 2, 10, 10));
            updatePanel.add(new JLabel("ID:"));
            updatePanel.add(idFieldUpdate);
            updatePanel.add(new JLabel("Name:"));
            updatePanel.add(nameFieldUpdate);
            updatePanel.add(new JLabel("Email:"));
            updatePanel.add(emailFieldUpdate);

            int result = JOptionPane.showConfirmDialog(frame, updatePanel, "Update Student", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                studentTableModel.setValueAt(idFieldUpdate.getText(), selectedRow, 0);
                studentTableModel.setValueAt(nameFieldUpdate.getText(), selectedRow, 1);
                studentTableModel.setValueAt(emailFieldUpdate.getText(), selectedRow, 2);
                updateStudentDropdown();
            }
        });

        panel.add(new JScrollPane(studentTable), BorderLayout.CENTER);
        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createCourseEnrollmentPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        courseDropdown = new JComboBox<>(courses.toArray(new String[0]));
        studentDropdown = new JComboBox<>();
        updateStudentDropdown();

        JButton enrollButton = new JButton("Enroll");
        enrollmentTableModel = new DefaultTableModel(new String[]{"Student", "Course"}, 0);
        JTable enrollmentTable = new JTable(enrollmentTableModel);

        JPanel inputPanel = new JPanel(new GridLayout(2, 3, 10, 10));
        inputPanel.add(new JLabel("Course:"));
        inputPanel.add(courseDropdown);
        inputPanel.add(enrollButton);
        inputPanel.add(new JLabel("Student:"));
        inputPanel.add(studentDropdown);

        enrollButton.addActionListener(e -> {
            String course = (String) courseDropdown.getSelectedItem();
            String student = (String) studentDropdown.getSelectedItem();

            if (course == null || student == null) {
                JOptionPane.showMessageDialog(frame, "Select a course and a student.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                enrollmentTableModel.addRow(new Object[]{student, course});
            }
        });

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(enrollmentTable), BorderLayout.CENTER);

        return panel;
    }

    private JPanel createGradeManagementPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        gradeTableModel = new DefaultTableModel(new String[]{"Student", "Course", "Grade"}, 0);
        JTable gradeTable = new JTable(gradeTableModel);

        JComboBox<String> gradeDropdown = new JComboBox<>(new String[]{"A", "B", "C", "D", "F"});
        JButton assignGradeButton = new JButton("Assign Grade");

        JPanel inputPanel = new JPanel(new GridLayout(2, 3, 10, 10));
        inputPanel.add(new JLabel("Student:"));
        inputPanel.add(studentDropdown);
        inputPanel.add(assignGradeButton);
        inputPanel.add(new JLabel("Course:"));
        inputPanel.add(courseDropdown);
        inputPanel.add(gradeDropdown);

        assignGradeButton.addActionListener(e -> {
            String student = (String) studentDropdown.getSelectedItem();
            String course = (String) courseDropdown.getSelectedItem();
            String grade = (String) gradeDropdown.getSelectedItem();

            if (student == null || course == null || grade == null) {
                JOptionPane.showMessageDialog(frame, "Select a student, course, and grade.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                gradeTableModel.addRow(new Object[]{student, course, grade});
            }
        });

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(gradeTable), BorderLayout.CENTER);

        return panel;
    }

    private void updateStudentDropdown() {
        studentDropdown.removeAllItems();
        for (Student student : students) {
            studentDropdown.addItem(student.toString());
        }
    }

    static class Student {
        String id, name, email;

        Student(String id, String name, String email) {
            this.id = id;
            this.name = name;
            this.email = email;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
