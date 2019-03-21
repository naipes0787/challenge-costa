package ar.com.wolox.challengecosta.model;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "album")
public class Album {
	@Id
	private Long id;
	
	@NotBlank
	@JsonProperty("title")
	private String title;
	
	@JsonProperty("userId")
	private Long userId;
	
	@OneToMany(mappedBy = "album")
	private
	List<AlbumUser> sharedAlbum;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public List<AlbumUser> getSharedAlbum() {
		return sharedAlbum;
	}

	public void setSharedAlbum(List<AlbumUser> sharedAlbum) {
		this.sharedAlbum = sharedAlbum;
	}

}