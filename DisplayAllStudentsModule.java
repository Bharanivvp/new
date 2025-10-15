import java.util.*;

public class DisplayAllStudentsModule {
    private TreeMap<Integer, Student> sortedStudents;

    public DisplayAllStudentsModule(TreeMap<Integer, Student> sortedStudents) {
        this.sortedStudents = sortedStudents;
    }

    public void execute() {
        if (sortedStudents.isEmpty()) {
            System.out.println("❌ No students in the system!");
            return;
        }

        System.out.println("\n╔══════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                     ALL STUDENTS (Sorted by ID)                              ║");
        System.out.println("╚══════════════════════════════════════════════════════════════════════════════╝");
        System.out.println();

        for (Student student : sortedStudents.values()) {
            System.out.println(student);
        }

        System.out.println("\n─".repeat(4));
        System.out.println("Total Students: " + sortedStudents.size());
    }
}