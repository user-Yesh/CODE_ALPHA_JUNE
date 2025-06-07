import java.util.ArrayList;
import java.util.Scanner;

class Student {
    String name;
    double grade;

    Student(String name, double grade) {
        this.name = name;
        this.grade = grade;
    }
}

public class StudentGradeManagerConsole {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();

        System.out.println("=== Student Grade Manager ===");

        while (true) {
            System.out.print("\nEnter student name (or 'done' to finish): ");
            String name = scanner.nextLine();

            if (name.equalsIgnoreCase("done")) {
                break;
            }

            System.out.print("Enter grade (0-100): ");
            double grade;
            try {
                grade = Double.parseDouble(scanner.nextLine());
                if (grade < 0 || grade > 100) {
                    System.out.println("Invalid grade. Must be between 0 and 100.");
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            students.add(new Student(name, grade));
            System.out.println("Student added!");
        }

        if (students.isEmpty()) {
            System.out.println("\nNo students were added.");
            return;
        }

        // Summary Report
        System.out.println("\n=== Summary Report ===");
        double sum = 0;
        double highest = Double.MIN_VALUE;
        double lowest = Double.MAX_VALUE;

        for (Student s : students) {
            System.out.println("Name: " + s.name + ", Grade: " + s.grade);
            sum += s.grade;
            if (s.grade > highest) highest = s.grade;
            if (s.grade < lowest) lowest = s.grade;
        }

        double average = sum / students.size();

        System.out.println("\nTotal Students: " + students.size());
        System.out.println("Average Grade: " + String.format("%.2f", average));
        System.out.println("Highest Grade: " + highest);
        System.out.println("Lowest Grade: " + lowest);
    }
}