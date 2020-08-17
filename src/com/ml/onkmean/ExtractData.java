package com.ml.onkmean;
import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;
public class ExtractData extends Thread {
	private static final String CONSUMER_KEY = "L8u6KlAyHuaMYR2ddpFXoC1FO";
	private static final String CONSUMER_KEY_SECRET = "t1Fs50YlCNsTVHQyi9T3lju8YHWVS5PPXTZ1kZNclQbVHXAYkz";
	
	private static String driverName= "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private  static String url = "jdbc:sqlserver://LAPTOP-6D68C8F6:1433;" +
			"databaseName=ontology";
	private static String userName="sa";
	private static String passWord="123456";
	private static Connection con;
	public static void main(String... ar) {
		// TODO Auto-generated method stub
	ExtractData objData=new ExtractData();
	objData.start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		while(true){
             try{
                 //your code
            	 extractData();
                 Thread.sleep((15*60)*1000);//this is poll interval
             }catch(InterruptedException e){
                 e.printStackTrace();
             }
		 
		 catch (Exception e) {
			// TODO: handle exception
		}
	
	}

}

	public void extractData()
	{
		try
		{
			ConfigurationBuilder builder = new ConfigurationBuilder();
			builder.setOAuthConsumerKey(CONSUMER_KEY);
			builder.setOAuthConsumerSecret(CONSUMER_KEY_SECRET);
			Configuration configuration = builder.build();
			TwitterFactory factory = new TwitterFactory(configuration);
			Twitter twitter = factory.getInstance();
			String accessToken = "806092692018954240-zcihLxx59SXqYJG2HGS5ycpDsQw0uam";
			 String accessTokenSecret= "ZNFgZYl6QzFqE280fy879fa4gwdzFhVPjzTqIJPq0dxle";
			  AccessToken oathAccessToken = new AccessToken(accessToken, accessTokenSecret);
		       twitter.setOAuthAccessToken(oathAccessToken);
		       Query query = new Query("Baleno");
		       query.setCount(200);
		       QueryResult result = null;
		       long status=Long.MAX_VALUE;
		       Class.forName(driverName);
		          do {
		           try {
		        	   con = DriverManager.getConnection(url,userName,passWord);
				         	   
					result = twitter.search(query);
				} catch (TwitterException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		           List<Status> tweets = result.getTweets();
		           
		           System.out.println(tweets.size());
		           int count=1;
		           for (Status tweet : tweets) {
		        	   try
		        	   {
		        	   	Statement st = con.createStatement();
		        	   	String _tweetText=tweet.getText().replaceAll("\\r\\n|\\r|\\n", " ");
		        	   	_tweetText=_tweetText.replace("'","");
		                int val = st.executeUpdate("insert into TweetTable(UserId,UserName,Tweets) values('" +tweet.getId() + "','" +tweet.getUser().getScreenName() + "','" + _tweetText + "')");
		        	   }
		        	   catch(SQLException e)
		        	   {
		        		   System.out.println(e.toString());
		        		   
		        	   }
		                System.out.println(count++ + tweet.getId() + "@"+tweet.getUser().getScreenName() + "|" + tweet.getText().replaceAll("\\r\\n|\\r|\\n", " ")+"|"+ tweet.isRetweeted() );
		                System.out.println("\n-----------------\n");
		                status= Math.min(tweet.getId(),status);
		                }
		           query.setMaxId(status - 1);
		           con.close();
		             } while ((query = result.nextQuery()) != null); 


	}
		catch (Exception e) {
			System.out.println(e.toString() + " " + e.getStackTrace());
		}
	}
}
