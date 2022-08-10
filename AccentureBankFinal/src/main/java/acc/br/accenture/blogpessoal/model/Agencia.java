package acc.br.accenture.blogpessoal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tb_agencia")
public class Agencia {
	//Gerando Chave Primaria
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	//Criando Nome para a agencia
	@Column(nullable = false)
	@NotNull(message = "ATEN��O!!! O nome � obrigat�rio!!!")
	@Size(min = 5, max = 45, message = "ATEN��O!!! O atributo nome deve conter no m�nimo 5 e no m�ximo 45 caracteres.")
	private String nome;
	
	//Criando endere�o para a Agencia
	@Column(nullable = false)
	@NotNull(message = "ATEN��O!!! O endere�o � obrigat�rio!!!")
	@Size(min = 5, max = 45, message = "ATEN��O!!! O atributo endere�o deve conter no m�nimo 5 e no m�ximo 45 caracteres.")
	private String endereco;
	
	//Criando Telefone para a Agencia
	@Column(nullable = false)
	@NotBlank(message = "ATEN��O!!! O atributo Fone � obrigat�rio!!!")
	@Size(min = 5, max = 45, message = "ATEN��O!!! O atributo telefone deve conter no m�nimo 5 e no m�ximo 45 caracteres.")
	private String fone;
	
	/*@OneToMany(mappedBy = "agencia", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("agencia")
	private List<Cliente> cliente;*/
	
	/*@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;*/
	
	//Costrutor para a parte de testes
		public Agencia(long id, String nome, String endereco, String fone) {
			this.id = id;
			this.nome = nome;
			this.endereco = endereco;
			this.fone = fone;
		}
		
		
		//Construtor vazio (Para objetos Null)
		public Agencia() {
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

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getFone() {
		return fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
	}


	/*public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}*/

	/*public List<Cliente> getCliente() {
		return cliente;
	}

	public void setCliente(List<Cliente> cliente) {
		this.cliente = cliente;
	}*/
	
	

	
	
	
	
}
