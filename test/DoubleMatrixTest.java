import java.util.Scanner;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author msamatar0
 */
public class DoubleMatrixTest{
    public DoubleMatrixTest(){}
    
    /**
     *
    
    Test 3 test Determinant and Sum
    Used for thoroughly * testing Determinant

    testDet[ testDataSet ][ 0 ] Square Matrix to test

    testDet[ testDataSet ][ 1 ][ 0 ][ 0 ] is Determinant Expected Result

    testDet[ testDataSet ][ 2 ][ 0 ][ 0 ] is the Matrix sum

    Note on Exceptions testDet[ testDataSet ][ 1 ][ 0 ][ 0 ] Points

    to matrixOpExceptionIndex e.getMessage() string. You need to check for

    the correct exceptions on non square matrix
     *
     */
    
    @Test
    public void testDeterminant(){
        System.out.println("Determinant");
        DoubleMatrix dm = new DoubleMatrix();
        for(int i = 0; i < testDetSum.length; ++i)
            assertEquals("Test #" + i + " ", testDetSum[i][1][0][0], dm.determinant(testDetSum[i][0]));
    }
    
    @Test
    public void testSum(){
        System.out.println("Sum");
        DoubleMatrix dm = new DoubleMatrix();
        for(int i = 0; i < testDetSum.length; ++i)
            assertEquals("Test #" + i, testDetSum[i][2][0][0], dm.sumMatrix(testDetSum[i][0]), .01);
    }
    
