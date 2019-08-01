### description    
  Given preorder and inorder traversal of a tree, construct the binary tree.  
    
  Note:  
  You may assume that duplicates do not exist in the tree.  
    
  For example, given  
    
  preorder = [3,9,20,15,7]  
  inorder = [9,3,15,20,7]  
  Return the following binary tree:  
    
      3  
     / \  
    9  20  
      /  \  
     15   7  
### solution    
```    
  class Solution {  
      public TreeNode buildTree(int[] preorder, int[] inorder) {  
          return helper(0, 0, inorder.length - 1, preorder, inorder);  
      }  
    
      public TreeNode helper(int preStart, int inStart, int inEnd, int[] preorder, int[] inorder) {  
          if (preStart > preorder.length - 1 || inStart > inEnd) {  
              return null;  
          }  
          int a = preorder[preStart];  
          TreeNode root = new TreeNode(a);  
          int inIndex = 0; // Index of current root in inorder  
          for (int i = inStart; i <= inEnd; i++) {  
              if (inorder[i] == root.val) {  
                  inIndex = i;  
                  break;  
              }  
          }  
          root.left = helper(preStart + 1, inStart, inIndex - 1, preorder, inorder);  
          root.right = helper(preStart + inIndex - inStart + 1, inIndex + 1, inEnd, preorder, inorder);  
          return root;  
      }  
  }  
```    
    
### 个人解读    
  重点题目，需要对于树的遍历有很深的理解，知道每个位置之间的相互关系。  
```  
  
        if (preStart > preorder.length - 1 || inStart > inEnd) {  
            return null;  
        }  
          
          
    这两行需要重点解读  
    root.left = helper(preStart + 1, inStart, inIndex - 1, preorder, inorder);    
    root.right = helper(preStart + inIndex - inStart + 1, inIndex + 1, inEnd, preorder, inorder);  
      
    preStart+1： 当前节点的左边节点，如果为null，进去了方法以后再判断  
    inIndex-1和inIndex+1: 在中序遍历中，当前节点的左边第一个索引和右边第一个索引。  
    preStart + inIndex - inStart + 1： inIndex - inStart为当前节点左边子树的数量，再经过+1后，就为右子树第一个了。  
  ```  
    
  切片法，需要思考，在每一个序列中，相对于某一个节点是怎样子的。  
  中序遍历时比较特殊的，需要一段一段跳，而前后都是一个一个走的。  
  
  前序和后续可以划分成左右中三个大段，这个是核心点，通过这个来划分。
    
tags:    
  -  树的遍历方向  
  -  重点题目  
