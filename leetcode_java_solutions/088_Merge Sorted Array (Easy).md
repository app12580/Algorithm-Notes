### description
Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.

Note:

The number of elements initialized in nums1 and nums2 are m and n respectively.
You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2.
Example:

Input:
nums1 = [1,2,3,0,0,0], m = 3
nums2 = [2,5,6],       n = 3

Output: [1,2,2,3,5,6]

### solution
写法一：
```
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
       int index = nums1.length - 1;
        int i = m - 1;
        int j = n - 1;
        while(index >= 0) {
            //这里if语句判断条件的"="，与算法的稳定性有关(原来位置关系经过排序后，是否发生变化)
            if(i < 0) {
                nums1[index--] = nums2[j--];
            } else if(j < 0) {
                nums1[index--] = nums1[i--];
            } else if(nums1[i] > nums2[j]) {   
                nums1[index--] = nums1[i--];
            } else if(nums1[i] <= nums2[j]) {
                nums1[index--] = nums2[j--];
            } 
        }  
    }
}    
```

写法二：
```
class Solution {
   public void merge(int[] nums1, int m, int[] nums2, int n) {
        int index = nums1.length - 1;
        int i = m - 1;
        int j = n - 1;
        while(i >= 0 && j >= 0) {
            if(nums1[i] >= nums2[j]) {
                nums1[index--] = nums1[i--];
            } else {
                nums1[index--] = nums2[j--];
            }
        }
        while(i >= 0) {
            nums1[index--] = nums1[i--];
        }
        while(j >= 0) {
            nums1[index--] = nums2[j--];
        }
    }
}
```

### 个人解读
双指针归并问题，常见两种写法：
+ 等指针i归并结束后，直接把指针j的后续结果移接到归并上，反之亦然
+ 一步一步的归并，不会发生整体移接

指针i的范围start_i, end_i
指针j的范围start_j, end_j
归并指针k的范围0, end
+ 方法一：
```
//总体循环条件是判断i和j均不超出范围
//反之：若有一个超出范围就说明这部分的指针已经遍历完了
while(i 不超过范围 && j 不超过范围) {
    if: 比较条件，如果i满足 {
                //dosomething
                k--; i--(或者i++)
    }
    else if: 比较条件，如果j满足 {
            //dosomething
            k--; j--(或者j++)
    }
}
//跳出循环后，还需要额外执行
while(i不超过范围) {
    k--; i--(或者i++)
}
while(j不超过范围) {
    k--; j--(或者j++)
}


```

+ 方法二： 
```
k = end;
while(k >= 0){  //总体循环条件是判断指针k
    if :i 超出范围  {
        //dosomething
        k--; j--(或者j++)
    }
    else if: j 超出范围 {
        //dosomething
        k--; i--(或者i++)
    }
    else if: 比较条件，如果i满足 {
            //dosomething
            k--; i--(或者i++)
    }
    else if: 比较条件，如果j满足 {
            //dosomething
            k--; j--(或者j++)
    }
}
```

tags:
  - 数组
  - 双指针
  - 归并