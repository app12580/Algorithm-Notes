### description  
  Implement FreqStack, a class which simulates the operation of a stack-like data structure.
  
  FreqStack has two functions:
  
  push(int x), which pushes an integer x onto the stack.
  pop(), which removes and returns the most frequent element in the stack.
  If there is a tie for most frequent element, the element closest to the top of the stack is removed and returned.
   
  
  Example 1:
  
  Input: 
  ["FreqStack","push","push","push","push","push","push","pop","pop","pop","pop"],
  [[],[5],[7],[5],[7],[4],[5],[],[],[],[]]
  Output: [null,null,null,null,null,null,null,5,7,5,4]
  Explanation:
  After making six .push operations, the stack is [5,7,5,7,4,5] from bottom to top.  Then:
  
  pop() -> returns 5, as 5 is the most frequent.
  The stack becomes [5,7,5,7,4].
  
  pop() -> returns 7, as 5 and 7 is the most frequent, but 7 is closest to the top.
  The stack becomes [5,7,5,4].
  
  pop() -> returns 5.
  The stack becomes [5,7,4].
  
  pop() -> returns 4.
  The stack becomes [5,7].
   
  
  Note:
  
  Calls to FreqStack.push(int x) will be such that 0 <= x <= 10^9.
  It is guaranteed that FreqStack.pop() won't be called if the stack has zero elements.
  The total number of FreqStack.push calls will not exceed 10000 in a single test case.
  The total number of FreqStack.pop calls will not exceed 10000 in a single test case.
  The total number of FreqStack.push and FreqStack.pop calls will not exceed 150000 across all test cases.
### solution  
```  
  // 方法一： HashMap
  Runtime: 47 ms, faster than 99.28% of Java online submissions for Maximum Frequency Stack.
  Memory Usage: 64.2 MB, less than 90.00% of Java online submissions for Maximum Frequency Stack.
  class FreqStack {
      HashMap<Integer, Integer> freq = new HashMap<>();
      HashMap<Integer, Stack<Integer>> m = new HashMap<>();
      int maxfreq = 0;
  
      public void push(int x) {
          int f = freq.getOrDefault(x, 0) + 1;
          freq.put(x, f);
          maxfreq = Math.max(maxfreq, f);
          if (!m.containsKey(f)) m.put(f, new Stack<Integer>());
          m.get(f).push(x);
      }
  
      public int pop() {
          int x = m.get(maxfreq).pop();
          freq.put(x, maxfreq - 1);
          if (m.get(maxfreq).size() == 0) maxfreq--;
          return x;
      }
  }
```  
  
### 个人解读  
  题目描述里面没有说的东西： 
  1、一个数据，即使pop了，但是频率不是归零，而仅仅是-1
  2、每次加入，相当于多了一个相同的元素，而不仅仅是加了一个计数。
  总结：
  1、肯定需要一个数据结构存储每个key的freq，使用HashMap
  2、因为相同频率时候，弹出最接近顶端的，所以每个频率要用一个stack
  3、key是频率，value是stack，可以使用HashMap
  4、为了弹出时候方便，所以使用maxFreq作为标记，可以直接从HashMap中找，不然就可能要用TreeMap代替了
  
tags:  
  -  Hash表
  -  模拟
