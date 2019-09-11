### description    
  Implement a MyCalendar class to store your events. A new event can be added if adding the event will not cause a double booking.  
    
  Your class will have the method, book(int start, int end). Formally, this represents a booking on the half open interval [start, end), the range of real numbers x such that start <= x < end.  
    
  A double booking happens when two events have some non-empty intersection (ie., there is some time that is common to both events.)  
    
  For each call to the method MyCalendar.book, return true if the event can be added to the calendar successfully without causing a double booking. Otherwise, return false and do not add the event to the calendar.  
    
  Your class will be called like this: MyCalendar cal = new MyCalendar(); MyCalendar.book(start, end)  
  Example 1:  
    
  MyCalendar();  
  MyCalendar.book(10, 20); // returns true  
  MyCalendar.book(15, 25); // returns false  
  MyCalendar.book(20, 30); // returns true  
  Explanation:   
  The first event can be booked.  The second can't because time 15 is already booked by another event.  
  The third event can be booked, as the first event takes every time less than 20, but not including 20.  
     
    
  Note:  
    
  The number of calls to MyCalendar.book per test case will be at most 1000.  
  In calls to MyCalendar.book(start, end), start and end are integers in the range [0, 10^9].  
     
### solution    
```    
Runtime: 66 ms, faster than 90.70% of Java online submissions for My Calendar I.  
Memory Usage: 47.3 MB, less than 68.42% of Java online submissions for My Calendar I.  
  
  
  class MyCalendar {  
    
      private List<int[]> timeList;  
    
      public MyCalendar() {  
          timeList = new ArrayList<>();  
      }  
    
    public boolean book(int start, int end) {  
          //先去找time[0]大于end的最左那一个索引  
          //然后比较它左面的集合是否重合交叉  
          if(timeList.isEmpty()) {  
              timeList.add(0, new int[]{start, end});  
              return true;  
          } else if(timeList.size() == 1) {  
              if(jiaocha(timeList.get(0), start, end)) return false;  
              if(start >= timeList.get(0)[1]) {  
                  timeList.add(1, new int[]{start, end});  
              } else {  
                  timeList.add(0, new int[]{start, end});  
              }  
              return true;  
          }  
          int l = 0;  
          int h = timeList.size() - 1;  
          while(l < h) {  
              int m = l + (h - l) / 2;  
              if(timeList.get(m)[0] >= end) {  
                  h = m;  
              } else {  
                  l = m + 1;  
              }  
          }  
          if(l == 0) {  
              timeList.add(0 , new int[]{start, end});  
              return true;  
          } else if(l == timeList.size() - 1){  
              if(jiaocha(timeList.get(l), start, end)) {  
                  //如果没找到，而是因为特例返回了末位  
                  return false;  
              }  
              if(jiaocha(timeList.get(l - 1), start, end) ) {  
                  return false;  
              } else {  
                 if(start >= timeList.get(l)[1]) {  
                      timeList.add(l+1, new int[]{start, end});  
                  } else {  
                      timeList.add(l, new int[]{start, end});  
                  }  
                  return true;  
              }  
          } else if(jiaocha(timeList.get(l - 1), start, end)) {  
              return false;  
          } else {  
              timeList.add(l, new int[]{start, end});  
              return true;  
          }  
      }  
    
      private boolean jiaocha(int[] ints, int start, int end) {  
          int x1 = ints[0];  
          int x2 = ints[1];  
          boolean flag = x1 < end && start < x2;  
          return flag;  
      }  
  }  
  
  
  //方法二： 通过TreeMap  
    
  Runtime: 76 ms, faster than 60.24% of Java online submissions for My Calendar I.  
  Memory Usage: 49 MB, less than 47.37% of Java online submissions for My Calendar I.  
    
  class MyCalendar {  
      private TreeMap<Integer, Integer> treeMap;  
      public MyCalendar() {  
          treeMap = new TreeMap<>();  
      }  
    
      public boolean book(int start, int end) {  
          Integer ceil = treeMap.ceilingKey(start);  
          if(ceil != null) {  
              if(end > ceil) return false;  
          }  
          Integer floor = treeMap.floorKey(start);  
          if(floor != null) {  
              if(treeMap.get(floor) > start) return false;  
          }  
          treeMap.put(start, end);  
          return true;  
      }  
  }  
  
  
  //方法三： 暴力法
  class MyCalendar {
      public MyCalendar() {
      }
  
      List<int[]> books = new ArrayList<>();
      public boolean book(int start, int end) {
          for (int[] b : books)
              if (Math.max(b[0], start) < Math.min(b[1], end)) return false;
          books.add(new int[]{ start, end });
          return true;
      }
  }
```    
    
### 个人解读    
  打算先弄一个排序好的数组来存储，然后通过二分判断是否可以添加  
    
  不知道为啥不行的方法  
    
  ```  
class MyCalendar {  
  
    private List<int[]> timeList;  
  
    public MyCalendar() {  
        timeList = new ArrayList<>();  
    }  
  
  public boolean book(int start, int end) {  
        //先去找time[0]大于end的最左那一个索引  
        //然后比较它左面的集合是否重合交叉  
        if(timeList.isEmpty()) {  
            timeList.add(0, new int[]{start, end});  
            return true;  
        } else if(timeList.size() == 1) {  
            if(jiaocha(timeList.get(0), start, end)) return false;  
            if(start >= timeList.get(0)[1]) {  
                timeList.add(1, new int[]{start, end});  
            } else {  
                timeList.add(0, new int[]{start, end});  
            }  
            return true;  
        }  
        int l = 0;  
        int h = timeList.size() - 1;  
        while(l < h) {  
            int m = l + (h - l) / 2;  
            if(timeList.get(m)[0] >= end) {  
                h = m;  
            } else {  
                l = m + 1;  
            }  
        }  
        if(l == 0) {  
            timeList.add(0 , new int[]{start, end});  
            return true;  
        } else if(l == timeList.size() - 1){  
            if(timeList.get(l)[0] < end) {  
                //如果没找到，而是因为特例返回了末位  
                return false;  
            }  
            if(jiaocha(timeList.get(l - 1), start, end) ) {  
                return false;  
            } else {  
                timeList.add(l -1 , new int[]{start, end});  
                return true;  
            }  
        } else if(jiaocha(timeList.get(l - 1), start, end)) {  
            return false;  
        } else {  
            timeList.add(l -1 , new int[]{start, end});  
            return true;  
        }  
    }  
  
    private boolean jiaocha(int[] ints, int start, int end) {  
        int x1 = ints[0];  
        int x2 = ints[1];  
        boolean flag = x1 < end && start < x2;  
        return flag;  
    }  
}  
  

  
  ```  
    
  反思，这个减1有问题，如果一个区间[100,10000000]，   
  感觉还是不对啊。找到最右边的了，然后只需要判断一个就应该可以了啊。  
    
  知道问题了，在发现右边第一个时候，已经在前面发生交叉了。  
    
  如果把这个二分法通过TreeMap系列的话，就很好解决了。  
    
  真不容易把这个思路搞出来，总是要各种讨论，因为那个二分法的局限导致的。  
    
  总结： 可以使用现成的数据结构减少成本  
     
tags:    
  -  TreeMap  
  -  区间  
