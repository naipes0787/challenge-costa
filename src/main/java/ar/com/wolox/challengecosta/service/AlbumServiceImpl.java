package ar.com.wolox.challengecosta.service;

import java.util.List;

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
	AlbumUserRepository albumUserRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	AlbumRepository albumRepository;
	
	@Transactional
	@Override
	public void shareAlbumWithUser(Album album, User user, Long accessTypeId) {
		AccessType accessType = AccessType.getById(accessTypeId);
		if(accessType.equals(AccessType.UNKNOWN)){
			throw new ResourceNotFoundException(AccessType.class.toString(), "id", accessTypeId);
		} else {
			AlbumUser albumUser = new AlbumUser(album, user, accessType);
			if(!this.existsAlbumUser(album.getRestId(), user.getRestId())) {
				if(!this.existsAlbum(album.getRestId())) {
					albumRepository.save(album);					
				}
				if(!this.existsUser(user.getRestId())) {
					userRepository.save(user);					
				}
				albumUserRepository.save(albumUser);
			}
		}
	}
	
	@Override
	public Boolean existsAlbumUser(Long albumId, Long userId) {
		if(albumUserRepository.findByAlbum_restIdAndUser_restId(albumId, userId) != null) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
	
	@Override
	public Boolean existsAlbum(Long restId) {
		if(albumRepository.findByRestId(restId) != null) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
	
	@Override
	public Boolean existsUser(Long restId) {
		if(userRepository.findByRestId(restId) != null) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	@Override
	public void updateAccessToAlbum(Long albumId, Long userId, Long accessTypeId) {
		AlbumUser albumUser = albumUserRepository.findByAlbum_restIdAndUser_restId(albumId, userId);
		if(albumUser != null) {
			AccessType accessType = AccessType.getById(accessTypeId);
			if(accessType.equals(AccessType.UNKNOWN)){
				throw new ResourceNotFoundException(AccessType.class.toString(), "id", accessTypeId);
			} else {
				albumUser.setAccessType(accessType);
				albumUserRepository.save(albumUser);
			}
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
