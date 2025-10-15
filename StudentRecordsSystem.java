import java.util.*;

public class StudentRecordsSystem {
    private HashMap<Integer, Student> studentMap;
    private TreeMap<Integer, Student> sortedStudents;
    private HashMap<String, ArrayList<Student>> departmentMap;

    private AddStudentModule addModule;
    private SearchByIdModule searchIdModule;
    private SearchByNameModule searchNameModule;
    private DisplayAllStudentsModule displayAllModule;
    private DisplayByDepartmentModule displayDeptModule;
    private TopStudentsByGpaModule topGpaModule;
    private UpdateStudentModule updateModule;
    private DeleteStudentModule deleteModule;
    private StatisticsModule statsModule;
    private ActivityRecordModule activityModule;  // NEW

    public StudentRecordsSystem() {
        this.studentMap = new HashMap<>();
        this.sortedStudents = new TreeMap<>();
        this.departmentMap = new HashMap<>();


        this.addModule = new AddStudentModule(studentMap, sortedStudents, departmentMap);
        this.searchIdModule = new SearchByIdModule(studentMap);
        this.searchNameModule = new SearchByNameModule(studentMap);
        this.displayAllModule = new DisplayAllStudentsModule(sortedStudents);
        this.displayDeptModule = new DisplayByDepartmentModule(departmentMap);
        this.topGpaModule = new TopStudentsByGpaModule(studentMap);
        this.updateModule = new UpdateStudentModule(studentMap, departmentMap);
        this.deleteModule = new DeleteStudentModule(studentMap, sortedStudents, departmentMap);
        this.statsModule = new StatisticsModule(studentMap, departmentMap);
        this.activityModule = new ActivityRecordModule(studentMap);  // NEW
    }

    public void loadSampleData() {
        addModule.addStudent(101, "Alice Johnson", 20, "Computer Science", 3.8);
        addModule.addStudent(102, "Bob Smith", 21, "Mathematics", 3.6);
        addModule.addStudent(103, "Charlie Brown", 19, "Computer Science", 3.9);
        addModule.addStudent(104, "Diana Prince", 22, "Physics", 3.7);
        addModule.addStudent(105, "Eve Williams", 20, "Mathematics", 3.95);
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n╔════════════════════════════════════════════╗");
            System.out.println("║   STUDENT RECORDS MANAGEMENT SYSTEM        ║");
            System.out.println("╠════════════════════════════════════════════╣");
            System.out.println("║  1.  Add New Student                       ║");
            System.out.println("║  2.  Search Student by ID                  ║");
            System.out.println("║  3.  Search Student by Name                ║");
            System.out.println("║  4.  Display All Students                  ║");
            System.out.println("║  5.  Display Students by Department        ║");
            System.out.println("║  6.  Display Top N Students by GPA         ║");
            System.out.println("║  7.  Update Student Information            ║");
            System.out.println("║  8.  Delete Student                        ║");
            System.out.println("║  9.  Display Statistics                    ║");
            System.out.println("║  10. Activity Records                      ║");  // NEW
            System.out.println("║  11. Exit                                  ║");
            System.out.println("╚════════════════════════════════════════════╝");
            System.out.print("\nEnter your choice: ");

            try {
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        addModule.execute(scanner);
                        break;
                    case 2:
                        searchIdModule.execute(scanner);
                        break;
                    case 3:
                        searchNameModule.execute(scanner);
                        break;
                    case 4:
                        displayAllModule.execute();
                        break;
                    case 5:
                        displayDeptModule.execute(scanner);
                        break;
                    case 6:
                        topGpaModule.execute(scanner);
                        break;
                    case 7:
                        updateModule.execute(scanner);
                        break;
                    case 8:
                        deleteModule.execute(scanner);
                        break;
                    case 9:
                        statsModule.execute();
                        break;
                    case 10:  // NEW
                        activityModule.execute(scanner);
                        break;
                    case 11:
                        System.out.println("\n✅ Thank you for using Student Records Management System!");
                        System.out.println("Goodbye! 👋");
                        scanner.close();
                        System.exit(0);
                    default:
                        System.out.println("\n❌ Invalid choice! Please enter a number between 1-11.");
                }
            } catch (InputMismatchException e) {
                System.out.println("\n❌ Invalid input! Please enter a number.");
                scanner.nextLine();
            }
        }
    }
}