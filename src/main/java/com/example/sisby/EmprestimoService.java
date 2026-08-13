package com.example.sisby;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class EmprestimoService {
    private final List<Emprestimo> emprestimos = new ArrayList<>();
    private int proximoId = 1;

    public Emprestimo emprestar(Livro livro, Usuario usuario) {
        Objects.requireNonNull(livro, "O livro e obrigatorio.");
        Objects.requireNonNull(usuario, "O usuario e obrigatorio.");

        if (!livro.isDisponivel()) {
            throw new IllegalStateException("Livro indisponivel para emprestimo.");
        }

        livro.emprestar();
        Emprestimo emprestimo = new Emprestimo(proximoId++, livro, usuario, LocalDate.now());
        emprestimos.add(emprestimo);
        return emprestimo;
    }

    public List<Emprestimo> listar() {
        return Collections.unmodifiableList(emprestimos);
    }

    public List<Emprestimo> listarAtivos() {
        List<Emprestimo> ativos = new ArrayList<>();

        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.isAtivo()) {
                ativos.add(emprestimo);
            }
        }

        return ativos;
    }

    public Optional<Emprestimo> buscarAtivoPorId(int id) {
        return emprestimos.stream()
                .filter(emprestimo -> emprestimo.getId() == id && emprestimo.isAtivo())
                .findFirst();
    }

    public boolean devolver(int id) {
        Optional<Emprestimo> emprestimo = buscarAtivoPorId(id);

        if (emprestimo.isEmpty()) {
            return false;
        }

        emprestimo.get().devolver(LocalDate.now());
        return true;
    }
}
