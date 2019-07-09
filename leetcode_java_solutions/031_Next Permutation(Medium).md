### description    
  Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.  
    
  If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).  
    
  The replacement must be in-place and use only constant extra memory.  
    
  Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.  
    
  1,2,3 → 1,3,2  
  3,2,1 → 1,2,3  
  1,1,5 → 1,5,1  
    
  下一个排列  
### solution    
```    
  Runtime: 1 ms, faster than 94.80% of Java online submissions for Next Permutation.  
  Memory Usage: 41.2 MB, less than 38.58% of Java online submissions for Next Permutation.  
    
  class Solution {  
      public void nextPermutation(int[] nums) {  
       int flag = -1;  
          int min = -1;  
          for(int i = nums.length - 1; i >= 1; i--) {  
              if(nums[i-1] < nums[i]) {  
                  flag = i;  
                  min = nums[i - 1];  
                  break;  
              }  
          }  
          if(flag == -1) {  
              int l = 0;  
              int h = nums.length - 1;  
              while(l < h) {  
                  nums[l] = nums[l] ^ nums[h];  
                  nums[h] = nums[l] ^ nums[h];  
                  nums[l] = nums[l] ^ nums[h];  
                  l++;  
                  h--;  
              }  
          } else {  
              //稍微预处理，把后面的反转一下  
              int l = flag;  
              int h = nums.length - 1;  
              while(l < h) {  
                  nums[l] = nums[l] ^ nums[h];  
                  nums[h] = nums[l] ^ nums[h];  
                  nums[l] = nums[l] ^ nums[h];  
                  l++;  
                  h--;  
              }  
              // 将后面的冒泡排序， 然后需要把比原来大的那个提前放到第一个位置  
              boolean hasOper = false;  
              for(int i = flag - 1; i < nums.length - 1; i++) {  
                  hasOper = false;  
                  for(int j = i; j < nums.length - 1; j++) {  
                      if(nums[j] > nums[j+1]) {  
                          swap(nums, j, j + 1);  
                          hasOper = true;  
                      }  
                  }  
                  if(!hasOper) {  
                      break;  
                  }  
              }  
                
              int flag2 = -1;  
              for(int i = flag - 1; i < nums.length; i++) {  
                  if(nums[i] > min) {  
                      flag2 = i;  
                      break;  
                  }  
              }  
              //把flag2放在第一位  
              int t = nums[flag2];  
              for(int i = flag2; i > flag - 1; i--) {  
                  nums[i] = nums[i - 1];  
              }  
              nums[flag - 1] = t;  
          }  
            
    
      }  
    
      private void swap(int[] arr, int l, int h) {  
          arr[l] = arr[l] ^ arr[h];  
          arr[h] = arr[l] ^ arr[h];  
          arr[l] = arr[l] ^ arr[h];  
      }  
    
    
  }  
```    
    
### 个人解读    
  
321  
1234729321  
  从后面往前遍历，如果a[i-1]<a[i] 则需要处理.此时把i和i-1调换，然后i-1和后面哪些从小到大排序。  
    
  具体步骤：   
  先找到有a[i-1]<a[i]的地方，然后将后面的冒泡排序一下，然后将比min大的值提前到a[i-1]的地方。  
    
tags:    
  -  双指针  
  -  数学  
