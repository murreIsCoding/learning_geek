package labuladong.binarysearch;

/**
 * 最基础的二分查找
 * 注意边界问题,搜索区间全部以闭区间的概念来理解[0,N]
 */
public class BasicBinarySearch {
    public static void main(String[] args) {
        BasicBinarySearch binarySearch = new BasicBinarySearch();
//        int[] nums = {1, 2, 3, 4, 5, 6, 9, 10, 100, 123};
//        int index = binarySearch.binarySearch(nums, 9);
        int[] nums = {1, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 4, 5};
//        int index = binarySearch.binarySearch_left_bound1(nums, 3);
        int index = binarySearch.binarySearch_right_bound2(nums, 3);

        System.out.println(index);
    }

    /**
     * 最普通的二分查找
     *
     * @param nums
     * @param target
     * @return
     */
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

    /**
     * 二分查找左边界,比较常见的写法,左闭合右开[0,N)
     * 现在变成找到不符合
     *
     * @param nums
     * @param target
     * @return
     */
    int binarySearch_left_bound1(int[] nums, int target) {
        int left = 0;
        int right = nums.length;
        //终止条件比如是[2,2),就不包含任何数了,所以不需要<=
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (target < nums[mid]) {//目标小于mid,则[left,mid)里面搜索
                right = mid;
            } else if (target > nums[mid]) {//目标大于mid,则在[mid+1,right]搜索
                left = mid + 1;
            } else if (nums[mid] == target) {//如果中间就是目标值,收缩右边界
                right = mid;
            }
        }
        //终止条件是[2,2),收紧右侧边界以锁定左侧边界,不用+1
        if (nums[left] == target) {
            return left;
        } else {
            return -1;
        }
    }

    /**
     * 二分查找左边界(两端闭区间写法)
     * [1,2,3,4,5] 3
     * 这里我们的终结条件是left==right+1;
     * 即上一次满足条件时区间内没有==target的值
     * right+1的值时有可能是满足条件的(不一定)
     */
    int binarySearch_left_bound2(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        //因为是闭区间,所以必须left>right才不符合条件,否则会错过一部分
        while (left <= right) {

            //这里是一个小优化,降低溢出的概率
            int mid = left + (right - left) / 2;
            if (target < nums[mid]) {//当目标小于中间值时,即mid不是,那么我们直接从闭合区间[LEFT,mid-1]找
                //为什么是-1,因为我们的搜索区间是闭区间
                right = mid - 1;
            } else if (target > nums[mid]) {//当目标大于中间值时,即mid不是,那么我们直接从闭合区间[mid+1,right]找
                left = mid + 1;
            } else if (nums[mid] == target) {//当中间就是目标时,收缩右边界[left,mid-1]
                right = mid - 1;
            }
        }
        //先判断有没有数组越界
        //终止条件是[3,2],left = right + 1,所以left就是左边界了
        if (left >= nums.length || nums[left] != target) {
            return -1;
        } else {
            return left;
        }
    }


    /**
     * 二分查找右边界,比较常见的写法,左闭合右开[0,N)
     * 现在变成找到不符合
     *
     * @param nums
     * @param target
     * @return
     */
    int binarySearch_right_bound1(int[] nums, int target) {
        int left = 0;
        int right = nums.length;
        //终止条件比如是[2,2)
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (target < nums[mid]) {//目标小于mid,则[left,mid)里面搜索
                right = mid;
            } else if (target > nums[mid]) {//目标大于mid,则在[mid+1,right]搜索
                left = mid + 1;
            } else if (nums[mid] == target) {//如果中间就是目标值,收缩左边界,主要,因为左边界是闭区间,所以
                left = mid + 1;
            }
        }
        //由于终止条件是left==right,但是此时的left和right是[2,2),
        //又因为收紧左侧边界时必须 left = mid + 1
        //所以最后无论返回 left 还是 right，必须减一
        return (nums[right - 1] == target) ? right - 1 : -1;
    }


    /**
     * 二分查找右边界,使用左右全闭合区间来[0,N]实现
     *
     * @param nums
     * @param target
     * @return
     */
    int binarySearch_right_bound2(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        //终止条件比如是[3,2]
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {//搜索[mid+1,right]
                left = mid + 1;
            } else if (nums[mid] > target) {//搜索[left,mid-1]
                right = mid - 1;
            } else if (nums[mid] == target) {//找到相关值了,但是不够,收缩左边界
                left = mid + 1;
            }
        }
        //由于right==left-1,那么直接返回right就行了
        //先检查合法性
        return (right < 0 || nums[right] != target) ? -1 : right;
    }


}
