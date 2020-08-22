package ar.com.wolox.challengecosta;

import ar.com.wolox.challengecosta.controllers.AlbumController;
import ar.com.wolox.challengecosta.controllers.CommentController;
import ar.com.wolox.challengecosta.controllers.PhotoController;
import ar.com.wolox.challengecosta.controllers.PostController;
import ar.com.wolox.challengecosta.controllers.UserController;
import ar.com.wolox.challengecosta.exceptions.ResourceNotFoundException;
import ar.com.wolox.challengecosta.models.Comment;
import ar.com.wolox.challengecosta.models.Photo;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChallengeCostaApplicationTests {

    @Test(expected = ResourceNotFoundException.class)
    public void testNonExistentUser() {
        UserController userController = new UserController();
        userController.getUserById(15L);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testNonExistentAlbum() {
        AlbumController albumController = new AlbumController();
        albumController.getAlbumById(15000L);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testNonExistentPhoto() {
        PhotoController photoController = new PhotoController();
        photoController.getPhotoById(15000L);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testNonExistentComment() {
        CommentController commentController = new CommentController();
        commentController.getCommentById(15000L);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testNonExistentPost() {
        PostController postController = new PostController();
        postController.getPostById(15000L);
    }

    @Test
    public void testPhotosByUser() {
        PhotoController photoController = new PhotoController();
        List<Photo> photos = photoController.getPhotosByUserId(1L);
        // Se calculó mediante el servicio externo que hay 500 fotos del usuario 1
        Assert.assertEquals(photos.size(), 500);
    }

    @Test
    public void testCommentsByUser() {
        CommentController commentController = new CommentController();
        List<Comment> comment = commentController.getFilteredComment(null, 1L);
        // Se calculó mediante el servicio externo que hay 50 comentarios del usuario 1
        Assert.assertEquals(comment.size(), 50);
    }

}
