package labuladong.dynamic_program.practice;

import org.junit.Assert;
import org.junit.Test;

public class CutRopeTest {

    @Test
    public void cuttingRope() {
        CutRope cutRope = new CutRope();
        Assert.assertEquals(2,cutRope.cuttingRope(3));
        Assert.assertEquals(4,cutRope.cuttingRope(4));
        Assert.assertEquals(6,cutRope.cuttingRope(5));
        Assert.assertEquals(9,cutRope.cuttingRope(6));
        Assert.assertEquals(12,cutRope.cuttingRope(7));
        Assert.assertEquals(18,cutRope.cuttingRope(8));
        Assert.assertEquals(27,cutRope.cuttingRope(9));
        Assert.assertEquals(36,cutRope.cuttingRope(10));
    }
}
