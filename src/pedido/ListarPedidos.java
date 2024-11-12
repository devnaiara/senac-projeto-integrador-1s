package pedido;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

public class ListarPedidos {
    public static void main(String[] args) {
        Connection conexao = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conexao = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/LogoLogo", "root", "root");

            String sql = "SELECT pedido.id_pedido, pedido.data_pedido, pedido.total_pedido, cliente.nome " +
                         "FROM pedido " +
                         "JOIN cliente ON pedido.id_cli = cliente.id_cli";
            Statement stmt = conexao.createStatement();
            ResultSet resultado = stmt.executeQuery(sql);

            SimpleDateFormat formatoBrasileiro = new SimpleDateFormat("dd/MM/yyyy");

            while (resultado.next()) {
                String dataFormatada = formatoBrasileiro.format(resultado.getDate("data_pedido"));
                System.out.printf("ID do Pedido: %d | Data do Pedido: %s | Nome do Cliente: %s | Total do Pedido: %.2f%n",
                        resultado.getInt("id_pedido"),
                        dataFormatada, 
                        resultado.getString("nome"), 
                        resultado.getDouble("total_pedido"));
            }

        } catch (ClassNotFoundException ex) {
            System.out.println("Driver do banco de dados não encontrado.");
        } catch (SQLException ex) {
            System.out.println("Erro ao acessar o Banco de Dados: " + ex.getMessage());
        } finally {
            try {
                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException e) {
                System.out.println("Erro ao fechar a conexão: " + e.getMessage());
            }
        }
    }
}
