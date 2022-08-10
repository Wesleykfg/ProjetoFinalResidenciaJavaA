package acc.br.accenture.blogpessoal.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import acc.br.accenture.blogpessoal.model.Usuario;
import acc.br.accenture.blogpessoal.repository.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository userRepository;

	// Sobrecarrega (@Override) o método loadUserByUsername.
	 

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		/**
		 * Para buscar o usuário no Banco de dados, utilizaremos o método findByUsuario,
		 * que foi assinado na interface UsuarioRepository
		 */
		
		Optional<Usuario> usuario = userRepository.findByUsuario(userName);
		
		/**
		 * Se o usuário não existir, o método lança uma Exception do tipo UsernameNotFoundException.
		 */ 
	  
		usuario.orElseThrow(() -> new UsernameNotFoundException(userName + " not found."));

		/* Retorna um objeto do tipo UserDetailsImpl criado com os dados recuperados do
		 * Banco de dados */

		return usuario.map(UserDetailsImpl::new).get();
	}
}