package com.example.sisby;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class LivroService {
    private final List<Livro> livros = new ArrayList<>();
    private int proximoId = 1;

    public Livro cadastrar(String titulo, String autor, int anoPublicacao) {
        String tituloValidado = validarTexto(titulo, "titulo");
        String autorValidado = validarTexto(autor, "autor");

        if (anoPublicacao <= 0) {
            throw new IllegalArgumentException("O ano de publicacao deve ser maior que zero.");
        }

        Livro livro = new Livro(proximoId++, tituloValidado, autorValidado, anoPublicacao);
        livros.add(livro);
        return livro;
    }

    public List<Livro> listar() {
        return Collections.unmodifiableList(livros);
    }

    public Optional<Livro> buscarPorId(int id) {
        return livros.stream()
                .filter(livro -> livro.getId() == id)
                .findFirst();
    }

    public List<Livro> buscarPorTitulo(String titulo) {
        String termo = validarTexto(titulo, "titulo").toLowerCase(Locale.ROOT);
        List<Livro> encontrados = new ArrayList<>();

        for (Livro livro : livros) {
            if (livro.getTitulo().toLowerCase(Locale.ROOT).contains(termo)) {
                encontrados.add(livro);
            }
        }

        return encontrados;
    }

    public boolean remover(int id) {
        Optional<Livro> livro = buscarPorId(id);

        if (livro.isEmpty() || !livro.get().isDisponivel()) {
            return false;
        }

        return livros.remove(livro.get());
    }

    private String validarTexto(String valor, String campo) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException("O " + campo + " e obrigatorio.");
        }

        return valor.trim();
    }
}
