### description    
  Given the root of a tree, you are asked to find the most frequent subtree sum. The subtree sum of a node is defined as the sum of all the node values formed by the subtree rooted at that node (including the node itself). So what is the most frequent subtree sum value? If there is a tie, return all the values with the highest frequency in any order.  
    
  Examples 1  
  Input:  
    
    5  
   /  \  
  2   -3  
  return [2, -3, 4], since all the values happen only once, return all of them in any order.  
  Examples 2  
  Input:  
    
    5  
   /  \  
  2   -5  
  return [2], since 2 happens twice, however -5 only occur once.  
  Note: You may assume the sum of values in any subtree is in the range of 32-bit signed integer.  
### solution    
```    
  
Runtime: 3 ms, faster than 100.00% of Java online submissions for Most Frequent Subtree Sum.  
Memory Usage: 39.2 MB, less than 80.49% of Java online submissions for Most Frequent Subtree Sum.  
  
  
  class Solution {  
       private int max = 0;  
      private Map<Integer, Integer> map = new HashMap<>();  
      private List<Integer> list = new ArrayList<>();  
    
    
      public int[] findFrequentTreeSum(TreeNode root) {  
          if(root == null) return new int[0];  
          helper(root);  
          int[] arr = new int[list.size()];  
          int ii =0;  
          for(int num: list) {  
              arr[ii++] = num;  
          }  
          return arr;  
      }  
    
      private int helper(TreeNode root) {  
          int sum = root.val;  
          if(root.left != null) {  
              sum += helper(root.left);  
          }  
          if(root.right != null) {  
              sum += helper(root.right);  
          }  
          int count = map.getOrDefault(sum, 0) + 1;  
          if(count > max) {  
              list.clear();  
              list.add(sum);  
              max = count;  
          } else if(count == max) {  
              list.add(sum);  
          }  
          map.put(sum, count);  
          return sum;  
      }  
  }  
```    
    
### 个人解读    
  使用递归，省去很多思路，然后把东西全放进全局变量里面  
    
tags:    
  -  树  
  -  递归  
  -  全局变量  
