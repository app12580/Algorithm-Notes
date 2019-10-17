### description    
  Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s) from beginWord to endWord, such that:  
    
  Only one letter can be changed at a time  
  Each transformed word must exist in the word list. Note that beginWord is not a transformed word.  
  Note:  
    
  Return an empty list if there is no such transformation sequence.  
  All words have the same length.  
  All words contain only lowercase alphabetic characters.  
  You may assume no duplicates in the word list.  
  You may assume beginWord and endWord are non-empty and are not the same.  
  Example 1:  
    
  Input:  
  beginWord = "hit",  
  endWord = "cog",  
  wordList = ["hot","dot","dog","lot","log","cog"]  
    
  Output:  
  [  
    ["hit","hot","dot","dog","cog"],  
    ["hit","hot","lot","log","cog"]  
  ]  
  Example 2:  
    
  Input:  
  beginWord = "hit"  
  endWord = "cog"  
  wordList = ["hot","dot","dog","lot","log"]  
    
  Output: []  
    
  Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.    
### solution    
```    
Runtime: 88 ms, faster than 68.76% of Java online submissions for Word Ladder II.  
Memory Usage: 49.9 MB, less than 48.08% of Java online submissions for Word Ladder II.  
  
  class Solution {  
  public List<List<String>> findLadders(String start, String end, List<String> wordList) {  
     HashSet<String> dict = new HashSet<String>(wordList);  
     List<List<String>> res = new ArrayList<List<String>>();           
     HashMap<String, ArrayList<String>> nodeNeighbors = new HashMap<String, ArrayList<String>>();// Neighbors for every node  
     HashMap<String, Integer> distance = new HashMap<String, Integer>();// Distance of every node from the start node  
     ArrayList<String> solution = new ArrayList<String>();  
    
     dict.add(start);            
     bfs(start, end, dict, nodeNeighbors, distance);                   
     dfs(start, end, dict, nodeNeighbors, distance, solution, res);     
     return res;  
  }  
    
  // BFS: Trace every node's distance from the start node (level by level).  
  private void bfs(String start, String end, Set<String> dict, HashMap<String, ArrayList<String>> nodeNeighbors, HashMap<String, Integer> distance) {  
    for (String str : dict)  
        nodeNeighbors.put(str, new ArrayList<String>());  
    
    Queue<String> queue = new LinkedList<String>();  
    queue.offer(start);  
    distance.put(start, 0);  
    
    while (!queue.isEmpty()) {  
        int count = queue.size();  
        boolean foundEnd = false;  
        for (int i = 0; i < count; i++) {  
            String cur = queue.poll();  
            int curDistance = distance.get(cur);                  
            ArrayList<String> neighbors = getNeighbors(cur, dict);  
    
            for (String neighbor : neighbors) {  
                nodeNeighbors.get(cur).add(neighbor);  
                if (!distance.containsKey(neighbor)) {// Check if visited  
                    distance.put(neighbor, curDistance + 1);  
                    if (end.equals(neighbor))// Found the shortest path  
                        foundEnd = true;  
                    else  
                        queue.offer(neighbor);  
                    }  
                }  
            }  
    
            if (foundEnd)  
                break;  
        }  
    }  
    
  // Find all next level nodes.      
  private ArrayList<String> getNeighbors(String node, Set<String> dict) {  
    ArrayList<String> res = new ArrayList<String>();  
    char chs[] = node.toCharArray();  
    
    for (char ch ='a'; ch <= 'z'; ch++) {  
        for (int i = 0; i < chs.length; i++) {  
            if (chs[i] == ch) continue;  
            char old_ch = chs[i];  
            chs[i] = ch;  
            if (dict.contains(String.valueOf(chs))) {  
                res.add(String.valueOf(chs));  
            }  
            chs[i] = old_ch;  
        }  
    
    }  
    return res;  
  }  
    
  // DFS: output all paths with the shortest distance.  
  private void dfs(String cur, String end, Set<String> dict, HashMap<String, ArrayList<String>> nodeNeighbors, HashMap<String, Integer> distance, ArrayList<String> solution, List<List<String>> res) {  
      solution.add(cur);  
      if (end.equals(cur)) {  
         res.add(new ArrayList<String>(solution));  
      } else {  
         for (String next : nodeNeighbors.get(cur)) {              
              if (distance.get(next) == distance.get(cur) + 1) {  
                   dfs(next, end, dict, nodeNeighbors, distance, solution, res);  
              }  
          }  
      }             
     solution.remove(solution.size() - 1);  
  }  
        
  }  
```    
    
