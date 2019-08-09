### description    
  Design a simplified version of Twitter where users can post tweets, follow/unfollow another user and is able to see the 10 most recent tweets in the user's news feed. Your design should support the following methods:  
    
  postTweet(userId, tweetId): Compose a new tweet.  
  getNewsFeed(userId): Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent.  
  follow(followerId, followeeId): Follower follows a followee.  
  unfollow(followerId, followeeId): Follower unfollows a followee.  
  Example:  
    
  Twitter twitter = new Twitter();  
    
  // User 1 posts a new tweet (id = 5).  
  twitter.postTweet(1, 5);  
    
  // User 1's news feed should return a list with 1 tweet id -> [5].  
  twitter.getNewsFeed(1);  
    
  // User 1 follows user 2.  
  twitter.follow(1, 2);  
    
  // User 2 posts a new tweet (id = 6).  
  twitter.postTweet(2, 6);  
    
  // User 1's news feed should return a list with 2 tweet ids -> [6, 5].  
  // Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.  
  twitter.getNewsFeed(1);  
    
  // User 1 unfollows user 2.  
  twitter.unfollow(1, 2);  
    
  // User 1's news feed should return a list with 1 tweet id -> [5],  
  // since user 1 is no longer following user 2.  
  twitter.getNewsFeed(1);  
    
### solution    
```    
  public class Twitter {  
  	private static int timeStamp=0;  
  	private Map<Integer, User> userMap;  
    
  	private class Tweet{  
  		public int id;  
  		public int time;  
  		public Tweet next;  
    
  		public Tweet(int id){  
  			this.id = id;  
  			time = timeStamp++;  
  			next=null;  
  		}  
  	}  
    
  	public class User{  
  		public int id;  
  		public Set<Integer> followed;  
  		public Tweet tweet_head;  
    
  		public User(int id){  
  			this.id=id;  
  			followed = new HashSet<>();  
  			follow(id); // first follow itself  
  			tweet_head = null;  
  		}  
    
  		public void follow(int id){  
  			followed.add(id);  
  		}  
    
  		public void unfollow(int id){  
  			followed.remove(id);  
  		}  
    
  		public void post(int id){  
  			Tweet t = new Tweet(id);  
  			t.next=tweet_head;  
  			tweet_head=t;  
  		}  
  	}  
    
  	public Twitter() {  
  		userMap = new HashMap<Integer, User>();  
  	}  
    
  	public void postTweet(int userId, int tweetId) {  
  		if(!userMap.containsKey(userId)){  
  			User u = new User(userId);  
  			userMap.put(userId, u);  
  		}  
  		userMap.get(userId).post(tweetId);  
    
  	}  
    
  	public List<Integer> getNewsFeed(int userId) {  
  		List<Integer> res = new LinkedList<>();  
    
  		if(!userMap.containsKey(userId))   return res;  
    
  		Set<Integer> users = userMap.get(userId).followed;  
  		PriorityQueue<Tweet> q = new PriorityQueue<Tweet>(users.size(), (a,b)->(b.time-a.time));  
  		for(int user: users){  
  			Tweet t = userMap.get(user).tweet_head;  
  			// very imporant! If we add null to the head we are screwed.  
  			if(t!=null){  
  				q.add(t);  
  			}  
  		}  
  		int n=0;  
  		while(!q.isEmpty() && n<10){  
  		  Tweet t = q.poll();  
  		  res.add(t.id);  
  		  n++;  
  		  if(t.next!=null)  
  			q.add(t.next);  
  		}  
    
  		return res;  
    
  	}  
    
  	/** Follower follows a followee. If the operation is invalid, it should be a no-op. */  
  	public void follow(int followerId, int followeeId) {  
  		if(!userMap.containsKey(followerId)){  
  			User u = new User(followerId);  
  			userMap.put(followerId, u);  
  		}  
  		if(!userMap.containsKey(followeeId)){  
  			User u = new User(followeeId);  
  			userMap.put(followeeId, u);  
  		}  
  		userMap.get(followerId).follow(followeeId);  
  	}  
    
  	/** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */  
  	public void unfollow(int followerId, int followeeId) {  
  		if(!userMap.containsKey(followerId) || followerId==followeeId)  
  			return;  
  		userMap.get(followerId).unfollow(followeeId);  
  	}  
  }  
```    
    
### 个人解读    
  需要用Set设置每个人的关注列表。  
  推文的问题：既需要通过用户查询，也需要通过时间查询。  
    
tags:    
  -  模拟  
  -  设计模式  
  -  PriorityQueue  
