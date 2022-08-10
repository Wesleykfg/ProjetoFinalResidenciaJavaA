package acc.br.accenture.blogpessoal.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acc.br.accenture.blogpessoal.model.ContaCorrente;
import acc.br.accenture.blogpessoal.model.Extrato;
import acc.br.accenture.blogpessoal.repository.ExtratoRepository;

@Service
public class ExtratoService {
	@Autowired
	ExtratoRepository extratoRepository;
	
	public List<Extrato> getAllExtrato(){
		List<Extrato> extratos = new ArrayList<Extrato>();
		extratoRepository.findAll().forEach(extrato -> extratos.add(extrato));
		return extratos;
		
	}
	
	public Extrato getExtratoById(long id) {
		return extratoRepository.findById(id).get();
	}
	
	public void saveOrUpdate(Extrato extrato) {
		extratoRepository.save(extrato);
		
	}
	
	public void delete(long id) {
		extratoRepository.deleteById(id);
		
	}
	
	public List<Extrato> getAllExtratos(){
		return (List<Extrato>) extratoRepository.findAll();
	}
	
	public Extrato salvar(Extrato extrato) {
		return extratoRepository.save(extrato);
	}
	
	public void createExtrato(ContaCorrente contaCorrente, String operacao, double valor) {
		Extrato extrato = new Extrato();
		extrato.setContaCorrente(contaCorrente);
		extrato.setOperacao(operacao);
		extrato.setValorOperacao(valor);
		salvar(extrato);
	}
}