### 个人解读    
  这句话被吞了么？？  
  ```  
  Note that beginWord is not a transformed word.  
  ```  
    
  思路一：通过DFS+回溯解决，因为目标是最短，如果全部遍历，量级非常大，所以dfs时候传入一个参数curLen描述当前进度，比这个大的都不用遍历了。然而还是遇到了TLE错误(一个将近3000个的数组)。  
    
  问题所在： 预处理的问题，O(N2)的复杂度。  
  1、尽量使用hash表  
  2、不是比较两个word，而是比较一个word的所有可能替换值  
  3、这种优化主要优化大数据，对于小数据属于拖累。  
    
  参考答案：  
  1、步骤一：通过BFS构建所有有效的数组和邻居  
  2、步骤二：根据BFS的结果再次遍历，获取结果  
  3、本题目主要难点在预处理上  
  3、先BFS再DFS也是可行的  
    
    
  ```  
    TLE   
    class Solution {  
     public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {  
            List<List<Integer>> res = new ArrayList<>();  
            wordList.add(beginWord);  
            List<Integer>[] graphic = buildGraphic(wordList);  
            int start = wordList.size() - 1;  
            int end = wordList.indexOf(endWord);  
            int[] flags = new int[wordList.size()];  
            dfs(res, new ArrayList<Integer>(), graphic, start, end, flags, 1);  
            List<List<String>> resStr = new ArrayList<>();  
            for(List<Integer> r: res) {  
                List<String> l = new ArrayList<>();  
                for(int rr: r) {  
                    l.add(wordList.get(rr));  
                }  
                resStr.add(l);  
            }  
            return resStr;  
        }  
      
        private void dfs(List<List<Integer>> res, ArrayList<Integer> preList, List<Integer>[] graphic, int start, int end, int[] flags, int curLen) {  
            if(start == end) {  
                preList.add(start);  
                if(res.size() == 0 || res.get(0).size() == curLen) {  
                    res.add(new ArrayList<>(preList));  
                } else if(res.get(0).size() > curLen) {  
                    res.clear();  
                    res.add(new ArrayList<>(preList));  
                }  
                preList.remove(preList.size() - 1);  
                return ;  
            }  
            preList.add(start);  
            for(int g: graphic[start]) {  
                if(flags[g] == 0 || curLen <= flags[g]) {  
                    flags[g] = curLen;  
                    dfs(res, preList, graphic, g, end, flags, curLen + 1);  
                }  
            }  
            preList.remove(preList.size() - 1);  
      
        }  
      
        private List<Integer>[] buildGraphic(List<String> wordList) {  
            int N = wordList.size();  
            List<Integer>[] graphic = new List[N];  
            for (int i = 0; i < N; i++) {  
                graphic[i] = new ArrayList<>();  
                for (int j = 0; j < N; j++) {  
                    if (isConnect(wordList.get(i), wordList.get(j))) {  
                        graphic[i].add(j);  
                    }  
                }  
            }  
            return graphic;  
        }  
      
        private boolean isConnect(String cur, String poll) {  
            if(cur.length() != poll.length()) {  
                return false;  
            }  
            boolean flag = false;  
            for(int i = 0; i < cur.length(); i++) {  
                if(cur.charAt(i) != poll.charAt(i)) {  
                    if(!flag) {  
                        flag = true;  
                    } else {  
                        return false;  
                    }  
                }  
            }  
            return flag;  
        }  
          
    }  
  ```  
    
tags:    
  -  字符串  
  -  BFS  
  -  DFS  
