package acc.br.accenture.blogpessoal.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acc.br.accenture.blogpessoal.model.Agencia;
import acc.br.accenture.blogpessoal.repository.AgenciaRepository;

@Service
public class AgenciaService {
	
	@Autowired
	AgenciaRepository agenciaRepository;
	
	public List<Agencia> getAllAgencia(){
		List<Agencia> agencias = new ArrayList<Agencia>();
		agenciaRepository.findAll().forEach(agencia -> agencias.add(agencia));
		return agencias;
		
	}
	
	public Agencia getAgenciaById(long id) {
		return agenciaRepository.findById(id).get();
	}
	
	public void saveOrUpdate(Agencia agencia) {
		agenciaRepository.save(agencia);
		
	}
	
	public void delete(long id) {
		agenciaRepository.deleteById(id);
		
	}
	
	public List<Agencia> getAllgencias(){
		return (List<Agencia>) agenciaRepository.findAll();
	}
	
	public Agencia save(Agencia agencia) {
		return agenciaRepository.save(agencia);
	}
	
	public void delete2(Long id) {
		agenciaRepository.deleteById(id);
	}

	public Agencia putAgencias(Agencia objAgencia) {
		// TODO Auto-generated method stub
		Agencia agencia = getAgenciaById(objAgencia.getId());
		agencia.setNome(objAgencia.getNome());
		agencia.setEndereco(objAgencia.getEndereco());
		agencia.setFone(objAgencia.getFone());
		return save(agencia);
		
	}

}
