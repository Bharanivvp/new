import java.util.*;

public class DeleteStudentModule {
    private HashMap<Integer, Student> studentMap;
    private TreeMap<Integer, Student> sortedStudents;
    private HashMap<String, ArrayList<Student>> departmentMap;
    private ActivityLogger logger;  // NEW

    public DeleteStudentModule(HashMap<Integer, Student> studentMap,
                               TreeMap<Integer, Student> sortedStudents,
                               HashMap<String, ArrayList<Student>> departmentMap) {
        this.studentMap = studentMap;
        this.sortedStudents = sortedStudents;
        this.departmentMap = departmentMap;
        this.logger = ActivityLogger.getInstance();  // NEW
    }

    public void execute(Scanner scanner) {
        System.out.println("\n╔══════════════════════════════╗");
        System.out.println("║      DELETE STUDENT          ║");
        System.out.println("╚══════════════════════════════╝");

        System.out.print("Enter Student ID to delete: ");
        int id = scanner.nextInt();

        Student student = studentMap.get(id);
        if (student == null) {
            System.out.println("❌ Student with ID " + id + " not found!");
            return;
        }

        System.out.println("\nStudent to delete: " + student);
        System.out.print("Are you sure you want to delete? (yes/no): ");
        scanner.nextLine();
        String confirm = scanner.nextLine();

        if (confirm.equalsIgnoreCase("yes")) {
            String studentName = student.getName();
            String studentDept = student.getDepartment();

            studentMap.remove(id);
            sortedStudents.remove(id);
            departmentMap.get(student.getDepartment()).remove(student);

            // LOG ACTIVITY
            logger.logActivity("DELETED", id, studentName,
                    "Student deleted - Department: " + studentDept);

            System.out.println("✅ Student deleted successfully!");
        } else {
            System.out.println("❌ Deletion cancelled!");
        }
    }
}