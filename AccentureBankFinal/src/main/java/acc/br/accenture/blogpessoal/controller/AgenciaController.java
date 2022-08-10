package acc.br.accenture.blogpessoal.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import acc.br.accenture.blogpessoal.model.Agencia;
import acc.br.accenture.blogpessoal.repository.AgenciaRepository;
import acc.br.accenture.blogpessoal.service.AgenciaService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/agencias")
public class AgenciaController {
	
	@Autowired
	private AgenciaRepository agenciaRepository;
	
	@Autowired
	private AgenciaService agenciaService;
	
	@GetMapping
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Agencia>> getAllAgencia(){
		List<Agencia> agencias = agenciaService.getAllgencias();
		return ResponseEntity.ok().body(agencias);
	}
	
	@GetMapping("/{id}") 
	public ResponseEntity<Agencia> getById(@PathVariable long id){
		return agenciaRepository.findById(id)
			.map(resposta -> ResponseEntity.ok(resposta))
			.orElse(ResponseEntity.notFound().build());	
	}
	
	@PostMapping
	public ResponseEntity<Agencia> postAgencia(@Valid @RequestBody Agencia agencia){
		return ResponseEntity.status(HttpStatus.CREATED).body(agenciaRepository.save(agencia));	
	}
	
	/*@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> inseriragencia(@RequestBody Agencia objAgencia) {
		Agencia agencia = agenciaService.salvar(objAgencia);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(agencia.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}*/
	
	/*@PutMapping("{id}")
	public ResponseEntity<Agencia> putAgencia(@Valid @RequestBody Agencia agencia) {
					
		return agenciaRepository.findById(agencia.getId())
				.map(resposta -> {
					return ResponseEntity.ok().body(agenciaRepository.save(agencia));
				})
				.orElse(ResponseEntity.notFound().build());

	}*/
	
	@RequestMapping(value="{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> putAgencia(@RequestBody Agencia objAgencia, @PathVariable Long id){
		objAgencia.setId(id);
		agenciaService.putAgencias(objAgencia);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteAgencia(@PathVariable long id) {
		
		return agenciaRepository.findById(id)
				.map(resposta -> {
					agenciaRepository.deleteById(id);
					return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
				})
				.orElse(ResponseEntity.notFound().build());
	}
	
	
	
	
	
}