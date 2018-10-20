package sp.com.senac.pi.model.cadastro;

public class Cidade {
	private int id;
    private String cidade;
    private String sigla;    
    private Estado estado;
    private Pais pais;
    private int inativo;

    public Cidade() {
        this.id = 0;
        this.cidade = "";
        this.sigla = "";
        this.estado = new Estado();
        this.pais = new Pais();
        this.inativo = 0;
    }
    
    public Cidade(int id) {
        this.id = id;
        this.cidade = "";
        this.sigla = "";
        this.estado = new Estado();
        this.pais = new Pais();
        this.inativo = 0;
    }
    
    public Cidade(int id, String cidade, String sigla, Estado estado, Pais pais, int inativo) {
        this.id = id;
        this.cidade = cidade;
        this.sigla = sigla;
        this.estado = estado;
        this.pais = pais;
        this.inativo = inativo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public Estado getEstados() {
        return estado;
    }

    public void setEstados(Estado estado) {
        this.estado = estado;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public int getInativo() {
        return inativo;
    }

    public void setInativo(int inativo) {
        this.inativo = inativo;
    }
}
