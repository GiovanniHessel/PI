package sp.com.senac.pi.model.pojo.compras;

import sp.com.senac.pi.model.pojo.base.Empresa;

public class Fornecedor extends Empresa {
	
	private int id;
	private String formatoJuridico;
	private String regimeTributario;
	private int inativo;
	
	public Fornecedor(int id, String formatoJuridico, String regimeTributario, int inativo) {
		this.id = id;
		this.formatoJuridico = formatoJuridico;
		this.regimeTributario = regimeTributario;
		this.inativo = inativo;
	}
	
	public Fornecedor() {
		this.id = 0;
		this.formatoJuridico = "";
		this.regimeTributario = "";
		this.inativo = 0;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFormatoJuridico() {
		return formatoJuridico;
	}

	public void setFormatoJuridico(String formatoJuridico) {
		this.formatoJuridico = formatoJuridico;
	}

	public String getRegimeTributario() {
		return regimeTributario;
	}

	public void setRegimeTributario(String regimeTributario) {
		this.regimeTributario = regimeTributario;
	}

	public int getInativo() {
		return inativo;
	}

	public void setInativo(int inativo) {
		this.inativo = inativo;
	}
	
	
	
}
