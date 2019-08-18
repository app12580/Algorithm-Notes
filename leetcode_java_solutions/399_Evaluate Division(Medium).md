### description  
  Equations are given in the format A / B = k, where A and B are variables represented as strings, and k is a real number (floating point number). Given some queries, return the answers. If the answer does not exist, return -1.0.
  
  Example:
  Given a / b = 2.0, b / c = 3.0.
  queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? .
  return [6.0, 0.5, -1.0, 1.0, -1.0 ].
  
  The input is: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries , where equations.size() == values.size(), and the values are positive. This represents the equations. Return vector<double>.
  
  According to the example above:
  
  equations = [ ["a", "b"], ["b", "c"] ],
  values = [2.0, 3.0],
  queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ]. 
   
  
  The input is always valid. You may assume that evaluating the queries will result in no division by zero and there is no contradiction.
### solution  
```  
  class Solution {
     public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
          HashMap<String, ArrayList<String>> pairs = new HashMap<String, ArrayList<String>>();
          HashMap<String, ArrayList<Double>> valuesPair = new HashMap<String, ArrayList<Double>>();
          for (int i = 0; i < equations.size(); i++) {
              List<String> equation = equations.get(i);
              if (!pairs.containsKey(equation.get(0))) {
                  pairs.put(equation.get(0), new ArrayList<String>());
                  valuesPair.put(equation.get(0), new ArrayList<Double>());
              }
              if (!pairs.containsKey(equation.get(1))) {
                  pairs.put(equation.get(1), new ArrayList<String>());
                  valuesPair.put(equation.get(1), new ArrayList<Double>());
              }
              pairs.get(equation.get(0)).add(equation.get(1));
              pairs.get(equation.get(1)).add(equation.get(0));
              valuesPair.get(equation.get(0)).add(values[i]);
              valuesPair.get(equation.get(1)).add(1/values[i]);
          }
  
          double[] result = new double[queries.size()];
          for (int i = 0; i < queries.size(); i++) {
              List<String> query = queries.get(i);
              result[i] = dfs(query.get(0), query.get(1), pairs, valuesPair, new HashSet<String>(), 1.0);
              if (result[i] == 0.0) result[i] = -1.0;
          }
          return result;
      }
  
      private double dfs(String start, String end, HashMap<String, ArrayList<String>> pairs, HashMap<String, ArrayList<Double>> values, HashSet<String> set, double value) {
          if (set.contains(start)) return 0.0;    // 重复
          if (!pairs.containsKey(start)) return 0.0;
          if (start.equals(end)) return value;    //终止条件
          set.add(start);
  
          ArrayList<String> strList = pairs.get(start);
          ArrayList<Double> valueList = values.get(start);
          double tmp = 0.0;
          for (int i = 0; i < strList.size(); i++) {
              tmp = dfs(strList.get(i), end, pairs, values, set, value*valueList.get(i));     //这一步可能为0
              if (tmp != 0.0) {
                  break;
              }
          }
          set.remove(start);      //回溯
          return tmp;
      }
  
  }
```  
  
### 个人解读  
  注意几点：
  1、商不可能为0的，题里面没有明确提出
  2、没有捷径，只能挨个尝试DFS
  3、甚至没有对新产生的结果放进中间集合里面
  
  不要怕麻烦，先预处理，把所有的被除数和除数分开存放在两个Map里面，然后一个字符一个字符的去DFS。  
  
tags:  
  -   字符串解析 
  -   DFS 
  -   回溯 
