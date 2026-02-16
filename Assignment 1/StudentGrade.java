import java.util.Scanner;

class Student {

    private String name;
    private int rollNo;
    private int[] marks;

    public Student(String name, int rollNo) {
        this.name = name;
        this.rollNo = rollNo;
        marks = new int[5];
    }

    public void setMarks(Scanner sc) {
        for (int i = 0; i < 5; i++) {
            marks[i] = sc.nextInt();
        }
    }

    public double average() {
        int sum = 0;
        for (int i = 0; i < 5; i++) {
            sum = sum + marks[i];
        }
        return sum / 5.0;
    }

    public char grade() {
        if (average() >= 75)
            return 'A';
        else if (average() >= 60)
            return 'B';
        else
            return 'C';
    }

    public void show() {
        System.out.println("Name: " + name);
        System.out.println("Roll No: " + rollNo);
        System.out.println("Average: " + average());
        System.out.println("Grade: " + grade());
    }
}

public class StudentGrade {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Name:");
        String name = sc.next();

        System.out.println("Enter Roll Number:");
        int roll = sc.nextInt();

        Student s = new Student(name, roll);

        System.out.println("Enter 5 marks:");
        s.setMarks(sc);

        s.show();

        sc.close();
    }
}