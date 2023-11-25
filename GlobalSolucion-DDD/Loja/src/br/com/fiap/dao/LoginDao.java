package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.validation.constraints.Email;

import br.com.fiap.entity.Login;
import br.com.fiap.dao.GerenciadorBd;

public class LoginDao {

	private Connection conexao;

	// Inserir
	public void inserir(Login login) {
		try (Connection conexao = GerenciadorBd.obterConexao();
				PreparedStatement comandoSql = conexao.prepareStatement(
						"INSERT INTO loginGlobal (nome, senha,email, sobrenome) VALUES (?, ?, ?, ?)")) {

			comandoSql.setString(1, login.getNome());
			comandoSql.setString(2, login.getSenha());
			comandoSql.setString(3, login.getEmail());
			comandoSql.setString(4, login.getSobrenome());
			comandoSql.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Fazer login
	public boolean verificarLogin(String senha, String email) {
		try (Connection conexao = GerenciadorBd.obterConexao();
				PreparedStatement comandoSql = conexao
						.prepareStatement("SELECT * FROM loginGlobal WHERE senha = ? AND email = ? ")) {

			comandoSql.setString(1, senha);
			comandoSql.setString(2, email);
			ResultSet rs = comandoSql.executeQuery();
			if (rs.next()) {
				// Se houver um resultado, significa que o login foi bem-sucedido
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Se chegou aqui, significa que o login falhou
		return false;
	}

	public boolean verificarLoginAdm(String usuario, String senha) {
		conexao = GerenciadorBd.obterConexao();
		PreparedStatement comandoSql = null;
		ResultSet resultSet = null;

		try {
			comandoSql = conexao.prepareStatement("SELECT * FROM adms WHERE usuario = ? AND senha = ?");
			comandoSql.setString(1, usuario);
			comandoSql.setString(2, senha);

			resultSet = comandoSql.executeQuery();

			// Se houver uma linha no resultado, o login é válido
			return resultSet.next();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			// Feche as conexões e recursos
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (comandoSql != null) {
					comandoSql.close();
				}
				if (conexao != null) {
					conexao.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
