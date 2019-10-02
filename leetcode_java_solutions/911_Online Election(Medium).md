### description    
  In an election, the i-th vote was cast for persons[i] at time times[i].  
    
  Now, we would like to implement the following query function: TopVotedCandidate.q(int t) will return the number of the person that was leading the election at time t.    
    
  Votes cast at time t will count towards our query.  In the case of a tie, the most recent vote (among tied candidates) wins.  
    
     
    
  Example 1:  
    
  Input: ["TopVotedCandidate","q","q","q","q","q","q"], [[[0,1,1,0,0,1,0],[0,5,10,15,20,25,30]],[3],[12],[25],[15],[24],[8]]  
  Output: [null,0,1,1,0,0,1]  
  Explanation:   
  At time 3, the votes are [0], and 0 is leading.  
  At time 12, the votes are [0,1,1], and 1 is leading.  
  At time 25, the votes are [0,1,1,0,0,1], and 1 is leading (as ties go to the most recent vote.)  
  This continues for 3 more queries at time 15, 24, and 8.  
     
    
  Note:  
    
  1 <= persons.length = times.length <= 5000  
  0 <= persons[i] <= persons.length  
  times is a strictly increasing array with all elements in [0, 10^9].  
  TopVotedCandidate.q is called at most 10000 times per test case.  
  TopVotedCandidate.q(int t) is always called with t >= times[0].  
    
    
### solution    
```    
  
// 方法一：  
Runtime: 178 ms, faster than 27.63% of Java online submissions for Online Election.  
Memory Usage: 73.2 MB, less than 15.63% of Java online submissions for Online Election.  
    
class TopVotedCandidate {  
  
    private TreeMap<Integer, Integer> timeMap = new TreeMap<>();  //key是时间  
  
    public TopVotedCandidate(int[] persons, int[] times) {  
        //预处理时候，需要统计当前节点各个数字的个数。  
        //经过思考后发现，这种属于定向遍历结果，不需要treeMap中排序，用int足以。  
        int maxCount = 0;  
        int val = -1;  
        Map<Integer, Integer> map = new HashMap<>();  
        for (int i = 0; i < times.length; i++) {  
            int time = times[i];  
            int p = persons[i];  
            int cur = map.getOrDefault(p, 0) +  1;  // error: 少了+1  
            if (cur >= maxCount) {  
                maxCount = cur;  
                val = p;  
            }  
            map.put(p, cur);        // error: 少了这行  
            timeMap.put(time, val);  
        }  
    }  
  
    public int q(int t) {  
        Map.Entry<Integer, Integer> entry = timeMap.floorEntry(t);      //注意是floor还是ceil  
        return entry.getValue();  
    }  
}  
```    
    
### 个人解读    
  最关键的在于用什么数据结果去存储。  
  然后是思路，如何处理多次调用的问题。  
  思路一： 首先预处理，把每个插入时间节点的情况存储进来，之后，每次调用时，使用二分法去找大于等于目标的最左值。  
  数据结构使用TreeMap这种自带排序的比较好。TreeMap<Integer, List<Integer>  
    
  总结： 这种题目，按照套路能做出来就完事了，先不去关效率咋样。。    
    
tags:    
  -  TreeMap  
