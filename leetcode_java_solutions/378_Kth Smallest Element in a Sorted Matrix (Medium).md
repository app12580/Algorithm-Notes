### description    
  Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.  
    
  Note that it is the kth smallest element in the sorted order, not the kth distinct element.  
    
  Example:  
    
  matrix = [  
     [ 1,  5,  9],  
     [10, 11, 13],  
     [12, 13, 15]  
  ],  
  k = 8,  
    
  return 13.  
  Note:   
  You may assume k is always valid, 1 ≤ k ≤ n2.  
### solution    
```    
  二分查找解法：  
    
  public int kthSmallest(int[][] matrix, int k) {  
      int m = matrix.length, n = matrix[0].length;  
      int lo = matrix[0][0], hi = matrix[m - 1][n - 1];  
      while (lo <= hi) {  
          int mid = lo + (hi - lo) / 2;  
          int cnt = 0;  
          for (int i = 0; i < m; i++) {  
              for (int j = 0; j < n && matrix[i][j] <= mid; j++) {  
                  cnt++;  
              }  
          }  
          if (cnt < k) lo = mid + 1;  
          else hi = mid - 1;  
      }  
      return lo;  
  }  
  堆解法：  
    
  public int kthSmallest(int[][] matrix, int k) {  
      int m = matrix.length, n = matrix[0].length;  
      PriorityQueue<Tuple> pq = new PriorityQueue<Tuple>();  
      for(int j = 0; j < n; j++) pq.offer(new Tuple(0, j, matrix[0][j]));  
      for(int i = 0; i < k - 1; i++) { // 小根堆，去掉 k - 1 个堆顶元素，此时堆顶元素就是第 k 的数  
          Tuple t = pq.poll();  
          if(t.x == m - 1) continue;  
          pq.offer(new Tuple(t.x + 1, t.y, matrix[t.x + 1][t.y]));  
      }  
      return pq.poll().val;  
  }  
    
  class Tuple implements Comparable<Tuple> {  
      int x, y, val;  
      public Tuple(int x, int y, int val) {  
          this.x = x; this.y = y; this.val = val;  
      }  
    
      @Override  
      public int compareTo(Tuple that) {  
          return this.val - that.val;  
      }  
  }  
```    
    
### 个人解读    
  看了两个解法，都是用的类似于遍历全排序的解法，方法一：所谓的二分法也只是从[最小值, 最大值]的区间里，不停的二分法，然后再不停的重复去遍历整个矩阵，举个简单的例子，[1,10]里面选了5，然后遍历了20个元素，然后选了7，又TM遍历把这20个元素再遍历一遍，与其这样，还不如第一次就全排序成一个数组(或其他数据结构)，然后转换成数组的Kth问题。  
  方法二：利用PriorityQueue的全排序问题  
    
  很多时候不是题没思路，而是总想着用O(n)去解决，结果想半天想不出来，看到的参考答案是O(NlgN)的结果  
    
tags:    
  -  数组  
  -  Kth  
    
