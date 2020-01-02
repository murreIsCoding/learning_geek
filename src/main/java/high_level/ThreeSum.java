package high_level;

import java.util.*;

/**
 * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
 * <p>
 * 注意：答案中不可以包含重复的三元组。
 * <p>
 * 例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 * <p>
 * 满足要求的三元组集合为：
 * [
 * [-1, 0, 1],
 * [-1, -1, 2]
 * ]
 */
public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        //排序
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            int v1 = nums[i];
            if (v1 > 0) {
                break;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = v1 + nums[left] + nums[right];
                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;
                } else if (sum > 0) {
                    right--;
                }
            }
        }
        return result;
    }

    /**
     * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。
     * 找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
     * <p>
     * 例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.
     * <p>
     * 与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).
     */

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int close = nums[0]+nums[1]+nums[2];
        for (int i = 0; i < nums.length - 2; i++) {
            int v1 = nums[i];
            int left = i + 1;
            int right = nums.length - 1;
            int lastSum = target;
            while (left < right) {
                int sum = v1 + nums[left] + nums[right];
                if (sum == target) {
                    return target;
                }

                close = Math.abs(target-close)> Math.abs(target-sum)?sum:close;
                if (lingjiedian(lastSum, sum,target)) {
                    break;
                }else {
                    lastSum=sum;
                }
                if (sum < target) {
                    left++;
                } else if (sum > target) {
                    right--;
                }
            }
        }
        return close;
    }

    public boolean lingjiedian(int lastSum, int sum,int target) {
        if ((lastSum > target && sum < target) || (lastSum < target && sum > target)) {
            return true;
        }else {
            return false;
        }
    }


    public static void main(String[] args) {
            int[] sum ={-1,0,1,1,55};
            System.out.println(new ThreeSum().threeSumClosest(sum,3));
    }
}
