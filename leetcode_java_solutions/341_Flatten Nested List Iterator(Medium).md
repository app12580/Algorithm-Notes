### description    
  Given a nested list of integers, implement an iterator to flatten it.  
    
  Each element is either an integer, or a list -- whose elements may also be integers or other lists.  
    
  Example 1:  
    
  Input: [[1,1],2,[1,1]]  
  Output: [1,1,2,1,1]  
  Explanation: By calling next repeatedly until hasNext returns false,   
               the order of elements returned by next should be: [1,1,2,1,1].  
  Example 2:  
    
  Input: [1,[4,[6]]]  
  Output: [1,4,6]  
  Explanation: By calling next repeatedly until hasNext returns false,   
               the order of elements returned by next should be: [1,4,6].  
### solution    
```    
  public class NestedIterator implements Iterator<Integer> {  
      Stack<NestedInteger> stack;  
    
      public NestedIterator(List<NestedInteger> nestedList) {  
          stack = new Stack<>();  
          for(int i = nestedList.size() - 1; i >= 0; i--) {  
              stack.push(nestedList.get(i));  
          }  
      }  
    
      @Override  
      public Integer next() {  
          return stack.pop().getInteger();  
      }  
    
      @Override  
      public boolean hasNext() {  
          if(stack.isEmpty()) return false;  
          NestedInteger peek = stack.peek();  
          if(peek.isInteger()) {  
              return true;  
          } else {  
              List<NestedInteger> list = stack.pop().getList();  
              for(int i = list.size() - 1; i >= 0; i--) {  
                  stack.push(list.get(i));  
              }  
              return hasNext();  
          }  
      }  
  }  
```    
    
### 个人解读    
  这种题目首先需要思考的是用什么数据结构好？  
  反正基本要么是栈，要么是队列。本题目感觉使用栈合适一些。  
    
  需要注意null的情况，可能NestedInteger不为空，但是int为空，所以需要在hasNext里面就做好准备。  
    
tags:    
  -  模拟  
