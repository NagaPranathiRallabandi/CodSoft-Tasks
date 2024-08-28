import java.util.*;
public class StudentGradeCalculator {

    String name;
    int maths;
    int physics;
    int chemistry;
    int english;
    int biology;

    int total;
    float avgPercent;

    void input() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your name : ");
        name = sc.nextLine();

        System.out.println("Enter your marks in Mathematics : ");
        maths = sc.nextInt();
        System.out.println("Enter your marks in Physics : ");
        physics = sc.nextInt();
        System.out.println("Enter your marks in Chemistry : ");
        chemistry = sc.nextInt();
        System.out.println("Enter your marks in English : ");
        english = sc.nextInt();
        System.out.println("Enter your marks in Biology : ");
        biology = sc.nextInt();

        sc.close();
    }

    void calc() {
        total = maths + physics + chemistry + english + biology;
        avgPercent = total / 4;
    }

    void output() {
        System.out.println("Hello " + name);
        System.out.println("Your Total marks : " + total);
        System.out.println("The average percent obtained is " + avgPercent);

        if (avgPercent > 75) {
            System.out.println("Grade : A");
        }
        else if ((avgPercent > 60) && (avgPercent < 75)) {
            System.out.println("Grade : B");
        }
        else if ((avgPercent > 50) && (avgPercent < 60)) {
            System.out.println("Grade : C");
        }
        else if ((avgPercent > 35) && (avgPercent < 50)) {
            System.out.println("Grade : D");
        }
        else {
            
        }
    }

    public static void main(String []args) {
        StudentGradeCalculator sg = new StudentGradeCalculator();
        sg.input();
        sg.calc();
        sg.output();
    }
    
}
