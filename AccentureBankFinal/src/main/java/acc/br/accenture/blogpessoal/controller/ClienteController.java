package acc.br.accenture.blogpessoal.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import acc.br.accenture.blogpessoal.model.Cliente;
import acc.br.accenture.blogpessoal.model.Cliente;
import acc.br.accenture.blogpessoal.repository.ClienteRepository;
import acc.br.accenture.blogpessoal.service.ClienteService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@GetMapping
	public ResponseEntity<List<Cliente>> getAllCliente(){
		List<Cliente> clientes = clienteService.listarAllClientes();
		return ResponseEntity.ok().body(clientes);
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/{cpf}", method = RequestMethod.GET)
	public ResponseEntity<Optional> getByCpf(@PathVariable String cpf) throws ObjectNotFoundException{
		try {
			Cliente cliente = clienteService.localizarClientePorCpf(cpf);
			return ResponseEntity.ok().body(Optional.ofNullable(cliente));
		}catch(ObjectNotFoundException e){
			return ResponseEntity.badRequest().body(Optional.ofNullable(e.getMessage()));
		}catch(Exception e){
			return ResponseEntity.badRequest().body(Optional.ofNullable(e.getMessage()));
		}
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<String> postCliente(@RequestBody Cliente objcliente) {
		try {
			if (clienteService.checkExistingCpf(objcliente.getCpf())) throw new Exception("ATEN��O!!! O CPF j� existe, por gentileza informe um outro CPF!");
			
			if (!clienteService.isValidCpf(objcliente.getCpf())) throw new Exception("ATEN��O!!! O CPF informado � inv�lido! Por gentileza informe um outro CPF!");
			
			Cliente cliente = clienteService.salvar(objcliente);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId()).toUri();
			return ResponseEntity.created(uri).build();
		}catch(Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping("/clientes/{id}")
	public ResponseEntity<Cliente> getById(@PathVariable long id)
	{
		return clienteRepository.findById(id)
			.map(resposta-> ResponseEntity.ok(resposta))
			.orElse(ResponseEntity.notFound().build());
	}
	
	@PutMapping("/atualizar/{id}")
	public ResponseEntity<Cliente> putCliente(@Valid @RequestBody Cliente cliente) 
	{
			return clienteRepository.findById(cliente.getId())
				.map(resposta -> ResponseEntity.ok().body(clienteRepository.save(cliente)))
				.orElse(ResponseEntity.notFound().build());

	}
	
	@RequestMapping(value="{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> putCliente(@RequestBody Cliente objCliente, @PathVariable Long id){
		objCliente.setId(id);
		clienteService.alterarCliente(objCliente);
		return ResponseEntity.noContent().build();
	}
}
