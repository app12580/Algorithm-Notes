### description    
  The set [1,2,3,...,n] contains a total of n! unique permutations.  
    
  By listing and labeling all of the permutations in order, we get the following sequence for n = 3:  
    
  "123"  
  "132"  
  "213"  
  "231"  
  "312"  
  "321"  
  Given n and k, return the kth permutation sequence.  
    
  Note:  
    
  Given n will be between 1 and 9 inclusive.  
  Given k will be between 1 and n! inclusive.  
  Example 1:  
    
  Input: n = 3, k = 3  
  Output: "213"  
  Example 2:  
    
  Input: n = 4, k = 9  
  Output: "2314"  
    
  返回全排列中第k小的那一个  
### solution    
```    
  
Runtime: 1 ms, faster than 99.36% of Java online submissions for Permutation Sequence.  
Memory Usage: 34.5 MB, less than 100.00% of Java online submissions for Permutation Sequence.  
  
class Solution {  
      public String getPermutation(int n, int k) {  
        List<Integer> list = new ArrayList<>();  
        for(int i = 1; i <= n; i++) {  
            list.add(i);  
        }  
        StringBuilder builder = new StringBuilder();  
        int rest = k;  
        while(builder.length() < n) {  
            int t = helper(list, rest, builder);  
            rest -= t;  
        }  
        return builder.toString();  
    }  
  
    private int helper(List<Integer> list, int k, StringBuilder builder) {  
        int pre = 0;  
        int index = 0;  
        int multi = 1;  
        for(int i = 1; i < list.size(); i++) {  
            multi *= i;  
        }  
        //计算index  
        while(pre + multi < k) {        //注意这里的不带等号  
            index++;  
            pre += multi;  
        }  
        builder.append(list.get(index));  
        list.remove(index);  
        return index * multi;  
    }  
}  
    
```    
    
### 个人解读    
  弄一个辅助函数，每次只确定第一个是几，然后递归下去。  
  需要注意细节：index的确认，rest那里的写法，辅助函数的返回值。  
    
tags:    
  -  全排列  
