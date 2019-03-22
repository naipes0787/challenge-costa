package ar.com.wolox.challengecosta.controller;

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

import ar.com.wolox.challengecosta.exception.ResourceNotFoundException;
import ar.com.wolox.challengecosta.model.User;
import ar.com.wolox.challengecosta.util.Constants;

@RestController
@RequestMapping("/api")
public class UserController {

	/**
	 * Get request para obtener todos los usuarios del sistema
	 * @return List<User>
	 */
	@GetMapping("/users")
	public List<User> getAllUsers() {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<User>> response = restTemplate.exchange(
				Constants.REST_USERS_URL, HttpMethod.GET, null,
		  new ParameterizedTypeReference<List<User>>(){});
		List<User> users = response.getBody();
		return users;
	}
    
	/**
	 * Get request para obtener el usuario solicitado según el id pasado por parámetro
	 * @return {@link User}
	 */
	@GetMapping("/users/{id}")
	public User getUserById(@PathVariable(value = "id") Long userId) {
		RestTemplate restTemplate = new RestTemplate();
		try {
			User user = restTemplate.getForObject((Constants.REST_USERS_URL + "/" +
				userId.toString()), User.class);
			return user;
		} catch (HttpClientErrorException e) {
			HttpStatus status = e.getStatusCode();
			if(status != HttpStatus.NOT_FOUND) {
				// Si no es un 404 arrojo la excepción
				throw e;
			} else {
				// Si es un 404 muestro un mensaje más entendible
				throw new ResourceNotFoundException("User", "userId", userId.toString());
			}
		}
	}

}