### description    
  Given an undirected graph, return true if and only if it is bipartite.  
    
  Recall that a graph is bipartite if we can split it's set of nodes into two independent subsets A and B such that every edge in the graph has one node in A and another node in B.  
    
  The graph is given in the following form: graph[i] is a list of indexes j for which the edge between nodes i and j exists.  Each node is an integer between 0 and graph.length - 1.  There are no self edges or parallel edges: graph[i] does not contain i, and it doesn't contain any element twice.  
    
  Example 1:  
  Input: [[1,3], [0,2], [1,3], [0,2]]  
  Output: true  
  Explanation:   
  The graph looks like this:  
  0----1  
  |    |  
  |    |  
  3----2  
  We can divide the vertices into two groups: {0, 2} and {1, 3}.  
  Example 2:  
  Input: [[1,2,3], [0,2], [0,1,3], [0,2]]  
  Output: false  
  Explanation:   
  The graph looks like this:  
  0----1  
  | \  |  
  |  \ |  
  3----2  
  We cannot find a way to divide the set of nodes into two independent subsets.  
     
    
  Note:  
    
  graph will have length in range [1, 100].  
  graph[i] will contain integers in range [0, graph.length - 1].  
  graph[i] will not contain i or duplicate values.  
  The graph is undirected: if any element j is in graph[i], then i will be in graph[j].  
    
  判断是否为二分图  
  二分图定义:能把一个图的节点分成两部分，使得每条边都各有一个节点在一边  
  输入条件是：下标i的这个点，与哪些值相邻  
### solution    
```    
  class Solution {  
      public boolean isBipartite(int[][] graph) {  
          int[] colors = new int[graph.length];  
            
          for (int i = 0; i < graph.length; i++) {  
              if (colors[i] == 0 && !dfs(graph, i, colors, 2)) {  
                  return false;  
              }  
          }  
          return true;  
      }  
        
      private int swapColor(int original) {  
          return (original == 1) ? 2 : 1;  
      }  
    
      private boolean dfs(int[][] graph, int v, int[] colors, int adjColor) {  
          if (colors[v] == 0) {  
              int newColor = swapColor(adjColor);  
              colors[v] = newColor;  
              for (int i = 0; i < graph[v].length; i++) {  
                  if (!dfs(graph, graph[v][i], colors, newColor)) {  
                      return false;  
                  }  
              }  
          }  
          else {  
              if (colors[v] == adjColor) {  
                  return false;  
              }  
          }  
          return true;  
      }  
  }  
    
  //我整理后自己尝试写了一次  
  class Solution {  
      public boolean isBipartite(int[][] graph) {  
          int[] res = new int[graph.length];     //0表示未处理，1表示集合A，2表示集合B  
          for(int i = 0; i < graph.length; i++) {  
              if(res[i] == 0 && !helper(graph, res, i, 1)) {  
                  return false;  
              }  
          }  
          return true;  
    
      }  
    
      private int swapNewColor(int preColor) {  
          return preColor == 1 ? 2 :1;  
      }  
    
      private boolean helper(int[][] graph, int[] res, int index, int addColor) {  //这里的addColor表示不能的颜色，换成需要添加的颜色也是OK的  
          //给index赋值  
          //给index关联的赋值  
          if(res[index] == 0) {  
              // 未处理过  
               
              int nextColor = swapNewColor(addColor);  
               res[index] = nextColor;  //   res[index] = nextColor;  //2替换1  
              for(int i = 0; i < graph[index].length; i++) {  
                  if(!helper(graph, res, graph[index][i], nextColor)) {  
                      return false;  
                  }  
              }  
          } else {  
              //已处理  
              if(res[index] == addColor) {      //替换成 if(res[index] != addColor) {  
                  return false;  
              }  
          }  
          return true;  
      }  
    
  }  
```    
    
### 个人解读    
  所谓图的结构，还是通过数组来实现的。  
  第一思路：从头开始遍历，先把第i=0的点放进SetA里面，然后把graph[i]里面的值放进SetB，遇到矛盾即退出。如果图分成互不相干的两块，那么对剩下的点重复此步骤。  
  看了下LeetCode里面的参考答案，感觉这好像是一个经典问题，完全可以转换成画地图问题了。  
  画地图问题的代码模板有两个要注意的地方，一个数递归方法的参数有哪些条件（原始图的数据；存放颜色结果的数组；当前遍历点；起始颜色）；第二地方就是要善用递归思想。  
  递归思想主要是前中后的那一套，以及产生的影响（在递归调用的时候，已经发生了什么）。  
  如果不仅仅是两个颜色，起始颜色这个参数可以换成不能用的颜色。  
    
tags:    
  -  数组  
  -  图  
