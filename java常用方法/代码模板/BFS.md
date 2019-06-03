### 广搜
Queue<Integer> queue = new LinkedList<>();
do{
    queue.offer(满足条件的初始项)
}
while (!queue.isEmpty()) {
    int x = queue.poll();   //取出满足条件的项
    {
        // doSomeThing 把剩下满足条件的项放进queue里面
        queue.offer(something)
    }
}
