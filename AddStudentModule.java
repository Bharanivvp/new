import java.util.*;

public class AddStudentModule {
    private HashMap<Integer, Student> studentMap;
    private TreeMap<Integer, Student> sortedStudents;
    private HashMap<String, ArrayList<Student>> departmentMap;
    private ActivityLogger logger;  // NEW

    public AddStudentModule(HashMap<Integer, Student> studentMap,
                            TreeMap<Integer, Student> sortedStudents,
                            HashMap<String, ArrayList<Student>> departmentMap) {
        this.studentMap = studentMap;
        this.sortedStudents = sortedStudents;
        this.departmentMap = departmentMap;
        this.logger = ActivityLogger.getInstance();  // NEW
    }

    public boolean addStudent(int id, String name, int age, String department, double gpa) {
        if (studentMap.containsKey(id)) {
            System.out.println("❌ Error: Student with ID " + id + " already exists!");
            return false;
        }

        if (gpa < 0.0 || gpa > 10.0) {
            System.out.println("❌ Error: GPA must be between 0.0 and 10.0!");
            return false;
        }

        Student student = new Student(id, name, age, department, gpa);
        studentMap.put(id, student);
        sortedStudents.put(id, student);

        departmentMap.putIfAbsent(department, new ArrayList<>());
        departmentMap.get(department).add(student);

        // LOG ACTIVITY
        logger.logActivity("ADDED", id, name,
                String.format("Student added - Dept: %s, Age: %d, GPA: %.2f", department, age, gpa));

        System.out.println("✅ Student added successfully!");
        return true;
    }

    public void execute(Scanner scanner) {
        System.out.println("\n╔══════════════════════════════╗");
        System.out.println("║      ADD NEW STUDENT         ║");
        System.out.println("╚══════════════════════════════╝");

        System.out.print("Enter Student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Age: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Department(CSE/ECE/IT/AIML/AIDS/EEE/MECH/CHEM/MBA): ");
        String dept = scanner.nextLine();

        System.out.print("Enter GPA (0.0-10.0): ");
        double gpa = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Number of courses enrolled: ");
        int courseCount = scanner.nextInt();
        scanner.nextLine();

        boolean added = addStudent(id, name, age, dept, gpa);

        if (added && courseCount > 0) {
            Student student = studentMap.get(id);

            for (int i = 1; i <= courseCount; i++) {
                System.out.print("Course " + i + ": ");
                String course = scanner.nextLine().trim();
                if (!course.isEmpty()) {
                    student.addCourse(course);
                    logger.logActivity("COURSE_ADDED", id, name, "Enrolled in course: " + course);
                }
            }
            System.out.println("✅ Courses added: " + student.getCourses());
        }
    }
}