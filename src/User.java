import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;

public class User extends Subject implements Observer, UserComponent{
    private String userId;
    private List<User> followers;
    private List<User> following;

    private DefaultListModel<String> followingList;

    private List<String> newsfeed;
    private List<String> tweets;
    private String incomingTweet;

    public User(String userId){
        this.userId = userId;
        tweets = new ArrayList<>();
        followers = new ArrayList<>();
        following = new ArrayList<>();
        newsfeed = new ArrayList<>();
    }

    public String getUserId() {
        return userId;
    }

    public List<User> getFollowers() {
        return followers;
    }

    public List<User> getFollowing() {
        return following;
    }

    public List<String> getTweets() {
        return tweets;
    }

    public List<String> getNewsfeed() {
        return newsfeed;
    }

    public DefaultListModel<String> getFollowingList() {
        return followingList;
    }
    
    public void printId(){
        System.out.println("User ID: " + userId);
    }

    public boolean checkAlreadyFollowing(String user){
        for(User x: following){
            if(x.getUserId().equals(user)){
                return true;
            }
        }
        return false;
    }

    public void addToFollowers( User followerUser){
        followers.add(followerUser);
    }

    public void addToFollowing(User followingUser){
        following.add(followingUser);
    }

    public String getIncomingTweet() {
        return incomingTweet;
    }

    public void tweetMessage(String message){
        // cache incoming tweets for tree views in UI
        tweets.add(message);
        this.incomingTweet = getUserId() + ": " + message;
        System.out.println("TWEET MESSAGE RECEIVED :  " + message );
        notifyObserver();
    }

    @Override
    public String toString() {
        // override to only return the ID of the object
        return getUserId();
    }

    @Override
    public void updateFeed(Subject tweet) {
        incomingTweet = ((User) tweet).getIncomingTweet();
        newsfeed.add(incomingTweet);
        System.out.println("Feed Update: " + incomingTweet);
        System.out.println("News Feed Qty. : " + newsfeed.size());
    }

    @Override
    public UserComponent getComponent() {
        return null;
    }

    @Override
    public void addUserComponent(UserComponent newUserComponent) {

    }
}
