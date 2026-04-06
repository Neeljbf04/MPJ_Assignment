import java.util.Scanner;

// Abstract class
abstract class Shape {
    abstract void calculateArea();
}

// Square
class Square extends Shape {
    int side;
    Square(int side) { this.side = side; }

    void calculateArea() {
        System.out.println("Area of Square: " + (side * side));
    }
}

// Rectangle
class Rectangle extends Shape {
    int length, breadth;
    Rectangle(int l, int b) {
        length = l;
        breadth = b;
    }

    void calculateArea() {
        System.out.println("Area of Rectangle: " + (length * breadth));
    }
}

// Circle
class Circle extends Shape {
    double radius;
    Circle(double r) { radius = r; }

    void calculateArea() {
        System.out.println("Area of Circle: " + (Math.PI * radius * radius));
    }
}

// Hillstations
class Hillstations {
    void famousfor() { System.out.println("Famous for beauty"); }
    void famousfood() { System.out.println("Famous for local food"); }
}

class Manali extends Hillstations {
    void famousfor() { System.out.println("Snow & Adventure"); }
    void famousfood() { System.out.println("Siddu"); }
}

class Mussoorie extends Hillstations {
    void famousfor() { System.out.println("Hill Views"); }
    void famousfood() { System.out.println("Momos"); }
}

class Gulmarg extends Hillstations {
    void famousfor() { System.out.println("Skiing"); }
    void famousfood() { System.out.println("Rogan Josh"); }
}

public class Assignment3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Shapes Area\n2. Hillstations Info\n3. Exit");
            int ch = sc.nextInt();

            switch (ch) {
                case 1:
                    System.out.println("1.Square 2.Rectangle 3.Circle");
                    int opt = sc.nextInt();

                    Shape s = null;

                    switch (opt) {
                        case 1:
                            System.out.print("Enter side: ");
                            s = new Square(sc.nextInt());
                            break;
                        case 2:
                            System.out.print("Enter length & breadth: ");
                            s = new Rectangle(sc.nextInt(), sc.nextInt());
                            break;
                        case 3:
                            System.out.print("Enter radius: ");
                            s = new Circle(sc.nextDouble());
                            break;
                    }
                    s.calculateArea();
                    break;

                case 2:
                    Hillstations hs;

                    System.out.println("1.Manali 2.Mussoorie 3.Gulmarg");
                    int h = sc.nextInt();

                    if (h == 1) hs = new Manali();
                    else if (h == 2) hs = new Mussoorie();
                    else hs = new Gulmarg();

                    hs.famousfor();
                    hs.famousfood();
                    break;

                case 3:
                    System.exit(0);
            }
        }
    }
}