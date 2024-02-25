public class Term {
        // Term means simply Node
        int coefficient;
        int exponentX;
        int exponentY;
        int exponentZ;
        Term next;

        public Term(int coefficient, int exponentX, int exponentY, int exponentZ) {
            this.coefficient = coefficient;
            this.exponentX = exponentX;
            this.exponentY = exponentY;
            this.exponentZ = exponentZ;
            this.next = null;
        }
}
