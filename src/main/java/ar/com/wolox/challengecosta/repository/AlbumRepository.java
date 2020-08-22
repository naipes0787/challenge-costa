package ar.com.wolox.challengecosta.repository;

import ar.com.wolox.challengecosta.model.Album;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {

    Optional<Album> findByRestId(Long restId);

}
