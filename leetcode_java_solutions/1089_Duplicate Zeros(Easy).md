### description    
  Given a fixed length array arr of integers, duplicate each occurrence of zero, shifting the remaining elements to the right.  
    
  Note that elements beyond the length of the original array are not written.  
    
  Do the above modifications to the input array in place, do not return anything from your function.  
    
     
    
  Example 1:  
    
  Input: [1,0,2,3,0,4,5,0]  
  Output: null  
  Explanation: After calling your function, the input array is modified to: [1,0,0,2,3,0,0,4]  
  Example 2:  
    
  Input: [1,2,3]  
  Output: null  
  Explanation: After calling your function, the input array is modified to: [1,2,3]  
     
    
  Note:  
    
  1 <= arr.length <= 10000  
  0 <= arr[i] <= 9  
    
### solution    
```    
  class Solution {  
      public void duplicateZeros(int[] arr) {  
          int count = 0;  
          int lastIndex = -1;  
          boolean zeroFlag = false;  
          // [1,2,3]  
          for (int i = 0; i < arr.length; i++) {  
              lastIndex++;  
              if (arr[i] == 0) {  
                  count += 2;  
              } else {  
                  count++;  
              }  
              if(count == arr.length) {  
                  break;  
              } else if(count == arr.length + 1) {  
                  //此时为半个0，需要做个标记  
                  zeroFlag = true;  
                  break;  
              }  
          }  
    
          int printIndex = arr.length - 1;  
          while(printIndex > 0) {  
              if(zeroFlag) {  
                  arr[printIndex--] = 0;  
                  lastIndex--;  
                  zeroFlag = false;  
              } else {  
                  if(arr[lastIndex] == 0) {  
                      arr[printIndex--] = 0;  
                      arr[printIndex--] = 0;  
                      lastIndex --;  
                  } else {  
                      arr[printIndex--] = arr[lastIndex];  
                      lastIndex --;  
                  }  
              }  
                
          }  
      }  
  }  
```    
    
### 个人解读    
  双指针问题。  
  然而问题在于不创建新的空间。  
    
  先统计0的索引。有问题。  
    
  方向性，从后往前或者从前往后。  
    
  想出来了，先遍历一遍，找出修正后的最后一个数字的索引是多少。然后从后往前遍历，这样就不会丢失数据。  
    
  最后凭感觉写出来的，能不能过都没把握。。。  
    
tags:    
  -  数学  
  -  双指针  
