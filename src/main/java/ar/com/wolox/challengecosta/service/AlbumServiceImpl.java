package ar.com.wolox.challengecosta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.wolox.challengecosta.exception.NotFoundException;
import ar.com.wolox.challengecosta.model.AccessType;
import ar.com.wolox.challengecosta.model.Album;
import ar.com.wolox.challengecosta.model.AlbumUser;
import ar.com.wolox.challengecosta.model.User;
import ar.com.wolox.challengecosta.repository.AccessTypeRepository;
import ar.com.wolox.challengecosta.repository.AlbumRepository;
import ar.com.wolox.challengecosta.repository.AlbumUserRepository;
import ar.com.wolox.challengecosta.repository.AlbumUserRepositoryImpl;
import ar.com.wolox.challengecosta.repository.UserRepository;

@Service
public class AlbumServiceImpl implements AlbumService {

	@Autowired
	AlbumUserRepositoryImpl customAlbumUserRepository;
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
	public void shareAlbumWithUser(Album album, User user, Long accessTypeId, 
			User userOwnerAlbum) {
		AccessType accessType = accessTypeRepository.findById(accessTypeId).
				orElseThrow(() -> new NotFoundException("AccessType", "id", accessTypeId));
		AlbumUser albumUser = new AlbumUser(album, user, accessType);
		if(!customAlbumUserRepository.existsAlbumUser(album.getId(), user.getId())) {
			userRepository.save(user);
			userRepository.save(userOwnerAlbum);
			albumRepository.save(album);
			albumUserRepository.save(albumUser);
		}
	}
	
}
