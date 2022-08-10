package acc.br.accenture.blogpessoal.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import acc.br.accenture.blogpessoal.model.Extrato;
import acc.br.accenture.blogpessoal.repository.ExtratoRepository;
import acc.br.accenture.blogpessoal.service.ExtratoService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/extratos")
public class ExtratoController {
	
	@Autowired
	private ExtratoRepository extratoRepository;
	
	@Autowired
	private ExtratoService extratoService;
	
	/*@GetMapping
	public ResponseEntity<List <Extrato>> getAll(){
		return ResponseEntity.ok(extratoRepository.findAll());
	}*/
	
	@GetMapping("/{id}") 
	public ResponseEntity<Extrato> getById(@PathVariable long id){
		return extratoRepository.findById(id)
			.map(resposta -> ResponseEntity.ok(resposta))
			.orElse(ResponseEntity.notFound().build());	
	}
	
	@GetMapping("/extrato/{extrato}")
	public ResponseEntity<List<Extrato>> getByName(@PathVariable String extrato){
		return ResponseEntity.ok(extratoRepository.findAllByOperacaoContainingIgnoreCase(extrato));
	}
	
	/*@PostMapping
	public ResponseEntity<Extrato> postExtrato(@RequestBody @Valid Extrato extrato){
		return ResponseEntity.status(HttpStatus.CREATED).body(extratoRepository.save(extrato));	
	}*/
	
	@PutMapping
	public ResponseEntity<Extrato> putExtrato(@Valid @RequestBody Extrato extrato) {
					
		return extratoRepository.findById(extrato.getId())
				.map(resposta -> {
					return ResponseEntity.ok().body(extratoRepository.save(extrato));
				})
				.orElse(ResponseEntity.notFound().build());

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteExtrato(@PathVariable long id) {
		
		return extratoRepository.findById(id)
				.map(resposta -> {
					extratoRepository.deleteById(id);
					return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
				})
				.orElse(ResponseEntity.notFound().build());
	}
	
		@RequestMapping(method=RequestMethod.GET)
		public ResponseEntity<List<Extrato>> getAll(){
			List<Extrato> extratos = extratoService.getAllExtrato();
			return ResponseEntity.ok().body(extratos);
		}
		
		@RequestMapping(method = RequestMethod.POST)
		public ResponseEntity<String> putExtratos(@RequestBody Extrato objextrato) {
			try {
				Extrato extrato = extratoService.salvar(objextrato);
				URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(extrato.getId()).toUri();
				return ResponseEntity.created(uri).build();
			}catch(Exception e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}
		}
	
	
}
