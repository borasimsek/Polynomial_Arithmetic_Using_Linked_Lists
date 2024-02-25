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

    private static int parseCoefficient(String termStr) {
        // İlk karakter artı (+) veya eksi (-) işareti mi kontrol ediyoruz
        boolean negative = false;
        int startIndex = 0;
        if (termStr.charAt(0) == '-') {
            negative = true;
            startIndex = 1;
        } else if (termStr.charAt(0) == '+') {
            startIndex = 1;
        }

        // Başlangıç indeksinden itibaren katsayıyı alma
        int coefficient = 0;
        for (int i = startIndex; i < termStr.length(); i++) {
            char c = termStr.charAt(i);
            if (Character.isDigit(c)) {
                coefficient = coefficient * 10 + (c - '0');
            } else {
                break;
            }
        }

        return negative ? -coefficient : coefficient;
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
        switch (operator) {
            case '+':
                System.out.println("Adding method will be done");
                result = polynomialAddition(poly_real1,poly_real2);
                displayPolynomial(result);
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
        System.out.println("-----------------------");

        return result;
    }

    private static Polynomial polynomialAddition(Polynomial polyReal1, Polynomial polyReal2) {
        Polynomial result = new Polynomial();

        Term current1 = polyReal1.head;
        Term current2 = polyReal2.head;

        while (current1 != null && current2 != null) {
            if (current1.exponentX == current2.exponentX && current1.exponentY == current2.exponentY && current1.exponentZ == current2.exponentZ) {
                int sumCoefficient = current1.coefficient + current2.coefficient;
                if (sumCoefficient != 0) {
                    result.addTerm(new Term(sumCoefficient, current1.exponentX, current1.exponentY, current1.exponentZ));
                }
                current1 = current1.next;
                current2 = current2.next;
            } else if (isTerm1Greater(current1, current2)) {
                result.addTerm(new Term(current1.coefficient, current1.exponentX, current1.exponentY, current1.exponentZ));
                current1 = current1.next;
            } else {
                result.addTerm(new Term(current2.coefficient, current2.exponentX, current2.exponentY, current2.exponentZ));
                current2 = current2.next;
            }
        }

        while (current1 != null) {
            result.addTerm(new Term(current1.coefficient, current1.exponentX, current1.exponentY, current1.exponentZ));
            current1 = current1.next;
        }

        while (current2 != null) {
            result.addTerm(new Term(current2.coefficient, current2.exponentX, current2.exponentY, current2.exponentZ));
            current2 = current2.next;
        }

        return result;
    }
    private static boolean isTerm1Greater(Term term1, Term term2) {
        if (term1.exponentX > term2.exponentX) {
            return true;
        } else if (term1.exponentX < term2.exponentX) {
            return false;
        } else if (term1.exponentY > term2.exponentY) {
            return true;
        } else if (term1.exponentY < term2.exponentY) {
            return false;
        } else if (term1.exponentZ > term2.exponentZ) {
            return true;
        } else if (term1.exponentZ < term2.exponentZ) {
            return false;
        } else {
            return false; // Eşitse de term1 büyük değildir.
        }
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
