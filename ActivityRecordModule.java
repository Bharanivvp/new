import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// ==================== ACTIVITY RECORD CLASS ====================
class ActivityRecord {
    private static int activityCounter = 1;
    private int activityId;
    private String activityType;
    private int studentId;
    private String studentName;
    private String description;
    private LocalDateTime timestamp;

    public ActivityRecord(String activityType, int studentId, String studentName, String description) {
        this.activityId = activityCounter++;
        this.activityType = activityType;
        this.studentId = studentId;
        this.studentName = studentName;
        this.description = description;
        this.timestamp = LocalDateTime.now();
    }

    public int getActivityId() { return activityId; }
    public String getActivityType() { return activityType; }
    public int getStudentId() { return studentId; }
    public String getStudentName() { return studentName; }
    public String getDescription() { return description; }
    public LocalDateTime getTimestamp() { return timestamp; }

    public String getFormattedTimestamp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return timestamp.format(formatter);
    }

    @Override
    public String toString() {
        return String.format("[%d] %s | Student: %s (ID: %d) | %s | Time: %s",
                activityId, activityType, studentName, studentId, description, getFormattedTimestamp());
    }
}

// ==================== ACTIVITY LOGGER ====================
class ActivityLogger {
    private static ActivityLogger instance;
    private ArrayList<ActivityRecord> activityLog;

    private ActivityLogger() {
        activityLog = new ArrayList<>();
    }

    public static ActivityLogger getInstance() {
        if (instance == null) {
            instance = new ActivityLogger();
        }
        return instance;
    }

    public void logActivity(String activityType, int studentId, String studentName, String description) {
        ActivityRecord record = new ActivityRecord(activityType, studentId, studentName, description);
        activityLog.add(record);
    }

    public ArrayList<ActivityRecord> getAllActivities() {
        return new ArrayList<>(activityLog);
    }

    public ArrayList<ActivityRecord> getActivitiesByStudent(int studentId) {
        ArrayList<ActivityRecord> studentActivities = new ArrayList<>();
        for (ActivityRecord record : activityLog) {
            if (record.getStudentId() == studentId) {
                studentActivities.add(record);
            }
        }
        return studentActivities;
    }

    public ArrayList<ActivityRecord> getActivitiesByType(String activityType) {
        ArrayList<ActivityRecord> typeActivities = new ArrayList<>();
        for (ActivityRecord record : activityLog) {
            if (record.getActivityType().equalsIgnoreCase(activityType)) {
                typeActivities.add(record);
            }
        }
        return typeActivities;
    }

    public int getTotalActivities() {
        return activityLog.size();
    }
}

// ==================== ACTIVITY RECORD MODULE ====================
public class ActivityRecordModule {
    private ActivityLogger logger;
    private HashMap<Integer, Student> studentMap;

    public ActivityRecordModule(HashMap<Integer, Student> studentMap) {
        this.logger = ActivityLogger.getInstance();
        this.studentMap = studentMap;
    }

    public void execute(Scanner scanner) {
        System.out.println("\n╔══════════════════════════════════╗");
        System.out.println("║    ACTIVITY RECORD MENU          ║");
        System.out.println("╚══════════════════════════════════╝");
        System.out.println("1. View All Activities");
        System.out.println("2. View Activities by Student");
        System.out.println("3. View Activities by Type");
        System.out.println("4. View Activity Statistics");
        System.out.println("5. Back to Main Menu");
        System.out.print("\nChoose option: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                viewAllActivities();
                break;
            case 2:
                viewActivitiesByStudent(scanner);
                break;
            case 3:
                viewActivitiesByType(scanner);
                break;
            case 4:
                viewActivityStatistics();
                break;
            case 5:
                return;
            default:
                System.out.println("❌ Invalid option!");
        }
    }

