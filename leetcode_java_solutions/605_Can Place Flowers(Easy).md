### description
Suppose you have a long flowerbed in which some of the plots are planted and some are not. However, flowers cannot be planted in adjacent plots - they would compete for water and both would die.

Given a flowerbed (represented as an array containing 0 and 1, where 0 means empty and 1 means not empty), and a number n, return if n new flowers can be planted in it without violating the no-adjacent-flowers rule.

Example 1:
Input: flowerbed = [1,0,0,0,1], n = 1
Output: True
Example 2:
Input: flowerbed = [1,0,0,0,1], n = 2
Output: False
Note:
The input array won't violate no-adjacent-flowers rule.
The input array size is in the range of [1, 20000].
n is a non-negative integer which won't exceed the input array size.
### solution
```
class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
         int count = 0;
        for(int i = 0; i < flowerbed.length; i++) {
            if(flowerbed[i] != 0) {
                continue;
            }
            if((i== 0 || flowerbed[i - 1] == 0) && (i== flowerbed.length - 1 || flowerbed[i + 1] == 0)) {
                count++;
                if(count >= n) {
                    return true;
                }
                i ++;       //error:之前忘记for循环里面也i++了，错写成i+=2
            }
        }
        return count >= n;
    }
}
```

CyC2018解法：
```
public boolean canPlaceFlowers(int[] flowerbed, int n) {
    int len = flowerbed.length;
    int cnt = 0;
    for (int i = 0; i < len && cnt < n; i++) {
        if (flowerbed[i] == 1) {
            continue;
        }
        int pre = i == 0 ? 0 : flowerbed[i - 1];
        int next = i == len - 1 ? 0 : flowerbed[i + 1];
        if (pre == 0 && next == 0) {
            cnt++;
            flowerbed[i] = 1;
        }
    }
    return cnt >= n;
}
我的解法稍微优化了一丢丢
```
### 个人解读
遇到此类问题，往往可以无脑DP，主要问题在于能否优化，使用贪婪算法
DP的话分为两种f(n)--第n个位置不种，g(n)第0个位置种上
稍微想想，每次需要判断挺多东西，还要考虑原来的n-1，n，n+1位置上是否为1

然后贪心算法来了，想当然的认为：从左往右，能种就种，是不是效率最好

证明：如果不是，则说明需要让出来一个"000"的结构
```
本来中间位置能种的，结果故意让开
0 0 0
0 0 0
```
如果后面跟着1，则血亏
如果后面跟着0，无论这个0上面种不种，都亏了

综上：贪心算法合理

tags:
  - 数组
  - 动态规划
  - 贪心算法
