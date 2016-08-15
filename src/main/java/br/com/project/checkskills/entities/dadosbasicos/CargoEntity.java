package br.com.project.checkskills.entities.dadosbasicos;


import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.project.checkskills.utils.BaseEntity;

@Entity
@Table(name = "TB_CARGO")
@AttributeOverride(name = "id", column = @Column(name = "ID_CARGO"))
public class CargoEntity extends BaseEntity<Long> {

	private static final long serialVersionUID = 1L;

	@Column(name = "nome_cargo", length = 45, nullable = false)
	private String nomeCargo;

	@Column(length = 255, nullable = true)
	private String descricao;

	@JoinColumn(name = "ID_NIVEL", referencedColumnName = "ID_NIVEL", nullable = false)
	@ManyToOne(optional = false)
	private NivelEntity nivelEntity;

	//@OneToOne(mappedBy ="cargoModelo")
	//private cargoModeloEntity cargomodeloEntity;
	
	public String getNomeCargo() {
		return nomeCargo;
	}

	public void setNomeCargo(String nomeCargo) {
		this.nomeCargo = nomeCargo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	

	public NivelEntity getNivelEntity() {
		return nivelEntity;
	}

	public void setNivelEntity(NivelEntity nivelEntity) {
		this.nivelEntity = nivelEntity;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

//	public cargoModeloEntity getCargomodeloEntity() {
	//	return cargomodeloEntity;
	//}

	//public void setCargomodeloEntity(cargoModeloEntity cargomodeloEntity) {
		//this.cargomodeloEntity = cargomodeloEntity;
	//}
	
	
	

}