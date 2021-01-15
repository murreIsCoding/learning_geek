package labuladong.dynamic_program.practice;

import org.junit.Assert;
import org.junit.Test;

public class OneAndZeroTest {
    OneAndZero oneAndZero = new OneAndZero();

    @Test
    public void findMaxForm() {
        String[] nums = {"10", "0001", "111001", "1", "0"};
        int m = 5;
        int n = 3;
        int res = oneAndZero.findMaxForm(nums, m, n);
        Assert.assertEquals(4, res);
    }

    @Test
    public void findMaxForm2() {
        String[] nums = {"10", "0", "1"};
        int m = 1;
        int n = 1;
        int res = oneAndZero.findMaxForm(nums, m, n);
        Assert.assertEquals(2, res);
    }

    @Test
    public void findMaxForm_leetcode() {
        String[] nums = {"10", "0", "1"};
        int m = 1;
        int n = 1;
        int res = oneAndZero.findMaxForm_leetcode(nums, m, n);
        Assert.assertEquals(2, res);
    }
}
