package sp.com.senac.pi.model.pojo.localizacao;

public class Pais {
	private int id;
    private String pais;
    private String sigla;
    private int inativo;

    public Pais() {
        this.id = 0;
        this.pais = "";
        this.sigla = "";
        this.inativo = 0;
    }

    public Pais(int id, String pais, String sigla, int inativo) {
        this.id = id;
        this.pais = pais;
        this.sigla = sigla;
        this.inativo = inativo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public int getInativo() {
        return inativo;
    }

    public void setInativo(int inativo) {
        this.inativo = inativo;
    }
}
