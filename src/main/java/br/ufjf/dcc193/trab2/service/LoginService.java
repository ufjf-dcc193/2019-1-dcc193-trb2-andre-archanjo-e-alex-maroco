 package br.ufjf.dcc193.trab2.service;

import org.springframework.stereotype.Service;

import br.ufjf.dcc193.trab2.model.Avaliador;

@Service
public class LoginService {

    private static Avaliador user = null;

    public LoginService() {
        
    }

    public void login(Avaliador a) {
        user = a;
    }

    public Avaliador getUser() {
        return user;
    }

    public void logout() {
        user = null;
    }
    /*
    private static LoginService instance = null;

    public LoginService() {
        if(instance == null) {
            instance = new LoginService();
        }
    }
    */
    
}