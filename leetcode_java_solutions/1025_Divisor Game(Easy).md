### description    
  Alice and Bob take turns playing a game, with Alice starting first.  
    
  Initially, there is a number N on the chalkboard.  On each player's turn, that player makes a move consisting of:  
    
  Choosing any x with 0 < x < N and N % x == 0.  
  Replacing the number N on the chalkboard with N - x.  
  Also, if a player cannot make a move, they lose the game.  
    
  Return True if and only if Alice wins the game, assuming both players play optimally.  
    
     
    
  Example 1:  
    
  Input: 2  
  Output: true  
  Explanation: Alice chooses 1, and Bob has no more moves.  
  Example 2:  
    
  Input: 3  
  Output: false  
  Explanation: Alice chooses 1, Bob chooses 1, and Alice has no more moves.  
     
    
  Note:  
    
  1 <= N <= 1000  
### solution    
```    
  class Solution {  
      public boolean divisorGame(int N) {  
          return N % 2 == 0;  
      }  
  }  
```    
    
### 个人解读    
  1	  2	    3	4	5	6	7	8	9  
  输  赢	输	赢	输	赢	输	赢	输  
  
  首先1是输的，因为2可以直达1，所以2是赢的。  
  重点在于1是最小的台阶。  
    
  因为是先手，所以需要给对方留下一个输的结局的时候，我就赢了。  
  想赢很简单，给自己造一个必赢的结果，然而判断输则需要遍历所有情况。  
    
  1是输，2是赢，接下来3是输，4是赢，  
  因此：只要某一位是输，那么它的下一位一定是赢。  
  对于某一位赢的下一位，因为他前面所有的输的都是奇数位，而当前(赢的下一位)的也是奇数，奇数不可能到达奇数。  
    
  这题目的证明过程很容易出现想当然的情况。  
    
tags:    
  -  重点数学  
  -  想当然  
