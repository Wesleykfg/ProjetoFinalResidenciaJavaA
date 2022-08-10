package acc.br.accenture.blogpessoal.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "tb_extrato")
public class Extrato {
	//Gerando Chave Primaria
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	@CreationTimestamp
	private LocalDateTime dataHoraMovimento;
	
	@Column(nullable = false)
	@NotNull(message = "ATEN��O!!! O tipo de opera��o � obrigat�rio!!!")
	@Size(min = 5, max = 13, message = "ATEN��O!!! O atributo Opera��o Corrente deve conter no m�nimo 5 e no m�ximo 13 caracteres.")
	private String operacao;
	
	//Rhaan- Adi��o ValorOpera��o
	@Column(nullable = false, columnDefinition = "DECIMAL(11,2)")
	@NotNull(message = "ATEN��O!!! O valor da Opera��o � obrigat�ria")
	private double valorOperacao;
	
	@ManyToOne
	@JoinColumn(name = "id_contaCorrente")
	private ContaCorrente contacorrente;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDataHoraMovimento() {
		return dataHoraMovimento;
	}

	public void setDataHoraMovimento(LocalDateTime dataHoraMovimento) {
		this.dataHoraMovimento = dataHoraMovimento;
	}

	public ContaCorrente getContacorrente() {
		return contacorrente;
	}

	public void setContacorrente(ContaCorrente contacorrente) {
		this.contacorrente = contacorrente;
	}

	public String getOperacao() {
		return operacao;
	}

	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}
	
	//Rhaan- Adi��o ValorOpera��o
	public double getValorOperacao() {
		return valorOperacao;
	}
	//Rhaan- Adi��o ValorOpera��o
	public void setValorOperacao(double valorOperacao) {
		if(valorOperacao>0) {
			this.valorOperacao = valorOperacao;
		}
	}

	public ContaCorrente getContaCorrente() {
		return contacorrente;
	}

	public void setContaCorrente(ContaCorrente contaCorrente) {
		this.contacorrente = contaCorrente;
	}
	
	

}
