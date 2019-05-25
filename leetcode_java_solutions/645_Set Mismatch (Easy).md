### description    
  The set S originally contains numbers from 1 to n. But unfortunately, due to the data error, one of the numbers in the set got duplicated to another number in the set, which results in repetition of one number and loss of another number.  
    
  Given an array nums representing the data status of this set after the error. Your task is to firstly find the number occurs twice and then find the number that is missing. Return them in the form of an array.  
    
  Example 1:  
  Input: nums = [1,2,2,4]  
  Output: [2,3]  
  Note:  
  The given array size will in the range [2, 10000].  
  The given array's numbers won't have any order.  
### solution    
```    
// 在原始的数组通过正负数调整，作为一个同步的存储结构  
class Solution {  
    public int[] findErrorNums(int[] nums) {  
        int[] res = new int[2];  
        for(int n: nums) {  
            if(nums[Math.abs(n) - 1] > 0) {  
                nums[Math.abs(n) - 1] *= -1;  
            } else {  
                res[0] = Math.abs(n);  
            }  
        }  
        for(int i = 0; i < nums.length; i++) {  
            if(nums[i] > 0) {  
                res[1] = i + 1;  
            }  
        }  
        return res;  
    }  
}  
  
  
     //cyc O(n)???  
  最直接的方法是先对数组进行排序，这种方法时间复杂度为 O(NlogN)。本题可以以 O(N) 的时间复杂度、O(1) 空间复杂度来求解。  
    
  主要思想是通过交换数组元素，使得数组上的元素在正确的位置上。  
    
  public int[] findErrorNums(int[] nums) {  
      for (int i = 0; i < nums.length; i++) {  
          while (nums[i] != i + 1 && nums[nums[i] - 1] != nums[i]) {  
              swap(nums, i, nums[i] - 1);  
          }  
      }  
      for (int i = 0; i < nums.length; i++) {  
          if (nums[i] != i + 1) {  
              return new int[]{nums[i], i + 1};  
          }  
      }  
      return null;  
  }  
    
  private void swap(int[] nums, int i, int j) {  
      int tmp = nums[i];  
      nums[i] = nums[j];  
      nums[j] = tmp;  
  }  
```    
    
### 个人解读    
  思路一： 就是先排序，然后遍历一遍就能知道结果了。  
  问题有没有更好的解法呢？  
  思路二(失败)：联想到了位运算，需要找到一种运算方式，把所有的都计算上，然后根据拿到的结果，再回推少了哪些数字  
  运算符： +， ^  
  1 2 2 4 5   
  ```  
    +: right:15  
       get: 14  
       A - B = 1  
    ^: right: A: 1 ^ 2 ^ 3 ^ 4 ^ 5  
       get:   B: 1 ^ 2 ^ 2 ^ 4 ^ 5  
       A ^ B  = 2 ^ 3  
      
    //没有唯一解...失败。。  
    //说明了位运算至少不配与加减法一样，能成为方程自由度的一环  
  ```  
  现在的问题就变成了：  
  ```  
    已知：   
      x - y = a  
      x ^ y = b  
    a,b为常量，x,y为正整数，能否把x,y求出来唯一解  
  ```  
  思路三(说来惭愧，看了答案才想到的)：   
  先创建一个数组bool[]，用来存储哪个位置不缺失，这样遍历数组的时候，如果bool标记为false则改为true，反之，则说明重复了。遍历完之后，bool仍然为false 的那个，就是缺失的。  
  反思： 为什么自己没有想到。。。  
  大概是因为，习惯了排序时候不额外添加新的内存空间了。然而。。。忽略了本题一个很重要的关键点：  
  ```  
  数组为1.2.3.4....n，中间只有一个元素为不一样的  
  关键点一： 全部为正数，可以把正负关系融入进来作为一个标记， 1为原始数据，而-1表示处理过了  
  关键点二：最重要的一点，数组的值因为其范围的特殊性，可以与数组下标一一对应  
  在遍历数组时候，获取到了值，可以直接扔进下标里(这一点要好好想清楚了，遍历到值，然后塞进标记的中间存储数组里，此时遍历的时候的i并不重要)  
  ```  
  经过优化，就可以不用创建bool[]数组了  
    
tags:    
  -  数组  
  - 自然数组  