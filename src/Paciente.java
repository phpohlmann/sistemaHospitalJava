import java.time.LocalDate;
import java.util.ArrayList;

public class Paciente {
  private String nome;
  private LocalDate dataNascimento;
  private ArrayList<Atendimento> atendimentos;
  private boolean ativo;

  public Paciente(String nome, LocalDate dataNascimento) {
    this.nome = nome;
    this.dataNascimento = dataNascimento;
    this.atendimentos = new ArrayList<>();
    this.ativo = true;
  }

  public String getNome() {
    return nome;
  }

  public LocalDate getDataNascimento() {
    return dataNascimento;
  }

  public ArrayList<Atendimento> getAtendimentos() {
    return atendimentos;
  }

  public void adicionarAtendimento(Atendimento atendimento) {
    this.atendimentos.add(atendimento);
  }

  public boolean isAtivo() {
    return ativo;
  }

  public void desativar() {
    this.ativo = false;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Paciente paciente = (Paciente) obj;
    return nome.equals(paciente.nome);
  }

  @Override
  public String toString() {
    return "Nome: " + nome + ", Data de Nascimento: " + dataNascimento + ", Ativo: " + ativo;
  }
}
