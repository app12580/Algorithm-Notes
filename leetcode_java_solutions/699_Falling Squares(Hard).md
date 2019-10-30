### description    
  On an infinite number line (x-axis), we drop given squares in the order they are given.  
    
  The i-th square dropped (positions[i] = (left, side_length)) is a square with the left-most point being positions[i][0] and sidelength positions[i][1].  
    
  The square is dropped with the bottom edge parallel to the number line, and from a higher height than all currently landed squares. We wait for each square to stick before dropping the next.  
    
  The squares are infinitely sticky on their bottom edge, and will remain fixed to any positive length surface they touch (either the number line or another square). Squares dropped adjacent to each other will not stick together prematurely.  
    
     
  Return a list ans of heights. Each height ans[i] represents the current highest height of any square we have dropped, after dropping squares represented by positions[0], positions[1], ..., positions[i].  
    
  Example 1:  
    
  Input: [[1, 2], [2, 3], [6, 1]]  
  Output: [2, 5, 5]  
  Explanation:  
  After the first drop of positions[0] = [1, 2]: _aa _aa ------- The maximum height of any square is 2.  
    
  After the second drop of positions[1] = [2, 3]: __aaa __aaa __aaa _aa__ _aa__ -------------- The maximum height of any square is 5. The larger square stays on top of the smaller square despite where its center of gravity is, because squares are infinitely sticky on their bottom edge.  
    
  After the third drop of positions[1] = [6, 1]: __aaa __aaa __aaa _aa _aa___a -------------- The maximum height of any square is still 5. Thus, we return an answer of [2, 5, 5].  
    
     
    
     
  Example 2:  
    
  Input: [[100, 100], [200, 100]]  
  Output: [100, 100]  
  Explanation: Adjacent squares don't get stuck prematurely - only their bottom edge can stick to surfaces.  
     
    
  Note:  
    
  1 <= positions.length <= 1000.  
  1 <= positions[i][0] <= 10^8.  
  1 <= positions[i][1] <= 10^6.  
### solution    
```    
  
// 方法： O(N^2)暴力法  
Runtime: 23 ms, faster than 63.46% of Java online submissions for Falling Squares.  
Memory Usage: 40 MB, less than 100.00% of Java online submissions for Falling Squares.  
  
  class Solution {  
      class Square {  
          int start;  
          int end;  
          int height;  
    
          public Square(int start, int end, int height) {  
              this.start = start;  
              this.end = end;  
              this.height = height;  
          }  
      }  
      public List<Integer> fallingSquares(int[][] positions) {  
          List<Integer> res = new ArrayList<>();  
          List<Square> list = new ArrayList<>();  
          int max = 0;  
          for(int[] p: positions) {  
              Square square = new Square(p[0], p[0] + p[1] - 1, p[1]);  
              max = Math.max(max, insert(list, square));  
              res.add(max);  
          }  
          return res;  
    
      }  
    
      private int insert(List<Square> list, Square cur) {  
          int max = cur.height;  
          for(Square s: list) {  
              if(s.start <= cur.end && cur.start <= s.end) {  
                  max = Math.max(max, cur.height + s.height);  
              }  
          }  
          cur.height = max;  
          list.add(cur);  
          return max;  
      }  
  }  
```    
  
    
### 个人解读    
  需要用一个数据结构存储当前高度信息，key是区间范围，value是高度。然后每次掉落时候，获取当前掉落方块的全部交叉区间，从中获取最大值，然后还需要更新数据结构信息。  
  一开始想着是TreeMap，但是如何获取全部交叉区间遇到些问题  
    
  难点一：如何通过O(NlogN)获取与当前交叉的方块  
  难点二：获取新的后，如何优化原来的数据高度  
  这样处理后，会获取O(NlogN)的复杂度。入股  
    
  总结：  
  自降要求，先做出来了再说。  
    
tags:    
  -  区间  
  -  暴力法  
