import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;

//-------------------------------------------------------------------------
/**
 *  Test class for Collinear.java
 *
 *  @author  
 *  @version 29/09/14 13:22:29
 */
@RunWith(JUnit4.class)
public class CollinearTest
{
    //~ Constructor ........................................................
    @Test
    public void testConstructor()
    {
      new Collinear();
    }

    //~ Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Check that the two methods work for empty arrays
     */
    @Test
    public void testEmpty()
    {
        int expectedResult = 0;

        assertEquals("countCollinear failed with 3 empty arrays",       expectedResult, Collinear.countCollinear(new int[0], new int[0], new int[0]));
        assertEquals("countCollinearSorted failed with 3 empty arrays", expectedResult, Collinear.countCollinearSorted(new int[0], new int[0], new int[0]));
    }

    // ----------------------------------------------------------
    /**
     * Check for no false positives in a single-element array
     */
    @Test
    public void testSingleFalse()
    {
        int[] a3 = { 15 };
        int[] a2 = { 5 };
        int[] a1 = { 10 };

        int expectedResult = 0;

        assertEquals("countCollinear({10}, {5}, {15})",       expectedResult, Collinear.countCollinear(a1, a2, a3) );
        assertEquals("countCollinearSorted({10}, {5}, {15})", expectedResult, Collinear.countCollinearSorted(a1, a2, a3) );
    }

    // ----------------------------------------------------------
    /**
     * Check for no false positives in a single-element array
     */
    @Test
    public void testSingleTrue()
    {
        int[] a3 = { 15, 5 };       int[] a2 = { 5 };       int[] a1 = { 10, 15, 5 };
        int[] a3sorted = { 5, 15 }; int[] a2sorted = { 5 }; int[] a1sorted = { 5, 10, 15 };

        int expectedResult = 1;

        assertEquals("countCollinear(" + Arrays.toString(a1) + "," + Arrays.toString(a2) + "," + Arrays.toString(a3) + ")",                         expectedResult, Collinear.countCollinear(a1, a2, a3));
        assertEquals("countCollinearSorted(" + Arrays.toString(a1sorted) + "," + Arrays.toString(a2sorted) + "," + Arrays.toString(a3sorted) + ")", expectedResult, Collinear.countCollinearSorted(a1sorted, a2sorted, a3sorted));
    }
	@Test
    public void testBinarySearchTrue(){
    	int [] b = {2,45,3,7,26,8,9, 90, 102 ,54, 95, 4 ,7,22, 35 ,200};
    	int x = 45;
    	boolean expectedResult = true;
    	assertEquals("binarySearch(" + Arrays.toString(b) + ")", expectedResult, Collinear.binarySearch(b , x));
    	
    	
    }
    // TODO: add more tests here. Each line of code and each decision in Collinear.java should
    // be executed at least once from at least one test.
    @Test
    public void testBinarySearchFalse(){
	int x = 3;
    	int [] c = {2,50,91,6,7,35,1 ,0 ,9 ,20, 43, 57 ,90 ,100};	
	boolean expectedresult = false;
	
    	assertEquals("binarySearch(" + Arrays.toString(c) + ")", expectedResult, Collinear.binarySearch(c, x));
    	
    }


}
