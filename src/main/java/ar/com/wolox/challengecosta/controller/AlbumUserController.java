package ar.com.wolox.challengecosta.controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import ar.com.wolox.challengecosta.model.Album;
import ar.com.wolox.challengecosta.model.User;
import ar.com.wolox.challengecosta.service.AlbumServiceImpl;
import ar.com.wolox.challengecosta.util.Constants;

@RestController
@RequestMapping("/")
public class AlbumUserController {

	@Autowired
	AlbumServiceImpl albumService;

	
	/**
	 * 
	 * @return {@link Album}
	 */
	@GetMapping("/shareAlbumWithUser")
	public void shareAlbumWithUser(@PathParam("albumId") Long albumId,
		    @PathParam("userId") Long userId, @PathParam("accessTypeId") Long accessTypeId) {
		RestTemplate restTemplate = new RestTemplate();
		User user = restTemplate.getForObject((Constants.REST_USERS_URL + "/" +
				userId.toString()), User.class);
		Album album = restTemplate.getForObject((Constants.REST_ALBUMS_URL + "/" +
				albumId.toString()), Album.class);
		User userOwnerAlbum = restTemplate.getForObject((Constants.REST_USERS_URL + "/" +
				album.getUserId().toString()), User.class);
		albumService.shareAlbumWithUser(album, user, accessTypeId, userOwnerAlbum);
	}
	
}
