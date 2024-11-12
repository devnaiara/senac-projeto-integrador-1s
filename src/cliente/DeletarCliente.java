package cliente;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class DeletarCliente {
    public static void main(String[] args) throws SQLException {
        Connection conexao = null;
        Scanner scanner = new Scanner(System.in);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conexao = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/LogoLogo", "root", "root");

            System.out.print("Informe o ID do cliente que deseja excluir: ");
            int idCliente = scanner.nextInt();

            String sqlDelete = "DELETE FROM cliente WHERE id_cliente = ?";

            PreparedStatement stmtDelete = conexao.prepareStatement(sqlDelete);
            stmtDelete.setInt(1, idCliente); // Define o ID do cliente na query

            int linhasAfetadas = stmtDelete.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Cliente excluído com sucesso!");
            } else {
                System.out.println("Falha ao excluir cliente. Verifique o ID e tente novamente.");
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
