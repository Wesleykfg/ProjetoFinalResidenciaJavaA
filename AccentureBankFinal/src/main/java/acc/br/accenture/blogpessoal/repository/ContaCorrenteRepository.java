package acc.br.accenture.blogpessoal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import acc.br.accenture.blogpessoal.model.ContaCorrente;

@Repository
public interface ContaCorrenteRepository extends JpaRepository<ContaCorrente, Long> {
	public List<ContaCorrente> findAllByContaCorrenteAgenciaContainingIgnoreCase(String contaCorrenteAgencia);

}
