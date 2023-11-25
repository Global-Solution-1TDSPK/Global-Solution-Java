package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.entity.Triagem;

public class TriagemDao {
	private Connection conexao;
	private Triagem triagem;

	// Inserir
	public void inserir(Triagem triagem) {
		try (Connection conexao = GerenciadorBd.obterConexao();
				PreparedStatement comandoSql = conexao.prepareStatement(
						"INSERT INTO TRIAGEM (motiVisita, tomMedicamento, condPreExist, sintEspecifico, contProxDoenInfec, viagemSurto) VALUES (?, ?, ?, ?, ?, ?)")) {

			comandoSql.setString(1, triagem.getMotiVisita());
			comandoSql.setString(2, triagem.getTomMedicamento());
			comandoSql.setString(3, triagem.getCondPreExist());
			comandoSql.setString(4, triagem.getSintEspecifico());
			comandoSql.setString(5, triagem.getContProxDoenInfec());
			comandoSql.setString(6, triagem.getViagemSurto());
			comandoSql.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	//Buscar lista de triagens
	public List<Triagem> verificarTriagem() {
		List<Triagem> triagens = new ArrayList<>();
		try (Connection conexao = GerenciadorBd.obterConexao();
				PreparedStatement comandoSql = conexao.prepareStatement("SELECT * FROM TRIAGEM")) {

			ResultSet rs = comandoSql.executeQuery();
			while (rs.next()) {
				// Para cada linha no resultado, crie um objeto Triagem e adicione à lista
				Triagem triagem = new Triagem();
				System.out.println(rs.getString("MOTI_VISITA"));
				triagem.setMotiVisita(rs.getString("MOTI_VISITA"));
				triagem.setTomMedicamento(rs.getString("TOM_MEDICAMENTO"));
				triagem.setCondPreExist(rs.getString("COND_PRE_EXIST"));
				triagem.setSintEspecifico(rs.getString("SINT_ESPECIFICO"));
				triagem.setContProxDoenInfec(rs.getString("CONT_PROX_DOEN_INFEC"));
				triagem.setViagemSurto(rs.getString("VIAGEM_SURTO"));
				triagens.add(triagem);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return triagens;

	}
}
