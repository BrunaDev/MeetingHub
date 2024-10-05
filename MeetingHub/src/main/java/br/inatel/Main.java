package br.inatel;

// Import de funções utilizadas no código
import java.util.Scanner;

// Import de classes usadas no código
import br.inatel.Model.*;
import br.inatel.DAO.*;

import javax.xml.crypto.Data;

public class Main {
    public static void main(String[] args) {

        // Instanciando variáveis de entrada e controle
        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        boolean flag2;
        boolean flag3;

        // Instanciando as classes de BD utilizadas
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        RecursoDAO recursoDAO = new RecursoDAO();
        ReservaDAO reservaDAO = new ReservaDAO();
        SalaDAO salaDAO = new SalaDAO();
        SalaHasRecursoDAO salaHasRecursoDAO = new SalaHasRecursoDAO();

        // Informações do Funcionario
        String nome;
        String email;
        String cargo;
        // Informações da reserva
        int sala_id;
        String data_hora_inicio;
        String data_hora_fim;

        // Informações do pedido
        int idPedido;

        // Instanciando a classe para adquirir a data
        //Data data = new Data();

        // MENU PARA SELEÇÃO INICIAL
        while (flag) {
            System.out.println("\n+------------------------------------------------------------+");
            System.out.println("|                   Bem-vindo ao Sistema                     |");
            System.out.println("|                        de Pedidos                          |");
            System.out.println("+------------------------------------------------------------+");
            System.out.println("\nPor favor selecione uma das opções abaixo: ");
            System.out.println("1 - Solicitor sala");
            System.out.println("2 - Solicitar recurso");
            System.out.println("3 - Sair");
            System.out.print("\nOpção: ");
            int op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1:
                    System.out.println("\n==============================================================");
                    System.out.println("                    Solicitação de Sala                       ");
                    System.out.println("==============================================================");

                    System.out.println("\nPor favor, entre com as informações abaixo:");

                    System.out.print("\nEntre com seu nome: ");
                    nome = sc.nextLine();

                    System.out.print("Entre com o seu Cargo: ");
                    cargo = sc.nextLine();

                    System.out.print("Entre com seu email: ");
                    email = sc.nextLine();

                    System.out.print("Qual sala deseja usar? (1 - Sala de reunião; 2 - Sala de recriação; 3 - Sala de apresentação) ");
                    sala_id = sc.nextInt();

                    System.out.print("Horário de início: ");
                    data_hora_inicio = sc.nextLine();

                    System.out.print("Horário previsto para o fim: ");
                    data_hora_fim = sc.nextLine();

                    Funcionario funcionario = new Funcionario(nome, cargo, email);
                    funcionarioDAO.insertFuncionario(funcionario);

                    Reserva reserva = new Reserva(data_hora_inicio, data_hora_fim, sala_id);
                    reservaDAO.insertReserva(reserva);
                    break;

                case 2:
                    System.out.print("\nEntre com o ID da sala: ");
                    sala_id = sc.nextInt();

                    if (reservaDAO.selectReservasPorSala(sala_id)) {
                        System.out.println("\n==============================================================");
                        System.out.println("                    Solicitação de Recursos                   ");
                        System.out.println("==============================================================");

                        flag2 = true;
                        while (flag2) {
                            System.out.println("\nPor favor selecione uma das opções abaixo: ");
                            System.out.println("1 - Gerenciar recursos");
                            System.out.println("2 - Voltar");
                            System.out.print("\nOpção: ");
                            op = sc.nextInt();
                            sc.nextLine();

                            switch (op) {
                                case 1:
                                    flag3 = true;
                                    while (flag3) {
                                        System.out.println("\n==============================================================");
                                        System.out.println("                   Gerenciamento de Recursos                  ");
                                        System.out.println("==============================================================");

                                        System.out.println("1 - Verificar recursos disponíveis");
                                        System.out.println("2 - Solicitar recurso");
                                        System.out.println("3 - Devolução de recurso");
                                        System.out.println("4 - Voltar");

                                        System.out.print("\nOpção: ");
                                        op = sc.nextInt();
                                        sc.nextLine();

                                        switch (op) {
                                            case 1:
                                                System.out.println("\n==============================================================");
                                                System.out.println("                      Recursos Disponíveis                      ");
                                                System.out.println("==============================================================");
                                                recursoDAO.selectRecurso(nome); // Selecionando os livros presentes no BD
                                                break;

                                            case 2:
                                                System.out.println("\n==============================================================");
                                                System.out.println("                        Solicitar Recurso                     ");
                                                System.out.println("==============================================================");

                                                System.out.print("Insira o ID do pedido: ");
                                                idPedido = sc.nextInt();

                                                if (reservaDAO.selectPedidoId(idPedido, cpf)) { // Verificando se o pedido existe no BD
                                                    System.out.print("Insira o ID do livro: ");
                                                    int idLivro = sc.nextInt();
                                                    if (recursoDAO.selectLivroId(idLivro)) { // Verificando se o livro existe no BD
                                                        salaHasRecursoDAO.insertLivroOnPedido(idLivro, idPedido); // Inserindo a relação livro-pedido
                                                        System.out.println("\nLivro adicionado com sucesso ao pedido!");
                                                    }
                                                }
                                                break;

                                            case 3:
                                                System.out.println("\n==============================================================");
                                                System.out.println("                        Remover Recurso                        ");
                                                System.out.println("==============================================================");

                                                System.out.print("Insira o ID do pedido: ");
                                                idPedido = sc.nextInt();

                                                if (reservaDAO.selectPedidoId(idPedido, cpf)) { // Verificando a existência do pedido no BD
                                                    System.out.print("Insira o ID do livro: ");
                                                    int idLivro = sc.nextInt();
                                                    if (salaHasRecursoDAO.deleteLivroFromPedido(idLivro, idPedido)) // Verificando a existência da relação
                                                        System.out.println("\nLivro removido com sucesso!");
                                                    else {
                                                        System.out.println("\nNão há livro de ID: " + idLivro
                                                                + " vinculado ao pedido de ID: " + idPedido);
                                                    }
                                                } else {
                                                    System.out.println("\nID de pedido inválido");
                                                }
                                                break;

                                            case 4:
                                                flag3 = false;
                                                break;
                                        }
                                    }
                                    break;

                                case 2:
                                    flag2 = false;
                                    break;
                            }
                        }
                    } else {
                        System.out.println("\nCPF inválido");
                    }
                    break;

                case 3:
                    flag = false;
                    break;

                default:
                    System.out.println("\nInsira um valor válido!");
                    break;
            }
        }
        System.out.println("\n\tPrograma finalizado");
        sc.close();
    }
}
