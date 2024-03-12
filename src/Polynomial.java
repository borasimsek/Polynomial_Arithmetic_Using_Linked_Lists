public class Polynomial {
    /*
    Note that polynomials consists of terms !
     */
    private Term head;
    public Polynomial() {

        this.head = null;
    }
    void addTerm(Term newTerm) {
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
    static Polynomial processPolynomials(char operator, String poly1, String poly2) {
        Polynomial result = new Polynomial();
        System.out.println("Operator: " + operator);
        System.out.println("Polynomial 1: " + poly1);
        Polynomial poly_real1 = strToLinkedList(poly1);
        displayPolynomial(poly_real1);
        System.out.println("Polynomial 2: " + poly2);
        Polynomial poly_real2 = strToLinkedList(poly2);
        displayPolynomial(poly_real2);
        switch (operator) {
            case '+' -> {
                System.out.println("Adding method will be done");
                result = Calculator.addTwoPoly(poly_real1,poly_real2);
                displayPolynomial(result);
            }
            case '-' -> {
                System.out.println("Subtracting method will be done");
                result = Calculator.subtractTwoPoly(poly_real1,poly_real2);
                displayPolynomial(result);
            }
            case '*' -> {
                System.out.println("Multiplication method will be done");
                result = Calculator.multiplyTwoPoly(poly_real1,poly_real2);
                displayPolynomial(result);
            }
            default -> System.out.println("IDK other methods bro");
        }
        System.out.println("--------------------------------------------------");
        return result;
    }
    private static Polynomial strToLinkedList(String poly) {
        Polynomial polynomial = new Polynomial();
        String[] terms = poly.split("(?=[+-])");
        for (String termStr : terms) {
            if (!termStr.isEmpty()) {
                Term term = processTerm(termStr);
                polynomial.addTerm(term);
            }
        }
        return polynomial;
    }
    private static Term processTerm(String termStr) {
        int coefficient = parseCoefficient(termStr);
        int exponentX = 0;
        int exponentY = 0;
        int exponentZ = 0;
        int indexOfX = termStr.indexOf('x');
        int indexOfY = termStr.indexOf('y');
        int indexOfZ = termStr.indexOf('z');

        if (indexOfX != -1) {
            int exponentIndex = indexOfX + 1;
            if (exponentIndex < termStr.length() && Character.isDigit(termStr.charAt(exponentIndex))) {
                while (exponentIndex < termStr.length() && Character.isDigit(termStr.charAt(exponentIndex))) {
                    exponentX = exponentX * 10 + (termStr.charAt(exponentIndex) - '0');
                    exponentIndex++;
                }
            } else {
                exponentX = 1;
            }
        } else {
            exponentX = 0;
        }
        if (indexOfY != -1) {
            int exponentIndex = indexOfY + 1;
            if (exponentIndex < termStr.length() && Character.isDigit(termStr.charAt(exponentIndex))) {
                while (exponentIndex < termStr.length() && Character.isDigit(termStr.charAt(exponentIndex))) {
                    exponentY = exponentY * 10 + (termStr.charAt(exponentIndex) - '0');
                    exponentIndex++;
                }
            } else {
                exponentY = 1;
            }
        } else {
            exponentY = 0;
        }
        if (indexOfZ != -1) {
            int exponentIndex = indexOfZ + 1;
            if (exponentIndex < termStr.length() && Character.isDigit(termStr.charAt(exponentIndex))) {
                while (exponentIndex < termStr.length() && Character.isDigit(termStr.charAt(exponentIndex))) {
                    exponentZ = exponentZ * 10 + (termStr.charAt(exponentIndex) - '0');
                    exponentIndex++;
                }
            } else {
                exponentZ = 1;
            }
        } else {
            exponentZ = 0;
        }
        return new Term(coefficient, exponentX, exponentY, exponentZ);
    }
    private static int parseCoefficient(String termStr) {
        boolean negative = false;
        int startIndex = 0;
        if (termStr.charAt(0) == '-') {
            negative = true;
            startIndex = 1;
        } else if (termStr.charAt(0) == '+') {
            startIndex = 1;
        }
        int coefficient = 0;
        boolean hasCoefficient = false;
        for (int i = startIndex; i < termStr.length(); i++) {
            char c = termStr.charAt(i);
            if (Character.isDigit(c)) {
                coefficient = coefficient * 10 + (c - '0');
                hasCoefficient = true;
            } else {
                break;
            }
        }
        if (!hasCoefficient) {
            coefficient = 1;
        }

        return negative ? -coefficient : coefficient;
    }
    private static void displayPolynomial(Polynomial polynomial) {
        Term current = polynomial.head;
        while (current != null) {
            System.out.print(current.coefficient+ " " + current.exponentX + " "
                    + current.exponentY + " " + current.exponentZ + " ");
            current = current.next;
        }
        System.out.println();
    }

    public Term getHead() {
        return head;
    }
}