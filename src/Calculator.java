public class Calculator {
    public static Polynomial polynomialAddition(Polynomial polyReal1, Polynomial polyReal2) {
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

    public static boolean isTerm1Greater(Term term1, Term term2) {
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
}
