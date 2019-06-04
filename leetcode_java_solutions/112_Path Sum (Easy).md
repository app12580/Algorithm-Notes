### description    
  Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.  
    
  Note: A leaf is a node with no children.  
    
  Example:  
    
  Given the below binary tree and sum = 22,  
    
        5  
       / \  
      4   8  
     /   / \  
    11  13  4  
   /  \      \  
  7    2      1  
  return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.  
    
  判断从根节点到叶子结点的路径和是否为target目标值。  
### solution    
```    
  class Solution {  
      public boolean hasPathSum(TreeNode root, int sum) {  
         if(root == null) {  
              return false;  
          }  
          if(root.left == null && root.right == null && root.val == sum) {  
              return true;  
          }  
          return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum -root.val);  
    
      }  
         
  }  
```    
    
### 个人解读    
  首先需要判断是否需要中间函数：因为f(root, target) = f(root.left, target - val) || f(root.right, target - val);所以不需要中间函数。  
  边界条件 root=[],sum=0时返回false;  
  这时候的边界条件有点恶心了，有两种解决思路：增加边界判断，不光是判断node==null，还需要往下一级判断；设置中间函数，在一开始把特殊情况的边界给排除掉。  
    
  反思：  
  ```  
 class Solution {  
     public boolean hasPathSum(TreeNode root, int sum) {  
        if(root == null) {  
            return false;  
        }  
        return helper(root, sum);  
   
     }  
     public boolean helper(TreeNode root, int sum){  
         if(root == null) {  
             return sum == 0;  
         }  
         return helper(root.left, sum - root.val) || helper(root.right, sum -root.val);  
     }  
 }   
  ```  
  这种解法的问题在于没有确定叶子节点，而是在遍历过程中，只要某一子树为空，就可以做path的终止点了。  
    
tags:    
  -  树  
  -  递归  
  -  叶子结点终止条件  
