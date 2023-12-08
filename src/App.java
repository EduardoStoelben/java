import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class App extends JFrame {
    private final GerenciadorFidelidade gerenciador;
    private JTextField matriculaField;
    private JTextField nomeField;
    private JTextArea outputArea;

    public App() {
        gerenciador = new GerenciadorFidelidade();

        setTitle("Sistema de Fidelidade");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLayout(new BorderLayout());

        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel panelCadastrar = criarPanelCadastrar();
        JPanel panelAtualizar = criarPanelAtualizar();
        JPanel panelRemover = criarPanelRemover();
        JPanel panelPontos = criarPanelPontos();
        JPanel panelBuscarMatricula = criarPanelBuscarMatricula();
        JPanel panelBuscarNome = criarPanelBuscarNome();

        tabbedPane.addTab("Cadastrar", panelCadastrar);
        tabbedPane.addTab("Atualizar", panelAtualizar);
        tabbedPane.addTab("Remover", panelRemover);
        tabbedPane.addTab("Adicionar/Retirar Pontos", panelPontos);
        tabbedPane.addTab("Buscar por Matrícula", panelBuscarMatricula);
        tabbedPane.addTab("Buscar por Nome", panelBuscarNome);

        add(tabbedPane, BorderLayout.CENTER);

        outputArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(outputArea);
        add(scrollPane, BorderLayout.SOUTH);

        atualizarOutput();
    }

    private JPanel criarPanelCadastrar() {
        JPanel panel = new JPanel(new GridLayout(4, 2));

        JLabel matriculaLabel = new JLabel("Matrícula:");
        JTextField matriculaField = new JTextField(15);
        JLabel nomeLabel = new JLabel("Nome:");
        JTextField nomeField = new JTextField(15);

        JButton cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String matricula = matriculaField.getText();
                String nome = nomeField.getText();
                if (!matricula.isEmpty() && !nome.isEmpty()) {
                    gerenciador.cadastrarAluno(matricula, nome);
                    atualizarOutput();
                } else {
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        panel.add(matriculaLabel);
        panel.add(matriculaField);
        panel.add(nomeLabel);
        panel.add(nomeField);
        panel.add(new JLabel());
        panel.add(new JLabel());
        panel.add(cadastrarButton);

        return panel;
    }

    private JPanel criarPanelAtualizar() {
        JPanel panel = new JPanel(new GridLayout(4, 2));

        JLabel matriculaLabel = new JLabel("Matrícula:");
        JTextField matriculaField = new JTextField(15);
        JLabel nomeLabel = new JLabel("Novo Nome:");
        JTextField nomeField = new JTextField(15);

        JButton buscarButton = new JButton("Buscar");
        buscarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String matricula = matriculaField.getText();
                Aluno aluno = gerenciador.buscarAlunoPorMatricula(matricula);
                if (aluno != null) {
                    nomeField.setText(aluno.getNome());
                } else {
                    JOptionPane.showMessageDialog(null, "Aluno não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton atualizarButton = new JButton("Atualizar");
        atualizarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String matricula = matriculaField.getText();
                String novoNome = nomeField.getText();
                if (!novoNome.isEmpty()) {
                    gerenciador.atualizarAluno(matricula, novoNome);
                    atualizarOutput();
                } else {
                    JOptionPane.showMessageDialog(null, "Preencha o novo nome!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        panel.add(matriculaLabel);
        panel.add(matriculaField);
        panel.add(nomeLabel);
        panel.add(nomeField);
        panel.add(buscarButton);
        panel.add(atualizarButton);

        return panel;
    }

    private JPanel criarPanelRemover() {
        JPanel panel = new JPanel(new GridLayout(2, 2));

        JLabel matriculaLabel = new JLabel("Matrícula:");
        JTextField matriculaField = new JTextField(15);

        JButton removerButton = new JButton("Remover");
        removerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String matricula = matriculaField.getText();
                gerenciador.removerAluno(matricula);
                atualizarOutput();
            }
        });

        panel.add(matriculaLabel);
        panel.add(matriculaField);
        panel.add(new JLabel());
        panel.add(removerButton);

        return panel;
    }

    private JPanel criarPanelPontos() {
        JPanel panel = new JPanel(new GridLayout(3, 2));

        JLabel matriculaLabel = new JLabel("Matrícula:");
        JTextField matriculaField = new JTextField(15);

        JLabel pontosLabel = new JLabel("Pontos:");
        JTextField pontosField = new JTextField(15);

        JButton adicionarPontosButton = new JButton("Adicionar Pontos");
        adicionarPontosButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String matricula = matriculaField.getText();
                String pontosStr = pontosField.getText();
                if (!matricula.isEmpty() && !pontosStr.isEmpty()) {
                    int pontos = Integer.parseInt(pontosStr);
                    gerenciador.adicionarPontosFidelidade(matricula, pontos);
                    atualizarOutput();
                } else {
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton retirarPontosButton = new JButton("Retirar Pontos");
        retirarPontosButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String matricula = matriculaField.getText();
                String pontosStr = pontosField.getText();
                if (!matricula.isEmpty() && !pontosStr.isEmpty()) {
                    int pontos = Integer.parseInt(pontosStr);
                    gerenciador.retirarPontosFidelidade(matricula, pontos);
                    atualizarOutput();
                } else {
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        panel.add(matriculaLabel);
        panel.add(matriculaField);
        panel.add(pontosLabel);
        panel.add(pontosField);
        panel.add(adicionarPontosButton);
        panel.add(retirarPontosButton);

        return panel;
    }

    private JPanel criarPanelBuscarMatricula() {
        JPanel panel = new JPanel(new GridLayout(2, 2));

        JLabel matriculaLabel = new JLabel("Matrícula:");
        matriculaField = new JTextField();

        JButton buscarPorMatriculaButton = new JButton("Buscar por Matrícula");
        buscarPorMatriculaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Aluno aluno = gerenciador.buscarAlunoPorMatricula(matriculaField.getText());
                if (aluno != null) {
                    exibirAluno(aluno);
                } else {
                    outputArea.setText("Aluno não encontrado.");
                }
            }
        });

        panel.add(matriculaLabel);
        panel.add(matriculaField);
        panel.add(new JLabel());
        panel.add(buscarPorMatriculaButton);

        return panel;
    }

    private JPanel criarPanelBuscarNome() {
        JPanel panel = new JPanel(new GridLayout(2, 2));

        JLabel nomeLabel = new JLabel("Nome:");
        nomeField = new JTextField();

        JButton buscarPorNomeButton = new JButton("Buscar por Nome");
        buscarPorNomeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List<Aluno> alunos = gerenciador.buscarAlunoPorNome(nomeField.getText());
                if (!alunos.isEmpty()) {
                    exibirAlunos(alunos);
                } else {
                    outputArea.setText("Nenhum aluno encontrado.");
                }
            }
        });

        panel.add(nomeLabel);
        panel.add(nomeField);
        panel.add(new JLabel());
        panel.add(buscarPorNomeButton);

        return panel;
    }

    private void atualizarOutput() {
        List<Aluno> alunos = gerenciador.listarAlunos();
        exibirAlunos(alunos);
    }

    private void exibirAluno(Aluno aluno) {
        outputArea.setText("Matrícula: " + aluno.getMatricula() + "\nNome: " + aluno.getNome() + "\nPontos: " + aluno.getPontosFidelidade());
    }

    private void exibirAlunos(List<Aluno> alunos) {
        StringBuilder resultado = new StringBuilder("Lista de Alunos:\n");
        for (Aluno aluno : alunos) {
            resultado.append("Matrícula: ").append(aluno.getMatricula()).append(", Nome: ").append(aluno.getNome()).append(", Pontos: ").append(aluno.getPontosFidelidade()).append("\n");
        }
        outputArea.setText(resultado.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            App app = new App();
            app.setVisible(true);
        });
    }
}
