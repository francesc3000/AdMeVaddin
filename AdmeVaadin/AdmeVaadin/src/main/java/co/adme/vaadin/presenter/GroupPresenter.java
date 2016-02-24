package co.adme.vaadin.presenter;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.UI;

import co.adme.vaadin.modelo.Group;
import co.adme.vaadin.modelo.Modelo;
import co.adme.vaadin.modelo.Publi;
import co.adme.vaadin.modelo.User;
import co.adme.vaadin.view.group.GroupCRUDViewImpl;
import co.adme.vaadin.view.group.GroupView;
import co.adme.vaadin.view.publi.PubliView;

@SpringComponent
@UIScope
public class GroupPresenter extends Presenter implements GroupView.GroupViewListener {
	
	private List<GroupView> viewList = new ArrayList<GroupView>();
	
	private GroupPresenter(){}

	@Override
	public void setViewAndBind(View view) {
		super.setViewAndBind(view);
		GroupView groupView = (GroupView) view;
		this.viewList.add(groupView);
		groupView.addGroupListener(this);
	}

	@Override
	public void save(String groupId, String name, List<User> memeberList, List<String> candidateList) {
		Group group = this.getGroup(groupId);
			
		group.setName(name);
		group.setMemberList(memeberList);
		group.setCandidateList(candidateList);
		
		group.save();
		this.getUser().addGroup(group);
		this.saveUser();
		
		if(groupId==null){
			int i=0;
			while(i<this.viewList.size()){
				try{
					GroupView groupView = this.viewList.get(i);
					groupView.setContent(group);				
				}catch(ConcurrentModificationException e){
					
				}
				i++;
			}
		}
	}

	@Override
	public String delete(Group group) {
		if(group.getPubliList().isEmpty()){
			if(getUser().removeGroup(group))
				if(groupService.delete(group.getId()))
					if(saveUser()){		
						for(GroupView groupView:this.viewList)
							groupView.remove(group);
					
						return "Borrado OK";
					}
		}
		else
			return "Error! Tiene publicaciones asociadas";
		
		return "Error al borrar";
	}

	@Override
	public List<Group> getGroupList() {		
		return this.getUser().getGroupList();
	}

	@Override
	public Group getGroup(String groupId) {
		Group group;
		if(groupId==null||groupId.equals(GroupCRUDViewImpl.NEW_GROUP))
			group = this.newGroup(GroupCRUDViewImpl.NEW_GROUP);
		else
			group = (Group) UI.getCurrent().getSession().getAttribute(groupId);
		
		return group;
	}

	@Override
	public List<User> getUserFriendList() {
		return this.getUser().getFriendList();
	}
	
	@Override
	public List<String> getUserFriendEmailList(){
		List<String> userFriendEmailList = new ArrayList<String>();
		
		for(User friend:this.getUserFriendList())
			userFriendEmailList.add(friend.getEmail());
		
		return userFriendEmailList;
	}

	private Group newGroup(String groupId) {
		Group group = this.getUser().newGroup("");
		UI.getCurrent().getSession().setAttribute(groupId, group);
		
		return group;
	}

	@Override
	public void addFavorite(Modelo modelo) {
		this.getUser().addFavoriteGroup((Group) modelo);
		this.saveUser();
		
		int i=0;
		while(i<this.viewList.size()){
			try{
				GroupView groupView = this.viewList.get(i);
				groupView.addFavorite(modelo);				
			}catch(ConcurrentModificationException e){
				
			}
			i++;
		}
	}

	@Override
	public void removeFavorite(Modelo modelo) {
		this.getUser().removeFavoriteGroup((Group) modelo);
		this.saveUser();
		
		int i=0;
		while(i<this.viewList.size()){
			try{
				GroupView groupView = this.viewList.get(i);
				groupView.removeFavorite(modelo);
			}catch(ConcurrentModificationException e){
				
			}
			i++;
		}
	}

	@Override
	public List<Modelo> getMyPubliList() {
		List<Modelo> publiList = new ArrayList<Modelo>();
		publiList.addAll(this.getUser().getPubliList());
		return publiList;
	}
	
	@Override
	public List<Modelo> getFavoriteList() {
		List<Modelo> favoritePubliList = new ArrayList<Modelo>();
		favoritePubliList.addAll(this.getUser().getFavoriteGroupList());
		return favoritePubliList;
	}

	@Override
	public List<Modelo> getWinPubliList() {
		List<Modelo> winPubliList = new ArrayList<Modelo>();
		winPubliList.addAll(this.getUser().getWinPubliList());
		return winPubliList;
	}
}
