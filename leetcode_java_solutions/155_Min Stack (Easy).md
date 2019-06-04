### description    
  Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.  
    
  push(x) -- Push element x onto stack.  
  pop() -- Removes the element on top of the stack.  
  top() -- Get the top element.  
  getMin() -- Retrieve the minimum element in the stack.  
  Example:  
  MinStack minStack = new MinStack();  
  minStack.push(-2);  
  minStack.push(0);  
  minStack.push(-3);  
  minStack.getMin();   --> Returns -3.  
  minStack.pop();  
  minStack.top();      --> Returns 0.  
  minStack.getMin();   --> Returns -2.  
### solution    
```    
  class MinStack {  
    
      private Stack<Integer> dataStack = new Stack<>();  
      private Stack<Integer> minStack= new Stack<>();  
        
        
      /** initialize your data structure here. */  
      public MinStack() {  
    
      }  
    
      public void push(int x) {  
          dataStack.push(x);  
          int min = Integer.MAX_VALUE;  
          if(!minStack.isEmpty()) {  
              min = minStack.peek();  
          }  
          minStack.push(Math.min(min, x));  
      }  
    
      public void pop() {  
          dataStack.pop();  
          minStack.pop();  
      }  
    
      public int top() {  
          return dataStack.peek();  
      }  
    
      public int getMin() {  
          return minStack.peek();  
      }  
  }  
    
  /**  
   * Your MinStack object will be instantiated and called as such:  
   * MinStack obj = new MinStack();  
   * obj.push(x);  
   * obj.pop();  
   * int param_3 = obj.top();  
   * int param_4 = obj.getMin();  
   */  
```    
    
### 个人解读    
  最小值栈比起普通的栈多了一个获取最小值的功能，由于每次取最小值时候，全部取出来循环遍历不太可靠。所以需要数据结构存储最小值信息。  
  因为如果用单int存储最小值后，等到pop以后，还需要啊再找最小值，所以最小值必须要存取一段的最小值信息。  
  最小值信息可以存储成每次都存一个，也可以只在min发生变化时候再存储。感觉前者耗空间但是更方便。  
    
tags:    
  -  模拟  
  -  分析中间变量  
