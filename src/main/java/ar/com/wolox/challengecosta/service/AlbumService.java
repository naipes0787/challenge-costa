package ar.com.wolox.challengecosta.service;

import java.util.List;

import ar.com.wolox.challengecosta.model.Album;
import ar.com.wolox.challengecosta.model.User;

public interface AlbumService {

	/**
	 * Asociar album a user y accessType (También se inserta el usuario propietario del 
	 * álbum para evitar inconsistencias en la base de datos)
	 * @param album
	 * @param user
	 * @param accessTypeId
	 */
	public void shareAlbumWithUser(Album album, User user, Long accessTypeId);
	
	/**
	 * Se actualiza el accessType que posee el usuario en el álbum en cuestión
	 * @param albumId
	 * @param userId
	 * @param accessTypeId
	 */
	public void updateAccessToAlbum(Long albumId, Long userId, Long accessTypeId);
	
	/**
	 * Devuelve una lista de usuarios que estén asociados al álbum y permiso pasados
	 * por parámetro
	 * @param albumId
	 * @param accessTypeId
	 * @return List<User>
	 */
	public List<User> getUsersByAlbumAndAccessType(Long albumId, Long accessTypeId);
	
	/**
	 * Devuelve TRUE si existe un AlbumUser que relacione al álbum y usuario pasados 
	 * por parámetro
	 * @param albumId
	 * @param userId
	 * @return {@link Boolean}
	 */
	public Boolean existsAlbumUser(Long albumId, Long userId);
	
}
