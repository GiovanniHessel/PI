package sp.com.senac.pi.model.localizacao;

public class Estado {
	private int id;
	private String estado;
	private String sigla;
	private Pais pais;
	private int inativo;
	
	public Estado() {
	    this.id = 0;
	    this.estado = "";
	    this.sigla = "";
	    this.pais = new Pais();
	    this.inativo = 0;
    }

    public Estado(int id, String estado, String sigla, Pais pais, int inativo) {
        this.id = id;
        this.estado = estado;
        this.sigla = sigla;
        this.pais = pais;
        this.inativo = inativo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
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
