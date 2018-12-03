package sp.com.senac.pi.model.dao.compras;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sp.com.senac.pi.conexao.contratos.DbConnection;
import sp.com.senac.pi.conexao.singleton.ConnectionSingleton;
import sp.com.senac.pi.model.pojo.compras.Compra;
import sp.com.senac.pi.model.pojo.compras.CompraItem;
import sp.com.senac.pi.model.pojo.compras.Comprador;
import sp.com.senac.pi.model.pojo.compras.Fornecedor;
import sp.com.senac.pi.model.pojo.producao.Estoque;
import sp.com.senac.pi.util.control.Util;

public class CompraDao {
	
	private DbConnection connection;

	public CompraDao() {
		this.connection = ConnectionSingleton.getNewConnection();
	}
	
	public Compra insert(Compra compra) {
		compra.setId(0);
		Compra compraInserida = this.sendDb(compra);
		compraInserida.setCompraItens(new CompraItemDao().alterarCompraItem(compraInserida));
		
		return compraInserida;
	}
	
	public Compra update(Compra compra) {
		Compra compraAlterada = this.sendDb(compra);
		compraAlterada.setCompraItens(new CompraItemDao().alterarCompraItem(compraAlterada));
		
		return compraAlterada;
	}
	
	
	public Compra getCompra(int id) {
		this.connection.open();
		try {
			PreparedStatement stmt = this.connection.getConnection()
					.prepareStatement("select * from vwCompra where id = ?");
			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();
			Compra compra = new Compra();
	
			if (rs.next()) {
				compra = this.carregaCompra(rs);
			}

			rs.close();
			stmt.close();
			connection.close();
			return compra;

		} catch (SQLException e) {
			connection.close();
			throw new RuntimeException(e);
		}		
	}
	
	public List<Compra> getCompras() {
		this.connection.open();
		try {
			PreparedStatement stmt = this.connection.getConnection()
					.prepareStatement("select * from vwCompra");

			ResultSet rs = stmt.executeQuery();
			List<Compra> compras = new ArrayList<Compra>();
	
			while (rs.next()) {
				Compra compra = this.carregaCompra(rs);
				compras.add(compra);
			}

			rs.close();
			stmt.close();
			connection.close();
			return compras;

		} catch (SQLException e) {
			connection.close();
			throw new RuntimeException(e);
		}		
	}
	
	public List<Compra> getComprasRecebidas() {
		this.connection.open();
		try {
			PreparedStatement stmt = this.connection.getConnection()
					.prepareStatement("select * from vwCompra where recebido = 1");

			ResultSet rs = stmt.executeQuery();
			List<Compra> compras = new ArrayList<Compra>();
	
			while (rs.next()) {
				Compra compra = this.carregaCompra(rs);
				compras.add(compra);
			}

			rs.close();
			stmt.close();
			connection.close();
			return compras;

		} catch (SQLException e) {
			connection.close();
			throw new RuntimeException(e);
		}		
	}
	
	public List<Compra> getComprasNaoRecebidas() {
		this.connection.open();
		try {
			PreparedStatement stmt = this.connection.getConnection()
					.prepareStatement("select * from vwCompra where recebido = 0");

			ResultSet rs = stmt.executeQuery();
			List<Compra> compras = new ArrayList<Compra>();
	
			while (rs.next()) {
				Compra compra = this.carregaCompra(rs);
				compras.add(compra);
			}

			rs.close();
			stmt.close();
			connection.close();
			return compras;

		} catch (SQLException e) {
			connection.close();
			throw new RuntimeException(e);
		}		
	}
	
	public List<Compra> getCompras(Fornecedor fornecedor) {
		this.connection.open();
		try {
			PreparedStatement stmt = this.connection.getConnection()
					.prepareStatement("select * from vwCompra where idFornecedor = ?");
			stmt.setInt(1, fornecedor.getId());
			
			ResultSet rs = stmt.executeQuery();
			List<Compra> compras = new ArrayList<Compra>();
	
			while (rs.next()) {
				Compra compra = this.carregaCompra(rs);
				compras.add(compra);
			}

			rs.close();
			stmt.close();
			connection.close();
			return compras;

		} catch (SQLException e) {
			connection.close();
			throw new RuntimeException(e);
		}		
	}
	
	public List<Compra> getCompras(Comprador comprador) {
		this.connection.open();
		try {
			PreparedStatement stmt = this.connection.getConnection()
					.prepareStatement("select * from vwCompra where idComprador = ?");
			stmt.setInt(1, comprador.getId());
			
			ResultSet rs = stmt.executeQuery();
			List<Compra> compras = new ArrayList<Compra>();
	
			while (rs.next()) {
				Compra compra = this.carregaCompra(rs);
				compras.add(compra);
			}

			rs.close();
			stmt.close();
			connection.close();
			return compras;

		} catch (SQLException e) {
			connection.close();
			throw new RuntimeException(e);
		}		
	}
	
	public List<Compra> getCompras(String dataInicial, String dataFinal) {
		this.connection.open();
		try {
			PreparedStatement stmt = this.connection.getConnection()
					.prepareStatement("select * from vwCompra where dataDaCompra >= ? and dataCompra <= ?");
			stmt.setString(1, dataInicial);
			stmt.setString(1, dataFinal);
			
			ResultSet rs = stmt.executeQuery();
			List<Compra> compras = new ArrayList<Compra>();
	
			while (rs.next()) {
				Compra compra = this.carregaCompra(rs);
				compras.add(compra);
			}

			rs.close();
			stmt.close();
			connection.close();
			return compras;

		} catch (SQLException e) {
			connection.close();
			throw new RuntimeException(e);
		}		
	}
	
	/*public Compra receber(Compra compra) {
		for (CompraItem compraItem : compra.getCompraItens() ) {
			Estoque estoque = new Estoque();
			estoque
		}
	}*/
	
	private Compra carregaCompra(ResultSet rs) {
		Compra compra = new Compra();
    	
        try {
	        
        	compra.setId(rs.getInt("id"));
        	compra.setDataDaCompra(new Util().getStringDate(rs.getTimestamp("dataDaCompra")));
        	compra.setFornecedor(new FornecedorDao().getFornecedor(rs.getInt("idFornecedor")));
        	compra.setComprador(new CompradorDao().getComprador(rs.getInt("idComprador")));
        	compra.setRecebido(rs.getBoolean("recebido"));
        	compra.setCompraItens( new CompraItemDao().getCompraItens(compra));
        	

        } catch (SQLException e) {
        	throw new RuntimeException(e);
		}
        
        return compra;
    }
	
	public Compra sendDb(Compra compra) {
		String sql = "exec Compras.SPIU_COMPRA ?,?,?,?,?";
		
		this.connection.open();
		try {
			
			PreparedStatement stmt = connection.getConnection().prepareStatement(sql);
			
			stmt.setInt(1, compra.getId());
			stmt.setString(2, new Util().getStringDate(compra.getDataDaCompra()));
			stmt.setInt(3, compra.getComprador().getId());
			stmt.setInt(4, compra.getFornecedor().getId());
			stmt.setBoolean(5, compra.isRecebido());
			
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				compra.setId(rs.getInt("id"));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			connection.close();
			return compra;
		}
		connection.close();
		return compra;
	}
}
