package part1;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static part1.LoopOpParam.*;

public class LoopOpParamTest {

    @Test(expected = IllegalArgumentException.class)
    public void testAscendingNegative() {
        part3_1(0,0);
    }

    @Test
    public void testAscendingPositive() {
        List res = part3_1(1, 3);
        List<Integer> output = new ArrayList();
        output.add(1);
        output.add(2);
        output.add(3);
        assertEquals(3, res.size());
        assertEquals(output, res);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDescendingNegative() {
        LoopOpParam.part3_2(14,0);
    }

    @Test
    public void testDescendingPositive() {
        List res = LoopOpParam.part3_2(1, 3);
        List<Integer> output = new ArrayList();
        output.add(2);
        assertEquals(1, res.size());
        assertEquals(output, res);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPowerOfNumberNegative() {
        part3_3(2, -1);
    }

    @Test
    public void testPowerOfNumberPositive() {
        int res = part3_3(2, 7);
        assertEquals(128, res);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetAllPowersWithIncorrectArgument() {
        part3_4(8, -1);
    }

    @Test
    public void testGetAllPowersFrom1ToN() {
        List res = part3_4(2, 3);
        List<Integer> output = new ArrayList();
        output.add(2);
        output.add(4);
        output.add(8);
        assertEquals(3, res.size());
        assertEquals(output, res);
    }
}
