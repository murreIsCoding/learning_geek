import org.apache.commons.lang3.BooleanUtils;

import java.util.ArrayList;
import java.util.List;

public class AndOrCal {
    final static Integer AND = 1;
    final static Integer OR = 2;

    public static void main(String[] args) {
        //布尔列表
        List<Boolean> booleans = new ArrayList<>();
        booleans.add(true);
        booleans.add(true);
        booleans.add(false);

        //连接符列表
        List<Integer> relations = new ArrayList<>();
        relations.add(AND);
        relations.add(AND);

        System.out.println(test(booleans,relations));
    }
    public static boolean test(List<Boolean> booleans, List<Integer> relations){
        return doResult(splitByOR(booleans,relations));
    }

    /**
     * 根据OR来分组
     *
     * @return
     */
    public static List<List<Boolean>> splitByOR(List<Boolean> booleans, List<Integer> relations) {
        //存放结果
        List<List<Boolean>> result = new ArrayList<>();
        //第一个自己先成一组
        List<Boolean> andBooleans = new ArrayList<>();
        andBooleans.add(booleans.get(0));
        result.add(andBooleans);
        for (int i = 0; i < relations.size(); i++) {
            Integer relation = relations.get(i);
            if (relation.equals(AND)) {
                //如果是AND,添加下一个boolean
                andBooleans.add(booleans.get(i + 1));
            } else if (relation.equals(OR)) {
                //如果是OR,创建新的andBooleans,并保存
                andBooleans = new ArrayList<>();
                result.add(andBooleans);
            }
        }
        return result;
    }

    /**
     * 开始处理
     * @return
     */
    public static boolean doResult(List<List<Boolean>> splitByORs){
        List<Boolean> andResults = new ArrayList<>();
        //每个分组内都是AND运算
        for (List<Boolean> booleans : splitByORs){
            Boolean[] booleans1 = new Boolean[booleans.size()];
            booleans.toArray(booleans1);
            Boolean andResult = BooleanUtils.and(booleans1);
            andResults.add(andResult);
        }
        //分组之间用OR运算
        Boolean[] booleans2 = new Boolean[andResults.size()];
        andResults.toArray(booleans2);
        return BooleanUtils.or(booleans2);
    }
}
