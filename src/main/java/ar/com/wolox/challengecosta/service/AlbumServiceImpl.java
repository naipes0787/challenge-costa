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
import ar.com.wolox.challengecosta.repository.AccessTypeRepository;
import ar.com.wolox.challengecosta.repository.AlbumRepository;
import ar.com.wolox.challengecosta.repository.AlbumUserRepository;
import ar.com.wolox.challengecosta.repository.UserRepository;

@Service
public class AlbumServiceImpl implements AlbumService {

	@Autowired
	AlbumUserRepository albumUserRepository;
	@Autowired
	AccessTypeRepository accessTypeRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	AlbumRepository albumRepository;
	
	@Transactional
	@Override
	public void shareAlbumWithUser(Album album, User user, Long accessTypeId) {
		AccessType accessType = accessTypeRepository.findById(accessTypeId).
				orElseThrow(() -> new ResourceNotFoundException("AccessType", "id", accessTypeId));
		AlbumUser albumUser = new AlbumUser(album, user, accessType);
		if(!this.existsAlbumUser(album.getId(), user.getId())) {
			userRepository.save(user);
			albumRepository.save(album);
			albumUserRepository.save(albumUser);
		}
	}
	
	@Override
	public Boolean existsAlbumUser(Long albumId, Long userId) {
		if(albumUserRepository.findByAlbum_idAndUser_id(albumId, userId) != null) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	@Override
	public void updateAccessToAlbum(Long albumId, Long userId, Long accessTypeId) {
		AlbumUser albumUser = albumUserRepository.findByAlbum_idAndUser_id(albumId, userId);
		if(albumUser != null) {
			AccessType accessType = accessTypeRepository.findById(accessTypeId).
					orElseThrow(() -> new ResourceNotFoundException("AccessType", "id", accessTypeId));
			albumUser.setAccessType(accessType);
			albumUserRepository.save(albumUser);
		}
	}
	
	@Override
	public List<User> getUsersByAlbumAndAccessType(Long albumId, Long accessTypeId){
		return userRepository.findByAlbumAndAccessType(albumId, accessTypeId);
	}
	
}
