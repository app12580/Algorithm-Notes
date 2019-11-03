### description  
  A game on an undirected graph is played by two players, Mouse and Cat, who alternate turns.
  
  The graph is given as follows: graph[a] is a list of all nodes b such that ab is an edge of the graph.
  
  Mouse starts at node 1 and goes first, Cat starts at node 2 and goes second, and there is a Hole at node 0.
  
  During each player's turn, they must travel along one edge of the graph that meets where they are.  For example, if the Mouse is at node 1, it must travel to any node in graph[1].
  
  Additionally, it is not allowed for the Cat to travel to the Hole (node 0.)
  
  Then, the game can end in 3 ways:
  
  If ever the Cat occupies the same node as the Mouse, the Cat wins.
  If ever the Mouse reaches the Hole, the Mouse wins.
  If ever a position is repeated (ie. the players are in the same position as a previous turn, and it is the same player's turn to move), the game is a draw.
  Given a graph, and assuming both players play optimally, return 1 if the game is won by Mouse, 2 if the game is won by Cat, and 0 if the game is a draw.
  
   
  
  Example 1:
  
  Input: [[2,5],[3],[0,4,5],[1,4,5],[2,3],[0,2,3]]
  Output: 0
  Explanation:
  4---3---1
  |   |
  2---5
   \ /
    0
   
  
  Note:
  
  3 <= graph.length <= 50
  It is guaranteed that graph[1] is non-empty.
  It is guaranteed that graph[2] contains a non-zero element. 
### solution  
```  

// 官方答案： 极大极小
Runtime: 38 ms, faster than 39.87% of Java online submissions for Cat and Mouse.
Memory Usage: 39.6 MB, less than 18.18% of Java online submissions for Cat and Mouse.

  class Solution {
       
    public int catMouseGame(int[][] graph) {
        int N = graph.length;
        final int DRAW = 0, MOUSE = 1, CAT = 2;

        int[][][] color = new int[N][N][3];
        int[][][] degree = new int[N][N][3];

        // degree[node] : the number of neutral children of this node
        for (int m = 0; m < N; ++m) {
            for (int c = 0; c < N; ++c) {
                degree[m][c][1] = graph[m].length;
                degree[m][c][2] = graph[c].length;
                for (int x: graph[c]) {
                    // 猫不能进老鼠洞
                    if (x == 0) {
                        degree[m][c][2]--;
                        break;
                    }
                }
            }
        }


        // 问题是这个queue干啥的啊？
        Queue<int[]> queue = new LinkedList();
        for (int i = 0; i < N; ++i){
            for (int t = 1; t <= 2; ++t) {
                color[0][i][t] = MOUSE; //所有老鼠在0的情况判定成老鼠赢
                queue.add(new int[]{0, i, t, MOUSE});
                // 所有老鼠和猫在一块的算猫赢
                if (i > 0) {
                    color[i][i][t] = CAT;
                    queue.add(new int[]{i, i, t, CAT});
                }
            }
        }


        // percolate
        while (!queue.isEmpty()) {
            // for nodes that are colored :
            int[] node = queue.remove();
            int i = node[0], j = node[1], t = node[2], c = node[3];
            // for every parent of this node i, j, t :
            for (int[] parent: parents(graph, i, j, t)) {
                int i2 = parent[0], j2 = parent[1], t2 = parent[2];
                // if this parent is not colored :
                if (color[i2][j2][t2] == DRAW) {
                    // if the parent can make a winning move (ie. mouse to MOUSE), do so
                    if (t2 == c) {
                        // c是当前节点的结果，
                        // 如果当前是猫赢，并且上一轮是猫的回合，上一轮就是猫赢
                        // 如果当前是鼠赢，并且上一轮是鼠的回合，上一轮就是鼠赢
                        color[i2][j2][t2] = c;
                        queue.add(new int[]{i2, j2, t2, c});
                    } else {
                        // 如果上一轮的选手不能通过当前这一步获胜，那么它就不会这么走，因此出度--
                        // 如果出度减到0，说明没有办法只能把胜利交给对方， 即3-t2
                        degree[i2][j2][t2]--;
                        if (degree[i2][j2][t2] == 0) {
                            color[i2][j2][t2] = 3 - t2;
                            queue.add(new int[]{i2, j2, t2, 3 - t2});
                        }
                    }
                }
            }
        }

        return color[1][2][1];
    }


    //int DRAW = 0, MOUSE = 1, CAT = 2;
    //表示输入点状态的前置状态点
    public List<int[]> parents(int[][] graph, int m, int c, int t) {
        List<int[]> ans = new ArrayList();
        if (t == 2) {
            for (int m2: graph[m])
                ans.add(new int[]{m2, c, 3-t});
        } else {
            for (int c2: graph[c]) if (c2 > 0)
                ans.add(new int[]{m, c2, 3-t});
        }
        return ans;
    }
  }
```  
  
### 个人解读  
  图题目分为两种：排序和解析。本题是解析。
  
  动态规划，覆盖全部情况。
  猫位置，老鼠位置，谁的回合，这三个量可以标记唯一状态。三维Dp
  
  
  思考一下可能的各种指标：
  1、各自到终点的距离
  2、是否成环以及成环位置
  
  代码很关键，可以作为模板
  
  总结：
  1、通过出度来代替for循环去找某一步的决策
  2、一个好的parents，可以获取所有上一步的情况
  3、从结果出发，使用一个
  
tags:  
  -  重点题目
  -  机器博弈
  -  图论
  -  极小极大
