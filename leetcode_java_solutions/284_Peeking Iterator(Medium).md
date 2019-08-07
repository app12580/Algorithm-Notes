### description    
  Given an Iterator class interface with methods: next() and hasNext(), design and implement a PeekingIterator that support the peek() operation -- it essentially peek() at the element that will be returned by the next call to next().  
    
  Example:  
    
  Assume that the iterator is initialized to the beginning of the list: [1,2,3].  
    
  Call next() gets you 1, the first element in the list.  
  Now you call peek() and it returns 2, the next element. Calling next() after that still return 2.   
  You call next() the final time and it returns 3, the last element.   
  Calling hasNext() after that should return false.  
  Follow up: How would you extend your design to be generic and work with all types, not just integer?  
### solution    
```    
  import java.util.NoSuchElementException;  
  class PeekingIterator implements Iterator<Integer> {  
      Integer next;  
      Iterator<Integer> iter;  
      boolean noSuchElement;  
    
      public PeekingIterator(Iterator<Integer> iterator) {  
          // initialize any member here.  
          iter = iterator;  
          advanceIter();  
      }  
    
      // Returns the next element in the iteration without advancing the iterator.  
      public Integer peek() {  
          // you should confirm with interviewer what to return/throw  
          // if there are no more values  
          return next;  
      }  
    
      // hasNext() and next() should behave the same as in the Iterator interface.  
      // Override them if needed.  
      @Override  
      public Integer next() {  
          if (noSuchElement)  
              throw new NoSuchElementException();  
          Integer res = next;  
          advanceIter();  
          return res;  
      }  
    
      @Override   
      public boolean hasNext() {  
          return !noSuchElement;  
      }  
        
      private void advanceIter() {  
          if (iter.hasNext()) {  
              next = iter.next();  
          } else {  
              noSuchElement = true;  
          }  
      }  
  }  
```    
    
### 个人解读    
  就弄两套遍历的结构就好了，但是问题在于iterator只能遍历一次，之后就用不了了。  
  这种题目描述发现总是看不懂啊。。。。题目描述的例子根本就没啥卵用，都说不到关键点上。  
    
  问题：连续调用peek()是否会返回不同值？  
  答案：返回相同的值。  
    
  核心思想：每次都要先偷偷摸摸的把next()调用了,然后peek()的时候只返回一个固定的成员变量。  
    
tags:    
  -  模拟  
  -  Iterator  
