import java.util.*;

public class SearchByIdModule {
    private HashMap<Integer, Student> studentMap;

    public SearchByIdModule(HashMap<Integer, Student> studentMap) {
        this.studentMap = studentMap;
    }

    public Student searchById(int id) {
        return studentMap.get(id);
    }

    public void execute(Scanner scanner) {
        System.out.println("\n╔══════════════════════════════╗");
        System.out.println("║    SEARCH STUDENT BY ID      ║");
        System.out.println("╚══════════════════════════════╝");

        System.out.print("Enter Student ID: ");
        int id = scanner.nextInt();

        Student student = searchById(id);
        if (student != null) {
            System.out.println("\n✅ Student Found:");
            System.out.println("─".repeat(80));
            System.out.println(student);
            System.out.println("─".repeat(80));
        } else {
            System.out.println("❌ Student with ID " + id + " not found!");
        }
    }
}