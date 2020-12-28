package labuladong.binarysearch;

/**
 * 二分查找  两边都是闭区间的解法 模板 [left,right]
 */
public class BinarySearchDemo {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 5, 5, 5, 5, 6, 7, 8, 100};
        int target = 5;
        int result = binarySearch(nums, target);
        System.out.println(result);
        int result2 = binarySearch_left_bound(nums, target);
        System.out.println(result2);
        int result3 = binarySearch_right_bound(nums, target);
        System.out.println(result3);
    }

    /**
     * 基本的二分查找
     *
     * @param nums
     * @param target
     * @return
     */
    static int binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        //终止条件,如[3,2]
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target == nums[mid]) {
                return mid;
            } else if (target < nums[mid]) {//[left,mid-1]
                right = mid - 1;
            } else if (target > nums[mid]) {//[mid+1,right]
                left = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 左边界
     *
     * @param nums
     * @param target
     * @return
     */
    static int binarySearch_left_bound(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        //终止条件,如[3,2]
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target == nums[mid]) {//不要直接返回,收紧右边界
                right = mid - 1;
            } else if (target < nums[mid]) {//[left,mid-1]
                right = mid - 1;
            } else if (target > nums[mid]) {//[mid+1,right]
                left = mid + 1;
            }
        }
        //检查数组越界以及是否相等
        return (left >= nums.length || nums[left] != target) ? -1 : left;
    }

    /**
     * 右边界
     *
     * @param nums
     * @param target
     * @return
     */
    static int binarySearch_right_bound(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        //终止条件,如[3,2]
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target == nums[mid]) {//不要直接返回,收紧左
                left = mid + 1;
            } else if (target < nums[mid]) {//[left,mid-1]
                right = mid - 1;
            } else if (target > nums[mid]) {//[mid+1,right]
                left = mid + 1;
            }
        }
        //检查数组越界以及是否相等
        return (right < 0 || nums[right] != target) ? -1 : right;
    }
}
