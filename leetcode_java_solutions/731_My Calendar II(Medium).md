### description    
  Implement a MyCalendarTwo class to store your events. A new event can be added if adding the event will not cause a triple booking.  
    
  Your class will have one method, book(int start, int end). Formally, this represents a booking on the half open interval [start, end), the range of real numbers x such that start <= x < end.  
    
  A triple booking happens when three events have some non-empty intersection (ie., there is some time that is common to all 3 events.)  
    
  For each call to the method MyCalendar.book, return true if the event can be added to the calendar successfully without causing a triple booking. Otherwise, return false and do not add the event to the calendar.  
    
  Your class will be called like this: MyCalendar cal = new MyCalendar(); MyCalendar.book(start, end)  
  Example 1:  
    
  MyCalendar();  
  MyCalendar.book(10, 20); // returns true  
  MyCalendar.book(50, 60); // returns true  
  MyCalendar.book(10, 40); // returns true  
  MyCalendar.book(5, 15); // returns false  
  MyCalendar.book(5, 10); // returns true  
  MyCalendar.book(25, 55); // returns true  
  Explanation:   
  The first two events can be booked.  The third event can be double booked.  
  The fourth event (5, 15) can't be booked, because it would result in a triple booking.  
  The fifth event (5, 10) can be booked, as it does not use time 10 which is already double booked.  
  The sixth event (25, 55) can be booked, as the time in [25, 40) will be double booked with the third event;  
  the time [40, 50) will be single booked, and the time [50, 55) will be double booked with the second event.  
     
    
  Note:  
    
  The number of calls to MyCalendar.book per test case will be at most 1000.  
  In calls to MyCalendar.book(start, end), start and end are integers in the range [0, 10^9].  
### solution    
```    
  class MyCalendarTwo {  
      private List<int[]> books = new ArrayList<>();      
      public boolean book(int s, int e) {  
          MyCalendar overlaps = new MyCalendar();  
          for (int[] b : books)  
              if (Math.max(b[0], s) < Math.min(b[1], e)) // overlap exist  
                  if (!overlaps.book(Math.max(b[0], s), Math.min(b[1], e))) return false; // overlaps overlapped  
          books.add(new int[]{ s, e });  
          return true;  
      }  
    
      private static class MyCalendar {  
          List<int[]> books = new ArrayList<>();  
          public boolean book(int start, int end) {  
              for (int[] b : books)  
                  if (Math.max(b[0], start) < Math.min(b[1], end)) return false;  
              books.add(new int[]{ start, end });  
              return true;  
          }  
      }  
  }  
```    
    
### 个人解读    
  比前面那个题目多了一次容忍度，感觉可以先拿出来，然后再判断。  
    
  但是有个问题，TreeMap的Key是不可以重复的。  
    
    
  采用暴力法，这样就可以不用TreeMap这种东西了。  
    
  然后实际核心计算时候，有一个关键点，用两个数据结构存储中间变量，一个存储原始数据，另一个存储每次交叉时候的重叠部分。这样只需要重叠部分能塞进去就说明此时可以成功插入。    
    
tags:    
  -  区间  
  -  数学  
