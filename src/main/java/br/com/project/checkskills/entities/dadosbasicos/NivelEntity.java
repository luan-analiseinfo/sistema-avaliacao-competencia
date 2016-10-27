package br.com.project.checkskills.entities.dadosbasicos;

import javax.persistence.AttributeOverride;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.com.project.checkskills.utils.BaseEntity;

@Entity
@Table(name = "TB_NIVEL")
@AttributeOverride(name = "id", column = @Column(name = "ID_NIVEL"))
public class NivelEntity extends BaseEntity<Long> {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "NIVEL", length = 45, nullable = false)
	@NotNull(message="Campo obrigatório")
	private String nivel;

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}
	
	@Override
	public String toString() {
		return String.format("%s[id=%d]", getClass().getSimpleName(), getId());
	}

}
