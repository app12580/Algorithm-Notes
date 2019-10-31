### description    
  You have k lists of sorted integers in ascending order. Find the smallest range that includes at least one number from each of the k lists.  
    
  We define the range [a,b] is smaller than range [c,d] if b-a < d-c or a < c if b-a == d-c.  
    
     
    
  Example 1:  
    
  Input: [[4,10,15,24,26], [0,9,12,20], [5,18,22,30]]  
  Output: [20,24]  
  Explanation:   
  List 1: [4, 10, 15, 24,26], 24 is in range [20,24].  
  List 2: [0, 9, 12, 20], 20 is in range [20,24].  
  List 3: [5, 18, 22, 30], 22 is in range [20,24].  
     
    
  Note:  
    
  The given list may contain duplicates, so ascending order means >= here.  
  1 <= k <= 3500  
  -105 <= value of elements <= 105.  
### solution    
```    
// 方法二： 优化后  
Runtime: 27 ms, faster than 93.69% of Java online submissions for Smallest Range Covering Elements from K Lists.  
Memory Usage: 50.7 MB, less than 72.97% of Java online submissions for Smallest Range Covering Elements from K Lists.  
  
class Solution {  
    
     public int[] smallestRange(List<List<Integer>> nums) {  
        //根据val从小到大  
        PriorityQueue<Element> pq = new PriorityQueue<Element>(new Comparator<Element>() {  
            public int compare(Element a, Element b) {  
                return a.val - b.val;  
            }  
        });  
        int max = Integer.MIN_VALUE;  
        for (int i = 0; i < nums.size(); i++) {  
            Element e = new Element(i, 0, nums.get(i).get(0));  
            pq.offer(e);  
            max = Math.max(max, nums.get(i).get(0));  
        }  
        int range = Integer.MAX_VALUE;  
        int start = -1, end = -1;  
        while (pq.size() == nums.size()) {  
  
            Element curr = pq.poll();  
            if (max - curr.val < range) {  
                range = max - curr.val;  
                start = curr.val;  
                end = max;  
            }  
            if (curr.idx + 1 < nums.get(curr.row).size()) {  
                curr.idx = curr.idx + 1;  
                curr.val = nums.get(curr.row).get(curr.idx);  
                pq.offer(curr);  
                if (curr.val > max) {  
                    max = curr.val;  
                }  
            }  
        }  
  
        return new int[] { start, end };  
    }  
  
  
    class Element {  
        int val;  
        int idx;  
        int row;  
  
        // row表示第几个list，idx表示list中的索引  
        public Element(int r, int i, int v) {  
            val = v;  
            idx = i;  
            row = r;  
        }  
    }  
  
      
      
}  
  
// 方法一：滑动窗口  
效率虽然低，不过能通过就不错了。。。  
Runtime: 102 ms, faster than 7.71% of Java online submissions for Smallest Range Covering Elements from K Lists.  
Memory Usage: 58.5 MB, less than 13.51% of Java online submissions for Smallest Range Covering Elements from K Lists.  
  
  class Solution {  
      
        
      public int[] smallestRange(List<List<Integer>> nums) {  
          int len = nums.size();  
          int[] counts = new int[len];  
          List<int[]> totalList = new ArrayList<>();  
          for(int i = 0; i < len; i++) {  
              List<Integer> list = nums.get(i);  
              for(int l: list) {  
                  totalList.add(new int[]{l, i});  
              }  
          }  
          Collections.sort(totalList, (p1, p2) -> p1[0] - p2[0]);  
          int left = 0;  
          int right = 0;  
          int minLen = Integer.MAX_VALUE;  
          int[] res = new int[2];  
          while(right < totalList.size()) {  
              int[] cur = totalList.get(right);  
              int index = cur[1];  
              counts[index]++;  
              //如果全覆盖了，需要比较res，然后打破counts  
              while(check(counts)) {  
                  int curLen = totalList.get(right)[0] - totalList.get(left)[0];  
                  if(curLen < minLen) {  
                      minLen = curLen;  
                      res[0] = totalList.get(left)[0];  
                      res[1] = totalList.get(right)[0];  
                  }  
                  int l = totalList.get(left++)[1];  
                  counts[l]--;  
              }  
              right++;  
          }  
          return res;  
      }  
    
      private boolean check(int[] counts) {  
          for(int c: counts) {  
              if(c < 1) return false;  
          }  
          return true;  
      }  
    
        
        
  }  
```    
    
### 个人解读    
  最大的一个疑问，能不能归并？  
  如果不能的话，只能放在一块讨论、  
    
  思路一：  
  用一个排序数据结构存储全部的int，value是所属的List下标。然后类似于滑动窗口的做法？  
    
  思路二：  
  优化。更新窗口时候不是整体滑动，而是哪个false了，更新哪一个，这样可以减少check的操作。  
    
tags:    
  -  数学  
  -  滑动窗口  
