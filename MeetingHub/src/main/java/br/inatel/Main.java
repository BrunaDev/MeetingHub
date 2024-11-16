package br.inatel;

import br.inatel.DAO.ConnectionDAO;
import br.inatel.DAO.FuncionarioDAO;
import br.inatel.DAO.RecursoDAO;
import br.inatel.DAO.ReservaDAO;
import br.inatel.DAO.SalaDAO;
import br.inatel.Model.Funcionario;
import br.inatel.Model.Recurso;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ConnectionDAO connectionDAO = new ConnectionDAO();
        connectionDAO.connectToDB();

        // Instanciando DAOs
        SalaDAO salaDAO = new SalaDAO();
        ReservaDAO reservaDAO = new ReservaDAO();
        RecursoDAO recursoDAO = new RecursoDAO();
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

        Scanner sc = new Scanner(System.in);
        boolean flag = true;

        while (flag) {
            System.out.println("\n+------------------------------------------------------------+");
            System.out.println("|                       Bem vindo ao                         |");
            System.out.println("|                   Sistema do MeetingHub                    |");
            System.out.println("+------------------------------------------------------------+");
            System.out.println("\nPor favor selecione uma das opções abaixo: ");
            System.out.println("1 - Solicitar sala para reunião");
            System.out.println("2 - Solicitar recursos");
            System.out.println("3 - Adicionar novo funcionário");
            System.out.println("4 - Verificar reservas");
            System.out.println("5 - Sair");
            System.out.print("\nOpção: ");
            int op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1:
                    System.out.println("\n==============================================================");
                    System.out.println("                       Solicitar Sala                          ");
                    System.out.println("==============================================================");

                    System.out.println("\nPor favor entre com as informações abaixo: ");
                    System.out.print("\nEntre com seu nome: ");
                    String nome = sc.nextLine();

                    System.out.print("Entre com o seu email: ");
                    String email = sc.nextLine();

                    System.out.print("Escolha a sala (1 - Sala de reunião, 2 - Sala de recreação, 3 - Sala de apresentação): ");
                    int nomeSala = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Qual o horário da reunião? ");
                    String data_hora_inicio = sc.nextLine();

                    System.out.print("Quando a reunião acaba? ");
                    String data_hora_fim = sc.nextLine();

                    Funcionario funcionario = new Funcionario(nome, email, "");
                    funcionarioDAO.insertFuncionario(funcionario);

                    break;

                case 2:
                    System.out.println("\n==============================================================");
                    System.out.println("                       Solicitar Recurso                       ");
                    System.out.println("==============================================================");

                    System.out.print("Informe o nome do recurso: ");
                    String nomeRecurso = sc.nextLine();

                    System.out.print("Informe a quantidade necessária: ");
                    String quantidade = sc.nextLine();

                    // Criação do recurso
                    Recurso recurso = new Recurso(nomeRecurso, quantidade);

                    // Inserir o recurso no banco de dados
                    boolean sucesso = recursoDAO.insertRecurso(recurso);

                    if (sucesso) {
                        System.out.println("Recurso solicitado com sucesso!");
                    } else {
                        System.out.println("Falha ao solicitar o recurso.");
                    }

                    break;

                case 3:
                    System.out.println("\n==============================================================");
                    System.out.println("                  Adicionar Novo Funcionário                  ");
                    System.out.println("==============================================================");

                    System.out.print("Informe o nome do funcionário: ");
                    nome = sc.nextLine();

                    System.out.print("Informe o email do funcionário: ");
                    email = sc.nextLine();

                    System.out.print("Informe o cargo do funcionário: ");
                    String cargo = sc.nextLine();

                    Funcionario novoFuncionario = new Funcionario(nome, email, cargo);
                    boolean funcionarioSucesso = funcionarioDAO.insertFuncionario(novoFuncionario);

                    if (funcionarioSucesso) {
                        System.out.println("Funcionário adicionado com sucesso!");
                    } else {
                        System.out.println("Falha ao adicionar o funcionário.");
                    }
                    break;

                case 4:
                    break;

                case 5:
                    System.out.println("Saindo do sistema. Até logo!");
                    flag = false;
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente.");
                    break;
            }
        }
        sc.close();
    }
}