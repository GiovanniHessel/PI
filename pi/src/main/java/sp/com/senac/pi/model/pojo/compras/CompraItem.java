package sp.com.senac.pi.model.pojo.compras;

import sp.com.senac.pi.model.pojo.producao.Produto;

public class CompraItem {
	private int id;
	private Integer idCompra;
	private Produto produto;
	private float quantidadeItem;
	private float custoItem;
	private float totalItem;
	
	public CompraItem(int id, Integer idCompra, Produto produto, float quantidadeItem, float custoItem,
			float totalItem) {
		this.id = id;
		this.idCompra = idCompra;
		this.produto = produto;
		this.quantidadeItem = quantidadeItem;
		this.custoItem = custoItem;
		this.totalItem = totalItem;
	}
	
	public CompraItem() {
		this.id = 0;
		this.idCompra = null;
		this.produto = new Produto();
		this.quantidadeItem = 0;
		this.custoItem = 0;
		this.totalItem = 0;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getIdCompra() {
		return idCompra;
	}

	public void setIdCompra(Integer idCompra) {
		this.idCompra = idCompra;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public float getQuantidadeItem() {
		return quantidadeItem;
	}

	public void setQuantidadeItem(float quantidadeItem) {
		this.quantidadeItem = quantidadeItem;
	}

	public float getCustoItem() {
		return custoItem;
	}

	public void setCustoItem(float custoItem) {
		this.custoItem = custoItem;
	}

	public float getTotalItem() {
		return totalItem;
	}

	public void setTotalItem(float totalItem) {
		this.totalItem = totalItem;
	}
	
	
}
