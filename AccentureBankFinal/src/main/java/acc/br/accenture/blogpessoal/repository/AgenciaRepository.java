package acc.br.accenture.blogpessoal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import acc.br.accenture.blogpessoal.model.Agencia;

@Repository
public interface AgenciaRepository extends JpaRepository<Agencia, Long> {
	public List<Agencia> findAllByFoneContainingIgnoreCase(String fone);

}
