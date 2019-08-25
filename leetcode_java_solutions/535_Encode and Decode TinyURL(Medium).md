### description    
  Note: This is a companion problem to the System Design problem: Design TinyURL.  
  TinyURL is a URL shortening service where you enter a URL such as https://leetcode.com/problems/design-tinyurl and it returns a short URL such as http://tinyurl.com/4e9iAk.  
    
  Design the encode and decode methods for the TinyURL service. There is no restriction on how your encode/decode algorithm should work. You just need to ensure that a URL can be encoded to a tiny URL and the tiny URL can be decoded to the original URL.  
### solution    
```    
  public class Codec {  
    
       private int index = 0;  
      private Map<Integer, String> map = new HashMap<>();  
      private static final String prefix = "http://tinyurl.com";  
    
      // Encodes a URL to a shortened URL.  
      public String encode(String longUrl) {  
          String en = "/" + index;  
          map.put(index++, longUrl);  
          return prefix + en;  
      }  
    
      // Decodes a shortened URL to its original URL.  
      public String decode(String shortUrl) {  
          String in = shortUrl.substring(shortUrl.lastIndexOf("/") + 1);  
          return map.get(Integer.valueOf(in));  
      }  
    
  }  
```    
    
### 个人解读    
  
  学名：短网址算法  
  网上比较流行的算法有两种 自增序列算法、 摘要算法  
    
  一开始以为会有字符串的加密和解密操作，没想到完全是凭借计数器存储在服务器里面的。  
  
    
tags:    
  -  短网址算法  
  -  自增序列法  
