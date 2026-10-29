package main.java.com.controller;
import main.java.com.model.Profissional;
import java.util.ArrayList;
import java.util.List;


public class Usuarios {
    
    List<Profissional> profissionais = new ArrayList<>();
    
    public Usuarios() {
    }
        
    public void registrarProfissional(Profissional profissional) {
        profissionais.add(profissional);
    }
}
