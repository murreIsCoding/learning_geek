package labuladong.dynamic_program;

import org.junit.Assert;
import org.junit.Test;

public class RegexTest {

    @Test
    public void isMatch() {
        Regex regex = new Regex();
        boolean result = regex.twoStringisMatch("123","123");
        Assert.assertEquals(true,result);
        boolean result2 = regex.twoStringisMatch("1234","123");
        Assert.assertEquals(false,result2);
    }

    @Test
    public void twoStringisMatch_recursion() {
        Regex regex = new Regex();
        boolean result = regex.twoStringisMatch_recursion("123","123");
        Assert.assertEquals(true,result);
        boolean result2 = regex.twoStringisMatch_recursion("1234","123");
        Assert.assertEquals(false,result2);
    }

    @Test
    public void dotMatch_recursion() {
        Regex regex = new Regex();
        boolean result = regex.dotMatch_recursion("123","123");
        Assert.assertEquals(true,result);
        boolean result2 = regex.dotMatch_recursion("123","12.");
        Assert.assertEquals(true,result2);
        boolean result3 = regex.dotMatch_recursion("1234","123");
        Assert.assertEquals(false,result3);
    }



    @Test
    public void isMatch_3() {
        Regex regex = new Regex();
        Assert.assertEquals(true,regex.isMatch_3("123","123"));
        Assert.assertEquals(false,regex.isMatch_3("1234","123"));
        Assert.assertEquals(true,regex.isMatch_3("123","12."));
        Assert.assertEquals(true,regex.isMatch_3("1233","123*"));
        Assert.assertEquals(true,regex.isMatch_3("12","123*"));
        Assert.assertEquals(true,regex.isMatch_3("1256","123*56"));
    }

    @Test
    public void isMatch_3_index() {
        Regex regex = new Regex();
        Assert.assertEquals(true,regex.isMatch_3_index("123","123",0,0));
        Assert.assertEquals(false,regex.isMatch_3_index("1234","123",0,0));
        Assert.assertEquals(true,regex.isMatch_3_index("123","12.",0,0));
        Assert.assertEquals(true,regex.isMatch_3_index("1233","123*",0,0));
        Assert.assertEquals(true,regex.isMatch_3_index("12","123*",0,0));
        Assert.assertEquals(true,regex.isMatch_3_index("1256","123*56",0,0));
    }
}
