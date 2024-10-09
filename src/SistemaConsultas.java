import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class SistemaConsultas {
  private ArrayList<Paciente> pacientes;

  public SistemaConsultas() {
    pacientes = new ArrayList<>();
  }

  public void incluirPaciente() {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Nome do paciente: ");
    String nome = scanner.nextLine();

    LocalDate dataNascimento = lerData("Data de nascimento (dd/MM/yyyy): ");
    Paciente paciente = new Paciente(nome, dataNascimento);
    pacientes.add(paciente);
    System.out.println("Paciente incluído com sucesso!");
  }

  private LocalDate lerData(String mensagem) {
    Scanner scanner = new Scanner(System.in);
    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    LocalDate data = null;
    boolean dataValida = false;
    while (!dataValida) {
      System.out.print(mensagem);
      String dataStr = scanner.nextLine();
      try {
        data = LocalDate.parse(dataStr, formato);
        dataValida = true;
      } catch (DateTimeParseException e) {
        System.out.println("Data inválida, tente novamente.");
      }
    }
    return data;
  }

  public void alterarPaciente() {
    Paciente paciente = buscarPacientePorNome();
    if (paciente != null) {
      Scanner scanner = new Scanner(System.in);
      System.out.print("Novo nome do paciente: ");
      String novoNome = scanner.nextLine();
      paciente = new Paciente(novoNome, paciente.getDataNascimento());
      System.out.println("Paciente alterado com sucesso!");
    } else {
      System.out.println("Paciente não encontrado!");
    }
  }

  public void realizarAtendimento() {
    Paciente paciente = buscarPacientePorNome();
    if (paciente != null) {
      LocalDate data = lerData("Data do atendimento (dd/MM/yyyy): ");
      Scanner scanner = new Scanner(System.in);
      System.out.print("Descrição do atendimento: ");
      String descricao = scanner.nextLine();
      Atendimento atendimento = new Atendimento(data, descricao);
      paciente.adicionarAtendimento(atendimento);
      System.out.println("Atendimento realizado com sucesso!");
    } else {
      System.out.println("Paciente não encontrado!");
    }
  }

  public void listarPacientes() {
    ArrayList<Paciente> pacientesAtivos = new ArrayList<>();

    for (Paciente paciente : pacientes) {
      if (paciente.isAtivo()) {
        pacientesAtivos.add(paciente);
      }
    }

    if (pacientesAtivos.isEmpty()) {
      System.out.println("Nenhum paciente ativo encontrado.");
      return;
    }

    Scanner scanner = new Scanner(System.in);
    int totalPacientes = pacientesAtivos.size();
    int mostrados = 0;

    while (mostrados < totalPacientes) {
      int limite = Math.min(mostrados + 5, totalPacientes);

      for (int i = mostrados; i < limite; i++) {
        System.out.println(pacientesAtivos.get(i));
      }

      mostrados = limite;

      if (mostrados < totalPacientes) {
        System.out.print("\nPressione Enter para ver mais ou digite 'cancelar' para interromper: ");
        String resposta = scanner.nextLine();
        if (resposta.equalsIgnoreCase("cancelar")) {
          System.out.println("Listagem interrompida.");
          break;
        }
      }
    }
  }


  public void mostrarPaciente() {
    Paciente paciente = buscarPacientePorNome();
    if (paciente != null) {
      System.out.println("Paciente: " + paciente.getNome());
      ArrayList<Atendimento> atendimentos = paciente.getAtendimentos();
      for (int i = 0; i < atendimentos.size(); i++) {
        System.out.println(atendimentos.get(i));
        if ((i + 1) % 5 == 0 && (i + 1) < atendimentos.size()) {
          System.out.println("Pressione Enter para continuar...");
          new Scanner(System.in).nextLine();
        }
      }
    } else {
      System.out.println("Paciente não encontrado!");
    }
  }

  public void apagarPaciente() {
    Paciente paciente = buscarPacientePorNome();
    if (paciente != null) {
      paciente.desativar();
      System.out.println("Paciente removido com sucesso!");
    } else {
      System.out.println("Paciente não encontrado!");
    }
  }

  private Paciente buscarPacientePorNome() {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Nome do paciente: ");
    String nome = scanner.nextLine();
    for (Paciente paciente : pacientes) {
      if (paciente.getNome().equalsIgnoreCase(nome) && paciente.isAtivo()) {
        return paciente;
      }
    }
    System.out.println("Paciente não encontrado.");
    return null;
  }

  public static void main(String[] args) {
    SistemaConsultas sistema = new SistemaConsultas();
    Scanner scanner = new Scanner(System.in);
    int opcao;

    do {
      System.out.println("\nSistema de Consultas Médicas");
      System.out.println("1. Incluir Paciente");
      System.out.println("2. Alterar Paciente");
      System.out.println("3. Realizar Atendimento");
      System.out.println("4. Listar Pacientes");
      System.out.println("5. Mostrar Paciente");
      System.out.println("6. Apagar Paciente");
      System.out.println("0. Sair");
      System.out.print("Escolha uma opção: ");
      opcao = scanner.nextInt();
      scanner.nextLine();  // Limpar o buffer do teclado

      switch (opcao) {
        case 1:
          sistema.incluirPaciente();
          break;
        case 2:
          sistema.alterarPaciente();
          break;
        case 3:
          sistema.realizarAtendimento();
          break;
        case 4:
          sistema.listarPacientes();
          break;
        case 5:
          sistema.mostrarPaciente();
          break;
        case 6:
          sistema.apagarPaciente();
          break;
        case 0:
          System.out.println("Encerrando o sistema.");
          break;
        default:
          System.out.println("Opção inválida.");
      }
    } while (opcao != 0);
  }
}
