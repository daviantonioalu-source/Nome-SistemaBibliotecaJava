package com.example.sisby;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class UsuarioService {
    private final List<Usuario> usuarios = new ArrayList<>();
    private int proximoId = 1;

    public Usuario cadastrar(String nome, String email) {
        String nomeValidado = validarTexto(nome, "nome");
        String emailValidado = validarTexto(email, "email");

        if (!emailValidado.contains("@")) {
            throw new IllegalArgumentException("Informe um email valido.");
        }

        Usuario usuario = new Usuario(proximoId++, nomeValidado, emailValidado);
        usuarios.add(usuario);
        return usuario;
    }

    public List<Usuario> listar() {
        return Collections.unmodifiableList(usuarios);
    }

    public Optional<Usuario> buscarPorId(int id) {
        return usuarios.stream()
                .filter(usuario -> usuario.getId() == id)
                .findFirst();
    }

    public List<Usuario> buscarPorNome(String nome) {
        String termo = validarTexto(nome, "nome").toLowerCase(Locale.ROOT);
        List<Usuario> encontrados = new ArrayList<>();

        for (Usuario usuario : usuarios) {
            if (usuario.getNome().toLowerCase(Locale.ROOT).contains(termo)) {
                encontrados.add(usuario);
            }
        }

        return encontrados;
    }

    public boolean remover(int id) {
        Optional<Usuario> usuario = buscarPorId(id);
        return usuario.isPresent() && usuarios.remove(usuario.get());
    }

    private String validarTexto(String valor, String campo) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException("O " + campo + " e obrigatorio.");
        }

        return valor.trim();
    }
}
