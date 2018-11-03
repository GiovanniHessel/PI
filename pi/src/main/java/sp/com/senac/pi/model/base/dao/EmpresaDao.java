package sp.com.senac.pi.model.base.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sp.com.senac.pi.conexao.contratos.DbConnection;
import sp.com.senac.pi.conexao.singleton.ConnectionSingleton;
import sp.com.senac.pi.model.base.Empresa;
import sp.com.senac.pi.model.localizacao.Cidade;
import sp.com.senac.pi.model.localizacao.Estado;
import sp.com.senac.pi.model.localizacao.Pais;

public class EmpresaDao {
	private DbConnection connection;

	public EmpresaDao() {
		this.connection = ConnectionSingleton.getConnection();
	}

	public Empresa insert(Empresa empresa) {
		empresa.setId(0);

		String sql = "exec SPIU_EMPRESA ?,?,?,?,?,?,?,?,?,?,?";

		this.connection.open();
		try {
	
			PreparedStatement stmt = connection.getConnection().prepareStatement(sql);

			stmt.setInt(1, empresa.getId());
			stmt.setString(2, empresa.getNomeFantasia());
			stmt.setString(3, empresa.getRazaoSocial());
			stmt.setString(4, empresa.getCNPJ());
			stmt.setString(5, empresa.getDataDeCriacao());
			stmt.setString(6, empresa.getLogradouro());
			stmt.setString(7, empresa.getNumero());
			stmt.setString(8, empresa.getComplemento());
			stmt.setString(9, empresa.getBairro());
			stmt.setInt(10, empresa.getCidade().getId());
			stmt.setInt(11, empresa.getInativo());

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				empresa.setId(rs.getInt("id"));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			connection.close();
			return empresa;
		}
		connection.close();
		return empresa;
	}

	public Empresa getEmpresa(int id) {
		this.connection.open();
		try {
			PreparedStatement stmt = this.connection.getConnection().prepareStatement("select * from vwEmpresa where id = ?");
			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();
			Empresa empresa = new Empresa();

			if (rs.next()) {
				empresa = this.carregaEmpresa(rs);
			}
			rs.close();
			stmt.close();
			connection.close();
			return empresa;

		} catch (SQLException e) {
			connection.close();
			throw new RuntimeException(e);
		}
	}

	public Empresa getEmpresa(String cnpj) {
		this.connection.open();
		try {
			PreparedStatement stmt = this.connection.getConnection().prepareStatement("select * from vwEmpresa where CNPJ = ?");
			stmt.setString(1, cnpj);

			ResultSet rs = stmt.executeQuery();
			Empresa empresa = new Empresa();
			
			if (rs.next()) {
				empresa = this.carregaEmpresa(rs);
			}
			rs.close();
			stmt.close();
			connection.close();
			return empresa;

		} catch (SQLException e) {
			connection.close();
			throw new RuntimeException(e);
		}
	}

	public List<Empresa> getEmpresas() {
		this.connection.open();
		try {
			PreparedStatement stmt = this.connection.getConnection().prepareStatement("select * from vwEmpresa");

			ResultSet rs = stmt.executeQuery();
			List<Empresa> empresas = new ArrayList<Empresa>();
			
			while (rs.next()) {
				Empresa empresa = this.carregaEmpresa(rs);
				empresas.add(empresa);
			}
			rs.close();
			stmt.close();
			connection.close();
			return empresas;
		} catch (SQLException e) {
			connection.close();
			throw new RuntimeException(e);
		}
	}

	private Empresa carregaEmpresa(ResultSet rs)  {
		Empresa empresa = new Empresa();
		Cidade cidade = new Cidade();
		Estado estado = new Estado();
    	Pais pais = new Pais();
    	
		try {
			empresa.setId(rs.getInt("id"));
			empresa.setNomeFantasia(rs.getString("nomeFantasia"));
			empresa.setRazaoSocial(rs.getString("razaoSocial"));
			empresa.setCNPJ(rs.getString("CNPJ"));
			empresa.setDataDeCriacao(rs.getString("dataDeCriacao"));
			empresa.setLogradouro(rs.getString("logradouro"));
			empresa.setNumero(rs.getString("numero"));
			empresa.setComplemento(rs.getString("complemento"));
			empresa.setBairro(rs.getString("bairro"));
			empresa.setInativo(rs.getInt("inativo"));
			
			cidade.setId(rs.getInt("idCidade"));
			cidade.setCidade(rs.getString("cidade"));
            cidade.setSigla(rs.getString("siglaCidade"));
            
			estado.setId(rs.getInt("idEstado"));
	        estado.setEstado(rs.getString("estado"));
	        estado.setSigla(rs.getString("siglaEstado"));
	        
	        pais.setId(rs.getInt("idPais"));
	        pais.setPais(rs.getString("pais"));
	        pais.setSigla(rs.getString("siglaPais"));
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		empresa.setCidade(cidade);
		
		return empresa;
	}
}
