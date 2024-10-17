package br.inatel;

import java.util.Scanner;
import br.inatel.Model.*;
import br.inatel.DAO.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        boolean flag2;
        boolean flag3;

        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        RecursoDAO recursoDAO = new RecursoDAO();
        ReservaDAO reservaDAO = new ReservaDAO();
        SalaDAO salaDAO = new SalaDAO();
        SalaHasRecursoDAO salaHasRecursoDAO = new SalaHasRecursoDAO();

        String nome, email, cargo;
        int sala_id;
        String data_hora_inicio, data_hora_fim;
        int idPedido;

        while (flag) {
            System.out.println("\n+------------------------------------------------------------+");
            System.out.println("|                   Bem-vindo ao Sistema                     |");
            System.out.println("|                de Reservas e Gerenciamento                 |");
            System.out.println("+------------------------------------------------------------+");
            System.out.println("\nPor favor selecione uma das opções abaixo: ");
            System.out.println("1 - Solicitar sala");
            System.out.println("2 - Gerenciar recursos");
            System.out.println("3 - Sair");
            System.out.print("\nOpção: ");
            int op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1:
                    System.out.println("\n================ Solicitação de Sala ====================");
                    System.out.println("\nPor favor, entre com as informações:");

                    System.out.print("Nome: ");
                    nome = sc.nextLine();

                    System.out.print("Cargo: ");
                    cargo = sc.nextLine();

                    System.out.print("Email: ");
                    email = sc.nextLine();

                    System.out.println("Escolha a sala (1 - Sala de reunião, 2 - Sala de recreação, 3 - Sala de apresentação): ");
                    sala_id = sc.nextInt();
                    sc.nextLine(); // Limpar o buffer do scanner

                    System.out.print("Data e hora de início (formato: AAAA-MM-DD HH:MM): ");
                    data_hora_inicio = sc.nextLine();

                    System.out.print("Data e hora de término (formato: AAAA-MM-DD HH:MM): ");
                    data_hora_fim = sc.nextLine();

                    Funcionario funcionario = new Funcionario(nome, email, cargo);
                    funcionarioDAO.insertFuncionario(funcionario);

                    Reserva reserva = new Reserva(data_hora_inicio, data_hora_fim, sala_id);
                    reservaDAO.insertReserva(reserva);
                    System.out.println("Sala reservada com sucesso!");
                    break;

                case 2:
                    System.out.print("\nEntre com o ID da sala para gerenciar recursos: ");
                    sala_id = sc.nextInt();

                    if (reservaDAO.selectReservasPorSala(sala_id)) {
                        System.out.println("\n================ Gerenciamento de Recursos =================");

                        flag2 = true;
                        while (flag2) {
                            System.out.println("\n1 - Verificar recursos disponíveis");
                            System.out.println("2 - Solicitar recurso");
                            System.out.println("3 - Devolver recurso");
                            System.out.println("4 - Voltar");

                            System.out.print("\nOpção: ");
                            op = sc.nextInt();
                            sc.nextLine();

                            switch (op) {
                                case 1:
                                    System.out.println("\n================ Recursos Disponíveis ==================");
                                    recursoDAO.selectRecursoPorSala(sala_id);
                                    break;

                                case 2:
                                    System.out.print("Insira o nome do recurso que deseja solicitar: ");
                                    String nomeRecurso = sc.nextLine();
                                    salaHasRecursoDAO.solicitarRecurso(sala_id, nomeRecurso);
                                    break;

                                case 3:
                                    System.out.print("Insira o nome do recurso que deseja devolver: ");
                                    nomeRecurso = sc.nextLine();
                                    salaHasRecursoDAO.devolverRecurso(sala_id, nomeRecurso);
                                    break;

                                case 4:
                                    flag2 = false;
                                    break;
                            }
                        }
                    } else {
                        System.out.println("Nenhuma reserva encontrada para essa sala.");
                    }
                    break;

                case 3:
                    flag = false;
                    break;

                default:
                    System.out.println("Opção inválida, tente novamente.");
                    break;
            }
        }
        System.out.println("\nPrograma finalizado.");
        sc.close();
    }
}
