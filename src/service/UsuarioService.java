import java.util.ArrayList;
import java.util.List;
import model.Users;
import model.Emprestimo;
import model.Livro;


public class UsuarioService {
    private List<Users> usuarios;

    public UsuarioService() {
        this.usuarios = new ArrayList<>();
    }

    public void adicionarUsuario(Users usuario) {
        usuarios.add(usuario);
    }

    public List<Users> listarUsuarios() {
        return usuarios;
    }

    public Users buscarUsuarioPorNome(String nome) {
        for (Users usuario : usuarios) {
            if (usuario.getNome().equalsIgnoreCase(nome)) {
                return usuario;
            }
        }
        return null; // Retorna null se o usuário não for encontrado
    }

    public void removerUsuario(Users usuario) {
        usuarios.remove(usuario);
    }
}