import java.util.*;

public class SearchByNameModule {
    private HashMap<Integer, Student> studentMap;

    public SearchByNameModule(HashMap<Integer, Student> studentMap) {
        this.studentMap = studentMap;
    }

    public List<Student> searchByName(String name) {
        List<Student> results = new ArrayList<>();
        for (Student student : studentMap.values()) {
            if (student.getName().toLowerCase().contains(name.toLowerCase())) {
                results.add(student);
            }
        }
        return results;
    }

    public void execute(Scanner scanner) {
        System.out.println("\n╔══════════════════════════════╗");
        System.out.println("║   SEARCH STUDENT BY NAME     ║");
        System.out.println("╚══════════════════════════════╝");

        scanner.nextLine();
        System.out.print("Enter Name to search: ");
        String name = scanner.nextLine();

        List<Student> results = searchByName(name);

        if (results.isEmpty()) {
            System.out.println("❌ No students found with name containing '" + name + "'");
        } else {
            System.out.println("\n✅ Search Results for '" + name + "':");
            System.out.println("─".repeat(4));
            for (Student student : results) {
                System.out.println(student);
            }
            System.out.println("─".repeat(4));
            System.out.println("Total: " + results.size() + " student(s) found");
        }
    }
}