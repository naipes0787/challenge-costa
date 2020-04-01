package ar.com.wolox.challengecosta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.wolox.challengecosta.model.AlbumUser;

import java.util.Optional;

@Repository
public interface AlbumUserRepository extends JpaRepository<AlbumUser, Long> {
	
	Optional<AlbumUser> findByAlbum_restIdAndUser_restId(Long albumId, Long userId);
	
}