public class StudentRecordsApp {
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════╗");
        System.out.println("║                                            ║");
        System.out.println("║   WELCOME TO STUDENT RECORDS SYSTEM        ║");
        System.out.println("║   Modular Design with Data Structures      ║");
        System.out.println("║                                            ║");
        System.out.println("╚════════════════════════════════════════════╝");

        StudentRecordsSystem system = new StudentRecordsSystem();

        //System.out.println("\n📦 Loading sample data...");
        //4system.loadSampleData();
       //2 System.out.println("✅ Sample data loaded successfully!");

        system.run();
    }
}
