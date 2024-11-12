package pedido;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class AddPedido {
    public static void main(String[] args) throws ParseException {
        Connection conexao = null;
        PreparedStatement stmt = null;
        Scanner scanner = new Scanner(System.in);
        
        SimpleDateFormat formatoBrasileiro = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatoAmericano = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conexao = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/LogoLogo", "root", "root");

            System.out.print("Informe o ID do cliente: ");
            int id_cliente = scanner.nextInt();  
            scanner.nextLine();  

            System.out.print("Data do Pedido (formato DD/MM/AAAA): ");
            String dataBrasileira = scanner.nextLine();

            Date data = formatoBrasileiro.parse(dataBrasileira);
            String dataAmericana = formatoAmericano.format(data);

            System.out.print("Total do Pedido: ");
            double total_pedido = scanner.nextDouble();


            String sql = "INSERT INTO pedido (data_pedido, id_cliente, total_pedido) VALUES (?, ?, ?)";

            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, dataAmericana);  
            stmt.setInt(2, id_cliente); 
            stmt.setDouble(3, total_pedido); 

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Pedido adicionado com sucesso!");
            } else {
                System.out.println("Falha ao adicionar pedido.");
            }

        } catch (ClassNotFoundException ex) {
            System.out.println("Driver do banco de dados não encontrado.");
        } catch (SQLException ex) {
            System.out.println("Erro ao acessar o Banco de Dados: " + ex.getMessage());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
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
