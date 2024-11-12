package cliente;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ListarClientes {

    public static void main(String[] args) throws SQLException {
        Connection conexao = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conexao = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/LogoLogo", "root", "root");

            String sql = "SELECT * FROM cliente";
            Statement stmt = conexao.createStatement();
            ResultSet resultado = stmt.executeQuery(sql);

            while (resultado.next()) {
                System.out.printf("ID: %d | Nome: %s | E-mail: %s | CEP: %s | Endereço: %s | Bairro: %s | Cidade: %s | Estado: %s | Telefone: %s%n",
                        resultado.getInt("id_cliente"),  
                        resultado.getString("nome_cliente"), 
                        resultado.getString("email"),
                        resultado.getString("cep"),
                        resultado.getString("endereco"),
                        resultado.getString("bairro"),
                        resultado.getString("cidade"),
                        resultado.getString("estado"),
                        resultado.getString("telefone"));
            }

        } catch (ClassNotFoundException ex) {
            System.out.println("Driver do banco de dados não encontrado.");
        } catch (SQLException ex) {
            System.out.println("Erro ao acessar o Banco de Dados: " + ex.getMessage());
        } finally {
            // Fechamento da conexão
            if (conexao != null) {
                conexao.close();
            }
        }
    }
}