    final Double[][][][] testDetSum = {
        /* test 1by1 first - 1by1 matrix, determinant */
        {{{3.0}}, {{3.0}}, {{3.0}}}, /* set 0 */
        {{{-5.0}}, {{-5.0}}, {{-5.0}}}, /* det is 1by1 content*/
        /* first touroughly test 2by2 

        do not even consider a 3by3 until a 2by2 is working */
        {{{1.0, 2.0}, /* Set:2 1*2 - 2*2 = -2 */
        {2.0, 2.0}} /*2by2*/, {{-2.0}} /*det result */, {{7.0}}},
        {{{2.0, 3.0}, {4.0, 5.0}}, {{-2.0}}, {{14.0}}},
        {{{3.0, -2.0}, /*set:49--8*/
        {4.0, 3.0}}, {{17.0}}, {{8.0}}},
        {{{2.5, 1.2}, /*set 5 2.5*(-1.3) - 1.2*2.3 = -6.01  */
        {2.3, -1.3}}, {{-6.01}}, {{4.7}}},
        //from http://ncalculators.com/matrix/matrix-determinant-calculator.htm

        {{{2.0, 5.0}, {2.0, 3.0}}, {{-4.0}}, {{12.0}}}, /**
         * set:6 solution -4
         */
        {{{-23.0, 5.23}, {2.22, 3.0}}, {{-80.6106}}, {{-12.55}}},
        {{{-23.0, -5.5}, {100.0, -3.0}}, {{619.0}}, {{68.5}}},
        /**
         * test 3by3 *
         * http://www.wikihow.com/Find-the-Determinant-of-a-3X3-Matrix
         *
         * Site above is using first row reference test set 9
         *
         * 1 [(4x2)-&(7x6)] = -34, -5[ (2x2)-(7*4) ] = 120, * 3 [ (2*6) - (4*4)
         * ] = -12; -34 + 120 -12 = 74' *
         */
        {{{1.0, 5.0, 3.0},
        {2.0, 4.0, 7.0},
        {4.0, 6.0, 2.0}}, {{74.0}}, {{34.0}}},
        {{{1.0, 4.0, 7.0}, /**
         * set 10
         */
        {2.0, 5.0, 8.0},
        {3.0, 6.0, 9.0}}, {{0.0}}, {{45.0}}},
        {{{1.0, -5.0, 3.0},
        {2.0, 4.0, 7.0},
        {4.0, 6.0, -2.0}}, {{-222.0}}, {{20.0}}},
        {{{1.0, 2.0, 3.0}, /**
         * set 12
         */
        {1.0, 1.0, 1.0},
        {2.0, 2.0, 2.0}}, {{0.0}}, {{15.0}}},
        {{{1.0, -5.0, 3.0},
        {2.0, 4.0, 7.0},
        {4.0, 6.0, 2.0}}, {{-166.0}}, {{24.0}}},
        //http://ncalculators.com/matrix/matrix-determinant-calculator.htm

        //first row reference

        {{{3.0, 5.0, 2.0}, /**
         * set 14
         */
        {2.0, 3.0, 4.0},
        {2.0, 2.0, 4.0},}, {{8.0}}, {{27.0}}},
        {{{2.0, 5.0, 2.0},
        {2.0, 3.0, 4.0},
        {2.0, 2.0, 4.0}}, {{4.0}}, {{26.0}}},
        /**
         * http://matrix.reshish.com/detCalculation.php & *
         * http://www.wolframalpha.com/widgets/view.jsp?id=7fcb0a2c0f0f41d9f4454ac2d8ed7ad6
         */
        {{{2.0, 4.0, -1.0}, {-3.0, 6.0, -2.0}, {4.0, 0.0, 5.0}}, {{112.0}}, {{15.0}}},
        {{{2.0, -3.0, -1.0}, {-3.0, 6.0, -2.0}, {4.0, -3.0, 5.0}}, {{42.0}}, {{5.0}}},
        /**
         * 3by3 matrices for the 2nd 4 by 4 matrix test set 18-20
         */
        {{{2.0, 0.0, -2.0}, {0.0, 6.0, -3.0}, {0.0, 2.0, 0.0}}, {{12.0}}, {{5.0}}},
        {{{1.0, 2.0, -2.0}, {4.0, 0.0, -3.0}, {5.0, 0.0, 0.0}}, {{-30.0}}, {{7.0}}},
        {{{1.0, 2.0, 0.0}, {4.0, 0.0, 6.0}, {5.0, 0.0, 2.0}}, {{44.0}}, {{20.0}}},
        /**
         * test 4by4 matrix
         */
        //http://ncalculators.com/matrix/4x4-matrix-determinant-calculator.htm

        /* http://matrix.reshish.com/determinant.php 

         http://www.wolframalpha.com/widgets/view.jsp?id=afd9b731195bb348e9014bf72973e022 */
        {{{2.0, 3.0, 4.0, 5.0}, /**
         * set 21
         */
        {6.0, 7.0, 8.0, 9.0},
        {10.0, 11.0, 12.0, 13.0},
        {14.0, 15.0, 16.0, 17.0}}, {{0.0}}, {{152.0}}},
        /*http://nebula.deanza.edu/~bloom/math43/Determinant4x4Matrix.pdf 

         shows work using row reference one should create 3by3 test set 18-20*/
        {{{3.0, 0.0, 2.0, -1.0},
        {1.0, 2.0, 0.0, -2.0},
        {4.0, 0.0, 6.0, -3.0},
        {5.0, 0.0, 2.0, 0.0}}, {{20.0}}, {{19.0}}},
        {{{2.0, 2.0, 4.0, 5.0},
        {2.5, 3.5, -2.5, 3.5},
        {6.0, 6.0, 7.0, 7.0},
        {8.0, 0.0, 11.0, 12.0}}, {{-740.0}}, {{77.0}}},
        /* test 5by5 http://matrix.reshish.com/determinant.php */
        {{{2.0, 2.0, 4.0, 5.0, -2.5}, /* set 24 */
        {2.5, 3.5, -2.5, 3.5, -1.0},
        {6.0, 6.0, -7.0, 7.0, 9.0},
        {8.0, 0.0, 11.0, 12.0, 3.0},
        {1.0, 3.0, 4.5, 5.0, -2.0}}, {{385.0}}, {{83.0}}},
        {{{2.5, 2.5, 4.0, 5.0, -2.5}, /**
         * not Verified
         */
        {2.5, 3.5, -3.5, 3.5, -1.0},
        {6.0, 6.0, -7.0, 4.0, 9.0},
        {8.0, 0.0, 11.0, 16.0, -3.0},
        {1.0, 3.0, -24.5, 5.0, -2.0}}, {{-7941.0}}, {{49.0}}},
        /**
         * test 6 by 6
         */
        {{{2.0, 2.0, 4.0, 5.0, -2.5, 0.0}, /**
         * not Verified
         */
        {2.5, 3.5, -2.5, 3.5, -1.0, 22.0},
        {6.0, 6.0, -7.0, 7.0, 9.0, 33.0},
        {8.0, 0.0, 11.0, 12.0, 3.0, -4.5},
        {1.0, 3.0, 4.5, 5.0, -2.0, 3.65},
        {2.0, 3.0, 4.0, 5.0, 6.0, 7.0}}, {{-11239.0}}, {{164.15}}},
        /**
         * test 7by7
         */
        {{
        {2.0, 2.0, 4.0, 5.0, -2.5, 1.0, 22.0}, /**
         * test set: 27 not Verified
         */
        {2.5, 3.5, -2.5, 4.5, -1.0, 22.0, -10.0},
        {6.0, 6.0, -7.0, 8.0, 9.0, 32.0, 23.0},
        {8.0, 0.0, 11.0, 12.0, 3.0, -4.25, 1.5},
        {1.0, 3.0, 4.5, 5.0, -2.0, 3.25, 4.0},
        {2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 5.6},
        {2.0, 4.5, 3.25, 6.5, -10.0, 0.5, 2.0},}, {{890222.7875000006}}, {{220.85}} },
    
       /* test nonSquare 2by 3 matrix throws exception matrixOpExceptionIndex[ 4 ] */
         {{{2.0, 0.0, -2.0}, {0.0, 6.0, -3.0}, }, {{2.0}}, {{3.0}}},
    
    }; //end TestData3 testDetSum
    
