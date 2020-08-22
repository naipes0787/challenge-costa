package ar.com.wolox.challengecosta.controller;

import ar.com.wolox.challengecosta.exception.ResourceNotFoundException;
import ar.com.wolox.challengecosta.model.Album;
import ar.com.wolox.challengecosta.util.Constants;
import java.util.List;
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
@RequestMapping("/api/albums")
public class AlbumController {

    /**
     * Get all albulms
     *
     * @return List<Album>
     */
    @GetMapping
    public List<Album> getAllAlbums() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Album>> response = restTemplate.exchange(
                Constants.REST_ALBUMS_URL, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Album>>() {
                });
        List<Album> albums = response.getBody();
        return albums;
    }

    /**
     * Get specific albulm by id
     *
     * @param albumId The id of the album
     *
     * @return List<Album>
     */
    @GetMapping("/{id}")
    public Album getAlbumById(@PathVariable(value = "id") Long albumId) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            Album album = restTemplate.getForObject((Constants.REST_ALBUMS_URL + "/" +
                    albumId.toString()), Album.class);
            return album;
        } catch (HttpClientErrorException e) {
            HttpStatus status = e.getStatusCode();
            if (status != HttpStatus.NOT_FOUND) {
                throw e;
            } else {
                throw new ResourceNotFoundException("Album", "albumId", albumId.toString());
            }
        }
    }

    /**
     * Get albumns from the specific user
     *
     * @param userId The id of the users
     *
     * @return List<Album> All the albums from the specific user
     */
    @GetMapping("/albumsByUserId")
    public List<Album> getAlbumsByUserId(@PathParam("userId") Long userId) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Album>> responseAlbum = restTemplate.exchange(
                (Constants.REST_ALBUMS_BY_USER_URL + userId.toString()), HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Album>>() {
                });
        List<Album> albums = responseAlbum.getBody();
        return albums;
    }

}
