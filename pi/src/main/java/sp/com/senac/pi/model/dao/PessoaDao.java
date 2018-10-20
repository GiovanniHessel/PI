package sp.com.senac.pi.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sp.com.senac.pi.conexao.contratos.DbConnection;
import sp.com.senac.pi.conexao.singleton.ConnectionSingleton;
import sp.com.senac.pi.model.cadastro.Cidade;
import sp.com.senac.pi.model.cadastro.Pessoa;

public class PessoaDao {
	private DbConnection connection;

	public PessoaDao() {
		this.connection = ConnectionSingleton.getConnection();
	}
	
	public Pessoa insert(Pessoa pessoa) {
		pessoa.setId(0);

		String sql = "insert into Pessoa" 
				+ "(nome, sobrenome, CPF, dataDeNascimento, logradouro, numero, complemento, bairro, idCidade, inativo)" 
				+ "Output Inserted.id as id"
				+ " values (?,?,?,?,?,?,?,?,?,?)";
		
		this.connection.open();
		try {
			// prepared statement para inserção

			PreparedStatement stmt = connection.getConnection().prepareStatement(sql);

			// seta os valores
			stmt.setString(1, pessoa.getNome());
			stmt.setString(2, pessoa.getSobrenome());
			stmt.setString(3, pessoa.getCPF());
			stmt.setString(4, pessoa.getDataDeNascimento());
			stmt.setString(5, pessoa.getLogradouro());
			stmt.setString(6, pessoa.getNumero());
			stmt.setString(7, pessoa.getComplemento());
			stmt.setString(8, pessoa.getBairro());
			stmt.setInt(9, pessoa.getCidade().getId());
			stmt.setInt(10, pessoa.getInativo());
			
			// executa
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				pessoa.setId(rs.getInt("id"));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			connection.close();
			return pessoa;
		}
		connection.close();
		return pessoa;
	}
	
	public Pessoa getPessoa(int id) {
        this.connection.open();
        try {
            PreparedStatement stmt = this.connection.getConnection().prepareStatement("select id, nome, sobrenome, CPF, dataDeNascimento, logradouro, numero, complemento, bairro, idCidade, inativo from Pessoa where id = ?");
            stmt.setInt(1, id);
    
            ResultSet rs = stmt.executeQuery();
            Pessoa pessoa = new Pessoa();
            // criando o objeto Contato
            if (rs.next()){
               
                pessoa.setId(				rs.getInt("id"));
                pessoa.setNome(				rs.getString("nome"));
                pessoa.setSobrenome(		rs.getString("sobreNome"));
                pessoa.setCPF(				rs.getString("CPF"));
                pessoa.setDataDeNascimento(	rs.getString("dataDeNascimento"));
                pessoa.setLogradouro(		rs.getString("logradouro"));
                pessoa.setNumero(			rs.getString("numero"));
                pessoa.setComplemento(		rs.getString("complemento"));
                pessoa.setBairro(			rs.getString("bairro"));
                Cidade cidade = new Cidade(		rs.getInt("idCidade"));
                pessoa.setCidade(cidade);
                pessoa.setInativo(			rs.getInt("inativo"));
                // adicionando o objeto à lista
            }
            rs.close();
            stmt.close();
            connection.close();
            return pessoa;

        } catch (SQLException e) {
            connection.close();
            throw new RuntimeException(e);
        }
    }

	public Pessoa getPessoaCPF(int cpf) {
        this.connection.open();
        try {
            PreparedStatement stmt = this.connection.getConnection().prepareStatement("select id, nome, sobrenome, CPF, dataDeNascimento, logradouro, numero, complemento, bairro, idCidade, inativo from Pessoa where CPF = ?");
            stmt.setInt(1, cpf);

            ResultSet rs = stmt.executeQuery();
            Pessoa pessoa = new Pessoa();
            // criando o objeto Contato
            if (rs.next()){
               
            	 pessoa.setId(				rs.getInt("id"));
                 pessoa.setNome(				rs.getString("nome"));
                 pessoa.setSobrenome(		rs.getString("sobreNome"));
                 pessoa.setCPF(				rs.getString("CPF"));
                 pessoa.setDataDeNascimento(	rs.getString("dataDeNascimento"));
                 pessoa.setLogradouro(		rs.getString("logradouro"));
                 pessoa.setNumero(			rs.getString("numero"));
                 pessoa.setComplemento(		rs.getString("complemento"));
                 pessoa.setBairro(			rs.getString("bairro"));
                 Cidade cidade = new Cidade(		rs.getInt("idCidade"));
                 pessoa.setCidade(cidade);
                 pessoa.setInativo(			rs.getInt("inativo"));
                // adicionando o objeto à lista
            }
            rs.close();
            stmt.close();
            connection.close();
            return pessoa;

        } catch (SQLException e) {
            connection.close();
            throw new RuntimeException(e);
        }
    }
    
    public List<Pessoa> getPessoas() {
        this.connection.open();
        try {
            PreparedStatement stmt = this.connection.getConnection().prepareStatement("select id, nome, sobrenome, CPF, dataDeNascimento, logradouro, numero, complemento, bairro, idCidade, inativo from Pessoa");

            ResultSet rs = stmt.executeQuery();
            List<Pessoa> pessoas = new ArrayList<Pessoa>();
            // criando o objeto Contato
            while (rs.next()){
                Pessoa pessoa = new Pessoa();
                pessoa.setId(				rs.getInt("id"));
                pessoa.setNome(				rs.getString("nome"));
                pessoa.setSobrenome(		rs.getString("sobreNome"));
                pessoa.setCPF(				rs.getString("CPF"));
                pessoa.setDataDeNascimento(	rs.getString("dataDeNascimento"));
                pessoa.setLogradouro(		rs.getString("logradouro"));
                pessoa.setNumero(			rs.getString("numero"));
                pessoa.setComplemento(		rs.getString("complemento"));
                pessoa.setBairro(			rs.getString("bairro"));
                Cidade cidade = new Cidade(		rs.getInt("idCidade"));
                pessoa.setCidade(cidade);
                pessoa.setInativo(			rs.getInt("inativo"));
                pessoas.add(pessoa);
            }
            rs.close();
            stmt.close();
            connection.close();
            return pessoas;
        } catch (SQLException e) {
            connection.close();
            throw new RuntimeException(e);
        }
    }
	
	
}
