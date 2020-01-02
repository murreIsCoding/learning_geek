public class WZ2 {
    public static void main(String[] args) {

              getCountOfWhat(11757);

    }

    public static void getCountOfWhat(int all){
        Integer i1 = 242;
        Integer i2 = 240;
        Integer i3 = 239;
        Integer i4 = 238;
        int count = 0 ;
        int c1 = 0 ;
        int c2 = 0;
        int c3= 0 ;
        int c4= 0;
        TheStruck theStruck = null;
        for ( c1= 0; c1 < all / i1 + 1; c1++) {
            for ( c2 = 0; c2 < all / i2 + 1; c2++) {
                for ( c3 = 0; c3 < all / i3 + 1; c3++) {
                    for ( c4 = 0; c4 < all / i4 + 1; c4++) {
                        if (i1 * c1 + i2 * c2 + i3 * c3 + i4 * c4 == all && c1>=11&& c2>=11&& c3>=11&& c4>=11) {
                            count++;
                            theStruck = new TheStruck(all,count,c1,c2,c3,c4);
                            System.out.println(c1+","+c2+","+c3+","+c4);
                        } else if (i1 * c1 + i2 * c2 + i3 * c3 + i4 * c4 > all) {
                            break;
                        }
                    }
                }
            }
        }

    }
    public static  class TheStruck{
        int sum;
        int count ;
        int c1 = 0 ;
        int c2 = 0;
        int c3= 0 ;
        int c4= 0;

        public TheStruck(int sum, int count, int c1, int c2, int c3, int c4) {
            this.sum = sum;
            this.count = count;
            this.c1 = c1;
            this.c2 = c2;
            this.c3 = c3;
            this.c4 = c4;
        }
    }
}
