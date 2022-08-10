package acc.br.accenture.blogpessoal.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity //Entity informa que essa classe irá gerar uma tabela
@Table(name = "tb_postagens") //Criar tabela e o nome- equivalente ao create table
public class Postagem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Autoincremento
	private long id;
	
	@NotNull(message = "O atributo título é obrigatório!")
	@Size(min = 5, max = 100, message = "O atributo titulo deve conter no mínimo 5 e no máximo 100 caracteres.")
	private String titulo; //equivalente ao titulo varchar
	
	@NotNull(message = "O atributo texto é obrigatório!")
	@Size(min = 10, max = 1000, message = "O atributo texto deve conter no mínimo 510e no máximo 1000 caracteres.")
	private String texto;
	
	@Temporal(TemporalType.TIMESTAMP) //configurando a data para ser timestamp - equivalente a data timestamp
	private Date data = new java.sql.Date(System.currentTimeMillis());
	
	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private Tema tema;
	
	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private Usuario usuario;
	
	//Métodos Get e Set
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Tema getTema() {
		return tema;
	}
	public void setTema(Tema tema) {
		this.tema = tema;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	

}
