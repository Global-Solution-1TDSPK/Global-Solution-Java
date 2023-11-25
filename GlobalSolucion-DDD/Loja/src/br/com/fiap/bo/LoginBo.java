package br.com.fiap.bo;

import br.com.fiap.entity.Login;
import br.com.fiap.dao.LoginDao;

public class LoginBo {

    private LoginDao loginDao;

    // Construtor
    public LoginBo() {
        this.loginDao = new LoginDao();
    }
    
    // Inserir
    public void cadastrar(Login login) {
        loginDao.inserir(login);
    }

    // Verificar login
    public boolean verificarLogin(String senha,String email) {
        return loginDao.verificarLogin(senha,email);
    }

    // Verificar login para administradores
    public boolean verificarLoginAdm(String usuario, String senha) {
        return loginDao.verificarLoginAdm(usuario, senha);
    }
}
