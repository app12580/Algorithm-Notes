### description    
  You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.  
    
  Define a pair (u,v) which consists of one element from the first array and one element from the second array.  
    
  Find the k pairs (u1,v1),(u2,v2) ...(uk,vk) with the smallest sums.  
    
  Example 1:  
    
  Input: nums1 = [1,7,11], nums2 = [2,4,6], k = 3  
  Output: [[1,2],[1,4],[1,6]]   
  Explanation: The first 3 pairs are returned from the sequence:   
               [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]  
  Example 2:  
    
  Input: nums1 = [1,1,2], nums2 = [1,2,3], k = 2  
  Output: [1,1],[1,1]  
  Explanation: The first 2 pairs are returned from the sequence:   
               [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]  
  Example 3:  
    
  Input: nums1 = [1,2], nums2 = [3], k = 3  
  Output: [1,3],[2,3]  
  Explanation: All possible pairs are returned from the sequence: [1,3],[2,3]  
### solution    
```    
  
//方法一： 丢人的效率。。。  
Runtime: 359 ms, faster than 5.27% of Java online submissions for Find K Pairs with Smallest Sums.  
Memory Usage: 41 MB, less than 66.67% of Java online submissions for Find K Pairs with Smallest Sums.  
  
  
  class Solution {  
      public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {  
          List<List<Integer>> res = new ArrayList<>();  
          if(nums1.length == 0 || nums2.length == 0) return res;  
    
          int idx1 = 0;  
          int idx2 = 0;  
          int len1 = nums1.length;  
          int len2 = nums2.length;  
          boolean[][] dp = new boolean[len1][len2];  
          for(int i = 0; i < Math.min(len1 * len2, k); i++) {  
              List<Integer> list = new ArrayList<>();  
              list.add(nums1[idx1]);  
              list.add(nums2[idx2]);  
              res.add(list);  
              dp[idx1][idx2] = true;  
              //然后移动两个索引  
              int[] next = helper(nums1, nums2, dp, i + 1);  
              idx1 = next[0];  
              idx2 = next[1];  
          }  
          return res;  
      }  
    
      private int[] helper(int[] nums1, int[] nums2, boolean[][] dp, int k) {  
          //两个索引的和要小于等于k  
          int minSum = Integer.MAX_VALUE;  
          int s1 = 0;  
          int s2 = 0;  
          for(int i = 0; i <= Math.min(nums1.length - 1, k); i++) {  
              for(int j = 0; j <= Math.min(nums2.length - 1, k - i); j++) {  
                  if(dp[i][j]) continue;  
                  if(nums1[i] + nums2[j] < minSum) {  
                      s1 = i;  
                      s2 = j;  
                      minSum = nums1[i] + nums2[j];  
                  }  
              }  
          }  
          return new int[]{s1, s2};  
      }  
  }  
    
  // 方法二： 优化路线  
  class Solution {  
      public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {  
           PriorityQueue<Tuple> pq = new PriorityQueue<Tuple>();  
          int m = nums1.length, n = nums2.length;  
          List<List<Integer>> res = new ArrayList<>();  
          if(nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0 || k <= 0) return res;  
          for(int j = 0; j <= n-1; j++) pq.offer(new Tuple(0, j, nums1[0]+nums2[j]));  
          for(int i = 0; i < Math.min(k, m *n); i++) {  
              Tuple t = pq.poll();  
              List<Integer> tmp = new ArrayList<>();  
              tmp.add(nums1[t.x]);  
              tmp.add(nums2[t.y]);  
              res.add(tmp);  
              if(t.x == m - 1) continue;  
              pq.offer(new Tuple (t.x + 1, t.y, nums1[t.x + 1] + nums2[t.y]));  
          }  
          return res;         
      }  
  }  
    
  class Tuple implements Comparable<Tuple> {  
      int x, y, val;  
      public Tuple (int x, int y, int val) {  
          this.x = x;  
          this.y = y;  
          this.val = val;  
      }  
        
      @Override  
      public int compareTo (Tuple that) {  
          return this.val - that.val;  
      }  
  }  
```    
    
### 个人解读    
  双指针问题，重点在于移动哪一个指针：  
  假定ij都有右移空间的情况，比较差值，然后再移动。  
  然而不对啊，双指针的回溯很成问题，还不如用动手原则，全算一遍再说。。。  
  回溯的时候用一个dp[][]来标记可以么。。  
  方法一相当于暴力法了。问题在于没有比较新加入的可能性，而是把所有的都试一遍  
    
  方法二的路线：  
  xxxxxxxxxxxxxxxxxxxxx  
    
  xxxxxxxxxxxxxxxxxxxxx  
  x  
    
  xxxxxxxxxxxxxxxxxxxxx  
  xy  
  y  
    
  每次新加入一个节点，只需要在priorityQueue里面，新加入一个下面的节点即可。很巧妙，因为下边的需要比较，而右边的肯定大于右上的。  
tags:    
  -  优先队列 PriorityQueue  
  -  重点数学  
