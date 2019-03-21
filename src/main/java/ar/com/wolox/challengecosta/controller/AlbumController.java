package ar.com.wolox.challengecosta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import ar.com.wolox.challengecosta.model.Album;
import ar.com.wolox.challengecosta.service.AlbumServiceImpl;
import ar.com.wolox.challengecosta.util.Constants;

@RestController
@RequestMapping("/")
public class AlbumController {

	@Autowired
	AlbumServiceImpl albumService;

	/**
	 * Obtener todos los álbumes del sistema
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
	 * Obtener el álbum solicitado según el id pasado por parámetro
	 * @return {@link Album}
	 */
	@GetMapping("/albums/{id}")
	public Album getAlbumById(@PathVariable(value = "id") Long albumId) {
		RestTemplate restTemplate = new RestTemplate();
		Album album = restTemplate.getForObject((Constants.REST_ALBUMS_URL + "/" +
				albumId.toString()), Album.class);
		return album;
	}

}