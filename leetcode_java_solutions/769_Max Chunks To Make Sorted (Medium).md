### description    
  Given an array arr that is a permutation of [0, 1, ..., arr.length - 1], we split the array into some number of "chunks" (partitions), and individually sort each chunk.  After concatenating them, the result equals the sorted array.  
    
  What is the most number of chunks we could have made?  
    
  Example 1:  
    
  Input: arr = [4,3,2,1,0]  
  Output: 1  
  Explanation:  
  Splitting into two or more chunks will not return the required result.  
  For example, splitting into [4, 3], [2, 1, 0] will result in [3, 4, 0, 1, 2], which isn't sorted.  
  Example 2:  
    
  Input: arr = [1,0,2,3,4]  
  Output: 4  
  Explanation:  
  We can split into two chunks, such as [1, 0], [2, 3, 4].  
  However, splitting into [1, 0], [2], [3], [4] is the highest number of chunks possible.  
  Note:  
    
  arr will have length in range [1, 10].  
  arr[i] will be a permutation of [0, 1, ..., arr.length - 1].  
    
中文：  
  数组arr是[0, 1, ..., arr.length - 1]的一种排列，我们将这个数组分割成几个“块”，并将这些块分别进行排序。之后再连接起来，使得连接的结果和按升序排序后的原数组相同。  
    
  我们最多能将数组分成多少块？  
    
  示例 1:  
    
  输入: arr = [4,3,2,1,0]  
  输出: 1  
  解释:  
  将数组分成2块或者更多块，都无法得到所需的结果。  
  例如，分成 [4, 3], [2, 1, 0] 的结果是 [3, 4, 0, 1, 2]，这不是有序的数组。  
  示例 2:  
    
  输入: arr = [1,0,2,3,4]  
  输出: 4  
  解释:  
  我们可以把它分成两块，例如 [1, 0], [2, 3, 4]。  
  然而，分成 [1, 0], [2], [3], [4] 可以得到最多的块数。  
  注意:  
    
  arr 的长度在 [1, 10] 之间。  
  arr[i]是 [0, 1, ..., arr.length - 1]的一种排列。  
    
  搞毛啊，"并将这些块分别进行排序"，应该是"分5别进行升序排序"，这么重要的东西不说  
  题目翻译：  
      把一个数组进行切分成多个块，要求这些块符合以下条件：  
      对每个块(小数组)内部进行升序排列，然后把这些块从左到右拼在一起得到一个新的数组，得到的结果要等于源数组的升序排列结果  
      问：最多能分成几块  
### solution    
```    
    
```    
    
### 个人解读    
  把题目读明白以后，就可以把问题转换成：把数组分块，假设为An，则要求Ai满足：  
  所有Ai < 所有 Ai+1  
  即max(Ai) < min(Ai+1)  
  //max(Ai)表示A数组内的最大值  
  求最大的数组个数  
    
  题里面还有一个关键性条件，数组为自然数组  
  知道了这个以后，就会知道，第一个分组的，恰好是最小的几个数，假设第一个分组的是长度是5，则前5个数是0,1,2,3,4的乱序  
    
tags:    
  -   读不懂题目描述    
