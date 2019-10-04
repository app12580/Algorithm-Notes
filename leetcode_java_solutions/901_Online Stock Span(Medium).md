### description    
  Write a class StockSpanner which collects daily price quotes for some stock, and returns the span of that stock's price for the current day.  
    
  The span of the stock's price today is defined as the maximum number of consecutive days (starting from today and going backwards) for which the price of the stock was less than or equal to today's price.  
    
  For example, if the price of a stock over the next 7 days were [100, 80, 60, 70, 60, 75, 85], then the stock spans would be [1, 1, 1, 2, 1, 4, 6].  
    
     
    
  Example 1:  
    
  Input: ["StockSpanner","next","next","next","next","next","next","next"], [[],[100],[80],[60],[70],[60],[75],[85]]  
  Output: [null,1,1,1,2,1,4,6]  
  Explanation:   
  First, S = StockSpanner() is initialized.  Then:  
  S.next(100) is called and returns 1,  
  S.next(80) is called and returns 1,  
  S.next(60) is called and returns 1,  
  S.next(70) is called and returns 2,  
  S.next(60) is called and returns 1,  
  S.next(75) is called and returns 4,  
  S.next(85) is called and returns 6.  
    
  Note that (for example) S.next(75) returned 4, because the last 4 prices  
  (including today's price of 75) were less than or equal to today's price.  
     
    
  Note:  
    
  Calls to StockSpanner.next(int price) will have 1 <= price <= 10^5.  
  There will be at most 10000 calls to StockSpanner.next per test case.  
  There will be at most 150000 calls to StockSpanner.next across all test cases.  
  The total time limit for this problem has been reduced by 75% for C++, and 50% for all other languages.  
### solution    
```    
  
Runtime: 98 ms, faster than 71.33% of Java online submissions for Online Stock Span.  
Memory Usage: 70.4 MB, less than 10.00% of Java online submissions for Online Stock Span.  
    
class StockSpanner {  
  
    private Stack<int[]> stack;  
    public StockSpanner() {  
        stack = new Stack<int[]>();  
    }  
  
    public int next(int price) {  
        int count = 1;  
        while(!stack.isEmpty() && stack.peek()[0] <= price) {  
            count += stack.pop()[1];  
        }  
        stack.push(new int[]{price, count});  
        return count;  
    }  
}  
  
```    
    
### 个人解读    
  感觉这题目流水账就差不多了啊。。。。  
  试一下看看效率吧。  
  流水账超时了TLE  
    
  然后想办法优化，优化的核心思路在于复用前面的结果。这个时候就需要举个例子好好分析下当前结果和前方结果的关系。  
  [[],[100],[80],[60],[70],[60],[75],[85]]  
        1     1    1     2    1    4     6  
          
  以85为例，进入的时候去前面找比85低的，然后发现，前面的参数只需要75的4和80的1即可，然后就会发现，本题目和单调栈法很接近。        
    
    
    
  超时算法：  
  ```  
    
class StockSpanner {  
      
    private List<Integer> list;  
    public StockSpanner() {  
        list = new ArrayList<>();  
    }  
  
    public int next(int price) {  
        if(list.size() == 0) {  
            list.add(price);  
            return 1;  
        }  
        int count = 1;  
        int index = list.size();  
        while(index > 0 && list.get(index - 1) <= price) {  
            count++;  
            index--;  
        }  
        list.add(price);  
        return count;  
    }  
}  
  ```  
    
tags:    
  -  单调栈法  
  -  数学  
