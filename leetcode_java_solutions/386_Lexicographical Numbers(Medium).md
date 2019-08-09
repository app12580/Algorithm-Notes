### description    
  Given an integer n, return 1 - n in lexicographical order.  
    
  For example, given 13, return: [1,10,11,12,13,2,3,4,5,6,7,8,9].  
    
  Please optimize your algorithm to use less time and space. The input size may be as large as 5,000,000.  
### solution    
```    
  
Runtime: 5 ms, faster than 26.57% of Java online submissions for Lexicographical Numbers.  
Memory Usage: 40.4 MB, less than 13.33% of Java online submissions for Lexicographical Numbers.  
  
  
  class Solution {  
      public List<Integer> lexicalOrder(int n) {  
          List<Integer> res = new ArrayList<>();  
          for(int i = 1; i <= Math.min(9, n); i++) {  
              res.add(i);  
              helper(res, i, n);  
          }  
          return res;  
      }  
    
      private void helper(List<Integer> res, int pre, int n) {  
          List<Integer> list = helper2(pre, n);  
          if(list.size() == 0) return;  
          for(int num: list) {  
              res.add(num);  
              helper(res, num, n);  
          }  
      }  
    
      private List<Integer> helper2(int pre, int n) {  
          List<Integer> res = new ArrayList<>();  
          for(int i = 0; i < 10; i++) {  
              int t = pre * 10 + i;  
              if(t > n) break;  
              res.add(t);  
          }  
          return res;  
      }  
    
        
    
  }  
  
  //方法二： 思路是差不多的
  class Solution {
      public List<Integer> lexicalOrder(int n) {
        List<Integer> result = new ArrayList<>();
        formSeries(result, 1, n);
        return result;
     }
  
     private void formSeries(List<Integer> result, int i, int n) {
        if(i > n)
           return;
        result.add(i);
        formSeries(result,i * 10, n);
  
        if(i % 10 != 9)
           formSeries(result, i + 1, n);  
      }
  }
```    
    
### 个人解读    
  两种思路，第一种是先按顺序填充了，然后再排序。  
  另一种思路是在生成数组时候，就按某种规律顺序生成。  
    
  思路一：设计一个辅助函数，每次返回input的下一个数字。但是这样时间开销会大啊。。  
  思路二：还是辅助函数，只是辅助函数的参数有:List<Integer> helper(int pre, int n)，返回的是只比pre多一位的数字。   
    
  打磨了好久，需要注意：  
  1、什么时候加上pre的值，helper2里面直接加不好，因为有时候重复。之后发现只有1-9这样的算是pre，剩下的都可以用list来解决。  
  2、代码之美(优化后两个递归入口)  
    
tags:    
  -   重点数学  
  -   数字逻辑  
  -   待优化  
