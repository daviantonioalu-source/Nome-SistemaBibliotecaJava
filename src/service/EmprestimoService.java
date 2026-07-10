import model.Emprestimo;
import model.Livro;
import model.Users;
import java.util.ArrayList;
import java.util.List;

public class EmprestimoService {
    private List<Emprestimo> emprestimos;

    public EmprestimoService() {
        this.emprestimos = new ArrayList<>();
    }

    public void adicionarEmprestimo(Emprestimo emprestimo) {
        emprestimos.add(emprestimo);
    }

    public List<Emprestimo> listarEmprestimos() {
        return emprestimos;
    }

    public Emprestimo buscarEmprestimoPorLivro(Livro livro) {
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getLivro().equals(livro)) {
                return emprestimo;
            }
        }
        return null; // Retorna null se o empréstimo não for encontrado
    }

    public void removerEmprestimo(Emprestimo emprestimo) {
        emprestimos.remove(emprestimo);
    }
    public void devolverLivro(Emprestimo emprestimo) {
        emprestimos.remove(emprestimo);
    }
    public List<Emprestimo> listarEmprestimosPorUsuario(Users usuario) {
        List<Emprestimo> emprestimosUsuario = new ArrayList<>();
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getUsuario().equals(usuario)) {
                emprestimosUsuario.add(emprestimo);
            }
        }
        return emprestimosUsuario;
    }

}
