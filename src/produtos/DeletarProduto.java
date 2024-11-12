package produtos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class DeletarProduto {

    public static void main(String[] args) throws SQLException {
        Connection conexao = null;
        Scanner scanner = new Scanner(System.in);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conexao = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/LogoLogo", "root", "root");

            System.out.print("Informe o ID do produto que deseja excluir: ");
            int idProduto = scanner.nextInt();

            String sqlCheck = "SELECT * FROM produto WHERE id_produto = ?";
            PreparedStatement stmtCheck = conexao.prepareStatement(sqlCheck);
            stmtCheck.setInt(1, idProduto);

            ResultSet rs = stmtCheck.executeQuery();
            if (!rs.next()) {
                System.out.println("Produto não encontrado. Verifique o ID e tente novamente.");
                return; 
            }

            String sqlDelete = "DELETE FROM produto WHERE id_produto = ?";
            PreparedStatement stmtDelete = conexao.prepareStatement(sqlDelete);
            stmtDelete.setInt(1, idProduto);

            int linhasAfetadas = stmtDelete.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Produto excluído com sucesso!");
            } else {
                System.out.println("Falha ao excluir produto. Tente novamente.");
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
