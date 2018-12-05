package sp.com.senac.pi.model.pojo.producao;

public class Produto {
	private int id; 
	private String nome;
	private String descricao;
	private String ean;
	private String marca;
	private float peso;
	private float largura;
	private float altura;
	private float comprimento;
	private Preco preco;
	private Custo custo;
	private Estoque estoque;
	private int inativo;
	
	public Produto(int id, String nome, String descricao, String ean, String marca, float peso, float largura, float altura,
			float comprimento, Preco preco, Custo custo, Estoque  estoque, int inativo) {
		
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.ean = ean;
		this.marca = marca;
		this.peso = peso;
		this.largura = largura;
		this.altura = altura;
		this.comprimento = comprimento;
		this.preco = preco;
		this.custo = custo;
		this.estoque = estoque;
		this.inativo = inativo;
	}
	
	public Produto() {
		
		this.id = 0;
		this.nome = "";
		this.descricao = "";
		this.ean = "";
		this.marca = "";
		this.peso = 0;
		this.largura = 0;
		this.altura = 0;
		this.comprimento = 0;
		this.preco = new Preco();
		this.custo =  new Custo();
		this.estoque =  new Estoque();
		this.inativo = 0;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getEan() {
		return ean;
	}

	public void setEan(String ean) {
		this.ean = ean;
	}
	

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public float getPeso() {
		return peso;
	}

	public void setPeso(float peso) {
		this.peso = peso;
	}

	public float getLargura() {
		return largura;
	}

	public void setLargura(float largura) {
		this.largura = largura;
	}

	public float getAltura() {
		return altura;
	}

	public void setAltura(float altura) {
		this.altura = altura;
	}

	public float getComprimento() {
		return comprimento;
	}

	public void setComprimento(float comprimento) {
		this.comprimento = comprimento;
	}

	public Preco getPreco() {
		return preco;
	}

	public void setPreco(Preco preco) {
		this.preco = preco;
	}

	public Custo getCusto() {
		return custo;
	}

	public void setCusto(Custo custo) {
		this.custo = custo;
	}

	public Estoque getEstoque() {
		return estoque;
	}

	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
	}

	public int getInativo() {
		return inativo;
	}

	public void setInativo(int inativo) {
		this.inativo = inativo;
	}
	
	
	
}
