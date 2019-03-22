package ar.com.wolox.challengecosta.controller;

import java.util.ArrayList;
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
import ar.com.wolox.challengecosta.model.Photo;
import ar.com.wolox.challengecosta.util.Constants;

@RestController
@RequestMapping("/api")
public class PhotoController {

	/**
	 * Get request para obtener todas las fotos del sistema
	 * @return List<Photo>
	 */
	@GetMapping("/photos")
	public List<Photo> getAllPhotos() {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<Photo>> response = restTemplate.exchange(
				Constants.REST_PHOTOS_URL, HttpMethod.GET, null,
		  new ParameterizedTypeReference<List<Photo>>(){});
		List<Photo> photos = response.getBody();
		return photos;
	}
    
	/**
	 * Get request para obtener la foto solicitada según el id pasado por parámetro
	 * @return {@link Photo}
	 */
	@GetMapping("/photos/{id}")
	public Photo getPhotoById(@PathVariable(value = "id") Long photoId) {
		RestTemplate restTemplate = new RestTemplate();
		Photo photo = restTemplate.getForObject((Constants.REST_PHOTOS_URL + "/" +
				photoId.toString()), Photo.class);
		return photo;
	}
	
	/**
	 * Get request para obtener las fotos pertenecientes al usuario cuyo id fue pasado por parámetro
	 * @return List<Photo>
	 */
	@GetMapping("/photosByUserId")
	public List<Photo> getPhotosByUserId(@PathParam("userId") Long userId) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<Album>> responseAlbum = restTemplate.exchange(
				(Constants.REST_ALBUMS_BY_USER_URL + userId.toString()), HttpMethod.GET, null,
		  new ParameterizedTypeReference<List<Album>>(){});
		List<Album> albums = responseAlbum.getBody();
		List<Photo> photos = new ArrayList<Photo>();
		albums.forEach(album -> {
			ResponseEntity<List<Photo>> responsePhoto = restTemplate.exchange(
					(Constants.REST_PHOTOS_BY_ALBUM_URL + album.getId().toString()), HttpMethod.GET, 
					null, new ParameterizedTypeReference<List<Photo>>(){});
			photos.addAll(responsePhoto.getBody());
		});
		return photos;
	}

}