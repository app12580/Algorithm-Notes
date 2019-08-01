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
  
tags:  
  -  树的遍历方向
  -  重点题目
