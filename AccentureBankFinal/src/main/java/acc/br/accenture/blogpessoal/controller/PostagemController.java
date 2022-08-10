package acc.br.accenture.blogpessoal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import acc.br.accenture.blogpessoal.model.Postagem;
import acc.br.accenture.blogpessoal.repository.PostagemRepository;

@RestController
@RequestMapping("/postagens")
@CrossOrigin(origins = "*", allowedHeaders = "*") // Comando para o front acessar o back end
public class PostagemController {
	
	@Autowired // injeção de dependencia - transferir a responsabilidade da classe postagem e interface repository para o spring
	private PostagemRepository postagemRepository;
	
	//retorna a lista com todos os recursos que estão no endereço /postagens
	@GetMapping
	public ResponseEntity<List<Postagem>> getAll(){
		//Classe responsavel por responder as requisições
		
	return ResponseEntity.ok(postagemRepository.findAll());
	// semelhante a select * from tb_postagem;
	}
	
	//retorna um recurso identificado pelo ID
	@GetMapping("/{id}") //Metodo Lambida
	public ResponseEntity<Postagem> getById(@PathVariable long id){
		return postagemRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.notFound().build());
	
	}
		
	/*Optional <Postagem> postagem = postagemRepository.findById(id);
	if (postagem.isPresent()) {
		return ResponseEntity.ok(resposta);
	}
	else {
		return ResponseEntity.notFound().build();
	}*/
	
	//retorna recursos que conhentam caracteres informados na busca
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity <List<Postagem>> getByTitulo(@PathVariable String titulo){
		return ResponseEntity.ok(postagemRepository.findAllByTituloContainingIgnoreCase(titulo));
	}
	
	//insere novo recurso
	@PostMapping
	public ResponseEntity<Postagem> postPostagem(@RequestBody Postagem postagem){
		return ResponseEntity.status(HttpStatus.CREATED).body(postagemRepository.save(postagemRepository.save(postagem)));
		
	}
	
	// atualiza um recurso existente
	@PutMapping
	public ResponseEntity<Postagem> putPostagem(@RequestBody Postagem postagem){
		return ResponseEntity.status(HttpStatus.OK).body(postagemRepository.save(postagemRepository.save(postagem)));
		
	}
	
	// delete pelo id
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletaPostagem(@PathVariable long id) {
	   if (!postagemRepository.existsById(id)) {
	        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

	    }
	    postagemRepository.deleteById(id);
	    return ResponseEntity.noContent().build();
	}
}
