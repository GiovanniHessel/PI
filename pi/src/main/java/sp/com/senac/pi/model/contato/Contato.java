package sp.com.senac.pi.model.contato;

public class Contato {
	private int id;
    private String ddd;
    private String numero;
    private String tipoNumero;
    private String email;
    private String site;
    private int idPessoa;
    private int idEmpresa;
    private int inativo;

    public Contato() {
        this.id = 0;
        this.ddd = "";
        this.numero = "";
        this.tipoNumero = "";
        this.email = "";
        this.site = "";
        this.idPessoa = 0;
        this.idEmpresa = 0;
        this.inativo = 0;
    }

    public Contato(int id, String ddd, String numero, String tipoNumero, String email, String site, int idPessoa, int idEmpresa, int inativo) {
        this.id = id;
        this.ddd = ddd;
        this.numero = numero;
        this.tipoNumero = tipoNumero;
        this.email = email;
        this.site = site;
        this.idPessoa = idPessoa;
        this.idEmpresa = idEmpresa;
        this.inativo = inativo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTipoNumero() {
        return tipoNumero;
    }

    public void setTipoNumero(String tipoNumero) {
        this.tipoNumero = tipoNumero;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }
    

    public int getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(int idPessoa) {
		this.idPessoa = idPessoa;
	}

	public int getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public int getInativo() {
        return inativo;
    }

    public void setInativo(int inativo) {
        this.inativo = inativo;
    }
}