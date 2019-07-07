### description    
  Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.  
    
  Note: You may not slant the container and n is at least 2.  
    
  The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.  
    
  Example:  
    
  Input: [1,8,6,2,5,4,8,3,7]  
  Output: 49  
### solution    
```    
  class Solution {  
     public int maxArea(int[] height) {  
      int left = 0, right = height.length - 1;  
  	int maxArea = 0;  
    
  	while (left < right) {  
  		maxArea = Math.max(maxArea, Math.min(height[left], height[right])  
  				* (right - left));  
  		if (height[left] < height[right])  
  			left++;  
  		else  
  			right--;  
  	}  
    
  	return maxArea;  
  }  
  }  
```    
    
### 个人解读    
  循环入栈法，与最大矩形面积类似。使用栈，记住一条： 当出栈的时候，就是出结果的时候。要么小进大出，要么大进小出。  
  本题是小进大出。  
  因为本题的大出，并不会影响结果，因为出去的结果肯定是小的，所以最终在进行出栈处理。  
    
  遇到问题，最左边的，无论大还是小，都是不能直接舍弃的，所以循环入栈法还有待商榷。  
    
  因为索引的优势，所以不能用循环入栈法，考虑其他。  
    
  如果从两端往中间靠拢。只需要单向的缩减索引。  
  理由： 不妨另left < right  
  此时left++  
  因为如果right--的话，此时的[left, right - 1] 肯定要小于[left, right]的，所以减right没有意义。  
    
   
   
    
tags:    
  -  循环入栈(误)  
  -  贪心算法  
