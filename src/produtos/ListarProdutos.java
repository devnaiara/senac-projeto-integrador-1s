package produtos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ListarProdutos {

    public static void main(String[] args) {
        Connection conexao = null;
        Statement stmt = null;
        ResultSet resultado = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conexao = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/LogoLogo", "root", "root");

            String sql = "SELECT id_produto, nome_produto, descricao, preco, cor, estoque FROM produto";
            stmt = conexao.createStatement();
            resultado = stmt.executeQuery(sql);

            while (resultado.next()) {
                System.out.printf("ID: %d | Nome: %s | Descrição: %s | Preço: %.2f | Cor: %s | Estoque: %d%n",
                        resultado.getInt("id_produto"),
                        resultado.getString("nome_produto"),
                        resultado.getString("descricao"),
                        resultado.getDouble("preco"),
                        resultado.getString("cor"),
                        resultado.getInt("estoque"));
            }

        } catch (ClassNotFoundException ex) {
            System.out.println("Driver do banco de dados não encontrado.");
        } catch (SQLException ex) {
            System.out.println("Erro ao acessar o Banco de Dados: " + ex.getMessage());
        } finally {
            try {
                if (resultado != null) {
                    resultado.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException ex) {
                System.out.println("Erro ao fechar os recursos: " + ex.getMessage());
            }
        }
    }
}
