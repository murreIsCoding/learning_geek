package labuladong.dynamic_program.practice;

import org.junit.Assert;
import org.junit.Test;

public class ClimbStairsTest {
    ClimbStairs c = new ClimbStairs();

    @Test
    public void climbStairs() {
        int[] cost = {10, 15, 20};
        int res = c.climbStairs(cost);
        System.out.println(res);
        Assert.assertEquals(15, res);
    }

    @Test
    public void climbStairs2() {
        int[] cost = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
        int res = c.climbStairs(cost);
        System.out.println(res);
        Assert.assertEquals(6, res);
    }


    @Test
    public void climbStairs_compress1() {
        int[] cost = {10, 15, 20};
        int res = c.climbStairs_compress(cost);
        System.out.println(res);
        Assert.assertEquals(15, res);
    }

    @Test
    public void climbStairs_compress2() {
        int[] cost = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
        int res = c.climbStairs_compress(cost);
        System.out.println(res);
        Assert.assertEquals(6, res);
    }
}
