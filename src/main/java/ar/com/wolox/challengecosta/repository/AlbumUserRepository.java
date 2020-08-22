package ar.com.wolox.challengecosta.repository;

import ar.com.wolox.challengecosta.model.AlbumUser;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumUserRepository extends JpaRepository<AlbumUser, Long> {

    Optional<AlbumUser> findByAlbum_restIdAndUser_restId(Long albumId, Long userId);

}
