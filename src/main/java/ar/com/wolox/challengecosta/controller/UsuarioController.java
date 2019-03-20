package ar.com.wolox.challengecosta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import ar.com.wolox.challengecosta.exception.NotFoundException;
import ar.com.wolox.challengecosta.model.Usuario;
import ar.com.wolox.challengecosta.repository.UsuarioRepository;
import ar.com.wolox.challengecosta.util.Constantes;

@RestController
@RequestMapping("/")
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;
    
	/**
	 * Obtener todos los usuarios del sistema
	 * @return List<Usuario>
	 */
	@GetMapping("/usuarios")
	public List<Usuario> getAllUsuarios2() {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<Usuario>> response = restTemplate.exchange(
				Constantes.REST_USUARIOS_URL, HttpMethod.GET, null,
		  new ParameterizedTypeReference<List<Usuario>>(){});
		List<Usuario> usuarios = response.getBody();
		return usuarios;
	}
    
	/**
	 * Obtener el usuario solicitado según el id pasado por parámetro
	 * @return Usuario
	 */
	@GetMapping("/usuarios/{id}")
	public Usuario getUsuarioById2(@PathVariable(value = "id") Long usuarioId) {
		RestTemplate restTemplate = new RestTemplate();
		Usuario usuario = restTemplate.getForObject((Constantes.REST_USUARIOS_URL + "/" +
				usuarioId.toString()), Usuario.class);
		return usuario;
	}

}