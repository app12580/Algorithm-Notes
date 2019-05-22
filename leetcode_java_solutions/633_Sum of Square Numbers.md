### description
Given a non-negative integer c, your task is to decide whether there're two integers a and b such that a2 + b2 = c.

Example 1:

```
Input: 5
Output: True
Explanation: 1 * 1 + 2 * 2 = 5
```
 

Example 2:

```
Input: 3
Output: False
```

### solution
```
class Solution {
    public boolean judgeSquareSum(int c) {
        int i = 0;
        int j = (int)Math.sqrt(c);
        while(i <= j) {
            int sum = i * i + j * j;
            if(sum < c) {
                i++;
            } else if(sum > c) {
                j--;
            } else {
                return true;
            }
        }
        return false;
    }
}
```

### 个人解读
可以依赖原生的求平方根函数

tags:
  - 双指针
