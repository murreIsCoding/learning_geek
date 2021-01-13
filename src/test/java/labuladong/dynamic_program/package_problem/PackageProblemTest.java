package labuladong.dynamic_program.package_problem;

import org.junit.Test;

public class PackageProblemTest {


    @Test
    public void knapsack() {
        int N = 3, W = 4;
        int[] wt = {2, 1, 3}, val = {4, 2, 3};
        PackageProblem p = new PackageProblem();
        System.out.println(p.knapsack(W, N, wt, val));
    }
}
