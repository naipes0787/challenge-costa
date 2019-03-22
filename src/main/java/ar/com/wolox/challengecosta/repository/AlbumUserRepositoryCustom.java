package ar.com.wolox.challengecosta.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import ar.com.wolox.challengecosta.model.AlbumUser;
import ar.com.wolox.challengecosta.model.User;

/**
 * Interfaz que contiene consultas personalizadas a la base de datos  
 */
@Repository
public interface AlbumUserRepositoryCustom {
	
	/**
	 * Devuelve TRUE si existe un AlbumUser que relacione al álbum y usuario pasados 
	 * por parámetro
	 * @param albumId
	 * @param userId
	 * @return {@link Boolean}
	 */
	public Boolean existsAlbumUser(Long albumId, Long userId);
	
	/**
	 * Devuelve el objeto AlbumUser asociado al álbum y usuario pasados por parámetro
	 * @param albumId
	 * @param userId
	 * @return {@link AlbumUser}
	 */
	public AlbumUser getAlbumUserByAlbumAndUser(Long albumId, Long userId);
	
	/**
	 * Devuelve una lista de usuarios que estén asociados al álbum y permiso pasados
	 * por parámetro
	 * @param albumId
	 * @param accessTypeId
	 * @return List<User>
	 */
	public List<User> getUsersByAlbumAndAccessType(Long albumId, Long accessTypeId);
	
}