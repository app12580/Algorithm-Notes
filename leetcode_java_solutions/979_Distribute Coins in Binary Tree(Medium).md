### description    
  Given the root of a binary tree with N nodes, each node in the tree has node.val coins, and there are N coins total.  
    
  In one move, we may choose two adjacent nodes and move one coin from one node to another.  (The move may be from parent to child, or from child to parent.)  
    
  Return the number of moves required to make every node have exactly one coin.  
    
     
    
  Example 1:  
    
    
    
  Input: [3,0,0]  
  Output: 2  
  Explanation: From the root of the tree, we move one coin to its left child, and one coin to its right child.  
  Example 2:  
    
    
    
  Input: [0,3,0]  
  Output: 3  
  Explanation: From the left child of the root, we move two coins to the root [taking two moves].  Then, we move one coin from the root of the tree to the right child.  
  Example 3:  
    
    
    
  Input: [1,0,2]  
  Output: 2  
  Example 4:  
    
    
    
  Input: [1,0,0,null,3]  
  Output: 4  
     
    
  Note:  
    
  1<= N <= 100  
  0 <= node.val <= N  
### solution    
```    
  
Runtime: 1 ms, faster than 94.09% of Java online submissions for Distribute Coins in Binary Tree.  
Memory Usage: 37.7 MB, less than 100.00% of Java online submissions for Distribute Coins in Binary Tree.  
  
  
  class Solution {  
       public int distributeCoins(TreeNode root) {  
          return dfs(root)[1];  
      }  
    
      //return [0]: 富余量， [1]:移动次数  
      //dfs的最终移动状态，会将所有的金币移动到node节点上，定义在哪无所谓，主要是定义清晰即可  
      //[1]的定义： 如果[0]>=0 表示已经移动的次数; 如果[0]<0表示需要从父节点借多少步才能成功，依旧是正数  
      private int[] dfs(TreeNode node) {  
          if(node == null) return new int[]{0,0};  
          int[] left = dfs(node.left);  
          int[] right = dfs(node.right);  
          int val = node.val;  
    
          int fuyu = val - 1 + left[0] + right[0];  
          int count = 0;  
          //下面的才是算法核心，对于count的处理  
          count += Math.abs(left[0]);     
          count += Math.abs(right[0]);     
          count += right[1] + left[1];  //error 一开始把这行漏写了。。。   
          return new int[]{fuyu, count};  
      }  
    
  }  
    
    
```    
    
### 个人解读    
  父子节点之间进行数据传唤，主要考验的是在函数中对树遍历的掌控，方向性，数据性。  
  有两种思路：一种是遍历，另外一种是通过数学变量，在遍历过程中对数学变量进行操作。  
    
  代码实施时候有过几种想法：  
  1、使用层次遍历，被pass了，因为树的左右子节点关系不可控  
  2、递归：需要辅助函数：需要的变量有，返回当前的方法参数节点移动的次数，以及最后是剩余还是缺少。  
  private (富余量, 移动次数) dfs(TreeNode node)  
  出现了贪婪算法结论一： 如果某一树的左右两节点一只为正，另一只为负，那么树內消化是贪婪的。  
    
  总结：  
  天啊，简直难以置信，我TM的几乎是一次成的完成了这个算法！！！！！！！！  
  放在以前根本是天书一样的代码好么。。。。  
    
  最后规律： 其实看透以后，最终结果基本上只和该节点的硬币树与1的差值有关。  
    
tags:    
  -  重点数学  
  -  树  
