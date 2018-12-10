package sp.com.senac.pi.model.pojo.compras;

import sp.com.senac.pi.model.pojo.base.Pessoa;

public class Comprador extends Pessoa{
	private int id;
	private float limiteDeCredito;
	private int inativo;
	
	public Comprador(int id, float limiteDeCredito, int inativo) {
		this.id = id;
		this.limiteDeCredito = limiteDeCredito;
		this.inativo = inativo;
	}
	
	public Comprador() {
		this.id = 0;
		this.limiteDeCredito = 0;
		this.inativo = 0;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getLimiteDeCredito() {
		return limiteDeCredito;
	}

	public void setLimiteDeCredito(float limiteDeCredito) {
		this.limiteDeCredito = limiteDeCredito;
	}

	public int getInativo() {
		return inativo;
	}

	public void setInativo(int inativo) {
		this.inativo = inativo;
	}
	
	
}
