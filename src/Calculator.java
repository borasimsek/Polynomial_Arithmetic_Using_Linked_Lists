public class Calculator {
    /*
        The purpose of this class is to hold calculation methods for two polynomials.
        week1 : reading input file and writing to output.txt
        week2 : Addition operator
        week3 : Subtraction operator
        week4 : Multiplication operator
     */
    static Polynomial addTwoPoly(Polynomial polyReal1, Polynomial polyReal2) {
        /*
          Takes two polynomial as an input and add them up by comparing their exponents. If all of them matches,
          coef. of the term will be added. It will analyze term by term.
         */
        Polynomial result = new Polynomial();
        Term current1 = polyReal1.getHead();
        Term current2 = polyReal2.getHead();
        while (current1 != null && current2 != null) {
            if (current1.getExponentX() == current2.getExponentX() && current1.getExponentY() == current2.getExponentY() && current1.getExponentZ() == current2.getExponentZ()) {
                int sumCoefficient = current1.getCoefficient() + current2.getCoefficient();
                if (sumCoefficient != 0) {
                    result.addTerm(new Term(sumCoefficient, current1.getExponentX(), current1.getExponentY(), current1.getExponentZ()));
                }
                current1 = current1.next;
                current2 = current2.next;
            } else if (isTerm1Greater(current1, current2)) {
                result.addTerm(new Term(current1.getCoefficient(), current1.getExponentX(), current1.getExponentY(), current1.getExponentZ()));
                current1 = current1.next;
            } else {
                result.addTerm(new Term(current2.getCoefficient(), current2.getExponentX(), current2.getExponentY(), current2.getExponentZ()));
                current2 = current2.next;
            }
        }

        while (current1 != null) {
            result.addTerm(new Term(current1.getCoefficient(), current1.getExponentX(), current1.getExponentY(), current1.getExponentZ()));
            current1 = current1.next;
        }

        while (current2 != null) {
            result.addTerm(new Term(current2.coefficient, current2.exponentX, current2.exponentY, current2.exponentZ));
            current2 = current2.next;
        }

        return result;
    }

    static  Polynomial subtractTwoPoly(Polynomial polyReal1 , Polynomial polyReal2){
        /*
            It is similar to addition of two polynomials. Only difference is we use - sign for current2.coef. part.
         */
        Polynomial result = new Polynomial();
        Term current1, current2;

        current1 = polyReal1.getHead();
        current2 = polyReal2.getHead();

        while (current1 != null && current2 != null) {
            if (current1.getExponentX() == current2.getExponentX() && current1.getExponentY() == current2.getExponentY() && current1.getExponentZ() == current2.getExponentZ()) {
                int subtract_Coefficient = current1.getCoefficient() - current2.getCoefficient();
                if (subtract_Coefficient != 0) {
                    result.addTerm(new Term(subtract_Coefficient, current1.getExponentX(), current1.getExponentY(), current1.getExponentZ()));
                }
                current1 = current1.next;
                current2 = current2.next;
            } else if (isTerm1Greater(current1, current2)) {
                result.addTerm(new Term(current1.getCoefficient(), current1.getExponentX(), current1.getExponentY(), current1.getExponentZ()));
                current1 = current1.next;
            } else {
                result.addTerm(new Term(-current2.getCoefficient(), current2.getExponentX(), current2.getExponentY(), current2.getExponentZ()));
                current2 = current2.next;
            }
        }
        while (current1 != null) {
            result.addTerm(new Term(current1.getCoefficient(), current1.getExponentX(), current1.getExponentY(), current1.getExponentZ()));
            current1 = current1.next;
        }
        while (current2 != null) {
            result.addTerm(new Term(-current2.coefficient, current2.exponentX, current2.exponentY, current2.exponentZ));
            current2 = current2.next;
        }
        return result;
    }
    static Polynomial multiplyTwoPoly(Polynomial polyReal1 , Polynomial polyReal2){
        Polynomial result = new Polynomial();
        Term current1 = polyReal1.getHead();
        Term current2 = polyReal2.getHead();
        Polynomial simplified_result = new Polynomial();


        while (current1 != null){
            while (current2 != null){
                int new_coef = current1.coefficient * current2.coefficient;
                int deg_x = current1.exponentX + current2.exponentX;
                int deg_y = current1.exponentY + current2.exponentY;
                int deg_z = current1.exponentZ + current2.exponentZ;
                Term result_term = new Term(new_coef,deg_x,deg_y,deg_z);
                result.addTerm(result_term);
                current2 = current2.next;
            }
            current1 = current1.next;
            current2 = polyReal2.getHead();
        }
        simplified_result = pretty_poly(result);

        return simplified_result;
    }
    static Polynomial pretty_poly(Polynomial result){
        Polynomial simplified_result = new Polynomial();
        Term current = result.getHead();

        while (current != null){
            Term compare = current.next;
            Term prev = current;
            while (compare != null){
                if(isCoefEqual(current, compare)){
                    current.coefficient += compare.coefficient;
                    prev.next = compare.next;
                } else {
                    prev = compare;
                }
                compare = compare.next;
            }
            if (current.coefficient != 0) {
                simplified_result.addTerm(new Term(current.coefficient, current.exponentX, current.exponentY, current.exponentZ));
            }
            current = current.next;
        }
        return simplified_result;
    }
    static boolean isCoefEqual(Term term1, Term term2) {
        return term1.exponentX == term2.exponentX && term1.exponentY == term2.exponentY && term1.exponentZ == term2.exponentZ;
    }
    static boolean isTerm1Greater(Term term1, Term term2) {
        /*
          The aim of this method is to compare two terms' exponents.
         */
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
            return false;
        }
    }
}