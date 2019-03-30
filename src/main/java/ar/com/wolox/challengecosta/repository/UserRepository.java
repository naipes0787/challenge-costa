package ar.com.wolox.challengecosta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ar.com.wolox.challengecosta.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query("SELECT au.user FROM AlbumUser AS au "
			+ "WHERE au.album.id = :albumId AND au.accessType.id = :accessTypeId")
	List<User> findByAlbumAndAccessType(@Param("albumId") Long albumId, @Param("accessTypeId") Long accessTypeId);
	
}