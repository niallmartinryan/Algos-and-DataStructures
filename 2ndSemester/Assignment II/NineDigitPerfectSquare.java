import java.util.Arrays;

/**
 * CS2010 (Hilary Term) - Assignment 1
 *
 * Nine Digit Perfect Square
 *
 * A natural number, p, is a perfect square if for some natural number k, k^2=p.
 * For example, 16 is a perfect square, as 4^2=16. The number 20 is not a
 * perfect square as there is no natural number k such that k^2=20.
 *
 * Problem: Develop an algorithm that will find all nine-digit perfect squares
 * that use all nine digits exactly once. For example, 139,854,276 is a solution
 * (the first) as 11,826^2=139,854,276.
 *
 * 1) Fill in the implementation of the methods described in this file.
 *
 * 2) Test your implementation by developing suitable test suite in the
 * NineDigitPerfectSquareTest JUnit test case.
 *
 * @author:
 *
 */

public class NineDigitPerfectSquare {

    /**
     * A method to return an array containing all squeres between low and high
     *
     * @param low: lowest perfect square
     * @param high: largest perfect square
     *
     * @return an array containing all the perfect squares between low and high
     */
    public int[] perfectSquaresBetween(int low, int high){
	
       return null;
    }

    public int countNineDigitPerfectSquares(){
        return getNineDigitPerfectSquares().length;
    }

    /**
     * A method to determine if the number specified in parameter "number"
     * contains all 9 digits exactly once.
     *
     * @param number
     *            : A number to be tested
     * @return whether the number contains all 9 digits exactly once
     */
    public boolean containsAllDigitsOnce(int number) {

      return false;
    }


    /**
     * A method to return an array containing all the squares discovered
     *
     * @return an array containing all of the perfect squares which
     * contain all digits 1..9 exactly once.
     */
    public int[] getNineDigitPerfectSquares() {
        return null;

    }
    int floor_sqrt(double x){
	int odd, s, r;    
	odd= 1;
	s = 1;
	r = 0;
	while ( s <= x ){
		r = r + 1;
		odd= odd+ 2;
		s = s + odd;
	}
	return r;
	} // floor_sqrt
    public int main(String [] args){
    	System.out.println(floor_sqrt(60));
    	System.out.println("finished");
    
    
    
    
    }
}
