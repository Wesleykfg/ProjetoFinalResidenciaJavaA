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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import acc.br.accenture.blogpessoal.model.ContaCorrente;
import acc.br.accenture.blogpessoal.repository.ContaCorrenteRepository;
import acc.br.accenture.blogpessoal.service.ContaCorrenteService;
import acc.br.accenture.blogpessoal.utilitarios.Valor;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/contascorrentes")
public class ContaCorrenteController {
	
	@Autowired
	private ContaCorrenteRepository contaCorrenteRepository;
	
	@Autowired
	private ContaCorrenteService contaCorrenteService;
	
	@GetMapping
	public ResponseEntity<List <ContaCorrente>> getAll(){
		return ResponseEntity.ok(contaCorrenteRepository.findAll());
	}
	
	@GetMapping("/{id}") 
	public ResponseEntity<ContaCorrente> getById(@PathVariable long id){
		return contaCorrenteRepository.findById(id)
			.map(resposta -> ResponseEntity.ok(resposta))
			.orElse(ResponseEntity.notFound().build());	
	}
	
	@GetMapping("/contaCorrente/{contaCorrente}")
	public ResponseEntity<List<ContaCorrente>> getByName(@PathVariable String contaCorrente){
		return ResponseEntity.ok(contaCorrenteRepository.findAllByContaCorrenteAgenciaContainingIgnoreCase(contaCorrente));
	}
	
	@PostMapping
	public ResponseEntity<ContaCorrente> postContaCorrente(@RequestBody @Valid ContaCorrente contaCorrente){
		return ResponseEntity.status(HttpStatus.CREATED).body(contaCorrenteRepository.save(contaCorrente));	
	}
	
	@PutMapping
	public ResponseEntity<ContaCorrente> putContaCorrente(@Valid @RequestBody ContaCorrente contaCorrente) {
					
		return contaCorrenteRepository.findById(contaCorrente.getId())
				.map(resposta -> {
					return ResponseEntity.ok().body(contaCorrenteRepository.save(contaCorrente));
				})
				.orElse(ResponseEntity.notFound().build());

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteContaCorrente(@PathVariable long id) {
		
		return contaCorrenteRepository.findById(id)
				.map(resposta -> {
					contaCorrenteRepository.deleteById(id);
					return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
				})
				.orElse(ResponseEntity.notFound().build());
	}
	
	/*@PostMapping("/depositar/{id}/{valor}")
	public void deposito(@PathVariable("id") long id, @PathVariable("valor") double valor){
		
		contaCorrenteService.depositar(id, valor);		
	}*/
	
	@RequestMapping(value="/depositoconta/{id}", method = RequestMethod.PUT)
	public ResponseEntity<String> depositoConta(@RequestBody Valor objBody, @PathVariable int id) throws Exception {
		try {
			objBody.setValor(Math.abs(objBody.getValor()));
			objBody.setId(id);
			Boolean result = contaCorrenteService.deposito(objBody);
			return result ? ResponseEntity.ok().body("O deposito foi realizado com sucesso") : ResponseEntity.ok().body("Opa, ocorreu um imprevisto, por gentileza, tente novamente!!!");
		}catch(Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
		@RequestMapping(value="transferenciaconta/{idOrigem}/{idDestino}", method = RequestMethod.PUT)
		public ResponseEntity<String> depositarConta(@RequestBody Valor objBody, @PathVariable Integer idOrigem, @PathVariable Integer idDestino) throws Exception {
			try {
				objBody.setValor(Math.abs(objBody.getValor()));
				objBody.setId(idOrigem);
				Boolean result = contaCorrenteService.transferencia(objBody, idDestino);
				return result ? ResponseEntity.ok().body("A transferencia foi realizada com sucesso!") : ResponseEntity.ok().body("ATEN��O!!! O saldo � insuficiente! Por Gentileza, tente novamente.");
			}catch(Exception e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}
		}
		
		@RequestMapping(value="/saqueconta/{id}", method = RequestMethod.PUT)
		public ResponseEntity<String> sacarConta(@RequestBody Valor objBody, @PathVariable Integer id) {
			try{
				objBody.setValor(Math.abs(objBody.getValor()));
				objBody.setId(id);
				Boolean result = contaCorrenteService.saque(objBody);
				return result ? ResponseEntity.ok().body("O saque foi realizado com sucesso!") : ResponseEntity.ok().body("ATEN��O!! O saldo � insuficiente! Por gentileza, tente novamente.");
			}catch(NumberFormatException e){
				return ResponseEntity.badRequest().body(e.getMessage());
			}catch(Exception e){
				return ResponseEntity.badRequest().body(e.getMessage());
			}
		}
	
	/*@PostMapping("/contacorrentes/sacar/{id}/{valor}")
	public void saque(@PathVariable("id") long id, @PathVariable("valor") double valor){
		contaCorrenteService.sacar(id, valor);
	}
	
	@PostMapping("/contacorrentes/transferir/{idOrigem}/{idDestino}/{valor}")
	public void transferencia(@PathVariable("idOrigem") long idOrigem, @PathVariable("idDestino") long idDestino, @PathVariable("valor") double valor) {
	
		contaCorrenteService.transferir(idOrigem, idDestino, valor);
	}
	
	@PostMapping("/contacorrentes/recalcularSaldo/{id}")
	public void recalculoSaldo(@PathVariable("id") long id) {
		contaCorrenteService.recalcularSaldo(id);*/
		
	}

