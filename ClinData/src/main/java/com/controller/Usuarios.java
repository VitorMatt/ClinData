
package main.java.com.controller;

import main.java.com.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class Usuarios {

    private List<Usuario> usuarios = new ArrayList<>();

    public void registrarUsuario(Usuario usuario) {

        if (buscarPorEmail(usuario.getEmail()) != null ||
                buscarPorCPF(usuario.getCpf()) != null) {

            System.out.println("Usuário já cadastrado!");
            return;
        }

        usuarios.add(usuario);
    }

    public Usuario buscarPorEmail(String email) {

        for (Usuario usuario : usuarios) {

            if (usuario.getEmail().equalsIgnoreCase(email)) {
                return usuario;
            }
        }

        return null;
    }

    public Usuario buscarPorCPF(String cpf) {

        for (Usuario usuario : usuarios) {

            if (usuario.getCpf().equals(cpf)) {
                return usuario;
            }
        }

        return null;
    }

    public boolean removerUsuario(String cpf) {

        Usuario usuario = buscarPorCPF(cpf);

        if (usuario != null) {
            usuarios.remove(usuario);
            return true;
        }

        return false;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }
}