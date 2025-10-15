import java.util.*;

public class UpdateStudentModule {
    private HashMap<Integer, Student> studentMap;
    private HashMap<String, ArrayList<Student>> departmentMap;
    private ActivityLogger logger;

    public UpdateStudentModule(HashMap<Integer, Student> studentMap,
                               HashMap<String, ArrayList<Student>> departmentMap) {
        this.studentMap = studentMap;
        this.departmentMap = departmentMap;
        this.logger = ActivityLogger.getInstance();
    }

    public void execute(Scanner scanner) {
        System.out.println("\n╔══════════════════════════════╗");
        System.out.println("║    UPDATE STUDENT INFO       ║");
        System.out.println("╚══════════════════════════════╝");

        // Get Student ID with validation
        int id = 0;
        boolean validId = false;
        while (!validId) {
            System.out.print("Enter Student ID to update: ");
            try {
                id = scanner.nextInt();
                scanner.nextLine();

                if (!studentMap.containsKey(id)) {
                    System.out.println("❌ Student with ID " + id + " not found!");
                    System.out.print("Do you want to try another ID? (yes/no): ");
                    String retry = scanner.nextLine().trim();
                    if (!retry.equalsIgnoreCase("yes") && !retry.equalsIgnoreCase("y")) {
                        System.out.println("⚠️  Operation cancelled.");
                        return;
                    }
                } else {
                    validId = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("❌ Invalid input! Please enter a numeric ID.");
                scanner.nextLine();
                System.out.print("Do you want to try again? (yes/no): ");
                String retry = scanner.nextLine().trim();
                if (!retry.equalsIgnoreCase("yes") && !retry.equalsIgnoreCase("y")) {
                    System.out.println("⚠️  Operation cancelled.");
                    return;
                }
            }
        }

        Student student = studentMap.get(id);
        System.out.println("\nCurrent Info: " + student);

        // Get update choice with validation
        int choice = 0;
        boolean validChoice = false;
        while (!validChoice) {
            System.out.println("\nWhat would you like to update?");
            System.out.println("1. Update Name");
            System.out.println("2. Update Age");
            System.out.println("3. Update Department");
            System.out.println("4. Update GPA");
            System.out.println("5. Add Course");
            System.out.println("6. Remove Course");
            System.out.print("Choose option: ");

            try {
                choice = scanner.nextInt();
                scanner.nextLine();

                if (choice < 1 || choice > 6) {
                    System.out.println("❌ Invalid option! Please choose between 1-6.");
                    System.out.print("Do you want to try again? (yes/no): ");
                    String retry = scanner.nextLine().trim();
                    if (!retry.equalsIgnoreCase("yes") && !retry.equalsIgnoreCase("y")) {
                        System.out.println("⚠️  Operation cancelled.");
                        return;
                    }
                } else {
                    validChoice = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("❌ Invalid input! Please enter a number between 1-6.");
                scanner.nextLine();
                System.out.print("Do you want to try again? (yes/no): ");
                String retry = scanner.nextLine().trim();
                if (!retry.equalsIgnoreCase("yes") && !retry.equalsIgnoreCase("y")) {
                    System.out.println("⚠️  Operation cancelled.");
                    return;
                }
            }
        }

        String oldValue, newValue;

        switch (choice) {
            case 1: // Update Name
                boolean validName = false;
                while (!validName) {
                    oldValue = student.getName();
                    System.out.print("Enter new name: ");
                    newValue = scanner.nextLine().trim();

                    if (newValue.isEmpty()) {
                        System.out.println("❌ Name cannot be empty!");
                        System.out.print("Do you want to try again? (yes/no): ");
                        String retry = scanner.nextLine().trim();
                        if (!retry.equalsIgnoreCase("yes") && !retry.equalsIgnoreCase("y")) {
                            System.out.println("⚠️  Update cancelled.");
                            return;
                        }
                    } else {
                        student.setName(newValue);
                        logger.logActivity("UPDATED", id, student.getName(),
                                "Name changed from '" + oldValue + "' to '" + newValue + "'");
                        System.out.println("✅ Name updated successfully!");
                        validName = true;
                    }
                }
                break;

            case 2: // Update Age
                boolean validAge = false;
                while (!validAge) {
                    int oldAge = student.getAge();
                    System.out.print("Enter new age: ");
                    try {
                        int newAge = scanner.nextInt();
                        scanner.nextLine();

                        if (newAge < 1 || newAge > 150) {
                            System.out.println("❌ Invalid age! Please enter an age between 1 and 150.");
                            System.out.print("Do you want to try again? (yes/no): ");
                            String retry = scanner.nextLine().trim();
                            if (!retry.equalsIgnoreCase("yes") && !retry.equalsIgnoreCase("y")) {
                                System.out.println("⚠️  Update cancelled.");
                                return;
                            }
                        } else {
                            student.setAge(newAge);
                            logger.logActivity("UPDATED", id, student.getName(),
                                    "Age changed from " + oldAge + " to " + newAge);
                            System.out.println("✅ Age updated successfully!");
                            validAge = true;
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("❌ Invalid input! Please enter a numeric age.");
                        scanner.nextLine();
                        System.out.print("Do you want to try again? (yes/no): ");
                        String retry = scanner.nextLine().trim();
                        if (!retry.equalsIgnoreCase("yes") && !retry.equalsIgnoreCase("y")) {
                            System.out.println("⚠️  Update cancelled.");
                            return;
                        }
                    }
                }
                break;

            case 3: // Update Department
                boolean validDept = false;
                while (!validDept) {
                    String oldDept = student.getDepartment();
                    System.out.print("Enter new department: ");
                    String newDept = scanner.nextLine().trim();

                    if (newDept.isEmpty()) {
                        System.out.println("❌ Department cannot be empty!");
                        System.out.print("Do you want to try again? (yes/no): ");
                        String retry = scanner.nextLine().trim();
                        if (!retry.equalsIgnoreCase("yes") && !retry.equalsIgnoreCase("y")) {
                            System.out.println("⚠️  Update cancelled.");
                            return;
                        }
                    } else {
                        student.setDepartment(newDept);

                        departmentMap.get(oldDept).remove(student);
                        departmentMap.putIfAbsent(newDept, new ArrayList<>());
                        departmentMap.get(newDept).add(student);

                        logger.logActivity("UPDATED", id, student.getName(),
                                "Department changed from '" + oldDept + "' to '" + newDept + "'");
                        System.out.println("✅ Department updated successfully!");
                        validDept = true;
                    }
                }
                break;

            case 4: // Update GPA
                boolean validGpa = false;
                while (!validGpa) {
                    double oldGpa = student.getGpa();
                    System.out.print("Enter new GPA (0.0-10.0): ");
                    try {
                        double gpa = scanner.nextDouble();
                        scanner.nextLine();

                        if (gpa < 0.0 || gpa > 10.0) {
                            System.out.println("❌ Invalid GPA! Must be between 0.0 and 10.0.");
                            System.out.print("Do you want to try again? (yes/no): ");
                            String retry = scanner.nextLine().trim();
                            if (!retry.equalsIgnoreCase("yes") && !retry.equalsIgnoreCase("y")) {
                                System.out.println("⚠️  Update cancelled.");
                                return;
                            }
                        } else {
                            student.setGpa(gpa);
                            logger.logActivity("UPDATED", id, student.getName(),
                                    String.format("GPA changed from %.2f to %.2f", oldGpa, gpa));
                            System.out.println("✅ GPA updated successfully!");
                            validGpa = true;
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("❌ Invalid input! Please enter a numeric GPA.");
                        scanner.nextLine();
                        System.out.print("Do you want to try again? (yes/no): ");
                        String retry = scanner.nextLine().trim();
                        if (!retry.equalsIgnoreCase("yes") && !retry.equalsIgnoreCase("y")) {
                            System.out.println("⚠️  Update cancelled.");
                            return;
                        }
                    }
                }
                break;

            case 5: // Add Course
                boolean validCourse = false;
                while (!validCourse) {
                    System.out.print("Enter course name to add: ");
                    String courseToAdd = scanner.nextLine().trim();

                    if (courseToAdd.isEmpty()) {
                        System.out.println("❌ Course name cannot be empty!");
                        System.out.print("Do you want to try again? (yes/no): ");
                        String retry = scanner.nextLine().trim();
                        if (!retry.equalsIgnoreCase("yes") && !retry.equalsIgnoreCase("y")) {
                            System.out.println("⚠️  Update cancelled.");
                            return;
                        }
                    } else {
                        student.addCourse(courseToAdd);
                        logger.logActivity("COURSE_ADDED", id, student.getName(),
                                "Enrolled in course: " + courseToAdd);
                        System.out.println("✅ Course added successfully!");
                        validCourse = true;
                    }
                }
                break;

            case 6: // Remove Course
                if (student.getCourses().isEmpty()) {
                    System.out.println("❌ No courses to remove!");
                } else {
                    boolean validRemove = false;
                    while (!validRemove) {
                        System.out.println("Current courses: " + student.getCourses());
                        System.out.print("Enter course name to remove: ");
                        String courseToRemove = scanner.nextLine().trim();

                        if (courseToRemove.isEmpty()) {
                            System.out.println("❌ Course name cannot be empty!");
                            System.out.print("Do you want to try again? (yes/no): ");
                            String retry = scanner.nextLine().trim();
                            if (!retry.equalsIgnoreCase("yes") && !retry.equalsIgnoreCase("y")) {
                                System.out.println("⚠️  Update cancelled.");
                                return;
                            }
                        } else if (!student.getCourses().contains(courseToRemove)) {
                            System.out.println("❌ Course '" + courseToRemove + "' not found!");
                            System.out.print("Do you want to try again? (yes/no): ");
                            String retry = scanner.nextLine().trim();
                            if (!retry.equalsIgnoreCase("yes") && !retry.equalsIgnoreCase("y")) {
                                System.out.println("⚠️  Update cancelled.");
                                return;
                            }
                        } else {
                            student.removeCourse(courseToRemove);
                            logger.logActivity("COURSE_REMOVED", id, student.getName(),
                                    "Removed from course: " + courseToRemove);
                            System.out.println("✅ Course removed successfully!");
                            validRemove = true;
                        }
                    }
                }
                break;
        }

        System.out.println("\nUpdated Info: " + student);
    }
}