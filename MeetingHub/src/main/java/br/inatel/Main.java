package br.inatel;

import br.inatel.DAO.ConnectionDAO;
import br.inatel.DAO.FuncionarioDAO;
import br.inatel.DAO.RecursoDAO;
import br.inatel.DAO.ReservaDAO;
import br.inatel.DAO.SalaDAO;
import br.inatel.DAO.SalaHasRecursoDAO;
import br.inatel.Model.Funcionario;
import br.inatel.Model.Reserva;

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
        SalaHasRecursoDAO salaHasRecursoDAO = new SalaHasRecursoDAO();

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
            System.out.println("4 - Gerenciamento de reservas");
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

                    System.out.print("Entre com o seu email: ");
                    String emailFunc = sc.nextLine();

                    System.out.print("Escolha a sala (1 - Sala de reunião, 2 - Sala de recreação, 3 - Sala de apresentação): ");
                    int Sala_id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Qual o horário da reunião? ");
                    String data_hora_inicio = sc.nextLine();

                    System.out.print("Quando a reunião acaba? ");
                    String data_hora_fim = sc.nextLine();

                    boolean exist = funcionarioDAO.selectFuncionario(emailFunc);

                    if (exist) {
                        Reserva novaReserva = new Reserva(data_hora_inicio, data_hora_fim, Sala_id, emailFunc);
                        int reservaId = reservaDAO.insertReserva(novaReserva);

                        if (reservaId != -1) {
                            boolean updateSuccess = funcionarioDAO.updateFuncionarioReserva(emailFunc, reservaId);

                            if (updateSuccess) {
                                System.out.println("Reserva criada e associada ao funcionário com sucesso!");
                            } else {
                                System.out.println("Erro ao associar a reserva ao funcionário.");
                            }
                        } else {
                            System.out.println("Erro ao criar a reserva.");
                        }
                    } else {
                        System.out.println("Funcionário não encontrado. Falha ao solicitar sala.");
                    }

                    break;

                case 2:
                    System.out.println("\n==============================================================");
                    System.out.println("                       Solicitar Recurso                       ");
                    System.out.println("==============================================================");

                    // mostrar uma lista contendo o nome do recurso, seu id e sua quantidade
                    System.out.println("Todos os recursos:");
                    recursoDAO.listRecurso();

                    System.out.print("Nome do solicitante: ");
                    String nomeSolicitante = sc.nextLine();

                    System.out.print("Informe o ID da sala: ");
                    int salaId = sc.nextInt();

                    System.out.print("Informe o ID do recurso: ");
                    int recursoId = sc.nextInt();

                    // System.out.print("Informe a quantidade necessária: ");
                    // int quantidade = sc.nextInt();

                    boolean sucesso = salaHasRecursoDAO.insertSalaRecurso(salaId, recursoId, nomeSolicitante);

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
                    String nome = sc.nextLine();

                    System.out.print("Informe o email do funcionário: ");
                    String email = sc.nextLine();

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
                    boolean subFlag = true;

                    while (subFlag) {
                        System.out.println("\n==============================================================");
                        System.out.println("                    Gerenciamento de Reservas                 ");
                        System.out.println("==============================================================");

                        System.out.println("Selecione uma opção:");
                        System.out.println("1 - Visualizar reservas por sala");
                        System.out.println("2 - Devolver recurso (deletar relação sala-recurso)");
                        System.out.println("3 - Atualizar horário de reserva");
                        System.out.println("4 - Visualizar todos os agendamentos");
                        System.out.println("5 - Sair");

                        int subOp = sc.nextInt();
                        sc.nextLine();

                        switch (subOp) {
                            case 1:
                                // Visualizar reservas por sala
                                System.out.print("Digite o ID da sala para verificar as reservas: ");
                                int salaIdReserva = sc.nextInt();
                                sc.nextLine();

                                boolean reservasEncontradas = reservaDAO.selectReservasPorSala(salaIdReserva);

                                if (reservasEncontradas) {
                                    System.out.println("Reservas encontradas para a sala " + salaIdReserva + ".");
                                } else {
                                    System.out.println("Nenhuma reserva encontrada para a sala " + salaIdReserva + ".");
                                }
                                break;

                            case 2:
                                // Devolver recurso (remover relação sala-recurso)
                                System.out.print("Digite o ID da sala: ");
                                int salaParaDevolucao = sc.nextInt();
                                sc.nextLine();
                                System.out.print("Digite o ID do recurso a ser devolvido: ");
                                int recursoParaDevolucao = sc.nextInt();
                                sc.nextLine();

                                boolean recursoDevolvido = salaHasRecursoDAO.deleteSalaRecurso(salaParaDevolucao, recursoParaDevolucao);

                                if (recursoDevolvido) {
                                    System.out.println("Recurso devolvido com sucesso.");
                                } else {
                                    System.out.println("Falha ao devolver o recurso.");
                                }
                                break;

                            case 3:
                                // Atualizar horário de reserva
                                System.out.print("Digite o ID da reserva a ser atualizada: ");
                                int reservaId = sc.nextInt();
                                sc.nextLine();

                                System.out.print("Digite o novo horário de início (HH:3mm): ");
                                String novoInicio = sc.nextLine();
                                System.out.print("Digite o novo horário de fim (HH:mm): ");
                                String novoFim = sc.nextLine();

                                boolean horarioAtualizado = reservaDAO.updateHorarioReserva(reservaId, novoInicio, novoFim);

                                if (horarioAtualizado) {
                                    System.out.println("Horário da reserva atualizado com sucesso.");
                                } else {
                                    System.out.println("Falha ao atualizar o horário da reserva.");
                                }
                                break;

                            case 4:
                                // Visualizar todos os agendamentos
                                System.out.println("Todos os agendamentos:");
                                reservaDAO.selectAllReservas();
                                break;

                            case 5:
                                // Ele deve apenas sair desse loop e voltar ao loop do sistema (que engloba tudo)
                                subFlag = false;
                                break;

                            default:
                                System.out.println("Opção inválida! Tente novamente.");
                                break;
                        }
                    }
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