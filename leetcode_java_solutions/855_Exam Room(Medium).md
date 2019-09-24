### description      
  In an exam room, there are N seats in a single row, numbered 0, 1, 2, ..., N-1.    
      
  When a student enters the room, they must sit in the seat that maximizes the distance to the closest person.  If there are multiple such seats, they sit in the seat with the lowest number.  (Also, if no one is in the room, then the student sits at seat number 0.)    
      
  Return a class ExamRoom(int N) that exposes two functions: ExamRoom.seat() returning an int representing what seat the student sat in, and ExamRoom.leave(int p) representing that the student in seat number p now leaves the room.  It is guaranteed that any calls to ExamRoom.leave(p) have a student sitting in seat p.    
      
       
      
  Example 1:    
      
  Input: ["ExamRoom","seat","seat","seat","seat","leave","seat"], [[10],[],[],[],[],[4],[]]    
  Output: [null,0,9,4,2,null,5]    
  Explanation:    
  ExamRoom(10) -> null    
  seat() -> 0, no one is in the room, then the student sits at seat number 0.    
  seat() -> 9, the student sits at the last seat number 9.    
  seat() -> 4, the student sits at the last seat number 4.    
  seat() -> 2, the student sits at the last seat number 2.    
  leave(4) -> null    
  seat() -> 5, the student sits at the last seat number 5.    
  ​​​​​​​    
      
  Note:    
      
  1 <= N <= 10^9    
  ExamRoom.seat() and ExamRoom.leave() will be called at most 10^4 times across all test cases.    
  Calls to ExamRoom.leave(p) are guaranteed to have a student currently sitting in seat number p.    
### solution      
```      
  //方法一 暴力法    
  Runtime: 668 ms, faster than 5.04% of Java online submissions for Exam Room.    
  Memory Usage: 56.6 MB, less than 5.88% of Java online submissions for Exam Room.    
      
  class ExamRoom{    
      int N;    
      List<Integer> L = new LinkedList<>();    
      public ExamRoom(int n) {    
          N = n;    
      }    
      
      public int seat() {    
          if (L.size() == 0) {    
              L.add(0);    
              return 0;    
          }    
          int d = Math.max(L.get(0), N - 1 - L.get(L.size() - 1));    //首位前方和末位后方的距离    
          for (int i = 0; i < L.size() - 1; ++i) {    
              d = Math.max(d, (L.get(i + 1) - L.get(i)) / 2);    
          }    
          if (L.get(0) == d) {    
              L.add(0, 0);    
              return 0;    
          }    
          for (int i = 0; i < L.size() - 1; ++i)    
              if ((L.get(i + 1) - L.get(i)) / 2 == d) {    
                  L.add(i + 1, (L.get(i + 1) + L.get(i)) / 2);    
                  return L.get(i + 1);    
              }    
          L.add(N - 1);    
          return N - 1;    
      }    
      
      public void leave(int p) {    
          for (int i = 0; i < L.size(); ++i) if (L.get(i) == p) L.remove(i);    
      }    
  }    
    
  // 方法二： 分治进阶  
  Runtime: 51 ms, faster than 85.09% of Java online submissions for Exam Room.  
  Memory Usage: 40.4 MB, less than 29.41% of Java online submissions for Exam Room.  
    
  class ExamRoom {  
      int N;  
      class Range {  
          int left;  
          int right;  
          int dist;  
          Range(int left, int right) {  
              this.left = left;  
              this.right = right;  
              if (left == -1) dist = right;  
              else if (right == N) dist = N - 1 - left;  
              else dist = (right-left)/2;  
          }  
      }  
      PriorityQueue<Range> queue;  
      public ExamRoom(int N) {  
          this.N = N;  
          queue = new PriorityQueue<>(new Comparator<Range>(){  
          //把距离大的放前面， 左面的放前面  
              @Override  
              public int compare(Range r1, Range r2) {  
                  if (r2.dist != r1.dist) return r2.dist - r1.dist;  
                  else return r1.left - r2.left;  
              }  
          });  
          queue.add(new Range(-1, N));  
      }  
    
      public int seat() {  
            //把距离最大的拿出来，然后拆解成两份  
          Range range = queue.poll();  
          int seat = (range.left + range.right)/2;  
          if (range.left == -1) seat = 0;  
          else if (range.right == N) seat = N-1;  
    
          queue.add(new Range(range.left, seat));  
          queue.add(new Range(seat, range.right));  
          return seat;  
      }  
    
    
        //获取两个区间，然后都删掉合并成一个  
      public void leave(int p) {  
          List<Range> list = new ArrayList<>(queue);  
          Range r1 = null, r2 = null;  
          for (Range range : list) {  
              if (range.right == p) r1 = range;  
              else if (range.left == p) r2 = range;  
              if (r1 != null && r2 != null) break;  
          }  
          queue.remove(r1);  
          queue.remove(r2);  
          queue.add(new Range(r1.left, r2.right));  
      }  
  }  
    
```      
      
### 个人解读      
  方法一的暴力法，发现ArrayList和LinkedList是差不多的。    
      
  方法二，利用了分治法，但是更加的直接，直接把分治内容划成了一个类，然后把这个类作为基础的结构放进集合里面。    
      
      
tags:      
  -  分治法进阶    
  -  数学    
