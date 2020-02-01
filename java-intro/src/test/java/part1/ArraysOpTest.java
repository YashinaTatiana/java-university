package part1;

import com.tngtech.java.junit.dataprovider.*;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static part1.ArraysOp.*;

@RunWith(DataProviderRunner.class)
public class ArraysOpTest {

    @Test(expected = IllegalArgumentException.class)
    public void testEvenNumbersNegative() {
        getEvenNumbers(null);
    }

    @Test
    public void testEvenNumbersPositive() {
        List<Integer> input = new ArrayList<>();
        input.add(1);
        input.add(2);
        input.add(6);
        input.add(113);
        List res = getEvenNumbers(input);
        input.remove(0);
        input.remove(2);
        assertEquals(input, res);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOddNumbersNegative() {
        getEvenNumbers(null);
    }

    @Test
    public void testOddNumbersPositive() {
        List<Integer> input = new ArrayList<>();
        input.add(1);
        input.add(2);
        input.add(6);
        input.add(113);
        List res = getOddNumbers(input);
        input.remove(1);
        input.remove(1);
        assertEquals(input, res);
    }

    @Test
    public void testGetDividedBy5Or10() {
        List<Integer> input = new ArrayList<>();
        input.add(5);
        input.add(3);
        input.add(100);
        input.add(234);
        List res = dividedBy5Or10(input);
        input.remove(1);
        input.remove(2);
        assertEquals(input, res);
    }

    @Test
    public void testGetDividedBy3Or9() {
        List<Integer> input = new ArrayList<>();
        input.add(5);
        input.add(3);
        input.add(100);
        input.add(21);
        List res = dividedBy3Or9(input);
        input.remove(0);
        input.remove(1);
        assertEquals(input, res);
    }

    @Test
    public void testFindPrimeNumbers() {
        List<Integer> input = new ArrayList<>();
        input.add(5);
        input.add(3);
        input.add(100);
        input.add(21);
        List res = findPrimeElements(input);
        input.remove(2);
        input.remove(2);
        assertEquals(input, res);
    }

    @Test
    public void testFindGcdLcmInArray() {
        List<Integer> input = new ArrayList<>();
        input.add(2);
        input.add(4);
        input.add(100);
        assertEquals(2, findGcdInArray(input));
        assertEquals(100, findLcmInArray(input));
    }

    @Test
    public void testFindHappyNumbers() {
        List<Integer> input = new ArrayList<>();
        input.add(13);
        input.add(19);
        input.add(134);
        input.add(25);
        List res = happyNumbers(input);
        input.remove(2);
        input.remove(2);
        assertEquals(input, res);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWrittenDigitNegative() {
        printDigit(11);
    }

    @DataProvider
    public static Object[][] printDigitData() {
        return new Object[][]{
                {9, "Nine"},
                {0, "Zero"},
                {6, "Six"}
        };
    }

    @Test
    @UseDataProvider("printDigitData")
    public void testWrittenDigit(int digit, String writtenDigit) {
        String res = ArraysOp.printDigit(digit);
        assertEquals(writtenDigit, res);
    }

}
