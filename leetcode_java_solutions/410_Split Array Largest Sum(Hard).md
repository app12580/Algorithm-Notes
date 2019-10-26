### description    
  Given an array which consists of non-negative integers and an integer m, you can split the array into m non-empty continuous subarrays. Write an algorithm to minimize the largest sum among these m subarrays.  
    
  Note:  
  If n is the length of array, assume the following constraints are satisfied:  
    
  1 ≤ n ≤ 1000  
  1 ≤ m ≤ min(50, n)  
  Examples:  
    
  Input:  
  nums = [7,2,5,10,8]  
  m = 2  
    
  Output:  
  18  
    
  Explanation:  
  There are four ways to split nums into two subarrays.  
  The best way is to split it into [7,2,5] and [10,8],  
  where the largest sum among the two subarrays is only 18.  
### solution    
```    
// 方法二： 二分法  
Runtime: 1 ms, faster than 60.32% of Java online submissions for Split Array Largest Sum.  
Memory Usage: 34.4 MB, less than 100.00% of Java online submissions for Split Array Largest Sum.  
class Solution {  
    public int splitArray(int[] nums, int m) {  
        long l = 0;  
        long r = 0;          
        int n = nums.length;  
        for (int i = 0; i < n; i++) {  
            r += nums[i];  
            if (l < nums[i]) {  
                l = nums[i];  
            }  
        }  
        long ans = r;  
        while (l <= r) {  
            long mid = (l + r) >> 1;  
            long sum = 0;  
            int cnt = 1;  
            for (int i = 0; i < n; i++) {  
                if (sum + nums[i] > mid) {  
                    cnt ++;  
                    sum = nums[i];  
                } else {  
                    sum += nums[i];  
                }  
            }  
            if (cnt <= m) {  
                ans = Math.min(ans, mid);  
                r = mid - 1;  
            } else {  
                l = mid + 1;  
            }  
        }  
        return (int)ans;        
    }  
}   
  
  
  // 方法一 数组分组的动态规划：  
  Runtime: 20 ms, faster than 29.27% of Java online submissions for Split Array Largest Sum.  
  Memory Usage: 34.8 MB, less than 100.00% of Java online submissions for Split Array Largest Sum.  
    
 class Solution {  
     public int splitArray(int[] nums, int m) {  
         long l = 0;  
         long r = 0;          
         int n = nums.length;  
         for (int i = 0; i < n; i++) {  
             r += nums[i];  
             if (l < nums[i]) {  
                 l = nums[i];  
             }  
         }  
         long ans = r;  
         while (l < r) {  
             long mid = (l + r) >> 1;  
             long sum = 0;  
             int cnt = 1;  
             for (int i = 0; i < n; i++) {  
                 if (sum + nums[i] > mid) {  
                     cnt ++;  
                     sum = nums[i];  
                 } else {  
                     sum += nums[i];  
                 }  
             }  
             if (cnt <= m) {  
                 ans = Math.min(ans, mid);  
                 r = mid;  
             } else {  
                 l = mid + 1;  
             }  
         }  
         return (int)ans;  
     }  
 }   
```    
    
### 个人解读    
  数组分组问题，可以联想到动态规划。  
    
  二分法的核心思路在于：虽然不知道最小的是哪一个，但是有办法可以试出来，给出的一个数是否可以让所有分组比之小，利用这一点，二分逼近。注意二分法可以使用模板  
    
tags:    
  -  数组分组  
  -  二分法  
