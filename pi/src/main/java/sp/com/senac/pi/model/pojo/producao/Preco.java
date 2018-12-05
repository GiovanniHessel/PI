package sp.com.senac.pi.model.pojo.producao;

public class Preco {
	private Integer id; 
	private Float preco; 
	private String dataPreco;
	private Integer idProduto;
	
	public Preco(Integer id, Float preco, String dataPreco, Integer idProduto) {
		this.id = id;
		this.preco = preco;
		this.dataPreco = dataPreco;
		this.idProduto = idProduto;
	}
	
	public Preco() {
		this.id = 0;
		this.preco = (float) 0;
		this.dataPreco = "";
		this.idProduto = 0;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Float getPreco() {
		return preco;
	}

	public void setPreco(Float preco) {
		this.preco = preco;
	}

	public String getDataPreco() {
		return dataPreco;
	}

	public void setDataPreco(String dataPreco) {
		this.dataPreco = dataPreco;
	}

	public Integer getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}
	
	
}
