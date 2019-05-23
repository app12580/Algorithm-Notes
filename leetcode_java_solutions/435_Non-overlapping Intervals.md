### description
Given a collection of intervals, find the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.

Note:

You may assume the interval's end point is always bigger than its start point.
Intervals like [1,2] and [2,3] have borders "touching" but they don't overlap each other.
 

Example 1:
Input: [ [1,2], [2,3], [3,4], [1,3] ]
Output: 1
Explanation: [1,3] can be removed and the rest of intervals are non-overlapping.
 

Example 2:
Input: [ [1,2], [1,2], [1,2] ]
Output: 2
Explanation: You need to remove two [1,2] to make the rest of intervals non-overlapping.
 

Example 3:
Input: [ [1,2], [2,3] ]
Output: 0
Explanation: You don't need to remove any of the intervals since they're already non-overlapping.
NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
### solution
```
public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        int count = 0;
        int end = Integer.MIN_VALUE;
        for(int[] interval: intervals) {
            if(interval[0] >= end) {        //这个地方第一次写的时候落了"=="的情况
                count++;
                end = interval[1];
            }
        }
        return intervals.length - count;
    }
```

### 个人解读
属于最优解问题：
思路一：动态规划
思路二：贪心算法
由于本题中f(n+1)不光要与f(n)关联，还要与更往前的进行关联，所以可能性不大
那么贪心算法的话，就要选择一个条件，使得在循环过程中，让某某指标尽可能的小
因为本题要找互不关联的区间个数，所以可以联想到从0开始遍历时，让末尾最小
所以让区间以末尾进行从小到大排序，然后从左往右开始遍历下标，如果当前区间的左端>=前面若干个区间的末尾，就把当前区间算进来

证明贪心算法合理性：
在某一时刻，新的区间左端<前面的末尾了，如果此时不遵循贪心算法，就需要至少替换前面一个区间，这样做最好的结果也是与贪心算法持平
所以该贪心算法合理


tags:
  - 数组
  - 双指针
