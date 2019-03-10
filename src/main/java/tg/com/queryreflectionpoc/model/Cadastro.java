package tg.com.queryreflectionpoc.model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "CADASTRO")
public class Cadastro {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idCadastro;

	private String nmCadastro;

	private String cdFuncao;

	@ManyToOne(optional = true)
	@JoinColumn(name = "ID_TIPO_CADASTRO")
	private TipoCadastro tipoCadastro;

	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone = "GMT-3")
	private Calendar dtCadastro;

	public Cadastro() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idCadastro == null) ? 0 : idCadastro.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cadastro other = (Cadastro) obj;
		if (idCadastro == null) {
			if (other.idCadastro != null)
				return false;
		} else if (!idCadastro.equals(other.idCadastro))
			return false;
		return true;
	}

	public Integer getIdCadastro() {
		return idCadastro;
	}

	public void setIdCadastro(Integer idCadastro) {
		this.idCadastro = idCadastro;
	}

	public String getNmCadastro() {
		return nmCadastro;
	}

	public void setNmCadastro(String nmCadastro) {
		this.nmCadastro = nmCadastro;
	}

	public String getCdFuncao() {
		return cdFuncao;
	}

	public void setCdFuncao(String cdFuncao) {
		this.cdFuncao = cdFuncao;
	}

	public Calendar getDtCadastro() {
		return dtCadastro;
	}

	public void setDtCadastro(Calendar dtCadastro) {
		this.dtCadastro = dtCadastro;
	}

}
