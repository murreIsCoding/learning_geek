package labuladong.interval;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

public class Interval {
    public static void main(String[] args) {
        Interval interval = new Interval();
//        int[][] intervals = {{1, 3}, {2, 6}, {9, 100}};
//        System.out.println(interval.removeCoveredIntervals(intervals));
//        System.out.println(interval.removeCoveredIntervals(intervals));
//        int[][] res = interval.merge(intervals);
        int[][] A = {{0, 2}, {5, 10}, {13, 23},{24, 25}};
        int[][] B = {{1, 5}, {8, 12}, {15, 24},{25, 26}};
        int[][] res = interval.intervalIntersection(A,B);
        for (int i = 0; i < res.length; i++) {
            System.out.println(res[i][0] + "-" + res[i][1]);
        }
    }

    /**
     * 删除掉被覆盖的区间
     *
     * @param intvs
     * @return 删除掉被覆盖区间后 还剩下的区间个数
     */
    int removeCoveredIntervals(int[][] intvs) {
        // 按照起点升序排列，起点相同时降序排列
        //注:排序是关键,因为排序过后的情况,使用下面3种方案处理才有意义
        Arrays.sort(intvs, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            }
            return a[0] - b[0];
        });

        // 记录合并区间的起点和终点
        int left = intvs[0][0];
        int right = intvs[0][1];

        int res = 0;
        for (int i = 1; i < intvs.length; i++) {
            int[] intv = intvs[i];
            // 情况一，找到覆盖区间
            if (left <= intv[0] && right >= intv[1]) {
                res++;
            }
            // 情况二，找到相交区间，合并
            if (right >= intv[0] && right <= intv[1]) {
                right = intv[1];
            }
            // 情况三，完全不相交，更新起点和终点
            if (right < intv[0]) {
                left = intv[0];
                right = intv[1];
            }
        }

        return intvs.length - res;
    }

    /**
     * 默写一遍
     *
     * @param intvs
     * @return
     */
    int removeCoveredIntervals_my(int[][] intvs) {
        //排序 很关键
        Arrays.sort(intvs, (o1, o2) -> {
            if (o1[0] != o2[0]) {
                return o1[0] - o2[0];
            }
            return o2[1] - o1[1];
        });
        // 记录合并区间的起点和终点
        int left = intvs[0][0];
        int right = intvs[0][1];

        //结果
        int res = 0;
        for (int i = 1; i < intvs.length; i--) {
            int[] intv = intvs[i];
            //情况1:覆盖
            if (intv[0] >= left && intv[1] <= right) {
                res++;
            }
            //情况2:交集
            else if (intv[0] <= right && intv[1] >= right) {
                right = intv[1];
            }
            //情况3:无交集
            else if (intv[0] > left) {
                left = intv[0];
                right = intv[1];
            }
        }
        //剩下区间数 = 总区间数 - 被覆盖区间数
        return intvs.length - res;
    }

    /**
     * 合并区间
     */
    // intervals 形如 [[1,3],[2,6]...]
    int[][] merge(int[][] intervals) {
        //排序
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        LinkedList<int[]> res = new LinkedList<>();
        res.add(intervals[0]);
        //对比然后一步一步合并
        for (int i = 1; i < intervals.length; i++) {
            int[] next = intervals[i];
            int[] now = res.getLast();
            //如果当前区间的右区间至少是大于等于被比较区间的左边界的,那么说明他们是相交的,只需要区它们俩比较大的区间作为合并区间的右边界即可
            if (now[1] >= next[0]) {
                now[1] = Math.max(now[1], next[1]);
            }
            //否则说明是不相交区间,直接放到结果里
            else {
                res.addLast(next);
            }
        }
        //返回结果
        return res.toArray(new int[0][0]);
    }

    /**
     * 找出交集
     * 使用双指针策略
     * 先判断是不是交集,如果是则计算出交集部分 即最大的左边界和最小的右边界
     * 如果不是交集跳过
     * 右边界小的那一个前进一步
     */
    int[][] intervalIntersection(int[][] A, int[][] B) {
        int i = 0, j = 0;//双指针
        LinkedList<int[]> res = new LinkedList<>();
        while (i < A.length && j < B.length) {
            int a1 = A[i][0];
            int a2 = A[i][1];
            int b1 = B[j][0];
            int b2 = B[j][1];
            //(b2 < a1 || a2 < b1)表示2种不存在交集的情况,取反
            if (!(b2 < a1 || a2 < b1)) {
                int a = Math.max(a1, b1);
                int b = Math.min(a2, b2);
                res.addLast(new int[]{a, b});
            }
            if (a2 < b2) {
                i++;
            } else {
                j++;
            }
        }
        return res.toArray(new int[0][0]);
    }

}
