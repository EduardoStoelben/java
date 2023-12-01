import java.io.*;
import java.util.List;

public class GerenciadorFidelidade {
    private RegistroFidelidade registro;
    private final String arquivoDados = "dados.txt";

    public GerenciadorFidelidade() {
        registro = carregarDados();
    }

    public void cadastrarAluno(String matricula, String nome) {
        Aluno novoAluno = new Aluno(matricula, nome);
        registro.adicionarAluno(novoAluno);
        salvarDados();
    }

    public void atualizarAluno(String matricula, String novoNome) {
        Aluno aluno = registro.buscarPorMatricula(matricula);
        if (aluno != null) {
            aluno.setNome(novoNome);
            salvarDados();
        }
    }

    public void removerAluno(String matricula) {
        Aluno aluno = registro.buscarPorMatricula(matricula);
        if (aluno != null) {
            registro.removerAluno(aluno);
            salvarDados();
        }
    }

    public Aluno buscarAlunoPorMatricula(String matricula) {
        return registro.buscarPorMatricula(matricula);
    }

    public List<Aluno> buscarAlunoPorNome(String nome) {
        return registro.buscarPorNome(nome);
    }

    public List<Aluno> listarAlunos() {
        return registro.listarAlunos();
    }

    private void salvarDados() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(arquivoDados))) {
            out.writeObject(registro);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private RegistroFidelidade carregarDados() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(arquivoDados))) {
            return (RegistroFidelidade) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new RegistroFidelidade();
        }
    }
}
