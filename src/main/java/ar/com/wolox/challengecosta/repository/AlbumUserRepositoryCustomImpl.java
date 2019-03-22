package ar.com.wolox.challengecosta.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import ar.com.wolox.challengecosta.model.AlbumUser;
import ar.com.wolox.challengecosta.model.User;

@Repository
public class AlbumUserRepositoryCustomImpl implements AlbumUserRepositoryCustom {
	
	@PersistenceContext
    EntityManager entityManager;

	@Override
	public Boolean existsAlbumUser(Long albumId, Long userId) {
		if(this.getAlbumUserByAlbumAndUser(albumId, userId) != null) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
	
	@Override
	public AlbumUser getAlbumUserByAlbumAndUser(Long albumId, Long userId) {
		Query query = entityManager.createNativeQuery("SELECT au.* FROM album_user au " +
                "WHERE au.album_id = ? AND au.user_id = ?", AlbumUser.class);
        query.setParameter(1, albumId);
        query.setParameter(2, userId);
        try {
        	return (AlbumUser)query.getSingleResult();
        } catch (NoResultException e) {
        	// Si no hay resultados, por el momento no existe el objeto
        	return null;
        }
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUsersByAlbumAndAccessType(Long albumId, Long accessTypeId) {
		Query query = entityManager.createNativeQuery("SELECT u.* FROM user u "	+ 
				"INNER JOIN album_user au ON au.user_id = u.id " +
                "WHERE au.album_id = ? AND au.access_type_id = ?");
        query.setParameter(1, albumId);
        query.setParameter(2, accessTypeId);
        return (List<User>)query.getResultList();
	}
		
}