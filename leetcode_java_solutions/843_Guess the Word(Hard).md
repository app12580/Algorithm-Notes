### description    
  This problem is an interactive problem new to the LeetCode platform.  
    
  We are given a word list of unique words, each word is 6 letters long, and one word in this list is chosen as secret.  
    
  You may call master.guess(word) to guess a word.  The guessed word should have type string and must be from the original list with 6 lowercase letters.  
    
  This function returns an integer type, representing the number of exact matches (value and position) of your guess to the secret word.  Also, if your guess is not in the given wordlist, it will return -1 instead.  
    
  For each test case, you have 10 guesses to guess the word. At the end of any number of calls, if you have made 10 or less calls to master.guess and at least one of these guesses was the secret, you pass the testcase.  
    
  Besides the example test case below, there will be 5 additional test cases, each with 100 words in the word list.  The letters of each word in those testcases were chosen independently at random from 'a' to 'z', such that every word in the given word lists is unique.  
    
  Example 1:  
  Input: secret = "acckzz", wordlist = ["acckzz","ccbazz","eiowzz","abcczz"]  
    
  Explanation:  
    
  master.guess("aaaaaa") returns -1, because "aaaaaa" is not in wordlist.  
  master.guess("acckzz") returns 6, because "acckzz" is secret and has all 6 matches.  
  master.guess("ccbazz") returns 3, because "ccbazz" has 3 matches.  
  master.guess("eiowzz") returns 2, because "eiowzz" has 2 matches.  
  master.guess("abcczz") returns 4, because "abcczz" has 4 matches.  
    
  We made 5 calls to master.guess and one of them was the secret, so we pass the test case.  
  Note:  Any solutions that attempt to circumvent the judge will result in disqualification.  
### solution    
```    
// 方法一： 通过已有单词作为工具人  
Runtime: 11 ms, faster than 22.61% of Java online submissions for Guess the Word.  
Memory Usage: 32 MB, less than 7.41% of Java online submissions for Guess the Word.  
  
  class Solution {  
     int[][] H;  
      public void findSecretWord(String[] wordlist, Master master) {  
          int N = wordlist.length;  
          H = new int[N][N];  
          for (int i = 0; i < N; ++i)  
              for (int j = i; j < N; ++j) {  
                  int match = 0;  
                  for (int k = 0; k < 6; ++k)  
                      if (wordlist[i].charAt(k) == wordlist[j].charAt(k))  
                          match++;  
                  H[i][j] = H[j][i] = match;  
              }  
    
          List<Integer> possible = new ArrayList();  
          Set<Integer> path = new HashSet<>();  
          for (int i = 0; i < N; ++i) possible.add(i);  
    
          while (!possible.isEmpty()) {  
              int guess = solve(possible, path);  
              int matches = master.guess(wordlist[guess]);  
              if (matches == 6) return;  
              List<Integer> possible2 = new ArrayList();  
              for (Integer j: possible) if (H[guess][j] == matches) possible2.add(j);  
              possible = possible2;  
              path.add(guess);  
          }  
    
      }  
        
       public int solve(List<Integer> possible, Set<Integer> path) {  
          if (possible.size() <= 2) return possible.get(0);  
          List<Integer> ansgrp = possible;  
          int ansguess = -1;  
    
          // 为啥是从全部遍历，而不是从possible遍历啊？？  
          // 因为即使不是结果，但是有拿去当工具人的价值  
          for (int guess = 0; guess < H.length; ++guess) {  
              if (!path.contains(guess)) {  
                  ArrayList<Integer>[] groups = new ArrayList[7];  
                  //groups 长度为7的list，每个list存储有i个公共字符的个数  
                  //遍历所有源单词，找出当前可能单词List的  
                  //查看那个最适合当工具人  
                  //然后就是问题了。为什么要极小化极大？  
                  //这是一个指标，最大值越小越平均，新的possible就越小  
                  for (int i = 0; i < 7; ++i) groups[i] = new ArrayList<Integer>();  
                  for (Integer j: possible) {  
                      if (j != guess) {  
                          groups[H[guess][j]].add(j);  
                      }  
                  }  
    
                  // 极小化极大？？？   
                  ArrayList<Integer> maxgroup = groups[0];  
                  for (int i = 0; i < 7; ++i)  
                      if (groups[i].size() > maxgroup.size())  
                          maxgroup = groups[i];  
    
                  if (maxgroup.size() < ansgrp.size()) {  
                      ansgrp = maxgroup;  
                      ansguess = guess;  
                  }  
              }  
          }  
    
          return ansguess;  
      }  
    
  }  
     
     
```    
    
### 个人解读    
  第一次遇到这种交互问题。。。  
    
  主要是根据wordList来制定猜测策略。获取六位数中，每一位的情况。但是返回值只有一个数字。。。难道要用到poor pig的那种标记思路么。。。  
    
  官方回答：  
  https://leetcode-cn.com/problems/guess-the-word/solution/cai-cai-zhe-ge-dan-ci-by-leetcode/  
    
  总结一下思路：  
  1、想办法选取一个工具人，使得根据工具人的结果会缩小可能的结果  
    这样不停缩小，直到命中结果。  
  2、关键是如何选取工具人，应该有一个更好的方法。  
  3、官方解答是从guess里面选取，然后极小化极大，根据最大可能结果最小来获取结果。  
    
tags:    
  -  交互问题  
  -  随机决策  
  -  极小化极大   
