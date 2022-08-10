package acc.br.accenture.blogpessoal.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import acc.br.accenture.blogpessoal.model.Extrato;

@Repository
public interface ExtratoRepository extends CrudRepository<Extrato, Long>{
	public List<Extrato> findAllByOperacaoContainingIgnoreCase(String operacao);

}