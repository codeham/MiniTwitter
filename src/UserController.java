import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import java.util.ArrayList;
import java.util.List;

public class UserController extends DefaultMutableTreeNode{
    private List<User> users;
    private List<String> groups;

    UserController(){
        users = new ArrayList<>();
        groups = new ArrayList<>();
    }

    public List<User> getUsers() {
        return users;
    }

    public List<String> getGroups() {
        return groups;
    }



    // leaf being added directly to the root of the tree
    public DefaultMutableTreeNode addLeaf(String userId, DefaultMutableTreeNode rootNode){
        DefaultMutableTreeNode newUser = new DefaultMutableTreeNode(new User(userId));
        // set allowing children to false since it is a leaf
        newUser.setAllowsChildren(false);
        rootNode.add(newUser);
        // add User object to list
        users.add((User)newUser.getUserObject());

        return newUser;
    }

    // leaf being added to a path
    public DefaultMutableTreeNode addLeaf(DefaultMutableTreeNode selectedNode, String userId){
        DefaultMutableTreeNode newUser = new DefaultMutableTreeNode(new User(userId));
        newUser.setAllowsChildren(false);
        selectedNode.add(newUser);
        // add User object to list
        users.add((User)newUser.getUserObject());

        return newUser;
    }

    // group being added directly to the root of the tree
    public DefaultMutableTreeNode addGroup(String groupId, DefaultMutableTreeNode rootNode){
        DefaultMutableTreeNode newGroup = new DefaultMutableTreeNode(new Group(groupId));
        // set allowing children to true since it is a group
        newGroup.setAllowsChildren(true);
        rootNode.add(newGroup);
        groups.add(groupId);


        return newGroup;
    }

    // group being added to a path
    public DefaultMutableTreeNode addGroup(DefaultMutableTreeNode selectedNode, String groupId){
        DefaultMutableTreeNode newGroup = new DefaultMutableTreeNode(new Group(groupId));
        newGroup.setAllowsChildren(true);
        selectedNode.add(newGroup);
        groups.add(groupId);

        return newGroup;
    }

    public User grabUser(String userId){
        for(User x: users){
            if(x.getUserId().equals(userId)){
                return x;
            }
        }
        return null;
    }


    public boolean checkGroupRepeat(String groupId){
      for(String x: groups){
          if(x.equals(groupId)){
              return true;
          }
      }
      return false;
    }

    public boolean checkUserExists(String userId){
        for(User x: users){
            if(x.getUserId().equals(userId)){
                return true;
            }
        }
        return false;
    }
}
