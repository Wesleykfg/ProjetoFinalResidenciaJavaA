package acc.br.accenture.blogpessoal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tb_cliente")
public class Cliente {
	
	//Gerando Chave Primaria
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		//Criando Nome para o Cliente
		@Column(nullable = false)
		@NotBlank(message = "ATEN��O!!!O atributo Nome � obrigat�rio!!!")
		@Size(min = 5, max = 45, message = "O atributo nome deve conter no m�nimo 5 e no m�ximo 45 caracteres.")
		private String nome;
		
		//Criando CPF para o Cliente
		@Column(nullable = false, unique=true)
		private String cpf;
		
		//Criando Telefone para o Cliente
		@Column(nullable = false)
		@NotBlank(message = "ATEN��O!!! O atributo Fone � obrigat�rio!!!")
		@Size(min = 11, max = 20, message = "O atributo Fone deve conter no m�nimo 11 e no m�ximo 20 caracteres.")
		private String fone;
		
		@ManyToOne
		@JoinColumn(name = "id_agencia", nullable = false)
		private Agencia agencia;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public String getCpf() {
			return cpf;
		}

		public void setCpf(String cpf) {
			this.cpf = cpf;
		}

		public String getFone() {
			return fone;
		}

		public void setFone(String fone) {
			this.fone = fone;
		}

		public Agencia getAgencia() {
			return agencia;
		}

		public void setAgencia(Agencia agencia) {
			this.agencia = agencia;
		}
		
		

		
		
		
		
}

