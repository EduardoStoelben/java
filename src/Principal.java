import java.util.List;
import java.util.Scanner;

public class Principal {
    private static final Scanner scanner = new Scanner(System.in);
    private static final GerenciadorFidelidade gerenciador = new GerenciadorFidelidade();

    public static void main(String[] args) {
        boolean executando = true;

        while (executando) {
            System.out.println(" Sistema de Fidelidade de Cafés ");
            System.out.println("1. Cadastrar Aluno");
            System.out.println("2. Atualizar Aluno");
            System.out.println("3. Remover Aluno");
            System.out.println("4. Buscar Aluno por Matrícula");
            System.out.println("5. Buscar Aluno por Nome");
            System.out.println("6. Listar Todos os Alunos");
            System.out.println("7. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarAluno();
                    break;
                case 2:
                    atualizarAluno();
                    break;
                case 3:
                    removerAluno();
                    break;
                case 4:
                    buscarPorMatricula();
                    break;
                case 5:
                    buscarPorNome();
                    break;
                case 6:
                    listarAlunos();
                    break;
                case 7:
                    executando = false;
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }
    }

    private static void cadastrarAluno() {
        System.out.print("Digite a matrícula do aluno: ");
        String matricula = scanner.nextLine();

        System.out.print("Digite o nome do aluno: ");
        String nome = scanner.nextLine();

        gerenciador.cadastrarAluno(matricula, nome);
        System.out.println("Aluno cadastrado com sucesso!");
    }

    private static void atualizarAluno() {
        System.out.print("Digite a matrícula do aluno que deseja atualizar: ");
        String matricula = scanner.nextLine();

        System.out.print("Digite o novo nome do aluno: ");
        String novoNome = scanner.nextLine();

        gerenciador.atualizarAluno(matricula, novoNome);
        System.out.println("Aluno atualizado com sucesso!");
    }

    private static void removerAluno() {
        System.out.print("Digite a matrícula do aluno que deseja remover: ");
        String matricula = scanner.nextLine();

        gerenciador.removerAluno(matricula);
        System.out.println("Aluno removido com sucesso!");
    }

    private static void buscarPorMatricula() {
        System.out.print("Digite a matrícula do aluno: ");
        String matricula = scanner.nextLine();

        Aluno alunoEncontrado = gerenciador.buscarAlunoPorMatricula(matricula);
        if (alunoEncontrado != null) {
            System.out.println("Aluno encontrado:");
            System.out.println("Matrícula: " + alunoEncontrado.getMatricula());
            System.out.println("Nome: " + alunoEncontrado.getNome());
        } else {
            System.out.println("Aluno não encontrado!");
        }
    }

    private static void buscarPorNome() {
        System.out.print("Digite o nome do aluno: ");
        String nome = scanner.nextLine();

        List<Aluno> alunosEncontrados = gerenciador.buscarAlunoPorNome(nome);
        if (!alunosEncontrados.isEmpty()) {
            System.out.println("Alunos encontrados:");
            for (Aluno aluno : alunosEncontrados) {
                System.out.println("Matrícula: " + aluno.getMatricula() + ", Nome: " + aluno.getNome());
            }
        } else {
            System.out.println("Nenhum aluno encontrado com esse nome!");
        }
    }

    private static void listarAlunos() {
        List<Aluno> alunos = gerenciador.listarAlunos();
        if (!alunos.isEmpty()) {
            System.out.println("Lista de Alunos:");
            for (Aluno aluno : alunos) {
                System.out.println("Matrícula: " + aluno.getMatricula() + ", Nome: " + aluno.getNome());
            }
        } else {
            System.out.println("Não há alunos cadastrados!");
        }
    }
}
