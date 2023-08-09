import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;

class Student {
    String name;
    String USN;
    int IA1;
    int IA2;
    int IA3;
    int sumOfBestTwoIAs;
    int CTA;
    int CIE;

    public Student(String name, String USN, int IA1, int IA2, int IA3, int sumOfBestTwoIAs, int CTA, int CIE) {
        this.name = name;
        this.USN = USN;
        this.IA1 = IA1;
        this.IA2 = IA2;
        this.IA3 = IA3;
        this.sumOfBestTwoIAs = sumOfBestTwoIAs;
        this.CTA = CTA;
        this.CIE = CIE;
    }

    public int getTotalMarks() {
        return sumOfBestTwoIAs + CTA + CIE;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", USN: " + USN + ", Total Marks: " + getTotalMarks();
    }
}

public class StudentDataAnalyzer {

    private static final int MAX_STUDENTS = 100; // Maximum number of students, adjust as needed

    public static void main(String[] args) {
        Student[] students = new Student[MAX_STUDENTS];
        int studentCount = 0;

        try (BufferedReader br = new BufferedReader(new FileReader("data.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 8 && studentCount < MAX_STUDENTS) {
                    String name = data[0];
                    String USN = data[1];
                    int IA1 = Integer.parseInt(data[2]);
                    int IA2 = Integer.parseInt(data[3]);
                    int IA3 = Integer.parseInt(data[4]);
                    int sumOfBestTwoIAs = Integer.parseInt(data[5]);
                    int CTA = Integer.parseInt(data[6]);
                    int CIE = Integer.parseInt(data[7]);

                    students[studentCount++] = new Student(name, USN, IA1, IA2, IA3, sumOfBestTwoIAs, CTA, CIE);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        showMenu(students, studentCount);
    }

    private static void showMenu(Student[] students, int studentCount) {
        System.out.println("Choose an option:");
        System.out.println("1. Find Topper based on Sum of Best Two IAs");
        System.out.println("2. Find Topper based on CTA");
        System.out.println("3. Find Topper based on CIE");
        System.out.println("4. Find Topper based on Total Marks");
        System.out.println("5. Exit");

        java.util.Scanner scanner = new java.util.Scanner(System.in);
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                findTopper(students, studentCount, Comparator.comparingInt(s -> s.sumOfBestTwoIAs));
                break;
            case 2:
                findTopper(students, studentCount, Comparator.comparingInt(s -> s.CTA));
                break;
            case 3:
                findTopper(students, studentCount, Comparator.comparingInt(s -> s.CIE));
                break;
            case 4:
                findTopper(students, studentCount, Comparator.comparingInt(Student::getTotalMarks));
                break;
            case 5:
                System.out.println("Exiting the program.");
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }

        scanner.close();
    }

    private static void findTopper(Student[] students, int studentCount, Comparator<Student> comparator) {
        Student topper = null;
        for (int i = 0; i < studentCount; i++) {
            if (topper == null || comparator.compare(students[i], topper) > 0) {
                topper = students[i];
            }
        }

        if (topper != null) {
            System.out.println("Topper:");
            System.out.println(topper);
        } else {
            System.out.println("No students found in the list.");
        }
    }
}
