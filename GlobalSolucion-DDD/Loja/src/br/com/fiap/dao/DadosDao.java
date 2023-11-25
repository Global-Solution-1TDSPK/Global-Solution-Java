package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.entity.Dados;

public class DadosDao {

    private Connection conexao;

    // inserir
    public void inserir(Dados dados) {
        conexao = GerenciadorBd.obterConexao();
        PreparedStatement comandoSql = null;
        try {
            comandoSql = conexao.prepareStatement("INSERT INTO usuarios (nome, sobrenome, senha, datanasc, telefone, cpf, sexo)"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?)");
            comandoSql.setString(1, dados.getNome());
            comandoSql.setString(2, dados.getSobrenome());
            comandoSql.setString(3, dados.getSenha());
            comandoSql.setInt(4, dados.getDataNasc());
            comandoSql.setInt(5, dados.getTelefone());
            comandoSql.setString(6, dados.getCpf());
            comandoSql.setString(7, dados.getSexo());
            comandoSql.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conexao != null) {
                    conexao.close();
                }
                if (comandoSql != null) {
                    comandoSql.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // atualizar
    public void atualizar(Dados novosDados, int idUsuario) {
        conexao = GerenciadorBd.obterConexao();
        PreparedStatement comandoSql = null;

        try {
            comandoSql = conexao.prepareStatement("UPDATE usuarios SET nome=?, sobrenome=?, senha=?, datanasc=?, telefone=?, cpf=?, sexo=? WHERE id=?");
            comandoSql.setString(1, novosDados.getNome());
            comandoSql.setString(2, novosDados.getSobrenome());
            comandoSql.setString(3, novosDados.getSenha());
            comandoSql.setInt(4, novosDados.getDataNasc());
            comandoSql.setInt(5, novosDados.getTelefone());
            comandoSql.setString(6, novosDados.getCpf());
            comandoSql.setString(7, novosDados.getSexo());
            comandoSql.setInt(8, idUsuario);

            comandoSql.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conexao != null) {
                    conexao.close();
                }
                if (comandoSql != null) {
                    comandoSql.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Busca Lista de Dados de usuario
    public List<Dados> verificarDados() {
        List<Dados> listaDados = new ArrayList<>();
        try (Connection conexao = GerenciadorBd.obterConexao();
                PreparedStatement comandoSql = conexao.prepareStatement("SELECT * FROM USUARIOS")) {

            ResultSet rs = comandoSql.executeQuery();
            while (rs.next()) {
                Dados dados = new Dados();
                dados.setNome(rs.getString("nome"));
                dados.setSobrenome(rs.getString("sobrenome"));
                dados.setSenha(rs.getString("senha"));
                dados.setDataNasc(rs.getInt("DataNasc"));
                dados.setTelefone(rs.getInt("telefone"));
                dados.setCpf(rs.getString("cpf"));
                listaDados.add(dados);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaDados;
    }
}

