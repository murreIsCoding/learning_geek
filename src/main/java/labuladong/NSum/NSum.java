package labuladong.NSum;

import java.util.Arrays;
import java.util.LinkedList;

public class NSum {
    /**
     * 如果假设输入一个数组 nums 和一个目标和 target
     * 请你返回 nums 中能够凑出 target 的两个元素的值
     * 比如输入 nums = [5,3,1,6], target = 9，那么算法返回两个元素 [3,6]。可以假设只有且仅有一对儿元素可以凑出 target。
     * <p>
     * 1.排序
     * 2.左右指针
     * 3.如果目标大于当前值,左指针向右移动;如果目标小于当前值,右指针向左移动
     */
    int[] twoSum(int[] nums, int target) {
        //排序
        Arrays.sort(nums);
        //左右指针
        int i = 0, j = nums.length - 1;
        while (i < j) {
            if (nums[i] + nums[j] == target) {
                return new int[]{nums[i], nums[j]};
            } else if (nums[i] + nums[j] < target) {
                i++;
            } else if (nums[i] + nums[j] > target) {
                j--;
            }
        }
        //如果没有找到,直接返回空
        return new int[]{};
    }

    /**
     * nums 中可能有多对儿元素之和都等于 target，请你的算法返回所有和为 target 的元素对儿，其中不能出现重复。
     * 如{1,1,2,2,3,3} target=4
     * 返回[1,3] [2,2]
     */
    int[][] twoSum_many(int[] nums, int target) {
        //排序
        Arrays.sort(nums);
        //左右指针
        int lo = 0, hi = nums.length - 1;
        LinkedList<int[]> res = new LinkedList<>();
        while (lo < hi) {
            int left = nums[lo];
            int right = nums[hi];
            if (nums[lo] + nums[hi] < target) {
                while (lo < hi && nums[lo] == left) lo++;
            } else if (nums[lo] + nums[hi] > target) {
                while (lo < hi && nums[hi] == right) hi--;
            } else if (nums[lo] + nums[hi] == target) {
                res.addLast(new int[]{left, right});
                // 跳过所有重复的元素
                while (lo < hi && nums[lo] == left) lo++;
                while (lo < hi && nums[hi] == right) hi--;
            }
        }
        //如果没有找到,直接返回空
        return res.toArray(new int[0][0]);
    }

    /**
     * 通用框架
     * 从 nums[start] 开始，计算有序数组nums 中所有和为 target 的二元组
     * 注:nums必须是排好序的
     */
    int[][] twoSumTarget(int[] nums, int start, int target) {
        // 左指针改为从 start 开始，其他不变
        int lo = start, hi = nums.length - 1;
        LinkedList<int[]> res = new LinkedList<>();
        while (lo < hi) {
            int left = nums[lo];
            int right = nums[hi];
            if (nums[lo] + nums[hi] < target) {
                while (lo < hi && nums[lo] == left) lo++;
            } else if (nums[lo] + nums[hi] > target) {
                while (lo < hi && nums[hi] == right) hi--;
            } else if (nums[lo] + nums[hi] == target) {
                res.addLast(new int[]{left, right});
                // 跳过所有重复的元素
                while (lo < hi && nums[lo] == left) lo++;
                while (lo < hi && nums[hi] == right) hi--;
            }
        }
        return res.toArray(new int[0][0]);
    }

    /**
     * 三个加起来等于0,不能重复
     * 给定数组[-1,0,1,2,-1,-4]
     * 返回[-1,0,1][-1,-1,2]
     * 思路 :穷举 先选出一个数,然后用前面的双指针框架选出另外2个数
     * 注意解决重复问题
     */
    int[][] threeSum_many(int[] nums, int start, int target) {
        LinkedList<int[]> res = new LinkedList<>();
        //排序
        Arrays.sort(nums);
        for (int i = start; i < nums.length; i++) {
            int curr = nums[i];
            int[][] two = twoSumTarget(nums, i + 1, target - curr);
            for (int j = 0; j < two.length; j++) {
                res.addLast(new int[]{curr, two[j][0], two[j][1]});
            }
            //跳过重复的
            while (i < nums.length - 1 && nums[i] == nums[i + 1]) {
                i++;
            }
        }
        return res.toArray(new int[0][0]);
    }

    /**
     * 四个加起来等于0
     *
     * @param nums
     * @return
     */
    int[][] fourSum_many(int[] nums, int start, int target) {
        LinkedList<int[]> res = new LinkedList<>();
        //排序
        Arrays.sort(nums);
        for (int i = start; i < nums.length; i++) {
            int curr = nums[i];
            int[][] two = threeSum_many(nums, i + 1, target - curr);
            for (int j = 0; j < two.length; j++) {
                res.addLast(new int[]{curr, two[j][0], two[j][1], two[j][2]});
            }
            //跳过重复的
            while (i < nums.length - 1 && nums[i] == nums[i + 1]) {
                i++;
            }
        }
        return res.toArray(new int[0][0]);
    }

    /**
     * Nsum问题
     * 注意:调用该方法前,需要先排序
     * 思路:如果n>2那么递归,如果n==2直接使用模板
     */
    int[][] nSum_many(int[] nums, int n, int start, int target) {
        LinkedList<int[]> res = new LinkedList<>();
        int length = nums.length;
        // 至少是 2Sum，且数组大小不应该小于 n,只是排除特殊情况
        if (n < 2 || length < n) {
            return res.toArray(new int[0][0]);
        }
        if (n == 2) {
            return twoSumTarget(nums, start, target);
        } else {
            // n > 2 时，递归计算 (n-1)Sum 的结果
            for (int i = start; i < nums.length; i++) {
                int curr = nums[i];
                int[][] arr = nSum_many(nums, n - 1, i + 1, target - curr);
                //结果+当前值 拼成新的数组
                for (int j = 0; j < arr.length; j++) {
                    int[] newArr = new int[n];
                    for (int k = 0; k < n - 1; k++) {
                        newArr[k] = arr[j][k];
                    }
                    newArr[n - 1] = curr;
                    res.addLast(newArr);
                }
                //跳过重复的
                while (i < nums.length - 1 && nums[i] == nums[i + 1]){
                    i++;
                }
            }

        }
        return res.toArray(new int[0][0]);
    }
}
