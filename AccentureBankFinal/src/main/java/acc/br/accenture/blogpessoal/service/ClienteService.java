package acc.br.accenture.blogpessoal.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import acc.br.accenture.blogpessoal.model.Cliente;
import acc.br.accenture.blogpessoal.repository.ClienteRepository;

@Service
public class ClienteService {
	@Autowired
	ClienteRepository clienteRepository;
	
	public List<Cliente> getAllCliente(){
		List<Cliente> clientes = new ArrayList<Cliente>();
		clienteRepository.findAll().forEach(cliente -> clientes.add(cliente));
		return clientes;
		
	}
	
	@RequestMapping
	public Cliente getClienteById(long id) {
		return clienteRepository.findById(id).get();
	}
	
	@RequestMapping
	public Cliente getClienteByNome(String nome) {
		return clienteRepository.findByNome(nome).get();
	}
	
	public void saveOrUpdate(Cliente cliente) {
		clienteRepository.save(cliente);
		
	}
	
	public void delete(long id) {
		clienteRepository.deleteById(id);
		
	}
	
	public List<Cliente> listarAllClientes(){
		return (List<Cliente>) clienteRepository.findAll();
	}
	
	public Cliente localizarClientePorCpf(String cpf) throws ObjectNotFoundException{
		Optional<Cliente> cliente = clienteRepository.findByCpf(cpf);
		return cliente.orElseThrow(() -> new ObjectNotFoundException(null, "Cliente n�o encontrado!"));
	}
	
	public Cliente salvarCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	public Boolean checkExistingCpf(String cpf){
		return clienteRepository.existsByCpf(cpf);
	}
	
	public Boolean isValidCpf(String cpf) {

        int[] cpfValido = new int[11];
        String letra;

        for (int i = 0; i < cpf.length(); i++) {
            letra = String.valueOf(cpf.charAt(i));
            cpfValido[i] = Integer.parseInt(letra);
        }

        int [] peso = {11,10,9,8,7,6,5,4,3,2};
        int primeirodigito = 0, segundodigito = 0;

        int resultado1 = cpfValido[0]*peso[1] + cpfValido[1]*peso[2] + cpfValido[2]*peso[3] +
                         cpfValido[3]*peso[4] + cpfValido[4]*peso[5] + cpfValido[5]*peso[6] +
                         cpfValido[6]*peso[7] + cpfValido[7]*peso[8] + cpfValido[8]*peso[9];

        int resultado2 = cpfValido[0]*peso[0] + cpfValido[1]*peso[1] + cpfValido[2]*peso[2] +
                         cpfValido[3]*peso[3] + cpfValido[4]*peso[4] + cpfValido[5]*peso[5] +
                         cpfValido[6]*peso[6] + cpfValido[7]*peso[7] + cpfValido[8]*peso[8] +
                         cpfValido[9]*peso[9];

        int resto1 = resultado1 % 11;
        int resto2 = resultado2 % 11;

        if (resto1 < 2){
            primeirodigito = 0;
            } else {
                primeirodigito = 11 - resto1;
            }

        if (resto2 < 2){
            segundodigito = 0;
            } else {
                segundodigito = 11 - resto2;
            }

        if (primeirodigito == cpfValido[9] && segundodigito == cpfValido[10]){
            return true;
        }else {
        	return false;
        }
	}
	
	public Cliente salvar(Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	public void excluir(Long id) {
		clienteRepository.deleteById(id);
	}
	
	public Cliente alterarCliente(Cliente objCliente) {
		Cliente cliente = getClienteById(objCliente.getId());
		cliente.setNome(objCliente.getNome());
		cliente.setCpf(objCliente.getCpf());
		cliente.setFone(objCliente.getFone());
		cliente.setAgencia(objCliente.getAgencia());
		return salvar(cliente);
	}

}
