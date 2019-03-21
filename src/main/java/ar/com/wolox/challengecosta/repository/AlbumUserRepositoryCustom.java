package ar.com.wolox.challengecosta.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface AlbumUserRepositoryCustom {
	
	public Boolean existsAlbumUser(Long albumId, Long userId);
	
}