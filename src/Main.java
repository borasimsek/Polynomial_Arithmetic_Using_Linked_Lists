import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class Term {
    int coefficient;
    int exponentX;
    int exponentY;
    int exponentZ;
    Term next;

    public Term(int coefficient, int exponentX, int exponentY, int exponentZ) {
        this.coefficient = coefficient;
        this.exponentX = exponentX;
        this.exponentY = exponentY;
        this.exponentZ = exponentZ;
        this.next = null;
    }
}

class Polynomial {
    Term head;

    public Polynomial() {
        this.head = null;
    }

    public void addTerm(int coefficient, int exponentX, int exponentY, int exponentZ) {
        Term newTerm = new Term(coefficient, exponentX, exponentY, exponentZ);
        if (head == null) {
            head = newTerm;
        } else {
            Term current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newTerm;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        try {
            File inputFile = new File("resources/input.txt");
            Scanner scanner = new Scanner(inputFile);
            FileWriter outputFile = new FileWriter("resources/output.txt");

            int numTestCases = scanner.nextInt();
            scanner.nextLine(); // consume newline

            for (int i = 0; i < numTestCases; i++) {
                char operator = scanner.next().charAt(0);
                String polynomial1 = scanner.next();
                String polynomial2 = scanner.next();

                Polynomial result = processPolynomials(operator, polynomial1, polynomial2);

                displayPolynomial(result); // Display the result in the console
                outputResult(outputFile, result); // Write the result to output.txt
            }

            scanner.close();
            outputFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Polynomial processPolynomials(char operator, String poly1, String poly2) {
        Polynomial result = new Polynomial();
        // Operator e göre işlem yapıcam
        System.out.println("Operator: " + operator);
        System.out.println("Polynomial 1: " + poly1);
        System.out.println("Polynomial 2: " + poly2);
        return result;
    }

    public static void outputResult(FileWriter outputFile, Polynomial result) throws IOException {
        // Write the polynomial to the output file

    }

    public static void displayPolynomial(Polynomial polynomial) {
        Term current = polynomial.head;
        while (current != null) {
            System.out.print(current.coefficient);
            if (current.exponentX != 0) {
                System.out.print("x^" + current.exponentX);
            }
            if (current.exponentY != 0) {
                System.out.print("y^" + current.exponentY);
            }
            if (current.exponentZ != 0) {
                System.out.print("z^" + current.exponentZ);
            }
            if (current.next != null) {
                System.out.print(" + ");
            }
            current = current.next;
        }
        System.out.println();
    }
}
