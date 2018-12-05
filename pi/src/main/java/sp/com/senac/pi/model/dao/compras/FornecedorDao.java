package sp.com.senac.pi.model.dao.compras;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sp.com.senac.pi.conexao.contratos.DbConnection;
import sp.com.senac.pi.conexao.singleton.ConnectionSingleton;
import sp.com.senac.pi.model.dao.base.EmpresaDao;
import sp.com.senac.pi.model.dao.contato.ContatoDao;
import sp.com.senac.pi.model.pojo.base.Empresa;
import sp.com.senac.pi.model.pojo.compras.Fornecedor;
import sp.com.senac.pi.model.pojo.localizacao.Cidade;
import sp.com.senac.pi.model.pojo.localizacao.Estado;
import sp.com.senac.pi.model.pojo.localizacao.Pais;
import sp.com.senac.pi.model.pojo.producao.Produto;
import sp.com.senac.pi.util.control.Util;

public class FornecedorDao {
	private DbConnection connection;

	public FornecedorDao() {
		this.connection = ConnectionSingleton.getNewConnection();
	}
	
	public Fornecedor insert(Fornecedor fornecedor) {
		fornecedor.setId(0);
		
		if (fornecedor.getIdEmpresa() == 0) {
			Empresa empresa = new EmpresaDao().insert(fornecedor);
			fornecedor.setIdEmpresa(empresa.getIdEmpresa());
		}
		
		if (fornecedor.getIdEmpresa() == 0) {
			return fornecedor;
		}
		
		return this.sendDB(fornecedor);
	}
	
	public Fornecedor update(Fornecedor fornecedor) {
		
		Empresa empresa = new  EmpresaDao().update(fornecedor);
		fornecedor.setIdEmpresa(empresa.getIdEmpresa());
		
		if (fornecedor.getIdEmpresa() == 0) {
			fornecedor.setId(0);
			return fornecedor;
		}
		
		return this.sendDB(fornecedor);
	}
	
	public Fornecedor delete(Fornecedor fornecedor) {
		this.connection.open();
		try {
			PreparedStatement stmt = this.connection.getConnection()
					.prepareStatement("exec Compras.SPD_FORNECEDOR ?");
			stmt.setInt(1, fornecedor.getId());

			ResultSet rs = stmt.executeQuery();
	
			if (rs.next()) {
				fornecedor.setId(rs.getInt("id"));
			}

			rs.close();
			stmt.close();
			connection.close();
			return fornecedor;

		} catch (SQLException e) {
			connection.close();
			throw new RuntimeException(e);
		}
	}
	
	public Fornecedor ativoInativo(Fornecedor fornecedor) {

		this.connection.open();
		try {
	
			PreparedStatement stmt = this.connection.getConnection().prepareStatement("update Compras.Fornecedor set inativo = ? OUTPUT DELETED.id  where id = ?");
			stmt.setInt(1, fornecedor.getInativo());
			stmt.setInt(2, fornecedor.getId());

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				fornecedor.setId(rs.getInt("id"));
			}
			
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			connection.close();
			return fornecedor;
		}
		connection.close();
		
