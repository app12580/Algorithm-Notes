### description    
  Given a non-empty special binary tree consisting of nodes with the non-negative value, where each node in this tree has exactly two or zero sub-node. If the node has two sub-nodes, then this node's value is the smaller value among its two sub-nodes. More formally, the property root.val = min(root.left.val, root.right.val) always holds.  
    
  Given such a binary tree, you need to output the second minimum value in the set made of all the nodes' value in the whole tree.  
    
  If no such second minimum value exists, output -1 instead.  
    
  Example 1:  
    
  Input:   
      2  
     / \  
    2   5  
       / \  
      5   7  
    
  Output: 5  
  Explanation: The smallest value is 2, the second smallest value is 5.  
     
    
  Example 2:  
    
  Input:   
      2  
     / \  
    2   2  
    
  Output: -1  
  Explanation: The smallest value is 2, but there isn't any second smallest value.  
    
  找出二叉树的第二小的数字，如果没有，返回-1  
  二叉树特点： 父节点小于等于子节点。每个节点的子节点数量只能为 2 或 0。  
### solution    
```    
  //方法一：   
  class Solution {  
      public int findSecondMinimumValue(TreeNode root) {  
           if(root == null) {  
              return -1;  
          }  
          Queue<TreeNode> queue = new LinkedList<>();  
          queue.offer(root);  
          int firstMin = root.val;  
          int secondMin = -1;  
          while(!queue.isEmpty()) {  
              TreeNode poll = queue.poll();  
              int val = poll.val;  
              if(val != firstMin) {  
                  if(secondMin == -1) {  
                      secondMin = val;  
                  } else {  
                      if(secondMin > val) {  
                          secondMin = val;  
                      }  
                  }  
              } else {  
                  if(poll.left != null) {  
                      queue.offer(poll.left);  
                      queue.offer(poll.right);  
                  }  
              }  
          }  
          return secondMin;  
      }  
  }  
    
    
  //方法二： 递归  
  public int findSecondMinimumValue(TreeNode root) {  
      if (root == null) return -1;  
      if (root.left == null && root.right == null) return -1;  
      int leftVal = root.left.val;  
      int rightVal = root.right.val;  
      if (leftVal == root.val) leftVal = findSecondMinimumValue(root.left);  
      if (rightVal == root.val) rightVal = findSecondMinimumValue(root.right);  
      if (leftVal != -1 && rightVal != -1) return Math.min(leftVal, rightVal);  
      if (leftVal != -1) return leftVal;  
      return rightVal;  
  }  
```    
    
### 个人解读    
  思路一： 利用二叉树的特殊性，找结果的方向性可知，所以放弃递归法。创建一个队列，把所有不等于根节点值的节点放进去，然后挨个比较。属于有目的性的，从根节点去筛选想要的结果。（层次遍历）  
  思路二： 递归思路。根据left是否等于root，来关联f(root)和f(root.left)的关系。f(root.left)可以理解成比左边比大于root的最小值。  
tags:    
  -  树  
  -  层次遍历    
  -  递归    
