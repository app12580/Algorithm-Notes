### description
A string S of lowercase letters is given. We want to partition this string into as many parts as possible so that each letter appears in at most one part, and return a list of integers representing the size of these parts.

Example 1:
Input: S = "ababcbacadefegdehijhklij"
Output: [9,7,8]
Explanation:
The partition is "ababcbaca", "defegde", "hijhklij".
This is a partition so that each letter appears in at most one part.
A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.
Note:

S will have length in range [1, 500].
S will consist of lowercase letters ('a' to 'z') only.
### solution
```
class Solution {
    public List<Integer> partitionLabels(String S) {
        List<Integer> res = new ArrayList<>();
        int[] lastIndexArr = new int[26];
        for(int i = 0; i < S.length(); i++) {
            lastIndexArr[char2Index(S.charAt(i))] = i;
        }
        int index = 0;
        int lastIndex = 0;
        while(index < S.length()) {
            lastIndex = index;
            for(int i = index; i < S.length() && i <= lastIndex; i++) {
                char c = S.charAt(i);
                lastIndex = Math.max(lastIndex, lastIndexArr[char2Index(c)]);
            }
            res.add(lastIndex - index + 1);
            index = lastIndex + 1;
        }
        return res;
    }
    private int char2Index(char c) {
        return c - 'a';
    }
}
```

### 个人解读
因为字母只有a-z，可以考虑创建一个26长度的数组
问题： 数组里面要存放什么信息：
猜测： 有两个数组，分别存放该字母第一次出现和最后一次的索引
然后这样就会有了26个区间，
ababcac dedede
[0,5],[1,3],[4, 6],[7,11],[8,12]
划分后：[0, 6] [7, 12]
输出答案：[7,6]
然后开始想办法优化：
思考一下[0,5] [1,3] [4,6] 合并的过程，会发现，本算法的核心是要不断的去把区间里面的出现的字母的右边界填充进来，并且及时[2]的位置不是第一次出现，但是仍然可以多算一遍，去找[2]的最后一次坐标
而且还会意识到，第一次出现索引并没有什么用，反正都是要从头走一遍的

tags:
  - 数组
  - 贪心算法
