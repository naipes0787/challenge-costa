package ar.com.wolox.challengecosta.controllers;

import ar.com.wolox.challengecosta.dtos.CommentDTO;
import ar.com.wolox.challengecosta.exceptions.ResourceNotFoundException;
import ar.com.wolox.challengecosta.models.Comment;
import ar.com.wolox.challengecosta.models.Post;
import ar.com.wolox.challengecosta.utils.Constants;
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
@RequestMapping("/comments")
public class CommentController {

    /**
     * Get all the comments
     *
     * @return List<Comment>
     */
    @GetMapping
    public List<CommentDTO> getAllComments() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<CommentDTO>> response = restTemplate.exchange(
                Constants.REST_COMMENTS_URL, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<CommentDTO>>() {
                });
        return response.getBody();
    }

    /**
     * Get a specific comment by id
     *
     * @param commentId The id of the comment
     *
     * @return {@link Comment}
     */
    @GetMapping("/{id}")
    public Comment getCommentById(@PathVariable(value = "id") Long commentId) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            return restTemplate.getForObject((Constants.REST_COMMENTS_URL + "/" +
                    commentId.toString()), Comment.class);
        } catch (HttpClientErrorException e) {
            HttpStatus status = e.getStatusCode();
            if (status != HttpStatus.NOT_FOUND) {
                throw e;
            } else {
                throw new ResourceNotFoundException("Comment", "commentId", commentId.toString());
            }
        }
    }

    /**
     * Get all the comments that have certain name or belongs to the user parameter
     *
     * @param name The name of the comment
     * @param userId The id of the user
     *
     * @return List<Comment>
     */
    @GetMapping("/filter")
    public List<Comment> getFilteredComment(@PathParam("name") String name,
            @PathParam("userId") Long userId) {
        RestTemplate restTemplate = new RestTemplate();
        List<Comment> comments = new ArrayList<>();
        if (name != null) {
            ResponseEntity<List<Comment>> responseComments = restTemplate.exchange(
                    (Constants.REST_COMMENTS_BY_NAME_URL + name),
                    HttpMethod.GET, null, new ParameterizedTypeReference<List<Comment>>() {
                    });
            comments = responseComments.getBody();
        } else {
            if (userId != null) {
                ResponseEntity<List<Post>> responsePosts = restTemplate.exchange(
                        (Constants.REST_POSTS_BY_USER_URL + userId.toString()), HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<Post>>() {
                        });
                List<Post> posts = responsePosts.getBody();
                List<Comment> commentsByUser = new ArrayList<>();
                posts.forEach(post -> {
                    ResponseEntity<List<Comment>> responseComment = restTemplate.exchange(
                            (Constants.REST_COMMENTS_BY_POST_URL + post.getRestId().toString()), HttpMethod.GET,
                            null, new ParameterizedTypeReference<List<Comment>>() {
                            });
                    commentsByUser.addAll(Objects.requireNonNull(responseComment.getBody()));
                });
                comments = commentsByUser;
            }
        }
        return comments;
    }

}
