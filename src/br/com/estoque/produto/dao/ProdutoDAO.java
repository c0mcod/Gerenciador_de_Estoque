package br.com.estoque.produto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.estoque.connection.ConnectionFactory;
import br.com.estoque.produto.model.Produto;

public class ProdutoDAO {
	public void CadastrarProduto(Produto produto) throws SQLException {
		String sql = "INSERT INTO produto(nome, quantidade, preco, id_fornecedor) VALUES(?, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);

			pstm.setString(1, produto.getNome());
			pstm.setInt(2, produto.getQuantidade());
			pstm.setString(3, produto.getPreco());
			pstm.setInt(4, produto.getId_fornecedor());

			pstm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
			if (pstm != null) {
				pstm.close();
			}
		}
	}

	public List<Produto> getProdutos() throws SQLException {
		String sql = "SELECT * FROM produto";
		List<Produto> produtos = new ArrayList<Produto>();

		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;

		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);

			rset = pstm.executeQuery();
			while (rset.next()) {
				Produto p = new Produto();
				p.setId(rset.getInt("id"));
				p.setNome(rset.getString("nome"));
				p.setQuantidade(rset.getInt("quantidade"));
				p.setPreco(rset.getString("preco"));
				p.setId_fornecedor(rset.getInt("id_fornecedor"));

				produtos.add(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
			if (pstm != null) {
				pstm.close();
			}
			if (rset != null) {
				rset.close();
			}
		}
		return produtos;
	}

	public void atualizarProduto(Produto produto) throws SQLException {
		String sql = "UPDATE produto SET nome = ?, quantidade = ?, preco = ?, id_fornecedor = ? WHERE id = ?";
		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);

			pstm.setString(1, produto.getNome());
			pstm.setInt(2, produto.getQuantidade());
			pstm.setString(3, produto.getPreco());
			pstm.setInt(4, produto.getId_fornecedor());

			pstm.setInt(5, produto.getId());

			pstm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
			if (pstm != null) {
				pstm.close();
			}
		}
	}

	public void deletarProduto(int id) throws SQLException {
		String sql = "DELETE FROM produto WHERE id = ?";
		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);

			pstm.setInt(1, id);
			pstm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
			if (pstm != null) {
				pstm.close();
			}
		}
	}

	public Produto buscarUmPorId(int id) {
		String sql = "SELECT nome, preco FROM produto WHERE id = ?";
		Produto produto = null;

		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;

		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);

			rset = pstm.executeQuery();

			while (rset.next()) {
				produto = new Produto();
				produto.setNome(rset.getString("nome"));
				produto.setPreco(rset.getString("preco"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rset != null)
					rset.close();
				if (pstm != null)
					pstm.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return produto;
	}

	public Produto buscarPrecoPorId(int id) {
		String sql = "SELECT preco FROM produto WHERE id = ?";
		Produto produto = null;

		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;

		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);

			rset = pstm.executeQuery();

			while (rset.next()) {
				produto = new Produto();
				produto.setPreco(rset.getString("preco"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rset != null)
					rset.close();
				if (pstm != null)
					pstm.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return produto;
	}

	public int cardKPIEstoqueTotal() {
		String sql = "SELECT SUM(quantidade) FROM produto";
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int totalQuantidade = 0;

		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();

			if (rs.next()) {
				totalQuantidade = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// sempre feche os recursos
			try {
				if (rs != null)
					rs.close();
				if (pstm != null)
					pstm.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return totalQuantidade;
	}

	public String cardKPIMenorEmEstoque() {
		String sql = "SELECT nome, quantidade FROM produto WHERE quantidade = (SELECT MIN(quantidade) FROM produto)";
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String resultado = "";

		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();

			if (rs.next()) {
				String nome = rs.getString("nome");
				int quantidade = rs.getInt("quantidade");
				resultado = nome + " - " + quantidade + " unidades";
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstm != null)
					pstm.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return resultado;
	}

	public List<Produto> addProdutoVenda() throws SQLException {
		String sql = "SELECT nome, preco FROM produto";
		List<Produto> produtos = new ArrayList<Produto>();

		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;

		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);

			rset = pstm.executeQuery();
			while (rset.next()) {
				Produto p = new Produto();
				p.setNome(rset.getString("nome"));
				p.setPreco(rset.getString("preco"));

				produtos.add(p);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
			if (pstm != null) {
				pstm.close();
			}
			if (rset != null) {
				rset.close();
			}
		}
		return produtos;
	}

	public double calcularValorVenda(int id, int quantidade) throws Exception {
		double valorTotal = 0.0;

		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;

		String sql = "SELECT preco FROM produto WHERE id = ?";

		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);

			pstm.setInt(1, id);
			rset = pstm.executeQuery();
			if (rset.next()) {
				double preco = rset.getDouble("preco");
				valorTotal = preco * quantidade;
			} else {
				System.out.println("Produto id: " + id + "não encontrado.");
			}
		} catch (SQLException e) {
			System.out.println("Erro ao calcular valor da venda: " + e.getMessage());
		}
		return valorTotal;
	}

	public String exibirNome(int id) throws Exception {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		String sql = "SELECT nome FROM produto WHERE id = ?";
		
		String nomeProduto = null;
		
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);
			
			pstm.setInt(1, id);
			rset = pstm.executeQuery();
			if (rset.next()) {
				nomeProduto = rset.getString("nome");
			}
		} catch (SQLException e) {
			System.out.println("Erro ao buscar o nome do produto: " + e.getMessage());
		}
		return nomeProduto;
	}
	

}
