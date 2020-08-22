package ar.com.wolox.challengecosta.repositories;

import ar.com.wolox.challengecosta.models.AlbumUser;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumUserRepository extends JpaRepository<AlbumUser, Long> {

    Optional<AlbumUser> findByAlbumRestIdAndUserRestId(Long albumId, Long userId);

}
