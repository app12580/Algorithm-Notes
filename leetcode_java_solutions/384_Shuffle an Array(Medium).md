### description    
  Shuffle a set of numbers without duplicates.  
    
  Example:  
    
  // Init an array with set 1, 2, and 3.  
  int[] nums = {1,2,3};  
  Solution solution = new Solution(nums);  
    
  // Shuffle the array [1,2,3] and return its result. Any permutation of [1,2,3] must equally likely to be returned.  
  solution.shuffle();  
    
  // Resets the array back to its original configuration [1,2,3].  
  solution.reset();  
    
  // Returns the random shuffling of array [1,2,3].  
  solution.shuffle();  
### solution    
```    
  //方法一 随机数  
  不通过，搞不懂原因。。。  
  class Solution {  
    
      private int[] origin;  
      private int[] shuffle;  
    
      public Solution(int[] nums) {  
          origin = nums;  
          shuffle = new int[nums.length];  
          for(int i = 0; i < origin.length; i++) {  
              shuffle[i] = origin[i];  
          }  
      }  
    
      /** Resets the array to its original configuration and return it. */  
      public int[] reset() {  
          for(int i = 0; i < origin.length; i++) {  
              shuffle[i] = origin[i];  
          }  
          return Arrays.copyOf(shuffle, shuffle.length);  
      }  
    
      /** Returns a random shuffling of the array. */  
      public int[] shuffle() {  
          int len = origin.length;  
          Random random = new Random();  
          for(int i = 0; i < len; i++) {  
              int k = random.nextInt(len);  
              int j = (i + k) % len;  
              if(i == j) j = (j + 1) % len;  
              shuffle[i] = shuffle[i] ^ shuffle[j];  
              shuffle[j] = shuffle[i] ^ shuffle[j];  
              shuffle[i] = shuffle[i] ^ shuffle[j];  
          }  
          return Arrays.copyOf(shuffle, shuffle.length);  
      }  
    
  }  
    
    
  //方法二：   
  class Solution {  
    
       
      private int[] origin;  
      private Random random = new Random();  
    
      public Solution(int[] nums) {  
          origin = nums;  
      }  
    
      /** Resets the array to its original configuration and return it. */  
      public int[] reset() {  
          return origin;  
      }  
    
      /** Returns a random shuffling of the array. */  
      public int[] shuffle() {  
          int len = origin.length;  
          int[] shuffle = origin.clone();  
          for(int i = 1; i < len; i++) {  
              int k = random.nextInt(i + 1);  
              // int k = random.nextInt(i);    
              swap(shuffle, i, k);  
          }  
          return shuffle;  
      }  
        
      private void swap(int[] a, int i, int j) {  
          int t = a[i];  
          a[i] = a[j];  
          a[j] = t;  
      }  
  }  
    
  // 方法三： 改来改去，最后终于TM通过了  
  class Solution {  
    
      private int[] origin;  
      Random random = new Random();  
    
    
      public Solution(int[] nums) {  
          origin = nums;  
      }  
    
      /** Resets the array to its original configuration and return it. */  
      public int[] reset() {  
          return origin;  
      }  
    
      /** Returns a random shuffling of the array. */  
      public int[] shuffle() {  
          int len = origin.length;  
          int[] shuffle = origin.clone();  
          for(int i = 0; i < len; i++) {  
              int k = random.nextInt(len);  
              int j = (i + k) % len;  
              if(i == j) continue;  
              shuffle[i] = shuffle[i] ^ shuffle[j];  
              shuffle[j] = shuffle[i] ^ shuffle[j];  
              shuffle[i] = shuffle[i] ^ shuffle[j];  
          }  
          return shuffle;  
      }  
  }  
   
```    
    
### 个人解读    
  题目描述有严重缺失，就是本题的答案校验应该有个部分：通过大量数据实验，来证明一定是随机的，而不是某一部分会出现偏重。  
  这样就会导致稍微有点不公平待遇就不会通过。  
  必须保证每一个节点，每一次操作都要是平等的。  
    
tags:    
  -  重点数学  
  -  随机数  
