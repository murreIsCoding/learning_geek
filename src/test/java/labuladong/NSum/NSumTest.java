package labuladong.NSum;

import cn.hutool.core.util.ArrayUtil;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class NSumTest {
    NSum nSum;

    @Before
    public void before() {
        nSum = new NSum();
    }

    @Test
    public void twoSum() {
        int[] nums = {5, 3, 1, 6};
        int target = 9;
        System.out.println(ArrayUtil.toString(nSum.twoSum(nums, target)));
    }

    @Test
    public void twoSum_many() {
        int[] nums = {1, 1, 2, 2, 3, 3};
        int target = 4;
        System.out.println(ArrayUtil.toString(nSum.twoSum_many(nums, target)));
    }

    @Test
    public void threeSum_many() {
        int[] nums = {-1, 0, 1, 2, -1, -4};
//        int[] nums = {1,1,1,1,1};
        int[][] res = nSum.threeSum_many(nums, 0, 0);
        System.out.println(ArrayUtil.toString(res));
    }

    @Test
    public void fourSum_many() {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        int[][] res = nSum.fourSum_many(nums, 0, 0);
        System.out.println(ArrayUtil.toString(res));
    }

    @Test
    public void nSum_many() {
//        Random random = new Random();
//        int k = 5;
//        int n = k * 2;
//        int[] nums = new int[n];
//        for (int i = 0; i < n; i++) {
//            int num = random.nextInt(n) - k;
//            nums[i] = num;
//        }
        int[] nums = {1, 2, 3, 129, 4, 5, 6, 549, 7, 8, 9, 765, 10, 11, 21, 421, 23, 532, 8, 123, 2, 3, 4, 5, 981};

        //先排序
        Arrays.sort(nums);
        System.out.println(ArrayUtil.toString(nums));

        int[][] res = nSum.nSum_many(nums, 7, 0, 3500);

        System.out.println("结果有" + res.length + "个");
        for (int i = 0; i < res.length; i++) {
            int[] curr = res[i];
            Arrays.sort(curr);
            System.out.println("结果" + ArrayUtil.toString(curr));
        }


        testResultNoRepeat(res);
    }

    private void testResultNoRepeat(int[][] res) {
        /**
         * 检查结果是不是完全不同的
         */
        A:
        for (int i = 0; i < res.length; i++) {
            int[] curr = res[i];
            Arrays.sort(curr);
            B:
            for (int j = i + 1; j < res.length; j++) {
                int[] compare = res[j];
                Arrays.sort(compare);
                for (int k = 0; k < curr.length; k++) {
                    if (curr[k] != compare[k]) {
                        continue B;
                    }
                }
                System.out.println("存在一样的结果");
                return;
            }
        }
        System.out.println("不存在一样的结果");
    }
}
