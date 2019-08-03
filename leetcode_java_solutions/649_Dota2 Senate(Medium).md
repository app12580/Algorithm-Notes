### description    
  In the world of Dota2, there are two parties: the Radiant and the Dire.  
    
  The Dota2 senate consists of senators coming from two parties. Now the senate wants to make a decision about a change in the Dota2 game. The voting for this change is a round-based procedure. In each round, each senator can exercise one of the two rights:  
    
  Ban one senator's right:  
  A senator can make another senator lose all his rights in this and all the following rounds.  
  Announce the victory:  
  If this senator found the senators who still have rights to vote are all from the same party, he can announce the victory and make the decision about the change in the game.  
     
    
  Given a string representing each senator's party belonging. The character 'R' and 'D' represent the Radiant party and the Dire party respectively. Then if there are n senators, the size of the given string will be n.  
    
  The round-based procedure starts from the first senator to the last senator in the given order. This procedure will last until the end of voting. All the senators who have lost their rights will be skipped during the procedure.  
    
  Suppose every senator is smart enough and will play the best strategy for his own party, you need to predict which party will finally announce the victory and make the change in the Dota2 game. The output should be Radiant or Dire.  
    
  Example 1:  
    
  Input: "RD"  
  Output: "Radiant"  
  Explanation: The first senator comes from Radiant and he can just ban the next senator's right in the round 1.   
  And the second senator can't exercise any rights any more since his right has been banned.   
  And in the round 2, the first senator can just announce the victory since he is the only guy in the senate who can vote.  
     
    
  Example 2:  
    
  Input: "RDD"  
  Output: "Dire"  
  Explanation:   
  The first senator comes from Radiant and he can just ban the next senator's right in the round 1.   
  And the second senator can't exercise any rights anymore since his right has been banned.   
  And the third senator comes from Dire and he can ban the first senator's right in the round 1.   
  And in the round 2, the third senator can just announce the victory since he is the only guy in the senate who can vote.  
     
    
  Note:  
    
  The length of the given string will in the range [1, 10,000].  
### solution    
```    
  
Runtime: 3 ms, faster than 99.34% of Java online submissions for Dota2 Senate.  
Memory Usage: 36.5 MB, less than 100.00% of Java online submissions for Dota2 Senate.  
  
  
  class Solution {  
     private String D = "Dire";  
      private String R = "Radiant";  
      public String predictPartyVictory(String senate) {  
          return helper(senate, 0);  
      }  
    
      // count D的个数 ，如果是负数，那么就是R的个数  
      public String helper(String senate, int count) {  
            
          int len = senate.length();  
          if(count >= len) {  
              return D;  
          } else if(count <= -len) {  
              return R;  
          }  
          StringBuilder builder = new StringBuilder();  
    
          for(int i = 0; i < len; i++) {  
              char cur = senate.charAt(i);  
              if(cur == 'D') {  
                  if(count >= 0) {  
                      builder.append('D');  
                      count++;  
                  } else {  
                      count++;  
                  }  
              } else {  
                  if(count <= 0) {  
                      builder.append('R');  
                      count--;  
                  } else {  
                      count--;  
                  }  
              }  
          }  
    
          return helper(builder.toString(), count);  
      }  
  }  
```    
    
### 个人解读    
  游戏规则很简单，只需要废掉最近的敌方阵营的人。  
  代码上需要通过设置好变量来描述，同时也需要做好终止情况。  
    
  可以使用递归，减少思路支线。  
  然后敲代码的时候，因为原始的位置关系很重要，同时如果一个节点去否决很早的  
    
tags:    
  -  数学  
  -  递归  
  -  辅助函数  
