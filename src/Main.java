import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

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
                Polynomial result = Polynomial.processPolynomials(operator, polynomial1, polynomial2);
                outputResult(outputFile, result); // Write the result to output.txt
            }

            scanner.close();
            outputFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void outputResult(FileWriter outputFile, Polynomial result) throws IOException {
        // I will write the results line by line to output.txt

    }

}
