package ar.com.wolox.challengecosta.model;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "user")
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
	@Id
	private Long id;
	
	@NotBlank
	@JsonProperty("name")
	private String name;
	
	@NotBlank
	@JsonProperty("username")
	private String username;
	
	@NotBlank
	@JsonProperty("email")
	private String email;
	
	@OneToMany(mappedBy = "user")
	private
	List<AlbumUser> sharedAlbum;
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<AlbumUser> getSharedAlbum() {
		return sharedAlbum;
	}

	public void setSharedAlbum(List<AlbumUser> sharedAlbum) {
		this.sharedAlbum = sharedAlbum;
	}

}