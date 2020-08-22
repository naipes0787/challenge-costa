package ar.com.wolox.challengecosta.repository;

import ar.com.wolox.challengecosta.model.AccessType;
import ar.com.wolox.challengecosta.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT au.user FROM AlbumUser AS au "
            + "WHERE au.album.id = :albumId AND au.accessType = :accessTypeId")
    List<User> findByAlbumAndAccessType(@Param("albumId") Long albumId,
            @Param("accessTypeId") AccessType accessType);

    Optional<User> findByRestId(Long restId);

}
