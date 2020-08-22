package ar.com.wolox.challengecosta.controller;

import ar.com.wolox.challengecosta.model.Album;
import ar.com.wolox.challengecosta.model.User;
import ar.com.wolox.challengecosta.service.AlbumServiceImpl;
import ar.com.wolox.challengecosta.util.Constants;
import java.util.List;
import javax.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/shared-albums")
public class AlbumUserController {

    @Autowired
    AlbumServiceImpl albumService;

    /**
     * Associate an album to a user with the giving access permission
     */
    @PostMapping("/shareAlbum")
    public void shareAlbumWithUser(@PathParam("albumId") Long albumId,
            @PathParam("userId") Long userId, @PathParam("accessTypeId") Long accessTypeId) {
        RestTemplate restTemplate = new RestTemplate();
        User user = restTemplate.getForObject((Constants.REST_USERS_URL + "/" +
                userId.toString()), User.class);
        Album album = restTemplate.getForObject((Constants.REST_ALBUMS_URL + "/" +
                albumId.toString()), Album.class);
        albumService.shareAlbumWithUser(album, user, accessTypeId);
    }

    /**
     * Update the access permission of the user to the album. If the album is not related to the user, then nothing will
     * happen
     */
    @PutMapping("/shareAlbum")
    public void updateAccessToAlbum(@PathParam("albumId") Long albumId,
            @PathParam("userId") Long userId, @PathParam("accessTypeId") Long accessTypeId) {
        albumService.updateAccessToAlbum(albumId, userId, accessTypeId);
    }

    /**
     * Get all the users with some access permission associated to a specific album
     *
     * @return List<User>
     */
    @GetMapping("/usersByAlbumAndAccessType")
    public List<User> getUsersByAlbumAndAccessType(@PathParam("albumId") Long albumId,
            @PathParam("accessTypeId") Long accessTypeId) {
        return albumService.getUsersByAlbumAndAccessType(albumId, accessTypeId);
    }

}
