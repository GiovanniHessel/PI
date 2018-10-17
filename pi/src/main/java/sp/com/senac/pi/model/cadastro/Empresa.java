package sp.com.senac.pi.model.cadastro;

import java.util.ArrayList;
import java.util.List;

public class Empresa {
    private int id;
    private String nomeFantasia;
    private String razaoSocial;
    private String cnpj;
    private String dataDeCriacao;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private Cidade cidade;
    private List<Contato> contatos;
    private int inativo;
    
    public Empresa() {
        this.id = 0;
        this.nomeFantasia = "";
        this.razaoSocial = "";
        this.cnpj = "";
        this.dataDeCriacao = "";
        this.logradouro = "";
		this.numero = "";
		this.complemento = "";
		this.bairro = "";
		this.cidade = new Cidade();
        this.contatos = new ArrayList<Contato>();;
        this.inativo = 0;
    }

	public Empresa(int id, String nomeFantasia, String razaoSocial, String cnpj, String dataDeCriacao,
			String logradouro, String numero, String complemento, String bairro, Cidade cidade, List<Contato> contatos,
			int inativo) {
		super();
		this.id = id;
		this.nomeFantasia = nomeFantasia;
		this.razaoSocial = razaoSocial;
		this.cnpj = cnpj;
		this.dataDeCriacao = dataDeCriacao;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cidade = cidade;
		this.contatos = contatos;
		this.inativo = inativo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getCNPJ() {
		return cnpj;
	}

	public void setCNPJ(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getDataDeCriacao() {
		return dataDeCriacao;
	}

	public void setDataDeCriacao(String dataDeCriacao) {
		this.dataDeCriacao = dataDeCriacao;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public List<Contato> getContatos() {
		return contatos;
	}

	public void setContatos(List<Contato> contatos) {
		this.contatos = contatos;
	}

	public int getInativo() {
		return inativo;
	}

	public void setInativo(int inativo) {
		this.inativo = inativo;
	}

    
}