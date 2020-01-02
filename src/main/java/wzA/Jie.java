package wzA;

import java.util.ArrayList;
import java.util.List;

public class Jie {
    int Y;
    int c1 = 0;
    int c2 = 0;
    int c3 = 0;
    int c4 = 0;

    public Jie(int y, int c1, int c2, int c3, int c4) {
        Y = y;
        this.c1 = c1;
        this.c2 = c2;
        this.c3 = c3;
        this.c4 = c4;
    }

    @Override
    public String toString() {
        return "Jie{" +
                "Y=" + Y +
                ", c1=" + c1 +
                ", c2=" + c2 +
                ", c3=" + c3 +
                ", c4=" + c4 +
                '}';
    }

    public static List<Jie> getCountOfWhat(int all) {
        Integer i1 = 242;
        Integer i2 = 240;
        Integer i3 = 239;
        Integer i4 = 238;
        int count = 0;
        int c1 = 0;
        int c2 = 0;
        int c3 = 0;
        int c4 = 0;
        List<Jie> jies = new ArrayList<>();
        for (c1 = 0; c1 < all / i1 + 1; c1++) {
            for (c2 = 0; c2 < all / i2 + 1; c2++) {
                for (c3 = 0; c3 < all / i3 + 1; c3++) {
                    for (c4 = 0; c4 < all / i4 + 1; c4++) {
                        if (i1 * c1 + i2 * c2 + i3 * c3 + i4 * c4 == all) {
                            Jie jie = new Jie(all, c1, c2, c3, c4);
                            jies.add(jie);
                        } else if (i1 * c1 + i2 * c2 + i3 * c3 + i4 * c4 > all) {
                            break;
                        }
                    }
                }
            }
        }
        return jies;
    }

    public static void main(String[] args) {
       new Thread(() -> run(10000,20000)).start();
       new Thread(() -> run(20000,30000)).start();
       new Thread(() -> run(30000,40000)).start();
       new Thread(() -> run(40000,50000)).start();
    }

    public static void run(int start , int end ){
        for (int i = start; i < end; i++) {
            List<Jie> jies = getCountOfWhat(i);
            if (jies != null && jies.size() != 0) {
                boolean can = true;
                for (Jie jie : jies) {
                    if (!(jie.c1 > 1 && jie.c2 > 1 && jie.c3 > 1 && jie.c4 > 1)) {
                        can = false;
                        break;
                    }
                }
                if (can) {
                    System.out.println(jies);
                }
            }
        }
    }
}


