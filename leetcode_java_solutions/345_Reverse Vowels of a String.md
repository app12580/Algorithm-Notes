### description
Write a function that takes a string as input and reverse only the vowels of a string.

Example 1:

Input: "hello"
Output: "holle"
Example 2:

Input: "leetcode"
Output: "leotcede"
Note:
The vowels does not include the letter "y".

### solution
```
class Solution {
    public String reverseVowels(String s) {
          Character[] charArr = new Character[]{'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'};
        List<Character> charList = new ArrayList<Character>(Arrays.asList(charArr));
        char[] res = s.toCharArray();
        int i = 0;
        int j = s.length() - 1;
        while(i < j) {
            if(!charList.contains((Character)res[i])) {
                i++;
            } else if(!charList.contains(res[j])) {
                j--;
            } else {
                char t = res[i];
                res[i] = res[j];
                res[j] = t;
                i++;
                j--;
            }
        }
        return new String(res);
    }
}
```

### 个人解读
注意数组与集合的转换，尽量不要使用基本数据类型，因为集合对基本数据类型兼容性差

tags:
  - 数组
  - 双指针
  - 字符串
