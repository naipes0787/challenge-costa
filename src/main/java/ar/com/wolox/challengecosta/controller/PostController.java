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
import ar.com.wolox.challengecosta.model.Post;
import ar.com.wolox.challengecosta.util.Constants;

@RestController
@RequestMapping("/api")
public class PostController {

	/**
	 * Get request para obtener todos los posts del sistema
	 * @return List<Post>
	 */
	@GetMapping("/posts")
	public List<Post> getAllPosts() {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<Post>> response = restTemplate.exchange(
				Constants.REST_POSTS_URL, HttpMethod.GET, null,
		  new ParameterizedTypeReference<List<Post>>(){});
		List<Post> posts = response.getBody();
		return posts;
	}
    
	/**
	 * Get request para obtener el post solicitado según el id pasado por parámetro
	 * @return {@link Post}
	 */
	@GetMapping("/posts/{id}")
	public Post getPostById(@PathVariable(value = "id") Long postId) {
		RestTemplate restTemplate = new RestTemplate();
		try {
			Post post = restTemplate.getForObject((Constants.REST_POSTS_URL + "/" +
					postId.toString()), Post.class);
			return post;
		} catch (HttpClientErrorException e) {
			HttpStatus status = e.getStatusCode();
			if(status != HttpStatus.NOT_FOUND) {
				// Si no es un 404 arrojo la excepción
				throw e;
			} else {
				// Si es un 404 muestro un mensaje más entendible
				throw new ResourceNotFoundException("Post", "postId", postId.toString());
			}
		}
	}

}