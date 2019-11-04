### description    
  Implement a MyCalendarThree class to store your events. A new event can always be added.  
    
  Your class will have one method, book(int start, int end). Formally, this represents a booking on the half open interval [start, end), the range of real numbers x such that start <= x < end.  
    
  A K-booking happens when K events have some non-empty intersection (ie., there is some time that is common to all K events.)  
    
  For each call to the method MyCalendar.book, return an integer K representing the largest integer such that there exists a K-booking in the calendar.  
    
  Your class will be called like this: MyCalendarThree cal = new MyCalendarThree(); MyCalendarThree.book(start, end)  
  Example 1:  
    
  MyCalendarThree();  
  MyCalendarThree.book(10, 20); // returns 1  
  MyCalendarThree.book(50, 60); // returns 1  
  MyCalendarThree.book(10, 40); // returns 2  
  MyCalendarThree.book(5, 15); // returns 3  
  MyCalendarThree.book(5, 10); // returns 3  
  MyCalendarThree.book(25, 55); // returns 3  
  Explanation:   
  The first two events can be booked and are disjoint, so the maximum K-booking is a 1-booking.  
  The third event [10, 40) intersects the first event, and the maximum K-booking is a 2-booking.  
  The remaining events cause the maximum K-booking to be only a 3-booking.  
  Note that the last event locally causes a 2-booking, but the answer is still 3 because  
  eg. [10, 20), [10, 40), and [5, 15) are still triple booked.  
     
    
  Note:  
    
  The number of calls to MyCalendarThree.book per test case will be at most 400.  
  In calls to MyCalendarThree.book(start, end), start and end are integers in the range [0, 10^9].  
    
  不断地添加区间，每次添加可能发生交叉，返回存在交叉的最大区间数量。  
    
### solution    
```    
  
// 方法一： 边界法：  
Runtime: 76 ms, faster than 84.71% of Java online submissions for My Calendar III.  
Memory Usage: 40.9 MB, less than 50.00% of Java online submissions for My Calendar III.  
  
  class MyCalendarThree {  
    
      TreeMap<Integer, Integer> treeMap;  
          public MyCalendarThree() {  
          treeMap = new TreeMap<>();  
      }  
    
      public int book(int start, int end) {  
          treeMap.put(start, treeMap.getOrDefault(start, 0) + 1);  
          treeMap.put(end, treeMap.getOrDefault(end, 0) - 1);  
          int count = 0;  
          int max = 0;  
          for(int v: treeMap.values()) {  
              count += v;  
              if(count > max) max = count;  
          }  
          return max;  
      }  
  }  
```    
    
### 个人解读    
    
  思路一：  
  用TreeMap存储，key是左边界，value是右边界和次数，然后每次添加都会看情况的重新重组。但是TreeMap不方便遍历啊。。。  
    
  思路二：  
  边界法，核心在于，重叠的部分并没有要求是目标区间里面的。所以可以联想到判断括号个数那样的去做，很巧妙的做法。  
  想像是用一个甘特图，然后从左到右扫描，找到最大次数  
    
    
tags:    
  -  数学  
  -  区间  
