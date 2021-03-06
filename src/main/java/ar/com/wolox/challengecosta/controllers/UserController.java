package ar.com.wolox.challengecosta.controllers;

import ar.com.wolox.challengecosta.dtos.UserDTO;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/users")
public class UserController {

    /**
     * Get all the users
     *
     * @return List<User>
     */
    @GetMapping
    public List<UserDTO> getAllUsers() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<UserDTO>> response = restTemplate.exchange(
                Constants.REST_USERS_URL, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<UserDTO>>() {
                });
        return response.getBody();
    }

    /**
     * Get a user by id
     *
     * @param userId The id of the user
     *
     * @return {@link UserDTO}
     */
    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable(value = "id") Long userId) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            return restTemplate.getForObject((Constants.REST_USERS_URL + "/" + userId), UserDTO.class);
        } catch (HttpClientErrorException e) {
            HttpStatus status = e.getStatusCode();
            if (status != HttpStatus.NOT_FOUND) {
                throw e;
            } else {
                throw new ResourceNotFoundException("User", "userId", userId.toString());
            }
        }
    }

}
