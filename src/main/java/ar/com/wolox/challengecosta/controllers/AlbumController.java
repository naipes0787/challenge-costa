package ar.com.wolox.challengecosta.controllers;

import ar.com.wolox.challengecosta.dtos.AlbumDTO;
import ar.com.wolox.challengecosta.exceptions.ResourceNotFoundException;
import ar.com.wolox.challengecosta.utils.Constants;
import java.util.List;
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
@RequestMapping("/albums")
public class AlbumController {

    /**
     * Get all albulms
     *
     * @return List<Album>
     */
    @GetMapping
    public List<AlbumDTO> getAllAlbums() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<AlbumDTO>> response = restTemplate.exchange(
                Constants.REST_ALBUMS_URL, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<AlbumDTO>>() {
                });
        return response.getBody();
    }

    /**
     * Get specific albulm by id
     *
     * @param albumId The id of the album
     *
     * @return List<Album>
     */
    @GetMapping("/{id}")
    public AlbumDTO getAlbumById(@PathVariable(value = "id") Long albumId) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            return restTemplate.getForObject((Constants.REST_ALBUMS_URL + "/" +
                    albumId.toString()), AlbumDTO.class);
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
    @GetMapping(params = "userId")
    public List<AlbumDTO> getAlbumsByUserId(@RequestParam(value = "userId") Long userId) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<AlbumDTO>> responseAlbum = restTemplate.exchange(
                (Constants.REST_ALBUMS_BY_USER_URL + userId), HttpMethod.GET, null,
                new ParameterizedTypeReference<List<AlbumDTO>>() {
                });
        return responseAlbum.getBody();
    }

}
