package ar.com.wolox.challengecosta.service;

import ar.com.wolox.challengecosta.model.Album;
import ar.com.wolox.challengecosta.model.User;

public interface AlbumService {

	public void shareAlbumWithUser(Album album, User user, Long accessTypeId, 
			User userOwnerAlbum);
	
//	public void updateAccessToAlbum(Album album, User user, Long accessTypeId);
//	
//	public void getUsersByAlbumAndAccessType(Album album, Long accessTypeId);
	
}
