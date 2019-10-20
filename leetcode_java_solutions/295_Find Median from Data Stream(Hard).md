### description    
  Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.  
    
  For example,  
  [2,3,4], the median is 3  
    
  [2,3], the median is (2 + 3) / 2 = 2.5  
    
  Design a data structure that supports the following two operations:  
    
  void addNum(int num) - Add a integer number from the data stream to the data structure.  
  double findMedian() - Return the median of all elements so far.  
     
    
  Example:  
    
  addNum(1)  
  addNum(2)  
  findMedian() -> 1.5  
  addNum(3)   
  findMedian() -> 2  
     
    
  Follow up:  
    
  If all integer numbers from the stream are between 0 and 100, how would you optimize it?  
  If 99% of all integer numbers from the stream are between 0 and 100, how would you optimize it?  
### solution    
```    
Runtime: 127 ms, faster than 18.58% of Java online submissions for Find Median from Data Stream.  
Memory Usage: 66.8 MB, less than 54.24% of Java online submissions for Find Median from Data Stream.      
  
   class MedianFinder {  
    
          private PriorityQueue<Integer> left = new PriorityQueue<>(Collections.reverseOrder());  
          private PriorityQueue<Integer> right = new PriorityQueue<>();  
          private boolean even = true;  
    
          public void addNum(int num) {  
              if(even) {  
                  right.offer(num);  
                  left.offer(right.poll());  
              } else {  
                  left.offer(num);  
                  right.offer(left.poll());  
              }  
              even = !even;  
          }  
    
          public double findMedian() {  
              if(even) {  
                  return (double)(right.peek() + left.peek()) / 2;  
              } else {  
                  return left.peek();  
              }  
          }  
      }  
        
      //方法二： 自己构造一个可以自由左右移动的排序结构  
      Runtime: 110 ms, faster than 76.41% of Java online submissions for Find Median from Data Stream.  
      Memory Usage: 66.3 MB, less than 54.24% of Java online submissions for Find Median from Data Stream.  
        
      class MedianFinder {  
          class TreeNode{  
              int val;  
              TreeNode parent,left,right;  
              TreeNode(int val, TreeNode p){  
                  this.val=val;  
                  this.parent=p;  
                  left=null;  
                  right=null;  
              }  
              void add(int num){  
                  if(num>=val){  
                      if(right==null)  
                          right=new TreeNode(num,this);  
                      else  
                          right.add(num);  
                  }else{  
                      if(left==null)  
                          left=new TreeNode(num,this);  
                      else  
                          left.add(num);  
                  }  
              }  
              TreeNode next(){  
                  TreeNode ret;  
                  if(right!=null){  
                      ret=right;  
                      while(ret.left!=null)  
                          ret=ret.left;  
                  }else{  
                      ret=this;  
                      while(ret.parent.right==ret)  
                          ret=ret.parent;  
                      ret=ret.parent;  
                  }  
                  return ret;  
              }  
              TreeNode prev(){  
                  TreeNode ret;  
                  if(left!=null){  
                      ret=left;  
                      while(ret.right!=null)  
                          ret=ret.right;  
                  }else{  
                      ret=this;  
                      while(ret.parent.left==ret)  
                          ret=ret.parent;  
                      ret=ret.parent;  
                  }  
                  return ret;  
              }  
          }  
          int n;  
          TreeNode root, curr;  
          // Adds a number into the data structure.  
          public void addNum(int num) {  
              if(root==null){  
                  root = new TreeNode(num,null);  
                  curr=root;  
                  n=1;  
              }else{  
                  root.add(num);  
                  n++;  
                  if(n%2==1){  
                      if(curr.val<=num)  
                          curr=curr.next();  
                  }else  
                      if(curr.val>num)  
                          curr=curr.prev();  
              }  
          }  
        
          // Returns the median of current data stream  
          public double findMedian() {  
              if(n%2==0){  
                  return ((double)curr.next().val+curr.val)/2;  
              }else  
                  return curr.val;  
          }  
      };  
```    
    
### 个人解读    
  这种Hard题很多时候是要考察各种优化项目  
  
  思路一： 流水账做法，使用某种数据结果，存储全部数据结果。  
  坚定一个知识点：java里面，同时自排序，并且可以同时任意访问的数据结构是不存在的。  
    
  思路二：  
  现在目标是获取中间的数字，因为自排序只能获取头部，所以采用两个优先队列来设置。  
    
    
    
tags:    
  -  数据结构  
  -  PriorityQueue  