      /**
         * 
Test 4 Data for TreeMap


         * 

         * test data used for testing TreeMap Utilities: get, put, remove,
         * treeInfo, treeKeys data mimics, submit input format,
         * GenericMatrixComparator and createMatrix must be working. You can
         * write one TreeMapUtility jUnit test, You have two data sets, notice
         * comma, you also have two solutions but form matrixNames you overlap
         * the first 3 for both 1st and 2nd data set. For example
         * dataPutGetRemove[0] matches... treekey[0], treeInfo[0], 1st three
         * entries in matrixNames, expPutGetRemove[0], expPutGetRemove[1],
         * expPutGetRemove[2] the 2nd data set dataPutGetRemove[1] matches all
         * entries in matrixNames first three matrices are repeated in 2nd set.


         *
         */
        
        @Test
        public void testTreeMap(){
            System.out.println("TreeMap");
            DoubleMatrix dm = new DoubleMatrix();
            for(int i = 0; i < dataPutGetRemove.length; ++i){
                Scanner in = new Scanner(dataPutGetRemove[i]);
                dm.processIO(in, null);
                assertEquals(treeKeys[i], dm.processIO(new Scanner("TreeMap Keys"), null).trim());
                assertEquals(treeInfo[i], dm.processIO(new Scanner("TreeMap Info"), null).trim());
            }
            for(int i = 0; i < matrixNames.length; ++i)
                assertArrayEquals(expPutGetRemove[i], dm.get(matrixNames[i]));
        }
        
        final String[] dataPutGetRemove = {
            " Matrix Create $x 2 2 2.0 3.0 4.0 5.0\n"
            + " Matrix Create $abc 3 2 2.0 3.0 4.0 2.0 3.0 5.0\n"
            + " Matrix Create $tuv 2 2 2.0 3.0 4.0 5.0\n", /*end of first data set*/
            " Matrix Create $x 2 2 2.0 3.0 4.0 5.0\n"
            + " Matrix Create $abc 3 2 2.0 3.0 4.0 2.0 3.0 5.0\n"
            + " Matrix Create $tuv 2 2 2.0 3.0 4.0 5.0\n"
            + " Matrix Create $x1 2 2 1.0 3.0 2.0 4.0\n"
            + " Matrix Create $y 2 2 2.0 2.0 -2.0 -2.0\n"
            + " Matrix Create $z 2 1 1.0 1.0\n", /* end of 2nd data set */};
        /* Nota Bene 2nd data set just adds 3 more Matrices */

        final String[] matrixNames = {"$x", "$abc", "$tuv", "$x1", "$y", "$z"};
        /* first data Set uses first 3 names; second all names */
        final String[] treeKeys = {"Matrix Keys: [$x, $abc, $tuv]",
            "Matrix Keys: [$x, $y, $z, $x1, $abc, $tuv]"};
        /* first array element is treeKeys for first data set */
        final String[] treeInfo = {"Matrix Info:\n\t$x 2 2\n\t$abc 3 2\n\t$tuv 2 2",
            "Matrix Info:\n\t$x 2 2\n\t$y 2 2\n\t$z 2 1\n\t$x1 2 2\n\t$abc 3 2\n\t$tuv 2 2"};

        final Double[][][] expPutGetRemove = {
            {{2.0, 3.0}, {4.0, 5.0}}, /*$x data */
            {{2.0, 3.0}, {4.0, 2.0}, {3.0, 5.0}}, /* $abc data */
            {{2.0, 3.0}, {4.0, 5.0}}, /* $tuv */
            {{1.0, 3.0}, {2.0, 4.0}}, /* $x1 */
            {{2.0, 2.0}, {-2.0, -2.0}}, /* $y */
            {{1.0}, {1.0}} /* $z data */
        }; //end TestData 3 set TreeMap    
}
