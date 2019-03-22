package ar.com.wolox.challengecosta.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import ar.com.wolox.challengecosta.model.Album;
import ar.com.wolox.challengecosta.model.User;
import ar.com.wolox.challengecosta.service.AlbumServiceImpl;
import ar.com.wolox.challengecosta.util.Constants;

@RestController
@RequestMapping("/api")
public class AlbumUserController {

	@Autowired
	AlbumServiceImpl albumService;

	/**
	 * Post request para asociar un álbum a un usuario con un tipo de permiso.
	 * Si el álbum ya está asociado al usuario, NO se agrega otro registro.
	 */
	@PostMapping("/shareAlbum")
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
	
	/**
	 * Put request para actualizar el tipo de permiso de un usuario a un álbum.
	 * Si el álbum no está asociado al usuario, NO se realiza ninguna acción.
	 */
	@PutMapping("/shareAlbum")
	public void updateAccessToAlbum(@PathParam("albumId") Long albumId,
		    @PathParam("userId") Long userId, @PathParam("accessTypeId") Long accessTypeId) {
		albumService.updateAccessToAlbum(albumId, userId, accessTypeId);
	}
	
	/**
	 * Get request para obtener todos los usuarios asociados al álbum pasado por parámetro
	 * con el permiso también pasado por parámetro
	 * @return List<User>
	 */
	@GetMapping("/usersByAlbumAndAccessType")
	public List<User> getUsersByAlbumAndAccessType(@PathParam("albumId") Long albumId,
		    @PathParam("accessTypeId") Long accessTypeId) {
		List<User> users = albumService.getUsersByAlbumAndAccessType(albumId, accessTypeId);
		return users;
	}
	
}
