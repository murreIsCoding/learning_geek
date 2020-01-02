public class SplitQuery {

    public static int getTargetIndex(int[] array, int start, int end, int target) {
        if (end - start == 1) {
            if (array[start] == target) {
                return start;
            } else {
                return end;
            }
        }
        int middle = (end + start) / 2;
        int middleValue = array[middle];
        if (target < middleValue) {
            return getTargetIndex(array, start, middle, target);
        } else if (target > middleValue) {
            return getTargetIndex(array, middle, end, target);
        } else if (target == middleValue) {
            return middle;
        }
        return 0;
    }

    public static void main(String[] args) {
        int[] array = {0,10,20,30,40,50,60,70};
        int targetIndex = getTargetIndex(array,0,array.length-1,40);
        System.out.println(targetIndex);
    }
}
