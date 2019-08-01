### description    
  Given inorder and postorder traversal of a tree, construct the binary tree.  
    
  Note:  
  You may assume that duplicates do not exist in the tree.  
    
  For example, given  
    
  inorder = [9,3,15,20,7]  
  postorder = [9,15,7,20,3]  
  Return the following binary tree:  
    
      3  
     / \  
    9  20  
      /  \  
     15   7  
### solution    
```    
    
class Solution {  
     
    public TreeNode buildTree(int[] inorder, int[] postorder) {  
        return helper(inorder, 0, inorder.length-1, postorder, 0, postorder.length-1);  
    }  
  
    private TreeNode helper(int[] inorder, int l1, int r1, int[] postorder, int l2, int r2) {  
        if (l1 > r1) {  
            return null;  
        }  
        if (l1 == r1) {  
            return new TreeNode(inorder[l1]);  
        }  
        TreeNode node = new TreeNode(postorder[r2]);  
        int idx = l1;  
        for (int i = l1; i <= r1; i++) {  
            if (inorder[i] == postorder[r2]) {  
                idx = i;  
                break;  
            }  
        }  
        node.left = helper(inorder, l1, idx-1, postorder, l2, l2+idx-1-l1);  
        node.right = helper(inorder, idx+1, r1, postorder, l2+idx-l1, r2-1);  
        return node;  
    }  
  
}  
```    
    
### 个人解读    
  参考[105](105_Construct%20Binary%20Tree%20from%20Preorder%20and%20Inorder%20Traversal(Medium).md)  
    
    
tags:    
  -  重点题目  
