package acc.br.accenture.blogpessoal.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/* Esta classe é responsável por habilitar a segurança básica da aplicação e o login
 * na aplicação. */

@EnableWebSecurity
public class BasicSecurityConfig extends WebSecurityConfigurerAdapter{
	
	//Injeção de dependências
	@Autowired
	private UserDetailsService userDetailsService;
	
/* criando uma nova instância da Classe AuthenticationManagerBuilder e 
	 *  define que o login será efetuado através dos usuários criados no Banco de dados.*/
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		 auth.userDetailsService(userDetailsService);

		 auth.inMemoryAuthentication()
			.withUser("root")
			.password(passwordEncoder().encode("root"))
			.authorities("ROLE_USER");

}
	
	
/*transformando a instância retornada pelo método como um 
*  objeto gerenciado pelo Spring*/
		
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
		
//Configurando a Segurança Web
		
		@Override
		protected void configure(HttpSecurity http) throws Exception{
			http.authorizeRequests()
			.antMatchers("/usuarios/logar").permitAll()
			.antMatchers("/usuarios/cadastrar").permitAll()
			.antMatchers(HttpMethod.OPTIONS).permitAll()
			.anyRequest().authenticated()
			.and().httpBasic()
			.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and().cors()
			.and().csrf().disable();
		}
		
		
	}


