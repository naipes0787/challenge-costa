package ar.com.wolox.challengecosta.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "album_user")
public class AlbumUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(nullable=false)
	private Album album;
	
	@ManyToOne
	@JoinColumn(nullable=false)
	private User user;
	
	@ManyToOne
	@JoinColumn(nullable=false)
	private AccessType accessType;
	
	/**
	 * Constructor que recibe los objetos Album, User y AccessType para
	 * denotar que el Album quedará compartido con User utilizando el permiso
	 * AccessType
	 * @param album
	 * @param user
	 * @param accessType
	 */
	public AlbumUser(Album album, User user, AccessType accessType) {
		this.album = album;
		this.user = user;
		this.accessType = accessType;
	}
	
	public AlbumUser() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	public User getUsuario() {
		return user;
	}

	public void setUsuario(User usuario) {
		this.user = usuario;
	}

	public AccessType getAccessType() {
		return accessType;
	}

	public void setAccessType(AccessType accessType) {
		this.accessType = accessType;
	}
	
}
