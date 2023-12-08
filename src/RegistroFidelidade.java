import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RegistroFidelidade implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Aluno> alunos;

    public List<Aluno> listarAlunos() {
        return alunos;
    }

    public RegistroFidelidade() {
        this.alunos = new ArrayList<>();
    }

    public void adicionarAluno(Aluno aluno) {
        alunos.add(aluno);
    }

    public void removerAluno(Aluno aluno) {
        alunos.remove(aluno);
    }

    public Aluno buscarPorMatricula(String matricula) {
        for (Aluno aluno : alunos) {
            if (aluno.getMatricula().equals(matricula)) {
                return aluno;
            }
        }
        return null;
    }

    public List<Aluno> buscarPorNome(String nome) {
        List<Aluno> resultados = new ArrayList<>();
        for (Aluno aluno : alunos) {
            if (aluno.getNome().toLowerCase().contains(nome.toLowerCase())) {
                resultados.add(aluno);
            }
        }
        return resultados;
    }
}
