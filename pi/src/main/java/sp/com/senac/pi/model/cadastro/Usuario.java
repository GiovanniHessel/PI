package sp.com.senac.pi.model.cadastro;

public class Usuario {
	private int id;
	private Pessoa pessoa;
	private String login;
	private String chave;
	private int inativo;
	
	public Usuario() {
		this.id = 0;
		this.pessoa = new Pessoa();
		this.login = "";
		this.chave = "";
		this.inativo = 0;
	}
	
	public Usuario(int id, Pessoa pessoa, String login, String chave, int inativo) {
		this.id = id;
		this.pessoa = pessoa;
		this.login = login;
		this.chave = chave;
		this.inativo = inativo;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getChave() {
		return chave;
	}
	public void setChave(String chave) {
		this.chave = chave;
	}
	public int getInativo() {
		return inativo;
	}
	public void setInativo(int inativo) {
		this.inativo = inativo;
	}
	
	
	
}
