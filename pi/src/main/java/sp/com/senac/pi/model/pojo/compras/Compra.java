package sp.com.senac.pi.model.pojo.compras;

import java.util.ArrayList;
import java.util.List;

public class Compra {
	private int id;
	private List<CompraItem> compraItem;
	private String dataDaCompra;
	private Comprador comprador;
	private Fornecedor fornecedor;
	private boolean recebido;
	
	public Compra(int id, List<CompraItem> compraItem, String dataDaCompra, Comprador comprador, Fornecedor fornecedor,
			boolean recebido) {
		this.id = id;
		this.compraItem = compraItem;
		this.dataDaCompra = dataDaCompra;
		this.comprador = comprador;
		this.fornecedor = fornecedor;
		this.recebido = recebido;
	}
	
	public Compra() {
		this.id = 0;
		this.compraItem = new ArrayList<CompraItem>();
		this.dataDaCompra = "";
		this.comprador = new Comprador();
		this.fornecedor = new Fornecedor();
		this.recebido = false;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<CompraItem> getCompraItem() {
		return compraItem;
	}

	public void setCompraItem(List<CompraItem> compraItem) {
		this.compraItem = compraItem;
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
