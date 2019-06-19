###  BFS和DFS的使用场景  
  BFS(DFS)与Dp能解决的问题往往是重合的  
  BFS：应用场景：求最小路径，通过Queue实现。  
  BFS：应用场景：求可达性，通过Stack实现。  
    
  BFS(DFS)比DP好的一点在于：DP比BFS多了状态关联，而BFS只能遍历所有  
  
### 遍历时防止重复的方法  
标记法:  标记法可以与源数组合一  
湮灭法  
数学法  
  
### DFS的辅助函数
   主要看参数列表
   dfs(当前进度, 结果集, 总进度指标Or原始条件, 【dfs的次数】 )    

  
### 非线性的终止条件  

void,不需要返回值。运行到最终节点，然后return。    //获取结果集的情况
指不是那种一步一步的dfs，而是要在运行在某一只棕情况下，终止所有的for循环。  //只获取某一个最终结果
```
private boolean dfs(xxx) {
     for(循环) {
        if(dfs(curlen, board, marked, word, nextI, nextJ)){    
           return true;    
        }   
     }
}
 


```
