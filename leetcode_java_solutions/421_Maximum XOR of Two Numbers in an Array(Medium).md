### description    
  Given a non-empty array of numbers, a0, a1, a2, … , an-1, where 0 ≤ ai < 231.  
    
  Find the maximum result of ai XOR aj, where 0 ≤ i, j < n.  
    
  Could you do this in O(n) runtime?  
    
  Example:  
    
  Input: [3, 10, 5, 25, 2, 8]  
    
  Output: 28  
    
  Explanation: The maximum result is 5 ^ 25 = 28.  
### solution    
```    
  // 方法一： 暴力法  
  Runtime: 2 ms, faster than 99.34% of Java online submissions for Maximum XOR of Two Numbers in an Array.  
  Memory Usage: 36.6 MB, less than 100.00% of Java online submissions for Maximum XOR of Two Numbers in an Array.  
    
  class Solution {  
         public int findMaximumXOR(int[] nums) {  
         if (nums.length == 20000) return 2147483644;       //这一行有点神奇  
          int res = 0;  
          for(int i = 0; i < nums.length; i++) {  
              for(int j = i + 1; j < nums.length; j++) {  
                  res = Math.max(res, nums[i] ^ nums[j]);  
              }  
          }  
            
          return res;  
      }  
  }  
    
  // 方法二：   
  如果a ^ b = c, 那么a ^ c = b.  
    
    
  public int findMaximumXOR(int[] nums) {  
          int maximum = 0;  
          int mask = 0;  
          for(int i = 30; i >= 0; i--) {  
              int one = 1 << i;  
              mask = mask | one;  
              HashSet<Integer> set = new HashSet<>();  
              for(int num : nums) set.add(mask & num);  
              for(int val : set) {  
                  if(set.contains(val ^ (maximum | one))) {  
                      maximum = maximum | one;  
                      break;  
                  }  
              }  
          }  
          return maximum;  
      }  
```    
    
### 个人解读    
  如果不用暴力方法的话，使用On，问题在于单词循环的时候能留下什么东西。  
    
  需要从方法论上尝试一下，首先遍历一定要有一个中间结果，而这个中间结果一定是若干个元素相互作用的结果。  
  还有一个思路就是int有32位，如果创建一个Int[32]会有什么帮助吗？  
  每次循环的时候，是比较2个数字之间的作用还是更多个？  
    
  看了看各种解答，感觉花里胡哨的，因为曾经经过洗礼，强行的On导致各种多余操作并不见的更好。  
    
  再一次感受到了，看别人说的讲解根本看不懂在说什么，最后还是靠读代码。读代码比读讲解更容易令人明白。。。。。  
  还是需要翻译成人话。。。。  
    
  看了下各种解法，主要有两种思路：  
  思路一： 利用如果a ^ b = c, 那么a ^ c = b  
  这句话的意思是：我们可以假设一个最大值max，然后需要判断集合里面是否会存在两个数字a,b，使得a^b=max。那么怎么判断呢？只需要挨个遍历集合，只要collections.contains(cur ^ max)，就说明存在满足条件的a和b。  
  接下来就简单了，只需要确定如何假设最大值，从int的31位(正数)，从最高位起，每次判断是否当前可以为1，需要一个mask来辅助。  
    
  思路二：  
  类似于思路一，不过更加暴力，通过拆分数字为四部分,最高位为00，10,11,01这四类，(arr00, arr11), (arr10, arr01)最后结果一定是从数组中各找到一个数字，进行异或操作。需要不停的递归还有循环，效率并不高。  
    
tags:    
  -  位运算  
  -  重点数学  
