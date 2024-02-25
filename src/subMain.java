import java.util.Scanner;

public class subMain {
    static class Term {
        int coefficient;
        int exponentX;
        int exponentY;
        int exponentZ;
        Term next; // next referansı eklendi

        public Term(int coefficient, int exponentX, int exponentY, int exponentZ) {
            this.coefficient = coefficient;
            this.exponentX = exponentX;
            this.exponentY = exponentY;
            this.exponentZ = exponentZ;
            this.next = null; // Başlangıçta next referansını null yapalım
        }
    }

    static class Polynomial {
        Term head;

        public void addTerm(Term term) {
            if (head == null) {
                head = term;
            } else {
                Term current = head;
                while (current.next != null) {
                    current = current.next;
                }
                current.next = term;
            }
        }
    }

    public static void main(String[] args) {
        String polyString = "3x2yz-y+z+7";
        Polynomial result = processPolynomial(polyString);
        displayPolynomial(result);
    }

    static Polynomial processPolynomial(String polyString) {
        Polynomial polynomial = new Polynomial();
        Scanner scanner = new Scanner(polyString);
        scanner.useDelimiter("[+-]");

        while (scanner.hasNext()) {
            String termStr = scanner.next();
            Term term = processTerm(termStr);
            polynomial.addTerm(term);
        }

        scanner.close();
        return polynomial;
    }

    static Term processTerm(String termStr) {
        int coefficient = 1;
        int exponentX = 0;
        int exponentY = 0;
        int exponentZ = 0;

        // Term stringini işle ve katsayıyı, x, y ve z üslerini al
        Scanner termScanner = new Scanner(termStr);
        while (termScanner.hasNext()) {
            String token = termScanner.next();
            if (token.matches("^[0-9]+$")) {
                coefficient = Integer.parseInt(token);
            } else if (token.contains("x")) {
                exponentX = extractExponent(token);
            } else if (token.contains("y")) {
                exponentY = extractExponent(token);
            } else if (token.contains("z")) {
                exponentZ = extractExponent(token);
            }
        }
        termScanner.close();

        return new Term(coefficient, exponentX, exponentY, exponentZ);
    }

    static int extractExponent(String token) {
        // Eğer x, y veya z'nin üssü belirtilmemişse varsayılan olarak 1 yap
        if (token.length() == 1) {
            return 1;
        } else {
            return Integer.parseInt(token.substring(1));
        }
    }

    static void displayPolynomial(Polynomial polynomial) {
        Term current = polynomial.head;
        while (current != null) {
            System.out.println(current.coefficient + " " + current.exponentX + " " + current.exponentY + " " + current.exponentZ);
            current = current.next;
        }
    }
}
