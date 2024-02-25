import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            File inputFile = new File("resources/input2.txt");
            Scanner scanner = new Scanner(inputFile);
            FileWriter outputFile = new FileWriter("resources/output.txt");

            int numTestCases = scanner.nextInt();
            scanner.nextLine(); // consume newline

            for (int i = 0; i < numTestCases; i++) {
                char operator = scanner.next().charAt(0);
                String polynomial1 = scanner.next();
                String polynomial2 = scanner.next();
                Polynomial result = Polynomial.processPolynomials(operator, polynomial1, polynomial2);
                outputResult_v2(outputFile, result); // Write the result to output.txt
            }

            scanner.close();
            outputFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void outputResult(FileWriter outputFile, Polynomial result) throws IOException {
        Term current = result.head;

        while (current != null) {
            // Her bir terimi dosyaya yaz
            outputFile.write(current.coefficient + "x" + current.exponentX
                    + "y" + current.exponentY + "z" + current.exponentZ + " ");
            current = current.next;
        }

        // Dosyayı kapat
        outputFile.close();
    }

    public static void outputResult_v2(FileWriter outputFile, Polynomial result) throws IOException {
        Term current = result.head;
        boolean firstTerm = true;
        while (current != null) {
            StringBuilder termStringBuilder = new StringBuilder();
            if (current.coefficient < 0) {
                termStringBuilder.append("-");
            } else if (!firstTerm) {
                termStringBuilder.append("+");
            }
            termStringBuilder.append(Math.abs(current.coefficient));
            if (current.exponentX != 0) {
                termStringBuilder.append(current.exponentX > 1 ? current.exponentX : "").append("x");
            }
            if (current.exponentY != 0) {
                termStringBuilder.append(current.exponentY > 1 ? current.exponentY : "").append("y");
            }
            if (current.exponentZ != 0) {
                termStringBuilder.append(current.exponentZ > 1 ? current.exponentZ : "").append("z");
            }
            if (!firstTerm && current.coefficient >= 0) {
                outputFile.write("");
            } else {
                firstTerm = false; // İlk terim artık yazıldı
            }
            outputFile.write(termStringBuilder.toString());
            current = current.next;
        }
        outputFile.close();
    }
}
