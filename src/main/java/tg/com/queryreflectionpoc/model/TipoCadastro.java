package tg.com.queryreflectionpoc.model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "TIPO_CADASTRO")
public class TipoCadastro {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idTipoCadastro;

	private String nmTipoCadastro;

	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone = "GMT-3")
	private Calendar dtCadastro;

	public TipoCadastro() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idTipoCadastro == null) ? 0 : idTipoCadastro.hashCode());
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
		TipoCadastro other = (TipoCadastro) obj;
		if (idTipoCadastro == null) {
			if (other.idTipoCadastro != null)
				return false;
		} else if (!idTipoCadastro.equals(other.idTipoCadastro))
			return false;
		return true;
	}

	public Integer getIdTipoCadastro() {
		return idTipoCadastro;
	}

	public void setIdTipoCadastro(Integer idTipoCadastro) {
		this.idTipoCadastro = idTipoCadastro;
	}

	public String getNmTipoCadastro() {
		return nmTipoCadastro;
	}

	public void setNmTipoCadastro(String nmTipoCadastro) {
		this.nmTipoCadastro = nmTipoCadastro;
	}

	public Calendar getDtCadastro() {
		return dtCadastro;
	}

	public void setDtCadastro(Calendar dtCadastro) {
		this.dtCadastro = dtCadastro;
	}

}
