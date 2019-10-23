### description      
  You are given an integer array nums and you have to return a new counts array. The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].    
      
  Example:    
      
  Input: [5,2,6,1]    
  Output: [2,1,1,0]     
  Explanation:    
  To the right of 5 there are 2 smaller elements (2 and 1).    
  To the right of 2 there is only 1 smaller element (1).    
  To the right of 6 there is 1 smaller element (1).    
  To the right of 1 there is 0 smaller element.    
### solution      
```      
// 方法一： 构建一个BST    
Runtime: 4 ms, faster than 98.06% of Java online submissions for Count of Smaller Numbers After Self.    
Memory Usage: 39.5 MB, less than 97.22% of Java online submissions for Count of Smaller Numbers After Self.    
    
  public class Solution {    
      class Node {    
          Node left, right;    
          int val, sum, dup = 1;    
          public Node(int v, int s) {    
              val = v;    
              sum = s;    
          }    
      }    
      public List<Integer> countSmaller(int[] nums) {    
          Integer[] ans = new Integer[nums.length];    
          Node root = null;    
          for (int i = nums.length - 1; i >= 0; i--) {    
              root = insert(nums[i], root, ans, i, 0);    
          }    
          return Arrays.asList(ans);    
      }    
        
      // 通过return Node进行拼接  
      // 大多数时候，这个return是没有意义的  
      private Node insert(int num, Node node, Integer[] ans, int i, int preSum) {    
          if (node == null) {    
              node = new Node(num, 0);    
              ans[i] = preSum;    
          } else if (node.val == num) {    
              node.dup++;    
              ans[i] = preSum + node.sum;    
          } else if (node.val > num) {    
              node.sum++;    
              node.left = insert(num, node.left, ans, i, preSum);    
          } else {    
              node.right = insert(num, node.right, ans, i, preSum + node.dup + node.sum);    
          }    
          return node;    
      }    
  }    
```      
      
### 个人解读      
  思路一：    
  从右边往左开始遍历，用一个数据结构存储所有的数据，然后每次去获取比当前小的值。    
  TreeMap，key是存进去的值，value是比key小的数字有多少。但是不行啊，每次put后需要更新全部ceil节点。    
      
  总结：    
  BST是排序类数据结构中，最快的一个，也是PriorityQueue的底层结构    
      
      
tags:      
  -  数据结构    
