import java.util.ArrayList;
import java.util.List;

public class User extends Subject implements Observer, UserComponent{
    private String userId;
    private List<User> followers;
    private List<User> following;
    private List<String> newsfeed;
    private List<String> tweets;
    private String incomingTweet;

    public User(String userId){
        this.userId = userId;
        tweets = new ArrayList<>();
    }

    public String getUserId() {
        return userId;
    }

    public List<User> getFollowers() {
        return followers;
    }

    public List<User> getFollowing(){
        return following;
    }

    public void printId(){
        System.out.println("User ID: " + userId);
    }

    public String getIncomingTweet() {
        return incomingTweet;
    }

    public void tweetMessage(String message){
        // cache incoming tweets for tree views in UI
        tweets.add(message);
        this.incomingTweet = message;
        System.out.println("TWEET MESSAGE RECEIVED :  " + message );
        notifyObserver();
    }

    @Override
    public void updateFeed(Subject tweet) {
        incomingTweet = ((User) tweet).getIncomingTweet();
        newsfeed.add(incomingTweet);
    }

    @Override
    public UserComponent getComponent() {
        return null;
    }

    @Override
    public void addUserComponent(UserComponent newUserComponent) {

    }
}
