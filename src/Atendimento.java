import java.time.LocalDate;

public class Atendimento {
  private LocalDate data;
  private String descricao;

  public Atendimento(LocalDate data, String descricao) {
    this.data = data;
    this.descricao = descricao;
  }

  public LocalDate getData() {
    return data;
  }

  public String getDescricao() {
    return descricao;
  }

  @Override
  public String toString() {
    return "Data: " + data + ", Descrição: " + descricao;
  }
}
