### description    
  Given an array where elements are sorted in ascending order, convert it to a height balanced BST.  
    
  For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.  
    
  Example:  
    
  Given the sorted array: [-10,-3,0,5,9],  
    
  One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:  
    
        0  
       / \  
     -3   9  
     /   /  
   -10  5  
     
   从有序数组中构造二叉查找树  
### solution    
```    
         public TreeNode sortedArrayToBST(int[] nums) {  
          return helper(nums, 0, nums.length - 1);  
      }  
    
      public TreeNode helper(int[] nums, int start, int end) {  
          if(start <= end) {  
              int mid = start + (end - start) / 2;  
              TreeNode node = new TreeNode(nums[mid]);  
              node.left = helper(nums, start, mid - 1);  
              node.right = helper(nums, mid + 1, end);  
              return node;  
          }  
          return null;  
      }  
  
```    
    
### 个人解读    
  主要还是利用递归，每次只创建一个节点。  
  每次只选取mid节点，做好极值处理，需要start <= end  
    
tags:    
  -  数组   
  -  BST   
  -  递归   
