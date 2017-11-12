import java.util.ArrayList;
import java.util.List;

public class Group implements UserComponent{
    private String groupId;
    private List<UserComponent> components = new ArrayList<>();

    public String getGroupId() {
        return groupId;
    }

    public Group(String groupId) {
        this.groupId = groupId;
    }

    public void displayGroupInfo(){
        System.out.println("Group ID: " + getGroupId());
    }

    @Override
    public UserComponent getComponent() {
        return this;
    }

    @Override
    public void addUserComponent(UserComponent newUserComponent) {
        components.add(newUserComponent);
    }
}
