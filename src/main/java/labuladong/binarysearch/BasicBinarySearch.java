package labuladong.binarysearch;

/**
 * 最基础的二分查找
 * 注意边界问题,搜索区间全部以闭区间的概念来理解[0,N]
 */
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
        //因为是闭区间,所以必须left>right才不符合条件,否则会错过一部分
        while (left <= right) {
            //这里是一个小优化,降低溢出的概率
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {//当中间就是目标时直接返回
                return mid;
            } else if (target < nums[mid]) {//当目标小于中间值时,即mid不是,那么我们直接从闭合区间[LEFT,mid-1]找
                //为什么是-1,因为我们的搜索区间是闭区间
                right = mid - 1;
            } else if (target > nums[mid]) {//当目标大于中间值时,即mid不是,那么我们直接从闭合区间[mid+1,right]找
                left = mid + 1;
            }
        }
        //找不到值,直接返回-1
        return -1;
    }
}
