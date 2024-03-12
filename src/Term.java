public class Term {
    /*
    This class can be seen as our Node. It holds coefficient, and exponents of the term. Single node has exactly 4 integers.
    For example, if our term is 5 ; it should hold extra zeros for exponentX,exponentY, and exponentZ
     */
    protected int coefficient;
    protected int exponentX;
    protected int exponentY;
    protected int exponentZ;
    protected Term next;

    public Term(int coefficient, int exponentX, int exponentY, int exponentZ) {
        this.coefficient = coefficient;
        this.exponentX = exponentX;
        this.exponentY = exponentY;
        this.exponentZ = exponentZ;
        this.next = null;
    }

    public int getCoefficient(){
        return coefficient;
    }

    public int getExponentX() {
        return exponentX;
    }

    public int getExponentY() {
        return exponentY;
    }

    public int getExponentZ() {
        return exponentZ;
    }

    public Term getNext() {
        return next;
    }

}