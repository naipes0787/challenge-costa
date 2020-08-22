package ar.com.wolox.challengecosta.services;

import ar.com.wolox.challengecosta.models.Album;
import ar.com.wolox.challengecosta.models.User;
import java.util.List;

public interface AlbumService {

    /**
     * Associate an album to a user and accesstype
     */
    void shareAlbumWithUser(Album album, User user, Long accessTypeId);

    /**
     * Update the accessType from the user in the specified album
     */
    void updateAccessToAlbum(Long albumId, Long userId, Long accessTypeId);

    /**
     * Return the users associated to the album and the access permissions
     *
     * @return List<User>
     */
    List<User> getUsersByAlbumAndAccessType(Long albumId, Long accessTypeId);

    /**
     * Returns TRUE if there is a relation between the album and the user
     *
     * @return {@link Boolean}
     */
    Boolean existsAlbumUser(Long albumId, Long userId);

    /**
     * Returns TRUE if there is an album with the specified id
     *
     * @return {@link Boolean}
     */
    Boolean existsAlbum(Long albumId);

    /**
     * Returns TRUE if there is an user with the specified id
     *
     * @return {@link Boolean}
     */
    Boolean existsUser(Long userId);

}
