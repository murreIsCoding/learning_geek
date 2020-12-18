package dynamic_program;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 扔鸡蛋问题
 * 有K个鸡蛋  有N层楼  让你算出最少的尝试次数，一定能找到鸡蛋恰好摔不碎的那层楼
 * 7
 * 6
 * 5
 * 4
 * 3
 * 2
 * 1
 * <p>
 * base case
 * 如果K为1,那么就只能逐层扫描N次
 * <p>
 * 状态转移公式
 * 我们选择在第i层扔鸡蛋
 * 如果鸡蛋碎了 : 那么子问题就变成 鸡蛋个数变成 k-1 , 层树变成i-1层
 * 如果鸡蛋没碎 : 那么子问题就变成 鸡蛋个数还是K, 层数是N-i层
 * 次数记得加一
 */
public class ThrowEgg {

    static Map<KEY, Integer> memo = new HashMap<>();

    public static int dp(int K, int N) {
        KEY key = new KEY(K, N);
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        //base case
        if (N == 0) {
            return 0;
        }
        if (K == 1) {
            return N;
        }
        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= N; i++) {
            int count = Math.max(
                    //碎了
                    dp(K - 1, i - 1),
                    //没碎
                    dp(K, N - i)
            ) + 1;
            if (count < min) {
                min = count;
            }
        }
        memo.put(key, min);
        //不会走到这
        return min;
    }


    public static void main(String[] args) {
        System.out.println(dp(2, 100));
    }

    static class KEY {
        int K;
        int N;

        public KEY(int k, int n) {
            K = k;
            N = n;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            KEY key = (KEY) o;
            return K == key.K &&
                    N == key.N;
        }

        @Override
        public int hashCode() {
            return Objects.hash(K, N);
        }
    }
}
