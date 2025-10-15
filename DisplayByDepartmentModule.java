import java.util.*;

public class DisplayByDepartmentModule {
    private HashMap<String, ArrayList<Student>> departmentMap;

    public DisplayByDepartmentModule(HashMap<String, ArrayList<Student>> departmentMap) {
        this.departmentMap = departmentMap;
    }

    public void execute(Scanner scanner) {
        System.out.println("\n╔══════════════════════════════╗");
        System.out.println("║  DISPLAY BY DEPARTMENT       ║");
        System.out.println("╚══════════════════════════════╝");

        scanner.nextLine();
        System.out.print("Enter Department: ");
        String department = scanner.nextLine();

        ArrayList<Student> students = departmentMap.get(department);

        if (students == null || students.isEmpty()) {
            System.out.println("❌ No students found in department: " + department);
            return;
        }

        System.out.println("\n✅ Students in " + department + " Department:");
        System.out.println("─".repeat(4));
        for (Student student : students) {
            System.out.println(student);
        }
        System.out.println("─".repeat(4));
        System.out.println("Total: " + students.size() + " student(s)");
    }
}