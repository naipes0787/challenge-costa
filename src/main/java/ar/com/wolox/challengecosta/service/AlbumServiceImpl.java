package ar.com.wolox.challengecosta.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.wolox.challengecosta.exception.ResourceNotFoundException;
import ar.com.wolox.challengecosta.model.AccessType;
import ar.com.wolox.challengecosta.model.Album;
import ar.com.wolox.challengecosta.model.AlbumUser;
import ar.com.wolox.challengecosta.model.User;
import ar.com.wolox.challengecosta.repository.AlbumRepository;
import ar.com.wolox.challengecosta.repository.AlbumUserRepository;
import ar.com.wolox.challengecosta.repository.UserRepository;

@Service
public class AlbumServiceImpl implements AlbumService {

	@Autowired
	private AlbumUserRepository albumUserRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AlbumRepository albumRepository;
	
	@Transactional
	@Override
	public void shareAlbumWithUser(Album album, User user, Long accessTypeId) {
		AccessType accessType = AccessType.getById(accessTypeId);
		if(accessType.equals(AccessType.UNKNOWN)){
			throw new ResourceNotFoundException(AccessType.class.toString(), "id", accessTypeId);
		} else {
			/* Sólo se guardará el permiso si no existe el registro de AlbumUser 
			 * y no se está tratando de compartir con el propietario (Ya que no tendría sentido) */
			if(!this.existsAlbumUser(album.getRestId(), user.getRestId())
					&& !album.getOwnerRestId().equals(user.getRestId())) {
				user = userRepository.findByRestId(user.getRestId())
						.orElse(userRepository.save(user));
				album = albumRepository.findByRestId(album.getRestId())
						.orElse(albumRepository.save(album));
				AlbumUser albumUser = new AlbumUser(album, user, accessType);
				albumUserRepository.save(albumUser);
			}
		}
	}
	
	@Override
	public Boolean existsAlbumUser(Long albumId, Long userId) {
		return !(Optional.empty().equals(
				albumUserRepository.findByAlbum_restIdAndUser_restId(albumId, userId)));
	}
	
	@Override
	public Boolean existsAlbum(Long restId) {
		return !(Optional.empty().equals(
				albumRepository.findByRestId(restId)));
	}
	
	@Override
	public Boolean existsUser(Long restId) {
		return !(Optional.empty().equals(
				userRepository.findByRestId(restId)));
	}

	@Override
	public void updateAccessToAlbum(Long albumId, Long userId, Long accessTypeId) {
		AccessType accessType = AccessType.getById(accessTypeId);
		if(accessType.equals(AccessType.UNKNOWN)){
			throw new ResourceNotFoundException(AccessType.class.toString(), "id", accessTypeId);
		} else {
			AlbumUser albumUser = albumUserRepository.findByAlbum_restIdAndUser_restId(albumId, userId)
					.orElseThrow(() -> new ResourceNotFoundException(AlbumUser.class.toString(),
							"id", (albumId + ", " + userId)));
			albumUser.setAccessType(accessType);
			albumUserRepository.save(albumUser);
		}
	}
	
	@Override
	public List<User> getUsersByAlbumAndAccessType(Long albumId, Long accessTypeId){
		AccessType accessType = AccessType.getById(accessTypeId);
		if(accessType.equals(AccessType.UNKNOWN)){
			throw new ResourceNotFoundException(AccessType.class.toString(), "id", accessTypeId);
		} else {
			return userRepository.findByAlbumAndAccessType(albumId, accessType);
		}
	}
	
}
