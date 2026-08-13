package com.example.sisby;

import java.time.LocalDate;
import java.util.Objects;

public class Emprestimo {
    private final int id;
    private final Livro livro;
    private final Usuario usuario;
    private final LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;

    public Emprestimo(int id, Livro livro, Usuario usuario, LocalDate dataEmprestimo) {
        this.id = id;
        this.livro = livro;
        this.usuario = usuario;
        this.dataEmprestimo = dataEmprestimo;
    }

    public int getId() {
        return id;
    }

    public Livro getLivro() {
        return livro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public boolean isAtivo() {
        return dataDevolucao == null;
    }

    public void devolver(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
        this.livro.devolver();
    }

    @Override
    public String toString() {
        String status = isAtivo() ? "Ativo" : "Devolvido em " + dataDevolucao;
        return String.format(
                "#%d | Livro: %s | Usuario: %s | Emprestimo: %s | %s",
                id,
                livro.getTitulo(),
                usuario.getNome(),
                dataEmprestimo,
                status
        );
    }

    @Override
    public boolean equals(Object objeto) {
        if (this == objeto) {
            return true;
        }
        if (!(objeto instanceof Emprestimo)) {
            return false;
        }
        Emprestimo emprestimo = (Emprestimo) objeto;
        return id == emprestimo.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
