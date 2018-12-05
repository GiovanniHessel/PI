package sp.com.senac.pi.model.pojo.producao;

public class Custo {
	private Integer id;
	private Float custo;
	private String dataCusto; 
	private Integer idProduto;
	
	public Custo(Integer id, Float custo, String dataCusto, Integer idProduto) {
		this.id = id;
		this.custo = custo;
		this.dataCusto = dataCusto;
		this.idProduto = idProduto;
	}
	
	public Custo() {
		this.id = 0;
		this.custo = (float) 0;
		this.dataCusto = "";
		this.idProduto = 0;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Float getCusto() {
		return custo;
	}

	public void setCusto(Float custo) {
		this.custo = custo;
	}

	public String getDataCusto() {
		return dataCusto;
	}

	public void setDataCusto(String dataCusto) {
		this.dataCusto = dataCusto;
	}

	public Integer getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}
	
	
}
