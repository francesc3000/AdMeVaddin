package co.adme.vaadin.view.group;

import java.util.List;

import co.adme.vaadin.modelo.Group;
import co.adme.vaadin.modelo.User;
import co.adme.vaadin.view.ModeloView;

public interface GroupView extends ModeloView{
	interface GroupViewListener extends ModeloView.ModeloViewListener{
		void save(String groupId, String name, List<User> userList, List<String> candidateList);
		String delete(Group group);
		List<Group> getGroupList();
		Group getGroup(String groupId);
		List<User> getUserFriendList();
		List<String> getUserFriendEmailList();
    }

    public void addGroupListener(GroupViewListener listener);
    
}
