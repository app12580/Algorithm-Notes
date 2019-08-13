### description    
  Given the coordinates of four points in 2D space, return whether the four points could construct a square.  
    
  The coordinate (x,y) of a point is represented by an integer array with two integers.  
    
  Example:  
    
  Input: p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,1]  
  Output: True  
     
    
  Note:  
    
  All the input integers are in the range [-10000, 10000].  
  A valid square has four equal sides with positive length and four equal angles (90-degree angles).  
  Input points have no order.  
### solution    
```    
Runtime: 1 ms, faster than 81.70% of Java online submissions for Valid Square.  
Memory Usage: 35 MB, less than 75.00% of Java online submissions for Valid Square.  
  
  class Solution {  
       public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {  
          Map<Integer, Integer> map = new HashMap<>();  
          int[] arr = new int[6];  
          int index = 0;  
          arr[index++] = getDis(p1, p2);  
          arr[index++] = getDis(p1, p3);  
          arr[index++] = getDis(p1, p4);  
          arr[index++] = getDis(p2, p3);  
          arr[index++] = getDis(p2, p4);  
          arr[index++] = getDis(p3, p4);  
          Arrays.sort(arr);  
          if(!(arr[0] == arr[1] && arr[1] == arr[2] && arr[2] == arr[3])) return false;  
          if(arr[3] == arr[4] || arr[4] != arr[5]) return false;  
          return arr[0] * 2 == arr[4];  
      }  
    
      public int getDis(int[] p1, int[] p2) {  
          return (p1[0] - p2[0])*(p1[0] - p2[0]) +(p1[1] - p2[1])*(p1[1] - p2[1]) ;  
      }  
  }  
```    
    
### 个人解读    
  应该是一个纯数学问题。  
    
  两个点的关系，要么是相邻的，要么是对角线。  
    
  然后想到从整体来看，C42一共六条边，4个1和2个根号2，通过平方去判断。  
    
tags:    
  -  数学  
