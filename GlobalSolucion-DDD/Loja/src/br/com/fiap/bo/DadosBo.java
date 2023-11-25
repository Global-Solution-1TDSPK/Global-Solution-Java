package br.com.fiap.bo;

import br.com.fiap.entity.Dados;
import br.com.fiap.dao.DadosDao;

import java.util.List;

public class DadosBo {

    private DadosDao dadosDao;

    // inserir
    public void cadastrar(Dados dado) {
        dadosDao = new DadosDao();
        dadosDao.inserir(dado);
    }

    // atualizar
    public void alterar(Dados novosDados, int idUsuario) {
        dadosDao = new DadosDao();
        dadosDao.atualizar(novosDados, idUsuario);
    }

    // Verificar dados
    public List<Dados> verificarDados() {
        dadosDao = new DadosDao();
        return dadosDao.verificarDados();
    }
}