    private void viewAllActivities() {
        System.out.println("\n╔══════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                          ALL ACTIVITY RECORDS                                ║");
        System.out.println("╚══════════════════════════════════════════════════════════════════════════════╝");

        ArrayList<ActivityRecord> activities = logger.getAllActivities();

        if (activities.isEmpty()) {
            System.out.println("❌ No activities recorded yet!");
            return;
        }

        System.out.println();
        for (ActivityRecord record : activities) {
            System.out.println(record);
        }
        System.out.println("\n─".repeat(80));
        System.out.println("Total Activities: " + activities.size());
    }

    private void viewActivitiesByStudent(Scanner scanner) {
        System.out.println("\n╔══════════════════════════════════╗");
        System.out.println("║   ACTIVITIES BY STUDENT          ║");
        System.out.println("╚══════════════════════════════════╝");

        System.out.print("Enter Student ID: ");
        int studentId = scanner.nextInt();

        Student student = studentMap.get(studentId);
        if (student == null) {
            System.out.println("❌ Student with ID " + studentId + " not found!");
            return;
        }

        ArrayList<ActivityRecord> activities = logger.getActivitiesByStudent(studentId);

        if (activities.isEmpty()) {
            System.out.println("❌ No activities found for student: " + student.getName());
            return;
        }

        System.out.println("\n✅ Activities for: " + student.getName() + " (ID: " + studentId + ")");
        System.out.println("─".repeat(80));
        for (ActivityRecord record : activities) {
            System.out.println(record);
        }
        System.out.println("─".repeat(80));
        System.out.println("Total: " + activities.size() + " activity/activities");
    }

    private void viewActivitiesByType(Scanner scanner) {
        System.out.println("\n╔══════════════════════════════════╗");
        System.out.println("║   ACTIVITIES BY TYPE             ║");
        System.out.println("╚══════════════════════════════════╝");

        System.out.println("\nActivity Types:");
        System.out.println("1. ADDED");
        System.out.println("2. UPDATED");
        System.out.println("3. DELETED");
        System.out.println("4. COURSE_ADDED");
        System.out.println("5. COURSE_REMOVED");
        System.out.print("\nEnter activity type name: ");
        String type = scanner.nextLine().trim().toUpperCase();

        ArrayList<ActivityRecord> activities = logger.getActivitiesByType(type);

        if (activities.isEmpty()) {
            System.out.println("❌ No activities found for type: " + type);
            return;
        }

        System.out.println("\n✅ Activities of type: " + type);
        System.out.println("─".repeat(80));
        for (ActivityRecord record : activities) {
            System.out.println(record);
        }
        System.out.println("─".repeat(80));
        System.out.println("Total: " + activities.size() + " activity/activities");
    }

    private void viewActivityStatistics() {
        System.out.println("\n╔══════════════════════════════════════════════════════╗");
        System.out.println("║              ACTIVITY STATISTICS                     ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");

        ArrayList<ActivityRecord> allActivities = logger.getAllActivities();

        if (allActivities.isEmpty()) {
            System.out.println("❌ No activities recorded yet!");
            return;
        }

        // Count by type
        HashMap<String, Integer> typeCounts = new HashMap<>();
        for (ActivityRecord record : allActivities) {
            String type = record.getActivityType();
            typeCounts.put(type, typeCounts.getOrDefault(type, 0) + 1);
        }

        System.out.println("\n📊 Total Activities: " + logger.getTotalActivities());
        System.out.println("\n📋 Activity Breakdown by Type:");
        for (Map.Entry<String, Integer> entry : typeCounts.entrySet()) {
            System.out.println("  • " + entry.getKey() + ": " + entry.getValue());
        }

        // Most recent activity
        if (!allActivities.isEmpty()) {
            ActivityRecord latest = allActivities.get(allActivities.size() - 1);
            System.out.println("\n🕒 Most Recent Activity:");
            System.out.println("  " + latest);
        }

        System.out.println("\n─".repeat(56));
    }
}