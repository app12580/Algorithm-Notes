package com.algorithm;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class AC {
    private static Node root=new Node();
    private static Queue<Node> queue=new LinkedList<Node>();
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        String[] word={"she","shr","he","her"};
        String s="she";
        for (int i = 0; i < word.length; i++) {
            insert(word[i]);
        }
        build_ac_automation(root);
        System.out.println(query(s));
    }

    public static int query(String s){
        int count=0;
        Node p=root;
        char[] str=s.toCharArray();
        for (int i = 0; i < str.length; i++) {
            int index=str[i]-'a';
            while(p.child[index]==null&&p!=root){
                p=p.fail;
            }
            p=p.child[index];
            p=(p==null)?root:p;
            Node temp=p;
            while(temp!=root&&temp.count!=-1){
                count+=temp.count;
                temp.count=-1;
                temp=temp.fail;
            }
        }
        return count;
    }
    public static void build_ac_automation(Node root){

        root.fail=null;
        queue.add(root);
        while(!queue.isEmpty()){
            Node temp=queue.poll();
            Node p=null;
            for (int i = 0; i < 26; i++) {
                if(temp.child[i]!=null){
                    if(temp==root){
                        temp.child[i].fail=root;
                    }else{
                        p=temp.fail;
                        while(p!=null){
                            if(p.child[i]!=null){
                                temp.child[i].fail=p.child[i];
                                break;
                            }
                            p=p.fail;
                        }
                        if(p==null){
                            temp.child[i].fail=root;
                        }
                    }
                    queue.add(temp.child[i]);
                }
            }
        }
    }
    public static void insert(String str){
        if(str.isEmpty()||str==""){
            return;
        }
        Node cnode=root;
        for (int i = 0; i < str.length(); i++) {
            int index=str.charAt(i)-'a';
            if(cnode.child[index]==null){
                Node pnode=new Node();
                cnode.child[index]=pnode;
            }
            cnode=cnode.child[index];
        }
        cnode.count=1;
    }
}

class Node{
    int count;
    Node fail;
    Node[] child;
    public Node(){
        fail=null;
        count=0;
        child=new Node[26];
    }
}

