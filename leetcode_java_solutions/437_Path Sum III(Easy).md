### description  
  You are given a binary tree in which each node contains an integer value.
  
  Find the number of paths that sum to a given value.
  
  The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).
  
  The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.
  
  Example:
  
  root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
  
        10
       /  \
      5   -3
     / \    \
    3   2   11
   / \   \
  3  -2   1
  
  Return 3. The paths that sum to 8 are:
  
  1.  5 -> 3
  2.  5 -> 2 -> 1
  3. -3 -> 11
  
  返回路径和满足要求个数，必须从上到下，不要求起始和终止点类型(是否根节点、叶子节点)。
### solution  
```  
  class Solution {
      public int pathSum(TreeNode root, int sum) {
          if(root == null) {
              return 0;
          }
          return pathSum(root.left, sum) + pathSum(root.right, sum) + startWithRoot(root, sum);
      }
      
      public int startWithRoot(TreeNode root, int sum) {
           if(root == null) {
              return 0;
          }
          int ret = 0;
          if(root.val == sum) {
              ret = 1;
          }
          return ret + startWithRoot(root.left, sum - root.val) + startWithRoot(root.right, sum - root.val);
      }
  }
  
  // 速率最高算法  TODO
    //大致上是使用一个HashMap存储所有的可达结果。
    //经过解读发现，hm里面存储的是从根节点的所有可达长度。
    //并且！hm中的value要么为0，要么为1
     public int pathSum(TreeNode root, int sum) {
          if (root == null) return 0;
  
          //key = prefix sum, value = number of ways to get to prefix sum
          Map<Integer, Integer> hm = new HashMap<Integer, Integer>();
          hm.put(0, 1);
          return getNumWays(root, hm, sum, 0);
      }
  
      private int getNumWays(TreeNode current, Map<Integer, Integer> hm, int target, int sumSoFar) {
          if (current == null) return 0;
          sumSoFar += current.val;
  
          int pathsToSumSoFar = hm.getOrDefault(sumSoFar-target, 0);        
          //sumSoFar-target 的含义是：从根节点到cur的为sumSoFar（记为A），如果hm中sumSoFar-target（记为B）的value不为0，就说明
          
          //|--------------|--------------------|
          //root节点       B   区间长度target   A
          //从root到A是有的，到B也是的，那么从B点到A点，就是题干所要求的的路径之一了。
          
          int currentCountForSumSoFar = hm.getOrDefault(sumSoFar, 0);
          hm.put(sumSoFar, currentCountForSumSoFar+1);
  
          int totalPaths = pathsToSumSoFar + getNumWays (current.left, hm, target, sumSoFar) + getNumWays (current.right, hm, target, sumSoFar);
  
          currentCountForSumSoFar = hm.get(sumSoFar);
          hm.put(sumSoFar, currentCountForSumSoFar - 1);
          return totalPaths;
      }
      @Test
         public void test1() {
     // [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
             TreeNode n1 = new TreeNode(10);
             TreeNode n2 = new TreeNode(5);
             TreeNode n3 = new TreeNode(-3);
             TreeNode n4 = new TreeNode(3);
             TreeNode n5 = new TreeNode(2);
             TreeNode n6 = new TreeNode(11);
             TreeNode n7 = new TreeNode(3);
             TreeNode n8 = new TreeNode(-2);
             TreeNode n9 = new TreeNode(1);
             n1.left = n2;
             n1.right = n3;
             n2.left = n4;
             n2.right = n5;
             n3.right = n6;
             n4.left = n7;
             n4.right = n8;
             n5.right = n9;
             int i = pathSum(n1, 8);
             System.out.println(i);
         }
     //方法二解读：
     最大的疑问，就是就算我存储了所有根节点可达长度，凭什么就能拿来用啊，万一那条路径在其他分支上呢。
     这时候就需要脑海对前中后序递归有个清晰的认识了，前序添加路径，中序计算，后续撤销路径，会发现所有的递归过程中，hm只是单挑路径的存储结果。
     可以理解为中序遍历的优势，可以保证在树的递归过程中只保存单向的中间内容。
```  
  
### 个人解读  
  判断f(root)与f(root.left)关系
  f(root) = f(root.left) + f(root.right) + 因为root引入而新产生的路径。
  所以需要中间函数。
  递归边界条件除了null以外，还需要加上别的。
  
tags:  
  -  树
  -  递归
  -  前中后应用  
  -  递归进阶  
