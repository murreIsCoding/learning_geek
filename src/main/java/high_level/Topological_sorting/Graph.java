package high_level.Topological_sorting;

import java.util.LinkedList;

public class Graph {
    private int v; // 顶点的个数
    private LinkedList<Integer>[] adj; // 邻接表

    public Graph(int v) {
        this.v = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i) {
            adj[i] = new LinkedList<>();
        }
    }

    public void addEdge(int s, int t) { // s先于t，边s->t
        adj[s].add(t);
    }


    public void topoSortByKahn() {
        // 统计每个顶点的入度
        int[] inDegree = new int[v];
        for (int i = 0; i < v; ++i) {
            for (int j = 0; j < adj[i].size(); ++j) {
                // i->w
                int w = adj[i].get(j);
                inDegree[w]++;
            }
        }
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < v; ++i) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }
        while (!queue.isEmpty()) {
            int i = queue.remove();
            System.out.print("->" + i);
            for (int j = 0; j < adj[i].size(); ++j) {
                int k = adj[i].get(j);
                inDegree[k]--;
                if (inDegree[k] == 0) {
                    queue.add(k);
                }
            }
        }
    }

    /**
     * 图上的深度优先搜索我们前面已经讲过了，实际上，拓扑排序也可以用深度优先搜索来实现。
     * 不过这里的名字要稍微改下，更加确切的说法应该是深度优先遍历，遍历图中的所有顶点，而非只是搜索一个顶点到另一个顶点的路径。
     * 关于这个算法的实现原理，我先把代码贴在下面，下面给你具体解释。
     * <p>
     * 这个算法包含两个关键部分。第一部分是通过邻接表构造逆邻接表。
     * 邻接表中，边 s->t 表示 s 先于 t 执行，也就是 t 要依赖 s。
     * 在逆邻接表中，边 s->t 表示 s 依赖于 t，s 后于 t 执行。
     * 为什么这么转化呢？这个跟我们这个算法的实现思想有关。
     * 第二部分是这个算法的核心，也就是递归处理每个顶点。
     * 对于顶点 vertex 来说，我们先输出它可达的所有顶点，也就是说，先把它依赖的所有的顶点输出了，然后再输出自己。
     * 到这里，用 Kahn 算法和 DFS 算法求拓扑排序的原理和代码实现都讲完了。
     * 我们来看下，这两个算法的时间复杂度分别是多少呢？
     * 从 Kahn 代码中可以看出来，每个顶点被访问了一次，每个边也都被访问了一次，所以，Kahn 算法的时间复杂度就是 O(V+E)（V 表示顶点个数，E 表示边的个数）。
     * DFS 算法的时间复杂度我们之前分析过。每个顶点被访问两次，每条边都被访问一次，所以时间复杂度也是 O(V+E)。
     * 注意，这里的图可能不是连通的，有可能是有好几个不连通的子图构成，所以，E 并不一定大于 V，两者的大小关系不确定。
     * 所以，在表示时间复杂度的时候，V、E 都要考虑在内。
     */

    public void topoSortByDFS() {
        // 先构建逆邻接表，边s->t表示，s依赖于t，t先于s
        LinkedList<Integer> inverseAdj[] = new LinkedList[v];
        // 申请空间
        for (int i = 0; i < v; ++i) {
            inverseAdj[i] = new LinkedList<>();
        }
        // 通过邻接表生成逆邻接表
        for (int i = 0; i < v; ++i) {
            for (int j = 0; j < adj[i].size(); ++j) {
                int w = adj[i].get(j); // i->w
                inverseAdj[w].add(i); // w->i
            }
        }
        boolean[] visited = new boolean[v];
        for (int i = 0; i < v; ++i) { // 深度优先遍历图
            if (visited[i] == false) {
                visited[i] = true;
                dfs(i, inverseAdj, visited);
            }
        }
    }

    private void dfs(int vertex, LinkedList<Integer> inverseAdj[], boolean[] visited) {
        for (int i = 0; i < inverseAdj[vertex].size(); ++i) {
            int w = inverseAdj[vertex].get(i);
            if (visited[w] == true) {
                continue;
            }
            visited[w] = true;
            dfs(w, inverseAdj, visited);
        } // 先把vertex这个顶点可达的所有顶点都打印出来之后，再打印它自己
        System.out.print("->" + vertex);
    }


    //3->2 2->0
    public static void main(String[] args) {
        Graph graph = new Graph(4);
        graph.addEdge(3, 2);
        graph.addEdge(2, 0);
        graph.topoSortByDFS();
    }


    /**
     * 课后思考在今天的讲解中，我们用图表示依赖关系的时候，如果 a 先于 b 执行，我们就画一条从 a 到 b 的有向边；反过来，如果 a 先于 b，我们画一条从 b 到 a 的有向边，表示 b 依赖 a，那今天讲的 Kahn 算法和 DFS 算法还能否正确工作呢？如果不能，应该如何改造一下呢？
     *
     * 我们今天讲了两种拓扑排序算法的实现思路，Kahn 算法和 DFS 深度优先搜索算法
     * ，如果换做 BFS 广度优先搜索算法，还可以实现吗？欢迎留言和我分享，也欢迎点击“请朋友读”，把今天
     */
}