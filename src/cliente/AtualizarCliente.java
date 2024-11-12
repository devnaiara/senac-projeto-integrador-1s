package cliente;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class AtualizarCliente {

    public static void main(String[] args) throws SQLException {
        Connection conexao = null;
        Scanner scanner = new Scanner(System.in);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            conexao = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/LogoLogo", "root", "root");

            System.out.print("Digite o ID do cliente a ser atualizado: ");
            int id = scanner.nextInt();
            scanner.nextLine();  

            System.out.print("Deseja atualizar todos os campos? (s/n): ");
            String atualizarTodos = scanner.nextLine();

            String sql = "";
            PreparedStatement stmt = null;

            if (atualizarTodos.equalsIgnoreCase("s")) {
                System.out.print("Nome do cliente: ");
                String nome = scanner.nextLine();
                
                System.out.print("E-mail: ");
                String email = scanner.nextLine();
                
                System.out.print("CEP: ");
                String cep = scanner.nextLine();
    
                System.out.print("Endereço: ");
                String endereco = scanner.nextLine();
    
                System.out.print("Bairro: ");
                String bairro = scanner.nextLine();
    
                System.out.print("Cidade: ");
                String cidade = scanner.nextLine();
    
                System.out.print("Estado: ");
                String estado = scanner.nextLine();
    
                System.out.print("Telefone: ");
                String telefone = scanner.nextLine();

                sql = "UPDATE cliente SET nome_cliente = ?, email = ?, cep = ?, endereco = ?, bairro = ?, cidade = ?, estado = ?, telefone = ? WHERE id_cliente = ?";
                stmt = conexao.prepareStatement(sql);
                stmt.setString(1, nome);
                stmt.setString(2, email);
                stmt.setString(3, cep);
                stmt.setString(4, endereco);
                stmt.setString(5, bairro);
                stmt.setString(6, cidade);
                stmt.setString(7, estado);
                stmt.setString(8, telefone);
                stmt.setInt(9, id);  
            } else {
                System.out.println("Escolha o campo que deseja atualizar:");
                System.out.println("1 - Nome");
                System.out.println("2 - E-mail");
                System.out.println("3 - Cep");
                System.out.println("4 - Endereço");
                System.out.println("5 - Bairro");             
                System.out.println("6 - Cidade");
                System.out.println("7 - Estado");
                System.out.println("8 - Telefone");

                int opcao = scanner.nextInt();
                scanner.nextLine();  // Limpar o buffer  

                switch (opcao) {
                    case 1 -> {
                        System.out.print("Novo nome do cliente: ");
                        String nome = scanner.nextLine();
                        sql = "UPDATE cliente SET nome_cliente = ? WHERE id_cliente = ?";
                        stmt = conexao.prepareStatement(sql);
                        stmt.setString(1, nome);
                        stmt.setInt(2, id);
                    }
                    case 2 -> {
                        System.out.print("Novo e-mail do cliente: ");
                        String email = scanner.nextLine();
                        sql = "UPDATE cliente SET email = ? WHERE id_cliente = ?";
                        stmt = conexao.prepareStatement(sql);
                        stmt.setString(1, email);
                        stmt.setInt(2, id);
                    }
                    case 3 -> {
                        System.out.print("Novo CEP do cliente: ");
                        String cep = scanner.nextLine();
                        sql = "UPDATE cliente SET cep = ? WHERE id_cliente = ?";
                        stmt = conexao.prepareStatement(sql);
                        stmt.setString(1, cep);
                        stmt.setInt(2, id);
                    }
                    case 4 -> {
                        System.out.print("Novo endereço do cliente: ");
                        String endereco = scanner.nextLine();
                        sql = "UPDATE cliente SET endereco = ? WHERE id_cliente = ?";
                        stmt = conexao.prepareStatement(sql);
                        stmt.setString(1, endereco);
                        stmt.setInt(2, id);
                    }
                    case 5 -> {
                        System.out.print("Novo bairro do cliente: ");
                        String bairro = scanner.nextLine();
                        sql = "UPDATE cliente SET bairro = ? WHERE id_cliente = ?";
                        stmt = conexao.prepareStatement(sql);
                        stmt.setString(1, bairro);
                        stmt.setInt(2, id);
                    }
                    case 6 -> {
                        System.out.print("Nova cidade do cliente: ");
                        String cidade = scanner.nextLine();
                        sql = "UPDATE cliente SET cidade = ? WHERE id_cliente = ?";
                        stmt = conexao.prepareStatement(sql);
                        stmt.setString(1, cidade);
                        stmt.setInt(2, id);
                    }
                    case 7 -> {
                        System.out.print("Novo estado do cliente: ");
                        String estado = scanner.nextLine();
                        sql = "UPDATE cliente SET estado = ? WHERE id_cliente = ?";
                        stmt = conexao.prepareStatement(sql);
                        stmt.setString(1, estado);
                        stmt.setInt(2, id);
                    }
                    case 8 -> {
                        System.out.print("Novo telefone do cliente: ");
                        String telefone = scanner.nextLine();
                        sql = "UPDATE cliente SET telefone = ? WHERE id_cliente = ?";
                        stmt = conexao.prepareStatement(sql);
                        stmt.setString(1, telefone);
                        stmt.setInt(2, id);
                    }
                    default -> {
                        System.out.println("Opção inválida.");
                        return;
                    }
                }
            }

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Cliente atualizado com sucesso!");
            } else {
                System.out.println("Cliente não encontrado.");
            }

        } catch (ClassNotFoundException ex) {
            System.out.println("Driver do banco de dados não encontrado.");
        } catch (SQLException ex) {
            System.out.println("Erro ao acessar o Banco de Dados: " + ex.getMessage());
        } finally {
            if (conexao != null) {
                conexao.close();
            }
            scanner.close();
        }
    }
}
