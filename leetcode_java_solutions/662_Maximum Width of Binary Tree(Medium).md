### description    
  Given a binary tree, write a function to get the maximum width of the given tree. The width of a tree is the maximum width among all levels. The binary tree has the same structure as a full binary tree, but some nodes are null.  
    
  The width of one level is defined as the length between the end-nodes (the leftmost and right most non-null nodes in the level, where the null nodes between the end-nodes are also counted into the length calculation.  
    
  Example 1:  
    
  Input:   
    
             1  
           /   \  
          3     2  
         / \     \    
        5   3     9   
    
  Output: 4  
  Explanation: The maximum width existing in the third level with the length 4 (5,3,null,9).  
  Example 2:  
    
  Input:   
    
            1  
           /    
          3      
         / \         
        5   3       
    
  Output: 2  
  Explanation: The maximum width existing in the third level with the length 2 (5,3).  
  Example 3:  
    
  Input:   
    
            1  
           / \  
          3   2   
         /          
        5        
    
  Output: 2  
  Explanation: The maximum width existing in the second level with the length 2 (3,2).  
  Example 4:  
    
  Input:   
    
            1  
           / \  
          3   2  
         /     \    
        5       9   
       /         \  
      6           7  
  Output: 8  
  Explanation:The maximum width existing in the fourth level with the length 8 (6,null,null,null,null,null,null,7).  
    
    
  Note: Answer will in the range of 32-bit signed integer.  
### solution    
```    
   Runtime: 1 ms, faster than 99.81% of Java online submissions for Maximum Width of Binary Tree.  
   Memory Usage: 36.5 MB, less than 100.00% of Java online submissions for Maximum Width of Binary Tree.  
     
     
class Solution {  
      public int widthOfBinaryTree(TreeNode root) {  
        return dfs(root, 0, 1, new ArrayList<Integer>(), new ArrayList<Integer>());  
    }  
  
    public int dfs(TreeNode root, int level, int order, List<Integer> start, List<Integer> end){  
        if(root == null)return 0;  
        if(start.size() == level){  
            start.add(order); end.add(order);   //当前level的第一个节点  
        } else {  
            end.set(level, order);  
        }  
        int cur = end.get(level) - start.get(level) + 1;  
        int left = dfs(root.left, level + 1, 2*order, start, end);  
        int right = dfs(root.right, level + 1, 2*order + 1, start, end);  
        return Math.max(cur, Math.max(left, right));  
    }  
}  
```    
    
### 个人解读    
    
  思路：通过递归，获取每一层的最左端和最右端，如果某一个为为null了，怎么办。。。  
  回想一下树的方法：  
  1、递归还是循环  
  2、递归时候会丢失内容  
    
  本题目的核心知识在于父节点是order，它的左右两个子节点分别是order * 2 和 order * 2 + 1.  
    
  本题目还需要注意，dfs做的是修改集合内容，而方法返回值是最大值。  
  left和right并不是左子树或是右子树的值，而是当递归运行到那里时候产生的结果  
    
    
tags:    
  -  树   
  -  树的坐标   
