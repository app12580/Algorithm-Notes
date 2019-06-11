### description    
  Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.  
    
  Note:   
  You may assume k is always valid, 1 ≤ k ≤ BST's total elements.  
    
  Example 1:  
    
  Input: root = [3,1,4,null,2], k = 1  
     3  
    / \  
   1   4  
    \  
     2  
  Output: 1  
  Example 2:  
    
  Input: root = [5,3,6,2,4,null,null,1], k = 3  
         5  
        / \  
       3   6  
      / \  
     2   4  
    /  
   1  
  Output: 3  
    
  找出二叉搜索树中第K小的数  
### solution    
```    
  递归解法：  
    
  public int kthSmallest(TreeNode root, int k) {  
      int leftCnt = count(root.left);  
      if (leftCnt == k - 1) return root.val;  
      if (leftCnt > k - 1) return kthSmallest(root.left, k);  
      return kthSmallest(root.right, k - leftCnt - 1);  
  }  
    
  private int count(TreeNode node) {  
      if (node == null) return 0;  
      return 1 + count(node.left) + count(node.right);  
  }  
    
    
  //中序遍历解法：   
  class Solution {  
       private int cnt = 0;  
      private int val;  
        
      public int kthSmallest(TreeNode root, int k) {  
          inOrder(root, k);  
          return val;  
      }  
        
      private void inOrder(TreeNode node, int k) {  
          if (node == null) return;  
          inOrder(node.left, k);        //这一段是中序遍历：左中右的顺序  
          cnt++;  
          if (cnt == k) {  
              val = node.val;  
              return;  
          }  
          inOrder(node.right, k);  
      }  
  }  
```    
    
### 个人解读    
  想办法利用BST是已经排序好的事实  
  方法一：递归法  
  先递归获取当前节点左子树的个数count，然后根据count来与K进行比较  
  疑虑：明明有着重复计算在里面，但是实际效率并没有太差。  
    
  方法二：中序遍历  
  设置两个全局变量cnt和val  
    
tags:    
  -  树  
  -  中序遍历  
  -  递归  
  -  Kth  
