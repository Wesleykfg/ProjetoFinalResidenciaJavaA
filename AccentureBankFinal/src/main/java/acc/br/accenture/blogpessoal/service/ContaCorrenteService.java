package acc.br.accenture.blogpessoal.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acc.br.accenture.blogpessoal.model.ContaCorrente;
import acc.br.accenture.blogpessoal.repository.ContaCorrenteRepository;
import acc.br.accenture.blogpessoal.repository.ExtratoRepository;
import acc.br.accenture.blogpessoal.utilitarios.Valor;

@Service
public class ContaCorrenteService {
	@Autowired
	private ContaCorrenteRepository contaCorrenteRepository;
	@Autowired
	ExtratoService extratoService;
	@Autowired
	ExtratoRepository extratoRepository;
	
	public List<ContaCorrente> getAllContaCorrente(){
		List<ContaCorrente> contaCorrentes = new ArrayList<ContaCorrente>();
		contaCorrenteRepository.findAll().forEach(contaCorrente -> contaCorrentes.add(contaCorrente));
		return contaCorrentes;
		
	}
	
	public ContaCorrente getContaCorrenteById(long id) {
		return contaCorrenteRepository.findById(id).get();
	}
	
	public void saveOrUpdate(ContaCorrente contaCorrente) {
		contaCorrenteRepository.save(contaCorrente);
		
	}
	
	public void delete(long id) {
		contaCorrenteRepository.deleteById(id);
		
	}
	
	/*public void depositar(long id, double valor){
		Extrato extrato = new Extrato();
		ContaCorrente contaCorrente = getContaCorrenteById(id);
		List<Extrato> listExtrato = contaCorrente.getExtrato();
		
		contaCorrente.setSaldo(contaCorrente.getSaldo() + valor);
		extrato.setOperacao("Deposito");
		extrato.setValorOperacao(valor);
		extrato.setContaCorrente(contaCorrente);
		
		listExtrato.add(extrato);
		contaCorrente.setExtrato(listExtrato);
		
		saveOrUpdate(contaCorrente);
		extratoService.saveOrUpdate(extrato);
		
	}
	
	public void sacar(long id, double valor){
		Extrato extrato = new Extrato();
		ContaCorrente contaCorrente = getContaCorrenteById(id);
		List<Extrato> listExtrato = contaCorrente.getExtrato();
		
		contaCorrente.setSaldo(contaCorrente.getSaldo() - valor);
		extrato.setOperacao("Saque");
		extrato.setValorOperacao(valor);
		extrato.setContaCorrente(contaCorrente);
		
		listExtrato.add(extrato);
		contaCorrente.setExtrato(listExtrato);
		
		saveOrUpdate(contaCorrente);
		extratoService.saveOrUpdate(extrato);
	}
	
	public void transferir(long idOrigem, long idDestino, double valor){
		ContaCorrente contaCorrenteOrigem = getContaCorrenteById(idOrigem);
		ContaCorrente contaCorrenteDestino= getContaCorrenteById(idDestino);
		
		
		
	}
	
	public void recalcularSaldo(long id){
        ContaCorrente contaCorrente = getContaCorrenteById(id);
        double valorOperacao=0;
        for(Extrato a : contaCorrente.getExtrato()) {
            if(a.getOperacao().contentEquals("Deposito")) {
                valorOperacao += a.getValorOperacao();
            }else if(a.getOperacao().contentEquals("Saque")) {
                valorOperacao -= a.getValorOperacao();
            }else if(a.getOperacao().contentEquals("Transferencia_Retirada")) {
                valorOperacao -= a.getValorOperacao();
            }else if(a.getOperacao().contentEquals("Transferencia_Recebida")) {
                valorOperacao += a.getValorOperacao();
            }           
        }       
        contaCorrente.setSaldo(valorOperacao);
        saveOrUpdate(contaCorrente);
    }*/
	
	/*public ResponseEntity<List<Extrato>> exibirExtrato(long id){
			
			ContaCorrente contaCorrente = getContaCorrenteById(id);
			
			return contaCorrente.getExtrato()
					.map(resposta -> ResponseEntity.ok(resposta))
					.orElse(ResponseEntity.notFound().build());;
					
	}*/
	
	/*public ResponseEntity<List<Extrato>> exibirExtrato(long id){
		
		ContaCorrente contaCorrente = getContaCorrenteById(id);
		List<Extrato> extratos = extratoService.getAllExtrato();
		List<Extrato> extratoConta = new ArrayList<Extrato>();
		extratos.forEach(extrato -> extrato.getContaCorrente().getId()==contaCorrente.getId() ?extratoConta.add(extrato): return);
		
	}*/
	
	public List<ContaCorrente> listaTodasContas(){
		return (List<ContaCorrente>) contaCorrenteRepository.findAll();
	}
	
	public ContaCorrente salvar(ContaCorrente contacorrente) {
		return contaCorrenteRepository.save(contacorrente);
	}
	
	public Boolean deposito(Valor objBody){
		try {
			ContaCorrente contaCorrente = getContaCorrenteById(objBody.getId());
			contaCorrente.setSaldo(contaCorrente.getSaldo() + objBody.getValor());
			salvar(contaCorrente);
			
			extratoService.createExtrato(contaCorrente, "deposito", objBody.getValor());
			
			return true;
		}catch(Exception e) {
			return false;
		}
	}
		
		public Boolean transferencia(Valor objBody, int idDestino) {
			ContaCorrente contaCorrenteOrigem = getContaCorrenteById(objBody.getId());
			ContaCorrente contaCorrenteDestino = getContaCorrenteById(idDestino);
			double valorEmContaOrigem = contaCorrenteOrigem.getSaldo();
			double valorTransf = objBody.getValor();
			if(valorEmContaOrigem<valorTransf) {
				return false;
			}else {
				contaCorrenteDestino.setSaldo(contaCorrenteDestino.getSaldo() + valorTransf);
				salvar(contaCorrenteDestino);
				contaCorrenteOrigem.setSaldo(valorEmContaOrigem - valorTransf);
				salvar(contaCorrenteOrigem);
				
				extratoService.createExtrato(contaCorrenteOrigem, "transferencia", valorTransf);
				
				return true;
			}
		}
		
		public boolean saque(Valor objBody) {
			ContaCorrente contaCorrente = getContaCorrenteById(objBody.getId());
			double valorEmConta = contaCorrente.getSaldo();
			double valorSaque = objBody.getValor();
			if(valorEmConta<valorSaque) {
				return false;
			}else {
				contaCorrente.setSaldo(valorEmConta - valorSaque);
				salvar(contaCorrente);
				extratoService.createExtrato(contaCorrente, "saque", valorSaque);
				return true;
			}
	}
	
	

}