package com.example.sisby;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Menu {
    private final Scanner scanner = new Scanner(System.in);
    private final LivroService livroService = new LivroService();
    private final UsuarioService usuarioService = new UsuarioService();
    private final EmprestimoService emprestimoService = new EmprestimoService();

    public void iniciar() {
        int opcao;

        do {
            exibirMenuPrincipal();
            opcao = lerInteiro("Escolha uma opcao: ");

            switch (opcao) {
                case 1:
                    cadastrarLivro();
                    break;
                case 2:
                    listarLivros();
                    break;
                case 3:
                    buscarLivroPorTitulo();
                    break;
                case 4:
                    removerLivro();
                    break;
                case 5:
                    cadastrarUsuario();
                    break;
                case 6:
                    listarUsuarios();
                    break;
                case 7:
                    buscarUsuarioPorNome();
                    break;
                case 8:
                    realizarEmprestimo();
                    break;
                case 9:
                    listarEmprestimos();
                    break;
                case 10:
                    devolverLivro();
                    break;
                case 0:
                    System.out.println("Encerrando o SISBY. Ate logo!");
                    break;
                default:
                    System.out.println("Opcao invalida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    private void exibirMenuPrincipal() {
        System.out.println();
        System.out.println("===== SISBY - Sistema de Biblioteca =====");
        System.out.println("1. Cadastrar livro");
        System.out.println("2. Listar livros");
        System.out.println("3. Buscar livro por titulo");
        System.out.println("4. Remover livro");
        System.out.println("5. Cadastrar usuario");
        System.out.println("6. Listar usuarios");
        System.out.println("7. Buscar usuario por nome");
        System.out.println("8. Realizar emprestimo");
        System.out.println("9. Listar emprestimos");
        System.out.println("10. Devolver livro");
        System.out.println("0. Sair");
        System.out.println("=========================================");
    }

    private void cadastrarLivro() {
        System.out.println("\nCadastro de livro");
        String titulo = lerTextoObrigatorio("Titulo: ");
        String autor = lerTextoObrigatorio("Autor: ");
        int ano = lerInteiroPositivo("Ano de publicacao: ");

        Livro livro = livroService.cadastrar(titulo, autor, ano);
        System.out.println("Livro cadastrado: " + livro);
    }

    private void listarLivros() {
        imprimirLista("Livros cadastrados", livroService.listar());
    }

    private void buscarLivroPorTitulo() {
        String titulo = lerTextoObrigatorio("Digite parte do titulo: ");
        imprimirLista("Resultado da busca", livroService.buscarPorTitulo(titulo));
    }

    private void removerLivro() {
        listarLivros();
        int id = lerInteiro("ID do livro para remover: ");

        if (livroService.remover(id)) {
            System.out.println("Livro removido com sucesso.");
            return;
        }

        System.out.println("Livro nao encontrado ou esta emprestado.");
    }

    private void cadastrarUsuario() {
        System.out.println("\nCadastro de usuario");
        String nome = lerTextoObrigatorio("Nome: ");
        String email = lerTextoObrigatorio("Email: ");

        Usuario usuario = usuarioService.cadastrar(nome, email);
        System.out.println("Usuario cadastrado: " + usuario);
    }

    private void listarUsuarios() {
        imprimirLista("Usuarios cadastrados", usuarioService.listar());
    }

    private void buscarUsuarioPorNome() {
        String nome = lerTextoObrigatorio("Digite parte do nome: ");
        imprimirLista("Resultado da busca", usuarioService.buscarPorNome(nome));
    }

    private void realizarEmprestimo() {
        if (livroService.listar().isEmpty() || usuarioService.listar().isEmpty()) {
            System.out.println("Cadastre ao menos um livro e um usuario antes de realizar emprestimos.");
            return;
        }

        listarLivros();
        int idLivro = lerInteiro("ID do livro: ");
        Optional<Livro> livro = livroService.buscarPorId(idLivro);

        if (livro.isEmpty()) {
            System.out.println("Livro nao encontrado.");
            return;
        }

        if (!livro.get().isDisponivel()) {
            System.out.println("Livro indisponivel para emprestimo.");
            return;
        }

        listarUsuarios();
        int idUsuario = lerInteiro("ID do usuario: ");
        Optional<Usuario> usuario = usuarioService.buscarPorId(idUsuario);

        if (usuario.isEmpty()) {
            System.out.println("Usuario nao encontrado.");
            return;
        }

        Emprestimo emprestimo = emprestimoService.emprestar(livro.get(), usuario.get());
        System.out.println("Emprestimo realizado: " + emprestimo);
    }

    private void listarEmprestimos() {
        imprimirLista("Emprestimos", emprestimoService.listar());
    }

    private void devolverLivro() {
        List<Emprestimo> ativos = emprestimoService.listarAtivos();
        imprimirLista("Emprestimos ativos", ativos);

        if (ativos.isEmpty()) {
            return;
        }

        int id = lerInteiro("ID do emprestimo para devolver: ");

        if (emprestimoService.devolver(id)) {
            System.out.println("Livro devolvido com sucesso.");
            return;
        }

        System.out.println("Emprestimo ativo nao encontrado.");
    }

    private int lerInteiro(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String entrada = scanner.nextLine().trim();

            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException erro) {
                System.out.println("Informe um numero valido.");
            }
        }
    }

    private int lerInteiroPositivo(String mensagem) {
        while (true) {
            int valor = lerInteiro(mensagem);

            if (valor > 0) {
                return valor;
            }

            System.out.println("Informe um numero maior que zero.");
        }
    }

    private String lerTextoObrigatorio(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String entrada = scanner.nextLine().trim();

            if (!entrada.isEmpty()) {
                return entrada;
            }

            System.out.println("Este campo e obrigatorio.");
        }
    }

    private void imprimirLista(String titulo, List<?> itens) {
        System.out.println("\n" + titulo);

        if (itens.isEmpty()) {
            System.out.println("Nenhum registro encontrado.");
            return;
        }

        for (Object item : itens) {
            System.out.println(item);
        }
    }
}
