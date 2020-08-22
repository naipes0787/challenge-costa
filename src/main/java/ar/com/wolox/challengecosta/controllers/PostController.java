package ar.com.wolox.challengecosta.controllers;

import ar.com.wolox.challengecosta.dtos.PostDTO;
import ar.com.wolox.challengecosta.exceptions.ResourceNotFoundException;
import ar.com.wolox.challengecosta.models.Post;
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
@RequestMapping("/posts")
public class PostController {

    /**
     * Get all the posts
     *
     * @return List<Post>
     */
    @GetMapping
    public List<PostDTO> getAllPosts() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<PostDTO>> response = restTemplate.exchange(
                Constants.REST_POSTS_URL, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<PostDTO>>() {
                });
        return response.getBody();
    }

    /**
     * Get a specific post by id
     *
     * @param postId The id of the post
     *
     * @return {@link Post}
     */
    @GetMapping("/{id}")
    public Post getPostById(@PathVariable(value = "id") Long postId) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            return restTemplate.getForObject((Constants.REST_POSTS_URL + "/" +
                    postId.toString()), Post.class);
        } catch (HttpClientErrorException e) {
            HttpStatus status = e.getStatusCode();
            if (status != HttpStatus.NOT_FOUND) {
                throw e;
            } else {
                throw new ResourceNotFoundException("Post", "postId", postId.toString());
            }
        }
    }

}
