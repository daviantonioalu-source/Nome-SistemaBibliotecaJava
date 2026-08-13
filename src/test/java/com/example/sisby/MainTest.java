package com.example.sisby;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MainTest {
    @Test
    void deveEmprestarEDevolverLivro() {
        LivroService livroService = new LivroService();
        UsuarioService usuarioService = new UsuarioService();
        EmprestimoService emprestimoService = new EmprestimoService();

        Livro livro = livroService.cadastrar("Dom Casmurro", "Machado de Assis", 1899);
        Usuario usuario = usuarioService.cadastrar("Ana Silva", "ana@email.com");

        Emprestimo emprestimo = emprestimoService.emprestar(livro, usuario);

        assertFalse(livro.isDisponivel());
        assertTrue(emprestimo.isAtivo());

        assertTrue(emprestimoService.devolver(emprestimo.getId()));
        assertTrue(livro.isDisponivel());
        assertFalse(emprestimo.isAtivo());
    }

    @Test
    void naoDeveEmprestarLivroIndisponivel() {
        Livro livro = new LivroService().cadastrar("Dom Casmurro", "Machado de Assis", 1899);
        Usuario usuario = new UsuarioService().cadastrar("Ana Silva", "ana@email.com");
        EmprestimoService service = new EmprestimoService();

        service.emprestar(livro, usuario);

        assertThrows(IllegalStateException.class, () -> service.emprestar(livro, usuario));
    }

    @Test
    void deveRejeitarCadastroComDadosInvalidos() {
        LivroService livroService = new LivroService();
        UsuarioService usuarioService = new UsuarioService();

        assertThrows(IllegalArgumentException.class, () -> livroService.cadastrar(" ", "Autor", 2020));
        assertThrows(IllegalArgumentException.class, () -> livroService.cadastrar("Livro", "Autor", 0));
        assertThrows(IllegalArgumentException.class, () -> usuarioService.cadastrar("Ana", "email-invalido"));
    }
}
