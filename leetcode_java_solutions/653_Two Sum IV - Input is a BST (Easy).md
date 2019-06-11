### description    
  Given a Binary Search Tree and a target number, return true if there exist two elements in the BST such that their sum is equal to the given target.  
    
  Example 1:  
    
  Input:   
      5  
     / \  
    3   6  
   / \   \  
  2   4   7  
    
  Target = 9  
    
  Output: True  
     
    
  Example 2:  
    
  Input:   
      5  
     / \  
    3   6  
   / \   \  
  2   4   7  
    
  Target = 28  
    
  Output: False  
    
  在二叉查找树中寻找两个节点，使它们的和为一个给定值  
### solution    
```    
  class Solution {  
       public boolean findTarget(TreeNode root, int k) {  
          List<Integer> list = new ArrayList<>();  
          helper(root, list);  
          Integer[] arr = new Integer[list.size()];  
          list.toArray(arr);  
          int i = 0;   
          int j = arr.length - 1;  
          while(i < j) {  
              int sum = arr[i] + arr[j];  
              if(sum == k) {  
                  return true;  
              } else if(sum < k) {  
                  i++;  
              } else {  
                  j--;  
              }  
          }  
          return false;  
      }  
    
      private void helper(TreeNode root, List<Integer> list) {  
          if(root == null) {  
              return;  
          }  
          helper(root.left, list);  
          int val = root.val;  
          list.add(val);  
          helper(root.right, list);  
      }  
  }  
```    
    
### 个人解读    
  如果是数组的话，可以利用双指针算法，一个从前往后，另一个从后往前。如果从中间(root)开始遍历的话，并没什么用。  
  没办法，先转化成数组再算(类似于数组先排序的预处理)  
    
tags:    
  -  树  
  -  数组  
  -  预处理  
