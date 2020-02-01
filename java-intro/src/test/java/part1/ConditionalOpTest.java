package part1;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static part1.ConditionalOp.*;

public class ConditionalOpTest {

    @Test
    public void compareEquals() {
        String res = compareSumOfSquaresAndSquareOfSum(1, 0);
        assertEquals("The sum of squares equals the square of sum.", res);
    }

    @Test
    public void compareThenSquareOfSumIsGreater() {
        String res = compareSumOfSquaresAndSquareOfSum(1, 1);
        assertEquals("The square of sum is greater.", res);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetPremiumInvalidArguments() {
        getPremium(-1, 0);
    }

    @Test
    public void testGetPremiumWithLowExp() {
        double res = getPremium(2, 50000);
        assertEquals((double) 1000, res, 0.01);
    }

    @Test
    public void testGetPremiumWithHighExp() {
        double res = getPremium(5, 50000);
        assertEquals((double) 2500, res, 0.01);
    }

    @Test
    public void testWhenNoPremium() {
        double res = getPremium(0, 15000);
        assertEquals((double) 0, res, 0.01);
    }

    @Test
    public void testGetPointsDistancesWhenSecondIsFarther() {
        String res = isFartherPoint(0,0, 1, 3);
        assertEquals("The second point is located farther.", res);
    }

    @Test
    public void testGetFartherPointEquals() {
        String res = isFartherPoint(-3,-1, 1, 3);
        assertEquals("Points have equal distances.", res);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testDefineRectangularTriangleWithInvalidSide() {
        isRectangularTriangle(0, 1, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRectangularTriangleWithInvalidSides() {
        isRectangularTriangle(3, 3, 10);
    }

    @Test
    public void testIsRectangularTriangle() {
        boolean res = isRectangularTriangle(3, 4, 5);
        assertEquals(true, res);
    }

    @Test
    public void testNotRectangularTriangle() {
        boolean res = isRectangularTriangle(2, 4, 4);
        assertEquals(false, res);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getSeasonByIncorrectMonth() {
        getSeasonByMonth(13);
    }

    @Test
    public void getSeasonByCorrectMonth() {
        String res = getSeasonByMonth(4);
        assertEquals("Spring", res);
    }

    @Test
    public void getSquaresOfThree() {
        List<Integer> input = new ArrayList<>();
        input.add(0);
        input.add(-1);
        input.add(2);
        List<Integer> res = squaresOfPositive(input);
        input.set(2, 4);
        assertEquals(input, res);
    }

}
