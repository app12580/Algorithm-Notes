### description    
  On a broken calculator that has a number showing on its display, we can perform two operations:  
    
  Double: Multiply the number on the display by 2, or;  
  Decrement: Subtract 1 from the number on the display.  
  Initially, the calculator is displaying the number X.  
    
  Return the minimum number of operations needed to display the number Y.  
    
     
    
  Example 1:  
    
  Input: X = 2, Y = 3  
  Output: 2  
  Explanation: Use double operation and then decrement operation {2 -> 4 -> 3}.  
  Example 2:  
    
  Input: X = 5, Y = 8  
  Output: 2  
  Explanation: Use decrement and then double {5 -> 4 -> 8}.  
  Example 3:  
    
  Input: X = 3, Y = 10  
  Output: 3  
  Explanation:  Use double, decrement and double {3 -> 6 -> 5 -> 10}.  
  Example 4:  
    
  Input: X = 1024, Y = 1  
  Output: 1023  
  Explanation: Use decrement operations 1023 times.  
     
    
  Note:  
    
  1 <= X <= 10^9  
  1 <= Y <= 10^9  
### solution    
```    
  class Solution {  
      public int brokenCalc(int X, int Y) {  
           if(X >= Y) return X - Y;  
           return 1 + Y % 2 + brokenCalc(X, (Y + 1) / 2);  
      }  
  }  
```    
    
### 个人解读    
  主要是在DFS和递归中徘徊。  
  感觉递归更容易一些，而且递归可以减少很多思路回路。    
    
  需要通过数学方法，不能无脑穷举。  
  直觉上是和Y的因式分解有关。然而并没有什么卵用。。。  
    
  首先递归是肯定的。  
  啊啊啊啊啊啊，放弃思考了。。  
    
  代码堪比那个什么格雷码的。。。。。  
  參考：https://leetcode.com/problems/broken-calculator/discuss/234484/JavaC%2B%2BPython-Change-Y-to-X-in-1-Line  
    
    
  方法：逆向思维  
  思路  
    
  除了对 X 执行乘 2 或 减 1 操作之外，我们也可以对 Y 执行除 2（当 Y 是偶数时）或者加 1 操作。  
    
  这样做的动机是我们可以总是贪心地执行除 2 操作：  
    
  当 Y 是偶数，如果先执行 2 次加法操作，再执行 1 次除法操作，我们可以通过先执行 1 次除法操作，再执行 1 次加法操作以使用更少的操作次数得到相同的结果 [(Y+2) / 2 vs Y/2 + 1]。  
    
  当 Y 是奇数，如果先执行 3 次加法操作，再执行 1 次除法操作，我们可以将其替代为顺次执行加法、除法、加法操作以使用更少的操作次数得到相同的结果 [(Y+3) / 2 vs (Y+1) / 2 + 1]。  
   
  总结：  
  为什么要逆向思维，很多博主都没有说明白。  
  关键点在于，逆向思维是从大到小，加减法在大的一端那里效率低，因此想到大的那一段尽可能乘除。  
  所以核心点在于小的那一段要加减多少？？从小到大的话，会迷茫，不知道减到多少合适，减到多少才会恰好在翻倍只是更接近目标。  
  换句话说，本题目要尽可能的多用乘除，少用加减，然后又发现在大的那一段乘除更好，因此！！！就让大的那一段无脑乘除即可。    
    
    
  //内存溢出  java.lang.StackOverflowError  
  Last executed input   1  
  expected 1000000000  
  ```  
  class Solution {  
      Set<Integer> set = new HashSet<>();  
      {  
          set.add(0);  
      }  
      public int brokenCalc(int X, int Y) {  
          if(set.contains(X)) return Integer.MAX_VALUE;  
          set.add(X);  
          if(X == Y) return 0;  
          if(X > Y) return X - Y;  
          int min = Math.min(brokenCalc(X * 2, Y), brokenCalc(X - 1, Y));  
          // int min = Math.min(brokenCalc(X - 1, Y), brokenCalc(X * 2, Y));  
          // 这一行先后顺序很有影响  
          if(min == Integer.MAX_VALUE) return min;  
          return 1 + min;  
      }  
  }  
  ```  
    
tags:    
  -  重点数学  
  -  数字逻辑  
