### description  
Given a (singly) linked list with head node root, write a function to split the linked list into k consecutive linked list "parts".  
  
The length of each part should be as equal as possible: no two parts should have a size differing by more than 1. This may lead to some parts being null.  
  
The parts should be in order of occurrence in the input list, and parts occurring earlier should always have a size greater than or equal parts occurring later.  
  
Return a List of ListNode's representing the linked list parts that are formed.  
  
Examples 1->2->3->4, k = 5 // 5 equal parts [ [1], [2], [3], [4], null ]  
Example 1:  
Input:   
root = [1, 2, 3], k = 5  
Output: [[1],[2],[3],[],[]]  
Explanation:  
The input and each element of the output are ListNodes, not arrays.  
For example, the input root has root.val = 1, root.next.val = 2, \root.next.next.val = 3, and root.next.next.next = null.  
The first element output[0] has output[0].val = 1, output[0].next = null.  
The last element output[4] is null, but it's string representation as a ListNode is [].  
Example 2:  
Input:   
root = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10], k = 3  
Output: [[1, 2, 3, 4], [5, 6, 7], [8, 9, 10]]  
Explanation:  
The input has been split into consecutive parts with size difference at most 1, and earlier parts are a larger size than the later parts.  
Note:  
  
The length of root will be in the range [0, 1000].  
Each value of a node in the input will be an integer in the range [0, 999].  
k will be an integer in the range [1, 50].  
Accepted  
  
  
### solution  
```  
K指针算法大成功！！！！  
class Solution {  
    public ListNode[] splitListToParts(ListNode root, int k) {  
        ListNode[] indexs = new ListNode[k];  
        ListNode init = new ListNode(-1);  
        init.next = root;  
        for(int i = 0; i < k; i++) {  
            //初始化节点  
            indexs[i] = init.next;  
            if(init.next == null) {  
                for(int j = 0; j < i; j++) {  
                    indexs[j].next = null;  
                }  
                return indexs;  
            }  
            init = init.next;  
        }  
        int c = -2;  
        while((c = gogogo(indexs[k -1], k)) == -2) {  
            for(int i = 0; i < k; i++) {  
                int j = i + 1;  
                while(j-- > 0) {  
                    indexs[i] = indexs[i].next;  
                }  
            }  
        }  
        for(int i = 0; i < c; i++) {  
            //前面的推进了以后，后续的也需要跟着向后移动一步  
            for(int m = i; m < k; m++) {  
                indexs[m] = indexs[m].next;  
            }  
        }  
        //现在indexs里面装的都是每一个切片里面最末尾的那一个了  
        ListNode[] res = new ListNode[k];  
        res[0] = root;  
        for(int i = 0; i < k - 1; i++) {  
            ListNode next = indexs[i].next;  
            indexs[i].next = null;  
            res[i + 1] = next;  
        }  
        return res;  
    }  
  
    /**  
     * n表示移动几步  
     * @param node  
     * @param n  
     * @return -2 表示成功， 其他的表示失败，返回前面还有几个node， -1表示node本身就是null  
     */  
    public int gogogo(ListNode node, int n) {  
        int count = 0;  
        for(int i = 0; i < n; i++) {  
            if(node == null) {  
                return count - 1;  
            } else {  
                count++;  
                node = node.next;  
            }  
        }  
  
        return -2;  
    }  
}  
```  
解法二(速度更快)：  
```  
public ListNode[] splitListToParts(ListNode root, int k) {  
    int N = 0;  
    ListNode cur = root;  
    while (cur != null) {  
        N++;  
        cur = cur.next;  
    }  
    int mod = N % k;  
    int size = N / k;  
    ListNode[] ret = new ListNode[k];  
    cur = root;  
    for (int i = 0; cur != null && i < k; i++) {  
        ret[i] = cur;  
        int curSize = size + (mod-- > 0 ? 1 : 0);  
        for (int j = 0; j < curSize - 1; j++) {  
            cur = cur.next;  
        }  
        ListNode next = cur.next;  
        cur.next = null;  
        cur = next;  
    }  
    return ret;  
}  
```  
  
### 个人解读    
第一眼看到的这个题目，就会觉得链表双不光是双指针，甚至能有k指针    
[10个] 0 - 9    
k = 3    
0 1 2    
1 3 5    
2 5 8    
3 7 11    
这种想法不行，因为可能出现333通过了，但是后面只能442了，然而单向列表无法回头    
又思考了一下，最后一轮只要最快的那个先动就好了，然后根据先动的能走多远来判断前面的结果    
如果这样成功的话，那么就说明：    
K指针是可行的！！！！    
  
cyc的做法什么鬼？？ 先遍历完一圈拿到链表的size然后再去分割？？    
然而沮丧的是，这种直接遍历出来长度的速度要比我的K指针速度要快TAT    
  
tags:    
  - 链表    
  - K指针    
