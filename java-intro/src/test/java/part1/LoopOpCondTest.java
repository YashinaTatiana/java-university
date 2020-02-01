package part1;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static part1.LoopOpCond.*;

public class LoopOpCondTest {

    @Test(expected = IllegalArgumentException.class)
    public void testNotDividedBy5AndDividedBy3Negative() {
        notDividedByFiveAndDividedByThree(null);
    }

    @Test
    public void testNotDividedBy5AndDividedBy3Positive() {
        List<Integer> a = new ArrayList<>();
        a.add(6);
        a.add(3);
        a.add(15);
        a.add(36);
        List res = notDividedByFiveAndDividedByThree(a);
        a.remove(2);
        assertEquals(a, res);
    }

    @Test
    public void testDividedByFivePositive() {
        List res = dividedByFive(13);
        List<Integer> output = new ArrayList();
        output.add(5);
        output.add(10);
        assertEquals(output, res);
    }

    @Test
    public void testIsPowerOfTwo() {
        boolean res = isPowerOfTwo(64);
        assertEquals(true, res);
    }

    @Test
    public void testNotPowerOfTwo() {
        boolean res = isPowerOfTwo(61);
        assertEquals(false, res);
    }

    @Test
    public void testFibonacciPositive() {
        List res = fib(5);
        List<Integer> output = new ArrayList();
        output.add(1);
        output.add(1);
        output.add(2);
        output.add(3);
        assertEquals(output, res);
    }

    @Test
    public void testFibonacciEmptyString() {
        List res = fib(-1);
        assertEquals(new ArrayList(), res);
    }

}
