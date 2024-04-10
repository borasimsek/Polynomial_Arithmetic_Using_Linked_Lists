# CS201_Project1_myself

## Polynomial Arithmetic Using Linked Lists
### Description
In this project, you will implement a program that can execute arithmetic operations on polynomials. The restrictions on polynomial terms are as follows:
•	The polynomial terms can contain at most three variables, namely x, y, and z. 
•	The polynomial terms can not contain just numbers without variables.
•	Coefficient and exponent of the input and output terms should be smaller than 1,000,000,000.
•	Coefficients can be negative, but exponents can not.

The order of the terms in each polynomial is as follows:
•	x has higher precedence than y, and y has higher precendence than z. So, you can have a term printed as xyz but not yxz or zxy.
•	Higher exponent has higher precedence than a lower exponent. So, you can have a polynomial as 5x4+4x3+2x2 (5x4+4x3+2x2) but not 3x2+x4 (3x2+x4). Combined with the first rule, if we have more than one variable, the term with the highest exponent in x will be the first term in the polynomial, then the term with the second highest exponent in x will be the second, etc.

With two polynomials, you should implement addition, subtraction, and multiplication.

## Input and Output
Input file contains n + 1 lines, where the first line contains the number of test cases n. Then the next n lines contain test cases one by one. Each line contains three items separated via space. First item is the operator to be applied. It is either '+', or '-', or '*'. Second and third items are the polynomials on which the operator will be applied.
Sample Input File #1:
3
+ 5x3y2+3x2y4 5x2y4+2x  
- 2xyz+4y 2xyz+4y
* x+y+z x+y+z  
Sample Input File #2:
3
- 5x3y2+3x2y4 5x2y4+2x  
+ 2xyz+4y 2xyz-4y
* x3+3x2y+3xy2+y3 x4+4x3y+6x2y2+4xy3+y4
Sample Input File #3:
1
+ x4y2-3x2y3+2x100y3 -x4y2+3x2y3-2x100y3

Output file contains n lines, where each line corresponds to one test case output. Each line contains the resulting polynomial. If the result is 0, just print 0. Beware the order of terms in printing the polynomial.
Sample Output File #1:
5x3y2+8x2y4+2x
0
x2+2xy+2xz+y2+2yz+z2
Sample Output File #2:
5x3y2-2x2y4-2x
4xyz
x7+7x6y+21x5y2+35x4y3+35x3y4+21x2y5+7xy6+y7
Sample Output File #3:
0
