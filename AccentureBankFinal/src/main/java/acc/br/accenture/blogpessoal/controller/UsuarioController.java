package acc.br.accenture.blogpessoal.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import acc.br.accenture.blogpessoal.model.Usuario;
import acc.br.accenture.blogpessoal.model.UsuarioLogin;
import acc.br.accenture.blogpessoal.repository.UsuarioRepository;
import acc.br.accenture.blogpessoal.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping("/all")
	public ResponseEntity <List<Usuario>> getAll(){
		
		return ResponseEntity.ok(usuarioRepository.findAll());
		
	}
	
	/*Executa o método autenticarUsuario da classe de serviço para efetuar
	  o login na api */
	@PostMapping("/logar")
	public ResponseEntity<UsuarioLogin> login(@RequestBody Optional<UsuarioLogin> usuarioLogin) {
		return usuarioService.autenticarUsuario(usuarioLogin)
			.map(respostaAutenticacao -> ResponseEntity.ok(respostaAutenticacao))
			.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}
		
		/* Executa o método cadastrarUsuario da classe de serviço para criar
		 * um novo usuário na api. */
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Usuario> postUsuario(@Valid @RequestBody Usuario usuario) {

		return usuarioService.cadastrarUsuario(usuario)
			.map(respostaCadastro -> ResponseEntity.status(HttpStatus.CREATED).body(respostaCadastro))
			.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());

	}
	
	/* Executa o método atualizarUsuario da classe de serviço para atualizar
	 * os dados de um usuário na api */
	
	@PutMapping("/atualizar")
	public ResponseEntity<Usuario> putUsuario(@Valid @RequestBody Usuario usuario) {
		return usuarioService.atualizarUsuario(usuario)
			.map(resposta -> ResponseEntity.status(HttpStatus.OK).body(resposta))
			.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}

}
