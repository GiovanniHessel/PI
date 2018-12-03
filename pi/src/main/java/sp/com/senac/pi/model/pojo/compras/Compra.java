package sp.com.senac.pi.model.pojo.compras;

import java.util.ArrayList;
import java.util.List;

public class Compra {
	private Integer id;
	private List<CompraItem> compraItens;
	private String dataDaCompra;
	private Comprador comprador;
	private Fornecedor fornecedor;
	private Boolean recebido;
	
	public Compra(Integer id, List<CompraItem> compraItens, String dataDaCompra, Comprador comprador, Fornecedor fornecedor,
			boolean recebido) {
		this.id = id;
		this.compraItens = compraItens;
		this.dataDaCompra = dataDaCompra;
		this.comprador = comprador;
		this.fornecedor = fornecedor;
		this.recebido = recebido;
	}
	
	public Compra() {
		this.id = 0;
		this.compraItens = new ArrayList<CompraItem>();
		this.dataDaCompra = "";
		this.comprador = new Comprador();
		this.fornecedor = new Fornecedor();
		this.recebido = false;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<CompraItem> getCompraItens() {
		return compraItens;
	}

	public void setCompraItens(List<CompraItem> compraItens) {
		this.compraItens = compraItens;
	}

	public String getDataDaCompra() {
		return dataDaCompra;
	}

	public void setDataDaCompra(String dataDaCompra) {
		this.dataDaCompra = dataDaCompra;
	}

	public Comprador getComprador() {
		return comprador;
	}

	public void setComprador(Comprador comprador) {
		this.comprador = comprador;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public boolean isRecebido() {
		return recebido;
	}

	public void setRecebido(boolean recebido) {
		this.recebido = recebido;
	}
	
	
	
	
}
