package br.com.project.checkskills.entities.dadosbasicos;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.stereotype.Component;

import br.com.project.checkskills.entities.autenticacao.UsuarioEntity;
import br.com.project.checkskills.entities.avaliacao.AvaliacaoEntity;
import br.com.project.checkskills.utils.BaseEntity;

@Entity
@Table(name = "TB_FUNCIONARIO")
@AttributeOverride(name = "id", column = @Column(name = "ID_FUNCIONARIO"))
@Component(value = "funcionarioEntity")
public class FuncionarioEntity extends BaseEntity<Long> {

	private static final long serialVersionUID = 1L;

	@Column(name = "NOME", nullable = false, length = 75)
	@NotNull(message = "Campo obrigatório")
	private String nome;

	@Column(name = "SEXO", length = 1, nullable = false)
	@NotNull(message = "Campo obrigatório")
	private String sexo;

	@Past(message="Dados inválidos")
	@NotNull(message="Campo obrigatório")
	@Column(name = "DATA_NASCIMENTO", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataNasc;
	
	@Past(message="Dados inválidos")
	@NotNull(message="Campo obrigatório")
	@Column(name = "DATA_ADMISSAO", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAdmissao;

    @Future(message="Dados inválidos")
	@Column(name = "DATA_DEMISSAO")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataDemissao;
	
	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REMOVE }, optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_FUNCIONARIO")
	private UsuarioEntity usuarioEntity;

	@ManyToOne(cascade = { CascadeType.MERGE }, optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_CARGO")
	private CargoEntity cargo;

	@OneToOne(mappedBy = "funcionario")
	private AvaliacaoEntity avaliacao;

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public CargoEntity getCargo() {
		return cargo;
	}

	public AvaliacaoEntity getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(AvaliacaoEntity avaliacao) {
		this.avaliacao = avaliacao;
	}

	public void setCargo(CargoEntity cargo) {
		this.cargo = cargo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


	
	public Date getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}


	
	public UsuarioEntity getUsuarioEntity() {
		return usuarioEntity;
	}

	public void setUsuarioEntity(UsuarioEntity usuarioEntity) {
		this.usuarioEntity = usuarioEntity;
	}
	
	

	public Date getDataAdmissao() {
		return dataAdmissao;
	}

	public void setDataAdmissao(Date dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}

	public Date getDataDemissao() {
		return dataDemissao;
	}

	public void setDataDemissao(Date dataDemissao) {
		this.dataDemissao = dataDemissao;
	}
	

	@Override
	public String toString() {
		return String.format("%s[id=%d]", getClass().getSimpleName(), getId());
	}

}
