package acc.br.accenture.blogpessoal.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import acc.br.accenture.blogpessoal.helpEnum.ContaCorrenteHelpEnum;

@Entity
@Table(name = "tb_contaCorrente")
public class ContaCorrente {
	//Gerando Chave Primaria
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private long id;
		
		//Criando numero da agencia
		@Column(nullable = false)
		@NotNull(message = "ATEN��O!!! A agencia � obrigat�ria!!!")
		@Size(min = 3, max = 3, message = "O atributo Agencia deve conter 3 caracteres.")
		private String contaCorrenteAgencia;
		
		//Criando numero da conta
		@Column(nullable = false)
		@NotNull(message = "ATEN��O!!! A conta � obrigat�ria!!!")
		@Size(min = 8, max = 45, message = "O atributo Conta Corrente deve conter no m�nimo 8 e no m�ximo 45 caracteres.")
		private String contaCorrenteNumero;
		
		//Rhaan - adi��o de atributo
		@Column(columnDefinition = "DECIMAL(13,2) DEFAULT 0.00")
		@NotNull(message = "ATEN��O!!! O saldo � obrigat�rio")
		private double saldo;
		
		//Rhaan - Adi��o para ENUM
		private String statusConta;
		
		
		/*@OneToOne
		@JsonIgnoreProperties("contacorrente")
		private Cliente cliente;*/
		
		@OneToMany(mappedBy = "contacorrente", cascade = CascadeType.REMOVE)
		@JsonIgnoreProperties("contacorrente")
		private List<Extrato> extrato;
				
		@ManyToOne
		@JoinColumn(name = "id_cliente", nullable = false)
		private Cliente cliente;

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getContaCorrenteAgencia() {
			return contaCorrenteAgencia;
		}

		public void setContaCorrenteAgencia(String contaCorrenteAgencia) {
			this.contaCorrenteAgencia = contaCorrenteAgencia;
		}

		public String getContaCorrenteNumero() {
			return contaCorrenteNumero;
		}

		public void setContaCorrenteNumero(String contaCorrenteNumero) {
			this.contaCorrenteNumero = contaCorrenteNumero;
		}
		
		//Rhaan - adi��o - Saldo
		public double getSaldo() {
			return saldo;
		}
		//Rhaan - adi��o Saldo.
		public void setSaldo(double saldo) {
			this.saldo = saldo;
		}
		
		//Rhaan - Adi��o ENUM
				public String getStatusConta() {
					return statusConta;
				}
				//Rhaan - Adi��o ENUM
				public void setStatusConta() {
					if(getSaldo()>=0) {
						this.statusConta = ContaCorrenteHelpEnum.ADIMPLENTE.toString();
					}else {
						this.statusConta = ContaCorrenteHelpEnum.INADIMPLENTE.toString();
					}			
				}

		public Cliente getCliente() {
			return cliente;
		}

		public void setCliente(Cliente cliente) {
			this.cliente = cliente;
		}

		/*public List<Extrato> getExtrato() {
			return extrato;
		}

		public void setExtrato(List<Extrato> extrato) {
			this.extrato = extrato;
		}*/

		
		
		
		

}

