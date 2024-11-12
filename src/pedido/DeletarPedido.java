package pedido;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class DeletarPedido {

    public static void main(String[] args) {
        Connection conexao = null;
        PreparedStatement stmtDelete = null;
        Scanner scanner = new Scanner(System.in);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conexao = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/LogoLogo", "root", "root");

            System.out.print("Informe o ID do pedido que deseja excluir: ");
            int idPedido = scanner.nextInt();

            String sqlDelete = "DELETE FROM pedido WHERE id_pedido = ?";

            stmtDelete = conexao.prepareStatement(sqlDelete);
            stmtDelete.setInt(1, idPedido);

            int linhasAfetadas = stmtDelete.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Pedido excluído com sucesso!");
            } else {
                System.out.println("Falha ao excluir pedido. Verifique o ID e tente novamente.");
            }

        } catch (ClassNotFoundException ex) {
            System.out.println("Driver do banco de dados não encontrado.");
        } catch (SQLException ex) {
            System.out.println("Erro ao acessar o Banco de Dados: " + ex.getMessage());
        } finally {
            // Fechar recursos no final
            try {
                if (stmtDelete != null) {
                    stmtDelete.close();
                }
                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException e) {
                System.out.println("Erro ao fechar os recursos: " + e.getMessage());
            }
            scanner.close();
        }
    }
}
