package labuladong.dynamic_program.practice;

import org.junit.Assert;
import org.junit.Test;

/**
 * * 示例 1：
 * * 输入：N = 10, K = 1, W = 10
 * * 输出：1.00000
 * * 说明：爱丽丝得到一张卡，然后停止。
 * * 示例 2：
 * * 输入：N = 6, K = 1, W = 10
 * * 输出：0.60000
 * * 说明：爱丽丝得到一张卡，然后停止。
 * * 在 W = 10 的 6 种可能下，她的得分不超过 N = 6 分。
 * * 示例 3：
 * * 输入：N = 21, K = 17, W = 10
 * * 说明:当她从[1,10]的卡堆里抽卡累积,她的策略时当她的分数>=17分就停止抽卡,那么它的分数<=21的概率是0.732278
 */
public class Point21Test {
    Point21 point21 = new Point21();

    @Test
    public void new21Game() {
        double result = point21.new21Game(10, 1, 10);
        Assert.assertEquals(1.0, result,0.00001);
    }
    @Test
    public void new21Game2() {
        double result = point21.new21Game(6, 1, 10);
        Assert.assertEquals(0.6, result,0.00001);
    }@Test
    public void new21Game3() {
        double result = point21.new21Game(21, 17, 10);
        Assert.assertEquals(0.73278, result,0.00001);
    }



    @Test
    public void testNew21Game2() {
        double result = point21.new21Game2(21, 17, 10);
        Assert.assertEquals(0.73278, result,0.00001);
    }
}
