### description    
  Given a binary search tree with non-negative values, find the minimum absolute difference between values of any two nodes.  
    
  Example:  
    
  Input:  
    
     1  
      \  
       3  
      /  
     2  
    
  Output:  
  1  
    
  Explanation:  
  The minimum absolute difference is 1, which is the difference between 2 and 1 (or between 2 and 3).  
### solution    
```    
  class Solution {  
     private Integer curInt = null;  
      private int max = 1;  
      private int cur = 1;  
    
      public int[] findMode(TreeNode root) {  
          if(root == null) return new int[0];  
          List<Integer> res = new ArrayList<>();  
          inOrder(root, res);  
          int[] arr = new int[res.size()];  
          for(int i = 0; i < res.size(); i++) {  
              arr[i] = res.get(i);  
          }  
          return arr;  
      }  
    
      private void inOrder(TreeNode root, List<Integer> res) {  
          if(root == null) return;  
          inOrder(root.left, res);  
          int val = root.val;  
          if(curInt != null) {  
              if(val == curInt) {  
                  cur++;  
              } else {  
                  cur = 1;  
              }  
          }  
          if(cur > max) {  
              max = cur;  
              res.clear();  
              res.add(val);  
          } else if(cur == max) {  
              res.add(val);  
          }  
    
          curInt = val;  
  inOrder(root.right, res);  
      }  
  }  
```    
    
### 个人解读    
  第一思路： 做一个map，然后遍历一遍，全部存起来，然后找最大值。  
  想办法优化的思路就是，从map改成单个int值。  
  问题是如何保证单向：然后想到中序遍历特性就迎刃而解了。  
    
tags:    
  -  树  
  -  中序遍历  
