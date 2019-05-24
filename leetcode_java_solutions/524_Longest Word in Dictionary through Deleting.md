### description  
Given a string and a string dictionary, find the longest string in the dictionary that can be formed by deleting some characters of the given string. If there are more than one possible results, return the longest word with the smallest lexicographical order. If there is no possible result, return the empty string.  
  
Example 1:  
Input:  
s = "abpcplea", d = ["ale","apple","monkey","plea"]  
  
Output:   
"apple"  
Example 2:  
Input:  
s = "abpcplea", d = ["a","b","c"]  
  
Output:   
"a"  
  
给定一个字符串和一个字符串字典，找到字典里面最长的字符串，该字符串可以通过删除给定字符串的某些字符来得到。如果答案不止一个，返回长度最长且字典顺序最小的字符串。如果答案不存在，则返回空字符串。  
  
### solution  
方法一： CS-NOTES中的解法  
```  
public String findLongestWord(String s, List<String> d) {  
    String longestWord = "";  
    for (String target : d) {  
        int l1 = longestWord.length(), l2 = target.length();  
        if (l1 > l2 || (l1 == l2 && longestWord.compareTo(target) < 0)) {  
            continue;  
        }  
        if (isSubstr(s, target)) {  
            longestWord = target;  
        }  
    }  
    return longestWord;  
}  
  
private boolean isSubstr(String s, String target) {  
    int i = 0, j = 0;  
    while (i < s.length() && j < target.length()) {  
        if (s.charAt(i) == target.charAt(j)) {  
            j++;  
        }  
        i++;  
    }  
    return j == target.length();  
}  
```  
```  
方法一的效率：  
Runtime: 16 ms, faster than 90.34% of Java online submissions for Longest Word in Dictionary through Deleting.  
Memory Usage: 36.9 MB, less than 99.16% of Java online submissions for Longest Word in Dictionary through Deleting.  
```  
  
方法二：  
```  
class Solution {  
    public String findLongestWord(String s, List<String> d) {  
        //中间结构有：是否继续的bool[]，进度int[]  
        int size = d.size();  
        boolean[] flags = new boolean[size];    //是否跳过该目标字符串  
        int[] progress = new int[size];  
        String longestWord = "";  
        for(int i = 0; i < s.length(); i++) {  
            char cur = s.charAt(i);  
            for(int j = 0; j < size; j++) {  
                if(flags[j]) {  
                    continue;  
                }  
                //比较长度和字典排序  
                String target = d.get(j);  
                //如果目标短，或者长度相等但是排在longestWord后面  
                if(target.length() < longestWord.length() || target.length() == longestWord.length() && target.compareTo(longestWord) > 0) {  
                    flags[j] = true;  
                    continue;  
                }  
                //比较  
                if(cur == target.charAt(progress[j])) {  
                    progress[j] ++;  
                }  
                if(progress[j] == target.length()) {  
                    longestWord = target;  
                    flags[j] = true;  
                }  
  
            }  
        }  
        return longestWord;  
    }  
}  
```  
```  
方法二效率：  
Runtime: 46 ms, faster than 28.97% of Java online submissions for Longest Word in Dictionary through Deleting.  
Memory Usage: 36.7 MB, less than 99.38% of Java online submissions for Longest Word in Dictionary through Deleting.  
```  
  
刚开始的感觉：循环分为循环单元和中间存储结构，然而方法一这种做法，放弃了中间存储过程，或者说每次循环，只是比较了单个目标字符串，感觉效率并不高。  
然而被事实打脸了。。。。  
意思是，虽然中间存储过程干的事情多了，但不是越多越好啊  
无味的操作太多了，有这个功夫，其他算法都跑完好几圈了  
### 个人解读  
  
  
tags:  
  - 数组  
  - 双指针  
