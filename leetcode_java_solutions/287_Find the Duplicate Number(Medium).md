### description    
  Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.  
    
  Example 1:  
    
  Input: [1,3,4,2,2]  
  Output: 2  
  Example 2:  
    
  Input: [3,1,3,4,2]  
  Output: 3  
  Note:  
    
  You must not modify the array (assume the array is read only).  
  You must use only constant, O(1) extra space.  
  Your runtime complexity should be less than O(n2).  
  There is only one duplicate number in the array, but it could be repeated more than once.  
### solution    
```    
  class Solution {  
       public int findDuplicate(int[] nums) {  
          for(int n: nums) {  
              if(nums[Math.abs(n) - 1] > 0) {  
                  nums[Math.abs(n) - 1] *= -1;  
              } else {  
                  return Math.abs(n);  
              }  
          }  
          return -1;  
      }  
  }  
  
  O(1) 并且不修改原来数组的解法
  二分查找解法：
  
  public int findDuplicate(int[] nums) {
       int l = 1, h = nums.length - 1;
       while (l <= h) {
           int mid = l + (h - l) / 2;
           int cnt = 0;
           for (int i = 0; i < nums.length; i++) {
               if (nums[i] <= mid) cnt++;
           }
           if (cnt > mid) h = mid - 1;
           else l = mid + 1;
       }
       return l;
  }
  
  1 2 2 4 5
  1 2 3 4 5
  解释： cnt为比自己小的个数
  2： cnt = 3， 
  3： cnt = 3
  4： cnt = 4
  二分法还带回调的。。。等着后面写个专题吧
  
  双指针解法，类似于有环链表中找出环的入口：
  
  [3,2,4,5,1,4] 3 5 4 1 2 4 1 2 4 1 2 4 1 2 ... slow = 4
  [1,2,2,3,4]
  public int findDuplicate(int[] nums) {
      int slow = nums[0], fast = nums[nums[0]];
      while (slow != fast) {
          slow = nums[slow];
          fast = nums[nums[fast]];
      }
      //前面的成环了
      fast = 0;
      while (slow != fast) {
          slow = nums[slow];
          fast = nums[fast];
      }
      return slow;
  }
  这种性质的数组会成环
  //        [3,2,4,5,1,4] _ 3 5 4 1 2 4 1 2 4 1 2 4 1 2 ... slow = 4
  为什么啊，成为环了以后，恰好停留的位置，和fast归零以后的位置
  //      [1,6,3,5,3,4,2] _ 1 6 2 3 5 4 3 
  感觉好像数学公式能推导出来
  假设原数组的结构是 a + b * n (每一个环长度为b)
  2x - x = b的倍数  ==> x为b的倍数而且是进了环以后，第一个b的倍数，即：x = (大于a的第一个b的倍数)
  だから！ 归零以后 slow = B1, fast = 0
  然后问题来了，这样子进了环以后，交点那个位置凭什么就是重复的点，因此还需要分析成环的属性
  命题一： 重复元素一定在环里面
  证明： 因为对于i这个数字，分为两个量， i这个值，和i这个索引上的值
  正常来说，链表的索引拿到i这个值以后，下一个就是i这个索引上的值
  A1  f(A1)
  A2  f(A2)
  A3  f(A3)
  A4  f(A4)
  A5  f(A5)
  并且入口是
  0 f(0)
  算了，不想了   
  
  然后回头看了一下，发现一件事情，循环的入口，都恰好是重复的那一个值
  
  思路突然来了： 不从0的入口出发，那么就从特异的那一个点出发
  假设！！！ 如果不是重复，而是刚好1,2,3,4...n。那么，是不能成环的(证明略，大致是因为入口是0，即食Ai->f(Ai)成环，因为入口是0，而环自闭了，没有入口。*自闭*是个关键词诶)，而且到了n之后就会报错，而把n换成重复值的时候，这个时候，就开始成环了
  だから！ 成环以后，第一个节点就是重复点
  
  然后再回头， 数组环属性： a + b * n， 大于a的b的倍数另为c
  所以第一个for循环结束时候，slow走了c步，fast走了2c步
  停留的点为： (c - a) % b 相对于环的点
  fast = 0之后
  在第二循环里面，fast需要先进环，走了a步
  此时：fast = , slow 从(c-a)%b 走了 a步，变成 c % b = 0，刚好在环的入口相交了！！！！！！
  这个算法太神奇了吧！
```    
    
### 个人解读    
  类比[645](645_Set%20Mismatch%20(Easy).md)，Medium和Easy一模一样的算法  
  关键都在于，特殊的nums属性，从1,2,3,4...n。  
  可以通过数组自身作为存储结果，保存处理过的标记  
  
  刚刚发现，不让创建额外数组，也不让修改原数组内容
  
  
    
tags:    
  -   数组    
  -   自然数组     
  -   数组嵌套       
  -   链表成环       
