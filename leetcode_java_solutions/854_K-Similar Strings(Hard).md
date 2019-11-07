### description      
  Strings A and B are K-similar (for some non-negative integer K) if we can swap the positions of two letters in A exactly K times so that the resulting string equals B.    
      
  Given two anagrams A and B, return the smallest K for which A and B are K-similar.    
      
  Example 1:    
      
  Input: A = "ab", B = "ba"    
  Output: 1    
  Example 2:    
      
  Input: A = "abc", B = "bca"    
  Output: 2    
  Example 3:    
      
  Input: A = "abac", B = "baca"    
  Output: 2    
  Example 4:    
      
  Input: A = "aabc", B = "abca"    
  Output: 2    
  Note:    
      
  1 <= A.length == B.length <= 20    
  A and B contain only lowercase letters from the set {'a', 'b', 'c', 'd', 'e', 'f'}    
### solution      
```      
    
//方法一： BFS ， 应用一个队列存储每次可能的变化结果。。    
Runtime: 22 ms, faster than 72.88% of Java online submissions for K-Similar Strings.    
Memory Usage: 37.4 MB, less than 100.00% of Java online submissions for K-Similar Strings.    
Next challenges:    
    
  class Solution {    
         public int kSimilarity(String A, String B) {  
               if (A.equals(B)) return 0;  
               Set<String> vis= new HashSet<>();  
               Queue<String> q= new LinkedList<>();  
               q.add(A);  
               vis.add(A);  
               int res=0;  
               while(!q.isEmpty()){  
                   res++;  
                   for (int sz=q.size(); sz>0; sz--){  
                       String cur= q.poll();  
                       int i=0;  
                       while (cur.charAt(i)==B.charAt(i)) i++;   //找出第一个不一样的i  
                       for (int j=i+1; j<cur.length(); j++){  
                           if (cur.charAt(j)==B.charAt(j) || cur.charAt(j)!=B.charAt(i) ) continue;    //如果两个字符相同或者j换到i的位置不是正确结果，就跳过  
                           String temp= swap(cur, i, j);  
                           if (temp.equals(B)) return res;  
                           if (vis.add(temp)) q.add(temp);  
                       }  
                   }  
               }  
               return res;  
           }  
           public String swap(String s, int i, int j){  
               char[] ca=s.toCharArray();  
               char temp=ca[i];  
               ca[i]=ca[j];  
               ca[j]=temp;  
               return new String(ca);  
           }  
  }    
```      
      
### 个人解读      
  返回String A 经过几次swap能变成String B    
    
  突然想到一个词，循环交换。    
  假设输入的两个字符串一定有结果。    
  abc    
  bac    
  cab    
      
  abc和bac只有前两个组成了闭合圈，所以K=1    
  abc和cab是三个组成了闭合圈，所以K=2    
      
  接下来关于循环交流圈的一个重要结论：    
  1、可以有多种匹配方式，但是总个数是不变的。    
  2、错误结论： 只要顺着这样斜着的一个一个走下去，最后一定能收束的(真实情况并不能一定会收束)    
  3、但是本题目，如果以字符为基准的话，那么就要在循环的时候，不能让重复的字符进入当前圈。    
      
  找循环圈好麻烦啊。。。主要是通过字符作为基础。    
      
  思路错误。。。好尴尬：    
  错误示例：    
  "cdacbeebad"  "aabbccddee"    
  Output  7    
  Expected   6    
      
  接下来继续挣扎一下，看看有没有根据奇偶成圈的说法，来计算结果    
      
  联想：    
  \\\\\    
   \\\\\     
       
   总结： 想了一堆高级思路，最后还是基础的BFS解决问题    
      
  ```    
  //失败算法    
      
   public int kSimilarity(String A, String B) {    
          Map<Character, List<String>> map = new HashMap<>();    
          int count = 0;    
          int cycle = 0;    
          for (int i = 0; i < A.length(); i++) {    
              if (A.charAt(i) != B.charAt(i)) {    
                  count++;    
                  if (!map.containsKey(A.charAt(i))) {    
                      map.put(A.charAt(i), new ArrayList<>());    
                  }    
                  map.get(A.charAt(i)).add("" + A.charAt(i) + B.charAt(i));    
              }    
          }    
          cycle = helper(map, count);    
          return count - cycle;    
      }    
      
      private int helper(Map<Character, List<String>> map, int count) {    
          List<String> curList;    
          int res = 0;    
          int odd = 0;    
          while (count > 0) {    
              Set<Character> seen = new HashSet<>();    
              curList = new ArrayList<>();    
              Character cur = map.keySet().iterator().next();    // 假设是a    
              seen.add(cur);    
              while (true) {    
                  String next = map.get(cur).get(0);    
                  if (seen.contains(next.charAt(1))) {    
                      char same = next.charAt(1);    
                      res++;    
                      int index = 0;  //找出index是从same出发的    
                      while (index < curList.size()) {    
                          if (curList.get(index).charAt(0) == same) break;    
                          index++;    
                      }    
                      curList.add(next);    
                      count -= (curList.size() - index);    
                      if((curList.size() - index) % 2 == 1) odd++;    
                      System.out.println(res);    
                      for (int k = index; k < curList.size(); k++) {    
                          String s = curList.get(k);    
                          System.out.println(s);    
                          char c1 = s.charAt(0);    
                          map.get(c1).remove(s);    
                          if (map.get(c1).size() == 0) map.remove(c1);    
                      }    
                      break;    
                  } else {    
                      curList.add(next);    
                      seen.add(next.charAt(1));    
                      cur = next.charAt(1);    
                  }    
      
              }    
      
          }    
      
          return res + odd / 2;    
      }    
  ```     
       
      
tags:      
  -  字符串    
  -  BFS    
