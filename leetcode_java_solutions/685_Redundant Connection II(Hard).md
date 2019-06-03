### description  
  
### solution  
```  
   public int[] findRedundantDirectedConnection(int[][] edges) {
          //三种情况
  //        有环，无入度2的节点
  //        无环，有入度2的节点
  //        有环，有入度2的节点
          int[] can1 = {-1, -1};      //前面的是爹
          int[] can2 = {-1, -1};
          int[] parent = new int[edges.length + 1];
          for (int i = 0; i < edges.length; i++) {
              if (parent[edges[i][1]] == 0) {
                  parent[edges[i][1]] = edges[i][0];
              } else {
                  can2 = new int[] {edges[i][0], edges[i][1]};        //当前的边
                  can1 = new int[] {parent[edges[i][1]], edges[i][1]};    //之前出现的，出现两个爹的边
                  edges[i][1] = 0;        //当前节点 执行泯灭
              }
          }
          for (int i = 0; i < edges.length; i++) {
              parent[i] = i;      //重新计算
          }
          for (int i = 0; i < edges.length; i++) {
              if (edges[i][1] == 0) {     // 跳过
                  continue;
              }
              int child = edges[i][1], father = edges[i][0];
              if (root(parent, father) == child) {        //如果成环
                  // 这一步很巧妙 临界发生条件
                  if (can1[0] == -1) {        //can1 未初始化，说明不含入度为2的点
                      return edges[i];   //如果成环并且不含入度为2的点， 则返回当前边
                  }
                  return can1;  //这一步有疑问，凭什么返回can1，
                  //如果成环，并且含有入度为2的点，则返回先出现的那一个入度为2的边？？？ 凭什么啊
                  //就凭此时的 can2的成环影响已经被湮灭了
              }
              parent[child] = father;
          }
          return can2;  //如果不成环，则返回后面的那个
      }
  
      int root(int[] parent, int i) {
          while (i != parent[i]) {
              parent[i] = parent[parent[i]];
              i = parent[i];
          }
          return i;
      }
```  
  
### 个人解读  
  文字描述里面说的[u,v]，u是父节点，v是子节点，结果下面里面的图里是u指向v。箭头指向了子节点。
  //[来自discuss](https://leetcode.com/problems/redundant-connection-ii/discuss/108045/C%2B%2BJava-Union-Find-with-explanation-O(n))
    ```
    This problem is very similar to "Redundant Connection". But the description on the parent/child relationships is much better clarified.
    
    There are two cases for the tree structure to be invalid.
    1) A node having two parents;
       including corner case: e.g. [[4,2],[1,5],[5,2],[5,3],[2,4]]
    2) A circle exists
    If we can remove exactly 1 edge to achieve the tree structure, a single node can have at most two parents. So my solution works in two steps.
    
    1) Check whether there is a node having two parents. 
        If so, store them as candidates A and B, and set the second edge invalid.   //这一步湮灭是非常关键的一步
    2) Perform normal union find. 
        If the tree is now valid    //不成环
               simply return candidate B
        else if candidates not existing 
               we find a circle, return current edge; 
        else 
               remove candidate A instead of B.
    ```
    
  通过创建一个parent数组来找出入度为2的边，
  通过并查集的find通用方法，来判断是否成环。这里面用了一个很巧妙的方法："临界判断条件"，因为有向图不同于无向图，parent不能乱设立，容易出现问题。
  把
  ```
   if (root(parent, father) == child) {     
  ```
  改成
    ```
    if (root(parent, father) == root(parent, child)) 
    ```
 程序报错。
  
  
  
tags:  
  -  并查集
  
