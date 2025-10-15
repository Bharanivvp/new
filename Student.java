import java.util.*;

public class Student {
    private int id;
    private String name;
    private int age;
    private String department;
    private double gpa;
    private List<String> courses;

    public Student(int id, String name, int age, String department, double gpa) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.department = department;
        this.gpa = gpa;
        this.courses = new ArrayList<>();
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getDepartment() { return department; }
    public double getGpa() { return gpa; }
    public List<String> getCourses() { return courses; }

    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
    public void setDepartment(String department) { this.department = department; }
    public void setGpa(double gpa) { this.gpa = gpa; }
    public void addCourse(String course) { this.courses.add(course); }
    public void removeCourse(String course) { this.courses.remove(course); }

    @Override
    public String toString() {
        return String.format("ID: %d | Name: %s | Age: %d | Dept: %s | GPA: %.2f | Courses: %s",
                id, name, age, department, gpa, courses.isEmpty() ? "None" : courses);
    }
}