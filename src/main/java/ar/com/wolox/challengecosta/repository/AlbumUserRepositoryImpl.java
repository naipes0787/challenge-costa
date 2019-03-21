package ar.com.wolox.challengecosta.repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import ar.com.wolox.challengecosta.model.AlbumUser;

@Repository
public class AlbumUserRepositoryImpl implements AlbumUserRepositoryCustom {
	
	@PersistenceContext
    EntityManager entityManager;

	@Override
	public Boolean existsAlbumUser(Long albumId, Long userId) {
		Query query = entityManager.createNativeQuery("SELECT au.* FROM album_user au " +
                "WHERE au.album_id = ? AND au.user_id = ?", AlbumUser.class);
        query.setParameter(1, albumId);
        query.setParameter(2, userId);
        try {
        	query.getSingleResult();
        } catch (NoResultException e) {
        	// Si no hay resultados, no existe el objeto
        	return Boolean.FALSE;
        }
        return Boolean.TRUE;
	}
		
}