### description    
  Given a binary tree, find its minimum depth.  
    
  The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.  
    
  Note: A leaf is a node with no children.  
    
  Example:  
    
  Given binary tree [3,9,20,null,null,15,7],  
    
      3  
     / \  
    9  20  
      /  \  
     15   7  
  return its minimum depth = 2.  
### solution    
```    
  class Solution {  
     public int minDepth(TreeNode root) {  
          if(root == null) {  
              return 0;  
          }  
          int left = minDepth(root.left);  
          int right = minDepth(root.right);  
          if (left == 0 || right == 0) return left + right + 1;  
          return Math.min(left, right) + 1;  
      }  
  }  
```    
    
### 个人解读    
  判断： 不需要中间函数   
  //错误： 没有注意到题干要求的是叶子节点，所以需要增加叶子结点判断。  
  需要注意的是，由于Math.min()操作，会把非叶子节点的结果也算进来，所以需要对叶子节点进行特殊排查。  
    
tags:    
  -  树  
  -  递归  
  -  特殊终止条件  
