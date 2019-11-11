### description    
  Given a single positive integer x, we will write an expression of the form x (op1) x (op2) x (op3) x ... where each operator op1, op2, etc. is either addition, subtraction, multiplication, or division (+, -, *, or /).  For example, with x = 3, we might write 3 * 3 / 3 + 3 - 3 which is a value of 3.  
    
  When writing such an expression, we adhere to the following conventions:  
    
  The division operator (/) returns rational numbers.  
  There are no parentheses placed anywhere.  
  We use the usual order of operations: multiplication and division happens before addition and subtraction.  
  It's not allowed to use the unary negation operator (-).  For example, "x - x" is a valid expression as it only uses subtraction, but "-x + x" is not because it uses negation.  
  We would like to write an expression with the least number of operators such that the expression equals the given target.  Return the least number of operators used.  
    
     
    
  Example 1:  
    
  Input: x = 3, target = 19  
  Output: 5  
  Explanation: 3 * 3 + 3 * 3 + 3 / 3.  The expression contains 5 operations.  
  Example 2:  
    
  Input: x = 5, target = 501  
  Output: 8  
  Explanation: 5 * 5 * 5 * 5 - 5 * 5 * 5 + 5 / 5.  The expression contains 8 operations.  
  Example 3:  
    
  Input: x = 100, target = 100000000  
  Output: 3  
  Explanation: 100 * 100 * 100 * 100.  The expression contains 3 operations.  
     
    
  Note:  
    
  2 <= x <= 100  
  1 <= target <= 2 * 10^8  
### solution    
```    
  
// 方法一： 每次与最近的 x ^ i 的进行比较，  
需要注意的是 product - target < target 这个条件如果不加上，就会Stack Overflow了  
Runtime: 4 ms, faster than 65.63% of Java online submissions for Least Operators to Express Number.  
Memory Usage: 37.6 MB, less than 33.33% of Java online submissions for Least Operators to Express Number.  
  
  class Solution {  
      Map<Integer, Integer> memo = new HashMap<>();  
      public int leastOpsExpressTarget(int x, int target) {  
          if (target == 1)   
              return x == 1 ? 0 : 1;  
          if (memo.containsKey(target))  
              return memo.get(target);  
          long product = x;  
          int count = 0;  
          while (product < target) {  
              count++;  
              product *= x;  
                
          }  
            
          // candidate1 : in the form : x*x*...*x - (......) = target  
          int cand1 = Integer.MAX_VALUE;  
          if (product == target)  
              cand1 = count;  
          else if (product - target < target) //如果差值小于target就不进行DFS了  
              cand1 = count + leastOpsExpressTarget(x, (int)(product - target)) + 1;  
            
          // candidate2 : in the form : x*x*...*x + (......) = target  
          int cand2 = Integer.MAX_VALUE;  
          product /= x;  
          cand2 = leastOpsExpressTarget(x, (int)(target - product)) + (count == 0 ? 2 : count);  
          int res = Math.min(cand1, cand2);  
          memo.put(target, res);  
          return res;  
      }  
  }  
```    
    
### 个人解读    
  如果能让用括号就好了，那样就可以通过BFS，存储每次可能出现的结果。  
    
  然后仔细观察题目，发现题目很类似于进制转换。  
    
  思路一：贪婪算法，每次获取最接近的最高位数。然后再递归差值  
    
  Input: x = 3, target = 19  
  19: 9 + 9 + 1 or 27 - 9 + 1  
     2 + 2 + 1 or 3 + 2 + 1  
    
  思路二：  
  用k进制去描述target，然后从后往前依次判断是加还减  
    
  这个题目有一堆解法，一堆思路     
       
       
     ```  
     stackOverFlow  
    public int leastOpsExpressTarget(int x, int target) {  
        Map<Integer, Integer> map = new HashMap<>();  
        List<Integer> list = new ArrayList<>();  
        map.put(1, 2);  
        int mul = 1;  
        int count = 1;  
        list.add(1);  
        while(mul  <= target / x) {  
            mul *= x;  
            list.add(mul);  
            map.put(mul, count++);  
        }  
        if(mul == target) {  
            return map.get(mul);  
        } else {  
            map.put(mul * x, count);  
            list.add(mul * x);  
        }  
        return helper(x, target, map, list);  
    }  
  
    private int helper(int x, int target, Map<Integer, Integer> map, List<Integer> list) {  
        if(map.containsKey(target)) {  
            return map.get(target);  
        }  
        int index = 0;  
        for(int i = 1; i < list.size(); i++) {  
            if(list.get(i) > target) {  
                index = i - 1;  
                break;  
            }  
        }  
        //index  
        int low = list.get(index);  
        int cur = index == 0 ? 2 : index;  
        int min = Math.min(cur + helper(x, target - list.get(index), map, list), cur + 1 + helper(x,  list.get(index + 1) - target, map, list));  
        map.put(target, min);  
        return min;  
    }  
  
     ```  
tags:    
  -  重点数学  
  -  动态规划  
