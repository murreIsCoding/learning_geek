public class Money {

    //假如我现在收入1W
    //我从现在开始每个月存款收入的50%，
    //我一年发13个月工资，每年我的月工资提升30%，但是每年涨幅递减，10年后不再涨。
    // 20年后我能存多少钱
    public static void main(String[] args) {
        Double currentPay = 15000.0;//当前工资
        Double payMonth = 14.0;//每年的收入
        Double storeRate = 0.6;//存款率
        Double groth = 1.3;
        Double myMoney = 0.0;
        for (int i = 0; i < 5; i++) {
            myMoney += payMonth * currentPay * storeRate;
            System.out.println("第"+(i+1)+"年底");
            System.out.println("工资"+currentPay.intValue());
            System.out.println("存款"+myMoney.intValue());
            System.out.println("工资涨幅"+groth);
            currentPay=currentPay*groth;
            groth = groth-0.03;
            if (groth<1){
                groth=1.0;
            }
        }
    }

}
