package br.com.estoque.fornecedor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.estoque.connection.ConnectionFactory;
import br.com.estoque.fornecedor.model.Fornecedor;


public class FornecedorDAO {
	public void CadastrarFornecedor(Fornecedor fornecedor) throws SQLException {
		String sql = "INSERT INTO fornecedor(nome, cnpj) VALUES(?, ?)";
		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);

			pstm.setString(1, fornecedor.getNome());
			pstm.setString(2, fornecedor.getCnpj());

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

	public List<Fornecedor> getFornecedores() throws SQLException {
		String sql = "SELECT * FROM Fornecedor";
		List<Fornecedor> fornecedores = new ArrayList<Fornecedor>();

		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;

		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);

			rset = pstm.executeQuery();
			while (rset.next()) {
				Fornecedor f = new Fornecedor();
				f.setId(rset.getInt("id"));
				f.setNome(rset.getString("nome"));
				f.setCnpj(rset.getString("cnpj"));

				fornecedores.add(f);
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
		return fornecedores;
	}

	public void atualizarFornecedor(Fornecedor fornecedor) throws SQLException {
		String sql = "UPDATE fornecedor SET nome = ?, cnpj = ? WHERE id = ?";
		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);

			pstm.setString(1, fornecedor.getNome());
			pstm.setString(2, fornecedor.getCnpj());

			pstm.setInt(3, fornecedor.getId());

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

	public void deletarFornecedor(int id) throws SQLException {
		String sql = "DELETE FROM fornecedor WHERE id = ?";
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
}
