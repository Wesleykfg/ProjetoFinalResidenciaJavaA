package acc.br.accenture.blogpessoal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import acc.br.accenture.blogpessoal.model.Cliente;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long> {
	public List<Cliente> findAllByCpfContainingIgnoreCase(String cpf);
	
	boolean existsByCpf(String cpf);
	
	Optional<Cliente> findByCpf(String cpf);

	public Optional<Cliente> findByNome(String nome);

}