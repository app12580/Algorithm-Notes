package com.algorithm.graph;

/**
 * 拓扑排序
 */
public class TopologicalSort {

    /**
     * 在一个有向图中，对所有的节点进行排序，要求没有一个节点指向它前面的节点。
     先统计所有节点的入度，对于入度为0的节点就可以分离出来，然后把这个节点指向的节点的入度减一。
     一直做改操作，直到所有的节点都被分离出来。
     如果最后不存在入度为0的节点，那就说明有环，不存在拓扑排序，也就是很多题目的无解的情况。
     */
    //难点在于拓扑排序的解不是唯一的，如果存在内部细节的优化排序，就会增大难度

}
