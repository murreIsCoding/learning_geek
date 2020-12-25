package labuladong.binarysearch;

public class BasicBinarySearch {
    public static void main(String[] args) {
        BasicBinarySearch binarySearch = new BasicBinarySearch();
        int[] nums = {1, 2, 3, 4, 5, 6, 9, 10, 100, 123};
        int index = binarySearch.binarySearch(nums, 9);
        System.out.println(index);
    }

    int binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (target < nums[mid]) {
                right = mid - 1;
            } else if (target > nums[mid]) {
                left = mid + 1;
            }
        }
        return -1;
    }
}
