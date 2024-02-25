public class Polynomial {
    private Term head;

    public Polynomial() {
        this.head = null;
    }

    public void addTerm(Term newTerm) {
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

    public static Polynomial processPolynomials(char operator, String poly1, String poly2) {
        Polynomial result = new Polynomial();
        System.out.println("Operator: " + operator);
        System.out.println("Polynomial 1: " + poly1);
        Polynomial poly_real1 = strToLinkedList(poly1);
        displayPolynomial(poly_real1);

        System.out.println("Polynomial 2: " + poly2);
        Polynomial poly_real2 = strToLinkedList(poly2);
        displayPolynomial(poly_real2);
        System.out.println("-----------------------");

        switch (operator) {
            case '+':
                System.out.println("Adding method will be done");
                break;
            case '-':
                System.out.println("Subtracting method will be done");
                break;
            case '*':
                System.out.println("Multiplication method will be done");
                break;
            default:
                System.out.println("IDK other methods bro");

        }

        return result;
    }

    private static Polynomial strToLinkedList(String poly) {
        Polynomial polynomial = new Polynomial();
        String[] terms = poly.split("[+-]");

        for (String termStr : terms) {
            if (!termStr.isEmpty()) {
                Term term = processTerm(termStr);
                polynomial.addTerm(term);
            }
        }

        return polynomial;
    }

    private static Term processTerm(String termStr) {
        int coefficient = 0;
        int exponentX = 0;
        int exponentY = 0;
        int exponentZ = 0;

        // Katsayıyı ve üs değerlerini bulmak için işlem yapacağız
        int indexOfX = termStr.indexOf('x');
        int indexOfY = termStr.indexOf('y');
        int indexOfZ = termStr.indexOf('z');

        // Eğer x varsa, onun üssünü alalım
        if (indexOfX != -1) {
            int exponentIndex = indexOfX + 1; // 'x' karakterinden sonraki ilk karakterin indexi
            while (exponentIndex < termStr.length() && Character.isDigit(termStr.charAt(exponentIndex))) {
                exponentX = exponentX * 10 + (termStr.charAt(exponentIndex) - '0');
                exponentIndex++;
            }
        }

        // Eğer y varsa, onun üssünü alalım
        if (indexOfY != -1) {
            int exponentIndex = indexOfY + 1; // 'y' karakterinden sonraki ilk karakterin indexi
            while (exponentIndex < termStr.length() && Character.isDigit(termStr.charAt(exponentIndex))) {
                exponentY = exponentY * 10 + (termStr.charAt(exponentIndex) - '0');
                exponentIndex++;
            }
        }

        if (indexOfZ != -1) {
            int exponentIndex = indexOfZ + 1; // 'z' karakterinden sonraki ilk karakterin indexi
            while (exponentIndex < termStr.length() && Character.isDigit(termStr.charAt(exponentIndex))) {
                exponentZ = exponentZ * 10 + (termStr.charAt(exponentIndex) - '0');
                exponentIndex++;
            }
        }

        // Katsayıyı alalım, eğer x, y veya z yoksa
        if (indexOfX == -1 && indexOfY == -1 && indexOfZ == -1) {
            coefficient = Integer.parseInt(termStr);
        } else { // Eğer x, y veya z varsa
            if (indexOfX > 0) { // Eğer x terimStr'in başındaysa
                coefficient = Integer.parseInt(termStr.substring(0, indexOfX));
            } else if (indexOfY > 0) { // Eğer y terimStr'in başındaysa
                coefficient = Integer.parseInt(termStr.substring(0, indexOfY));
            } else if (indexOfZ > 0) { // Eğer z terimStr'in başındaysa
                coefficient = Integer.parseInt(termStr.substring(0, indexOfZ));
            }
        }

        return new Term(coefficient, exponentX, exponentY, exponentZ);
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
}
