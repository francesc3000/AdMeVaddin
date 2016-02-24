package co.adme.vaadin.presenter;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.UI;

import co.adme.vaadin.modelo.Group;
import co.adme.vaadin.modelo.Modelo;
import co.adme.vaadin.modelo.Owner;
import co.adme.vaadin.modelo.Publi;
import co.adme.vaadin.modelo.User;
import co.adme.vaadin.view.group.GroupView;
import co.adme.vaadin.view.publi.PubliCRUDViewImpl;
import co.adme.vaadin.view.publi.PubliView;

@SpringComponent
@UIScope
public class PubliPresenter extends Presenter implements PubliView.PubliViewListener {

	private List<PubliView> viewList = new ArrayList<PubliView>();
	
	private PubliPresenter(){}

	@Override
	public void setViewAndBind(View view) {
		super.setViewAndBind(view);
		PubliView publiView = (PubliView) view;
		this.viewList.add(publiView);
		publiView.addPubliListener(this);
	}

	@Override
	public void save(String publiId, String title, String description, 
					 String city, Float price, Owner ownerIn ) {
		Owner owner_new;
		if(ownerIn!=null)
			owner_new = ownerIn;
		else
			owner_new = this.getUser();
		
		Publi publi = this.getPubli(publiId);
		publi.setTitle(title);
		publi.setDescription(description);
		publi.setCity(city);
		publi.setPrice(price);
		Owner owner_old = publi.getOwner();
		publi.setOwner(owner_new);
		
		
		publi.save();
		owner_new.addPubli(publi);
		if(owner_new instanceof User){
			User user = (User) owner_new;
			user.save();
		}
		else if(owner_new instanceof Group){
			Group group = (Group) owner_new;
			group.save();
		}
		
		if(owner_old!=null && !owner_old.equals(owner_new)){
			owner_old.removePubli(publi);
			if(owner_old instanceof User){
				User user = (User) owner_old;
				user.save();
			}
			else if(owner_old instanceof Group){
				Group group = (Group) owner_old;
				group.save();
			}
		}
		
		if(publiId==null){
			int i=0;
			while(i<this.viewList.size()){
				try{
					PubliView publiView = this.viewList.get(i);
					publiView.setContent(publi);				
				}catch(ConcurrentModificationException e){
					
				}
				i++;
			}
		}
	}
	
	@Override
	public void delete(Publi publi) {
		/*
		if(publi.getSponsorList().isEmpty()){
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
		publiService.delete(publi);
		*/
	}

	@Override
	public Publi getPubli(String publiId) {
		 Publi publi;
		 if(publiId==null||publiId.equals(PubliCRUDViewImpl.NEW_PUBLI))
			 publi = this.newPubli(PubliCRUDViewImpl.NEW_PUBLI);
		 else
			 publi = (Publi) UI.getCurrent().getSession().getAttribute(publiId);
		 
		 return publi;
	}
	//Metodos de servicio
	@Override
	public List<Modelo> getMyPubliList(){	
		List<Modelo> userAndGroupPubliList = new ArrayList<Modelo>();
		userAndGroupPubliList.addAll(this.getUser().getUserAndGroupPubliList());
		return userAndGroupPubliList;
	}

	@Override
	public List<Modelo> getFavoriteList() {
		List<Modelo> favoriteList = new ArrayList<Modelo>();
		favoriteList.addAll(this.getUser().getFavoritePubliList());
		return favoriteList;
	}

	@Override
	public List<Modelo> getWinPubliList() {
		List<Modelo> winPubliList = new ArrayList<Modelo>();
		winPubliList.addAll(this.getUser().getWinPubliList());
		return winPubliList;
	}

	@Override
	public Container getOwnerContainer() {
		BeanItemContainer<Owner> container = new BeanItemContainer<Owner>(
				 Owner.class);
		 
		container.addBean(getUser());
		 container.addAll(this.getOwnerList());
		 
		 return container;
	}

	public List<Owner> getOwnerList() {
		List<Owner> ownerList = new ArrayList<Owner>();
		
		ownerList.addAll(this.getUser().getGroupList());
		
		return ownerList;
	}

	private Publi newPubli(String publiId) {
		Publi publi = this.getUser().newPubli();
		UI.getCurrent().getSession().setAttribute(publiId, publi);
		
		return publi;
	}

	@Override
	public void addFavorite(Modelo modelo) {
		this.getUser().addFavoritePubli((Publi) modelo);
		this.saveUser();
		
		int i=0;
		while(i<this.viewList.size()){
			try{
				PubliView publiView = this.viewList.get(i);
				publiView.addFavorite(modelo);				
			}catch(ConcurrentModificationException e){
				
			}
			i++;
		}
	}

	@Override
	public void removeFavorite(Modelo modelo) {
		this.getUser().removeFavoritePubli((Publi) modelo);
		this.saveUser();
		
		int i=0;
		while(i<this.viewList.size()){
			try{
				PubliView publiView = this.viewList.get(i);
				publiView.removeFavorite(modelo);
			}catch(ConcurrentModificationException e){
				
			}
			i++;
		}
	}

	@Override
	public Boolean adme(Publi publi) {
		Boolean result = false;
		
		if(this.getUser().addSponsorPubli(publi))
			if(publi.getOwner().addWinPubli(publi))
				if(publi.addSponsor(getUser()))
					if(publi.save())
						if(publi.getOwner() instanceof User){
							User user = (User) publi.getOwner();
							result = user.save();
						}else{
							Group group = (Group) publi.getOwner();
							for(Group userGroup:this.getUser().getGroupList())
								if(userGroup.equals(group))
									userGroup.addWinPubli(publi);
							result = group.save();
						}
		
		if(result){
			this.getUser().save();
			int i=0;
			while(i<this.viewList.size()){
				try{
					PubliView publiView = this.viewList.get(i);
					publiView.addWin(publi);
				}catch(ConcurrentModificationException e){
					
				}
				i++;
			}
		}
		
		return result;
	}

	@Override
	public Boolean unadme(Publi publi) {
		Boolean result = false;
		
		if(this.getUser().removeSponsorPubli(publi))
			if(publi.getOwner().removeWinPubli(publi))
				if(publi.removeSponsor(getUser()))
					if(publi.save())
						if(this.getUser().save())
							if(publi.getOwner() instanceof User){
								User user = (User) publi.getOwner();
								result = user.save();
							}else{
								Group group = (Group) publi.getOwner();
								result = group.save();
							}
							
					
		
		if(result){
			int i=0;
			while(i<this.viewList.size()){
				try{
					PubliView publiView = this.viewList.get(i);
					publiView.removeWin(publi);;				
				}catch(ConcurrentModificationException e){
					
				}
				i++;
			}
		}
		
		return result;
	}

	@Override
	public Boolean isUserSponsor(Publi publi) {
		return publi.isSponsor(this.getUser());
	}
}
