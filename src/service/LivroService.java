import java.util.ArrayList;
import java.util.List;
import model.Livro;
import model.Emprestimo;
import model.Users;

public class LivroService {
    private List<Livro> livros;

    public LivroService() {
        this.livros = new ArrayList<>();
    }

    public void adicionarLivro(Livro livro) {
        livros.add(livro);
    }

    public List<Livro> listarLivros() {
        return livros;
    }

    public Livro buscarLivroPorTitulo(String titulo) {
        for (Livro livro : livros) {
            if (livro.getTitulo().equalsIgnoreCase(titulo)) {
                return livro;
            }
        }
        return null; // Retorna null se o livro não for encontrado
    }

    public void removerLivro(Livro livro) {
        livros.remove(livro);
    }
}