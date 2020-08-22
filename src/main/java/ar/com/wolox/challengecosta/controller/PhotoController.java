package ar.com.wolox.challengecosta.controller;

import ar.com.wolox.challengecosta.exception.ResourceNotFoundException;
import ar.com.wolox.challengecosta.model.Album;
import ar.com.wolox.challengecosta.model.Photo;
import ar.com.wolox.challengecosta.util.Constants;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.websocket.server.PathParam;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/photos")
public class PhotoController {

    /**
     * Get all the photos
     *
     * @return List<Photo>
     */
    @GetMapping
    public List<Photo> getAllPhotos() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Photo>> response = restTemplate.exchange(
                Constants.REST_PHOTOS_URL, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Photo>>() {
                });
        return response.getBody();
    }

    /**
     * Get a photo by id
     *
     * @param photoId The id of the photo
     *
     * @return {@link Photo}
     */
    @GetMapping("/{id}")
    public Photo getPhotoById(@PathVariable(value = "id") Long photoId) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            return restTemplate.getForObject((Constants.REST_PHOTOS_URL + "/" +
                    photoId.toString()), Photo.class);
        } catch (HttpClientErrorException e) {
            HttpStatus status = e.getStatusCode();
            if (status != HttpStatus.NOT_FOUND) {
                throw e;
            } else {
                throw new ResourceNotFoundException("Photo", "photoId", photoId.toString());
            }
        }
    }

    /**
     * Get the photos which belongs to the user id
     *
     * @param userId The id of the user
     *
     * @return List<Photo>
     */
    @GetMapping("/photosByUserId")
    public List<Photo> getPhotosByUserId(@PathParam("userId") Long userId) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Album>> responseAlbum = restTemplate.exchange(
                (Constants.REST_ALBUMS_BY_USER_URL + userId.toString()), HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Album>>() {
                });
        List<Album> albums = responseAlbum.getBody();
        List<Photo> photos = new ArrayList<>();
        albums.forEach(album -> {
            ResponseEntity<List<Photo>> responsePhoto = restTemplate.exchange(
                    (Constants.REST_PHOTOS_BY_ALBUM_URL + album.getRestId().toString()), HttpMethod.GET,
                    null, new ParameterizedTypeReference<List<Photo>>() {
                    });
            photos.addAll(Objects.requireNonNull(responsePhoto.getBody()));
        });
        return photos;
    }

}
