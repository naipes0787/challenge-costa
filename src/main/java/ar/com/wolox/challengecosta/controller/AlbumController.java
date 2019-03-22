package ar.com.wolox.challengecosta.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import ar.com.wolox.challengecosta.model.Album;
import ar.com.wolox.challengecosta.util.Constants;

@RestController
@RequestMapping("/api")
public class AlbumController {

	/**
	 * Get request para obtener todos los álbumes del sistema
	 * @return List<Album>
	 */
	@GetMapping("/albums")
	public List<Album> getAllAlbums() {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<Album>> response = restTemplate.exchange(
				Constants.REST_ALBUMS_URL, HttpMethod.GET, null,
		  new ParameterizedTypeReference<List<Album>>(){});
		List<Album> albums = response.getBody();
		return albums;
	}
    
	/**
	 * Get request para obtener el álbum solicitado según el id pasado por parámetro
	 * @return {@link Album}
	 */
	@GetMapping("/albums/{id}")
	public Album getAlbumById(@PathVariable(value = "id") Long albumId) {
		RestTemplate restTemplate = new RestTemplate();
		Album album = restTemplate.getForObject((Constants.REST_ALBUMS_URL + "/" +
				albumId.toString()), Album.class);
		return album;
	}
	
	/**
	 * Get request para obtener los álbumes pertenecientes al usuario cuyo id fue pasado por parámetro
	 * @return List<Album>
	 */
	@GetMapping("/albumsByUserId")
	public List<Album> getAlbumsByUserId(@PathParam("userId") Long userId) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<Album>> responseAlbum = restTemplate.exchange(
				(Constants.REST_ALBUMS_BY_USER_URL + userId.toString()), HttpMethod.GET, null,
		  new ParameterizedTypeReference<List<Album>>(){});
		List<Album> albums = responseAlbum.getBody();
		return albums;
	}

}