### description    
  We are given an array A of N lowercase letter strings, all of the same length.  
    
  Now, we may choose any set of deletion indices, and for each string, we delete all the characters in those indices.  
    
  For example, if we have an array A = ["abcdef","uvwxyz"] and deletion indices {0, 2, 3}, then the final array after deletions is ["bef","vyz"].  
    
  Suppose we chose a set of deletion indices D such that after deletions, the final array has its elements in lexicographic order (A[0] <= A[1] <= A[2] ... <= A[A.length - 1]).  
    
  Return the minimum possible value of D.length.  
    
     
    
  Example 1:  
    
  Input: ["ca","bb","ac"]  
  Output: 1  
  Explanation:   
  After deleting the first column, A = ["a", "b", "c"].  
  Now A is in lexicographic order (ie. A[0] <= A[1] <= A[2]).  
  We require at least 1 deletion since initially A was not in lexicographic order, so the answer is 1.  
  Example 2:  
    
  Input: ["xc","yb","za"]  
  Output: 0  
  Explanation:   
  A is already in lexicographic order, so we don't need to delete anything.  
  Note that the rows of A are not necessarily in lexicographic order:  
  ie. it is NOT necessarily true that (A[0][0] <= A[0][1] <= ...)  
  Example 3:  
    
  Input: ["zyx","wvu","tsr"]  
  Output: 3  
  Explanation:   
  We have to delete every column.  
     
    
  Note:  
    
  1 <= A.length <= 100  
  1 <= A[i].length <= 100  
### solution    
```    
Runtime: 5 ms, faster than 20.27% of Java online submissions for Delete Columns to Make Sorted II.  
Memory Usage: 36.6 MB, less than 100.00% of Java online submissions for Delete Columns to Make Sorted II.  
  
  class Solution {  
   public int minDeletionSize(String[] A) {  
          Set<Integer> set = new HashSet<>();  
          int len = A[0].length();  
          for(int j = 1; j < A.length; j++) {  
              set.add(j);  
          }  
          Set<Integer> cur = new HashSet<>();  
          int res = 0;  
          for(int i = 0; i < len; i++) {  
              boolean delete = false;  
              cur.clear();  
              cur.addAll(set);  
              for(int j: set) {  
                  if(A[j].charAt(i) < A[j - 1].charAt(i)) {  
                      res++;  
                      delete = true;  
                      break;  
                  } else if(A[j].charAt(i) == A[j - 1].charAt(i)) {  
                      cur.add(j);  
                  } else {  
                      cur.remove(j);  
                  }  
              }  
              if(!delete) {  
                  if(cur.size() == 0) return res;  
                  set.clear();  
                  set.addAll(cur);  
              }  
          }  
    
          return res;  
      }  
  }  
```    
    
### 个人解读    
  流水账，从左到右遍历，判断每一行要不要删除。注意标记法，标记出来那些需要判断的行。  
  注意，每一位字符遍历的时候，需要根据是否删除分别操作，如果不删除，需要更新下一次遍历的内容。  
  因此需要两个集合存储信息，一个存储当前循环需要哪些字符串，另一个存储下一次循环需要遍历那些字符串  
    
    
tags:    
  -  流水账  
  -  字符串  
