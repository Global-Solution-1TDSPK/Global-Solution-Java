package br.com.fiap.bo;

import br.com.fiap.entity.Login;
import br.com.fiap.entity.Triagem;

import java.util.List;

import br.com.fiap.dao.LoginDao;
import br.com.fiap.dao.TriagemDao;

public class TriagemBo {

	private TriagemDao triagemDao;

    // Construtor
    public TriagemBo() {
        this.triagemDao = new TriagemDao();
    }
    
    // Inserir
    public void cadastrar(Triagem triagem) {
        triagemDao.inserir(triagem);
    }
	
    // Verificar login
    public List<Triagem> verificarTriagem() {
        return triagemDao.verificarTriagem();
        }
}
