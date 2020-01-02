import java.util.HashMap;

public class Main3 {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello World!");

        for (int j=0;j<20 ;j++){
            HashMap hashMap1 = new HashMap();
            for (int i = 0; i < 1000000; i++) {
                String[] strs = {new String("aaaaaaa"),
                        "bbbbbbbbbbbbbb",
                        "ccccccccccc",
                        "dddddddddddddddd",
                        "eeeeeeeeeeeee",
                        "ffffffffff","gggggggggg"
                        ,"hhhhhhhhhhhhhh","iiiiiiiii","jjjjjjjjjjjj","lkkkkkkkkkkkkkkk","lllllllllll","mmmmmmmmmmmmmm"};
                hashMap1.put(i+"",strs);
            }
        }


        System.out.println("ok了");
        Thread.sleep(300 * 1000);
    }
}
