package sp.com.senac.pi.model.pojo.compras;

import sp.com.senac.pi.model.pojo.base.Pessoa;

public class Comprador extends Pessoa{
	private int id;
	private float limiteCredito;
	private int inativo;
	
	public Comprador(int id, float limiteCredito, int inativo) {
		this.id = id;
		this.limiteCredito = limiteCredito;
		this.inativo = inativo;
	}
	
	public Comprador() {
		this.id = 0;
		this.limiteCredito = 0;
		this.inativo = 0;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getLimiteCredito() {
		return limiteCredito;
	}

	public void setLimiteCredito(float limiteCredito) {
		this.limiteCredito = limiteCredito;
	}

	public int getInativo() {
		return inativo;
	}

	public void setInativo(int inativo) {
		this.inativo = inativo;
	}
	
	
}
