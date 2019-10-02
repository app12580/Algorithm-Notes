### description    
  A string of '0's and '1's is monotone increasing if it consists of some number of '0's (possibly 0), followed by some number of '1's (also possibly 0.)  
    
  We are given a string S of '0's and '1's, and we may flip any '0' to a '1' or a '1' to a '0'.  
    
  Return the minimum number of flips to make S monotone increasing.  
    
     
    
  Example 1:  
    
  Input: "00110"  
  Output: 1  
  Explanation: We flip the last digit to get 00111.  
  Example 2:  
    
  Input: "010110"  
  Output: 2  
  Explanation: We flip to get 011111, or alternatively 000111.  
  Example 3:  
    
  Input: "00011000"  
  Output: 2  
  Explanation: We flip to get 00000000.  
     
    
  Note:  
    
  1 <= S.length <= 20000  
  S only consists of '0' and '1' characters.  
### solution    
```    
  // 方法一： 遍历  
  Runtime: 202 ms, faster than 5.05% of Java online submissions for Flip String to Monotone Increasing.  
  Memory Usage: 36.3 MB, less than 100.00% of Java online submissions for Flip String to Monotone Increasing.  
    
  class Solution {  
    public int minFlipsMonoIncr(String S) {  
        char[] chars = S.toCharArray();  
        return dfs(chars, 0);  
    }  
  
    private int dfs(char[] chars, int start) {  
        int end = chars.length - 1;  
        if (start > end) {  
            return -1;  
        }  
        int left = start;  
        char pre = chars[start];  
        int count = 1;  
        while (left < end && chars[left + 1] == pre) {  
            left++;  
            count++;  
        }  
        //此时left是最右一个与开始相同的了。  
        if (left == end) {  
            return 0;  
        }  
        int val1 = 0;   //改变当前  
        int val2 = 0;   //当前不变  
        if (pre == '0') {  
            val1 = count + countZero(chars, left + 1);  
            val2 = dfs(chars, left + 1);  
        } else {  
            val1 = count + dfs(chars, left + 1);  
            val2 = countZero(chars, left + 1);  
        }  
        return Math.min(val1, val2);  
    }  
  
    private int countZero(char[] chars, int start) {  
        int res = 0;  
        for (int i = start; i < chars.length; i++) {  
            if (chars[i] == '0') {  
                res++;  
            }  
        }  
        return res;  
    }  
}  
  
// 方法二： 数学法  
Runtime: 380 ms, faster than 5.05% of Java online submissions for Flip String to Monotone Increasing.  
Memory Usage: 36.5 MB, less than 100.00% of Java online submissions for Flip String to Monotone Increasing.  
为毛效率还是这么可怜啊。。。。甚至不如递归。。。  
  
class Solution {  
      public int minFlipsMonoIncr(String S) {  
        char[] chars = S.toCharArray();  
        boolean firstIsZero = chars[0] == '0';  
        List<Integer> list = new ArrayList<>();  
        int l = 0;  
        while (l < S.length()) {  
            char cur = chars[l];  
            int count = 1;  
            while (l < S.length() - 1 && chars[l + 1] == chars[l]) {  
                count++;  
                l++;  
            }  
            l++;  
            if (cur == '0' && firstIsZero) {  
                firstIsZero = false;  
                continue;  
            }  
            if (cur == '1' && l == S.length()) {  
                continue;  
            }  
  
            list.add(count);  
        }  
        int res = Integer.MAX_VALUE;  
        // 101010  
        for (int i = 0; i < list.size(); i++) {  
            int cur = count(list, 0, i, 0) + count(list, i + 1, list.size() - 1, 1);  
            res = Math.min(res, cur);  
        }  
        res = Math.min(res, count(list, 0, list.size() - 1, 1));  
        res = Math.min(res, count(list, 0, list.size() - 1, 0));  
        return res;  
    }  
  
    //flag==0时，表示偶数位上的和，即1的个数  
    private int count(List<Integer> list, int start, int end, int flag) {  
        int res = 0;  
        for (int i = start; i <= end; i++) {  
            if (i % 2 != flag) continue;  
            res += list.get(i);  
        }  
        return res;  
    }  
  
}  
  
//方法三 最优解  
  
Runtime: 7 ms, faster than 43.17% of Java online submissions for Flip String to Monotone Increasing.  
Memory Usage: 36.2 MB, less than 100.00% of Java online submissions for Flip String to Monotone Increasing.  
  
class Solution {  
      public int minFlipsMonoIncr(String S) {  
        int count0 = 0, count1 = 0, n=S.length();  
        for(int i = 0; i < n; ++i) if(S.charAt(i)=='0') count0++;  
        int min = count0;  
        for(int i = 0; i < n; ++i){  
            if(S.charAt(i) =='0') count0--;  
            else count1++;  
            min = Math.min(min, count1+count0);  
        }  
        return min;  
    }  
}  
    
```    
    
### 个人解读    
  感觉这题目好像及见过。类似的有swap两个数组，是两个数组都要达到递增。  
    
  问题核心在于：使用数学方法还是用dp遍历。  
  因为对未来遍历的未知，所以遍历是必须的。  
    
  效率太低了，需要想办法。。。  
    
  想到了一个数学方法，利用只有01两个数字。  
  分析数组，一定是若干个0，然后若干个1，若干个0，然后若干个1，若干个0，然后若干个1。这样排列的。  
    
  010101010....  
  和  
  10101010101....  
    
  然后会发现，每次都是要间隔的去修改。如果0开头，第一个数字不用开。。。  
  纠错：   
  1、如果1结尾，最后一个数字不用管。。  
  2、并不是一定间隔。而是前面0，后面1。如果间隔就全是一样子的数字了。  
    
    
  反思：  
  主要数学核心在于，某一点时，前面1的个数，和后面0的个数。  
  然而这点并不需要与处理得。。。。  
    
tags:    
  -   重点数学  
