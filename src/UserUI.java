import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserUI extends JFrame implements UIBuild{
    // Jframe main
    JFrame frame;

    // Admin Panel Instance
    AdminPanel adminPanel;

    // JPane
    JScrollPane topPane;
    JScrollPane bottomPane;

    // list view
    private JTree currentFollowing;
    private JTree newsFeed;

    private JButton followUser;
    private JButton postTweet;

    // text areas
    private JTextField userId;
    private JTextField twitterMessage;

    // user
    User user;

    UserUI(){
        UIbuilder();
    }

    UserUI(User user){
        this.user = user;
        System.out.println("User ID opened is: " + user.getUserId());
        adminPanel = AdminPanel.getInstance();
        UIbuilder();
    }

    private void UIbuilder(){
        textManager();
        treeManager();
        buttonManager();
        frameManager();
    }

    public void frameManager(){
        frame = new JFrame();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setTitle("@" + user.getUserId());
        frame.setResizable(false);
        frame.setLayout(null);

        frame.add(userId);
        frame.add(followUser);

        // top tree
        frame.add(topPane);

        // twitter message
        frame.add(twitterMessage);
        frame.add(postTweet);

        // bottom tree
        frame.add(bottomPane);


        frame.setSize(505,640);
        frame.setVisible(true);
    }

    public void treeManager(){
        // initialize tree nodes
        DefaultMutableTreeNode topView = new DefaultMutableTreeNode("Tree View");
        DefaultMutableTreeNode rootTop = new DefaultMutableTreeNode("Root");

        topView.add(rootTop);
        currentFollowing = new JTree(topView);
        topPane = new JScrollPane(currentFollowing);
        topPane.setBounds(5,45,495,275);

        DefaultMutableTreeNode bottomView = new DefaultMutableTreeNode("News Feed");
        DefaultMutableTreeNode rootBottom = new DefaultMutableTreeNode("Root");

        bottomView.add(rootBottom);
        newsFeed = new JTree(bottomView);
        bottomPane = new JScrollPane(newsFeed);
        bottomPane.setBounds(5, 370, 495, 245);


    }

    public void textManager(){
        userId = new JTextField();
        userId.setBounds(5,5,250, 35);

        twitterMessage = new JTextField();
        twitterMessage.setBounds(5, 325, 250, 35);
    }

    public void labelManager(){

    }

    public void buttonManager(){
        followUser = new JButton();
        followUser.setText("Follow User");
        followUser.setBounds(255, 5, 245, 35);
        followUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                followUser();
            }
        });

        postTweet = new JButton();
        postTweet.setText("Post Tweet");
        postTweet.setBounds(255, 325, 245, 35);
        postTweet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                postUserTweet();
            }
        });
    }

    public void postUserTweet(){
        String message = twitterMessage.getText();
        this.user.tweetMessage(message);
        twitterMessage.setText("");
        // observer notified by User object...
    }

    public void followUser(){
        // check if the field isn't empty
        String twitterUser = userId.getText();
        if(twitterUser.trim().equals("")){
            JOptionPane.showMessageDialog(frame, "Error: Enter a user ID !");
            userId.setText("");
            return;
        }

        // can't follow yourself !!
        if(twitterUser.equals(user.getUserId())){
            JOptionPane.showMessageDialog(frame, "Error: Can't follow yourself !");
            userId.setText("");
            return;
        }

        // check if user is already being followed
        if(user.checkAlreadyFollowing(twitterUser)){
            JOptionPane.showMessageDialog(frame, "Error: Already following that user !");
            userId.setText("");
            return;
        }

        // otherwise check if that user exists then...
        if(adminPanel.userController.checkUserExists(twitterUser)){
            User following = adminPanel.userController.grabUser(twitterUser);
            // add them to your following list
            // add yourself to their followers list
            user.addToFollowing(following);
            following.addToFollowers(user);
            System.out.println(user.getUserId() + " is now following " +twitterUser );
        }else{
            JOptionPane.showMessageDialog(frame, "Error: User does not exist");
        }

        userId.setText("");
    }
}
