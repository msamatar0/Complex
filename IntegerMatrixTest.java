/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author msamatar0
 */
public class IntegerMatrixTest {
    
    public IntegerMatrixTest() {
    }

    /**
     * Test of newElement method, of class IntegerMatrix.
     */
//    @Test
//    public void testNewElement() {
//        System.out.println("newElement");
//        String elem = "";
//        IntegerMatrix instance = new IntegerMatrix();
//        Integer expResult = null;
//        Integer result = instance.newElement(elem);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of plus method, of class IntegerMatrix.
     */
//    @Test
//    public void testPlus() {
//        System.out.println("plus");
//        Integer m = null;
//        Integer n = null;
//        IntegerMatrix instance = new IntegerMatrix();
//        Integer expResult = null;
//        Integer result = instance.plus(m, n);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of minus method, of class IntegerMatrix.
     */
//    @Test
//    public void testMinus() {
//        System.out.println("minus");
//        Integer m = null;
//        Integer n = null;
//        IntegerMatrix instance = new IntegerMatrix();
//        Integer expResult = null;
//        Integer result = instance.minus(m, n);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of multiply method, of class IntegerMatrix.
     */
//    @Test
//    public void testMultiply() {
//        System.out.println("multiply");
//        Integer m = null;
//        Integer n = null;
//        IntegerMatrix instance = new IntegerMatrix();
//        Integer expResult = null;
//        Integer result = instance.multiply(m, n);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    /** 
        make sure printResult is working, 
        then add printMatrix();
        test cases is simplified using Integer Matrices
        testPrint[testCase][0]  Matrix M1
        testPrint[testCase][1]  Matrix M2
        testPrint[testCase][2]  Matrix M3 result M1+M2
    
        for testing PrintResult, we are just passing 3 matrices, and an char operator
        you don't have to be in the Integer domain, its an Object[][] being passed.
        remember erasure so you have
        Object[][] result = instance.printResult( testPrint[i][0],
                                                  testPrint[i][1],
                                                  testPrint[i][2], '+' );
        we can wait to later on to test matrix plus
    */
    Integer[][][][] testPrint = {
        
        { { {2} } , /*1 by 1 */
          { {3} } , //format String is "%3s"
          { {5} } ,
        }, {
          { {1, 2} }, /*1 by 2*/
          { {10,5} },
          { {11,7} },
        }, {
           { { 2, 3},{20,-9} },   //format string is "%4s" ( 20 is 2+2)
           { {-2, 2},{-20, 18} },  //format String is "%5s" (-20 is 4+2)
           { { 0, 5,},{0, 9} },  //format String is "%3s", (1 digit + 2 
        }, {
 
           { {200, -203}, { 20,30} },
           { { 20,   13}, { 80,-20} },
           { {220, -200}, { 100, 10} },
        }, {
            { {20, 20}, {10, -10}, {5,6} },
            { { 2, 18}, { 4, 200}, {2,4} },
            { {22, 38}, {14, 190}, {7,10} },
        }, {  /*creating fake + examples to test uneven rows */
            { {1}, {2}, {3} }, /*3by1*/
            { {2, 3, -5 } }, /*1 by 3*/
            { {20} },
        }, {
            { {1, 2} }, //1 by 2
            { {2}, {20}, {30} }, //3 by 1
            { {2, 2}, {3, 45} }, //2by 2
        }
    };
    /** results for test 1
     * 
     * need to find longest toString of each matrix     * 
     * and create a format string of the form %ns where n is 
     * the longest toString element plus 2 more
     */
    String[] testPrintResultExpected = {
        "|  2|  +  |  3|  =  |  5|\n",
        "|  1  2|  +  |  10   5|  =  |  11   7|\n",
        
        "|   2   3|     |   -2    2|     |  0  5|\n" +
        "|  20  -9|  +  |  -20   18|  =  |  0  9|\n",
        
        "|   200  -203|     |   20   13|     |   220  -200|\n" +
        "|    20    30|  +  |   80  -20|  =  |   100    10|\n",
        
        "|   20   20|     |    2   18|     |   22   38|\n" +
        "|   10  -10|  +  |    4  200|  =  |   14  190|\n" +
        "|    5    6|     |    2    4|     |    7   10|\n",
        
        "|  1|     |   2   3  -5|     |  20|\n" +
        "|  2|  +                  =  \n" +
        "|  3|                        \n",
        
        "|  1  2|  +  |   2|  =  |   2   2|\n" +
        "             |  20|     |   3  45|\n" +
        "             |  30|     \n",

    };
    /**
     * test 1 uses first matrix in each testPrint group
     * printMatrix uses same methodology as printResult
     */
    String[] testPrintMatrixExpected = {
        "|  2|\n",     /* {{2}} 1 by 1*/
        "|  1  2|\n", /* { {1, 2} }, 1 by 2*/
        "|   2   3|\n" +  // { { 2, 3},{20,-9} },   
        "|  20  -9|\n",  //format string is "%4s" ( 20 is 2+2)
         
        "|   200  -203|\n" + //format is "%6s//{ {200, -203},
        "|    20    30|\n",  //                  {20, 30} }
        
        "|   20   20|\n" +  // { {20, 20}, {10, -10}, {5,6} },
        "|   10  -10|\n" + //format "%5s"
        "|    5    6|\n",
        
        "|  1|\n|  2|\n|  3|\n", //test 3 by 1 { {1}, {2}, {3} },
        
        "|  1  2|\n" // { {1, 2} }, //1 by 2       
    };
    @Test
    public void testPrint(){
        System.out.println("Print");
        
    }
}
