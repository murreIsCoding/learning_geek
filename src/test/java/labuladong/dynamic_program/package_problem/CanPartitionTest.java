package labuladong.dynamic_program.package_problem;

import org.junit.Assert;
import org.junit.Test;

public class CanPartitionTest {

    CanPartition canPartition = new CanPartition();

    /**
     * 示例 1:
     * 输入: [1, 5, 11, 5]
     * 输出: true
     * 解释: 数组可以分割成 [1, 5, 5] 和 [11].
     *  
     * 示例 2:
     * 输入: [1, 2, 3, 5]
     * 输出: false
     * 解释: 数组不能分割成两个元素和相等的子集.
     */
    @Test
    public void canPartition() {
        int[] nums = {1, 5, 11, 5};
        boolean res = canPartition.canPartition(nums);
        Assert.assertEquals(true, res);
    }

    @Test
    public void canPartition2() {
        int[] nums = {1, 2, 3, 5};
        boolean res = canPartition.canPartition(nums);
        Assert.assertEquals(false, res);
    }

    @Test
    public void canPartition_ds_1() {
        int[] nums = {1, 5, 11, 5};
        boolean res = canPartition.canPartitionStateCompression(nums);
        Assert.assertEquals(true, res);
    }

    @Test
    public void canPartition_ds_2() {
        int[] nums = {1, 2, 3, 5};
        boolean res = canPartition.canPartitionStateCompression(nums);
        Assert.assertEquals(false, res);
    }

    @Test
    public void canPartition_ds_leetcode() {
        int[] nums = {1, 2, 5};
        boolean res = canPartition.canPartitionStateCompression(nums);
        Assert.assertEquals(false, res);
    }
}
