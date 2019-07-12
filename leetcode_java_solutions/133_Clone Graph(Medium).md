### description    
  Given a reference of a node in a connected undirected graph, return a deep copy (clone) of the graph. Each node in the graph contains a val (int) and a list (List[Node]) of its neighbors.  
    
  Example:  
    
  Input:  
  {"$id":"1","neighbors":[{"$id":"2","neighbors":[{"$ref":"1"},{"$id":"3","neighbors":[{"$ref":"2"},{"$id":"4","neighbors":[{"$ref":"3"},{"$ref":"1"}],"val":4}],"val":3}],"val":2},{"$ref":"4"}],"val":1}  
    
  Explanation:  
  Node 1's value is 1, and it has two neighbors: Node 2 and 4.  
  Node 2's value is 2, and it has two neighbors: Node 1 and 3.  
  Node 3's value is 3, and it has two neighbors: Node 2 and 4.  
  Node 4's value is 4, and it has two neighbors: Node 1 and 3.  
     
    
  Note:  
    
  The number of nodes will be between 1 and 100.  
  The undirected graph is a simple graph, which means no repeated edges and no self-loops in the graph.  
  Since the graph is undirected, if node p has node q as neighbor, then node q must have node p as neighbor too.  
  You must return the copy of the given node as a reference to the cloned graph.  
    
    
### solution    
```    
      public HashMap<Integer, Node> map = new HashMap<>();  
    
      public Node cloneGraph(Node node) {  
          if (node == null) return null;  
    
          if (map.containsKey(node.val))  
              return map.get(node.val);  
    
          Node newNode = new Node(node.val, new ArrayList<Node>());  
          map.put(newNode.val, newNode);  
    
          // 单向操作，只在自己的回合去添加邻居。  
          for (Node neighbor : node.neighbors)  
              newNode.neighbors.add(cloneGraph(neighbor));      //添加邻居的时候，调用递归，如果它没有创建，则执行邻居的创建，并给邻居增加邻居  
    
          return newNode;  
      }  
```    
    
### 个人解读    
  题目有个点没有说清楚： 所有节点的数字不重复。因此可以根据数字作为节点的唯一标识，而不是内存地址。  
  我的主要问题在于，如何控制每个neighbor的正确执行。  
  令邻居单向处理，不想着一遍处理当前节点，同时还把另外的做好。  
    
    
tags:    
  -  模拟  
  -  DFS  
