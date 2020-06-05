import java.text.NumberFormat;
import java.util.Locale;

public class SetTest {
    public static void main(String args[]) {
        double x = 132927207.123;
        NumberFormat ddf1 = NumberFormat.getInstance();
        ddf1.setMaximumFractionDigits(2);
        ddf1.setGroupingUsed(false);
        String s = ddf1.format(x);
        System.out.print(s);
    }
}
