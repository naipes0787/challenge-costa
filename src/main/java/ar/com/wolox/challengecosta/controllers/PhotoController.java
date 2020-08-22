package ar.com.wolox.challengecosta.controllers;

import ar.com.wolox.challengecosta.dtos.AlbumDTO;
import ar.com.wolox.challengecosta.dtos.PhotoDTO;
import ar.com.wolox.challengecosta.exceptions.ResourceNotFoundException;
import ar.com.wolox.challengecosta.utils.Constants;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public List<PhotoDTO> getAllPhotos() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<PhotoDTO>> response = restTemplate.exchange(
                Constants.REST_PHOTOS_URL, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<PhotoDTO>>() {
                });
        return response.getBody();
    }

    /**
     * Get a photo by id
     *
     * @param photoId The id of the photo
     *
     * @return {@link PhotoDTO}
     */
    @GetMapping("/{id}")
    public PhotoDTO getPhotoById(@PathVariable(value = "id") Long photoId) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            return restTemplate.getForObject((Constants.REST_PHOTOS_URL + "/" +
                    photoId.toString()), PhotoDTO.class);
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
     * @return List<PhotoDTO>
     */
    @GetMapping(params = "userId")
    public List<PhotoDTO> getPhotosByUserId(@RequestParam("userId") Long userId) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<AlbumDTO>> responseAlbum = restTemplate.exchange(
                (Constants.REST_ALBUMS_BY_USER_URL + userId), HttpMethod.GET, null,
                new ParameterizedTypeReference<List<AlbumDTO>>() {
                });
        List<AlbumDTO> albums = responseAlbum.getBody();
        List<PhotoDTO> photos = new ArrayList<>();
        albums.forEach(album -> {
            ResponseEntity<List<PhotoDTO>> responsePhoto = restTemplate.exchange(
                    (Constants.REST_PHOTOS_BY_ALBUM_URL + album.getId()), HttpMethod.GET,
                    null, new ParameterizedTypeReference<List<PhotoDTO>>() {
                    });
            photos.addAll(Objects.requireNonNull(responsePhoto.getBody()));
        });
        return photos;
    }

}
