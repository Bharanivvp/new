import java.util.*;

public class StatisticsModule {
    private HashMap<Integer, Student> studentMap;
    private HashMap<String, ArrayList<Student>> departmentMap;

    public StatisticsModule(HashMap<Integer, Student> studentMap,
                            HashMap<String, ArrayList<Student>> departmentMap) {
        this.studentMap = studentMap;
        this.departmentMap = departmentMap;
    }

    public void execute() {
        if (studentMap.isEmpty()) {
            System.out.println("❌ No students in the system!");
            return;
        }

        double totalGpa = 0;
        double maxGpa = 0;
        double minGpa = 10.0;
        int totalCourses = 0;

        for (Student student : studentMap.values()) {
            double gpa = student.getGpa();
            totalGpa += gpa;
            maxGpa = Math.max(maxGpa, gpa);
            minGpa = Math.min(minGpa, gpa);
            totalCourses += student.getCourses().size();
        }

        System.out.println("\n╔══════════════════════════════════════════════════════╗");
        System.out.println("║              SYSTEM STATISTICS                       ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("📊 General Statistics:");
        System.out.println("  • Total Students: " + studentMap.size());
        System.out.println("  • Total Departments: " + departmentMap.size());
        System.out.println("  • Total Courses Enrolled: " + totalCourses);
        System.out.println();
        System.out.println("🎓 GPA Statistics:");
        System.out.println("  • Average GPA: " + String.format("%.2f", totalGpa / studentMap.size()));
        System.out.println("  • Highest GPA: " + String.format("%.2f", maxGpa));
        System.out.println("  • Lowest GPA: " + String.format("%.2f", minGpa));
        System.out.println();
        System.out.println("🏛️  Department-wise Distribution:");

        for (Map.Entry<String, ArrayList<Student>> entry : departmentMap.entrySet()) {
            double deptAvgGpa = 0;
            for (Student s : entry.getValue()) {
                deptAvgGpa += s.getGpa();
            }
            deptAvgGpa /= entry.getValue().size();

            System.out.println("  • " + entry.getKey() + ": " + entry.getValue().size() +
                    " students (Avg GPA: " + String.format("%.2f", deptAvgGpa) + ")");
        }
        System.out.println("─".repeat(6));
    }
}