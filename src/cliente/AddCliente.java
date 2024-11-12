package cliente;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class AddCliente {
    public static void main(String[] args) throws SQLException {
        Connection conexao = null;
        Scanner scanner = new Scanner(System.in);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conexao = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/LogoLogo", "root", "root");

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

            String sql = "INSERT INTO cliente (nome_cliente, email, cep, endereco, bairro, cidade, estado, telefone) " +
                         "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, nome);        // Nome do cliente
            stmt.setString(2, email);       // E-mail
            stmt.setString(3, cep);         // CEP
            stmt.setString(4, endereco);    // Endereço
            stmt.setString(5, bairro);      // Bairro
            stmt.setString(6, cidade);      // Cidade
            stmt.setString(7, estado);      // Estado
            stmt.setString(8, telefone);    // Telefone

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Cliente adicionado com sucesso.");
            } else {
                System.out.println("Falha ao adicionar cliente.");
            }

        } catch (ClassNotFoundException ex) {
            System.out.println("Driver do banco de dados não encontrado.");
        } catch (SQLException ex) {
            System.out.println("Erro ao acessar o Banco de Dados: " + ex.getMessage());
        } finally {
            // Fechar conexão e scanner
            if (conexao != null) {
                conexao.close();
            }
            scanner.close();
        }
    }
}
