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
            scanner.nextLine();
            for (int i = 0; i < numTestCases; i++) {
                char operator = scanner.next().charAt(0);
                String polynomial1 = scanner.next();
                String polynomial2 = scanner.next();
                Polynomial result = Polynomial.processPolynomials(operator, polynomial1, polynomial2);
                outputResult(outputFile, result);
            }
            scanner.close();
            outputFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void outputResult(FileWriter outputFile, Polynomial result) throws IOException {
        /**
         *
            * @param outputFile : output file
         *                   result : result of the operation
         *                      This method writes the result of the operation to the output file.
         *                      It will analyze term by term and write them to the file.
         *                      If the coefficient is negative, it will write - sign.
         *                      If the coefficient is positive, it will write + sign.
         *                      If the coefficient is 0, it will not write anything.
         *                      If the exponent is greater than 1, it will write exponent.
         *                      If the exponent is 0, it will not write x,y or z.
         *                      If the exponent is 1, it will not write exponent.
         *                      If the exponent is greater than 1, it will write exponent.
         *                      It will write the result to the file.
         *
         */
        Term current = result.getHead();
        boolean firstTerm = true;
        while (current != null) {
            StringBuilder termStringBuilder = new StringBuilder();
            if (current.getCoefficient() < 0) {
                termStringBuilder.append("-");
            } else if (!firstTerm) {
                termStringBuilder.append("+");
            }
            termStringBuilder.append(Math.abs(current.getCoefficient()));
            if (current.getExponentX() != 0) {
                termStringBuilder.append("x").append(current.getExponentX() > 1 ? current.getExponentX() : "");
            }if (current.getExponentY() != 0) {
                termStringBuilder.append("y").append(current.getExponentY() > 1 ? current.getExponentY() : "");
            }if (current.getExponentZ() != 0) {
                termStringBuilder.append("z").append(current.getExponentZ() > 1 ? current.getExponentZ() : "");
            }if (!firstTerm && current.getCoefficient() >= 0) {
                outputFile.write("");
            } else {
                firstTerm = false;
            }
            outputFile.write(termStringBuilder.toString());
            current = current.next;
        }
        outputFile.write("\n");
    }
}