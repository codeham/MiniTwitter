import java.util.*;

public class PositiveCount implements Visitor{
    private final String[] positiveWords = {"Good", "Cool", "Amazing", "Wow", "Awesome", "Rad", "Great", "Congrats", "Proud", "Prosper", "Motivation"};
    private final HashSet wordsTable = new HashSet();

    private int totalMessages = 0;
    private int positiveCount = 0;

    PositiveCount(){
        wordsTable.addAll(Arrays.asList(positiveWords));
    }

    public void findPositive(List<String> messagesList){
        for(String msg: messagesList){
            String[] words = msg.split(" ");
            for(String eachWord: words){
                if(wordsTable.contains(eachWord)){
                    // break out of loop, it's a positive message !
                    positiveCount++;
                    continue;
                }
            }
        }
    }

    public int calculatePercent(){
        return positiveCount;
    }

    public void incrementMessageCount(int messageCount){
        totalMessages = totalMessages + messageCount;
    }

    @Override
    public void visit(Group group) {

    }

    @Override
    public void visit(User user) {
        incrementMessageCount(user.getTweets().size());
        findPositive(user.getTweets());
    }
}
