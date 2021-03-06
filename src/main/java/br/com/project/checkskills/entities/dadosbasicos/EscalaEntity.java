package br.com.project.checkskills.entities.dadosbasicos;

import javax.persistence.AttributeOverride;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import br.com.project.checkskills.utils.BaseEntity;


@Entity
@Table(name = "TB_ESCALA")
@AttributeOverride(name = "id", column = @Column(name = "ID_ESCALA"))
@Component(value="escalaEntity")
public class EscalaEntity extends BaseEntity<Long>{


	private static final long serialVersionUID = 1L;

	  	@Basic(optional = false)
	    @Column(name = "NOME",nullable=false)
	    private String nome;
	    
	  	@Basic(optional = false)
	    @Column(name = "VALOR",nullable=false)
	    private int valor;
	    

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public int getValor() {
			return valor;
		}

		public void setValor(int valor) {
			this.valor = valor;
		}


		@Override
		public String toString() {
			return String.format("%s[id=%d]", getClass().getSimpleName(), getId());
		}
	
}