		return fornecedor;
	}
	
	public Fornecedor getFornecedor(int id) {
		this.connection.open();
		try {
			PreparedStatement stmt = this.connection.getConnection()
					.prepareStatement("select * from Compras.vwFornecedor where id = ?");
			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();
			Fornecedor fornecedor = new Fornecedor();
	
			if (rs.next()) {
				fornecedor = this.carregaFornecedor(rs);
			}

			rs.close();
			stmt.close();
			connection.close();
			return fornecedor;

		} catch (SQLException e) {
			connection.close();
			throw new RuntimeException(e);
		}
	}

	public List<Fornecedor> getFornecedorNome(String nome) {
		this.connection.open();
		try {
			PreparedStatement stmt = this.connection.getConnection().prepareStatement("select * from Compras.vwFornecedor where nomeFantasia like '" + nome +"'");

			ResultSet rs = stmt.executeQuery();
			List<Fornecedor> fornecedores = new ArrayList<Fornecedor>();
		
			while (rs.next()) {
				fornecedores.add(this.carregaFornecedor(rs));
			}

			rs.close();
			stmt.close();
			connection.close();
			return fornecedores;

		} catch (SQLException e) {
			connection.close();
			throw new RuntimeException(e);
		}
	}
	
	public Fornecedor getFornecedorCpf(String cpnj) {
		this.connection.open();
		try {
			PreparedStatement stmt = this.connection.getConnection().prepareStatement("select * from Compras.vwFornecedor where cpnj = ?");
			stmt.setString(1, cpnj);

			ResultSet rs = stmt.executeQuery();
			Fornecedor fornecedor = new Fornecedor();
		
			if (rs.next()) {
				fornecedor = this.carregaFornecedor(rs);
			}

			rs.close();
			stmt.close();
			connection.close();
			return fornecedor;

		} catch (SQLException e) {
			connection.close();
			throw new RuntimeException(e);
		}
	}
	
	public List<Fornecedor> getFornecedores() {
		this.connection.open();
		try {
			PreparedStatement stmt = this.connection.getConnection().prepareStatement("select * from Compras.vwFornecedor");
			List<Fornecedor> fornecedores = new ArrayList<Fornecedor>();
			ResultSet rs = stmt.executeQuery();
		
			while (rs.next()) {
				Fornecedor fornecedor = this.carregaFornecedor(rs);
				fornecedores.add(fornecedor);
			}

			rs.close();
			stmt.close();
			connection.close();
			return fornecedores;

		} catch (SQLException e) {
			connection.close();
			throw new RuntimeException(e);
		}
	}
	
	private Fornecedor carregaFornecedor(ResultSet rs) {
		Fornecedor fornecedor = new Fornecedor();
		Cidade cidade = new Cidade();
		Estado estado = new Estado();
    	Pais pais = new Pais();
    	
		try {
			
			fornecedor.setId(rs.getInt("id"));
			fornecedor.setFormatoJuridico(rs.getString("formatoJuridico"));
			fornecedor.setRegimeTributario(rs.getString("regimeTributario"));
			fornecedor.setInativo(rs.getInt("inativo"));
			
			fornecedor.setIdEmpresa(rs.getInt("idEmpresa"));
			fornecedor.setNomeFantasia(rs.getString("nomeFantasia"));
			fornecedor.setRazaoSocial(rs.getString("razaoSocial"));
			fornecedor.setCnpj(rs.getString("CNPJ"));
			fornecedor.setDataDeCriacao(new Util().getStringDate(rs.getTimestamp("dataDeCriacao")));
			
			fornecedor.setCep(rs.getString("Cep"));
			fornecedor.setLogradouro(rs.getString("logradouro"));
			fornecedor.setNumero(rs.getString("numero"));
			fornecedor.setComplemento(rs.getString("complemento"));
			fornecedor.setBairro(rs.getString("bairro"));
			fornecedor.setContatos(new ContatoDao().getContatos(fornecedor));
			
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
			System.out.println("afonso");
			throw new RuntimeException(e);
		}
		
		estado.setPais(pais);
        cidade.setEstado(estado);
        fornecedor.setCidade(cidade);

        return fornecedor;
	}
	
	private Fornecedor sendDB(Fornecedor fornecedor)  {
		
		String sql = "exec Compras.SPIU_FORNECEDOR ?,?,?,?,?";
		
		this.connection.open();
		try {
			
			PreparedStatement stmt = connection.getConnection().prepareStatement(sql);
			
			stmt.setInt(1, fornecedor.getId());
			stmt.setString(2, fornecedor.getFormatoJuridico());
			stmt.setString(3, fornecedor.getRegimeTributario());
			stmt.setInt(4, fornecedor.getIdEmpresa());
			stmt.setInt(5, fornecedor.getInativo());
			
			
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				fornecedor.setId(rs.getInt("id"));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println("3");
			
			System.out.println(e.getMessage());
			connection.close();
			return fornecedor;
		}
		connection.close();
		return fornecedor;
	
	}
}
