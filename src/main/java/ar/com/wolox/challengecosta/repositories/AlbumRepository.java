package ar.com.wolox.challengecosta.repositories;

import ar.com.wolox.challengecosta.models.Album;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {

    Optional<Album> findByRestId(Long restId);

}
