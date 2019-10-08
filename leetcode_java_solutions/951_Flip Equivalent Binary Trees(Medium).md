### description    
  For a binary tree T, we can define a flip operation as follows: choose any node, and swap the left and right child subtrees.  
    
  A binary tree X is flip equivalent to a binary tree Y if and only if we can make X equal to Y after some number of flip operations.  
    
  Write a function that determines whether two binary trees are flip equivalent.  The trees are given by root nodes root1 and root2.  
    
     
    
  Example 1:  
    
  Input: root1 = [1,2,3,4,5,6,null,null,null,7,8], root2 = [1,3,2,null,6,4,5,null,null,null,null,8,7]  
  Output: true  
  Explanation: We flipped at nodes with values 1, 3, and 5.  
  Flipped Trees Diagram  
     
    
  Note:  
    
  Each tree will have at most 100 nodes.  
  Each value in each tree will be a unique integer in the range [0, 99].  
### solution    
```    
   Runtime: 0 ms, faster than 100.00% of Java online submissions for Flip Equivalent Binary Trees.  
   Memory Usage: 34.8 MB, less than 100.00% of Java online submissions for Flip Equivalent Binary Trees.  
     
     
class Solution {  
   public boolean flipEquiv(TreeNode root1, TreeNode root2) {  
        if(root1 == null && root2 == null) return true;  
        if(root1 == null || root2 == null) return false;  
        if(root1.val != root2.val) return false;  
        return (flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right)) || (flipEquiv(root1.left, root2.right) && flipEquiv(root1.right, root2.left));  
    }  
}  
```    
    
### 个人解读    
  主要是利用递归，  
  如果两个树的左子树和右子树都满足条件，或者交叉满足，就可以递归成功。  
    
tags:    
  -  树  
  -  递归  
