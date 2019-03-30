package ar.com.wolox.challengecosta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.wolox.challengecosta.model.Album;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {

	Album findByRestId(Long restId);
	
}