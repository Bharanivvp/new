import java.util.*;

public class TopStudentsByGpaModule {
    private HashMap<Integer, Student> studentMap;

    public TopStudentsByGpaModule(HashMap<Integer, Student> studentMap) {
        this.studentMap = studentMap;
    }

    public void execute(Scanner scanner) {
        System.out.println("\n╔══════════════════════════════╗");
        System.out.println("║   TOP STUDENTS BY GPA        ║");
        System.out.println("╚══════════════════════════════╝");

        System.out.print("Enter number of top students: ");
        int n = scanner.nextInt();

        if (studentMap.isEmpty()) {
            System.out.println("❌ No students in the system!");
            return;
        }

        PriorityQueue<Student> gpaRanking = new PriorityQueue<>(
                (s1, s2) -> Double.compare(s2.getGpa(), s1.getGpa())
        );
        gpaRanking.addAll(studentMap.values());

        System.out.println("\n✅ Top " + n + " Students by GPA:");
        System.out.println("─".repeat(4));

        int count = 0;
        int rank = 1;
        while (!gpaRanking.isEmpty() && count < n) {
            System.out.println("Rank " + rank + ": " + gpaRanking.poll());
            count++;
            rank++;
        }
        System.out.println("─".repeat(4));
    }
}