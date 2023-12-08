import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Aluno implements Serializable {
    private static final long serialVersionUID = 1L;
    private String matricula;
    private String nome;
    private int pontosFidelidade;

    public Aluno(String matricula, String nome) {
        this.matricula = matricula;
        this.nome = nome;
        this.pontosFidelidade = 0;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPontosFidelidade() {
        return pontosFidelidade;
    }

    public void adicionarPontosFidelidade(int pontos) {
        this.pontosFidelidade += pontos;
    }

    public void retirarPontosFidelidade(int pontos) {
        if (this.pontosFidelidade >= pontos) {
            this.pontosFidelidade -= pontos;
        } else {
            System.out.println("Não há pontos suficientes para retirar.");
        }
    }
}