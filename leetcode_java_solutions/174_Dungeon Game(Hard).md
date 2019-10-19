### description  
  The demons had captured the princess (P) and imprisoned her in the bottom-right corner of a dungeon. The dungeon consists of M x N rooms laid out in a 2D grid. Our valiant knight (K) was initially positioned in the top-left room and must fight his way through the dungeon to rescue the princess.
  
  The knight has an initial health point represented by a positive integer. If at any point his health point drops to 0 or below, he dies immediately.
  
  Some of the rooms are guarded by demons, so the knight loses health (negative integers) upon entering these rooms; other rooms are either empty (0's) or contain magic orbs that increase the knight's health (positive integers).
  
  In order to reach the princess as quickly as possible, the knight decides to move only rightward or downward in each step.
  
   
  
  Write a function to determine the knight's minimum initial health so that he is able to rescue the princess.
  
  For example, given the dungeon below, the initial health of the knight must be at least 7 if he follows the optimal path RIGHT-> RIGHT -> DOWN -> DOWN.
  
  -2 (K)	-3	3
  -5	-10	1
  10	30	-5 (P)
   
  
  Note:
  
  The knight's health has no upper bound.
  Any room can contain threats or power-ups, even the first room the knight enters and the bottom-right room where the princess is imprisoned.
### solution  
```  
  
```  
  
### 个人解读  
  感觉就是一个简单的DP问题，然而并不行，因为看的不是最终结果而是要看中间进度。
  有想过弄一个int[N]的数组，来描述 每种路径上的最大扣血
  
  本题目的关键点在于：在DP的过程中会丢失某条路线上的最大损失量。(也真是佩服某些博主了，扯了一堆没用的DFS，最后直接来个逆向DP。。。)
  
  一开始想着，难道真要逼着用DFS么。。。
  
  后来发现关键点在于逆向思维，从最后出发。
  问题关键：凭什么可以逆向思维？？都是加减法。
  核心：对于回血的处理方式不同。
  举个例子：如果某一格子回血100000+血，如果是从终点出发的话，那么此时就已经知道这10w血有多少是浪费的了。而如果从起点出发，那么此时就不知道这10w够不够用，因此必须保留住这个变量。
  
tags:  
  -  
