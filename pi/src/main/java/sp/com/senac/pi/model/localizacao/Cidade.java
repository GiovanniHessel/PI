package sp.com.senac.pi.model.localizacao;

public class Cidade {
	private int id;
    private String cidade;
    private String sigla;    
    private Estado estado;
    private int inativo;

    public Cidade() {
        this.id = 0;
        this.cidade = "";
        this.sigla = "";
        this.estado = new Estado();
        this.inativo = 0;
    }
    
    public Cidade(int id, String cidade, String sigla, Estado estado, int inativo) {
        this.id = id;
        this.cidade = cidade;
        this.sigla = sigla;
        this.estado = estado;
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

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }


    public int getInativo() {
        return inativo;
    }

    public void setInativo(int inativo) {
        this.inativo = inativo;
    }
}
