### description    
  There are two sorted arrays nums1 and nums2 of size m and n respectively.  
    
  Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).  
    
  You may assume nums1 and nums2 cannot be both empty.  
    
  Example 1:  
    
  nums1 = [1, 3]  
  nums2 = [2]  
    
  The median is 2.0  
  Example 2:  
    
  nums1 = [1, 2]  
  nums2 = [3, 4]  
    
  The median is (2 + 3)/2 = 2.5  
### solution    
```    
  
// 方法一： 流水账，一步一步推进  
Runtime: 2 ms, faster than 99.97% of Java online submissions for Median of Two Sorted Arrays.  
Memory Usage: 46.7 MB, less than 90.97% of Java online submissions for Median of Two Sorted Arrays.  
为啥效率这么高。。。。  
  
  class Solution {  
     // 3 3 : 4  
      // 2 3 : 3  
      // 2 2 : 3  
      // odd: (m+n) /2  even: (m+n)/2  和 (m+n)/2 - 1  
      public double findMedianSortedArrays(int[] nums1, int[] nums2) {  
          int m = nums1.length;  
          int n = nums2.length;  
          int mid = (m + n) / 2 + 1;  
          boolean even = (m + n) % 2 == 0;  
          if(nums1.length == 0) {  
              if(!even) {  
                  return nums2[n/2];  
              } else {  
                  return (double)(nums2[n/2] + nums2[n/2-1]) /2;  
              }  
          }  
          if(nums2.length == 0) {  
              if(!even) {  
                  return nums1[m/2];  
              } else {  
                  return (double)(nums1[m/2] + nums1[m/2-1]) /2;  
              }  
          }  
          int i = 0, j = 0;  
          while(true) {  
              int total = i + j;  
              //前面的包括max的数字个数为mid  
              if(total == mid) {  
                  //然后去找左面的最大值 如果是even，还要找第二大的  
                  int p = i == 0 ? Integer.MIN_VALUE : nums1[i - 1];  
                  int q = j == 0 ? Integer.MIN_VALUE : nums2[j - 1];  
                  if(!even) {  
                      return Math.max(p, q);  
                  } else {  
                      int max = Math.max(p, q);  
                      if(p > q) {  
                          i--;  
                      } else {  
                          j--;  
                      }  
                      p = i == 0 ? Integer.MIN_VALUE : nums1[i - 1];  
                      q = j == 0 ? Integer.MIN_VALUE : nums2[j - 1];  
                      int second = Math.max(p, q);  
                      return (double)(max + second)/2;  
                  }  
              }  
              if(i == m) {  
                  j++;  
              } else if(j == n) {  
                  i++;  
              } else {  
                  if(nums1[i] < nums2[j]) {  
                      i++;  
                  } else {  
                      j++;  
                  }  
              }  
          }  
      }  
    
  }  
    
  // 方法二： 中间划分法  
  代码虽然简洁了，但是效率反而下降。  
  可以使用二分法优化  
  Runtime: 3 ms, faster than 37.39% of Java online submissions for Median of Two Sorted Arrays.  
  Memory Usage: 46.9 MB, less than 88.89% of Java online submissions for Median of Two Sorted Arrays.  
    
  class Solution {  
      public double findMedianSortedArrays(int[] nums1, int[] nums2) {  
          if(nums1.length > nums2.length) {  
              int[] temp = nums1;  
              nums1 = nums2;  
              nums2 = temp;  
          }  
          int m = nums1.length;  
          int n = nums2.length;  
          boolean even = (m + n) % 2 == 0;  
          for(int i = 0; i <= m; i++) {  
              //i表示num1保留多少个  
              int j = (m + n + 1) / 2 - i;  //此时num2左边有多少个数字  
              int leftMax = Math.max(i == 0 ? Integer.MIN_VALUE : nums1[i - 1], j == 0 ? Integer.MIN_VALUE : nums2[j-1]);  
              int rightMin = Math.min(i == m ? Integer.MAX_VALUE : nums1[i], j == n ? Integer.MAX_VALUE : nums2[j]);  
              if(leftMax <= rightMin) {  
                  if(!even) {  
                      return leftMax;  
                  } else {  
                      return (double)(leftMax + rightMin) / 2;  
                  }  
              }  
          }  
          return -1;  
    
      }  
    
  }  
```    
    
### 个人解读    
  找出两个排序数组中间值的平均自。  
  感觉可以用双指针流水账做下来，先弄好一个标记，判断是否。  
  但是遇到了问题，(i+j)与mid的关系很难明确定义，存在各种if奇数还是偶数的情况。  
  问题关键，是要找到一个明确的并且简介的遍历方式。  
    
  总结一：  
  1、不知道为啥效率这么高。。。  
  2、关于ij到终止时候的意义以及终止条件：  
    --目的：终止后，从ij前面的挑选出来最大值，看情况找最小值  
    --终止条件：等号左边：ij前方的个数；等号右边：最大值所在的个数  
  3、注意索引和个数的区别  
  4、其实可以直接for循环ij，找出来  
    
  方法二：  
  核心规律：将所有数字分成两部分，需要for循环Math.min(m, n) + 1次，直到满足条件：  
    左边最大 <= 右边最小  
  如果odd，返回左边最大；如果even返回(左边最大+右边最小)/2  
    
  注意事项： 通过ij一步步推进反而是效率最高的。可能事实如此，也可能与测试用例有关。  
    
tags:    
  -  双指针之绝对细节  
  -  二分法  
  -  算法效率  
