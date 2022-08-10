package acc.br.accenture.blogpessoal.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;

@Entity //Entity informa que essa classe irá gerar uma tabela
@Table(name = "tb_usuario") //Criar tabela e o nome - equivalente ao create table
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Autoincremento
	private long id;
	
	@NotNull(message = "O atributo nome completo é obrigatório!")
	private String nome; // nome do usuario varchar
	
	@ApiModelProperty(example = "email@email.com.br")
	@Email
	@NotNull(message = "O atributo usuario é obrigatório!")
	private String usuario; // email varchar
	
	@NotBlank(message = "O atributo senha é obrigatório!")
	@Size(min = 8, message = "O atributo senha deve conter no mínimo 6 e no máximo 255 caracteres.")
	private String senha; // senha varchar
	
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("usuario")
	private List<Postagem> postagem;
	
	
	//Costrutor para a partede testes
	public Usuario(long id, String nome, String usuario, String senha) {
		this.id = id;
		this.nome = nome;
		this.usuario = usuario;
		this.senha = senha;
	}
	
	
	//Construtor vazio (Para objetos Null)
	public Usuario() {
	}



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nomeCompleto) {
		this.nome = nomeCompleto;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Postagem> getPostagem() {
		return postagem;
	}

	public void setPostagem(List<Postagem> postagem) {
		this.postagem = postagem;
	}
	
	
	
}