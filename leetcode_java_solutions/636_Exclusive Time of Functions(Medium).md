### description    
  On a single threaded CPU, we execute some functions.  Each function has a unique id between 0 and N-1.  
    
  We store logs in timestamp order that describe when a function is entered or exited.  
    
  Each log is a string with this format: "{function_id}:{"start" | "end"}:{timestamp}".  For example, "0:start:3" means the function with id 0 started at the beginning of timestamp 3.  "1:end:2" means the function with id 1 ended at the end of timestamp 2.  
    
  A function's exclusive time is the number of units of time spent in this function.  Note that this does not include any recursive calls to child functions.  
    
  The CPU is single threaded which means that only one function is being executed at a given time unit.  
    
  Return the exclusive time of each function, sorted by their function id.  
    
     
    
  Example 1:  
    
    
    
  Input:  
  n = 2  
  logs = ["0:start:0","1:start:2","1:end:5","0:end:6"]  
  Output: [3, 4]  
  Explanation:  
  Function 0 starts at the beginning of time 0, then it executes 2 units of time and reaches the end of time 1.  
  Now function 1 starts at the beginning of time 2, executes 4 units of time and ends at time 5.  
  Function 0 is running again at the beginning of time 6, and also ends at the end of time 6, thus executing for 1 unit of time.   
  So function 0 spends 2 + 1 = 3 units of total time executing, and function 1 spends 4 units of total time executing.  
     
    
  Note:  
    
  1 <= n <= 100  
  Two functions won't start or end at the same time.  
  Functions will always log when they exit.  
### solution    
```    
  class Solution {  
       
         public int[] exclusiveTime(int n, List<String> logs) {  
          int[] res = new int[n];  
          Stack<int[]> stack = new Stack<>();  
          for(int i = 0; i < logs.size(); i++) {  
              String cur = logs.get(i);  
              String[] split = cur.split(":");  
              int id = Integer.valueOf(split[0]);  
              boolean start = "start".equals(split[1]);  
              int time = Integer.valueOf(split[2]);  
              if(start) {  
                  if(stack.isEmpty()) {  
                      stack.push(new int[]{id, time});  
                  } else {  
                      int[] peek = stack.peek();  
                      int preId = peek[0];  
                      int preTime = peek[1];  
                      res[preId] += time - preTime;  
                      stack.push(new int[]{id, time});  
                  }  
              } else {  
                  int[] pop = stack.pop();  
                  int preTime = pop[1];  
                  res[id] += time - preTime + 1;  
                  if(!stack.isEmpty()) {  
                      stack.peek()[1] = time + 1;  
                  }  
              }  
          }  
          return res;  
      }  
        
  }  
```    
    
### 个人解读    
  通过栈进行记录，需要注意起始和结束的时候时间处理。  
    
  总结：  
  1、栈里面只需要存储start的数据  
  2、遇到end时候，需要弹栈，然后修改前一个start时间  
  3、注意+-1的细节  
    
tags:    
  -  栈  
